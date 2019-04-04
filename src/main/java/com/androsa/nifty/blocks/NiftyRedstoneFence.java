package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class NiftyRedstoneFence extends NiftyFence {

    public NiftyRedstoneFence() {
        super(NiftyBlock.REDSTONE);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockReader blockReader, BlockPos pos, EnumFacing side) {
        return 5;
    }
}
