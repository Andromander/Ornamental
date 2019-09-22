package com.androsa.nifty.items;

import com.androsa.nifty.NiftyConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.NonNullList;

public class NiftyTallBlockItem extends TallBlockItem {

    public NiftyTallBlockItem(Block blockIn, Item.Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (NiftyConfig.showMissingnoBlocks.get()) {
            super.fillItemGroup(group, items);
        }
    }
}
