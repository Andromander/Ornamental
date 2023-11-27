package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class OrnamentalItemModelProvider extends ItemModelProvider {

    public static final ResourceLocation TRANSLUCENT = new ResourceLocation("translucent");

    public OrnamentalItemModelProvider(PackOutput output, String modid, ExistingFileHelper helper) {
        super(output, modid, helper);
    }

    public ResourceLocation ornamentLoc(String path) {
        return new ResourceLocation(OrnamentalMod.MODID, path);
    }

    public String blockName(Supplier<? extends Block> block) {
        return BuiltInRegistries.ITEM.getKey(block.get().asItem()).getPath();
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
        existingParent(block, ornamentLoc("block/util/fence_inventory"))
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
        blockItemPole(block, name, Optional.empty());
    }

    public void blockItemPole(Supplier<? extends Block> block, String name, Optional<ResourceLocation> type) {
        ResourceLocation tex = mcLoc("block/" + name);
        blockItemPole(block, tex, tex, tex, type);
    }

    public void blockItemPole(Supplier<? extends Block> block, String end, String side) {
        ResourceLocation sideloc = mcLoc("block/" + side);
        ResourceLocation toploc = mcLoc("block/" + end);
        blockItemPole(block, toploc, toploc, sideloc, Optional.empty());
    }

    public void blockItemPole(Supplier<? extends Block> block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, Optional<ResourceLocation> type) {
        ItemModelBuilder builder = existingParent(block, ornamentLoc("block/util/pole_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
        type.ifPresent(builder::renderType);
    }

    public void blockItemPoleMissing(Supplier<? extends Block> block) {
        ResourceLocation tex = modLoc("block/missingno");
        blockItemPole(block, tex, tex, tex, Optional.empty());
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
        existingParent(block, ornamentLoc("block/util/beam_inventory"))
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
        existingParent(block, ornamentLoc("block/util/wall_inventory"))
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
        existingParent(block, ornamentLoc("block/util/saddle_door_inventory"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);
    }

    public void eggItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
    }

    public ItemModelBuilder existingParent(Supplier<? extends Block> block, ResourceLocation parent) {
        return withExistingParent(blockName(block), parent);
    }
}
