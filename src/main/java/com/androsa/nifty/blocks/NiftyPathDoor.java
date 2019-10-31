package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NiftyPathDoor extends NiftyDoor {

    protected static final VoxelShape SOUTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape WEST_AABB_TOP = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape EAST_AABB_TOP = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 15.0D, 16.0D);

    public NiftyPathDoor() {
        super(NiftyBlock.PATH);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(FACING);
        boolean flag = !state.get(OPEN);
        boolean flag1 = state.get(HINGE) == DoorHingeSide.RIGHT;

        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            switch(direction) {
                case EAST:
                default:
                    return flag ? EAST_AABB_TOP : (flag1 ? NORTH_AABB_TOP : SOUTH_AABB_TOP);
                case SOUTH:
                    return flag ? SOUTH_AABB_TOP : (flag1 ? EAST_AABB_TOP : WEST_AABB_TOP);
                case WEST:
                    return flag ? WEST_AABB_TOP : (flag1 ? SOUTH_AABB_TOP : NORTH_AABB_TOP);
                case NORTH:
                    return flag ? NORTH_AABB_TOP : (flag1 ? WEST_AABB_TOP : EAST_AABB_TOP);
            }
        } else {
            switch(direction) {
                case EAST:
                default:
                    return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
                case SOUTH:
                    return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
                case WEST:
                    return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
                case NORTH:
                    return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
            }
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
            BlockState blockstate = worldIn.getBlockState(pos);

            if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                isReplacing = true;
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                worldIn.setBlockState(pos.up(), ModBlocks.grass_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                worldIn.setBlockState(pos, ModBlocks.grass_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                isReplacing = false;
            } else {
                isReplacing = true;
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                worldIn.setBlockState(pos.down(), ModBlocks.grass_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                worldIn.setBlockState(pos, ModBlocks.grass_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                isReplacing = false;
            }

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
