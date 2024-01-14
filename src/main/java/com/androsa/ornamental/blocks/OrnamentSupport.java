package com.androsa.ornamental.blocks;

import com.androsa.ornamental.builder.BlockConverter;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModTags;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class OrnamentSupport extends Block implements SimpleWaterloggedBlock, OrnamentalBlock {

    public static final MapCodec<OrnamentSupport> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(OrnamentBuilder.CODEC.fieldOf("ornament_builder").forGetter(OrnamentSupport::getBuilder),
                            propertiesCodec())
                    .apply(instance, OrnamentSupport::new));

    public static final EnumProperty<CornerType> CORNER = EnumProperty.create("corner", CornerType.class);
    public static final BooleanProperty UPPER_HALF = BooleanProperty.create("upper_half");
    public static final BooleanProperty NS_CONNECT = BooleanProperty.create("ns_connect"); //North-South
    public static final BooleanProperty EW_CONNECT = BooleanProperty.create("ew_connect"); //East-West
    public static final BooleanProperty TB_CONNECT = BooleanProperty.create("tb_connect"); //Top-Bottom
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    //Key: U/D: Up or Down, for Half. T/B: Top or Bottom, for Z-value. L/R: Left or Right, for X-value
    protected static final VoxelShape DTL_CORNER = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
    protected static final VoxelShape DTR_CORNER = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    protected static final VoxelShape DBL_CORNER = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
    protected static final VoxelShape DBR_CORNER = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape UTL_CORNER = Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape UTR_CORNER = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape UBL_CORNER = Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape UBR_CORNER = Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape PATH_DTL_CORNER = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 7.0D, 8.0D);
    protected static final VoxelShape PATH_DTR_CORNER = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 7.0D, 8.0D);
    protected static final VoxelShape PATH_DBL_CORNER = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_DBR_CORNER = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape PATH_UTL_CORNER = Block.box(0.0D, 8.0D, 0.0D, 8.0D, 15.0D, 8.0D);
    protected static final VoxelShape PATH_UTR_CORNER = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 15.0D, 8.0D);
    protected static final VoxelShape PATH_UBL_CORNER = Block.box(0.0D, 8.0D, 8.0D, 8.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_UBR_CORNER = Block.box(8.0D, 8.0D, 8.0D, 16.0D, 15.0D, 16.0D);

    private final OrnamentBuilder builder;

    public OrnamentSupport(OrnamentBuilder builder, Properties props) {
        super(props);
        this.builder = builder;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(CORNER, CornerType.TOP_LEFT)
                .setValue(UPPER_HALF, false)
                .setValue(NS_CONNECT, false)
                .setValue(EW_CONNECT, false)
                .setValue(TB_CONNECT, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CORNER, UPPER_HALF, NS_CONNECT, EW_CONNECT, TB_CONNECT, WATERLOGGED);
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        List<VoxelShape> shapes = Lists.newArrayList();
        boolean path = builder.pathShape;

        switch (state.getValue(CORNER)) {
            case TOP_LEFT -> {
                if (path && (!state.getValue(TB_CONNECT) || state.getValue(UPPER_HALF))) {
                    addShape(Optional.empty(), state, shapes, PATH_UTL_CORNER, PATH_DTL_CORNER);
                } else {
                    addShape(Optional.empty(), state, shapes, UTL_CORNER, DTL_CORNER);
                }
                if (path && !state.getValue(UPPER_HALF)) {
                    addShape(Optional.of(TB_CONNECT), state, shapes, PATH_DTL_CORNER, PATH_UTL_CORNER);
                } else {
                    addShape(Optional.of(TB_CONNECT), state, shapes, DTL_CORNER, UTL_CORNER);
                }
                addShape(Optional.of(NS_CONNECT), state, shapes, path ? PATH_UBL_CORNER : UBL_CORNER, path ? PATH_DBL_CORNER : DBL_CORNER);
                addShape(Optional.of(EW_CONNECT), state, shapes, path ? PATH_UTR_CORNER : UTR_CORNER, path ? PATH_DTR_CORNER : DTR_CORNER);
            }
            case TOP_RIGHT -> {
                if (path && (!state.getValue(TB_CONNECT) || state.getValue(UPPER_HALF))) {
                    addShape(Optional.empty(), state, shapes, PATH_UTR_CORNER, PATH_DTR_CORNER);
                } else {
                    addShape(Optional.empty(), state, shapes, UTR_CORNER, DTR_CORNER);
                }
                if (path && !state.getValue(UPPER_HALF)) {
                    addShape(Optional.of(TB_CONNECT), state, shapes, PATH_DTR_CORNER, PATH_UTR_CORNER);
                } else {
                    addShape(Optional.of(TB_CONNECT), state, shapes, DTR_CORNER, UTR_CORNER);
                }
                addShape(Optional.of(NS_CONNECT), state, shapes, path ? PATH_UBR_CORNER : UBR_CORNER, path ? PATH_DBR_CORNER : DBR_CORNER);
                addShape(Optional.of(EW_CONNECT), state, shapes, path ? PATH_UTL_CORNER : UTL_CORNER, path ? PATH_DTL_CORNER : DTL_CORNER);
            }
            case BOTTOM_LEFT -> {
                if (path && (!state.getValue(TB_CONNECT) || state.getValue(UPPER_HALF))) {
                    addShape(Optional.empty(), state, shapes, PATH_UBL_CORNER, PATH_DBL_CORNER);
                } else {
                    addShape(Optional.empty(), state, shapes, UBL_CORNER, DBL_CORNER);
                }
                if (path && !state.getValue(UPPER_HALF)) {
                    addShape(Optional.of(TB_CONNECT), state, shapes, PATH_DBL_CORNER, PATH_UBL_CORNER);
                } else {
                    addShape(Optional.of(TB_CONNECT), state, shapes, DBL_CORNER, UBL_CORNER);
                }
                addShape(Optional.of(NS_CONNECT), state, shapes, path ? PATH_UTL_CORNER : UTL_CORNER, path ? PATH_DTL_CORNER : DTL_CORNER);
                addShape(Optional.of(EW_CONNECT), state, shapes, path ? PATH_UBR_CORNER : UBR_CORNER, path ? PATH_DBR_CORNER : DBR_CORNER);
            }
            case BOTTOM_RIGHT -> {
                if (path && (!state.getValue(TB_CONNECT) || state.getValue(UPPER_HALF))) {
                    addShape(Optional.empty(), state, shapes, PATH_UBR_CORNER, PATH_DBR_CORNER);
                } else {
                    addShape(Optional.empty(), state, shapes, UBR_CORNER, DBR_CORNER);
                }
                if (path && !state.getValue(UPPER_HALF)) {
                    addShape(Optional.of(TB_CONNECT), state, shapes, PATH_DBR_CORNER, PATH_UBR_CORNER);
                } else {
                    addShape(Optional.of(TB_CONNECT), state, shapes, DBR_CORNER, UBR_CORNER);
                }
                addShape(Optional.of(NS_CONNECT), state, shapes, path ? PATH_UTR_CORNER : UTR_CORNER, path ? PATH_DTR_CORNER : DTR_CORNER);
                addShape(Optional.of(EW_CONNECT), state, shapes, path ? PATH_UBL_CORNER : UBL_CORNER, path ? PATH_DBL_CORNER : DBL_CORNER);
            }
        }

        return shapes.stream().reduce(Shapes::or).orElse(Shapes.block());
    }

    private void addShape(Optional<BooleanProperty> connection, BlockState state, List<VoxelShape> list, VoxelShape upper, VoxelShape lower) {
        //No check or check is true
        if (connection.isEmpty() || state.getValue(connection.get())) {
            list.add(state.getValue(UPPER_HALF) ? upper : lower);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        /*
         * This block is divided into 8 pieces. Upper half is checked first followed by the corner.
         * Waterlogging is checked as usual.
         * Unlike Poles and Beams, however, Supports will not be checking for an existing Support state.
         * This is because Supports are the sole block, they will have their state altered externally.
         */

        boolean upper = context.getClickedFace() == Direction.DOWN || context.getClickLocation().y() - context.getClickedPos().getY() > 0.5D;
        boolean x = context.getClickLocation().x() - context.getClickedPos().getX() < 0.5D;
        boolean z = context.getClickLocation().z() - context.getClickedPos().getZ() < 0.5D;
        CornerType corner = null;

        if (x && z) corner = CornerType.TOP_LEFT;
        if (!x && z) corner = CornerType.TOP_RIGHT;
        if (x && !z) corner = CornerType.BOTTOM_LEFT;
        if (!x && !z) corner = CornerType.BOTTOM_RIGHT;

        return defaultBlockState().setValue(UPPER_HALF, upper).setValue(CORNER, corner).setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
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
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
        entity.causeFallDamage(distance, builder.fallMultiplier, level.damageSources().fall());
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        if (builder.hasPower) {
            int power = 2;
            if (state.getValue(TB_CONNECT)) power += 2;
            if (state.getValue(NS_CONNECT)) power += 2;
            if (state.getValue(EW_CONNECT)) power += 2;
            return power;
        }
        return super.getSignal(state, getter, pos, direction);
    }

    @Override
    @Nonnull
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor accessor, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        if (builder.createBubbles) {
            if (direction == Direction.UP && facingState.is(Blocks.WATER)) {
                accessor.scheduleTick(pos, this, builder.tickSchedule);
            }
        }

        return super.updateShape(state, direction, facingState, accessor, pos, facingPos);
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);

        if (builder.convertPredicates != null) {
            for (BlockConverter converter : builder.convertPredicates) {
                if (converter.predicate().test(state, level, pos, player, hand, result)) {
                    return changeBlock(stack, converter.list().get().get(10), converter.sound(), level, pos, player, hand);
                }
            }
        }

        //Convert predicates overrule changing states of Supports
        if (stack.is(ModTags.Items.SUPPORT_FILLERS)) {
            switch (result.getDirection().getAxis()) {
                case X -> {
                    if (!state.getValue(EW_CONNECT)) {
                        level.setBlock(pos, state.setValue(EW_CONNECT, true), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
                case Y -> {
                    if (!state.getValue(TB_CONNECT)) {
                        level.setBlock(pos, state.setValue(TB_CONNECT, true), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
                case Z -> {
                    if (!state.getValue(NS_CONNECT)) {
                        level.setBlock(pos, state.setValue(NS_CONNECT, true), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        if (stack.canPerformAction(ToolActions.AXE_STRIP)) {
            switch (result.getDirection().getAxis()) {
                case X -> {
                    if (state.getValue(EW_CONNECT)) {
                        level.setBlock(pos, state.setValue(EW_CONNECT, false), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
                case Y -> {
                    if (state.getValue(TB_CONNECT)) {
                        level.setBlock(pos, state.setValue(TB_CONNECT, false), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
                case Z -> {
                    if (state.getValue(NS_CONNECT)) {
                        level.setBlock(pos, state.setValue(NS_CONNECT, false), 3);
                        level.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return super.use(state, level, pos, player, hand, result);
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
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
        if (type == PathComputationType.WATER) {
            return getter.getFluidState(pos).is(FluidTags.WATER);
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
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        super.randomTick(state, level, pos, source);
        if (builder.canMelt) {
            if (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos)) {
                this.turnIntoWater(level, pos);
            }
        }

        if (builder.extinguishes) {
            BlockPos above = pos.above();
            if (level.getFluidState(pos).canExtinguish(level, pos)) {
                level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
                level.sendParticles(ParticleTypes.LARGE_SMOKE, above.getX() + 0.5D, above.getY() + 0.25D, above.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
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
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState neighbour, boolean moving) {
        if (builder.createBubbles) {
            level.scheduleTick(pos, this, builder.tickSchedule);
        }
    }

    @Override
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

    public enum CornerType implements StringRepresentable {
        TOP_LEFT("top_left"),
        TOP_RIGHT("top_right"),
        BOTTOM_LEFT("bottom_left"),
        BOTTOM_RIGHT("bottom_right");

        private final String name;

        CornerType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
