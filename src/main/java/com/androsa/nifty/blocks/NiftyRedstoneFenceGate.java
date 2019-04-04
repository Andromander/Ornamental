package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import java.util.function.Supplier;

public class NiftyRedstoneFenceGate extends NiftyFenceGate {

    public NiftyRedstoneFenceGate(Supplier<IBlockState> state) {
        super(state, NiftyBlock.REDSTONE);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return 3;
    }
}
