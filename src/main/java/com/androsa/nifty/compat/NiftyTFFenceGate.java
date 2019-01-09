package com.androsa.nifty.compat;

import com.androsa.nifty.blocks.NiftyFenceGate;
import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Supplier;

public class NiftyTFFenceGate extends NiftyFenceGate implements BlockModelHelper {

    public NiftyTFFenceGate(Supplier<IBlockState> state, SoundType sound, float hardness) {
        super(state, sound, hardness, 10.0F);
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(POWERED).build());
        ModelUtil.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }
}
