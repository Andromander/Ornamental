package com.androsa.nifty.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;

import java.util.Set;
import java.util.function.Supplier;

public abstract class NiftyBlockTagProvider extends BlockTagsProvider {

    public NiftyBlockTagProvider(DataGenerator generator) {
        super(generator);
    }

    protected void beaconBaseTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(block.get());
        }
    }

    protected void doorTag(Set<Supplier<? extends DoorBlock>> set) {
        for (Supplier<? extends DoorBlock> door : set) {
            getOrCreateBuilder(BlockTags.DOORS).add(door.get());
        }
    }

    protected void dragonImmuneTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            getOrCreateBuilder(BlockTags.DRAGON_IMMUNE).add(block.get());
        }
    }

    protected void fenceTag(Set<Supplier<? extends FenceBlock>> set) {
        for (Supplier<? extends FenceBlock> fence : set) {
            getOrCreateBuilder(BlockTags.FENCES).add(fence.get());
            getOrCreateBuilder(Tags.Blocks.FENCES).add(fence.get());
        }
    }

    protected void fenceGateTag(Set<Supplier<? extends FenceGateBlock>> set) {
        for (Supplier<? extends FenceGateBlock> fencegate : set) {
            getOrCreateBuilder(BlockTags.FENCE_GATES).add(fencegate.get());
            getOrCreateBuilder(Tags.Blocks.FENCE_GATES).add(fencegate.get());
        }
    }

    protected void piglinGuardedTag(Set<Supplier<? extends Block>> set) {
        for (Supplier<? extends Block> block : set) {
            getOrCreateBuilder(BlockTags.GUARDED_BY_PIGLINS).add(block.get());
        }
    }

    protected void slabTag(Set<Supplier<? extends SlabBlock>> set) {
        for (Supplier<? extends SlabBlock> slab : set) {
            getOrCreateBuilder(BlockTags.SLABS).add(slab.get());
        }
    }

    protected void stairsTag(Set<Supplier<? extends StairsBlock>> set) {
        for (Supplier<? extends StairsBlock> stairs : set) {
            getOrCreateBuilder(BlockTags.STAIRS).add(stairs.get());
        }
    }

    protected void trapdoorTag(Set<Supplier<? extends TrapDoorBlock>> set) {
        for (Supplier<? extends TrapDoorBlock> trapdoor : set) {
            getOrCreateBuilder(BlockTags.TRAPDOORS).add(trapdoor.get());
        }
    }
}