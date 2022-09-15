package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> POLES = BlockTags.create(new ResourceLocation(OrnamentalMod.MODID, "poles"));
        public static final TagKey<Block> BEAMS = BlockTags.create(new ResourceLocation(OrnamentalMod.MODID, "beams"));
        public static final TagKey<Block> SADDLE_DOORS = BlockTags.create(new ResourceLocation(OrnamentalMod.MODID, "saddle_doors"));
        public static final TagKey<Block> GRASS_GOLEM_FLOWER_PLANTABLE = BlockTags.create(new ResourceLocation(OrnamentalMod.MODID, "grass_golem_flower_plantable"));
    }

    public static class Items {
        public static final TagKey<Item> POLES = ItemTags.create(new ResourceLocation(OrnamentalMod.MODID, "poles"));
        public static final TagKey<Item> BEAMS = ItemTags.create(new ResourceLocation(OrnamentalMod.MODID, "beams"));
        public static final TagKey<Item> SADDLE_DOORS = ItemTags.create(new ResourceLocation(OrnamentalMod.MODID, "saddle_doors"));
    }
}
