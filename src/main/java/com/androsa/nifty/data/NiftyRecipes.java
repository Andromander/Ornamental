package com.androsa.nifty.data;

import com.androsa.nifty.*;
import com.androsa.nifty.data.provider.NiftyRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class NiftyRecipes extends NiftyRecipeProvider {

    public NiftyRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
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
        stairs(consumer, ModBlocks.path_stairs, Blocks.GRASS_PATH);
        stairs(consumer, ModBlocks.bone_stairs, Blocks.BONE_BLOCK);
        stairs(consumer, ModBlocks.snow_stairs, Blocks.SNOW_BLOCK);
        stairs(consumer, ModBlocks.ice_stairs, Blocks.ICE);
        stairs(consumer, ModBlocks.packed_ice_stairs, Blocks.PACKED_ICE);
        stairs(consumer, ModBlocks.blue_ice_stairs, Blocks.BLUE_ICE);
        stairs(consumer, ModBlocks.netherite_stairs, Blocks.NETHERITE_BLOCK);

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
        slab(consumer, ModBlocks.path_slab, Blocks.GRASS_PATH);
        slab(consumer, ModBlocks.bone_slab, Blocks.BONE_BLOCK);
        slab(consumer, ModBlocks.snow_slab, Blocks.SNOW_BLOCK);
        slab(consumer, ModBlocks.ice_slab, Blocks.ICE);
        slab(consumer, ModBlocks.packed_ice_slab, Blocks.PACKED_ICE);
        slab(consumer, ModBlocks.blue_ice_slab, Blocks.BLUE_ICE);
        slab(consumer, ModBlocks.netherite_slab, Blocks.NETHERITE_BLOCK);

        fence(consumer, ModBlocks.iron_fence, Blocks.IRON_BLOCK, Items.IRON_INGOT);
        fence(consumer, ModBlocks.gold_fence, Blocks.GOLD_BLOCK, Items.GOLD_INGOT);
        fence(consumer, ModBlocks.diamond_fence, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        fence(consumer, ModBlocks.emerald_fence, Blocks.EMERALD_BLOCK, Items.EMERALD);
        fence(consumer, ModBlocks.lapis_fence, Blocks.LAPIS_BLOCK, Items.LAPIS_BLOCK);
        fence(consumer, ModBlocks.obsidian_fence, Blocks.OBSIDIAN, ModBlocks.obsidian_slab);
        fence(consumer, ModBlocks.coal_fence, Blocks.COAL_BLOCK, Items.COAL);
        fence(consumer, ModBlocks.redstone_fence, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        fence(consumer, ModBlocks.clay_fence, Blocks.CLAY, Items.CLAY_BALL);
        fence(consumer, ModBlocks.dirt_fence, Blocks.DIRT, ModBlocks.dirt_slab);
        fence(consumer, ModBlocks.grass_fence, Blocks.GRASS_BLOCK, ModBlocks.grass_slab);
        fence(consumer, ModBlocks.hay_fence, Blocks.HAY_BLOCK, Items.WHEAT);
        fence(consumer, ModBlocks.path_fence, Blocks.GRASS_PATH, ModBlocks.path_slab);
        fence(consumer, ModBlocks.brick_fence, Blocks.BRICKS, Items.BRICK);
        fence(consumer, ModBlocks.quartz_fence, Blocks.QUARTZ_BLOCK, Items.QUARTZ);
        fence(consumer, ModBlocks.bone_fence, Blocks.BONE_BLOCK, Items.BONE_MEAL);
        fence(consumer, ModBlocks.red_nether_brick_fence, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART);
        fence(consumer, ModBlocks.snow_fence, Blocks.SNOW_BLOCK, Items.SNOWBALL);
        fence(consumer, ModBlocks.ice_fence, Blocks.ICE, ModBlocks.ice_slab);
        fence(consumer, ModBlocks.packed_ice_fence, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab);
        fence(consumer, ModBlocks.blue_ice_fence, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab);
        fence(consumer, ModBlocks.netherite_fence, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT);

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

        fencegate(consumer, ModBlocks.iron_fence_gate, Blocks.IRON_BLOCK, Items.IRON_INGOT);
        fencegate(consumer, ModBlocks.gold_fence_gate, Blocks.GOLD_BLOCK, Items.GOLD_INGOT);
        fencegate(consumer, ModBlocks.diamond_fence_gate, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        fencegate(consumer, ModBlocks.emerald_fence_gate, Blocks.EMERALD_BLOCK, Items.EMERALD);
        fencegate(consumer, ModBlocks.lapis_fence_gate, Blocks.LAPIS_BLOCK, Items.LAPIS_BLOCK);
        fencegate(consumer, ModBlocks.obsidian_fence_gate, Blocks.OBSIDIAN, ModBlocks.obsidian_slab);
        fencegate(consumer, ModBlocks.coal_fence_gate, Blocks.COAL_BLOCK, Items.COAL);
        fencegate(consumer, ModBlocks.redstone_fence_gate, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        fencegate(consumer, ModBlocks.clay_fence_gate, Blocks.CLAY, Items.CLAY_BALL);
        fencegate(consumer, ModBlocks.dirt_fence_gate, Blocks.DIRT, ModBlocks.dirt_slab);
        fencegate(consumer, ModBlocks.grass_fence_gate, Blocks.GRASS_BLOCK, ModBlocks.grass_slab);
        fencegate(consumer, ModBlocks.hay_fence_gate, Blocks.HAY_BLOCK, Items.WHEAT);
        fencegate(consumer, ModBlocks.path_fence_gate, Blocks.GRASS_PATH, ModBlocks.path_slab);
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
    }
}