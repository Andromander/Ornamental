package com.androsa.ornamental.data;

import com.androsa.ornamental.data.provider.OrnamentalBlockItemTagProvider;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;

public abstract class OrnamentalBlockItemTags extends OrnamentalBlockItemTagProvider {
    @Override
    public void run() {
        tag(ModTags.Blocks.BEAMS, ModTags.Items.BEAMS).add(array(OrnamentalBlockTags.BEAMS));
        tag(BlockTags.DOORS, ItemTags.DOORS).add(array(OrnamentalBlockTags.DOORS));
        tag(BlockTags.FENCES, ItemTags.FENCES).add(array(OrnamentalBlockTags.FENCES));
        tag(Tags.Blocks.FENCES, ItemTags.FENCES).add(array(OrnamentalBlockTags.FENCES));
        tag(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES).add(array(OrnamentalBlockTags.FENCE_GATES));
        tag(Tags.Blocks.FENCE_GATES, ItemTags.FENCE_GATES).add(array(OrnamentalBlockTags.FENCE_GATES));
        tag(ModTags.Blocks.POLES, ModTags.Items.POLES).add(array(OrnamentalBlockTags.POLES));
        tag(ModTags.Blocks.SADDLE_DOORS, ModTags.Items.SADDLE_DOORS).add(array(OrnamentalBlockTags.SADDLE_DOORS));
        tag(BlockTags.SLABS, ItemTags.SLABS).add(array(OrnamentalBlockTags.SLABS));
        tag(BlockTags.STAIRS, ItemTags.STAIRS).add(array(OrnamentalBlockTags.STAIRS));
        tag(ModTags.Blocks.SUPPORTS, ModTags.Items.SUPPORTS).add(array(OrnamentalBlockTags.SUPPORTS));
        tag(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS).add(array(OrnamentalBlockTags.TRAPDOORS));
        tag(BlockTags.WALLS, ItemTags.WALLS).add(array(OrnamentalBlockTags.WALLS));
    }
}
