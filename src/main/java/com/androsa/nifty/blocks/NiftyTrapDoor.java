package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NiftyTrapDoor extends TrapDoorBlock {

    private float fallDamage;

    public NiftyTrapDoor(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));

        this.fallDamage = block.multiplier;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
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
        BlockState state = worldIn.getBlockState(pos);

        if (!state.get(OPEN)) {
            if (material == Material.CLAY || material == Material.LEAVES || material == Material.WOOL || material == Material.EARTH || material == Material.ORGANIC) {
                state = state.cycle(OPEN);
                worldIn.setBlockState(pos, state, 2);
                this.playSound(null, worldIn, pos, state.get(OPEN));
            }
        }
    }

    @Override
    protected void playSound(@Nullable PlayerEntity player, World worldIn, BlockPos pos, boolean state) {
        if (state) {
            int i = this.material == Material.IRON ? 1037 : 1007;
            worldIn.playEvent(player, i, pos, 0);
        } else {
            int j = this.material == Material.IRON ? 1036 : 1013;
            worldIn.playEvent(player, j, pos, 0);
        }

    }
}
