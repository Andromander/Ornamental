package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalRecipeProvider;
import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class OrnamentalRecipes extends OrnamentalRecipeProvider {

    public OrnamentalRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, OrnamentalMod.MODID);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        stairs(consumer, ModBlocks.iron_stairs, Blocks.IRON_BLOCK, false);
        stairs(consumer, ModBlocks.gold_stairs, Blocks.GOLD_BLOCK, false);
        stairs(consumer, ModBlocks.diamond_stairs, Blocks.DIAMOND_BLOCK, false);
        stairs(consumer, ModBlocks.emerald_stairs, Blocks.EMERALD_BLOCK, false);
        stairs(consumer, ModBlocks.lapis_stairs, Blocks.LAPIS_BLOCK, false);
        stairs(consumer, ModBlocks.obsidian_stairs, Blocks.OBSIDIAN, false);
        stairs(consumer, ModBlocks.coal_stairs, Blocks.COAL_BLOCK, false);
        stairs(consumer, ModBlocks.redstone_stairs, Blocks.REDSTONE_BLOCK, false);
        stairs(consumer, ModBlocks.clay_stairs, Blocks.CLAY, false);
        stairs(consumer, ModBlocks.dirt_stairs, Blocks.DIRT, false);
        stairs(consumer, ModBlocks.grass_stairs, Blocks.GRASS_BLOCK, false);
        stairs(consumer, ModBlocks.hay_stairs, Blocks.HAY_BLOCK, false);
        stairs(consumer, ModBlocks.path_stairs, Blocks.DIRT_PATH, false);
        stairs(consumer, ModBlocks.bone_stairs, Blocks.BONE_BLOCK, false);
        stairs(consumer, ModBlocks.snow_stairs, Blocks.SNOW_BLOCK, false);
        stairs(consumer, ModBlocks.ice_stairs, Blocks.ICE, false);
        stairs(consumer, ModBlocks.packed_ice_stairs, Blocks.PACKED_ICE, false);
        stairs(consumer, ModBlocks.blue_ice_stairs, Blocks.BLUE_ICE, false);
        stairs(consumer, ModBlocks.netherite_stairs, Blocks.NETHERITE_BLOCK, false);
        stairs(consumer, ModBlocks.amethyst_stairs, Blocks.AMETHYST_BLOCK, false);
        stairs(consumer, ModBlocks.copper_stairs, Blocks.COPPER_BLOCK, false);
        stairs(consumer, ModBlocks.exposed_copper_stairs, Blocks.EXPOSED_COPPER, false);
        stairs(consumer, ModBlocks.weathered_copper_stairs, Blocks.WEATHERED_COPPER, false);
        stairs(consumer, ModBlocks.oxidized_copper_stairs, Blocks.OXIDIZED_COPPER, false);
        stairs(consumer, ModBlocks.magma_stairs, Blocks.MAGMA_BLOCK, false);
        stairs(consumer, ModBlocks.calcite_stairs, Blocks.CALCITE, false);

        slab(consumer, ModBlocks.iron_slab, Blocks.IRON_BLOCK, false);
        slab(consumer, ModBlocks.gold_slab, Blocks.GOLD_BLOCK, false);
        slab(consumer, ModBlocks.diamond_slab, Blocks.DIAMOND_BLOCK, false);
        slab(consumer, ModBlocks.emerald_slab, Blocks.EMERALD_BLOCK, false);
        slab(consumer, ModBlocks.lapis_slab, Blocks.LAPIS_BLOCK, false);
        slab(consumer, ModBlocks.obsidian_slab, Blocks.OBSIDIAN, false);
        slab(consumer, ModBlocks.coal_slab, Blocks.COAL_BLOCK, false);
        slab(consumer, ModBlocks.redstone_slab, Blocks.REDSTONE_BLOCK, false);
        slab(consumer, ModBlocks.clay_slab, Blocks.CLAY, false);
        slab(consumer, ModBlocks.dirt_slab, Blocks.DIRT, false);
        slab(consumer, ModBlocks.grass_slab, Blocks.GRASS_BLOCK, false);
        slab(consumer, ModBlocks.hay_slab, Blocks.HAY_BLOCK, false);
        slab(consumer, ModBlocks.path_slab, Blocks.DIRT_PATH, false);
        slab(consumer, ModBlocks.bone_slab, Blocks.BONE_BLOCK, false);
        slab(consumer, ModBlocks.snow_slab, Blocks.SNOW_BLOCK, true);
        slab(consumer, ModBlocks.ice_slab, Blocks.ICE, false);
        slab(consumer, ModBlocks.packed_ice_slab, Blocks.PACKED_ICE, false);
        slab(consumer, ModBlocks.blue_ice_slab, Blocks.BLUE_ICE, false);
        slab(consumer, ModBlocks.netherite_slab, Blocks.NETHERITE_BLOCK, false);
        slab(consumer, ModBlocks.amethyst_slab, Blocks.AMETHYST_BLOCK, false);
        slab(consumer, ModBlocks.copper_slab, Blocks.COPPER_BLOCK, false);
        slab(consumer, ModBlocks.exposed_copper_slab, Blocks.EXPOSED_COPPER, false);
        slab(consumer, ModBlocks.weathered_copper_slab, Blocks.WEATHERED_COPPER, false);
        slab(consumer, ModBlocks.oxidized_copper_slab, Blocks.OXIDIZED_COPPER, false);
        slab(consumer, ModBlocks.magma_slab, Blocks.MAGMA_BLOCK, false);
        slab(consumer, ModBlocks.calcite_slab, Blocks.CALCITE, false);

        fence(consumer, ModBlocks.iron_fence, Blocks.IRON_BLOCK, Items.IRON_INGOT, false);
        fence(consumer, ModBlocks.gold_fence, Blocks.GOLD_BLOCK, Items.GOLD_INGOT, false);
        fence(consumer, ModBlocks.diamond_fence, Blocks.DIAMOND_BLOCK, Items.DIAMOND, false);
        fence(consumer, ModBlocks.emerald_fence, Blocks.EMERALD_BLOCK, Items.EMERALD, false);
        fence(consumer, ModBlocks.lapis_fence, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI, false);
        fence(consumer, ModBlocks.obsidian_fence, Blocks.OBSIDIAN, ModBlocks.obsidian_slab.get().asItem(), false);
        fence(consumer, ModBlocks.coal_fence, Blocks.COAL_BLOCK, Items.COAL, false);
        fence(consumer, ModBlocks.redstone_fence, Blocks.REDSTONE_BLOCK, Items.REDSTONE, false);
        fence(consumer, ModBlocks.clay_fence, Blocks.CLAY, Items.CLAY_BALL, false);
        fence(consumer, ModBlocks.dirt_fence, Blocks.DIRT, ModBlocks.dirt_slab.get().asItem(), false);
        fence(consumer, ModBlocks.grass_fence, Blocks.GRASS_BLOCK, ModBlocks.grass_slab.get().asItem(), false);
        fence(consumer, ModBlocks.hay_fence, Blocks.HAY_BLOCK, Items.WHEAT, false);
        fence(consumer, ModBlocks.path_fence, Blocks.DIRT_PATH, ModBlocks.path_slab.get().asItem(), false);
        fence(consumer, ModBlocks.brick_fence, Blocks.BRICKS, Items.BRICK, false);
        fence(consumer, ModBlocks.quartz_fence, Blocks.QUARTZ_BLOCK, Items.QUARTZ, false);
        fence(consumer, ModBlocks.bone_fence, Blocks.BONE_BLOCK, Items.BONE_MEAL, false);
        fence(consumer, ModBlocks.red_nether_brick_fence, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART, false);
        fence(consumer, ModBlocks.snow_fence, Blocks.SNOW_BLOCK, Items.SNOWBALL, false);
        fence(consumer, ModBlocks.ice_fence, Blocks.ICE, ModBlocks.ice_slab.get().asItem(), false);
        fence(consumer, ModBlocks.packed_ice_fence, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab.get().asItem(), false);
        fence(consumer, ModBlocks.blue_ice_fence, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab.get().asItem(), false);
        fence(consumer, ModBlocks.netherite_fence, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT, false);
        fence(consumer, ModBlocks.amethyst_fence, Blocks.AMETHYST_BLOCK, Items.AMETHYST_SHARD, false);
        fence(consumer, ModBlocks.copper_fence, Blocks.COPPER_BLOCK, Items.COPPER_INGOT, false);
        fence(consumer, ModBlocks.exposed_copper_fence, Blocks.EXPOSED_COPPER, ModBlocks.exposed_copper_slab.get().asItem(), false);
        fence(consumer, ModBlocks.weathered_copper_fence, Blocks.WEATHERED_COPPER, ModBlocks.weathered_copper_slab.get().asItem(), false);
        fence(consumer, ModBlocks.oxidized_copper_fence, Blocks.OXIDIZED_COPPER, ModBlocks.oxidized_copper_slab.get().asItem(), false);
        fence(consumer, ModBlocks.magma_fence, Blocks.MAGMA_BLOCK, Items.MAGMA_CREAM, false);
        fence(consumer, ModBlocks.calcite_fence, Blocks.CALCITE, ModBlocks.calcite_slab.get().asItem(), false);

        trapdoor(consumer, ModBlocks.gold_trapdoor, Items.GOLD_INGOT, false);
        trapdoor(consumer, ModBlocks.diamond_trapdoor, Items.DIAMOND, false);
        trapdoor(consumer, ModBlocks.emerald_trapdoor, Items.EMERALD, false);
        trapdoor(consumer, ModBlocks.lapis_trapdoor, Items.LAPIS_LAZULI, false);
        trapdoor(consumer, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.coal_trapdoor, Items.COAL, false);
        trapdoorWide(consumer, ModBlocks.redstone_trapdoor, Items.REDSTONE, false);
        trapdoorWide(consumer, ModBlocks.clay_trapdoor, Items.CLAY, false);
        trapdoorWide(consumer, ModBlocks.dirt_trapdoor, ModBlocks.dirt_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.grass_trapdoor, ModBlocks.grass_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.hay_trapdoor, Items.HAY_BLOCK, false);
        trapdoorWide(consumer, ModBlocks.path_trapdoor, ModBlocks.path_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.brick_trapdoor, Items.BRICK, false);
        trapdoorWide(consumer, ModBlocks.quartz_trapdoor, Items.QUARTZ, false);
        trapdoorWide(consumer, ModBlocks.bone_trapdoor, Items.BONE_MEAL, false);
        trapdoorWide(consumer, ModBlocks.nether_brick_trapdoor, Items.NETHER_BRICK, false);
        trapdoorWide(consumer, ModBlocks.red_nether_brick_trapdoor, Items.NETHER_WART, false);
        trapdoorWide(consumer, ModBlocks.snow_trapdoor, Items.SNOWBALL, false);
        trapdoorWide(consumer, ModBlocks.ice_trapdoor, ModBlocks.ice_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.packed_ice_trapdoor, ModBlocks.packed_ice_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.blue_ice_trapdoor, ModBlocks.blue_ice_slab.get(), false);
        trapdoor(consumer, ModBlocks.netherite_trapdoor, Items.NETHERITE_INGOT, false);
        trapdoorWide(consumer, ModBlocks.amethyst_trapdoor, Items.AMETHYST_SHARD, false);
        trapdoor(consumer, ModBlocks.copper_trapdoor, Items.COPPER_INGOT, false);
        trapdoor(consumer, ModBlocks.exposed_copper_trapdoor, ModBlocks.exposed_copper_slab.get(), false);
        trapdoor(consumer, ModBlocks.weathered_copper_trapdoor, ModBlocks.weathered_copper_slab.get(), false);
        trapdoor(consumer, ModBlocks.oxidized_copper_trapdoor, ModBlocks.oxidized_copper_slab.get(), false);
        trapdoorWide(consumer, ModBlocks.magma_trapdoor, Items.MAGMA_CREAM, false);
        trapdoor(consumer, ModBlocks.calcite_trapdoor, ModBlocks.calcite_slab.get(), false);

        fencegate(consumer, ModBlocks.iron_fence_gate, Blocks.IRON_BLOCK, Items.IRON_INGOT, false);
        fencegate(consumer, ModBlocks.gold_fence_gate, Blocks.GOLD_BLOCK, Items.GOLD_INGOT, false);
        fencegate(consumer, ModBlocks.diamond_fence_gate, Blocks.DIAMOND_BLOCK, Items.DIAMOND, false);
        fencegate(consumer, ModBlocks.emerald_fence_gate, Blocks.EMERALD_BLOCK, Items.EMERALD, false);
        fencegate(consumer, ModBlocks.lapis_fence_gate, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI, false);
        fencegate(consumer, ModBlocks.obsidian_fence_gate, Blocks.OBSIDIAN, ModBlocks.obsidian_slab.get(), false);
        fencegate(consumer, ModBlocks.coal_fence_gate, Blocks.COAL_BLOCK, Items.COAL, false);
        fencegate(consumer, ModBlocks.redstone_fence_gate, Blocks.REDSTONE_BLOCK, Items.REDSTONE, false);
        fencegate(consumer, ModBlocks.clay_fence_gate, Blocks.CLAY, Items.CLAY_BALL, false);
        fencegate(consumer, ModBlocks.dirt_fence_gate, Blocks.DIRT, ModBlocks.dirt_slab.get(), false);
        fencegate(consumer, ModBlocks.grass_fence_gate, Blocks.GRASS_BLOCK, ModBlocks.grass_slab.get(), false);
        fencegate(consumer, ModBlocks.hay_fence_gate, Blocks.HAY_BLOCK, Items.WHEAT, false);
        fencegate(consumer, ModBlocks.path_fence_gate, Blocks.DIRT_PATH, ModBlocks.path_slab.get(), false);
        fencegate(consumer, ModBlocks.brick_fence_gate, Blocks.BRICKS, Items.BRICK, false);
        fencegate(consumer, ModBlocks.quartz_fence_gate, Blocks.QUARTZ_BLOCK, Items.QUARTZ, false);
        fencegate(consumer, ModBlocks.bone_fence_gate, Blocks.BONE_BLOCK, Items.BONE_MEAL, false);
        fencegate(consumer, ModBlocks.nether_brick_fence_gate, Blocks.NETHER_BRICKS, Items.NETHER_BRICK, false);
        fencegate(consumer, ModBlocks.red_nether_brick_fence_gate, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART, false);
        fencegate(consumer, ModBlocks.snow_fence_gate, Blocks.SNOW_BLOCK, Items.SNOWBALL, false);
        fencegate(consumer, ModBlocks.ice_fence_gate, Blocks.ICE, ModBlocks.ice_slab.get(), false);
        fencegate(consumer, ModBlocks.packed_ice_fence_gate, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab.get(), false);
        fencegate(consumer, ModBlocks.blue_ice_fence_gate, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab.get(), false);
        fencegate(consumer, ModBlocks.netherite_fence_gate, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT, false);
        fencegate(consumer, ModBlocks.amethyst_fence_gate, Blocks.AMETHYST_BLOCK, Items.AMETHYST_SHARD, false);
        fencegate(consumer, ModBlocks.copper_fence_gate, Blocks.COPPER_BLOCK, Items.COPPER_INGOT, false);
        fencegate(consumer, ModBlocks.exposed_copper_fence_gate, Blocks.EXPOSED_COPPER, ModBlocks.exposed_copper_slab.get(), false);
        fencegate(consumer, ModBlocks.weathered_copper_fence_gate, Blocks.WEATHERED_COPPER, ModBlocks.weathered_copper_slab.get(), false);
        fencegate(consumer, ModBlocks.oxidized_copper_fence_gate, Blocks.OXIDIZED_COPPER, ModBlocks.oxidized_copper_slab.get(), false);
        fencegate(consumer, ModBlocks.magma_fence_gate, Blocks.MAGMA_BLOCK, Items.MAGMA_CREAM, false);
        fencegate(consumer, ModBlocks.calcite_fence_gate, Blocks.CALCITE, ModBlocks.calcite_slab.get(), false);

        door(consumer, ModBlocks.gold_door, Items.GOLD_INGOT, false);
        door(consumer, ModBlocks.diamond_door, Items.DIAMOND, false);
        door(consumer, ModBlocks.emerald_door, Items.EMERALD, false);
        door(consumer, ModBlocks.lapis_door, Items.LAPIS_LAZULI, false);
        door(consumer, ModBlocks.obsidian_door, ModBlocks.obsidian_slab.get(), false);
        door(consumer, ModBlocks.coal_door, Items.COAL, false);
        door(consumer, ModBlocks.redstone_door, Items.REDSTONE, false);
        door(consumer, ModBlocks.clay_door, Items.CLAY, false);
        door(consumer, ModBlocks.dirt_door, ModBlocks.dirt_slab.get(), false);
        door(consumer, ModBlocks.grass_door, ModBlocks.grass_slab.get(), false);
        door(consumer, ModBlocks.hay_door, Items.WHEAT, false);
        door(consumer, ModBlocks.path_door, ModBlocks.path_slab.get(), false);
        door(consumer, ModBlocks.brick_door, Items.BRICK, false);
        door(consumer, ModBlocks.quartz_door, Items.QUARTZ, false);
        door(consumer, ModBlocks.bone_door, Items.BONE_MEAL, false);
        door(consumer, ModBlocks.nether_brick_door, Items.NETHER_BRICK, false);
        door(consumer, ModBlocks.red_nether_brick_door, Items.NETHER_WART, false);
        door(consumer, ModBlocks.snow_door, Items.SNOWBALL, false);
        door(consumer, ModBlocks.ice_door, ModBlocks.ice_slab.get(), false);
        door(consumer, ModBlocks.packed_ice_door, ModBlocks.packed_ice_slab.get(), false);
        door(consumer, ModBlocks.blue_ice_door, ModBlocks.blue_ice_slab.get(), false);
        door(consumer, ModBlocks.netherite_door, Items.NETHERITE_INGOT, false);
        door(consumer, ModBlocks.amethyst_door, Items.AMETHYST_SHARD, false);
        door(consumer, ModBlocks.copper_door, Items.COPPER_INGOT, false);
        door(consumer, ModBlocks.exposed_copper_door, ModBlocks.exposed_copper_slab.get(), false);
        door(consumer, ModBlocks.weathered_copper_door, ModBlocks.weathered_copper_slab.get(), false);
        door(consumer, ModBlocks.oxidized_copper_door, ModBlocks.oxidized_copper_slab.get(), false);
        door(consumer, ModBlocks.magma_door, Items.MAGMA_CREAM, false);
        door(consumer, ModBlocks.calcite_door, ModBlocks.calcite_slab.get(), false);

        pole(consumer, ModBlocks.iron_pole, ModBlocks.iron_slab.get(), false);
        pole(consumer, ModBlocks.gold_pole, ModBlocks.gold_slab.get(), false);
        pole(consumer, ModBlocks.diamond_pole, ModBlocks.diamond_slab.get(), false);
        pole(consumer, ModBlocks.emerald_pole, ModBlocks.emerald_slab.get(), false);
        pole(consumer, ModBlocks.lapis_pole, ModBlocks.lapis_slab.get(), false);
        pole(consumer, ModBlocks.obsidian_pole, ModBlocks.obsidian_slab.get(), false);
        pole(consumer, ModBlocks.coal_pole, ModBlocks.coal_slab.get(), false);
        pole(consumer, ModBlocks.redstone_pole, ModBlocks.redstone_slab.get(), false);
        pole(consumer, ModBlocks.clay_pole, ModBlocks.clay_slab.get(), false);
        pole(consumer, ModBlocks.dirt_pole, ModBlocks.dirt_slab.get(), false);
        pole(consumer, ModBlocks.grass_pole, ModBlocks.grass_slab.get(), false);
        pole(consumer, ModBlocks.hay_pole, ModBlocks.hay_slab.get(), false);
        pole(consumer, ModBlocks.path_pole, ModBlocks.path_slab.get(), false);
        pole(consumer, ModBlocks.brick_pole, Blocks.BRICK_SLAB, false);
        pole(consumer, ModBlocks.quartz_pole, Blocks.QUARTZ_SLAB, false);
        pole(consumer, ModBlocks.bone_pole, ModBlocks.bone_slab.get(), false);
        pole(consumer, ModBlocks.nether_brick_pole, Blocks.NETHER_BRICK_SLAB, false);
        pole(consumer, ModBlocks.red_nether_brick_pole, Blocks.RED_NETHER_BRICK_SLAB, false);
        pole(consumer, ModBlocks.snow_pole, ModBlocks.snow_slab.get(), false);
        pole(consumer, ModBlocks.ice_pole, ModBlocks.ice_slab.get(), false);
        pole(consumer, ModBlocks.packed_ice_pole, ModBlocks.packed_ice_slab.get(), false);
        pole(consumer, ModBlocks.blue_ice_pole, ModBlocks.blue_ice_slab.get(), false);
        pole(consumer, ModBlocks.netherite_pole, ModBlocks.netherite_slab.get(), false);
        pole(consumer, ModBlocks.amethyst_pole, ModBlocks.amethyst_slab.get(), false);
        pole(consumer, ModBlocks.copper_pole, ModBlocks.copper_slab.get(), false);
        pole(consumer, ModBlocks.exposed_copper_pole, ModBlocks.exposed_copper_slab.get(), false);
        pole(consumer, ModBlocks.weathered_copper_pole, ModBlocks.weathered_copper_slab.get(), false);
        pole(consumer, ModBlocks.oxidized_copper_pole, ModBlocks.oxidized_copper_slab.get(), false);
        pole(consumer, ModBlocks.magma_pole, ModBlocks.magma_slab.get(), false);
        pole(consumer, ModBlocks.calcite_pole, ModBlocks.calcite_slab.get(), false);

        beam(consumer, ModBlocks.iron_beam, ModBlocks.iron_slab.get(), false);
        beam(consumer, ModBlocks.gold_beam, ModBlocks.gold_slab.get(), false);
        beam(consumer, ModBlocks.diamond_beam, ModBlocks.diamond_slab.get(), false);
        beam(consumer, ModBlocks.emerald_beam, ModBlocks.emerald_slab.get(), false);
        beam(consumer, ModBlocks.lapis_beam, ModBlocks.lapis_slab.get(), false);
        beam(consumer, ModBlocks.obsidian_beam, ModBlocks.obsidian_slab.get(), false);
        beam(consumer, ModBlocks.coal_beam, ModBlocks.coal_slab.get(), false);
        beam(consumer, ModBlocks.redstone_beam, ModBlocks.redstone_slab.get(), false);
        beam(consumer, ModBlocks.clay_beam, ModBlocks.clay_slab.get(), false);
        beam(consumer, ModBlocks.dirt_beam, ModBlocks.dirt_slab.get(), false);
        beam(consumer, ModBlocks.grass_beam, ModBlocks.grass_slab.get(), false);
        beam(consumer, ModBlocks.hay_beam, ModBlocks.hay_slab.get(), false);
        beam(consumer, ModBlocks.path_beam, ModBlocks.path_slab.get(), false);
        beam(consumer, ModBlocks.brick_beam, Blocks.BRICK_SLAB, false);
        beam(consumer, ModBlocks.quartz_beam, Blocks.QUARTZ_SLAB, false);
        beam(consumer, ModBlocks.bone_beam, ModBlocks.bone_slab.get(), false);
        beam(consumer, ModBlocks.nether_brick_beam, Blocks.NETHER_BRICK_SLAB, false);
        beam(consumer, ModBlocks.red_nether_brick_beam, Blocks.RED_NETHER_BRICK_SLAB, false);
        beam(consumer, ModBlocks.snow_beam, ModBlocks.snow_slab.get(), false);
        beam(consumer, ModBlocks.ice_beam, ModBlocks.ice_slab.get(), false);
        beam(consumer, ModBlocks.packed_ice_beam, ModBlocks.packed_ice_slab.get(), false);
        beam(consumer, ModBlocks.blue_ice_beam, ModBlocks.blue_ice_slab.get(), false);
        beam(consumer, ModBlocks.netherite_beam, ModBlocks.netherite_slab.get(), false);
        beam(consumer, ModBlocks.amethyst_beam, ModBlocks.amethyst_slab.get(), false);
        beam(consumer, ModBlocks.copper_beam, ModBlocks.copper_slab.get(), false);
        beam(consumer, ModBlocks.exposed_copper_beam, ModBlocks.exposed_copper_slab.get(), false);
        beam(consumer, ModBlocks.weathered_copper_beam, ModBlocks.weathered_copper_slab.get(), false);
        beam(consumer, ModBlocks.oxidized_copper_beam, ModBlocks.oxidized_copper_slab.get(), false);
        beam(consumer, ModBlocks.magma_beam, ModBlocks.magma_slab.get(), false);
        beam(consumer, ModBlocks.calcite_beam, ModBlocks.calcite_slab.get(), false);

        convertPoleBeam(consumer, ModBlocks.iron_pole, ModBlocks.iron_beam);
        convertPoleBeam(consumer, ModBlocks.gold_pole, ModBlocks.gold_beam);
        convertPoleBeam(consumer, ModBlocks.diamond_pole, ModBlocks.diamond_beam);
        convertPoleBeam(consumer, ModBlocks.emerald_pole, ModBlocks.emerald_beam);
        convertPoleBeam(consumer, ModBlocks.lapis_pole, ModBlocks.lapis_beam);
        convertPoleBeam(consumer, ModBlocks.obsidian_pole, ModBlocks.obsidian_beam);
        convertPoleBeam(consumer, ModBlocks.coal_pole, ModBlocks.coal_beam);
        convertPoleBeam(consumer, ModBlocks.redstone_pole, ModBlocks.redstone_beam);
        convertPoleBeam(consumer, ModBlocks.clay_pole, ModBlocks.clay_beam);
        convertPoleBeam(consumer, ModBlocks.dirt_pole, ModBlocks.dirt_beam);
        convertPoleBeam(consumer, ModBlocks.grass_pole, ModBlocks.grass_beam);
        convertPoleBeam(consumer, ModBlocks.hay_pole, ModBlocks.hay_beam);
        convertPoleBeam(consumer, ModBlocks.path_pole, ModBlocks.path_beam);
        convertPoleBeam(consumer, ModBlocks.brick_pole, ModBlocks.brick_beam);
        convertPoleBeam(consumer, ModBlocks.quartz_pole, ModBlocks.quartz_beam);
        convertPoleBeam(consumer, ModBlocks.bone_pole, ModBlocks.bone_beam);
        convertPoleBeam(consumer, ModBlocks.nether_brick_pole, ModBlocks.nether_brick_beam);
        convertPoleBeam(consumer, ModBlocks.red_nether_brick_pole, ModBlocks.red_nether_brick_beam);
        convertPoleBeam(consumer, ModBlocks.snow_pole, ModBlocks.snow_beam);
        convertPoleBeam(consumer, ModBlocks.ice_pole, ModBlocks.ice_beam);
        convertPoleBeam(consumer, ModBlocks.packed_ice_pole, ModBlocks.packed_ice_beam);
        convertPoleBeam(consumer, ModBlocks.blue_ice_pole, ModBlocks.blue_ice_beam);
        convertPoleBeam(consumer, ModBlocks.netherite_pole, ModBlocks.netherite_beam);
        convertPoleBeam(consumer, ModBlocks.amethyst_pole, ModBlocks.amethyst_beam);
        convertPoleBeam(consumer, ModBlocks.copper_pole, ModBlocks.copper_beam);
        convertPoleBeam(consumer, ModBlocks.exposed_copper_pole, ModBlocks.exposed_copper_beam);
        convertPoleBeam(consumer, ModBlocks.weathered_copper_pole, ModBlocks.weathered_copper_beam);
        convertPoleBeam(consumer, ModBlocks.oxidized_copper_pole, ModBlocks.oxidized_copper_beam);
        convertPoleBeam(consumer, ModBlocks.magma_pole, ModBlocks.magma_beam);
        convertPoleBeam(consumer, ModBlocks.calcite_pole, ModBlocks.calcite_beam);

        wall(consumer, ModBlocks.iron_wall, Blocks.IRON_BLOCK, false);
        wall(consumer, ModBlocks.gold_wall, Blocks.GOLD_BLOCK, false);
        wall(consumer, ModBlocks.diamond_wall, Blocks.DIAMOND_BLOCK, false);
        wall(consumer, ModBlocks.emerald_wall, Blocks.EMERALD_BLOCK, false);
        wall(consumer, ModBlocks.lapis_wall, Blocks.LAPIS_BLOCK, false);
        wall(consumer, ModBlocks.obsidian_wall, Blocks.OBSIDIAN, false);
        wall(consumer, ModBlocks.coal_wall, Blocks.COAL_BLOCK, false);
        wall(consumer, ModBlocks.redstone_wall, Blocks.REDSTONE_BLOCK, false);
        wall(consumer, ModBlocks.clay_wall, Blocks.CLAY, false);
        wall(consumer, ModBlocks.dirt_wall, Blocks.DIRT, false);
        wall(consumer, ModBlocks.grass_wall, Blocks.GRASS_BLOCK, false);
        wall(consumer, ModBlocks.hay_wall, Blocks.HAY_BLOCK, false);
        wall(consumer, ModBlocks.path_wall, Blocks.DIRT_PATH, false);
        wall(consumer, ModBlocks.quartz_wall, Blocks.QUARTZ_BLOCK, false);
        wall(consumer, ModBlocks.bone_wall, Blocks.BONE_BLOCK, false);
        wall(consumer, ModBlocks.snow_wall, Blocks.SNOW_BLOCK, false);
        wall(consumer, ModBlocks.ice_wall, Blocks.ICE, false);
        wall(consumer, ModBlocks.packed_ice_wall, Blocks.PACKED_ICE, false);
        wall(consumer, ModBlocks.blue_ice_wall, Blocks.BLUE_ICE, false);
        wall(consumer, ModBlocks.netherite_wall, Blocks.NETHERITE_BLOCK, false);
        wall(consumer, ModBlocks.amethyst_wall, Blocks.AMETHYST_BLOCK, false);
        wall(consumer, ModBlocks.copper_wall, Blocks.COPPER_BLOCK, false);
        wall(consumer, ModBlocks.exposed_copper_wall, Blocks.EXPOSED_COPPER, false);
        wall(consumer, ModBlocks.weathered_copper_wall, Blocks.WEATHERED_COPPER, false);
        wall(consumer, ModBlocks.oxidized_copper_wall, Blocks.OXIDIZED_COPPER, false);
        wall(consumer, ModBlocks.magma_wall, Blocks.MAGMA_BLOCK, false);
        wall(consumer, ModBlocks.calcite_wall, Blocks.CALCITE, false);

        saddleDoor(consumer, ModBlocks.iron_saddle_door, Blocks.IRON_TRAPDOOR, false);
        saddleDoor(consumer, ModBlocks.gold_saddle_door, ModBlocks.gold_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.diamond_saddle_door, ModBlocks.diamond_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.emerald_saddle_door, ModBlocks.emerald_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.lapis_saddle_door, ModBlocks.lapis_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.obsidian_saddle_door, ModBlocks.obsidian_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.coal_saddle_door, ModBlocks.coal_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.redstone_saddle_door, ModBlocks.redstone_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.clay_saddle_door, ModBlocks.clay_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.dirt_saddle_door, ModBlocks.dirt_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.grass_saddle_door, ModBlocks.grass_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.hay_saddle_door, ModBlocks.hay_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.path_saddle_door, ModBlocks.path_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.brick_saddle_door, ModBlocks.brick_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.quartz_saddle_door, ModBlocks.quartz_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.bone_saddle_door, ModBlocks.bone_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.nether_brick_saddle_door, ModBlocks.nether_brick_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.red_nether_brick_saddle_door, ModBlocks.red_nether_brick_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.snow_saddle_door, ModBlocks.snow_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.ice_saddle_door, ModBlocks.ice_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.packed_ice_saddle_door, ModBlocks.packed_ice_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.blue_ice_saddle_door, ModBlocks.blue_ice_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.netherite_saddle_door, ModBlocks.netherite_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.amethyst_saddle_door, ModBlocks.amethyst_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.copper_saddle_door, ModBlocks.copper_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.exposed_copper_saddle_door, ModBlocks.exposed_copper_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.weathered_copper_saddle_door, ModBlocks.weathered_copper_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.oxidized_copper_saddle_door, ModBlocks.oxidized_copper_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.magma_saddle_door, ModBlocks.magma_trapdoor.get(), false);
        saddleDoor(consumer, ModBlocks.calcite_saddle_door, ModBlocks.calcite_trapdoor.get(), false);

        saddleDoorFromDoor(consumer, ModBlocks.iron_saddle_door, Blocks.IRON_DOOR);
        saddleDoorFromDoor(consumer, ModBlocks.gold_saddle_door, ModBlocks.gold_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.diamond_saddle_door, ModBlocks.diamond_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.emerald_saddle_door, ModBlocks.emerald_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.lapis_saddle_door, ModBlocks.lapis_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.obsidian_saddle_door, ModBlocks.obsidian_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.coal_saddle_door, ModBlocks.coal_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.redstone_saddle_door, ModBlocks.redstone_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.clay_saddle_door, ModBlocks.clay_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.dirt_saddle_door, ModBlocks.dirt_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.grass_saddle_door, ModBlocks.grass_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.hay_saddle_door, ModBlocks.hay_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.path_saddle_door, ModBlocks.path_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.brick_saddle_door, ModBlocks.brick_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.quartz_saddle_door, ModBlocks.quartz_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.bone_saddle_door, ModBlocks.bone_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.nether_brick_saddle_door, ModBlocks.nether_brick_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.red_nether_brick_saddle_door, ModBlocks.red_nether_brick_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.snow_saddle_door, ModBlocks.snow_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.ice_saddle_door, ModBlocks.ice_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.packed_ice_saddle_door, ModBlocks.packed_ice_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.blue_ice_saddle_door, ModBlocks.blue_ice_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.netherite_saddle_door, ModBlocks.netherite_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.amethyst_saddle_door, ModBlocks.amethyst_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.copper_saddle_door, ModBlocks.copper_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.exposed_copper_saddle_door, ModBlocks.exposed_copper_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.weathered_copper_saddle_door, ModBlocks.weathered_copper_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.oxidized_copper_saddle_door, ModBlocks.oxidized_copper_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.magma_saddle_door, ModBlocks.magma_door.get());
        saddleDoorFromDoor(consumer, ModBlocks.calcite_saddle_door, ModBlocks.calcite_door.get());
    }
}