package com.androsa.ornamental.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalBlockTagProvider extends BlockTagsProvider {

    public OrnamentalBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper) {
        super(output, provider, modid, helper);
    }

    protected void addToTag(TagKey<Block> tag, List<RegistryObject<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(tag).add(block.get());
        }
    }
}