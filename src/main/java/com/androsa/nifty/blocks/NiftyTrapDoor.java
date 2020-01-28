package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.properties.Half;
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

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class NiftyTrapDoor extends TrapDoorBlock {

    protected static final VoxelShape PATH_EAST_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_OPEN_AABB = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_SOUTH_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape PATH_NORTH_OPEN_AABB = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_BOTTOM_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_AABB = Block.makeCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private float fallDamage;
    private boolean noRedstone;
    private NiftyBlock blockType;

    public NiftyTrapDoor(Properties props, NiftyBlock block) {
        super(props.hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level).nonOpaque());

        this.fallDamage = block.multiplier;
        this.noRedstone = block.canOpen;
        this.blockType = block;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (!state.get(OPEN)) {
            return state.get(HALF) == Half.TOP ? PATH_TOP_AABB : PATH_BOTTOM_AABB;
        } else {
            switch(state.get(HORIZONTAL_FACING)) {
                case NORTH:
                default:
                    return PATH_NORTH_OPEN_AABB;
                case SOUTH:
                    return PATH_SOUTH_OPEN_AABB;
                case WEST:
                    return PATH_WEST_OPEN_AABB;
                case EAST:
                    return PATH_EAST_OPEN_AABB;
            }
        }
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
                    this.setBlock(worldIn, pos, ModBlocks.grass_trapdoor);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                }
                return this.performNormally(state, worldIn, pos, player);

            case GRASS:
                if (!itemstack.isEmpty()) {
                    if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                        this.setBlock(worldIn, pos, ModBlocks.dirt_trapdoor);
                        worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    } else if (!itemstack.isEmpty() && itemstack.getItem() instanceof ShovelItem) {
                        this.setBlock(worldIn, pos, ModBlocks.path_trapdoor);
                        worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                        return ActionResultType.SUCCESS;
                    }
                }
                return this.performNormally(state, worldIn, pos, player);

            case PATH:
                if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                    this.setBlock(worldIn, pos, ModBlocks.grass_trapdoor);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                }
                return this.performNormally(state, worldIn, pos, player);

            default:
                return this.performNormally(state, worldIn, pos, player);
        }
    }

    private ActionResultType performNormally(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!noRedstone) {
            return ActionResultType.PASS;
        } else {
            state = state.cycle(OPEN);
            world.setBlockState(pos, state, 2);
            if (state.get(WATERLOGGED)) {
                world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            this.playSound(player, world, pos, state.get(OPEN));
            return ActionResultType.SUCCESS;
        }
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends Block> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(POWERED, state.get(POWERED))
                .with(OPEN, state.get(OPEN))
                .with(HALF, state.get(HALF))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        BlockState state = worldIn.getBlockState(pos);

        if (!state.get(OPEN)) {
            if (material == Material.CLAY || material == Material.LEAVES || material == Material.WOOL || material == Material.EARTH || material == Material.ORGANIC) {
                state = state.cycle(OPEN);
                worldIn.setBlockState(pos, state, 2);
                this.playSound(null, worldIn, pos, state.get(OPEN));
            }
        }
    }

    @Override
    protected void playSound(@Nullable PlayerEntity player, World worldIn, BlockPos pos, boolean state) {
        if (state) {
            int i = this.material == Material.IRON ? 1037 : 1007;
            worldIn.playEvent(player, i, pos, 0);
        } else {
            int j = this.material == Material.IRON ? 1036 : 1013;
            worldIn.playEvent(player, j, pos, 0);
        }
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
}
