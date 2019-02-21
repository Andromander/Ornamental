package com.androsa.nifty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class NiftyStairs extends BlockStairs {

    private boolean isBeaconBase;
    private ToolType toolType;
    private int toolLevel;

    public NiftyStairs(IBlockState state, SoundType sound, float hardness, float resistance, ToolType tool, int level, boolean base) {
        super(state, Block.Properties.from(state.getBlock()).hardnessAndResistance(hardness, resistance).sound(sound));

        this.toolType = tool;
        this.toolLevel = level;
        this.isBeaconBase = base;
    }

    @Override
    public ToolType getHarvestTool(IBlockState state) {
        return toolType;
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return toolLevel;
    }

    @Override
    public boolean isBeaconBase(IBlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
        return this.isBeaconBase;
    }
}
