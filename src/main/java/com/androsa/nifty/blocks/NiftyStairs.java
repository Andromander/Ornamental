package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NiftyStairs extends StairsBlock {

    private float fallDamage;

    public NiftyStairs(NiftyBlock block) {
        super(() -> new Block(Properties.create(block.material, block.color)).getDefaultState(), Block.Properties.create(block.material, block.color).hardnessAndResistance(block.hardness, block.resistance).sound(block.sound).harvestTool(block.tool).harvestLevel(block.level));

        this.fallDamage = block.multiplier;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }
}
