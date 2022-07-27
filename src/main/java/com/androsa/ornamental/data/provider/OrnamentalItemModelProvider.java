package com.androsa.ornamental.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public abstract class OrnamentalItemModelProvider extends ItemModelProvider {

    public OrnamentalItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    public String blockName(Supplier<? extends Block> block) {
        return ForgeRegistries.ITEMS.getKey(block.get().asItem()).getPath();
    }

    public void blockItem(Supplier<? extends Block> block) {
        existingParent(block, modLoc("block/" + blockName(block)));
    }

    public void blockItemModel(Supplier<? extends Block> block) {
        existingParent(block, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + blockName(block)));
    }

    public void blockItemModel(Supplier<? extends Block> block, String name) {
        existingParent(block, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public void blockItemFence(Supplier<? extends Block> block, String name) {
        ResourceLocation loc = mcLoc("block/" + name);
        blockItemFence(block, loc, loc, loc);
    }

    public void blockItemFence(Supplier<? extends Block> block, String side, String top) {
        ResourceLocation sideloc = mcLoc("block/" + side);
        ResourceLocation toploc = mcLoc("block/" + top);
        blockItemFence(block, sideloc, toploc, toploc);
    }

    public void blockItemFence(Supplier<? extends Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        existingParent(block, modLoc("block/util/fence_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public void blockItemFenceMissing(Supplier<? extends Block> block) {
        ResourceLocation tex = modLoc("block/missingno");
        blockItemFence(block, tex, tex, tex);
    }

    public void blockItemTrapdoor(Supplier<? extends Block> block) {
        existingParent(block, modLoc("block/" + blockName(block) + "_bottom"));
    }

    public void blockItemPole(Supplier<? extends Block> block, String name) {
        ResourceLocation tex = mcLoc("block/" + name);
        blockItemPole(block, tex, tex, tex);
    }

    public void blockItemPole(Supplier<? extends Block> block, String end, String side) {
        ResourceLocation sideloc = mcLoc("block/" + side);
        ResourceLocation toploc = mcLoc("block/" + end);
        blockItemPole(block, toploc, toploc, sideloc);
    }

    public void blockItemPole(Supplier<? extends Block> block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side) {
        existingParent(block, modLoc("block/util/pole_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public void blockItemPoleMissing(Supplier<? extends Block> block) {
        ResourceLocation tex = modLoc("block/missingno");
        blockItemPole(block, tex, tex, tex);
    }

    public void blockItemBeam(Supplier<? extends Block> block, String name) {
        ResourceLocation tex = mcLoc("block/" + name);
        blockItemBeam(block, tex, tex, tex);
    }

    public void blockItemBeam(Supplier<? extends Block> block, String end, String side) {
        ResourceLocation sideloc = mcLoc("block/" + side);
        ResourceLocation toploc = mcLoc("block/" + end);
        blockItemBeam(block, toploc, toploc, sideloc);
    }

    public void blockItemBeam(Supplier<? extends Block> block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side) {
        existingParent(block, modLoc("block/util/beam_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public void blockItemBeamMissing(Supplier<? extends Block> block) {
        ResourceLocation tex = modLoc("block/missingno");
        blockItemBeam(block, tex, tex, tex);
    }

    public void blockItemWall(Supplier<? extends Block> block, String name) {
        ResourceLocation tex = mcLoc("block/" + name);
        blockItemWall(block, tex, tex, tex);
    }

    public void blockItemWall(Supplier<? extends Block> block, String side, String top) {
        ResourceLocation sideloc = mcLoc("block/" + side);
        ResourceLocation toploc = mcLoc("block/" + top);
        blockItemWall(block, sideloc, toploc, toploc);
    }

    public void blockItemWall(Supplier<? extends Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        existingParent(block, modLoc("block/util/wall_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public void blockItemWallMissing(Supplier<? extends Block> block) {
        ResourceLocation tex = modLoc("block/missingno");
        blockItemWall(block, tex, tex, tex);
    }

    public void blockItemSaddleDoor(Supplier<? extends Block> block, String name) {
        ResourceLocation texture = modLoc("block/" + name + "_trapdoor");
        blockItemSaddleDoor(block, texture, texture, texture);
    }

    public void blockItemSaddleDoorV(Supplier<? extends Block> block, String name) {
        ResourceLocation texture = mcLoc("block/" + name);
        blockItemSaddleDoor(block, texture, texture, texture);
    }

    public void blockItemSaddleDoorM(Supplier<? extends Block> block) {
        ResourceLocation texture = modLoc("block/missingno");
        blockItemSaddleDoor(block, texture, texture, texture);
    }

    public void blockItemSaddleDoor(Supplier<? extends Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        existingParent(block, modLoc("block/util/saddle_door_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public ItemModelBuilder existingParent(Supplier<? extends Block> block, ResourceLocation parent) {
        return withExistingParent(blockName(block), parent);
    }
}
