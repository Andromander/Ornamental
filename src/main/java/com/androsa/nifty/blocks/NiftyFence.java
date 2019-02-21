package com.androsa.nifty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.ToolType;

public class NiftyFence extends BlockFence {

    private ToolType toolType;
    private int toolLevel;

    public NiftyFence(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level) {
        super(Block.Properties.create(material, color).hardnessAndResistance(hardness, resistance).sound(sound));

        this.toolType = tool;
        this.toolLevel = level;
    }

    @Override
    public ToolType getHarvestTool(IBlockState state) {
        return toolType;
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return toolLevel;
    }
}
