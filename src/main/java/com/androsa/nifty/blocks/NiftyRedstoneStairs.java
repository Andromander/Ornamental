package com.androsa.nifty.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class NiftyRedstoneStairs extends NiftyStairs {

    public NiftyRedstoneStairs(IBlockState state) {
        super(state, SoundType.METAL, 5.0F, 10.0F, ToolType.PICKAXE, 0, false);
    }

    @Override
    @Deprecated
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getWeakPower(IBlockState blockState, IBlockReader blockReader, BlockPos pos, EnumFacing side) {
        return 11;
    }
}
