package com.androsa.ornamental.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.function.Supplier;

public abstract class OrnamentalBlockTagProvider extends BlockTagsProvider {

    public OrnamentalBlockTagProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    protected void beaconBaseTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(BlockTags.BEACON_BASE_BLOCKS).add(block.get());
        }
    }

    protected void doorTag(Set<Supplier<? extends DoorBlock>> set) {
        for (Supplier<? extends DoorBlock> door : set) {
            tag(BlockTags.DOORS).add(door.get());
        }
    }

    protected void dragonImmuneTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(BlockTags.DRAGON_IMMUNE).add(block.get());
        }
    }

    protected void fenceTag(Set<Supplier<? extends FenceBlock>> set) {
        for (Supplier<? extends FenceBlock> fence : set) {
            tag(BlockTags.FENCES).add(fence.get());
            tag(Tags.Blocks.FENCES).add(fence.get());
        }
    }

    protected void fenceGateTag(Set<Supplier<? extends FenceGateBlock>> set) {
        for (Supplier<? extends FenceGateBlock> fencegate : set) {
            tag(BlockTags.FENCE_GATES).add(fencegate.get());
            tag(Tags.Blocks.FENCE_GATES).add(fencegate.get());
        }
    }

    protected void piglinGuardedTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            tag(BlockTags.GUARDED_BY_PIGLINS).add(block.get());
        }
    }

    protected void slabTag(Set<Supplier<? extends SlabBlock>> set) {
        for (Supplier<? extends SlabBlock> slab : set) {
            tag(BlockTags.SLABS).add(slab.get());
        }
    }

    protected void stairsTag(Set<Supplier<? extends StairsBlock>> set) {
        for (Supplier<? extends StairsBlock> stairs : set) {
            tag(BlockTags.STAIRS).add(stairs.get());
        }
    }

    protected void trapdoorTag(Set<Supplier<? extends TrapDoorBlock>> set) {
        for (Supplier<? extends TrapDoorBlock> trapdoor : set) {
            tag(BlockTags.TRAPDOORS).add(trapdoor.get());
        }
    }
}