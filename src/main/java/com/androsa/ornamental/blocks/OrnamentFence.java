package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
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

public class OrnamentFence extends FenceBlock implements IOrnamentalBlock {

    protected final VoxelShape[] collisionShapes = this.makeShapes(2.0F, 2.0F, 23.0F, 0.0F, 23.0F);
    protected final VoxelShape[] shapes = this.makeShapes(2.0F, 2.0F, 15.0F, 0.0F, 15.0F);

    private final OrnamentBuilder builder;

    public OrnamentFence(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath || builder.pathShape ? this.shapes[this.getAABBIndex(state)] : super.getShape(state, worldIn, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath || builder.pathShape ? this.collisionShapes[this.getAABBIndex(state)] : super.getCollisionShape(state, worldIn, pos, context);
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
    public int getSignal(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 5 : 0;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_fence, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_fence, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_fence, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_fence, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentFence> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentFence> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(NORTH, state.getValue(NORTH))
                .setValue(SOUTH, state.getValue(SOUTH))
                .setValue(EAST, state.getValue(EAST))
                .setValue(WEST, state.getValue(WEST))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
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
