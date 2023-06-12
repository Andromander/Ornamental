package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.BlockConverter;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.google.common.collect.ImmutableMap;
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
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Supplier;

public class OrnamentWall extends WallBlock implements OrnamentalBlock {

    protected final Map<BlockState, VoxelShape> collisionShapeByIndex = this.makeShapes(4.0F, 3.0F, 23.0F, 0.0F, 23.0F, 25.0F);
    protected final Map<BlockState, VoxelShape> shapeByIndex = this.makeShapes(4.0F, 3.0F, 15.0F, 0.0F, 13.0F, 15.0F);
    private final OrnamentBuilder builder;

    public OrnamentWall(Properties props, OrnamentBuilder builder) {
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
            for (WallSide eastheight : EAST_WALL.getPossibleValues()) {
                for (WallSide northheight : NORTH_WALL.getPossibleValues()) {
                    for (WallSide westheight : WEST_WALL.getPossibleValues()) {
                        for (WallSide southheight : SOUTH_WALL.getPossibleValues()) {
                            VoxelShape totalshape = Shapes.empty();
                            totalshape = applyWallShape(totalshape, eastheight, eastlow, easttall);
                            totalshape = applyWallShape(totalshape, westheight, westlow, westtall);
                            totalshape = applyWallShape(totalshape, northheight, northlow, northtall);
                            totalshape = applyWallShape(totalshape, southheight, southlow, southtall);
                            if (upstate) {
                                totalshape = Shapes.or(totalshape, upshape);
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

    private static VoxelShape applyWallShape(VoxelShape base, WallSide wallheight, VoxelShape lowshape, VoxelShape tallshape) {
        return switch (wallheight) {
            case TALL -> Shapes.or(base, tallshape);
            case LOW -> Shapes.or(base, lowshape);
            default -> base;
        };
    }
    //End WallBlock vanillacopy

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return builder.pathShape ? this.shapeByIndex.get(state) : super.getShape(state, worldIn, pos, context);
    }

    @Override
    @Nonnull
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return builder.pathShape ? this.collisionShapeByIndex.get(state) : super.getCollisionShape(state, worldIn, pos, context);
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
        return builder.hasPower ? 11 : 0;
    }

    @Override
    @Nonnull
    @Deprecated
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (builder.convertPredicates != null) {
            for (BlockConverter converter : builder.convertPredicates) {
                if (converter.predicate().test(state, worldIn, pos, player, hand, result)) {
                    return changeBlock(itemstack, converter.list().get().get(8), converter.sound(), worldIn, pos, player, hand);
                }
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
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
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentWall && state.getBlock() instanceof OrnamentWall) {
                boolean crossS = !state.getValue(UP) && state.getValue(EAST_WALL) == WallSide.TALL && state.getValue(WEST_WALL) == WallSide.TALL && state.getValue(NORTH_WALL) == WallSide.TALL && state.getValue(SOUTH_WALL) == WallSide.TALL;
                boolean crossO = !otherState.getValue(UP) && otherState.getValue(EAST_WALL) != WallSide.NONE && otherState.getValue(WEST_WALL) != WallSide.NONE && otherState.getValue(NORTH_WALL) != WallSide.NONE && otherState.getValue(SOUTH_WALL) != WallSide.NONE;
                if (direction == Direction.UP) {
                    if (state.getValue(UP) && otherState.getValue(UP))
                        return true;
                    if (crossS && crossO)
                        return true;
                    if (state.getValue(NORTH_WALL) == WallSide.TALL && state.getValue(SOUTH_WALL) == WallSide.TALL)
                        return otherState.getValue(NORTH_WALL) != WallSide.NONE && otherState.getValue(SOUTH_WALL) != WallSide.NONE;
                    if (state.getValue(WEST_WALL) == WallSide.TALL && state.getValue(EAST_WALL) == WallSide.TALL)
                        return otherState.getValue(WEST_WALL) != WallSide.NONE && otherState.getValue(EAST_WALL) != WallSide.NONE;
                } else if (direction == Direction.DOWN) {
                    if (state.getValue(UP) && otherState.getValue(UP))
                        return true;
                    if (state.getValue(NORTH_WALL) != WallSide.NONE && state.getValue(SOUTH_WALL) != WallSide.NONE)
                        return otherState.getValue(NORTH_WALL) != WallSide.NONE && state.getValue(SOUTH_WALL) != WallSide.NONE;
                    if (state.getValue(WEST_WALL) != WallSide.NONE && state.getValue(EAST_WALL) != WallSide.NONE)
                        return otherState.getValue(WEST_WALL) != WallSide.NONE && state.getValue(EAST_WALL) != WallSide.NONE;
                } else {
                    if (state.getValue(EAST_WALL) != WallSide.NONE && state.getValue(WEST_WALL) != WallSide.NONE)
                        return true;
                    if (state.getValue(NORTH_WALL) != WallSide.NONE && state.getValue(SOUTH_WALL) != WallSide.NONE)
                        return true;
                }
            }
        }
        return super.skipRendering(state, otherState, direction);
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
