package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class OrnamentDoor extends DoorBlock implements OrnamentalBlock {

    protected static final VoxelShape PATH_SOUTH_AABB_TOP = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    protected static final VoxelShape PATH_NORTH_AABB_TOP = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_WEST_AABB_TOP = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_EAST_AABB_TOP = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentDoor(Properties props, OrnamentBuilder builder) {
        super(props, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN); //placeholders, these are provided with an OrnamentBuilder
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            Direction direction = state.getValue(FACING);
            boolean flag = !state.getValue(OPEN);
            boolean flag1 = state.getValue(HINGE) == DoorHingeSide.RIGHT;

            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                return switch (direction) {
                    case SOUTH -> flag ? PATH_SOUTH_AABB_TOP : (flag1 ? PATH_EAST_AABB_TOP : PATH_WEST_AABB_TOP);
                    case WEST -> flag ? PATH_WEST_AABB_TOP : (flag1 ? PATH_SOUTH_AABB_TOP : PATH_NORTH_AABB_TOP);
                    case NORTH -> flag ? PATH_NORTH_AABB_TOP : (flag1 ? PATH_WEST_AABB_TOP : PATH_EAST_AABB_TOP);
                    default -> flag ? PATH_EAST_AABB_TOP : (flag1 ? PATH_NORTH_AABB_TOP : PATH_SOUTH_AABB_TOP);
                };
            } else {
                return switch (direction) {
                    case SOUTH -> flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
                    case WEST -> flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
                    case NORTH -> flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
                    default -> flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
                };
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier, DamageSource.FALL);
    }

    @Override
    @Deprecated
    public boolean hasAnalogOutputSignal(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos) {
        return builder.hasPower && state.getValue(POWERED) ? 10 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        worldIn.updateNeighbourForOutputSignal(pos, this);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if (builder.mealGrass && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if (builder.hoeDirt && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_door, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if (builder.shovelPath && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_door, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if (builder.hoeGrass && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return this.performNormally(state, worldIn, pos, player);
    }

    private InteractionResult performNormally(BlockState state, Level worldIn, BlockPos pos, Player player) {
        if (!builder.canOpen) {
            return InteractionResult.PASS;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlock(pos, state, 10);
            worldIn.levelEvent(player, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return InteractionResult.SUCCESS;
        }
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends OrnamentDoor> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        BlockState blockstate =  worldIn.getBlockState(pos);

        if (blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
            setBlocks(newblock, worldIn, pos, pos.above(), DoubleBlockHalf.UPPER);
        } else {
            setBlocks(newblock, worldIn, pos, pos.below(), DoubleBlockHalf.LOWER);
        }
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.SUCCESS;
    }

    private void setBlocks(Supplier<? extends OrnamentDoor> block, Level world, BlockPos selectPos, BlockPos nearPos, DoubleBlockHalf half) {
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
    @Deprecated
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.canMelt) {
            if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(Level world, BlockPos pos) {
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

    @Override
    @Deprecated
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (!builder.projectileHitSounds.isEmpty()) {
            if (!level.isClientSide()) {
                BlockPos pos = result.getBlockPos();
                for (SoundEvent sound : builder.projectileHitSounds) {
                    level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                }
            }
        }
    }
}
