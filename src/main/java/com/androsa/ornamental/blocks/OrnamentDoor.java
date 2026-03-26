package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.BlockConverter;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.enums.BubbleColumnDirection;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Supplier;

public class OrnamentDoor extends DoorBlock implements OrnamentalBlock {

    public static final MapCodec<OrnamentDoor> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(OrnamentBuilder.CODEC.fieldOf("ornament_builder").forGetter(OrnamentDoor::getBuilder),
                            propertiesCodec())
                    .apply(instance, OrnamentDoor::new));

    private static final Map<Direction, VoxelShape> PATH_SHAPES = Shapes.rotateHorizontal(Block.box(0.0, 0.0, 13.0, 16.0, 15.0, 16.0));

    private final OrnamentBuilder builder;

    public OrnamentDoor(OrnamentBuilder builder, Properties props) {
        super(builder.blockSetType, props);
        this.builder = builder;
    }

    @Override
    public MapCodec<? extends OrnamentDoor> codec() {
        return CODEC;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                Direction facing = state.getValue(FACING);
                Direction open = state.getValue(OPEN)
                        ? (state.getValue(HINGE) == DoorHingeSide.RIGHT ? facing.getCounterClockWise() : facing.getClockWise())
                        : facing;
                return PATH_SHAPES.get(open);
            } else {
                return super.getShape(state, worldIn, pos, context);
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (builder.floorHazard != null) {
            if (builder.floorHazard.predicate().test(level, pos, state, entity)) {
                entity.hurt(builder.floorHazard.damage().apply(level), builder.floorHazard.amount());
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, double fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier, worldIn.damageSources().fall());
    }

    @Override
    public BubbleColumnDirection getBubbleColumnDirection(BlockState state) {
        return builder.bubbleDirection;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (builder.spreadChance == -1) {
            return super.getFlammability(state, level, pos, direction);
        }
        return builder.spreadChance;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (builder.flammability == -1) {
            return super.getFireSpreadSpeed(state, level, pos, direction);
        }
        return builder.flammability;
    }

    @Override
    @Deprecated
    public boolean hasAnalogOutputSignal(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos, Direction dir) {
        return builder.hasPower && state.getValue(POWERED) ? 10 : 0;
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, Orientation fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        worldIn.updateNeighbourForOutputSignal(pos, this);
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticker, BlockPos currentPos, Direction direction, BlockPos nearPos, BlockState facing, RandomSource random) {
        if (builder.bubbleDirection != BubbleColumnDirection.NONE) {
            if (direction == Direction.UP && facing.is(Blocks.WATER)) {
                ticker.scheduleTick(currentPos, this, builder.tickSchedule);
            }
        }

        return super.updateShape(state, level, ticker, currentPos, direction, nearPos, facing, random);
    }

    @Override
    @Nonnull
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (builder.convertPredicates != null) {
            for (BlockConverter converter : builder.convertPredicates) {
                if (converter.predicate().test(state, worldIn, pos, player, hand, result)) {
                    return changeBlock(stack, converter.list().get().get(5), converter.sound(), worldIn, pos, player, hand);
                }
            }
        }

        return super.useItemOn(stack, state, worldIn, pos, player, hand, result);
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends Block> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
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
            itemstack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        }
        return InteractionResult.SUCCESS;
    }

    private void setBlocks(Supplier<? extends Block> block, Level world, BlockPos selectPos, BlockPos nearPos, DoubleBlockHalf half) {
        BlockState blockstate = world.getBlockState(selectPos);
        BlockPos pos = blockstate.getValue(HALF) == DoubleBlockHalf.LOWER ? selectPos : nearPos;

        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 35);
        world.setBlockAndUpdate(nearPos, block.get().defaultBlockState().setValue(FACING, blockstate.getValue(FACING)).setValue(OPEN, blockstate.getValue(OPEN)).setValue(HINGE, blockstate.getValue(HINGE)).setValue(POWERED, blockstate.getValue(POWERED)).setValue(HALF, half));
        world.setBlockAndUpdate(selectPos, block.get().defaultBlockState().setValue(FACING, blockstate.getValue(FACING)).setValue(OPEN, blockstate.getValue(OPEN)).setValue(HINGE, blockstate.getValue(HINGE)).setValue(POWERED, blockstate.getValue(POWERED)).setValue(HALF, blockstate.getValue(HALF)));
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (builder.bubbleDirection != BubbleColumnDirection.NONE) {
            BubbleColumnBlock.updateColumn(Blocks.BUBBLE_COLUMN, level, pos.above(), state);
        }
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.canMelt) {
            if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightDampening()) {
                this.turnIntoWater(worldIn, pos);
            }
        }

        if (builder.extinguishes) {
            BlockPos above = pos.above();
            if (worldIn.getFluidState(pos).canExtinguish(worldIn, pos)) {
                worldIn.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F);
                worldIn.sendParticles(ParticleTypes.LARGE_SMOKE, above.getX() + 0.5D, above.getY() + 0.25D, above.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    protected void turnIntoWater(Level world, BlockPos pos) {
        if (world.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos) && builder.canVaporise) {
            world.removeBlock(pos, false);
        } else {
            if (world.getBlockState(pos).getValue(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
                world.neighborChanged(pos, builder.meltResult, null);
            } else {
                world.setBlockAndUpdate(pos.relative(Direction.DOWN), builder.meltResult.defaultBlockState());
                world.neighborChanged(pos.relative(Direction.DOWN), builder.meltResult, null);
            }
        }
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState nearstate, boolean moving) {
        if (builder.bubbleDirection != BubbleColumnDirection.NONE) {
            level.scheduleTick(pos, this, builder.tickSchedule);
        }
    }

    @Override
    @Deprecated
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (!builder.projectileHitSounds.isEmpty()) {
            if (!level.isClientSide()) {
                BlockPos pos = result.getBlockPos();
                for (SoundEvent sound : builder.projectileHitSounds) {
                    level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 0.5F + level.getRandom().nextFloat() * 1.2F);
                }
            }
        }
    }
}
