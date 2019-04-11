package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class NiftyStairs extends BlockStairs {

    private boolean isBeaconBase;
    private ToolType toolType;
    private float fallDamage;
    private int toolLevel;

    public NiftyStairs(IBlockState state, NiftyBlock block, boolean base) {
        super(state, Block.Properties.from(state.getBlock()).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound));

        this.toolType = block.tool;
        this.toolLevel = block.level;
        this.fallDamage = block.multiplier;
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
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public boolean isBeaconBase(IBlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
        return this.isBeaconBase;
    }
}
