package com.androsa.nifty.blocks;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NiftyGrassDoor extends NiftyDoor {

    public NiftyGrassDoor() {
        super(NiftyBlock.GRASS);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult result) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty()) {
            if (itemstack.getItem() instanceof HoeItem) {
                BlockState blockstate = worldIn.getBlockState(pos);

                if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                    isReplacing = true;
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    worldIn.setBlockState(pos.up(), ModBlocks.dirt_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                    worldIn.setBlockState(pos, ModBlocks.dirt_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                    isReplacing = false;
                } else {
                    isReplacing = true;
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    worldIn.setBlockState(pos.down(), ModBlocks.dirt_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                    worldIn.setBlockState(pos, ModBlocks.dirt_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                    isReplacing = false;
                }

                worldIn.playSound(null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.damageItem(1, playerIn, (user) -> user.sendBreakAnimation(hand));
                return true;
            } else if (itemstack.getItem() instanceof ShovelItem) {
                BlockState blockstate = worldIn.getBlockState(pos);

                if (blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                    isReplacing = true;
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    worldIn.setBlockState(pos.up(), ModBlocks.path_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                    worldIn.setBlockState(pos, ModBlocks.path_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                    isReplacing = false;
                } else {
                    isReplacing = true;
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    worldIn.setBlockState(pos.down(), ModBlocks.path_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.LOWER), 3);
                    worldIn.setBlockState(pos, ModBlocks.path_door.get().getDefaultState().with(FACING, blockstate.get(FACING)).with(OPEN, blockstate.get(OPEN)).with(HINGE, blockstate.get(HINGE)).with(POWERED, blockstate.get(POWERED)).with(HALF, DoubleBlockHalf.UPPER), 3);
                    isReplacing = false;
                }

                worldIn.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemstack.damageItem(1, playerIn, (user) -> user.sendBreakAnimation(hand));
                return true;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, playerIn, hand, result);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
