package com.androsa.nifty.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class NiftyFenceGate extends BlockFenceGate {

    private ToolType toolType;
    private int toolLevel;

    public NiftyFenceGate(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level) {
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

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(material == Material.IRON || material == Material.ROCK) {
            return false;
        } else {
            if (state.get(OPEN)) {
                state = state.with(OPEN, Boolean.FALSE);
                worldIn.setBlockState(pos, state, 10);
            } else {
                EnumFacing enumfacing = player.getHorizontalFacing();
                if (state.get(HORIZONTAL_FACING) == enumfacing.getOpposite()) {
                    state = state.with(HORIZONTAL_FACING, enumfacing);
                }

                state = state.with(OPEN, Boolean.TRUE);
                worldIn.setBlockState(pos, state, 10);
            }

            worldIn.playEvent(player, state.get(OPEN) ? 1008 : 1014, pos, 0);
            return true;
        }

    }
}
