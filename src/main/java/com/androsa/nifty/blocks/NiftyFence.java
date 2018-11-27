package com.androsa.nifty.blocks;

import com.androsa.nifty.util.BlockModelHelper;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class NiftyFence extends BlockFence implements BlockModelHelper {

    public NiftyFence(Material material, MapColor color, SoundType sound, float hardness, float resistance) {
        super(material, color);

        this.setSoundType(sound);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public NiftyFence(Material material, MapColor color, SoundType sound, float hardness, float resistance, int harvest) {
        super(material, color);

        this.setSoundType(sound);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHarvestLevel("pickaxe", harvest);
    }

    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
