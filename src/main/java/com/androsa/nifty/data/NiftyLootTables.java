package com.androsa.nifty.data;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.ModEntities;
import com.androsa.nifty.data.provider.GolemLootTableProvider;
import com.androsa.nifty.data.provider.NiftyLootTableProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationTracker;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
        return ImmutableList.of(Pair.of(BlockTables::new, LootParameterSets.BLOCK), Pair.of(EntityTables::new, LootParameterSets.ENTITY));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> tableMap, ValidationTracker tracker) { }

    public static class BlockTables extends NiftyLootTableProvider {
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

    public static class EntityTables extends GolemLootTableProvider {

        private static final Set<EntityType<?>> ALLOWED_ENTITIES = ImmutableSet.of(
                ModEntities.GOLD_GOLEM.get(),
                ModEntities.DIAMOND_GOLEM.get(),
                ModEntities.EMERALD_GOLEM.get(),
                ModEntities.LAPIS_GOLEM.get(),
                ModEntities.OBSIDIAN_GOLEM.get(),
                ModEntities.COAL_GOLEM.get(),
                ModEntities.REDSTONE_GOLEM.get(),
                ModEntities.CLAY_GOLEM.get(),
                ModEntities.DIRT_GOLEM.get(),
                ModEntities.GRASS_GOLEM.get(),
                ModEntities.HAY_GOLEM.get(),
                ModEntities.PATH_GOLEM.get(),
                ModEntities.BRICK_GOLEM.get(),
                ModEntities.QUARTZ_GOLEM.get(),
                ModEntities.BONE_GOLEM.get(),
                ModEntities.NETHER_BRICK_GOLEM.get(),
                ModEntities.RED_NETHER_BRICK_GOLEM.get(),
                ModEntities.ICE_GOLEM.get(),
                ModEntities.PACKED_ICE_GOLEM.get(),
                ModEntities.BLUE_ICE_GOLEM.get()
        );

        @Override
        protected void addTables() {
            registerLootTable(ModEntities.GOLD_GOLEM, flowerGolemTable(Blocks.DANDELION, Items.GOLD_INGOT));
            registerLootTable(ModEntities.DIAMOND_GOLEM, flowerGolemTable(Blocks.BLUE_ORCHID, Items.DIAMOND));
            registerLootTable(ModEntities.EMERALD_GOLEM, flowerGolemTable(Blocks.ALLIUM, Items.ALLIUM));
            registerLootTable(ModEntities.LAPIS_GOLEM, golemTable(Items.LAPIS_LAZULI));
            registerLootTable(ModEntities.OBSIDIAN_GOLEM, golemTableBlock(Blocks.OBSIDIAN));
            registerLootTable(ModEntities.COAL_GOLEM, golemTable(Items.COAL));
            registerLootTable(ModEntities.REDSTONE_GOLEM, golemTable(Items.REDSTONE));
            registerLootTable(ModEntities.CLAY_GOLEM, golemTable(Items.CLAY_BALL));
            registerLootTable(ModEntities.DIRT_GOLEM, golemTableBlock(Blocks.DIRT));
            registerLootTable(ModEntities.GRASS_GOLEM, golemTableBlock(Blocks.GRASS_BLOCK));
            registerLootTable(ModEntities.HAY_GOLEM, golemTable(Items.WHEAT));
            registerLootTable(ModEntities.PATH_GOLEM, golemTableBlock(Blocks.GRASS_PATH));
            registerLootTable(ModEntities.BRICK_GOLEM, golemTable(Items.BRICK));
            registerLootTable(ModEntities.QUARTZ_GOLEM, golemTable(Items.QUARTZ));
            registerLootTable(ModEntities.BONE_GOLEM, golemTable(Items.BONE));
            registerLootTable(ModEntities.NETHER_BRICK_GOLEM, golemTable(Items.NETHER_BRICK));
            registerLootTable(ModEntities.RED_NETHER_BRICK_GOLEM, golemTable(Items.NETHER_WART));
            registerLootTable(ModEntities.ICE_GOLEM, golemTableBlock(Blocks.ICE));
            registerLootTable(ModEntities.PACKED_ICE_GOLEM, golemTableBlock(Blocks.PACKED_ICE));
            registerLootTable(ModEntities.BLUE_ICE_GOLEM, golemTableBlock(Blocks.BLUE_ICE));
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ModEntities.ENTITIES.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }

        @Override
        protected boolean isNonLiving(EntityType<?> type) {
            return !ALLOWED_ENTITIES.contains(type) && type.getClassification() == EntityClassification.MISC;
        }
    }
}
