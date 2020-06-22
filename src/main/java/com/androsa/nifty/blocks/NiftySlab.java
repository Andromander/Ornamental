package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
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
import net.minecraft.state.properties.SlabType;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;
import java.util.function.Supplier;

public class NiftySlab extends SlabBlock {

    protected static final VoxelShape PATH_BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_FULL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private final NiftyBuilder builder;

    public NiftySlab(Properties props, NiftyBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (builder.isPath) {
            SlabType slabtype = state.get(TYPE);
            switch(slabtype) {
                case DOUBLE:
                    return PATH_FULL_SHAPE;
                case TOP:
                    return PATH_TOP_SHAPE;
                default:
                    return PATH_BOTTOM_SHAPE;
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.handleFallDamage(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState state, IBlockReader reader, BlockPos pos, Direction side) {
        if (builder.hasPower)
            return state.get(TYPE) == SlabType.DOUBLE ? 15 : 7;
        return super.getWeakPower(state, reader, pos, side);
    }

    @Override
    @Deprecated
    public ActionResultType onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (builder.isDirt) {
            if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
                this.setBlock(worldIn, pos, ModBlocks.grass_slab);
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
                    this.setBlock(worldIn, pos, ModBlocks.dirt_slab);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                } else if (itemstack.getItem() instanceof ShovelItem) {
                    this.setBlock(worldIn, pos, ModBlocks.path_slab);
                    worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                    return ActionResultType.SUCCESS;
                }
            }
        }

        if (builder.isPath) {
            if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
                BlockState blockstate = worldIn.getBlockState(pos);
                worldIn.setBlockState(pos, ModBlocks.grass_slab.get().getDefaultState().with(TYPE, blockstate.get(TYPE)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
                worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
                return ActionResultType.SUCCESS;
            }
        }

        return super.onUse(state, worldIn, pos, player, hand, result);
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends Block> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(TYPE, state.get(TYPE))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce) {
            if (otherState.getBlock() == ModBlocks.ice_slab.get() && otherState.get(TYPE) == SlabType.DOUBLE && state.get(TYPE) == SlabType.DOUBLE) {
                return true;
            }
        }
        return super.isSideInvisible(state, otherState, direction);
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
        if (builder.isIce) {
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
        return builder.isIce ? PushReaction.NORMAL : super.getPushReaction(state);
    }

    @Override
    @Deprecated
    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return builder.isIce ? type == EntityType.POLAR_BEAR : super.canEntitySpawn(state, worldIn, pos, type);
    }
}
