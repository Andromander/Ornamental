package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class OrnamentSaddleDoor extends Block implements OrnamentalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    private static final VoxelShape PATH_SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    private static final VoxelShape PATH_NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    private static final VoxelShape PATH_WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static final VoxelShape PATH_EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;
    private final SoundEvent closeSound;
    private final SoundEvent openSound;

    public OrnamentSaddleDoor(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
        this.closeSound = builder.saddledoorSounds[1];
        this.openSound = builder.saddledoorSounds[0];

        this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false));
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Nonnull
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean closed = !state.getValue(OPEN);
        boolean rhinge = state.getValue(HINGE) == DoorHingeSide.RIGHT;

        if (builder.pathShape) {
            return switch (direction) {
                case SOUTH -> closed ? PATH_SOUTH_AABB : (rhinge ? PATH_EAST_AABB : PATH_WEST_AABB);
                case WEST -> closed ? PATH_WEST_AABB : (rhinge ? PATH_SOUTH_AABB : PATH_NORTH_AABB);
                case NORTH -> closed ? PATH_NORTH_AABB : (rhinge ? PATH_WEST_AABB : PATH_EAST_AABB);
                default -> closed ? PATH_EAST_AABB : (rhinge ? PATH_NORTH_AABB : PATH_SOUTH_AABB);
            };
        } else {
            return switch (direction) {
                case SOUTH -> closed ? SOUTH_AABB : (rhinge ? EAST_AABB : WEST_AABB);
                case WEST -> closed ? WEST_AABB : (rhinge ? SOUTH_AABB : NORTH_AABB);
                case NORTH -> closed ? NORTH_AABB : (rhinge ? WEST_AABB : EAST_AABB);
                default -> closed ? EAST_AABB : (rhinge ? NORTH_AABB : SOUTH_AABB);
            };
        }
    }

    @Override
    @Nonnull
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbor, LevelAccessor accessor, BlockPos pos, BlockPos neighborpos) {
        return direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighbor, accessor, pos, neighborpos);
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType path) {
        return switch (path) {
            case LAND, AIR -> state.getValue(OPEN);
            default -> false;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        boolean flag = level.hasNeighborSignal(pos);

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(HINGE, this.getHinge(context)).setValue(POWERED, flag).setValue(OPEN, flag);
    }

    private DoorHingeSide getHinge(BlockPlaceContext context) {
        BlockGetter getter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        Direction clockdir = direction.getClockWise();
        Direction counterdir = direction.getCounterClockWise();
        BlockPos counterpos = blockpos.relative(counterdir);
        BlockPos blockpos4 = blockpos.relative(clockdir);
        BlockState blockstate = getter.getBlockState(counterpos);
        BlockState blockstate2 = getter.getBlockState(blockpos4);

        int i = (blockstate.isCollisionShapeFullBlock(getter, counterpos) ? -1 : 0) + (blockstate2.isCollisionShapeFullBlock(getter, blockpos4) ? 1 : 0);
        boolean flag = blockstate.is(this);
        boolean flag1 = blockstate2.is(this);
        if ((!flag || flag1) && i <= 0) {
            if ((!flag1 || flag) && i >= 0) {
                int dirX = direction.getStepX();
                int dirZ = direction.getStepZ();
                Vec3 vec3 = context.getClickLocation();
                double x = vec3.x - (double)blockpos.getX();
                double z = vec3.z - (double)blockpos.getZ();
                return (dirX >= 0 || !(z < 0.5D)) && (dirX <= 0 || !(z > 0.5D)) && (dirZ >= 0 || !(x > 0.5D)) && (dirZ <= 0 || !(x < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
            } else {
                return DoorHingeSide.LEFT;
            }
        } else {
            return DoorHingeSide.RIGHT;
        }
    }

    @Override
    @Nonnull
    @Deprecated
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if (builder.mealGrass && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_saddle_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if (builder.hoeDirt && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_saddle_door, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if (builder.shovelPath && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_saddle_door, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if (builder.hoeGrass && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_saddle_door, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        if (!builder.canOpen) {
            return InteractionResult.PASS;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlock(pos, state, 10);
            worldIn.playSound(player, pos, state.getValue(OPEN) ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, worldIn.getRandom().nextFloat() * 0.1F + 0.9F);
            worldIn.gameEvent(player, state.getValue(OPEN) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return InteractionResult.sidedSuccess(worldIn.isClientSide());
        }
    }

    private InteractionResult changeBlock(ItemStack itemstack, Supplier<? extends OrnamentSaddleDoor> newblock, SoundEvent sound, Level worldIn, BlockPos pos, Player player, InteractionHand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!player.getAbilities().instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide());
    }

    private void setBlock(Level world, BlockPos pos, Supplier<? extends OrnamentSaddleDoor> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(FACING, state.getValue(FACING))
                .setValue(OPEN, state.getValue(OPEN))
                .setValue(HINGE, state.getValue(HINGE))
                .setValue(POWERED, state.getValue(POWERED)));
    }

    @Override
    @Deprecated
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos frompos, boolean moving) {
        boolean flag = level.hasNeighborSignal(pos);
        if (!this.defaultBlockState().is(block) && flag != state.getValue(POWERED)) {
            if (flag != state.getValue(OPEN)) {
                level.playSound(null, pos, state.getValue(OPEN) ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                level.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            level.setBlock(pos, state.setValue(POWERED, flag).setValue(OPEN, flag), 2);
        }
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, Direction.UP) || blockstate.is(this);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float dist) {
        entity.causeFallDamage(dist, builder.fallMultiplier, level.damageSources().fall());
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
            world.setBlockAndUpdate(pos, builder.meltResult.defaultBlockState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Nonnull
    @Deprecated
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    @Nonnull
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    @Nonnull
    @Deprecated
    public BlockState mirror(BlockState state, Mirror mirror) {
        return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(FACING))).cycle(HINGE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(FACING, OPEN, HINGE, POWERED);
    }
}
