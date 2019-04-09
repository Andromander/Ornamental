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

import java.util.function.Supplier;

public class NiftyDirtFenceGate extends NiftyFenceGate {

    public NiftyDirtFenceGate(Supplier<IBlockState> state) {
        super(state, NiftyBlock.DIRT);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.DYE) {
            EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());
            if (enumdyecolor.getMetadata() == itemstack.getItem().getMetadata(15)) {
                IBlockState blockstate =  worldIn.getBlockState(pos);
                worldIn.setBlockState(pos, ModBlocks.grass_fence_gate.getDefaultState().withProperty(FACING, blockstate.getValue(FACING)).withProperty(OPEN, blockstate.getValue(OPEN)).withProperty(POWERED, blockstate.getValue(POWERED)).withProperty(IN_WALL, blockstate.getValue(IN_WALL)), 3);
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
