package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalRecipeProvider;
import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class OrnamentalRecipes extends OrnamentalRecipeProvider {

    public OrnamentalRecipes(DataGenerator generator) {
        super(generator, OrnamentalMod.MODID);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        stairs(consumer, ModBlocks.iron_stairs, Blocks.IRON_BLOCK);
        stairs(consumer, ModBlocks.gold_stairs, Blocks.GOLD_BLOCK);
        stairs(consumer, ModBlocks.diamond_stairs, Blocks.DIAMOND_BLOCK);
        stairs(consumer, ModBlocks.emerald_stairs, Blocks.EMERALD_BLOCK);
        stairs(consumer, ModBlocks.lapis_stairs, Blocks.LAPIS_BLOCK);
        stairs(consumer, ModBlocks.obsidian_stairs, Blocks.OBSIDIAN);
        stairs(consumer, ModBlocks.coal_stairs, Blocks.COAL_BLOCK);
        stairs(consumer, ModBlocks.redstone_stairs, Blocks.REDSTONE_BLOCK);
        stairs(consumer, ModBlocks.clay_stairs, Blocks.CLAY);
        stairs(consumer, ModBlocks.dirt_stairs, Blocks.DIRT);
        stairs(consumer, ModBlocks.grass_stairs, Blocks.GRASS_BLOCK);
        stairs(consumer, ModBlocks.hay_stairs, Blocks.HAY_BLOCK);
        stairs(consumer, ModBlocks.path_stairs, Blocks.DIRT_PATH);
        stairs(consumer, ModBlocks.bone_stairs, Blocks.BONE_BLOCK);
        stairs(consumer, ModBlocks.snow_stairs, Blocks.SNOW_BLOCK);
        stairs(consumer, ModBlocks.ice_stairs, Blocks.ICE);
        stairs(consumer, ModBlocks.packed_ice_stairs, Blocks.PACKED_ICE);
        stairs(consumer, ModBlocks.blue_ice_stairs, Blocks.BLUE_ICE);
        stairs(consumer, ModBlocks.netherite_stairs, Blocks.NETHERITE_BLOCK);
        stairs(consumer, ModBlocks.amethyst_stairs, Blocks.AMETHYST_BLOCK);
        stairs(consumer, ModBlocks.copper_stairs, Blocks.COPPER_BLOCK);
        stairs(consumer, ModBlocks.exposed_copper_stairs, Blocks.EXPOSED_COPPER);
        stairs(consumer, ModBlocks.weathered_copper_stairs, Blocks.WEATHERED_COPPER);
        stairs(consumer, ModBlocks.oxidized_copper_stairs, Blocks.OXIDIZED_COPPER);

        slab(consumer, ModBlocks.iron_slab, Blocks.IRON_BLOCK);
        slab(consumer, ModBlocks.gold_slab, Blocks.GOLD_BLOCK);
        slab(consumer, ModBlocks.diamond_slab, Blocks.DIAMOND_BLOCK);
        slab(consumer, ModBlocks.emerald_slab, Blocks.EMERALD_BLOCK);
        slab(consumer, ModBlocks.lapis_slab, Blocks.LAPIS_BLOCK);
        slab(consumer, ModBlocks.obsidian_slab, Blocks.OBSIDIAN);
        slab(consumer, ModBlocks.coal_slab, Blocks.COAL_BLOCK);
        slab(consumer, ModBlocks.redstone_slab, Blocks.REDSTONE_BLOCK);
        slab(consumer, ModBlocks.clay_slab, Blocks.CLAY);
        slab(consumer, ModBlocks.dirt_slab, Blocks.DIRT);
        slab(consumer, ModBlocks.grass_slab, Blocks.GRASS_BLOCK);
        slab(consumer, ModBlocks.hay_slab, Blocks.HAY_BLOCK);
        slab(consumer, ModBlocks.path_slab, Blocks.DIRT_PATH);
        slab(consumer, ModBlocks.bone_slab, Blocks.BONE_BLOCK);
        slabOverride(consumer, ModBlocks.snow_slab, Blocks.SNOW_BLOCK);
        slab(consumer, ModBlocks.ice_slab, Blocks.ICE);
        slab(consumer, ModBlocks.packed_ice_slab, Blocks.PACKED_ICE);
        slab(consumer, ModBlocks.blue_ice_slab, Blocks.BLUE_ICE);
        slab(consumer, ModBlocks.netherite_slab, Blocks.NETHERITE_BLOCK);
        slab(consumer, ModBlocks.amethyst_slab, Blocks.AMETHYST_BLOCK);
        slab(consumer, ModBlocks.copper_slab, Blocks.COPPER_BLOCK);
        slab(consumer, ModBlocks.exposed_copper_slab, Blocks.EXPOSED_COPPER);
        slab(consumer, ModBlocks.weathered_copper_slab, Blocks.WEATHERED_COPPER);
        slab(consumer, ModBlocks.oxidized_copper_slab, Blocks.OXIDIZED_COPPER);

        fence(consumer, ModBlocks.iron_fence, Blocks.IRON_BLOCK, Items.IRON_INGOT);
        fence(consumer, ModBlocks.gold_fence, Blocks.GOLD_BLOCK, Items.GOLD_INGOT);
        fence(consumer, ModBlocks.diamond_fence, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        fence(consumer, ModBlocks.emerald_fence, Blocks.EMERALD_BLOCK, Items.EMERALD);
        fence(consumer, ModBlocks.lapis_fence, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI);
        fence(consumer, ModBlocks.obsidian_fence, Blocks.OBSIDIAN, ModBlocks.obsidian_slab);
        fence(consumer, ModBlocks.coal_fence, Blocks.COAL_BLOCK, Items.COAL);
        fence(consumer, ModBlocks.redstone_fence, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        fence(consumer, ModBlocks.clay_fence, Blocks.CLAY, Items.CLAY_BALL);
        fence(consumer, ModBlocks.dirt_fence, Blocks.DIRT, ModBlocks.dirt_slab);
        fence(consumer, ModBlocks.grass_fence, Blocks.GRASS_BLOCK, ModBlocks.grass_slab);
        fence(consumer, ModBlocks.hay_fence, Blocks.HAY_BLOCK, Items.WHEAT);
        fence(consumer, ModBlocks.path_fence, Blocks.DIRT_PATH, ModBlocks.path_slab);
        fence(consumer, ModBlocks.brick_fence, Blocks.BRICKS, Items.BRICK);
        fence(consumer, ModBlocks.quartz_fence, Blocks.QUARTZ_BLOCK, Items.QUARTZ);
        fence(consumer, ModBlocks.bone_fence, Blocks.BONE_BLOCK, Items.BONE_MEAL);
        fence(consumer, ModBlocks.red_nether_brick_fence, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART);
        fence(consumer, ModBlocks.snow_fence, Blocks.SNOW_BLOCK, Items.SNOWBALL);
        fence(consumer, ModBlocks.ice_fence, Blocks.ICE, ModBlocks.ice_slab);
        fence(consumer, ModBlocks.packed_ice_fence, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab);
        fence(consumer, ModBlocks.blue_ice_fence, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab);
        fence(consumer, ModBlocks.netherite_fence, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT);
        fence(consumer, ModBlocks.amethyst_fence, Blocks.AMETHYST_BLOCK, Items.AMETHYST_SHARD);
        fence(consumer, ModBlocks.copper_fence, Blocks.COPPER_BLOCK, Items.COPPER_INGOT);
        fence(consumer, ModBlocks.exposed_copper_fence, Blocks.EXPOSED_COPPER, ModBlocks.exposed_copper_slab);
        fence(consumer, ModBlocks.weathered_copper_fence, Blocks.WEATHERED_COPPER, ModBlocks.weathered_copper_slab);
        fence(consumer, ModBlocks.oxidized_copper_fence, Blocks.OXIDIZED_COPPER, ModBlocks.oxidized_copper_slab);

        trapdoor(consumer, ModBlocks.gold_trapdoor, Items.GOLD_INGOT);
        trapdoor(consumer, ModBlocks.diamond_trapdoor, Items.DIAMOND);
        trapdoor(consumer, ModBlocks.emerald_trapdoor, Items.EMERALD);
        trapdoor(consumer, ModBlocks.lapis_trapdoor, Items.LAPIS_LAZULI);
        trapdoor(consumer, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_slab);
        trapdoorWide(consumer, ModBlocks.coal_trapdoor, Items.COAL);
        trapdoorWide(consumer, ModBlocks.redstone_trapdoor, Items.REDSTONE);
        trapdoorWide(consumer, ModBlocks.clay_trapdoor, Items.CLAY);
        trapdoorWide(consumer, ModBlocks.dirt_trapdoor, ModBlocks.dirt_slab);
        trapdoorWide(consumer, ModBlocks.grass_trapdoor, ModBlocks.grass_slab);
        trapdoorWide(consumer, ModBlocks.hay_trapdoor, Items.HAY_BLOCK);
        trapdoorWide(consumer, ModBlocks.path_trapdoor, ModBlocks.path_slab);
        trapdoorWide(consumer, ModBlocks.brick_trapdoor, Items.BRICK);
        trapdoorWide(consumer, ModBlocks.quartz_trapdoor, Items.QUARTZ);
        trapdoorWide(consumer, ModBlocks.bone_trapdoor, Items.BONE_MEAL);
        trapdoorWide(consumer, ModBlocks.nether_brick_trapdoor, Items.NETHER_BRICK);
        trapdoorWide(consumer, ModBlocks.red_nether_brick_trapdoor, Items.NETHER_WART);
        trapdoorWide(consumer, ModBlocks.snow_trapdoor, Items.SNOWBALL);
        trapdoorWide(consumer, ModBlocks.ice_trapdoor, ModBlocks.ice_slab);
        trapdoorWide(consumer, ModBlocks.packed_ice_trapdoor, ModBlocks.packed_ice_slab);
        trapdoorWide(consumer, ModBlocks.blue_ice_trapdoor, ModBlocks.blue_ice_slab);
        trapdoor(consumer, ModBlocks.netherite_trapdoor, Items.NETHERITE_INGOT);
        trapdoorWide(consumer, ModBlocks.amethyst_trapdoor, Items.AMETHYST_SHARD);
        trapdoor(consumer, ModBlocks.copper_trapdoor, Items.COPPER_INGOT);
        trapdoor(consumer, ModBlocks.exposed_copper_trapdoor, ModBlocks.exposed_copper_slab);
        trapdoor(consumer, ModBlocks.weathered_copper_trapdoor, ModBlocks.weathered_copper_slab);
        trapdoor(consumer, ModBlocks.oxidized_copper_trapdoor, ModBlocks.oxidized_copper_slab);

        fencegate(consumer, ModBlocks.iron_fence_gate, Blocks.IRON_BLOCK, Items.IRON_INGOT);
        fencegate(consumer, ModBlocks.gold_fence_gate, Blocks.GOLD_BLOCK, Items.GOLD_INGOT);
        fencegate(consumer, ModBlocks.diamond_fence_gate, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        fencegate(consumer, ModBlocks.emerald_fence_gate, Blocks.EMERALD_BLOCK, Items.EMERALD);
        fencegate(consumer, ModBlocks.lapis_fence_gate, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI);
        fencegate(consumer, ModBlocks.obsidian_fence_gate, Blocks.OBSIDIAN, ModBlocks.obsidian_slab);
        fencegate(consumer, ModBlocks.coal_fence_gate, Blocks.COAL_BLOCK, Items.COAL);
        fencegate(consumer, ModBlocks.redstone_fence_gate, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        fencegate(consumer, ModBlocks.clay_fence_gate, Blocks.CLAY, Items.CLAY_BALL);
        fencegate(consumer, ModBlocks.dirt_fence_gate, Blocks.DIRT, ModBlocks.dirt_slab);
        fencegate(consumer, ModBlocks.grass_fence_gate, Blocks.GRASS_BLOCK, ModBlocks.grass_slab);
        fencegate(consumer, ModBlocks.hay_fence_gate, Blocks.HAY_BLOCK, Items.WHEAT);
        fencegate(consumer, ModBlocks.path_fence_gate, Blocks.DIRT_PATH, ModBlocks.path_slab);
        fencegate(consumer, ModBlocks.brick_fence_gate, Blocks.BRICKS, Items.BRICK);
        fencegate(consumer, ModBlocks.quartz_fence_gate, Blocks.QUARTZ_BLOCK, Items.QUARTZ);
        fencegate(consumer, ModBlocks.bone_fence_gate, Blocks.BONE_BLOCK, Items.BONE_MEAL);
        fencegate(consumer, ModBlocks.nether_brick_fence_gate, Blocks.NETHER_BRICKS, Items.NETHER_BRICK);
        fencegate(consumer, ModBlocks.red_nether_brick_fence_gate, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART);
        fencegate(consumer, ModBlocks.snow_fence_gate, Blocks.SNOW_BLOCK, Items.SNOWBALL);
        fencegate(consumer, ModBlocks.ice_fence_gate, Blocks.ICE, ModBlocks.ice_slab);
        fencegate(consumer, ModBlocks.packed_ice_fence_gate, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab);
        fencegate(consumer, ModBlocks.blue_ice_fence_gate, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab);
        fencegate(consumer, ModBlocks.netherite_fence_gate, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT);
        fencegate(consumer, ModBlocks.amethyst_fence_gate, Blocks.AMETHYST_BLOCK, Items.AMETHYST_SHARD);
        fencegate(consumer, ModBlocks.copper_fence_gate, Blocks.COPPER_BLOCK, Items.COPPER_INGOT);
        fencegate(consumer, ModBlocks.exposed_copper_fence_gate, Blocks.EXPOSED_COPPER, ModBlocks.exposed_copper_slab);
        fencegate(consumer, ModBlocks.weathered_copper_fence_gate, Blocks.WEATHERED_COPPER, ModBlocks.weathered_copper_slab);
        fencegate(consumer, ModBlocks.oxidized_copper_fence_gate, Blocks.OXIDIZED_COPPER, ModBlocks.oxidized_copper_slab);

        door(consumer, ModBlocks.gold_door, Items.GOLD_INGOT);
        door(consumer, ModBlocks.diamond_door, Items.DIAMOND);
        door(consumer, ModBlocks.emerald_door, Items.EMERALD);
        door(consumer, ModBlocks.lapis_door, Items.LAPIS_LAZULI);
        door(consumer, ModBlocks.obsidian_door, ModBlocks.obsidian_slab);
        door(consumer, ModBlocks.coal_door, Items.COAL);
        door(consumer, ModBlocks.redstone_door, Items.REDSTONE);
        door(consumer, ModBlocks.clay_door, Items.CLAY);
        door(consumer, ModBlocks.dirt_door, ModBlocks.dirt_slab);
        door(consumer, ModBlocks.grass_door, ModBlocks.grass_slab);
        door(consumer, ModBlocks.hay_door, Items.WHEAT);
        door(consumer, ModBlocks.path_door, ModBlocks.path_slab);
        door(consumer, ModBlocks.brick_door, Items.BRICK);
        door(consumer, ModBlocks.quartz_door, Items.QUARTZ);
        door(consumer, ModBlocks.bone_door, Items.BONE_MEAL);
        door(consumer, ModBlocks.nether_brick_door, Items.NETHER_BRICK);
        door(consumer, ModBlocks.red_nether_brick_door, Items.NETHER_WART);
        door(consumer, ModBlocks.snow_door, Items.SNOWBALL);
        door(consumer, ModBlocks.ice_door, ModBlocks.ice_slab);
        door(consumer, ModBlocks.packed_ice_door, ModBlocks.packed_ice_slab);
        door(consumer, ModBlocks.blue_ice_door, ModBlocks.blue_ice_slab);
        door(consumer, ModBlocks.netherite_door, Items.NETHERITE_INGOT);
        door(consumer, ModBlocks.amethyst_door, Items.AMETHYST_SHARD);
        door(consumer, ModBlocks.copper_door, Items.COPPER_INGOT);
        door(consumer, ModBlocks.exposed_copper_door, ModBlocks.exposed_copper_slab);
        door(consumer, ModBlocks.weathered_copper_door, ModBlocks.weathered_copper_slab);
        door(consumer, ModBlocks.oxidized_copper_door, ModBlocks.oxidized_copper_slab);

        pole(consumer, ModBlocks.iron_pole, ModBlocks.iron_slab);
        pole(consumer, ModBlocks.gold_pole, ModBlocks.gold_slab);
        pole(consumer, ModBlocks.diamond_pole, ModBlocks.diamond_slab);
        pole(consumer, ModBlocks.emerald_pole, ModBlocks.emerald_slab);
        pole(consumer, ModBlocks.lapis_pole, ModBlocks.lapis_slab);
        pole(consumer, ModBlocks.obsidian_pole, ModBlocks.obsidian_slab);
        pole(consumer, ModBlocks.coal_pole, ModBlocks.coal_slab);
        pole(consumer, ModBlocks.redstone_pole, ModBlocks.redstone_slab);
        pole(consumer, ModBlocks.clay_pole, ModBlocks.clay_slab);
        pole(consumer, ModBlocks.dirt_pole, ModBlocks.dirt_slab);
        pole(consumer, ModBlocks.grass_pole, ModBlocks.grass_slab);
        pole(consumer, ModBlocks.hay_pole, ModBlocks.hay_slab);
        pole(consumer, ModBlocks.path_pole, ModBlocks.path_slab);
        pole(consumer, ModBlocks.brick_pole, Blocks.BRICK_SLAB);
        pole(consumer, ModBlocks.quartz_pole, Blocks.QUARTZ_SLAB);
        pole(consumer, ModBlocks.bone_pole, ModBlocks.bone_slab);
        pole(consumer, ModBlocks.nether_brick_pole, Blocks.NETHER_BRICK_SLAB);
        pole(consumer, ModBlocks.red_nether_brick_pole, Blocks.RED_NETHER_BRICK_SLAB);
        pole(consumer, ModBlocks.snow_pole, ModBlocks.snow_slab);
        pole(consumer, ModBlocks.ice_pole, ModBlocks.ice_slab);
        pole(consumer, ModBlocks.packed_ice_pole, ModBlocks.packed_ice_slab);
        pole(consumer, ModBlocks.blue_ice_pole, ModBlocks.blue_ice_slab);
        pole(consumer, ModBlocks.netherite_pole, ModBlocks.netherite_slab);
        pole(consumer, ModBlocks.amethyst_pole, Items.AMETHYST_SHARD);
        pole(consumer, ModBlocks.copper_pole, ModBlocks.copper_slab);
        pole(consumer, ModBlocks.exposed_copper_pole, ModBlocks.exposed_copper_slab);
        pole(consumer, ModBlocks.weathered_copper_pole, ModBlocks.weathered_copper_slab);
        pole(consumer, ModBlocks.oxidized_copper_pole, ModBlocks.oxidized_copper_slab);

        beam(consumer, ModBlocks.iron_beam, ModBlocks.iron_slab);
        beam(consumer, ModBlocks.gold_beam, ModBlocks.gold_slab);
        beam(consumer, ModBlocks.diamond_beam, ModBlocks.diamond_slab);
        beam(consumer, ModBlocks.emerald_beam, ModBlocks.emerald_slab);
        beam(consumer, ModBlocks.lapis_beam, ModBlocks.lapis_slab);
        beam(consumer, ModBlocks.obsidian_beam, ModBlocks.obsidian_slab);
        beam(consumer, ModBlocks.coal_beam, ModBlocks.coal_slab);
        beam(consumer, ModBlocks.redstone_beam, ModBlocks.redstone_slab);
        beam(consumer, ModBlocks.clay_beam, ModBlocks.clay_slab);
        beam(consumer, ModBlocks.dirt_beam, ModBlocks.dirt_slab);
        beam(consumer, ModBlocks.grass_beam, ModBlocks.grass_slab);
        beam(consumer, ModBlocks.hay_beam, ModBlocks.hay_slab);
        beam(consumer, ModBlocks.path_beam, ModBlocks.path_slab);
        beam(consumer, ModBlocks.brick_beam, Blocks.BRICK_SLAB);
        beam(consumer, ModBlocks.quartz_beam, Blocks.QUARTZ_SLAB);
        beam(consumer, ModBlocks.bone_beam, ModBlocks.bone_slab);
        beam(consumer, ModBlocks.nether_brick_beam, Blocks.NETHER_BRICK_SLAB);
        beam(consumer, ModBlocks.red_nether_brick_beam, Blocks.RED_NETHER_BRICK_SLAB);
        beam(consumer, ModBlocks.snow_beam, ModBlocks.snow_slab);
        beam(consumer, ModBlocks.ice_beam, ModBlocks.ice_slab);
        beam(consumer, ModBlocks.packed_ice_beam, ModBlocks.packed_ice_slab);
        beam(consumer, ModBlocks.blue_ice_beam, ModBlocks.blue_ice_slab);
        beam(consumer, ModBlocks.netherite_beam, ModBlocks.netherite_slab);
        beam(consumer, ModBlocks.amethyst_beam, Items.AMETHYST_SHARD);
        beam(consumer, ModBlocks.copper_beam, ModBlocks.copper_slab);
        beam(consumer, ModBlocks.exposed_copper_beam, ModBlocks.exposed_copper_slab);
        beam(consumer, ModBlocks.weathered_copper_beam, ModBlocks.weathered_copper_slab);
        beam(consumer, ModBlocks.oxidized_copper_beam, ModBlocks.oxidized_copper_slab);

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

        wall(consumer, ModBlocks.iron_wall, Blocks.IRON_BLOCK);
        wall(consumer, ModBlocks.gold_wall, Blocks.GOLD_BLOCK);
        wall(consumer, ModBlocks.diamond_wall, Blocks.DIAMOND_BLOCK);
        wall(consumer, ModBlocks.emerald_wall, Blocks.EMERALD_BLOCK);
        wall(consumer, ModBlocks.lapis_wall, Blocks.LAPIS_BLOCK);
        wall(consumer, ModBlocks.obsidian_wall, Blocks.OBSIDIAN);
        wall(consumer, ModBlocks.coal_wall, Blocks.COAL_BLOCK);
        wall(consumer, ModBlocks.redstone_wall, Blocks.REDSTONE_BLOCK);
        wall(consumer, ModBlocks.clay_wall, Blocks.CLAY);
        wall(consumer, ModBlocks.dirt_wall, Blocks.DIRT);
        wall(consumer, ModBlocks.grass_wall, Blocks.GRASS_BLOCK);
        wall(consumer, ModBlocks.hay_wall, Blocks.HAY_BLOCK);
        wall(consumer, ModBlocks.path_wall, Blocks.DIRT_PATH);
        wall(consumer, ModBlocks.quartz_wall, Blocks.QUARTZ_BLOCK);
        wall(consumer, ModBlocks.bone_wall, Blocks.BONE_BLOCK);
        wall(consumer, ModBlocks.snow_wall, Blocks.SNOW_BLOCK);
        wall(consumer, ModBlocks.ice_wall, Blocks.ICE);
        wall(consumer, ModBlocks.packed_ice_wall, Blocks.PACKED_ICE);
        wall(consumer, ModBlocks.blue_ice_wall, Blocks.BLUE_ICE);
        wall(consumer, ModBlocks.netherite_wall, Blocks.NETHERITE_BLOCK);
        wall(consumer, ModBlocks.amethyst_wall, Blocks.AMETHYST_BLOCK);
        wall(consumer, ModBlocks.copper_wall, Blocks.COPPER_BLOCK);
        wall(consumer, ModBlocks.exposed_copper_wall, Blocks.EXPOSED_COPPER);
        wall(consumer, ModBlocks.weathered_copper_wall, Blocks.WEATHERED_COPPER);
        wall(consumer, ModBlocks.oxidized_copper_wall, Blocks.OXIDIZED_COPPER);
    }
}