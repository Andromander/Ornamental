package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.BlockConverter;
import com.androsa.ornamental.builder.OrnamentBuilder;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class OrnamentFenceGate extends FenceGateBlock implements OrnamentalBlock {

    protected static final VoxelShape PATH_HITBOX_ZAXIS = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 15.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_HITBOX_ZAXIS_INWALL = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 12.0D, 10.0D);
    protected static final VoxelShape PATH_HITBOX_XAXIS_INWALL = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 12.0D, 16.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_ZAXIS = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 23.0D, 10.0D);
    protected static final VoxelShape PATH_COLLISION_BOX_XAXIS = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 23.0D, 16.0D);
    protected static final VoxelShape PATH_RENDER_BOX_Z = Shapes.or(Block.box(0.0D, 4.0D, 7.0D, 2.0D, 15.0D, 9.0D), Block.box(14.0D, 4.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_X = Shapes.or(Block.box(7.0D, 4.0D, 0.0D, 9.0D, 15.0D, 2.0D), Block.box(7.0D, 4.0D, 14.0D, 9.0D, 15.0D, 16.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_Z = Shapes.or(Block.box(0.0D, 1.0D, 7.0D, 2.0D, 12.0D, 9.0D), Block.box(14.0D, 1.0D, 7.0D, 16.0D, 12.0D, 9.0D));
    protected static final VoxelShape PATH_RENDER_BOX_INWALL_X = Shapes.or(Block.box(7.0D, 1.0D, 0.0D, 9.0D, 12.0D, 2.0D), Block.box(7.0D, 1.0D, 14.0D, 9.0D, 12.0D, 16.0D));

    private final OrnamentBuilder builder;

    public OrnamentFenceGate(Properties props, OrnamentBuilder builder) {
        super(props, builder.fencegateSounds[0], builder.fencegateSounds[1]);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            if (state.getValue(IN_WALL)) {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS_INWALL : PATH_HITBOX_ZAXIS_INWALL;
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_HITBOX_XAXIS : PATH_HITBOX_ZAXIS;
            }
        }
        return super.getShape(state, worldIn, pos, context);
    }

    @Override
    @Nonnull
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (builder.pathShape) {
            if (state.getValue(OPEN)) {
                return Shapes.empty();
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.Z ? PATH_COLLISION_BOX_ZAXIS : PATH_COLLISION_BOX_XAXIS;
            }
        }
        return super.getCollisionShape(state, worldIn, pos, context);
    }

    @Override
    @Nonnull
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
        if (builder.pathShape) {
            if (state.getValue(IN_WALL)) {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_INWALL_X : PATH_RENDER_BOX_INWALL_Z;
            } else {
                return state.getValue(FACING).getAxis() == Direction.Axis.X ? PATH_RENDER_BOX_X : PATH_RENDER_BOX_Z;
            }
        }
        return super.getOcclusionShape(state, worldIn, pos);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (builder.hazardPredicate != null && builder.damagePredicate != null) {
            if (builder.hazardPredicate.test(level, pos, state, entity)) {
                entity.hurt(builder.damagePredicate.apply(level), builder.damageAmount);
            }
        } else {
            super.stepOn(level, pos, state, entity);
        }
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, builder.fallMultiplier, worldIn.damageSources().fall());
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState blockState, BlockGetter blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 3 : 0;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState facing, LevelAccessor level, BlockPos currentPos, BlockPos nearPos) {
        if (builder.createBubbles) {
            if (direction == Direction.UP && facing.is(Blocks.WATER)) {
                level.scheduleTick(currentPos, this, builder.tickSchedule);
            }
        }

        return super.updateShape(state, direction, facing, level, currentPos, nearPos);
    }

    @Override
    @Nonnull
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (builder.convertPredicates != null) {
            for (BlockConverter converter : builder.convertPredicates) {
                if (converter.predicate().test(state, worldIn, pos, player, hand, result)) {
                    return changeBlock(itemstack, converter.list().get().get(4), converter.sound(), worldIn, pos, player, hand);
                }
            }
        }

        if (!builder.canOpen) {
            return InteractionResult.PASS;
        } else {
            return super.use(state, worldIn, pos, player, hand, result);
        }
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends Block> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        worldIn.setBlockAndUpdate(pos, newblock.get().withPropertiesOf(worldIn.getBlockState(pos)));
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (builder.createBubbles) {
            CustomBubbleColumnBlock.updateColumn(level, pos.above(), state);
        }
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

        if (builder.extinguishes) {
            BlockPos above = pos.above();
            if (worldIn.getFluidState(pos).canExtinguish(worldIn, pos)) {
                worldIn.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);
                worldIn.sendParticles(ParticleTypes.LARGE_SMOKE, above.getX() + 0.5D, above.getY() + 0.25D, above.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    protected void turnIntoWater(Level world, BlockPos pos) {
        if (world.dimensionType().ultraWarm() && builder.canVaporise) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState nearstate, boolean moving) {
        if (builder.createBubbles) {
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
                    level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
                }
            }
        }
    }
}
