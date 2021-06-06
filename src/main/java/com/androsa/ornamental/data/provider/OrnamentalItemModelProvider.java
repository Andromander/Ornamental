package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

import java.util.function.Supplier;

public abstract class OrnamentalItemModelProvider extends ItemModelProvider {

    public OrnamentalItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    public ResourceLocation ornamentLoc(String path) {
        return new ResourceLocation(OrnamentalMod.MODID, path);
    }

    public String blockName(Supplier<? extends Block> block) {
        return block.get().asItem().getRegistryName().getPath();
    }

    public void blockItem(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), modLoc("block/" + blockName(block)));
    }

    public void blockItemModel(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + blockName(block)));
    }

    public void blockItemModel(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public void blockItemFence(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", mcLoc("block/" + name));
    }

    public void blockItemFenceColumn(Supplier<? extends Block> block, String side, String top) {
        withExistingParent(blockName(block), ornamentLoc("block/util/fence_inventory_column"))
                .texture("end", mcLoc("block/" + top))
                .texture("side", mcLoc("block/" + side));
    }

    public void blockItemFenceMissing(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/missingno"));
    }

    public void blockItemTrapdoor(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), modLoc("block/" + blockName(block) + "_bottom"));
    }

    public void blockItemPole(Supplier<? extends Block> block, String name) {
        blockItemPole(block, name, name);
    }

    public void blockItemPole(Supplier<? extends Block> block, String end, String side) {
        withExistingParent(blockName(block), ornamentLoc("block/util/pole_inventory"))
                .texture("end", mcLoc("block/" + end))
                .texture("side", mcLoc("block/" + side));
    }

    public void blockItemPoleMissing(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), ornamentLoc("block/util/pole_inventory"))
                .texture("end", modLoc("block/missingno"))
                .texture("side", modLoc("block/missingno"));
    }

    public void blockItemBeam(Supplier<? extends Block> block, String name) {
        blockItemBeam(block, name, name);
    }

    public void blockItemBeam(Supplier<? extends Block> block, String end, String side) {
        withExistingParent(blockName(block), ornamentLoc("block/util/beam_inventory"))
                .texture("end", mcLoc("block/" + end))
                .texture("side", mcLoc("block/" + side));
    }

    public void blockItemBeamMissing(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), ornamentLoc("block/util/beam_inventory"))
                .texture("end", modLoc("block/missingno"))
                .texture("side", modLoc("block/missingno"));
    }

    public void blockItemWall(Supplier<? extends Block> block, String name) {
        withExistingParent(blockName(block), mcLoc("block/wall_inventory"))
                .texture("wall", mcLoc("block/" + name));
    }

    public void blockItemWallColumn(Supplier<? extends Block> block, String side, String top) {
        withExistingParent(blockName(block), ornamentLoc("block/util/wall_inventory_column"))
                .texture("end", mcLoc("block/" + top))
                .texture("side", mcLoc("block/" + side));
    }

    public void blockItemWallMissing(Supplier<? extends Block> block) {
        withExistingParent(blockName(block), mcLoc("block/wall_inventory"))
                .texture("wall", modLoc("block/missingno"));
    }
}
