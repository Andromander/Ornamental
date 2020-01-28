package com.androsa.nifty.items;

import com.androsa.nifty.NiftyBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class NiftyBlockItem extends BlockItem {

    private final Supplier<ForgeConfigSpec.BooleanValue> isOptional;
    private final int burnTime;

    public NiftyBlockItem(Block blockIn, Item.Properties builder, NiftyBlock optional, int time) {
        super(blockIn, builder);

        isOptional = optional.booleanValue;
        burnTime = time;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (isOptional.get().get()) {
            super.fillItemGroup(group, items);
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
