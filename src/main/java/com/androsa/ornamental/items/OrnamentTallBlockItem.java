package com.androsa.ornamental.items;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeConfigSpec;

public class OrnamentTallBlockItem extends TallBlockItem {

    private final OrnamentBuilder builder;
    private final int index;

    public OrnamentTallBlockItem(Block blockIn, Item.Properties builder, OrnamentBuilder ornament, int index) {
        super(blockIn, builder);
        this.builder = ornament;
        this.index = index;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return builder.burnTime[index];
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (builder.hasConfig) {
            ForgeConfigSpec.BooleanValue val = builder.booleanValue.get();

            if (val == null) {
                throw new NullPointerException(builder.name + " expected a config value but found null.");
            } else {
                if (val.get()) {
                    super.fillItemGroup(group, items);
                }
            }
        } else {
            super.fillItemGroup(group, items);
        }
    }
}
