package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
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

public class OrnamentDoor extends DoorBlock implements IOrnamentalBlock {

    protected static final VoxelShape PATH_SOUTH_AABB_TOP = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    protected static final VoxelShape PATH_NORTH_AABB_TOP = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_AABB_TOP = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_EAST_AABB_TOP = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentDoor(Properties props, OrnamentBuilder builder) {
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
            Direction direction = state.getValue(FACING);
            boolean flag = !state.getValue(OPEN);
            boolean flag1 = state.getValue(HINGE) == DoorHingeSide.RIGHT;

            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                switch(direction) {
                    case EAST:
                    default:
                        return flag ? PATH_EAST_AABB_TOP : (flag1 ? PATH_NORTH_AABB_TOP : PATH_SOUTH_AABB_TOP);
                    case SOUTH:
                        return flag ? PATH_SOUTH_AABB_TOP : (flag1 ? PATH_EAST_AABB_TOP : PATH_WEST_AABB_TOP);
                    case WEST:
                        return flag ? PATH_WEST_AABB_TOP : (flag1 ? PATH_SOUTH_AABB_TOP : PATH_NORTH_AABB_TOP);
                    case NORTH:
                        return flag ? PATH_NORTH_AABB_TOP : (flag1 ? PATH_WEST_AABB_TOP : PATH_EAST_AABB_TOP);
                }
            } else {
                switch(direction) {
                    case EAST:
                    default:
                        return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
                    case SOUTH:
                        return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
                    case WEST:
                        return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
                    case NORTH:
                        return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
                }
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
    public boolean hasAnalogOutputSignal(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getAnalogOutputSignal(BlockState state, World worldIn, BlockPos pos) {
        return builder.hasPower && state.getValue(POWERED) ? 10 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        worldIn.updateNeighbourForOutputSignal(pos, this);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_door, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_door, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return this.performNormally(state, worldIn, pos, player);
    }

    private ActionResultType performNormally(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (!builder.canOpen) {
            return ActionResultType.PASS;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlock(pos, state, 10);
            worldIn.levelEvent(player, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return ActionResultType.SUCCESS;
        }
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentDoor> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        BlockState blockstate =  worldIn.getBlockState(pos);

        if (blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
            setBlocks(newblock, worldIn, pos, pos.above(), DoubleBlockHalf.UPPER);
        } else {
            setBlocks(newblock, worldIn, pos, pos.below(), DoubleBlockHalf.LOWER);
        }
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlocks(Supplier<? extends OrnamentDoor> block, World world, BlockPos selectPos, BlockPos nearPos, DoubleBlockHalf half) {
        BlockState blockstate = world.getBlockState(selectPos);
        BlockPos pos = blockstate.getValue(HALF) == DoubleBlockHalf.LOWER ? selectPos : nearPos;

        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 35);
        world.setBlockAndUpdate(nearPos, block.get().defaultBlockState().setValue(FACING, blockstate.getValue(FACING)).setValue(OPEN, blockstate.getValue(OPEN)).setValue(HINGE, blockstate.getValue(HINGE)).setValue(POWERED, blockstate.getValue(POWERED)).setValue(HALF, half));
        world.setBlockAndUpdate(selectPos, block.get().defaultBlockState().setValue(FACING, blockstate.getValue(FACING)).setValue(OPEN, blockstate.getValue(OPEN)).setValue(HINGE, blockstate.getValue(HINGE)).setValue(POWERED, blockstate.getValue(POWERED)).setValue(HALF, blockstate.getValue(HALF)));
    }

    private int getCloseSound() {
        return this.material == Material.METAL || this.material == Material.STONE ? 1011 : 1012;
    }

    private int getOpenSound() {
        return this.material == Material.METAL || this.material == Material.STONE ? 1005 : 1006;
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
            if (world.getBlockState(pos).getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
                world.neighborChanged(pos, builder.meltResult, pos);
            } else {
                world.setBlockAndUpdate(pos.relative(Direction.DOWN), builder.meltResult.defaultBlockState());
                world.neighborChanged(pos.relative(Direction.DOWN), builder.meltResult, pos.relative(Direction.DOWN));
            }
        }
    }
}
