package com.androsa.ornamental.items;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeConfigSpec;

public class OrnamentBlockItem extends BlockItem {

    private final OrnamentBuilder builder;
    private final int index;

    public OrnamentBlockItem(Block blockIn, Item.Properties builder, OrnamentBuilder ornament, int index) {
        super(blockIn, builder);
        this.builder = ornament;
        this.index = index;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (builder.hasConfig) {
			ForgeConfigSpec.BooleanValue val = builder.booleanValue.get();

			if (val == null) {
				throw new NullPointerException(builder.name + " expected a config value but found null.");
			} else {
				if (val.get()) {
					super.fillItemCategory(group, items);
				}
			}
		} else {
			super.fillItemCategory(group, items);
		}
	}

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return builder.burnTime[index];
    }
}
