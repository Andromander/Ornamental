package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalBlockTagProvider;
import com.androsa.ornamental.registry.ModTags;
import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class OrnamentalBlockTags extends OrnamentalBlockTagProvider {

    public static final List<Supplier<? extends Block>> BEACON_BASES = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> BEAMS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> CRYSTAL_SOUNDS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> DOORS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> DRAGON_IMMUNE = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> FENCES = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> FENCE_GATES = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> INFINIBURN_OVERWORLD = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> NETHER_BRICK_FENCE = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> PIGLIN_GUARDED = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> POLES = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SADDLE_DOORS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SLABS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SNOW = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> STAIRS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SUPPORTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> TRAPDOORS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> VIBRATIONS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> WALLS = Lists.newArrayList();

    public static final List<Supplier<? extends Block>> PICKAXE_TOOL = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SHOVEL_TOOL = Lists.newArrayList();

    public static final List<Supplier<? extends Block>> STONE_REQUIRED = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> IRON_REQUIRED = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> DIAMOND_REQUIRED = Lists.newArrayList();

    public OrnamentalBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, OrnamentalMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addToTag(BlockTags.BEACON_BASE_BLOCKS, BEACON_BASES);
        addToTag(ModTags.Blocks.BEAMS, BEAMS);
        addToTag(BlockTags.CRYSTAL_SOUND_BLOCKS, CRYSTAL_SOUNDS);
        addToTag(BlockTags.DOORS, DOORS);
        addToTag(BlockTags.DRAGON_IMMUNE, DRAGON_IMMUNE);
        addToTag(BlockTags.FENCES, FENCES);
        addToTag(Tags.Blocks.FENCES, FENCES);
        addToTag(BlockTags.FENCE_GATES, FENCE_GATES);
        addToTag(Tags.Blocks.FENCE_GATES, FENCE_GATES);
        addToTag(BlockTags.GUARDED_BY_PIGLINS, PIGLIN_GUARDED);
        addToTag(BlockTags.INFINIBURN_OVERWORLD, INFINIBURN_OVERWORLD);
        addToTag(Tags.Blocks.FENCES_NETHER_BRICK, NETHER_BRICK_FENCE);
        addToTag(ModTags.Blocks.POLES, POLES);
        addToTag(ModTags.Blocks.SADDLE_DOORS, SADDLE_DOORS);
        addToTag(BlockTags.SLABS, SLABS);
        addToTag(BlockTags.SNOW, SNOW);
        addToTag(BlockTags.STAIRS, STAIRS);
        addToTag(ModTags.Blocks.SUPPORTS, SUPPORTS);
        addToTag(BlockTags.TRAPDOORS, TRAPDOORS);
        addToTag(BlockTags.VIBRATION_RESONATORS, VIBRATIONS);
        addToTag(BlockTags.WALLS, WALLS);

        addToTag(BlockTags.MINEABLE_WITH_PICKAXE, PICKAXE_TOOL);
        addToTag(BlockTags.MINEABLE_WITH_SHOVEL, SHOVEL_TOOL);

        addToTag(BlockTags.NEEDS_STONE_TOOL, STONE_REQUIRED);
        addToTag(BlockTags.NEEDS_IRON_TOOL, IRON_REQUIRED);
        addToTag(BlockTags.NEEDS_DIAMOND_TOOL, DIAMOND_REQUIRED);

        tag(ModTags.Blocks.GRASS_GOLEM_FLOWER_PLANTABLE).addTag(BlockTags.SMALL_FLOWERS);
    }
}
