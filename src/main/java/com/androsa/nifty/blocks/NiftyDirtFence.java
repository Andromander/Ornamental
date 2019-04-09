package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NiftyDirtFence extends NiftyFence {

    public NiftyDirtFence() {
        super(NiftyBlock.DIRT);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.DYE) {
            EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());
            if (enumdyecolor.getMetadata() == itemstack.getItem().getMetadata(15)) {
                IBlockState blockstate =  worldIn.getBlockState(pos);
                worldIn.setBlockState(pos, ModBlocks.grass_fence.getDefaultState().withProperty(NORTH, blockstate.getValue(NORTH)).withProperty(SOUTH, blockstate.getValue(SOUTH)).withProperty(EAST, blockstate.getValue(EAST)).withProperty(WEST, blockstate.getValue(WEST)), 3);
                worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!playerIn.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
                return true;
            } else {
                return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        } else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }
}
