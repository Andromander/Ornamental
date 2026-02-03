package com.androsa.ornamental.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalItemTagProvider extends ItemTagsProvider {

    public OrnamentalItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, BlockTagsProvider blockTags) {
        super(output, provider, modid);
    }

    protected void addToTag(TagKey<Item> tag, List<? extends Supplier<? extends Block>> list) {
        for (Supplier<? extends Block> block : list) {
            tag(tag).add(block.get().asItem());
        }
    }

    public static class BlockToItemTag implements TagAppender<Block, Block> {

        private final TagAppender<Item, Item> itemAppender;

        public BlockToItemTag(TagAppender<Item, Item> itemAppender) {
            this.itemAppender = itemAppender;
        }

        @Override
        public TagAppender<Block, Block> add(Block block) {
            this.itemAppender.add(Objects.requireNonNull(block.asItem()));
            return this;
        }

        @Override
        public TagAppender<Block, Block> addOptional(Block block) {
            this.itemAppender.addOptional(Objects.requireNonNull(block.asItem()));
            return this;
        }

        @Override
        public TagAppender<Block, Block> addTag(TagKey<Block> tag) {
            this.itemAppender.addTag(TagKey.create(Registries.ITEM, tag.location()));
            return this;
        }

        @Override
        public TagAppender<Block, Block> addOptionalTag(TagKey<Block> tag) {
            this.itemAppender.addOptionalTag(TagKey.create(Registries.ITEM, tag.location()));
            return this;
        }

        @Override
        public TagAppender<Block, Block> add(TagEntry entry) {
            itemAppender.add(entry);
            return this;
        }

        @Override
        public TagAppender<Block, Block> replace(boolean value) {
            itemAppender.replace(value);
            return this;
        }

        @Override
        public TagAppender<Block, Block> remove(Block block) {
            itemAppender.remove(block.asItem());
            return this;
        }

        @Override
        public TagAppender<Block, Block> remove(TagKey<Block> tag) {
            itemAppender.remove(TagKey.create(Registries.ITEM, tag.location()));
            return this;
        }
    }
}
