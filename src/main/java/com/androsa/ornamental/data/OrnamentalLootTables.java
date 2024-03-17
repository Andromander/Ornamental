package com.androsa.ornamental.data;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.data.provider.GolemLootTableProvider;
import com.androsa.ornamental.data.provider.OrnamentLootTableProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrnamentalLootTables extends LootTableProvider {

    public OrnamentalLootTables(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockTables::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(VanillaBlocks::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(EntityTables::new, LootContextParamSets.ENTITY)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> tableMap, ValidationContext tracker) { }

    public static class BlockTables extends OrnamentLootTableProvider {

        protected BlockTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
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
            dropSelf(ModBlocks.netherite_stairs);
            dropSelf(ModBlocks.amethyst_stairs);
            dropSelf(ModBlocks.copper_stairs);
            dropSelf(ModBlocks.exposed_copper_stairs);
            dropSelf(ModBlocks.weathered_copper_stairs);
            dropSelf(ModBlocks.oxidized_copper_stairs);
            dropSelf(ModBlocks.magma_stairs);
            dropSelf(ModBlocks.calcite_stairs);

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
            dropSlab(ModBlocks.netherite_slab);
            dropSlab(ModBlocks.amethyst_slab);
            dropSlab(ModBlocks.copper_slab);
            dropSlab(ModBlocks.exposed_copper_slab);
            dropSlab(ModBlocks.weathered_copper_slab);
            dropSlab(ModBlocks.oxidized_copper_slab);
            dropSlab(ModBlocks.magma_slab);
            dropSlab(ModBlocks.calcite_slab);

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
            dropSelf(ModBlocks.netherite_fence);
            dropSelf(ModBlocks.amethyst_fence);
            dropSelf(ModBlocks.copper_fence);
            dropSelf(ModBlocks.exposed_copper_fence);
            dropSelf(ModBlocks.weathered_copper_fence);
            dropSelf(ModBlocks.oxidized_copper_fence);
            dropSelf(ModBlocks.magma_fence);
            dropSelf(ModBlocks.calcite_fence);

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
            dropSelf(ModBlocks.netherite_trapdoor);
            dropSelf(ModBlocks.amethyst_trapdoor);
            dropSelf(ModBlocks.copper_trapdoor);
            dropSelf(ModBlocks.exposed_copper_trapdoor);
            dropSelf(ModBlocks.weathered_copper_trapdoor);
            dropSelf(ModBlocks.oxidized_copper_trapdoor);
            dropSelf(ModBlocks.magma_trapdoor);
            dropSelf(ModBlocks.calcite_trapdoor);

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
            dropSelf(ModBlocks.netherite_fence_gate);
            dropSelf(ModBlocks.amethyst_fence_gate);
            dropSelf(ModBlocks.copper_fence_gate);
            dropSelf(ModBlocks.exposed_copper_fence_gate);
            dropSelf(ModBlocks.weathered_copper_fence_gate);
            dropSelf(ModBlocks.oxidized_copper_fence_gate);
            dropSelf(ModBlocks.magma_fence_gate);
            dropSelf(ModBlocks.calcite_fence_gate);

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
            dropDoor(ModBlocks.netherite_door);
            dropDoor(ModBlocks.amethyst_door);
            dropDoor(ModBlocks.copper_door);
            dropDoor(ModBlocks.exposed_copper_door);
            dropDoor(ModBlocks.weathered_copper_door);
            dropDoor(ModBlocks.oxidized_copper_door);
            dropDoor(ModBlocks.magma_door);
            dropDoor(ModBlocks.calcite_door);

            dropSelf(ModBlocks.iron_pole);
            dropSelf(ModBlocks.gold_pole);
            dropSelf(ModBlocks.diamond_pole);
            dropSelf(ModBlocks.emerald_pole);
            dropSelf(ModBlocks.lapis_pole);
            dropSelf(ModBlocks.obsidian_pole);
            dropSelf(ModBlocks.coal_pole);
            dropSelf(ModBlocks.redstone_pole);
            dropSelf(ModBlocks.missingno_pole);
            dropSelf(ModBlocks.clay_pole);
            dropSelf(ModBlocks.dirt_pole);
            dropSelf(ModBlocks.grass_pole);
            dropSelf(ModBlocks.hay_pole);
            dropSelf(ModBlocks.path_pole);
            dropSelf(ModBlocks.brick_pole);
            dropSelf(ModBlocks.quartz_pole);
            dropSelf(ModBlocks.bone_pole);
            dropSelf(ModBlocks.nether_brick_pole);
            dropSelf(ModBlocks.red_nether_brick_pole);
            dropSelf(ModBlocks.snow_pole);
            dropSelf(ModBlocks.ice_pole);
            dropSelf(ModBlocks.packed_ice_pole);
            dropSelf(ModBlocks.blue_ice_pole);
            dropSelf(ModBlocks.netherite_pole);
            dropSelf(ModBlocks.amethyst_pole);
            dropSelf(ModBlocks.copper_pole);
            dropSelf(ModBlocks.exposed_copper_pole);
            dropSelf(ModBlocks.weathered_copper_pole);
            dropSelf(ModBlocks.oxidized_copper_pole);
            dropSelf(ModBlocks.magma_pole);
            dropSelf(ModBlocks.calcite_pole);

            dropSelf(ModBlocks.iron_beam);
            dropSelf(ModBlocks.gold_beam);
            dropSelf(ModBlocks.diamond_beam);
            dropSelf(ModBlocks.emerald_beam);
            dropSelf(ModBlocks.lapis_beam);
            dropSelf(ModBlocks.obsidian_beam);
            dropSelf(ModBlocks.coal_beam);
            dropSelf(ModBlocks.redstone_beam);
            dropSelf(ModBlocks.missingno_beam);
            dropSelf(ModBlocks.clay_beam);
            dropSelf(ModBlocks.dirt_beam);
            dropSelf(ModBlocks.grass_beam);
            dropSelf(ModBlocks.hay_beam);
            dropSelf(ModBlocks.path_beam);
            dropSelf(ModBlocks.brick_beam);
            dropSelf(ModBlocks.quartz_beam);
            dropSelf(ModBlocks.bone_beam);
            dropSelf(ModBlocks.nether_brick_beam);
            dropSelf(ModBlocks.red_nether_brick_beam);
            dropSelf(ModBlocks.snow_beam);
            dropSelf(ModBlocks.ice_beam);
            dropSelf(ModBlocks.packed_ice_beam);
            dropSelf(ModBlocks.blue_ice_beam);
            dropSelf(ModBlocks.netherite_beam);
            dropSelf(ModBlocks.amethyst_beam);
            dropSelf(ModBlocks.copper_beam);
            dropSelf(ModBlocks.exposed_copper_beam);
            dropSelf(ModBlocks.weathered_copper_beam);
            dropSelf(ModBlocks.oxidized_copper_beam);
            dropSelf(ModBlocks.magma_beam);
            dropSelf(ModBlocks.calcite_beam);

            dropSelf(ModBlocks.iron_wall);
            dropSelf(ModBlocks.gold_wall);
            dropSelf(ModBlocks.diamond_wall);
            dropSelf(ModBlocks.emerald_wall);
            dropSelf(ModBlocks.lapis_wall);
            dropSelf(ModBlocks.obsidian_wall);
            dropSelf(ModBlocks.coal_wall);
            dropSelf(ModBlocks.redstone_wall);
            dropSelf(ModBlocks.missingno_wall);
            dropSelf(ModBlocks.clay_wall);
            dropSelf(ModBlocks.dirt_wall);
            dropSelf(ModBlocks.grass_wall);
            dropSelf(ModBlocks.hay_wall);
            dropSelf(ModBlocks.path_wall);
            dropSelf(ModBlocks.quartz_wall);
            dropSelf(ModBlocks.bone_wall);
            dropSelf(ModBlocks.snow_wall);
            dropSelf(ModBlocks.ice_wall);
            dropSelf(ModBlocks.packed_ice_wall);
            dropSelf(ModBlocks.blue_ice_wall);
            dropSelf(ModBlocks.netherite_wall);
            dropSelf(ModBlocks.amethyst_wall);
            dropSelf(ModBlocks.copper_wall);
            dropSelf(ModBlocks.exposed_copper_wall);
            dropSelf(ModBlocks.weathered_copper_wall);
            dropSelf(ModBlocks.oxidized_copper_wall);
            dropSelf(ModBlocks.magma_wall);
            dropSelf(ModBlocks.calcite_wall);

            dropSelf(ModBlocks.iron_saddle_door);
            dropSelf(ModBlocks.gold_saddle_door);
            dropSelf(ModBlocks.diamond_saddle_door);
            dropSelf(ModBlocks.emerald_saddle_door);
            dropSelf(ModBlocks.lapis_saddle_door);
            dropSelf(ModBlocks.obsidian_saddle_door);
            dropSelf(ModBlocks.coal_saddle_door);
            dropSelf(ModBlocks.redstone_saddle_door);
            dropSelf(ModBlocks.missingno_saddle_door);
            dropSelf(ModBlocks.clay_saddle_door);
            dropSelf(ModBlocks.dirt_saddle_door);
            dropSelf(ModBlocks.grass_saddle_door);
            dropSelf(ModBlocks.hay_saddle_door);
            dropSelf(ModBlocks.path_saddle_door);
            dropSelf(ModBlocks.brick_saddle_door);
            dropSelf(ModBlocks.quartz_saddle_door);
            dropSelf(ModBlocks.bone_saddle_door);
            dropSelf(ModBlocks.nether_brick_saddle_door);
            dropSelf(ModBlocks.red_nether_brick_saddle_door);
            dropSelf(ModBlocks.snow_saddle_door);
            dropSelf(ModBlocks.ice_saddle_door);
            dropSelf(ModBlocks.packed_ice_saddle_door);
            dropSelf(ModBlocks.blue_ice_saddle_door);
            dropSelf(ModBlocks.netherite_saddle_door);
            dropSelf(ModBlocks.amethyst_saddle_door);
            dropSelf(ModBlocks.copper_saddle_door);
            dropSelf(ModBlocks.exposed_copper_saddle_door);
            dropSelf(ModBlocks.weathered_copper_saddle_door);
            dropSelf(ModBlocks.oxidized_copper_saddle_door);
            dropSelf(ModBlocks.magma_saddle_door);
            dropSelf(ModBlocks.calcite_saddle_door);

            dropSelf(ModBlocks.iron_support);
            dropSelf(ModBlocks.gold_support);
            dropSelf(ModBlocks.diamond_support);
            dropSelf(ModBlocks.emerald_support);
            dropSelf(ModBlocks.lapis_support);
            dropSelf(ModBlocks.obsidian_support);
            dropSelf(ModBlocks.coal_support);
            dropSelf(ModBlocks.redstone_support);
            dropSelf(ModBlocks.missingno_support);
            dropSelf(ModBlocks.clay_support);
            dropSelf(ModBlocks.dirt_support);
            dropSelf(ModBlocks.grass_support);
            dropSelf(ModBlocks.hay_support);
            dropSelf(ModBlocks.path_support);
            dropSelf(ModBlocks.brick_support);
            dropSelf(ModBlocks.quartz_support);
            dropSelf(ModBlocks.bone_support);
            dropSelf(ModBlocks.nether_brick_support);
            dropSelf(ModBlocks.red_nether_brick_support);
            dropSelf(ModBlocks.snow_support);
            dropSelf(ModBlocks.ice_support);
            dropSelf(ModBlocks.packed_ice_support);
            dropSelf(ModBlocks.blue_ice_support);
            dropSelf(ModBlocks.netherite_support);
            dropSelf(ModBlocks.copper_support);
            dropSelf(ModBlocks.exposed_copper_support);
            dropSelf(ModBlocks.weathered_copper_support);
            dropSelf(ModBlocks.oxidized_copper_support);
            dropSelf(ModBlocks.amethyst_support);
            dropSelf(ModBlocks.magma_support);
            dropSelf(ModBlocks.calcite_support);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    public static class VanillaBlocks extends BlockLootSubProvider {

        protected VanillaBlocks() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            add(Blocks.DIRT_PATH, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.DIRT));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ImmutableList.of(Blocks.DIRT_PATH);
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
                ModEntities.BLUE_ICE_GOLEM.get(),
                ModEntities.NETHERITE_GOLEM.get(),
                ModEntities.AMETHYST_GOLEM.get(),
                ModEntities.COPPER_GOLEM.get(),
                ModEntities.MAGMA_GOLEM.get(),
                ModEntities.CALCITE_GOLEM.get()
        );

        protected EntityTables() {
            super(FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        public void generate() {
            add(ModEntities.GOLD_GOLEM, flowerGolemTable(Blocks.DANDELION, Items.GOLD_INGOT));
            add(ModEntities.DIAMOND_GOLEM, flowerGolemTable(Blocks.BLUE_ORCHID, Items.DIAMOND));
            add(ModEntities.EMERALD_GOLEM, flowerGolemTable(Blocks.ALLIUM, Items.EMERALD));
            add(ModEntities.LAPIS_GOLEM, golemTable(Items.LAPIS_LAZULI));
            add(ModEntities.OBSIDIAN_GOLEM, golemTableBlock(Blocks.OBSIDIAN));
            add(ModEntities.COAL_GOLEM, golemTable(Items.COAL));
            add(ModEntities.REDSTONE_GOLEM, golemTable(Items.REDSTONE));
            add(ModEntities.CLAY_GOLEM, golemTable(Items.CLAY_BALL));
            add(ModEntities.DIRT_GOLEM, golemTableBlock(Blocks.DIRT));
            add(ModEntities.GRASS_GOLEM, golemTableBlock(Blocks.GRASS_BLOCK));
            add(ModEntities.HAY_GOLEM, golemTable(Items.WHEAT));
            add(ModEntities.PATH_GOLEM, golemTableBlock(Blocks.DIRT_PATH));
            add(ModEntities.BRICK_GOLEM, golemTable(Items.BRICK));
            add(ModEntities.QUARTZ_GOLEM, golemTable(Items.QUARTZ));
            add(ModEntities.BONE_GOLEM, golemTable(Items.BONE));
            add(ModEntities.NETHER_BRICK_GOLEM, golemTable(Items.NETHER_BRICK));
            add(ModEntities.RED_NETHER_BRICK_GOLEM, golemTable(Items.NETHER_WART));
            add(ModEntities.ICE_GOLEM, golemTableBlock(Blocks.ICE));
            add(ModEntities.PACKED_ICE_GOLEM, golemTableBlock(Blocks.PACKED_ICE));
            add(ModEntities.BLUE_ICE_GOLEM, golemTableBlock(Blocks.BLUE_ICE));
            add(ModEntities.NETHERITE_GOLEM, golemTable(Items.NETHERITE_INGOT));
            add(ModEntities.AMETHYST_GOLEM, golemTable(Items.AMETHYST_SHARD));
            add(ModEntities.COPPER_GOLEM, golemTable(Items.COPPER_INGOT));
            add(ModEntities.MAGMA_GOLEM, golemTable(Items.MAGMA_CREAM));
            add(ModEntities.CALCITE_GOLEM, golemTable(Blocks.CALCITE));
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return ModEntities.ENTITIES.getEntries().stream().map(Supplier::get);
        }

        @Override
        protected boolean canHaveLootTable(EntityType<?> type) {
            return ALLOWED_ENTITIES.contains(type) || type.getCategory() != MobCategory.MISC;
        }
    }
}
