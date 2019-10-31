package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class NiftyDoor extends DoorBlock {

    private float fallDamage;
    private boolean noRedstone;
    protected boolean isReplacing = false;

    public NiftyDoor(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));

        this.fallDamage = block.multiplier;
        this.noRedstone = block.canOpen;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!noRedstone) {
            return false;
        } else {
            state = state.cycle(OPEN);
            worldIn.setBlockState(pos, state, 10);
            worldIn.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return true;
        }
    }

    private int getCloseSound() {
        return this.material == Material.IRON || this.material == Material.ROCK ? 1011 : 1012;
    }

    private int getOpenSound() {
        return this.material == Material.IRON || this.material == Material.ROCK ? 1005 : 1006;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!isReplacing) {
            super.onBlockHarvested(worldIn, pos, state, player);
        }
    }
}
