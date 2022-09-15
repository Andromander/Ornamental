package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> GRASS_GOLEM_FLOWER_PLANTABLE = BlockTags.create(new ResourceLocation(OrnamentalMod.MODID, "grass_golem_flower_plantable"));
    }
}
