package com.androsa.ornamental.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.function.Supplier;

public abstract class OrnamentalBlockTagProvider extends BlockTagsProvider {

    public OrnamentalBlockTagProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    protected void addToTag(TagKey<Block> tag, Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(tag).add(block.get());
        }
    }
}