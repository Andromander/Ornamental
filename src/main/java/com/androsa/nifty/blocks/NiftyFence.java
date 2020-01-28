package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class NiftyFence extends FenceBlock {

    protected final VoxelShape[] collisionShapes = this.makeShapes(2.0F, 2.0F, 23.0F, 0.0F, 23.0F);
    protected final VoxelShape[] shapes = this.makeShapes(2.0F, 2.0F, 15.0F, 0.0F, 15.0F);

    private float fallDamage;
    private NiftyBlock blockType;

    public NiftyFence(Properties props, NiftyBlock block) {
        super(props.hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));
        if (block == NiftyBlock.ICE) props.nonOpaque();

        this.fallDamage = block.multiplier;
        this.blockType = block;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return blockType == NiftyBlock.PATH ? this.shapes[this.getIndex(state)] : super.getShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return blockType == NiftyBlock.PATH ? this.collisionShapes[this.getIndex(state)] : super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, Entity entity) {
        switch (blockType) {
            case ICE:
            case PACKED_ICE:
                return 0.98F;
            case BLUE_ICE:
                return 0.989F;
            default:
                return super.getSlipperiness(state, world, pos, entity);
        }
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return blockType == NiftyBlock.ICE;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.handleFallDamage(fallDistance, fallDamage);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return blockType == NiftyBlock.REDSTONE;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return blockType == NiftyBlock.REDSTONE ? 5 : 0;
    }

    @Override
    public ActionResultType onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        switch (blockType) {
            case DIRT:
                if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
                    this.setBlock(worldIn, pos, ModBlocks.grass_fence);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                }
                return super.onUse(state, worldIn, pos, player, hand, result);

            case GRASS:
                if (!itemstack.isEmpty()) {
                    if (itemstack.getItem() instanceof HoeItem) {
                        this.setBlock(worldIn, pos, ModBlocks.dirt_fence);
                        worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    } else if (itemstack.getItem() instanceof ShovelItem) {
                        this.setBlock(worldIn, pos, ModBlocks.path_fence);
                        worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    }
                }
                return super.onUse(state, worldIn, pos, player, hand, result);

            case PATH:
                if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                    this.setBlock(worldIn, pos, ModBlocks.grass_fence);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                }
                return super.onUse(state, worldIn, pos, player, hand, result);

            default:
                return super.onUse(state, worldIn, pos, player, hand, result);
        }
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends Block> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(NORTH, state.get(NORTH))
                .with(SOUTH, state.get(SOUTH))
                .with(EAST, state.get(EAST))
                .with(WEST, state.get(WEST))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (blockType.booleanValue.get().get()) {
            super.onBlockHarvested(world, pos, state, player);
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (blockType == NiftyBlock.ICE) {
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                if (worldIn.dimension.doesWaterVaporize()) {
                    worldIn.removeBlock(pos, false);
                    return;
                }

                Material material = worldIn.getBlockState(pos.down()).getMaterial();
                if (material.blocksMovement() || material.isLiquid()) {
                    worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
                }
            }
        }
    }

    @Override
    @Deprecated
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (blockType == NiftyBlock.ICE) {
            if (worldIn.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.dimension.doesWaterVaporize()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
            world.neighborChanged(pos, Blocks.WATER, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return blockType == NiftyBlock.ICE ? PushReaction.NORMAL : super.getPushReaction(state);
    }

    @Override
    @Deprecated
    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return blockType == NiftyBlock.ICE ? type == EntityType.POLAR_BEAR : super.canEntitySpawn(state, worldIn, pos, type);
    }
}
