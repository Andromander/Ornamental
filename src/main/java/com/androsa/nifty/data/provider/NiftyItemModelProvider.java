package com.androsa.nifty.data.provider;

import com.androsa.nifty.NiftyMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;

import java.util.function.Supplier;

public abstract class NiftyItemModelProvider extends ItemModelProvider {

    public NiftyItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, NiftyMod.MODID, helper);
    }

    public String blockName(Supplier<? extends Block> block) {
        return block.get().asItem().getRegistryName().getPath();
    }

    public ItemModelBuilder blockItem(Supplier<? extends Block> block) {
        return withExistingParent(blockName(block), modLoc("block/" + blockName(block)));
    }

    public ItemModelBuilder blockItemModel(Supplier<? extends Block> block) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + blockName(block)));
    }

    public ItemModelBuilder blockItemModel(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public ItemModelBuilder blockItemFence(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", mcLoc("block/" + name));
    }

    public ItemModelBuilder blockItemFenceColumn(Supplier<? extends Block> block, String side, String top) {
        return withExistingParent(blockName(block), modLoc("block/util/fence_inventory_column"))
                .texture("end", mcLoc("block/" + top))
                .texture("side", mcLoc("block/" + side));
    }

    public ItemModelBuilder blockItemFenceMissing(Supplier<? extends Block> block) {
        return withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/missingno"));
    }

    public ItemModelBuilder blockItemTrapdoor(Supplier<? extends Block> block) {
        return withExistingParent(blockName(block), modLoc("block/" + blockName(block) + "_bottom"));
    }
}
