package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class NiftyDirtDoor extends NiftyDoor {

    public NiftyDirtDoor() {
        super(NiftyBlock.DIRT);
    }

    @Override
    @Deprecated
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == Items.BONE_MEAL) {
            BlockState blockstate =  worldIn.getBlockState(pos);

            if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                isReplacing = true;
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                worldIn.setBlockState(pos.up(), ModBlocks.grass_door.getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                worldIn.setBlockState(pos, ModBlocks.grass_door.getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                isReplacing = false;
            } else {
                isReplacing = true;
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                worldIn.setBlockState(pos.down(), ModBlocks.grass_door.getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                worldIn.setBlockState(pos, ModBlocks.grass_door.getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                isReplacing = false;
            }

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
