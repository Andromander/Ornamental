package com.androsa.ornamental.blocks;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModBlocks;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class OrnamentBeam extends Block implements IWaterLoggable, IOrnamentalBlock {

    protected static final VoxelShape BL_SHAPE_X = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    protected static final VoxelShape BR_SHAPE_X = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_X = Block.box(0.0D, 8.0D, 0.0D, 16.0F, 16.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE_X = Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape BL_SHAPE_Z = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE_Z = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_Z = Block.box(0.0D, 8.0D, 0.0D, 8.0F, 16.0D, 16.0D);
    protected static final VoxelShape TR_SHAPE_Z = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape BL_SHAPE_X_PATH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 8.0D);
    protected static final VoxelShape BR_SHAPE_X_PATH = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_X_PATH = Block.box(0.0D, 8.0D, 0.0D, 16.0F, 15.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE_X_PATH = Block.box(0.0D, 8.0D, 8.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape BL_SHAPE_Z_PATH = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 7.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE_Z_PATH = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_Z_PATH = Block.box(0.0D, 8.0D, 0.0D, 8.0F, 15.0D, 16.0D);
    protected static final VoxelShape TR_SHAPE_Z_PATH = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    private static VoxelShape[] BEAM_SHAPES_X = new VoxelShape[]{ TL_SHAPE_X, TR_SHAPE_X, BL_SHAPE_X, BR_SHAPE_X };
    private static VoxelShape[] BEAM_SHAPES_Z = new VoxelShape[]{ TL_SHAPE_Z, TR_SHAPE_Z, BL_SHAPE_Z, BR_SHAPE_Z };
    private static VoxelShape[] BEAM_SHAPES_X_PATH = new VoxelShape[]{ TL_SHAPE_X_PATH, TR_SHAPE_X_PATH, BL_SHAPE_X_PATH, BR_SHAPE_X_PATH };
    private static VoxelShape[] BEAM_SHAPES_Z_PATH = new VoxelShape[]{ TL_SHAPE_Z_PATH, TR_SHAPE_Z_PATH, BL_SHAPE_Z_PATH, BR_SHAPE_Z_PATH };

    public static final EnumProperty<PoleType> TYPE = EnumProperty.create("type", PoleType.class);
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final OrnamentBuilder builder;

    public OrnamentBeam(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, PoleType.BL_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TYPE, HORIZONTAL_AXIS, WATERLOGGED);
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        List<VoxelShape> shapes = Lists.newArrayList();
        PoleType type = state.getValue(TYPE);
        Direction.Axis axis = state.getValue(HORIZONTAL_AXIS);
        boolean[] corners = type.getCorners();

        if (builder.isPath || builder.pathShape) {
            if (type == PoleType.L_HALF)
                return axis == Direction.Axis.X ? VoxelShapes.or(BL_SHAPE_X, TL_SHAPE_X_PATH) : VoxelShapes.or(BL_SHAPE_Z, TL_SHAPE_Z_PATH);
            else if (type == PoleType.R_HALF)
                return axis == Direction.Axis.X ? VoxelShapes.or(BR_SHAPE_X, TR_SHAPE_X_PATH) : VoxelShapes.or(BR_SHAPE_Z, TR_SHAPE_Z_PATH);
            else if (type == PoleType.TL_FILL)
                return axis == Direction.Axis.X ? VoxelShapes.or(BL_SHAPE_X, TL_SHAPE_X_PATH, TR_SHAPE_X_PATH) : VoxelShapes.or(BL_SHAPE_Z, TL_SHAPE_Z_PATH, TR_SHAPE_Z_PATH);
            else if (type == PoleType.TR_FILL)
                return axis == Direction.Axis.X ? VoxelShapes.or(BR_SHAPE_X, TL_SHAPE_X_PATH, TR_SHAPE_X_PATH) : VoxelShapes.or(BR_SHAPE_Z, TL_SHAPE_Z_PATH, TR_SHAPE_Z_PATH);
            else if (type == PoleType.BL_FILL)
                return axis == Direction.Axis.X ? VoxelShapes.or(BL_SHAPE_X, BR_SHAPE_X_PATH, TL_SHAPE_X_PATH) : VoxelShapes.or(BL_SHAPE_Z, BR_SHAPE_Z_PATH, TL_SHAPE_Z_PATH);
            else if (type == PoleType.BR_FILL)
                return axis == Direction.Axis.X ? VoxelShapes.or(BR_SHAPE_X, BL_SHAPE_X_PATH, TR_SHAPE_X_PATH) : VoxelShapes.or(BR_SHAPE_Z, BL_SHAPE_Z_PATH, TR_SHAPE_Z_PATH);
            else if (type == PoleType.FULL)
                return OrnamentSlab.PATH_FULL_SHAPE;
            else {
                for (int i = 0; i < corners.length; i++) {
                    if (corners[i]) {
                        if (state.getValue(HORIZONTAL_AXIS) == Direction.Axis.X) {
                            shapes.add(BEAM_SHAPES_X_PATH[i]);
                        } else {
                            shapes.add(BEAM_SHAPES_Z_PATH[i]);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < corners.length; i++) {
                if (corners[i]) {
                    if (state.getValue(HORIZONTAL_AXIS) == Direction.Axis.X) {
                        shapes.add(BEAM_SHAPES_X[i]);
                    } else {
                        shapes.add(BEAM_SHAPES_Z[i]);
                    }
                }
            }
        }

        Optional<VoxelShape> optional = shapes.stream().reduce(VoxelShapes::or);

        if (!optional.isPresent()) {
            OrnamentalMod.LOGGER.error("Ornament Pole had no shapes! Resorting to full cube");
            return VoxelShapes.block();
        }
        return optional.get();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        /*
         * 1. Check 3 corner blocks so we can become Full and remove Waterlogged
         * 2. Check 2 corner blocks so we can determine what 3 corner block it becomes, checking look context
         * 3. Check 1 corner blocks so we can determine what 2 corner block it becomes, checking look context
         * 4. Check look context to determine which corner we place.
         */

        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        //All true: TL. Z true: TR. X true: BL. No true: BR.
        boolean x = context.getClickLocation().x - (double)context.getClickedPos().getX() < 0.5D;
        boolean y = context.getClickLocation().y - (double)context.getClickedPos().getY() < 0.5D;
        boolean z = context.getClickLocation().z - (double)context.getClickedPos().getZ() < 0.5D;
        FluidState fluidstate = context.getLevel().getFluidState(pos);
        boolean filled = fluidstate.getType() == Fluids.WATER;
        Direction direction = context.getClickedFace();
        Direction.Axis facing = context.getHorizontalDirection().getAxis();
        Direction.Axis axis = direction.getAxis();


        //Are we placing inside a Beam?
        if (state.is(this)) {
            Direction.Axis stateAxis = state.getValue(HORIZONTAL_AXIS);

            switch (state.getValue(TYPE)) {
                case TL_FILL:
                case TR_FILL:
                case BL_FILL:
                case BR_FILL:
                    return state.setValue(TYPE, PoleType.FULL).setValue(WATERLOGGED, false);
                case TL_CORNER:
                    if (((!z && stateAxis == Direction.Axis.X) || (!x && stateAxis == Direction.Axis.Z)) && !y) {
                        return state.setValue(TYPE, PoleType.T_HALF).setValue(WATERLOGGED, filled);
                    } else if ((z && stateAxis == Direction.Axis.X) || (x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.L_HALF).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.TL_BR).setValue(WATERLOGGED, filled);
                    }
                case TR_CORNER:
                    if ((((z || direction == Direction.NORTH) && stateAxis == Direction.Axis.X) || ((x || direction == Direction.WEST) && stateAxis == Direction.Axis.Z)) && !y) {
                        return state.setValue(TYPE, PoleType.T_HALF).setValue(WATERLOGGED, filled);
                    } else if ((!z && stateAxis == Direction.Axis.X) || (!x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.R_HALF).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.TR_BL).setValue(WATERLOGGED, filled);
                    }
                case BL_CORNER:
                    if (((!z && stateAxis == Direction.Axis.X) || (!x && stateAxis == Direction.Axis.Z)) && y) {
                        return state.setValue(TYPE, PoleType.B_HALF).setValue(WATERLOGGED, filled);
                    } else if ((z && stateAxis == Direction.Axis.X) || (x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.L_HALF).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.TR_BL).setValue(WATERLOGGED, filled);
                    }
                case BR_CORNER:
                    if ((((z || direction == Direction.NORTH) && stateAxis == Direction.Axis.X) || ((x || direction == Direction.WEST) && stateAxis == Direction.Axis.Z)) && y) {
                        return state.setValue(TYPE, PoleType.B_HALF).setValue(WATERLOGGED, filled);
                    } else if ((!z && stateAxis == Direction.Axis.X) || (!x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.R_HALF).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.TL_BR).setValue(WATERLOGGED, filled);
                    }
                case T_HALF:
                    if ((z && stateAxis == Direction.Axis.X) || (x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.TL_FILL).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.TR_FILL).setValue(WATERLOGGED, filled);
                    }
                case L_HALF:
                    if (!y) {
                        return state.setValue(TYPE, PoleType.TL_FILL).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.BL_FILL).setValue(WATERLOGGED, filled);
                    }
                case R_HALF:
                    if (!y) {
                        return state.setValue(TYPE, PoleType.TR_FILL).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.BR_FILL).setValue(WATERLOGGED, filled);
                    }
                case B_HALF:
                    if ((z && stateAxis == Direction.Axis.X) || (x && stateAxis == Direction.Axis.Z)) {
                        return state.setValue(TYPE, PoleType.BL_FILL).setValue(WATERLOGGED, filled);
                    } else {
                        return state.setValue(TYPE, PoleType.BR_FILL).setValue(WATERLOGGED, filled);
                    }
                case TR_BL:
                    if (stateAxis == Direction.Axis.X) {
                        if ((axis.isVertical() || axis == Direction.Axis.X || direction == Direction.SOUTH) && z && (!y || direction == Direction.UP) || direction == Direction.NORTH && !y) {
                            return state.setValue(TYPE, PoleType.TL_FILL).setValue(WATERLOGGED, filled);
                        } else {
                            return state.setValue(TYPE, PoleType.BR_FILL).setValue(WATERLOGGED, filled);
                        }
                    } else {
                        if ((axis.isVertical() || axis == Direction.Axis.Z || direction == Direction.EAST) && x && (!y || direction == Direction.UP) || direction == Direction.WEST && !y) {
                            return state.setValue(TYPE, PoleType.TL_FILL).setValue(WATERLOGGED, filled);
                        } else {
                            return state.setValue(TYPE, PoleType.BR_FILL).setValue(WATERLOGGED, filled);
                        }
                    }
                case TL_BR:
                    if (stateAxis == Direction.Axis.X) {
                        if ((axis.isVertical() || axis == Direction.Axis.X || direction == Direction.SOUTH) && !z && (!y || direction == Direction.UP) || direction == Direction.NORTH && !y) {
                            return state.setValue(TYPE, PoleType.TR_FILL).setValue(WATERLOGGED, filled);
                        } else {
                            return state.setValue(TYPE, PoleType.BL_FILL).setValue(WATERLOGGED, filled);
                        }
                    } else {
                        if ((axis.isVertical() || axis == Direction.Axis.Z || direction == Direction.EAST) && !x && (!y || direction == Direction.UP) || direction == Direction.WEST && !y) {
                            return state.setValue(TYPE, PoleType.TR_FILL).setValue(WATERLOGGED, filled);
                        } else {
                            return state.setValue(TYPE, PoleType.BL_FILL).setValue(WATERLOGGED, filled);
                        }
                    }
                default:
                    //fallback. Just go full
                    OrnamentalMod.LOGGER.error("Ornamental Pole failed to place properly. State: " + state);
                    return state.setValue(TYPE, PoleType.FULL).setValue(WATERLOGGED, false);
            }
        } else {
            if (facing == Direction.Axis.X) {
                if (x && y) return defaultBlockState().setValue(TYPE, PoleType.BL_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.Z).setValue(WATERLOGGED, filled);
                else if (!x && y) return defaultBlockState().setValue(TYPE, PoleType.BR_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.Z).setValue(WATERLOGGED, filled);
                else if (x && !y) return defaultBlockState().setValue(TYPE, PoleType.TL_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.Z).setValue(WATERLOGGED, filled);
                else return defaultBlockState().setValue(TYPE, PoleType.TR_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.Z).setValue(WATERLOGGED, filled);
            } else {
                if (z && y) return defaultBlockState().setValue(TYPE, PoleType.BL_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(WATERLOGGED, filled);
                else if (!z && y) return defaultBlockState().setValue(TYPE, PoleType.BR_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(WATERLOGGED, filled);
                else if (z && !y) return defaultBlockState().setValue(TYPE, PoleType.TL_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(WATERLOGGED, filled);
                else return defaultBlockState().setValue(TYPE, PoleType.TR_CORNER).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(WATERLOGGED, filled);
            }
        }
    }

    @Override
    @Deprecated
    public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
        ItemStack stack = context.getItemInHand();
        PoleType type = state.getValue(TYPE);
        Direction.Axis axis = state.getValue(HORIZONTAL_AXIS);

        //Only Full shapes are non-replaceable.
        if (type != PoleType.FULL && stack.getItem() == this.asItem()) {
            if (context.replacingClickedOnBlock()) {
                //All true: TL. Z true: TR. X true: BL. No true: BR.
                boolean x = context.getClickLocation().x - (double)context.getClickedPos().getX() < 0.5D;
                boolean y = context.getClickLocation().y - (double)context.getClickedPos().getY() < 0.5D;
                boolean z = context.getClickLocation().z - (double)context.getClickedPos().getZ() < 0.5D;
                Direction direction = context.getClickedFace();

                switch (type) {
                    case TL_CORNER:
                        if (axis == Direction.Axis.X)
                            return !(z && !y) || direction == Direction.DOWN;
                        else return !(x && !y) || direction == Direction.DOWN;
                    case TR_CORNER:
                        if (axis == Direction.Axis.X)
                            return !(!z && !y) || direction == Direction.NORTH || direction == Direction.DOWN;
                        else return !(!x && !y) || direction == Direction.WEST || direction == Direction.DOWN;
                    case BL_CORNER:
                        if (axis == Direction.Axis.X)
                            return !(z && y) || direction == Direction.UP;
                        else return !(x && y) || direction == Direction.UP;
                    case BR_CORNER:
                        if (axis == Direction.Axis.X)
                            return !(!z && y) || direction == Direction.NORTH || direction == Direction.UP;
                        else return !(!x && y) || direction == Direction.WEST || direction == Direction.UP;
                    case TL_FILL:
                        return axis == Direction.Axis.X ? (y || direction == Direction.DOWN) && !z : (y || direction == Direction.DOWN) && !x;
                    case TR_FILL:
                        return axis == Direction.Axis.X ? (y || direction == Direction.DOWN) && (z || direction == Direction.NORTH) : (y || direction == Direction.DOWN) && (x || direction == Direction.WEST);
                    case BL_FILL:
                        return axis == Direction.Axis.X ? (!y || direction == Direction.UP) && !z : (!y || direction == Direction.UP) && !x;
                    case BR_FILL:
                        return axis == Direction.Axis.X ? (!y || direction == Direction.UP) && (z || direction == Direction.NORTH) : (!y || direction == Direction.UP) && (x || direction == Direction.WEST);
                    case T_HALF:
                        return y;
                    case B_HALF:
                        return !y || direction == Direction.UP;
                    case L_HALF:
                        return axis == Direction.Axis.X ? !z : !x;
                    case R_HALF:
                        return axis == Direction.Axis.X ? z || direction == Direction.NORTH : x || direction == Direction.WEST;
                    case TL_BR:
                        if (axis == Direction.Axis.X)
                            return (y && z || (!y || direction == Direction.UP) && !z) || direction == Direction.NORTH && !z || direction == Direction.DOWN && !y;
                        else return (y && x || (!y || direction == Direction.UP) && !x) || direction == Direction.WEST && !x || direction == Direction.DOWN && !y;
                    case TR_BL:
                        if (axis == Direction.Axis.X)
                            return ((!y || direction == Direction.UP) && z || y && !z) || direction == Direction.NORTH && !z || direction == Direction.DOWN && !y;
                            else return ((!y || direction == Direction.UP) && x || y && !x) || direction == Direction.WEST && !x || direction == Direction.DOWN && !y;
                    default:
                        return false; //Full is the only one left
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void fallOn(World world, BlockPos pos, Entity entity, float distance) {
        entity.causeFallDamage(distance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean isSignalSource(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getSignal(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        if (builder.hasPower) {
            switch (state.getValue(TYPE).getShape()) {
                case CORNER: return 4;
                case HALF: return 8;
                case FILL: return 12;
                case BLOCK: return 15;
            }
        }
        return super.getSignal(state, world, pos, direction);
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return state.getValue(TYPE) != PoleType.FULL && IWaterLoggable.super.placeLiquid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return state.getValue(TYPE) != PoleType.FULL && IWaterLoggable.super.canPlaceLiquid(worldIn, pos, state, fluidIn);
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_beam, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_beam, SoundEvents.GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_beam, SoundEvents.SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_beam, SoundEvents.GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.use(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentBeam> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.instabuild && !itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentBeam> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, block.get().defaultBlockState()
                .setValue(TYPE, state.getValue(TYPE))
                .setValue(HORIZONTAL_AXIS, state.getValue(HORIZONTAL_AXIS))
                .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce || builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentBeam && state.getBlock() instanceof OrnamentBeam) {
                OrnamentBeam pole = (OrnamentBeam) state.getBlock();
                OrnamentBeam otherPole = (OrnamentBeam) otherState.getBlock();

                if (otherPole.getBuilder() == pole.getBuilder() && otherState.getValue(TYPE) == PoleType.FULL && state.getValue(TYPE) == PoleType.FULL) {
                    return true;
                }
            }
        }
        return super.skipRendering(state, otherState, direction);
    }

    @Override
    @Deprecated
    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		if (type == PathType.WATER) {
			return worldIn.getFluidState(pos).is(FluidTags.WATER);
		}
		return false;
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
