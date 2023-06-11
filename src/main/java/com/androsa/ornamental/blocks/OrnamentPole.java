package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Supplier;

public class OrnamentPole extends Block implements SimpleWaterloggedBlock, OrnamentalBlock {

    protected static final VoxelShape TL_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape BL_SHAPE = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_PATH = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 15.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE_PATH = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 15.0D, 8.0D);
    protected static final VoxelShape BL_SHAPE_PATH = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 15.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE_PATH = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 15.0D, 16.0D);

    public static final BooleanProperty TOP_LEFT = BooleanProperty.create("top_left");
    public static final BooleanProperty TOP_RIGHT = BooleanProperty.create("top_right");
    public static final BooleanProperty BOTTOM_LEFT = BooleanProperty.create("bottom_left");
    public static final BooleanProperty BOTTOM_RIGHT = BooleanProperty.create("bottom_right");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final BooleanProperty[] CORNERS = new BooleanProperty[]{ TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT };
    private static final Map<BooleanProperty, VoxelShape> POLE_SHAPES = ImmutableMap.of(TOP_LEFT, TL_SHAPE, TOP_RIGHT, TR_SHAPE, BOTTOM_LEFT, BL_SHAPE, BOTTOM_RIGHT, BR_SHAPE);
    private static final Map<BooleanProperty, VoxelShape> POLE_SHAPES_PATH = ImmutableMap.of(TOP_LEFT, TL_SHAPE_PATH, TOP_RIGHT, TR_SHAPE_PATH, BOTTOM_LEFT, BL_SHAPE_PATH, BOTTOM_RIGHT, BR_SHAPE_PATH);

    private final OrnamentBuilder builder;

    public OrnamentPole(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TOP_LEFT, false)
                .setValue(TOP_RIGHT, false)
                .setValue(BOTTOM_LEFT, false)
                .setValue(BOTTOM_RIGHT, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> container) {
        container.add(TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, WATERLOGGED);
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Nonnull
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        List<VoxelShape> shapes = Lists.newArrayList();

        for (BooleanProperty property : CORNERS) {
            if (state.getValue(property)) {
                if (builder.pathShape) {
                    shapes.add(POLE_SHAPES_PATH.get(property));
                } else {
                    shapes.add(POLE_SHAPES.get(property));
                }
            }
        }

        Optional<VoxelShape> optional = shapes.stream().reduce(Shapes::or);

        if (optional.isEmpty()) {
            //So the block can be interacted with still.
            return Shapes.block();
        }
        return optional.get();
    }

    public boolean isFull(BlockState state) {
        if (state.getBlock() instanceof OrnamentPole) {
            return state.getValue(TOP_LEFT) && state.getValue(TOP_RIGHT) && state.getValue(BOTTOM_LEFT) && state.getValue(BOTTOM_RIGHT);
        }
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        /*
         * 1. Check the XZ of the block being placed.
         * 2. For specific cases, check Direction to override default XZ behaviour.
         * 3. Check for waterlogging
         */

        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        //All true: TL. Z true: TR. X true: BL. No true: BR.
        boolean x = context.getClickLocation().x - (double)context.getClickedPos().getX() < 0.5D;
        boolean z = context.getClickLocation().z - (double)context.getClickedPos().getZ() < 0.5D;
        FluidState fluidstate = context.getLevel().getFluidState(pos);
        boolean filled = fluidstate.getType() == Fluids.WATER;
        Direction direction = context.getClickedFace();

        if (state.is(this)) {
            if (x && z) {
                return setState(state, TOP_LEFT, filled);
            }

            if (!x && z) {
                if (direction == Direction.WEST && state.getValue(TOP_RIGHT)) {
                    return setState(state, TOP_LEFT, filled);
                }
                return setState(state, TOP_RIGHT, filled);
            }

            if (x && !z) {
                if (direction == Direction.NORTH && state.getValue(BOTTOM_LEFT)) {
                    return setState(state, TOP_LEFT, filled);
                }
                return setState(state, BOTTOM_LEFT, filled);
            }

            if (!x && !z) {
                if (state.getValue(BOTTOM_RIGHT)) {
                    if (direction == Direction.WEST) {
                        return setState(state, BOTTOM_LEFT, filled);
                    } else if (direction == Direction.NORTH) {
                        return setState(state, TOP_RIGHT, filled);
                    }
                }
                return setState(state, BOTTOM_RIGHT, filled);
            }
        } else {
            if (x && z) return setState(defaultBlockState(), TOP_LEFT, filled);
            if (!x && z) return setState(defaultBlockState(), TOP_RIGHT, filled);
            if (x && !z) return setState(defaultBlockState(), BOTTOM_LEFT, filled);
            if (!x && !z) return setState(defaultBlockState(), BOTTOM_RIGHT, filled);
        }

        return state;
    }

    private BlockState setState(BlockState state, BooleanProperty corner, boolean isFilled) {
        BlockState blockstate = state.setValue(corner, true);
        if (isFull(blockstate)) {
            isFilled = false;
        }
        return blockstate.setValue(WATERLOGGED, isFilled);
    }

    @Override
    @Deprecated
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();

        //Only Full shapes are non-replaceable.
        if (!isFull(state) && stack.getItem() == this.asItem()) {
            if (context.replacingClickedOnBlock()) {
                //All true: TL. Z true: TR. X true: BL. No true: BR.
                boolean x = context.getClickLocation().x - (double)context.getClickedPos().getX() < 0.5D;
                boolean z = context.getClickLocation().z - (double)context.getClickedPos().getZ() < 0.5D;
                Direction direction = context.getClickedFace();

                if (x && z) {
                    return !state.getValue(TOP_LEFT);
                }

                if (!x && z) {
                    if (direction == Direction.WEST) {
                        return !state.getValue(TOP_LEFT);
                    }
                    return !state.getValue(TOP_RIGHT);
                }

                if (x && !z) {
                    if (direction == Direction.NORTH) {
                        return !state.getValue(TOP_LEFT);
                    }
                    return !state.getValue(BOTTOM_LEFT);
                }

                if (!x && !z) {
                    if (direction == Direction.NORTH) {
                        return !state.getValue(TOP_RIGHT);
                    } else if (direction == Direction.WEST) {
                        return !state.getValue(BOTTOM_LEFT);
                    }
                    return !state.getValue(BOTTOM_RIGHT);
                }
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    @Nonnull
    @Deprecated
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        int corners = (int) Arrays.stream(CORNERS).filter(state::getValue).count();

        if (corners <= 0) {
            return Collections.emptyList();
        } else {
            List<ItemStack> list = Lists.newArrayList();

            for (int i = 0; i < corners; i++) {
                list.addAll(super.getDrops(state, builder));
            }

            return list;
        }
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
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float distance) {
        entity.causeFallDamage(distance, builder.fallMultiplier, world.damageSources().fall());
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        if (builder.hasPower) {
            int corners = (int) Arrays.stream(CORNERS).filter(state::getValue).count();
            return switch (corners) {
                case 1 -> 4;
                case 2 -> 8;
                case 3 -> 12;
                case 4 -> 15;
                default -> super.getSignal(state, world, pos, direction);
            };
        }
        return super.getSignal(state, world, pos, direction);
    }

    @Override
    @Nonnull
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return !isFull(state) && SimpleWaterloggedBlock.super.placeLiquid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return !isFull(state) && SimpleWaterloggedBlock.super.canPlaceLiquid(worldIn, pos, state, fluidIn);
    }

    @Override
    @Nonnull
    @Deprecated
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        if (builder.createBubbles) {
            if (facing == Direction.UP && facingState.is(Blocks.WATER)) {
                worldIn.scheduleTick(currentPos, this, builder.tickSchedule);
            }
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Nonnull
    @Deprecated
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if (builder.mealGrass && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_pole, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if (builder.hoeDirt && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_pole, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if (builder.shovelPath && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_pole, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if (builder.hoeGrass && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_pole, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends OrnamentPole> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.SUCCESS;
    }

    private void setBlock(Level world, BlockPos pos, Supplier<? extends OrnamentPole> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(TOP_LEFT, state.getValue(TOP_LEFT))
                .setValue(TOP_RIGHT, state.getValue(TOP_RIGHT))
                .setValue(BOTTOM_LEFT, state.getValue(BOTTOM_LEFT))
                .setValue(BOTTOM_RIGHT, state.getValue(BOTTOM_RIGHT))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentPole otherPole && state.getBlock() instanceof OrnamentPole pole) {
                if (otherPole.getBuilder() == pole.getBuilder() && isFull(otherState) && isFull(state)) {
                    return true;
                }
            }
        }
        return super.skipRendering(state, otherState, direction);
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		if (type == PathComputationType.WATER) {
			return worldIn.getFluidState(pos).is(FluidTags.WATER);
		}
		return false;
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
