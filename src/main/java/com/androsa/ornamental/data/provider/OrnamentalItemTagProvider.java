package com.androsa.ornamental.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalItemTagProvider extends ItemTagsProvider {

    public OrnamentalItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper, BlockTagsProvider blockTags) {
        super(output, provider, blockTags, modid, helper);
    }

    protected void piglinLoveTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(ItemTags.PIGLIN_LOVED).add(block.get().asItem());
        }
    }
}
