package com.androsa.nifty.blocks.redstone;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyTrapDoor;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class NiftyRedstoneTrapDoor extends NiftyTrapDoor {

    public NiftyRedstoneTrapDoor() {
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
        return 5;
    }
}
