package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class NiftyFenceGate extends FenceGateBlock {

    private ToolType toolType;
    private int toolLevel;
    private float fallDamage;

    public NiftyFenceGate(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound));

        this.toolType = block.tool;
        this.toolLevel = block.level;
        this.fallDamage = block.multiplier;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return toolType;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return toolLevel;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if(material == Material.IRON || material == Material.ROCK) {
            return false;
        } else {
            if (state.get(OPEN)) {
                state = state.with(OPEN, Boolean.FALSE);
                worldIn.setBlockState(pos, state, 10);
            } else {
                Direction enumfacing = player.getHorizontalFacing();
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
