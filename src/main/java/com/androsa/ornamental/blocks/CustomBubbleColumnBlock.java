package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class CustomBubbleColumnBlock extends Block implements BucketPickup {
    public static final BooleanProperty DRAG_DOWN = BlockStateProperties.DRAG;

    public CustomBubbleColumnBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(DRAG_DOWN, true));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Shapes.empty();
    }

    @Override
    @Deprecated
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        BlockState stateabove = level.getBlockState(pos.above());
        if (stateabove.isAir()) {
            entity.onAboveBubbleCol(state.getValue(DRAG_DOWN));
            if (!level.isClientSide()) {
                ServerLevel server = (ServerLevel) level;

                for (int i = 0; i < 2; i++) {
                    server.sendParticles(ParticleTypes.SPLASH, pos.getX() + level.random.nextDouble(), pos.getY() + 1, pos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    server.sendParticles(ParticleTypes.BUBBLE, pos.getX() + level.random.nextDouble(), pos.getY() + 1, pos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        } else {
            entity.onInsideBubbleColumn(state.getValue(DRAG_DOWN));
        }
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        updateColumn(level, pos, state, level.getBlockState(pos.below()));
    }

    public static void updateColumn(LevelAccessor accessor, BlockPos pos, BlockState state) {
        updateColumn(accessor, pos, accessor.getBlockState(pos), state);
    }

    public static void updateColumn(LevelAccessor accessor, BlockPos pos, BlockState state, BlockState below) {
        if (canExistIn(state)) {
            BlockState columnstate = getColumnState(below);
            accessor.setBlock(pos, columnstate, 2);
            BlockPos.MutableBlockPos mutable = pos.mutable().move(Direction.UP);

            while (canExistIn(accessor.getBlockState(mutable))) {
                if (!accessor.setBlock(mutable, columnstate, 2)) {
                    return;
                }
                mutable.move(Direction.UP);
            }
        }
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState facing, LevelAccessor level, BlockPos pos, BlockPos nearpos) {
        level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        if (!state.canSurvive(level, pos) || direction == Direction.DOWN || direction == Direction.UP && !facing.is(ModBlocks.fake_bubble_column.get()) && canExistIn(facing)) {
            level.scheduleTick(pos, this, 5);
        }
        return super.updateShape(state, direction, facing, level, pos, nearpos);
    }

    private static boolean canExistIn(BlockState state) {
        return state.is(ModBlocks.fake_bubble_column.get()) || state.is(Blocks.WATER) && state.getFluidState().getAmount() >= 8 && state.getFluidState().isSource();
    }

    private static BlockState getColumnState(BlockState state) {
        if (state.is(ModBlocks.fake_bubble_column.get())) {
            return state;
        } else if (state.getBlock() instanceof OrnamentalBlock ornament) {
            if (!ornament.getBuilder().bubbleDragDown) {
                return ModBlocks.fake_bubble_column.get().defaultBlockState().setValue(DRAG_DOWN, false);
            } else {
                return ModBlocks.fake_bubble_column.get().defaultBlockState().setValue(DRAG_DOWN, true);
            }
        }
        return Blocks.WATER.defaultBlockState();
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        if (state.getValue(DRAG_DOWN)) {
            level.addAlwaysVisibleParticle(ParticleTypes.CURRENT_DOWN, x + 0.5D, y + 0.8D, z, 0.0D, 0.0D, 0.0D);
            if (random.nextInt(200) == 0) {
                level.playLocalSound(x, y, z, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        } else {
            level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + 0.5D, y + 0.8D, z + 0.5D, 0.0D, 0.04D, 0.0D);
            level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), 0.0D, 0.04D, 0.0D);
            if (random.nextInt(200) == 0) {
                level.playLocalSound(x, y, z, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return below.is(ModBlocks.fake_bubble_column.get()) || (state.getBlock() instanceof OrnamentalBlock ornament && ornament.getBuilder().createBubbles);
    }

    @Override
    public ItemStack pickupBlock(Player player, LevelAccessor accessor, BlockPos pos, BlockState state) {
        accessor.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        return new ItemStack(Items.WATER_BUCKET);
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Fluids.WATER.getPickupSound();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DRAG_DOWN);
    }
}
