package com.androsa.nifty.blocks.redstone;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftySlab;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class NiftyRedstoneSlab extends NiftySlab {

    public NiftyRedstoneSlab() {
        super(NiftyBlock.REDSTONE);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(BlockState blockState, IBlockReader blockReader, BlockPos pos, Direction side) {
        return blockState.get(TYPE) == SlabType.DOUBLE ? 15 : 7;
    }
}
