package com.androsa.nifty.data;

import com.androsa.nifty.registry.ModBlocks;
import com.androsa.nifty.data.provider.NiftyItemTagProvider;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public class NiftyItemTags extends NiftyItemTagProvider {

    private ImmutableSet<Supplier<? extends Block>> piglin_loved = ImmutableSet.of(
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate, ModBlocks.gold_door);

    public NiftyItemTags(DataGenerator generator, BlockTagsProvider blockTags) {
        super(generator, blockTags);
    }

    @Override
    protected void registerTags() {
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
        copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);

        piglinLoveTag(piglin_loved);
    }
}
