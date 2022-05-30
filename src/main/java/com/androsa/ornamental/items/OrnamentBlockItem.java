package com.androsa.ornamental.items;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
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
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
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
    public int getBurnTime(ItemStack itemStack, RecipeType<?> type) {
        if (index > builder.burnTime.length || index < 0) {
            // The index is outside the burnTime array bounds. This is because the number is negative or greater than the size of the array. This is a developer error.
            return 0;
        }
        return builder.burnTime[index];
    }
}
