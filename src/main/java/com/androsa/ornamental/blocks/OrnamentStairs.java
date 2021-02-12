package com.androsa.ornamental.blocks;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.properties.Half;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class OrnamentStairs extends StairsBlock implements IOrnamentalBlock {

    protected static final VoxelShape PATH_AABB_SLAB_TOP = OrnamentSlab.PATH_TOP_SHAPE;
    protected static final VoxelShape PATH_AABB_SLAB_BOTTOM = OrnamentSlab.PATH_BOTTOM_SHAPE;
    protected static final VoxelShape PATH_NWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
    protected static final VoxelShape PATH_SWD_CORNER = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
    protected static final VoxelShape PATH_NWU_CORNER = Block.makeCuboidShape(0.0D, 7.0D, 0.0D, 8.0D, 15.0D, 8.0D);
    protected static final VoxelShape PATH_SWU_CORNER = Block.makeCuboidShape(0.0D, 7.0D, 8.0D, 8.0D, 15.0D, 16.0D);
    protected static final VoxelShape PATH_NED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    protected static final VoxelShape PATH_SED_CORNER = Block.makeCuboidShape(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape PATH_NEU_CORNER = Block.makeCuboidShape(8.0D, 7.0D, 0.0D, 16.0D, 15.0D, 8.0D);
    protected static final VoxelShape PATH_SEU_CORNER = Block.makeCuboidShape(8.0D, 7.0D, 8.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape[] PATH_SLAB_TOP_SHAPES = makeShapes(PATH_AABB_SLAB_TOP, PATH_NWD_CORNER, PATH_NED_CORNER, PATH_SWD_CORNER, PATH_SED_CORNER);
    protected static final VoxelShape[] PATH_SLAB_BOTTOM_SHAPES = makeShapes(PATH_AABB_SLAB_BOTTOM, PATH_NWU_CORNER, PATH_NEU_CORNER, PATH_SWU_CORNER, PATH_SEU_CORNER);
    private static final int[] metaInt = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};

    private OrnamentBuilder builder;

    public OrnamentStairs(Properties props, OrnamentBuilder builder) {
        super(() -> new Block(props).getDefaultState(), props);
        this.builder = builder;
    }

    @Override
    public OrnamentBuilder getBuilder() {
        return builder;
    }

    private static VoxelShape[] makeShapes(VoxelShape slabShape, VoxelShape nwCorner, VoxelShape neCorner, VoxelShape swCorner, VoxelShape seCorner) {
        return IntStream.range(0, 16).mapToObj((meta) -> combineShapes(meta, slabShape, nwCorner, neCorner, swCorner, seCorner)).toArray(VoxelShape[]::new);
    }

    private static VoxelShape combineShapes(int bitfield, VoxelShape slabShape, VoxelShape nwCorner, VoxelShape neCorner, VoxelShape swCorner, VoxelShape seCorner) {
        VoxelShape voxelshape = slabShape;
        if ((bitfield & 1) != 0) {
            voxelshape = VoxelShapes.or(slabShape, nwCorner);
        }

        if ((bitfield & 2) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, neCorner);
        }

        if ((bitfield & 4) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, swCorner);
        }

        if ((bitfield & 8) != 0) {
            voxelshape = VoxelShapes.or(voxelshape, seCorner);
        }

        return voxelshape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (builder.isPath || builder.pathShape)
            return (state.get(HALF) == Half.TOP ? PATH_SLAB_TOP_SHAPES : PATH_SLAB_BOTTOM_SHAPES)[metaInt[this.getShapeMeta(state)]];
        return super.getShape(state, worldIn, pos, context);
    }

    private int getShapeMeta(BlockState state) {
        return state.get(SHAPE).ordinal() * 4 + state.get(FACING).getHorizontalIndex();
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, builder.fallMultiplier);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return builder.hasPower;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return builder.hasPower ? 11 : 0;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (!itemstack.isEmpty()) {
            if ((builder.isDirt || builder.mealGrass) && item == Items.BONE_MEAL) {
                return changeBlock(itemstack, ModBlocks.grass_stairs, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }

            if ((builder.isGrass || builder.hoeDirt) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.dirt_stairs, SoundEvents.BLOCK_GRAVEL_BREAK, worldIn, pos, player, hand);
            }
            if ((builder.isGrass || builder.shovelPath) && item instanceof ShovelItem) {
                return changeBlock(itemstack, ModBlocks.path_stairs, SoundEvents.ITEM_SHOVEL_FLATTEN, worldIn, pos, player, hand);
            }

            if ((builder.isPath || builder.hoeGrass) && item instanceof HoeItem) {
                return changeBlock(itemstack, ModBlocks.grass_stairs, SoundEvents.BLOCK_GRASS_BREAK, worldIn, pos, player, hand);
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, hand, result);
    }

    private ActionResultType changeBlock(ItemStack itemstack, Supplier<? extends OrnamentStairs> newblock, SoundEvent sound, World worldIn, BlockPos pos, PlayerEntity player, Hand hand) {
        this.setBlock(worldIn, pos, newblock);
        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!player.abilities.isCreativeMode && !itemstack.isDamageable()) {
            itemstack.shrink(1);
        } else {
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
        }
        return ActionResultType.SUCCESS;
    }

    private void setBlock(World world, BlockPos pos, Supplier<? extends OrnamentStairs> block) {
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, block.get().getDefaultState()
                .with(FACING, state.get(FACING))
                .with(SHAPE, state.get(SHAPE))
                .with(HALF, state.get(HALF))
                .with(WATERLOGGED, state.get(WATERLOGGED)));
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
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        if (builder.isIce || builder.canMelt) {
            if (worldIn.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(worldIn, pos)) {
                this.turnIntoWater(worldIn, pos);
            }
        }
    }

    private void turnIntoWater(World world, BlockPos pos) {
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
