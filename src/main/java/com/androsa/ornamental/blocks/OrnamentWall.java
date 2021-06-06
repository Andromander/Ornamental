package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class OrnamentWall extends WallBlock implements IOrnamentalBlock {

    protected final Map<BlockState, VoxelShape> collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 23.0F, 0.0F, 23.0F, 25.0F);
    protected final Map<BlockState, VoxelShape> shapeByIndex = this.makeShapes(4.0F, 3.0F, 15.0F, 0.0F, 13.0F, 15.0F);
    private final OrnamentBuilder builder;

    public OrnamentWall(AbstractBlock.Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    //Begin WallBlock vanillacopy
    private Map<BlockState, VoxelShape> makeShapes(float topshape, float offset, float yUpMax, float yMin, float yLowMax, float yTallMax) {
        float topmin = 8.0F - topshape;
        float topmax = 8.0F + topshape;
        float minoff = 8.0F - offset;
        float maxoff = 8.0F + offset;
        VoxelShape upshape = Block.box(topmin, 0.0D, topmin, topmax, yUpMax, topmax);
        VoxelShape northlow = Block.box(minoff, yMin, 0.0D, maxoff, yLowMax, maxoff);
        VoxelShape southlow = Block.box(minoff, yMin, minoff, maxoff, yLowMax, 16.0D);
        VoxelShape westlow = Block.box(0.0D, yMin, minoff, maxoff, yLowMax, maxoff);
        VoxelShape eastlow = Block.box(minoff, yMin, minoff, 16.0D, yLowMax, maxoff);
        VoxelShape northtall = Block.box(minoff, yMin, 0.0D, maxoff, yTallMax, maxoff);
        VoxelShape southtall = Block.box(minoff, yMin, minoff, maxoff, yTallMax, 16.0D);
        VoxelShape westtall = Block.box(0.0D, yMin, minoff, maxoff, yTallMax, maxoff);
        VoxelShape easttall = Block.box(minoff, yMin, minoff, 16.0D, yTallMax, maxoff);
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

        for (Boolean upstate : UP.getPossibleValues()) {
            for (WallHeight eastheight : EAST_WALL.getPossibleValues()) {
                for (WallHeight northheight : NORTH_WALL.getPossibleValues()) {
                    for (WallHeight westheight : WEST_WALL.getPossibleValues()) {
                        for (WallHeight southheight : SOUTH_WALL.getPossibleValues()) {
                            VoxelShape totalshape = VoxelShapes.empty();
                            totalshape = applyWallShape(totalshape, eastheight, eastlow, easttall);
                            totalshape = applyWallShape(totalshape, westheight, westlow, westtall);
                            totalshape = applyWallShape(totalshape, northheight, northlow, northtall);
                            totalshape = applyWallShape(totalshape, southheight, southlow, southtall);
                            if (upstate) {
                                totalshape = VoxelShapes.or(totalshape, upshape);
                            }

                            BlockState blockstate = this.defaultBlockState().setValue(UP, upstate).setValue(EAST_WALL, eastheight).setValue(WEST_WALL, westheight).setValue(NORTH_WALL, northheight).setValue(SOUTH_WALL, southheight);
                            builder.put(blockstate.setValue(WATERLOGGED, false), totalshape);
                            builder.put(blockstate.setValue(WATERLOGGED, true), totalshape);
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    private static VoxelShape applyWallShape(VoxelShape base, WallHeight wallheight, VoxelShape lowshape, VoxelShape tallshape) {
        switch (wallheight) {
            case TALL:
                return VoxelShapes.or(base, tallshape);
            case LOW:
                return VoxelShapes.or(base, lowshape);
            default:
                return base;
        }
    }
    //End WallBlock vanillacopy

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath || builder.pathShape ? this.shapeByIndex.get(state) : super.getShape(state, worldIn, pos, context);
    }

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return builder.isPath || builder.pathShape ? this.collisionShapeByIndex.get(state) : super.getCollisionShape(state, worldIn, pos, context);
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
        return builder.hasPower ? 11 : 0;
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_wall, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_wall, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_wall, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_wall, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentWall> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentWall> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(UP, state.getValue(UP))
                .setValue(NORTH_WALL, state.getValue(NORTH_WALL))
                .setValue(SOUTH_WALL, state.getValue(SOUTH_WALL))
                .setValue(EAST_WALL, state.getValue(EAST_WALL))
                .setValue(WEST_WALL, state.getValue(WEST_WALL))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce || builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentWall && state.getBlock() instanceof OrnamentWall) {
                boolean crossS = !state.getValue(UP) && state.getValue(EAST_WALL) == WallHeight.TALL && state.getValue(WEST_WALL) == WallHeight.TALL && state.getValue(NORTH_WALL) == WallHeight.TALL && state.getValue(SOUTH_WALL) == WallHeight.TALL;
                boolean crossO = !otherState.getValue(UP) && otherState.getValue(EAST_WALL) != WallHeight.NONE && otherState.getValue(WEST_WALL) != WallHeight.NONE && otherState.getValue(NORTH_WALL) != WallHeight.NONE && otherState.getValue(SOUTH_WALL) != WallHeight.NONE;
                if (direction == Direction.UP) {
                    if (state.getValue(UP) && otherState.getValue(UP))
                        return true;
                    if (crossS && crossO)
                        return true;
                    if (state.getValue(NORTH_WALL) == WallHeight.TALL && state.getValue(SOUTH_WALL) == WallHeight.TALL)
                        return otherState.getValue(NORTH_WALL) != WallHeight.NONE && otherState.getValue(SOUTH_WALL) != WallHeight.NONE;
                    if (state.getValue(WEST_WALL) == WallHeight.TALL && state.getValue(EAST_WALL) == WallHeight.TALL)
                        return otherState.getValue(WEST_WALL) != WallHeight.NONE && otherState.getValue(EAST_WALL) != WallHeight.NONE;
                } else if (direction == Direction.DOWN) {
                    if (state.getValue(UP) && otherState.getValue(UP))
                        return true;
                    if (state.getValue(NORTH_WALL) != WallHeight.NONE && state.getValue(SOUTH_WALL) != WallHeight.NONE)
                        return otherState.getValue(NORTH_WALL) != WallHeight.NONE && state.getValue(SOUTH_WALL) != WallHeight.NONE;
                    if (state.getValue(WEST_WALL) != WallHeight.NONE && state.getValue(EAST_WALL) != WallHeight.NONE)
                        return otherState.getValue(WEST_WALL) != WallHeight.NONE && state.getValue(EAST_WALL) != WallHeight.NONE;
                } else {
                    if (state.getValue(EAST_WALL) != WallHeight.NONE && state.getValue(WEST_WALL) != WallHeight.NONE)
                        return true;
                    if (state.getValue(NORTH_WALL) != WallHeight.NONE && state.getValue(SOUTH_WALL) != WallHeight.NONE)
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
