package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.util.BlockModelHelper;
import net.minecraft.block.BlockFence;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class NiftyFence extends BlockFence implements BlockModelHelper {

    public NiftyFence(NiftyBlock block) {
        super(block.material, block.color);

        this.setSoundType(block.sound);
        this.setHardness(block.hardness);
        this.setResistance(block.resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHarvestLevel(block.tool, block.level);
    }

    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
