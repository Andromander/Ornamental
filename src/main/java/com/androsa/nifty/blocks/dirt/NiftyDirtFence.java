package com.androsa.nifty.blocks.dirt;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyFence;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class NiftyDirtFence extends NiftyFence {

    public NiftyDirtFence() {
        super(NiftyBlock.DIRT);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
            BlockState blockstate =  worldIn.getBlockState(pos);
            worldIn.setBlockState(pos, ModBlocks.grass_fence.get().getDefaultState().with(NORTH, blockstate.get(NORTH)).with(SOUTH, blockstate.get(SOUTH)).with(EAST, blockstate.get(EAST)).with(WEST, blockstate.get(WEST)).with(WATERLOGGED, blockstate.get(WATERLOGGED)), 3);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }
            return true;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, hand, result);
        }
    }
}