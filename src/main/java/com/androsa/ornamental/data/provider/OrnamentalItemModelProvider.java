package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public abstract class OrnamentalItemModelProvider {

    protected final ItemModelGenerators itemModels;

    public OrnamentalItemModelProvider(ItemModelGenerators generators) {
        this.itemModels = generators;
    }

    public abstract void runItemGen();

    public void eggItem(Supplier<Item> item, int primary, int secondary) {
        Identifier resourcelocation = itemModels.generateLayeredItem(
                item.get(), Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "item/spawn_egg"), Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "item/spawn_egg_overlay")
        );
        itemModels.itemModelOutput.accept(item.get(), ItemModelUtils.tintedModel(resourcelocation, ItemModelUtils.constantTint(primary), ItemModelUtils.constantTint(secondary)));
    }
}
