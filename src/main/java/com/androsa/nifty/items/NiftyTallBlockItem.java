package com.androsa.nifty.items;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class NiftyTallBlockItem extends TallBlockItem {

    private final int burnTime;
    private final Supplier<ForgeConfigSpec.BooleanValue> isOptional;

    public NiftyTallBlockItem(Block blockIn, Item.Properties builder, int time, NiftyBlock optional) {
        super(blockIn, builder);

        isOptional = optional.booleanValue;
        burnTime = time;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (isOptional.get().get()) {
            super.fillItemGroup(group, items);
        }
    }
}
