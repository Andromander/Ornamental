package com.androsa.nifty.items;

import com.androsa.nifty.NiftyConfig;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class NiftyBlockItem extends BlockItem {

    private final boolean isOptional;

    public NiftyBlockItem(Block blockIn, Item.Properties builder, boolean optional) {
        super(blockIn, builder);

        isOptional = optional;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (!isOptional) {
            super.fillItemGroup(group, items);
        } else {
            if (NiftyConfig.showMissingnoBlocks.get()) {
                super.fillItemGroup(group, items);
            }
        }
    }
}
