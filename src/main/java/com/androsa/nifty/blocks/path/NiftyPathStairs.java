package com.androsa.nifty.blocks.path;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.Half;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.stream.IntStream;

public class NiftyPathStairs extends NiftyStairs {

    protected static final VoxelShape AABB_SLAB_TOP = NiftyPathSlab.TOP_SHAPE;
    protected static final VoxelShape AABB_SLAB_BOTTOM = NiftyPathSlab.BOTTOM_SHAPE;
    protected static final VoxelShape NWD_CORNER = Block.makeCuboidShape(0.0D, 1.0D, 0.0D, 8.0D, 8.0D, 8.0D);
    protected static final VoxelShape SWD_CORNER = Block.makeCuboidShape(0.0D, 1.0D, 8.0D, 8.0D, 8.0D, 16.0D);
    protected static final VoxelShape NWU_CORNER = Block.makeCuboidShape(0.0D, 7.0D, 0.0D, 8.0D, 15.0D, 8.0D);
    protected static final VoxelShape SWU_CORNER = Block.makeCuboidShape(0.0D, 7.0D, 8.0D, 8.0D, 15.0D, 16.0D);
    protected static final VoxelShape NED_CORNER = Block.makeCuboidShape(8.0D, 1.0D, 0.0D, 16.0D, 8.0D, 8.0D);
    protected static final VoxelShape SED_CORNER = Block.makeCuboidShape(8.0D, 1.0D, 8.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape NEU_CORNER = Block.makeCuboidShape(8.0D, 7.0D, 0.0D, 16.0D, 15.0D, 8.0D);
    protected static final VoxelShape SEU_CORNER = Block.makeCuboidShape(8.0D, 7.0D, 8.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape[] SLAB_TOP_SHAPES = makeShapes(AABB_SLAB_TOP, NWD_CORNER, NED_CORNER, SWD_CORNER, SED_CORNER);
    protected static final VoxelShape[] SLAB_BOTTOM_SHAPES = makeShapes(AABB_SLAB_BOTTOM, NWU_CORNER, NEU_CORNER, SWU_CORNER, SEU_CORNER);
    private static final int[] metaInt = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};

    public NiftyPathStairs() {
        super(NiftyBlock.PATH);
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
        return (state.get(HALF) == Half.TOP ? SLAB_TOP_SHAPES : SLAB_BOTTOM_SHAPES)[metaInt[this.getShapeMeta(state)]];
    }

    private int getShapeMeta(BlockState state) {
        return state.get(SHAPE).ordinal() * 4 + state.get(FACING).getHorizontalIndex();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
            BlockState blockstate = worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.grass_stairs.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(SHAPE, blockstate.get(SHAPE)).with(HALF, blockstate.get(HALF)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            itemstack.damageItem(1, playerIn, (user) -> user.sendBreakAnimation(hand));
            return true;
        } else {
            return super.onBlockActivated(state, worldIn, pos, playerIn, hand, result);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
