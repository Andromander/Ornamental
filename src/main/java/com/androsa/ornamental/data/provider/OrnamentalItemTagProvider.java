package com.androsa.ornamental.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;

import java.util.Set;
import java.util.function.Supplier;

public abstract class OrnamentalItemTagProvider extends ItemTagsProvider {

    public OrnamentalItemTagProvider(DataGenerator generator, BlockTagsProvider blockTags) {
        super(generator, blockTags);
    }

    protected void piglinLoveTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            getOrCreateBuilder(ItemTags.field_232903_N_).add(block.get().asItem()); //piglin_loved
        }
    }
}
