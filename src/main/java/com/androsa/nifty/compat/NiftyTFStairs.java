package com.androsa.nifty.compat;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.blocks.NiftyStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyTFStairs extends NiftyStairs {

    private final float multiplier;
    private final boolean isArctic;

    public NiftyTFStairs(IBlockState state, NiftyBlock block) {
        super(state, block, true);

        multiplier = block == NiftyBlock.STEELEAF ? 0.75F : block == NiftyBlock.ARCTIC ? 0.1F : 1.0F;
        isArctic = block == NiftyBlock.ARCTIC;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, multiplier);
    }

    @Override
    @Deprecated
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        if (isArctic)
            return player.getHeldItemMainhand().getItem() instanceof ItemShears ? 0.2F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
        else
            return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        if(Loader.isModLoaded("twilightforest")) {
            list.add(new ItemStack(this, 1, 0));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
