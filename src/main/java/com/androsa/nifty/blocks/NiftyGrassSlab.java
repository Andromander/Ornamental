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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyGrassSlab extends NiftySlab {

    public NiftyGrassSlab(boolean isDouble) {
        super(isDouble, NiftyBlock.GRASS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemSpade) {
            IBlockState blockstate = worldIn.getBlockState(pos);
            if(isDouble()) {
                worldIn.setBlockState(pos, ModBlocks.double_dirt_slab.getDefaultState().withProperty(VARIANT, blockstate.getValue(VARIANT)).withProperty(HALF, blockstate.getValue(HALF)), 3);
            } else {
                worldIn.setBlockState(pos, ModBlocks.dirt_slab.getDefaultState().withProperty(VARIANT, blockstate.getValue(VARIANT)).withProperty(HALF, blockstate.getValue(HALF)), 3);
            }
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            itemstack.damageItem(1, playerIn);
            return true;
        } else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
