package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NiftyDirtSlab extends NiftySlab {

    public NiftyDirtSlab() {
        super(NiftyBlock.DIRT);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
            IBlockState blockstate =  worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.grass_slab.getDefaultState().with(TYPE, blockstate.get(TYPE)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }
            return true;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, hand, side, hitX, hitY, hitZ);
        }
    }
}
