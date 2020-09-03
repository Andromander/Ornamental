package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.SlabType;
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

public class OrnamentSlab extends SlabBlock implements IOrnamentalBlock {

    protected static final VoxelShape PATH_BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_FULL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentSlab(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (builder.isPath || builder.pathShape) {
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
        entityIn.onLivingFall(fallDistance, builder.fallMultiplier);
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
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_slab, SoundEvents.BLOCK_GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_slab, SoundEvents.ITEM_SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentSlab> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.isCreativeMode && !itemstack.isDamageable()) {
            itemstack.shrink(1);
        } else {
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentSlab> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(TYPE, state.get(TYPE))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce || builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentSlab && state.getBlock() instanceof OrnamentSlab) {
                OrnamentSlab slab = (OrnamentSlab) state.getBlock();
                OrnamentSlab otherSlab = (OrnamentSlab) otherState.getBlock();

                if (otherSlab.getBuilder() == slab.getBuilder() && otherState.get(TYPE) == SlabType.DOUBLE && state.get(TYPE) == SlabType.DOUBLE) {
                    return true;
                }
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
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce || builder.canMelt) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.func_230315_m_().func_236040_e_() && builder.canVaporise) { //doesWaterVaporize
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, builder.meltResult.getDefaultState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return builder.isIce ? PushReaction.NORMAL : builder.pushReaction;
    }
}
