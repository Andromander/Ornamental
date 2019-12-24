package com.androsa.nifty.blocks.path;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyFenceGate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
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

public class NiftyPathFenceGate extends NiftyFenceGate {

    protected static final VoxelShape HITBOX_ZAXIS = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 15.0D, 10.0D);
    protected static final VoxelShape HITBOX_XAXIS = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 15.0D, 16.0D);
    protected static final VoxelShape HITBOX_ZAXIS_INWALL = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 12.0D, 10.0D);
    protected static final VoxelShape HITBOX_XAXIS_INWALL = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 12.0D, 16.0D);
    protected static final VoxelShape COLLISION_BOX_ZAXIS = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 23.0D, 10.0D);
    protected static final VoxelShape COLLISION_BOX_XAXIS = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 23.0D, 16.0D);
    protected static final VoxelShape RENDER_BOX_Z = VoxelShapes.or(Block.makeCuboidShape(0.0D, 4.0D, 7.0D, 2.0D, 15.0D, 9.0D), Block.makeCuboidShape(14.0D, 4.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    protected static final VoxelShape RENDER_BOX_X = VoxelShapes.or(Block.makeCuboidShape(7.0D, 4.0D, 0.0D, 9.0D, 15.0D, 2.0D), Block.makeCuboidShape(7.0D, 4.0D, 14.0D, 9.0D, 15.0D, 16.0D));
    protected static final VoxelShape RENDER_BOX_INWALL_Z = VoxelShapes.or(Block.makeCuboidShape(0.0D, 1.0D, 7.0D, 2.0D, 12.0D, 9.0D), Block.makeCuboidShape(14.0D, 1.0D, 7.0D, 16.0D, 12.0D, 9.0D));
    protected static final VoxelShape RENDER_BOX_INWALL_X = VoxelShapes.or(Block.makeCuboidShape(7.0D, 1.0D, 0.0D, 9.0D, 12.0D, 2.0D), Block.makeCuboidShape(7.0D, 1.0D, 14.0D, 9.0D, 12.0D, 16.0D));

    public NiftyPathFenceGate() {
        super(NiftyBlock.PATH);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(IN_WALL)) {
            return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? HITBOX_XAXIS_INWALL : HITBOX_ZAXIS_INWALL;
        } else {
            return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? HITBOX_XAXIS : HITBOX_ZAXIS;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(OPEN)) {
            return VoxelShapes.empty();
        } else {
            return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.Z ? COLLISION_BOX_ZAXIS : COLLISION_BOX_XAXIS;
        }
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        if (state.get(IN_WALL)) {
            return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? RENDER_BOX_INWALL_X : RENDER_BOX_INWALL_Z;
        } else {
            return state.get(HORIZONTAL_FACING).getAxis() == Direction.Axis.X ? RENDER_BOX_X : RENDER_BOX_Z;
        }
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
            BlockState blockstate = worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.grass_fence_gate.get().getDefaultState().with(HORIZONTAL_FACING, blockstate.get(HORIZONTAL_FACING)).with(OPEN, blockstate.get(OPEN)).with(POWERED, blockstate.get(POWERED)).with(IN_WALL, blockstate.get(IN_WALL)), 3);
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
