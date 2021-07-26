package com.androsa.ornamental.items;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;

public class OrnamentTallBlockItem extends DoubleHighBlockItem {

    private final OrnamentBuilder builder;
    private final int index;

    public OrnamentTallBlockItem(Block blockIn, Item.Properties builder, OrnamentBuilder ornament, int index) {
        super(blockIn, builder);
        this.builder = ornament;
        this.index = index;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> type) {
        return builder.burnTime[index];
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
}
