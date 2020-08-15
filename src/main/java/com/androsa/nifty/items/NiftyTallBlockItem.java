package com.androsa.nifty.items;

import com.androsa.nifty.builder.NiftyBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeConfigSpec;

public class NiftyTallBlockItem extends TallBlockItem {

    private final NiftyBuilder builder;
    private final int index;

    public NiftyTallBlockItem(Block blockIn, Item.Properties builder, NiftyBuilder nifty, int index) {
        super(blockIn, builder);
        this.builder = nifty;
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
        }
    }
}
