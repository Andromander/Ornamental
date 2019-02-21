package com.androsa.nifty.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import static net.minecraftforge.common.ToolType.PICKAXE;

public class NiftyRedstoneFence extends NiftyFence {

    public NiftyRedstoneFence() {
        super(Material.IRON, MaterialColor.TNT, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 0);
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
