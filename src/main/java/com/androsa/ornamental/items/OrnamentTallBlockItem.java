package com.androsa.ornamental.items;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

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
        if (index >= builder.burnTime.length || index < 0) {
            OrnamentalMod.LOGGER.debug("Index is out of Burn Time array! Index: {}, Array Length: {}, Builder: {}", index, builder.burnTime.length, builder.name);
            return 0;
        }
        return builder.burnTime[index];
    }
}
