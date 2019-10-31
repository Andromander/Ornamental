package com.androsa.nifty.items;

import com.androsa.nifty.NiftyConfig;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.NonNullList;

public class NiftyTallBlockItem extends TallBlockItem {

    private final int burnTime;
    private final boolean isOptional;

    public NiftyTallBlockItem(Block blockIn, Item.Properties builder, int time, boolean optional) {
        super(blockIn, builder);

        burnTime = time;
        isOptional = optional;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
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
