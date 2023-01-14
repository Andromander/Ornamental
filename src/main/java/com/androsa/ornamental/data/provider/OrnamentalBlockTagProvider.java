package com.androsa.ornamental.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalBlockTagProvider extends BlockTagsProvider {

    public OrnamentalBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper) {
        super(output, provider, modid, helper);
    }

    protected void addToTag(TagKey<Block> tag, Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(tag).add(block.get());
        }
    }
}