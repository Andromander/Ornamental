package com.androsa.nifty.data;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.data.provider.NiftyLootTableProvider;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationTracker;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class NiftyLootTables extends LootTableProvider {

    public NiftyLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Nifty Loot Tables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> tableMap, ValidationTracker tracker) { }

    public static class Blocks extends NiftyLootTableProvider {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.iron_stairs);
            dropSelf(ModBlocks.gold_stairs);
            dropSelf(ModBlocks.diamond_stairs);
            dropSelf(ModBlocks.emerald_stairs);
            dropSelf(ModBlocks.lapis_stairs);
            dropSelf(ModBlocks.obsidian_stairs);
            dropSelf(ModBlocks.coal_stairs);
            dropSelf(ModBlocks.redstone_stairs);
            dropSelf(ModBlocks.missingno_stairs);
            dropSelf(ModBlocks.clay_stairs);
            dropSelf(ModBlocks.dirt_stairs);
            dropSelf(ModBlocks.grass_stairs);
            dropSelf(ModBlocks.hay_stairs);
            dropSelf(ModBlocks.path_stairs);
            dropSelf(ModBlocks.bone_stairs);
            dropSelf(ModBlocks.snow_stairs);
            dropSelf(ModBlocks.ice_stairs);
            dropSelf(ModBlocks.packed_ice_stairs);
            dropSelf(ModBlocks.blue_ice_stairs);

            dropSlab(ModBlocks.iron_slab);
            dropSlab(ModBlocks.gold_slab);
            dropSlab(ModBlocks.diamond_slab);
            dropSlab(ModBlocks.emerald_slab);
            dropSlab(ModBlocks.lapis_slab);
            dropSlab(ModBlocks.obsidian_slab);
            dropSlab(ModBlocks.coal_slab);
            dropSlab(ModBlocks.redstone_slab);
            dropSlab(ModBlocks.missingno_slab);
            dropSlab(ModBlocks.clay_slab);
            dropSlab(ModBlocks.dirt_slab);
            dropSlab(ModBlocks.grass_slab);
            dropSlab(ModBlocks.hay_slab);
            dropSlab(ModBlocks.path_slab);
            dropSlab(ModBlocks.bone_slab);
            dropSlab(ModBlocks.snow_slab);
            dropSlab(ModBlocks.ice_slab);
            dropSlab(ModBlocks.packed_ice_slab);
            dropSlab(ModBlocks.blue_ice_slab);

            dropSelf(ModBlocks.iron_fence);
            dropSelf(ModBlocks.gold_fence);
            dropSelf(ModBlocks.diamond_fence);
            dropSelf(ModBlocks.emerald_fence);
            dropSelf(ModBlocks.lapis_fence);
            dropSelf(ModBlocks.obsidian_fence);
            dropSelf(ModBlocks.coal_fence);
            dropSelf(ModBlocks.redstone_fence);
            dropSelf(ModBlocks.missingno_fence);
            dropSelf(ModBlocks.clay_fence);
            dropSelf(ModBlocks.dirt_fence);
            dropSelf(ModBlocks.grass_fence);
            dropSelf(ModBlocks.hay_fence);
            dropSelf(ModBlocks.path_fence);
            dropSelf(ModBlocks.brick_fence);
            dropSelf(ModBlocks.quartz_fence);
            dropSelf(ModBlocks.bone_fence);
            dropSelf(ModBlocks.red_nether_brick_fence);
            dropSelf(ModBlocks.snow_fence);
            dropSelf(ModBlocks.ice_fence);
            dropSelf(ModBlocks.packed_ice_fence);
            dropSelf(ModBlocks.blue_ice_fence);

            dropSelf(ModBlocks.gold_trapdoor);
            dropSelf(ModBlocks.diamond_trapdoor);
            dropSelf(ModBlocks.emerald_trapdoor);
            dropSelf(ModBlocks.lapis_trapdoor);
            dropSelf(ModBlocks.obsidian_trapdoor);
            dropSelf(ModBlocks.coal_trapdoor);
            dropSelf(ModBlocks.redstone_trapdoor);
            dropSelf(ModBlocks.missingno_trapdoor);
            dropSelf(ModBlocks.clay_trapdoor);
            dropSelf(ModBlocks.dirt_trapdoor);
            dropSelf(ModBlocks.grass_trapdoor);
            dropSelf(ModBlocks.hay_trapdoor);
            dropSelf(ModBlocks.path_trapdoor);
            dropSelf(ModBlocks.brick_trapdoor);
            dropSelf(ModBlocks.quartz_trapdoor);
            dropSelf(ModBlocks.bone_trapdoor);
            dropSelf(ModBlocks.nether_brick_trapdoor);
            dropSelf(ModBlocks.red_nether_brick_trapdoor);
            dropSelf(ModBlocks.snow_trapdoor);
            dropSelf(ModBlocks.ice_trapdoor);
            dropSelf(ModBlocks.packed_ice_trapdoor);
            dropSelf(ModBlocks.blue_ice_trapdoor);

            dropSelf(ModBlocks.iron_fence_gate);
            dropSelf(ModBlocks.gold_fence_gate);
            dropSelf(ModBlocks.diamond_fence_gate);
            dropSelf(ModBlocks.emerald_fence_gate);
            dropSelf(ModBlocks.lapis_fence_gate);
            dropSelf(ModBlocks.obsidian_fence_gate);
            dropSelf(ModBlocks.coal_fence_gate);
            dropSelf(ModBlocks.redstone_fence_gate);
            dropSelf(ModBlocks.missingno_fence_gate);
            dropSelf(ModBlocks.clay_fence_gate);
            dropSelf(ModBlocks.dirt_fence_gate);
            dropSelf(ModBlocks.grass_fence_gate);
            dropSelf(ModBlocks.hay_fence_gate);
            dropSelf(ModBlocks.path_fence_gate);
            dropSelf(ModBlocks.brick_fence_gate);
            dropSelf(ModBlocks.quartz_fence_gate);
            dropSelf(ModBlocks.bone_fence_gate);
            dropSelf(ModBlocks.nether_brick_fence_gate);
            dropSelf(ModBlocks.red_nether_brick_fence_gate);
            dropSelf(ModBlocks.snow_fence_gate);
            dropSelf(ModBlocks.ice_fence_gate);
            dropSelf(ModBlocks.packed_ice_fence_gate);
            dropSelf(ModBlocks.blue_ice_fence_gate);

            dropDoor(ModBlocks.gold_door);
            dropDoor(ModBlocks.diamond_door);
            dropDoor(ModBlocks.emerald_door);
            dropDoor(ModBlocks.lapis_door);
            dropDoor(ModBlocks.obsidian_door);
            dropDoor(ModBlocks.coal_door);
            dropDoor(ModBlocks.redstone_door);
            dropDoor(ModBlocks.missingno_door);
            dropDoor(ModBlocks.clay_door);
            dropDoor(ModBlocks.dirt_door);
            dropDoor(ModBlocks.grass_door);
            dropDoor(ModBlocks.hay_door);
            dropDoor(ModBlocks.path_door);
            dropDoor(ModBlocks.brick_door);
            dropDoor(ModBlocks.quartz_door);
            dropDoor(ModBlocks.bone_door);
            dropDoor(ModBlocks.nether_brick_door);
            dropDoor(ModBlocks.red_nether_brick_door);
            dropDoor(ModBlocks.snow_door);
            dropDoor(ModBlocks.ice_door);
            dropDoor(ModBlocks.packed_ice_door);
            dropDoor(ModBlocks.blue_ice_door);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
