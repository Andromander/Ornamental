package com.androsa.ornamental.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalItemTagProvider extends ItemTagsProvider {

    public OrnamentalItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper, BlockTagsProvider blockTags) {
        super(output, provider, blockTags.contentsGetter(), modid, helper);
    }

    protected void addToTag(TagKey<Item> tag, List<RegistryObject<? extends Block>> list) {
        for (Supplier<? extends Block> block : list) {
            tag(tag).add(block.get().asItem());
        }
    }
}
