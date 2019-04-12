package com.androsa.nifty.compat;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyStairs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import thebetweenlands.common.block.misc.BlockOctine;

public class NiftyBLStairs extends NiftyStairs {

    public final boolean isOctine;

    public NiftyBLStairs(IBlockState state, NiftyBlock block) {
        super(state, block, false);

        isOctine = block == NiftyBlock.OCTINE;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
        if (isOctine) {
            BlockOctine octine = new BlockOctine();
            octine.neighborChanged(state, world, pos, block, from);
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if (Loader.isModLoaded("thebetweenlands")) {
            items.add(new ItemStack(this));
        }
    }
}
