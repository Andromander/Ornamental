package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.ToolType;

public class NiftySlab extends BlockSlab {

    private ToolType toolType;
    private int toolLevel;

    public NiftySlab(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound));

        this.toolType = block.tool;
        this.toolLevel = block.level;
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
