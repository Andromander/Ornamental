package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalItemTagProvider;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrnamentalItemTags extends OrnamentalItemTagProvider {

    public static final List<RegistryObject<? extends Block>> BEAMS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> DOORS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> FENCES = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> FENCE_GATES = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> NETHER_BRICK_FENCES = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> PIGLIN_LOVED = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> POLES = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> SADDLE_DOORS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> SLABS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> STAIRS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> TRAPDOORS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> WALLS = Lists.newArrayList();

    public OrnamentalItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(output, provider, OrnamentalMod.MODID, helper, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addToTag(ModTags.Items.BEAMS, BEAMS);
        addToTag(ItemTags.DOORS, DOORS);
        addToTag(ItemTags.FENCES, FENCES);
        addToTag(Tags.Items.FENCES, FENCES);
        addToTag(Tags.Items.FENCE_GATES, FENCE_GATES);
        addToTag(Tags.Items.FENCES_NETHER_BRICK, NETHER_BRICK_FENCES);
        addToTag(ItemTags.PIGLIN_LOVED, PIGLIN_LOVED);
        addToTag(ModTags.Items.POLES, POLES);
        addToTag(ModTags.Items.SADDLE_DOORS, SADDLE_DOORS);
        addToTag(ItemTags.SLABS, SLABS);
        addToTag(ItemTags.STAIRS, STAIRS);
        addToTag(ItemTags.TRAPDOORS, TRAPDOORS);
        addToTag(ItemTags.WALLS, WALLS);
    }
}
