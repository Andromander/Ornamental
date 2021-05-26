package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.data.provider.OrnamentalItemTagProvider;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class OrnamentalItemTags extends OrnamentalItemTagProvider {

    private ImmutableSet<Supplier<? extends Block>> piglin_loved = ImmutableSet.of(
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate, ModBlocks.gold_door, ModBlocks.gold_pole, ModBlocks.gold_beam);

    public OrnamentalItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper, blockTags);
    }

    @Override
    protected void addTags() {
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        copy(Tags.Blocks.FENCES_NETHER_BRICK, Tags.Items.FENCES_NETHER_BRICK);

        piglinLoveTag(piglin_loved);
    }
}
