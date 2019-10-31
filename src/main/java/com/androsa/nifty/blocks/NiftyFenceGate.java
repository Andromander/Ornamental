package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class NiftyFenceGate extends FenceGateBlock {

    private float fallDamage;
    private boolean noRedstone;

    public NiftyFenceGate(NiftyBlock block) {
        super(Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));

        this.fallDamage = block.multiplier;
        this.noRedstone = block.canOpen;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if(!noRedstone) {
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
