package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NiftyGrassStairs extends NiftyStairs {

    public NiftyGrassStairs(IBlockState state) {
        super(state, NiftyBlock.GRASS, false);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemSpade) {
            IBlockState blockstate = worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.dirt_stairs.getDefaultState().with(FACING, blockstate.get(FACING)).with(SHAPE, blockstate.get(SHAPE)).with(HALF, blockstate.get(HALF)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            itemstack.damageItem(1, playerIn);
            return true;
        } else {
            return super.onBlockActivated(state, worldIn, pos, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
