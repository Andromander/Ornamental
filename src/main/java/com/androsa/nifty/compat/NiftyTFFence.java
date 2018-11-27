package com.androsa.nifty.compat;

import com.androsa.nifty.blocks.NiftyFence;
import com.androsa.nifty.util.BlockModelHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NiftyTFFence extends NiftyFence implements BlockModelHelper {

    public NiftyTFFence(Material material, MapColor color, SoundType sound, float hardness) {
        super(material, color, sound, hardness, 10.0F);
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

    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
