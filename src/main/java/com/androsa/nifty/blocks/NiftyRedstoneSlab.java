package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class NiftyRedstoneSlab extends NiftySlab {

    private final boolean isDouble;

    public NiftyRedstoneSlab(boolean isDouble) {
        super(isDouble, NiftyBlock.REDSTONE);

        this.isDouble = isDouble;
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if(!isDouble) {
            return 7;
        } else {
            return 15;
        }
    }
}
