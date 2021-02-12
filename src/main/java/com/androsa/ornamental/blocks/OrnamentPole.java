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

public class OrnamentPole extends Block implements IWaterLoggable, IOrnamentalBlock {

    protected static final VoxelShape TL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape BL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE = Block.makeCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape TL_SHAPE_PATH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 15.0D, 8.0D);
    protected static final VoxelShape TR_SHAPE_PATH = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 15.0D, 8.0D);
    protected static final VoxelShape BL_SHAPE_PATH = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 15.0D, 16.0D);
    protected static final VoxelShape BR_SHAPE_PATH = Block.makeCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 15.0D, 16.0D);
    private static VoxelShape[] POLE_SHAPES = new VoxelShape[]{ TL_SHAPE, TR_SHAPE, BL_SHAPE, BR_SHAPE };
    private static VoxelShape[] POLE_SHAPES_PATH = new VoxelShape[]{ TL_SHAPE_PATH, TR_SHAPE_PATH, BL_SHAPE_PATH, BR_SHAPE_PATH };

    public static final EnumProperty<PoleType> TYPE = EnumProperty.create("type", PoleType.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final OrnamentBuilder builder;

    public OrnamentPole(Properties props, OrnamentBuilder builder) {
        super(props);
        this.builder = builder;
        this.setDefaultState(this.stateContainer.getBaseState().with(TYPE, PoleType.TL_CORNER).with(WATERLOGGED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> container) {
        container.add(TYPE, WATERLOGGED);
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        List<VoxelShape> shapes = Lists.newArrayList();
        boolean[] corners = state.get(TYPE).getCorners();

        for (int i = 0; i < corners.length; i++) {
            if (builder.isPath || builder.pathShape) {
                if (corners[i]) shapes.add(POLE_SHAPES_PATH[i]);
            } else {
                if (corners[i]) shapes.add(POLE_SHAPES[i]);
            }
        }

        Optional<VoxelShape> optional = shapes.stream().reduce(VoxelShapes::or);

        if (!optional.isPresent()) {
            OrnamentalMod.LOGGER.error("Ornament Pole had no shapes! Resorting to full cube");
            return VoxelShapes.fullCube();
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

        BlockPos pos = context.getPos();
        BlockState state = context.getWorld().getBlockState(pos);
        //All true: TL. Z true: TR. X true: BL. No true: BR.
        boolean x = context.getHitVec().x - (double)context.getPos().getX() < 0.5D;
        boolean z = context.getHitVec().z - (double)context.getPos().getZ() < 0.5D;
        FluidState fluidstate = context.getWorld().getFluidState(pos);
        boolean filled = fluidstate.getFluid() == Fluids.WATER;
        Direction direction = context.getFace();
        Direction.Axis axis = direction.getAxis();

        //Are we placing inside a Pole?
        if (state.isIn(this)) {

            switch (state.get(TYPE)) {
                case TL_FILL:
                case TR_FILL:
                case BL_FILL:
                case BR_FILL:
                    return state.with(TYPE, PoleType.FULL).with(WATERLOGGED, false);
                case TL_CORNER:
                    if (direction != Direction.NORTH && !x && z) {
                        return state.with(TYPE, PoleType.T_HALF).with(WATERLOGGED, filled);
                    } else if (direction != Direction.WEST && x && !z) {
                        return state.with(TYPE, PoleType.L_HALF).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.TL_BR).with(WATERLOGGED, filled);
                    }
                case TR_CORNER:
                    if (direction != Direction.NORTH && x && z || direction == Direction.WEST && z) {
                        return state.with(TYPE, PoleType.T_HALF).with(WATERLOGGED, filled);
                    } else if (direction != Direction.EAST && !x && !z) {
                        return state.with(TYPE, PoleType.R_HALF).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.TR_BL).with(WATERLOGGED, filled);
                    }
                case BL_CORNER:
                    if (direction != Direction.WEST && x && z || direction == Direction.NORTH && x) {
                        return state.with(TYPE, PoleType.L_HALF).with(WATERLOGGED, filled);
                    } else if (direction != Direction.SOUTH && !x && !z) {
                        return state.with(TYPE, PoleType.B_HALF).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.TR_BL).with(WATERLOGGED, filled);
                    }
                case BR_CORNER:
                    if (direction != Direction.EAST && !x && z || direction == Direction.NORTH && !x) {
                        return state.with(TYPE, PoleType.R_HALF).with(WATERLOGGED, filled);
                    } else if (direction != Direction.SOUTH && x && !z || direction == Direction.WEST && !z) {
                        return state.with(TYPE, PoleType.B_HALF).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.TL_BR).with(WATERLOGGED, filled);
                    }
                case T_HALF:
                    if (direction != Direction.WEST && x) {
                        return state.with(TYPE, PoleType.TL_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.TR_FILL).with(WATERLOGGED, filled);
                    }
                case L_HALF:
                    if (direction != Direction.NORTH && z) {
                        return state.with(TYPE, PoleType.TL_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.BL_FILL).with(WATERLOGGED, filled);
                    }
                case R_HALF:
                    if (direction != Direction.NORTH && z) {
                        return state.with(TYPE, PoleType.TR_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.BR_FILL).with(WATERLOGGED, filled);
                    }
                case B_HALF:
                    if (direction != Direction.WEST && x) {
                        return state.with(TYPE, PoleType.BL_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.BR_FILL).with(WATERLOGGED, filled);
                    }
                case TR_BL:
                    if ((axis.isVertical() || direction == Direction.EAST || direction == Direction.SOUTH) && x && z || direction == Direction.NORTH && x || direction == Direction.WEST && z) {
                        return state.with(TYPE, PoleType.TL_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.BR_FILL).with(WATERLOGGED, filled);
                    }
                case TL_BR:
                    if ((axis.isVertical() || direction == Direction.EAST || direction == Direction.SOUTH) && !x && z || direction == Direction.NORTH && !x || direction == Direction.WEST && z) {
                        return state.with(TYPE, PoleType.TR_FILL).with(WATERLOGGED, filled);
                    } else {
                        return state.with(TYPE, PoleType.BL_FILL).with(WATERLOGGED, filled);
                    }
                default:
                    //fallback. Just go full
                    OrnamentalMod.LOGGER.error("Ornamental Pole failed to place properly. State: " + state);
                    return state.with(TYPE, PoleType.FULL).with(WATERLOGGED, false);
            }
        } else {
            if (x && z) {
                return getDefaultState().with(TYPE, PoleType.TL_CORNER).with(WATERLOGGED, filled);
            } else if (!x && z) {
                return getDefaultState().with(TYPE, PoleType.TR_CORNER).with(WATERLOGGED, filled);
            } else if (x && !z) {
                return getDefaultState().with(TYPE, PoleType.BL_CORNER).with(WATERLOGGED, filled);
            } else {
                return getDefaultState().with(TYPE, PoleType.BR_CORNER).with(WATERLOGGED, filled);
            }
        }
    }

    @Override
    @Deprecated
    public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
        ItemStack stack = context.getItem();
        PoleType type = state.get(TYPE);

        //Only Full shapes are non-replaceable.
        if (type != PoleType.FULL && stack.getItem() == this.asItem()) {
            if (context.replacingClickedOnBlock()) {
                //All true: TL. Z true: TR. X true: BL. No true: BR.
                boolean x = context.getHitVec().x - (double)context.getPos().getX() < 0.5D;
                boolean z = context.getHitVec().z - (double)context.getPos().getZ() < 0.5D;
                Direction direction = context.getFace();

                switch (type) {
                    case TL_CORNER:
                        return !(x && z);
                    case TR_CORNER:
                        return !(!x && z) || direction == Direction.WEST;
                    case BL_CORNER:
                        return !(x && !z) || direction == Direction.NORTH;
                    case BR_CORNER:
                        return !(!x && !z) || direction == Direction.NORTH || direction == Direction.WEST;
                    case TL_FILL:
                        return !x && !z;
                    case TR_FILL:
                        return x && !z || direction == Direction.WEST && !x;
                    case BL_FILL:
                        return !x && z || direction == Direction.NORTH && !z;
                    case BR_FILL:
                        return x && z || direction == Direction.WEST && !x || direction == Direction.NORTH && !z;
                    case T_HALF:
                        return !z && direction == Direction.SOUTH;
                    case B_HALF:
                        return !z && direction == Direction.NORTH;
                    case L_HALF:
                        return !x && direction == Direction.EAST;
                    case R_HALF:
                        return !x && direction == Direction.WEST;
                    case TL_BR:
                        return (!x && z || x && !z) || direction == Direction.NORTH && !z || direction == Direction.WEST && !x;
                    case TR_BL:
                        return (x && z || !x && !z) || direction == Direction.NORTH && !z || direction == Direction.WEST && !x;
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
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.onLivingFall(distance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState state, IBlockReader world, BlockPos pos, Direction direction) {
        if (builder.hasPower) {
            switch (state.get(TYPE).getShape()) {
                case CORNER: return 4;
                case HALF: return 8;
                case FILL: return 12;
                case BLOCK: return 15;
            }
        }
        return super.getWeakPower(state, world, pos, direction);
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return state.get(TYPE) != PoleType.FULL && IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return state.get(TYPE) != PoleType.FULL && IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
    }

    @Override
    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Deprecated
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_pole, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_pole, SoundEvents.BLOCK_GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_pole, SoundEvents.ITEM_SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_pole, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentPole> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.isCreativeMode && !itemstack.isDamageable()) {
            itemstack.shrink(1);
        } else {
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentPole> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(TYPE, state.get(TYPE))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState otherState, Direction direction) {
        if (builder.isIce || builder.breakableCull) {
            if (otherState.getBlock() instanceof OrnamentPole && state.getBlock() instanceof OrnamentPole) {
                OrnamentPole pole = (OrnamentPole) state.getBlock();
                OrnamentPole otherPole = (OrnamentPole) otherState.getBlock();

                if (otherPole.getBuilder() == pole.getBuilder() && otherState.get(TYPE) == PoleType.FULL && state.get(TYPE) == PoleType.FULL) {
                    return true;
                }
            }
        }
        return super.isSideInvisible(state, otherState, direction);
    }

    @Override
    @Deprecated
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        switch(type) {
            case LAND:
                return false;
            case WATER:
                return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
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
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    protected void turnIntoWater(World world, BlockPos pos) {
        if (world.getDimensionType().isUltrawarm() && builder.canVaporise) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, builder.meltResult.getDefaultState());
            world.neighborChanged(pos, builder.meltResult, pos);
        }
    }

    @Override
    @Deprecated
    public PushReaction getPushReaction(BlockState state) {
        return builder.isIce ? PushReaction.NORMAL : builder.pushReaction;
    }
}
