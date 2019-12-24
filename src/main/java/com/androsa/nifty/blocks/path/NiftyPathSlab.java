package com.androsa.nifty.blocks.path;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftySlab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NiftyPathSlab extends NiftySlab {

    protected static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape FULL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public NiftyPathSlab() {
        super(NiftyBlock.PATH);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        SlabType slabtype = state.get(TYPE);
        switch(slabtype) {
            case DOUBLE:
                return FULL_SHAPE;
            case TOP:
                return TOP_SHAPE;
            default:
                return BOTTOM_SHAPE;
        }
    }

    @Override
    @Deprecated
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof HoeItem) {
            BlockState blockstate = worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.grass_slab.get().getDefaultState().with(TYPE, blockstate.get(TYPE)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
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
