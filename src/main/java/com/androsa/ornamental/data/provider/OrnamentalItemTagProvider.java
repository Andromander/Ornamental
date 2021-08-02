package com.androsa.ornamental.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.function.Supplier;

public abstract class OrnamentalItemTagProvider extends ItemTagsProvider {

    public OrnamentalItemTagProvider(DataGenerator generator, String modid, ExistingFileHelper helper, BlockTagsProvider blockTags) {
        super(generator, blockTags, modid, helper);
    }

    protected void piglinLoveTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(ItemTags.PIGLIN_LOVED).add(block.get().asItem());
        }
    }
}
