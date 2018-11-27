package com.androsa.nifty.compat;

import com.androsa.nifty.blocks.NiftySlab;
import com.androsa.nifty.util.BlockModelHelper;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyTFSlab extends NiftySlab implements BlockModelHelper {

    public NiftyTFSlab(boolean isDouble, Material material, MapColor color, SoundType sound, float hardness) {
        super(isDouble, material, color, sound, hardness, 10.0F);
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if (Loader.isModLoaded("twilightforest")) {
            items.add(new ItemStack(this));
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
        if (this.isDouble())
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).ignore(HALF).build());
        else {
            ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(VARIANT).build());
            ModelUtil.registerToState(this, 0, getDefaultState());
        }
    }
}
