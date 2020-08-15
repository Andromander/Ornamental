package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
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
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;
import java.util.function.Supplier;

public class NiftyFence extends FenceBlock implements INiftyBlock {

    protected final VoxelShape[] collisionShapes = this.makeShapes(2.0F, 2.0F, 23.0F, 0.0F, 23.0F);
    protected final VoxelShape[] shapes = this.makeShapes(2.0F, 2.0F, 15.0F, 0.0F, 15.0F);

    private final NiftyBuilder builder;

    public NiftyFence(Properties props, NiftyBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public NiftyBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath ? this.shapes[this.getIndex(state)] : super.getShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath ? this.collisionShapes[this.getIndex(state)] : super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 5 : 0;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (builder.isDirt) {
            if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
                this.setBlock(worldIn, pos, ModBlocks.grass_fence);
                worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
                return ActionResultType.SUCCESS;
            }
        }

        if (builder.isGrass) {
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
        }

        if (builder.isPath) {
            if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                this.setBlock(worldIn, pos, ModBlocks.grass_fence);
                worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                return ActionResultType.SUCCESS;
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, hand, result);
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
        if (builder.hasConfig) {
            ForgeConfigSpec.BooleanValue val = builder.booleanValue.get();

            if (val == null) {
                throw new NullPointerException(builder.name + " expected a config value but found null.");
            } else {
                if (val.get()) {
                    super.onBlockHarvested(world, pos, state, player);
                }
            }
        }
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (builder.isIce) {
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                if (worldIn.func_230315_m_().func_236040_e_()) { //doesWaterVaporize
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
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.func_230315_m_().func_236040_e_()) { //doesWaterVaporize
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
            world.neighborChanged(pos, Blocks.WATER, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return builder.isIce ? PushReaction.NORMAL : super.getPushReaction(state);
    }
}
