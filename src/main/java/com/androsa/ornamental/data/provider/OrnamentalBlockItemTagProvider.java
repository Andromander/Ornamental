package com.androsa.ornamental.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Supplier;

public abstract class OrnamentalBlockItemTagProvider {

    public abstract void run();

    protected abstract TagAppender<Block, Block> tag(TagKey<Block> blocktag, TagKey<Item> itemtag);

    protected Block[] array(List<Supplier<? extends Block>> list) {
        List<Block> blocks = Lists.newArrayList();

        for (Supplier<? extends Block> block : list) {
            blocks.add(block.get());
        }

        return blocks.toArray(new Block[0]);
    }
}
