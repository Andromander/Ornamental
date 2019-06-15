package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class NiftyRedstoneStairs extends NiftyStairs {

    public NiftyRedstoneStairs() {
        super(NiftyBlock.REDSTONE, false);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return 11;
    }
}
