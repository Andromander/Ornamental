package com.androsa.ornamental.data.provider;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public abstract class OrnamentalItemModelProvider {

    protected final ItemModelGenerators itemModels;

    public OrnamentalItemModelProvider(ItemModelGenerators generators) {
        this.itemModels = generators;
    }

    public abstract void runItemGen();

    public void eggItem(Supplier<Item> item, int primary, int secondary) {
        this.itemModels.generateSpawnEgg(item.get(), primary, secondary);
    }
}
