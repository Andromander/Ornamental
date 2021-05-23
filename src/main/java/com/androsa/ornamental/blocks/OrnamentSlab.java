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

    protected static final VoxelShape PATH_BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_TOP_SHAPE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_FULL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

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
            SlabType slabtype = state.getValue(TYPE);
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
    public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState state, IBlockReader reader, BlockPos pos, Direction side) {
        if (builder.hasPower)
            return state.getValue(TYPE) == SlabType.DOUBLE ? 15 : 7;
        return super.getSignal(state, reader, pos, side);
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_slab, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_slab, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_slab, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentSlab> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentSlab> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(TYPE, state.getValue(TYPE))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce || builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentSlab && state.getBlock() instanceof OrnamentSlab) {
                OrnamentSlab slab = (OrnamentSlab) state.getBlock();
                OrnamentSlab otherSlab = (OrnamentSlab) otherState.getBlock();

                if (otherSlab.getBuilder() == slab.getBuilder() && otherState.getValue(TYPE) == SlabType.DOUBLE && state.getValue(TYPE) == SlabType.DOUBLE) {
                    return true;
                }
            }
        }
        return super.skipRendering(state, otherState, direction);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
        if (builder.hasConfig) {
            ForgeConfigSpec.BooleanValue val = builder.booleanValue.get();

            if (val == null) {
                throw new NullPointerException(builder.name + " expected a config value but found null.");
            } else {
                return val.get() && super.canHarvestBlock(state, world, pos, player);
            }
        }
        return super.canHarvestBlock(state, world, pos, player);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce || builder.canMelt) {
            if (worldIn.getBrightness(LightType.BLOCK, pos) > 11 - state.getLightBlock(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.dimensionType().ultraWarm() && builder.canVaporise) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPistonPushReaction(BlockState state) {
        return builder.isIce ? PushReaction.NORMAL : builder.pushReaction;
    }
}
