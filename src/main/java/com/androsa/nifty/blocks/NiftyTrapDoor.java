package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Fluids;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class NiftyTrapDoor extends BlockTrapDoor {

    private ToolType toolType;
    private int toolLevel;
    private float fallDamage;

    public NiftyTrapDoor(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound));

        this.toolType = block.tool;
        this.toolLevel = block.level;
        this.fallDamage = block.multiplier;
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
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (this.material == Material.IRON || this.material == Material.ROCK) {
            return false;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlockState(pos, state, 2);
            if (state.get(WATERLOGGED)) {
                worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            }

            this.playSound(player, worldIn, pos, state.get(OPEN));
            return true;
        }
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        IBlockState state = worldIn.getBlockState(pos);

        if (!state.get(OPEN)) {
            if (material == Material.CLAY || material == Material.LEAVES || material == Material.CLOTH || material == Material.GROUND || material == Material.GRASS) {
                state = state.cycle(OPEN);
                worldIn.setBlockState(pos, state, 2);
                this.playSound(null, worldIn, pos, state.get(OPEN));
            }
        }
    }

    @Override
    protected void playSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos, boolean state) {
        if (state) {
            int i = this.material == Material.IRON ? 1037 : 1007;
            worldIn.playEvent(player, i, pos, 0);
        } else {
            int j = this.material == Material.IRON ? 1036 : 1013;
            worldIn.playEvent(player, j, pos, 0);
        }

    }
}
