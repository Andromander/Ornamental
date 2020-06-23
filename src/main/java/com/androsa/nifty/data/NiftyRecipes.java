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
        stairs(consumer, ModBlocks.iron_stairs, Blocks.IRON_BLOCK, NiftyBuilders.IRON);
        stairs(consumer, ModBlocks.gold_stairs, Blocks.GOLD_BLOCK, NiftyBuilders.GOLD);
        stairs(consumer, ModBlocks.diamond_stairs, Blocks.DIAMOND_BLOCK, NiftyBuilders.DIAMOND);
        stairs(consumer, ModBlocks.emerald_stairs, Blocks.EMERALD_BLOCK, NiftyBuilders.EMERALD);
        stairs(consumer, ModBlocks.lapis_stairs, Blocks.LAPIS_BLOCK, NiftyBuilders.LAPIS);
        stairs(consumer, ModBlocks.obsidian_stairs, Blocks.OBSIDIAN, NiftyBuilders.OBSIDIAN);
        stairs(consumer, ModBlocks.coal_stairs, Blocks.COAL_BLOCK, NiftyBuilders.COAL);
        stairs(consumer, ModBlocks.redstone_stairs, Blocks.REDSTONE_BLOCK, NiftyBuilders.REDSTONE);
        stairs(consumer, ModBlocks.clay_stairs, Blocks.CLAY, NiftyBuilders.CLAY);
        stairs(consumer, ModBlocks.dirt_stairs, Blocks.DIRT, NiftyBuilders.DIRT);
        stairs(consumer, ModBlocks.grass_stairs, Blocks.GRASS_BLOCK, NiftyBuilders.GRASS);
        stairs(consumer, ModBlocks.hay_stairs, Blocks.HAY_BLOCK, NiftyBuilders.HAY);
        stairs(consumer, ModBlocks.path_stairs, Blocks.GRASS_PATH, NiftyBuilders.PATH);
        stairs(consumer, ModBlocks.bone_stairs, Blocks.BONE_BLOCK, NiftyBuilders.BONE);
        stairs(consumer, ModBlocks.snow_stairs, Blocks.SNOW_BLOCK, NiftyBuilders.SNOW);
        stairs(consumer, ModBlocks.ice_stairs, Blocks.ICE, NiftyBuilders.ICE);
        stairs(consumer, ModBlocks.packed_ice_stairs, Blocks.PACKED_ICE, NiftyBuilders.PACKED_ICE);
        stairs(consumer, ModBlocks.blue_ice_stairs, Blocks.BLUE_ICE, NiftyBuilders.BLUE_ICE);

        slab(consumer, ModBlocks.iron_slab, Blocks.IRON_BLOCK, NiftyBuilders.IRON);
        slab(consumer, ModBlocks.gold_slab, Blocks.GOLD_BLOCK, NiftyBuilders.GOLD);
        slab(consumer, ModBlocks.diamond_slab, Blocks.DIAMOND_BLOCK, NiftyBuilders.DIAMOND);
        slab(consumer, ModBlocks.emerald_slab, Blocks.EMERALD_BLOCK, NiftyBuilders.EMERALD);
        slab(consumer, ModBlocks.lapis_slab, Blocks.LAPIS_BLOCK, NiftyBuilders.LAPIS);
        slab(consumer, ModBlocks.obsidian_slab, Blocks.OBSIDIAN, NiftyBuilders.OBSIDIAN);
        slab(consumer, ModBlocks.coal_slab, Blocks.COAL_BLOCK, NiftyBuilders.COAL);
        slab(consumer, ModBlocks.redstone_slab, Blocks.REDSTONE_BLOCK, NiftyBuilders.REDSTONE);
        slab(consumer, ModBlocks.clay_slab, Blocks.CLAY, NiftyBuilders.CLAY);
        slab(consumer, ModBlocks.dirt_slab, Blocks.DIRT, NiftyBuilders.DIRT);
        slab(consumer, ModBlocks.grass_slab, Blocks.GRASS_BLOCK, NiftyBuilders.GRASS);
        slab(consumer, ModBlocks.hay_slab, Blocks.HAY_BLOCK, NiftyBuilders.HAY);
        slab(consumer, ModBlocks.path_slab, Blocks.GRASS_PATH, NiftyBuilders.PATH);
        slab(consumer, ModBlocks.bone_slab, Blocks.BONE_BLOCK, NiftyBuilders.BONE);
        slab(consumer, ModBlocks.snow_slab, Blocks.SNOW_BLOCK, NiftyBuilders.SNOW);
        slab(consumer, ModBlocks.ice_slab, Blocks.ICE, NiftyBuilders.ICE);
        slab(consumer, ModBlocks.packed_ice_slab, Blocks.PACKED_ICE, NiftyBuilders.PACKED_ICE);
        slab(consumer, ModBlocks.blue_ice_slab, Blocks.BLUE_ICE, NiftyBuilders.BLUE_ICE);

        fence(consumer, ModBlocks.iron_fence, Blocks.IRON_BLOCK, Items.IRON_INGOT, NiftyBuilders.IRON);
        fence(consumer, ModBlocks.gold_fence, Blocks.GOLD_BLOCK, Items.GOLD_INGOT, NiftyBuilders.GOLD);
        fence(consumer, ModBlocks.diamond_fence, Blocks.DIAMOND_BLOCK, Items.DIAMOND, NiftyBuilders.DIAMOND);
        fence(consumer, ModBlocks.emerald_fence, Blocks.EMERALD_BLOCK, Items.EMERALD, NiftyBuilders.EMERALD);
        fence(consumer, ModBlocks.lapis_fence, Blocks.LAPIS_BLOCK, Items.LAPIS_BLOCK, NiftyBuilders.LAPIS);
        fence(consumer, ModBlocks.obsidian_fence, Blocks.OBSIDIAN, ModBlocks.obsidian_slab, NiftyBuilders.OBSIDIAN);
        fence(consumer, ModBlocks.coal_fence, Blocks.COAL_BLOCK, Items.COAL, NiftyBuilders.COAL);
        fence(consumer, ModBlocks.redstone_fence, Blocks.REDSTONE_BLOCK, Items.REDSTONE, NiftyBuilders.REDSTONE);
        fence(consumer, ModBlocks.clay_fence, Blocks.CLAY, Items.CLAY_BALL, NiftyBuilders.CLAY);
        fence(consumer, ModBlocks.dirt_fence, Blocks.DIRT, ModBlocks.dirt_slab, NiftyBuilders.DIRT);
        fence(consumer, ModBlocks.grass_fence, Blocks.GRASS_BLOCK, ModBlocks.grass_slab, NiftyBuilders.GRASS);
        fence(consumer, ModBlocks.hay_fence, Blocks.HAY_BLOCK, Items.WHEAT, NiftyBuilders.HAY);
        fence(consumer, ModBlocks.path_fence, Blocks.GRASS_PATH, ModBlocks.path_slab, NiftyBuilders.PATH);
        fence(consumer, ModBlocks.brick_fence, Blocks.BRICKS, Items.BRICK, NiftyBuilders.BRICK);
        fence(consumer, ModBlocks.quartz_fence, Blocks.QUARTZ_BLOCK, Items.QUARTZ, NiftyBuilders.QUARTZ);
        fence(consumer, ModBlocks.bone_fence, Blocks.BONE_BLOCK, Items.BONE_MEAL, NiftyBuilders.BONE);
        fence(consumer, ModBlocks.red_nether_brick_fence, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART, NiftyBuilders.RED_NETHER_BRICK);
        fence(consumer, ModBlocks.snow_fence, Blocks.SNOW_BLOCK, Items.SNOWBALL, NiftyBuilders.SNOW);
        fence(consumer, ModBlocks.ice_fence, Blocks.ICE, ModBlocks.ice_slab, NiftyBuilders.ICE);
        fence(consumer, ModBlocks.packed_ice_fence, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab, NiftyBuilders.PACKED_ICE);
        fence(consumer, ModBlocks.blue_ice_fence, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab, NiftyBuilders.BLUE_ICE);

        trapdoor(consumer, ModBlocks.gold_trapdoor, Items.GOLD_INGOT, NiftyBuilders.GOLD);
        trapdoor(consumer, ModBlocks.diamond_trapdoor, Items.DIAMOND, NiftyBuilders.DIAMOND);
        trapdoor(consumer, ModBlocks.emerald_trapdoor, Items.EMERALD, NiftyBuilders.EMERALD);
        trapdoor(consumer, ModBlocks.lapis_trapdoor, Items.LAPIS_LAZULI, NiftyBuilders.LAPIS);
        trapdoor(consumer, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_slab, NiftyBuilders.OBSIDIAN);
        trapdoorWide(consumer, ModBlocks.coal_trapdoor, Items.COAL, NiftyBuilders.COAL);
        trapdoorWide(consumer, ModBlocks.redstone_trapdoor, Items.REDSTONE, NiftyBuilders.REDSTONE);
        trapdoorWide(consumer, ModBlocks.clay_trapdoor, Items.CLAY, NiftyBuilders.CLAY);
        trapdoorWide(consumer, ModBlocks.dirt_trapdoor, ModBlocks.dirt_slab, NiftyBuilders.DIRT);
        trapdoorWide(consumer, ModBlocks.grass_trapdoor, ModBlocks.grass_slab, NiftyBuilders.GRASS);
        trapdoorWide(consumer, ModBlocks.hay_trapdoor, Items.HAY_BLOCK, NiftyBuilders.HAY);
        trapdoorWide(consumer, ModBlocks.path_trapdoor, ModBlocks.path_slab, NiftyBuilders.PATH);
        trapdoorWide(consumer, ModBlocks.brick_trapdoor, Items.BRICK, NiftyBuilders.BRICK);
        trapdoorWide(consumer, ModBlocks.quartz_trapdoor, Items.QUARTZ, NiftyBuilders.QUARTZ);
        trapdoorWide(consumer, ModBlocks.bone_trapdoor, Items.BONE_MEAL, NiftyBuilders.BONE);
        trapdoorWide(consumer, ModBlocks.nether_brick_trapdoor, Items.NETHER_BRICK, NiftyBuilders.NETHER_BRICK);
        trapdoorWide(consumer, ModBlocks.red_nether_brick_trapdoor, Items.NETHER_WART, NiftyBuilders.RED_NETHER_BRICK);
        trapdoorWide(consumer, ModBlocks.snow_trapdoor, Items.SNOWBALL, NiftyBuilders.SNOW);
        trapdoorWide(consumer, ModBlocks.ice_trapdoor, ModBlocks.ice_slab, NiftyBuilders.ICE);
        trapdoorWide(consumer, ModBlocks.packed_ice_trapdoor, ModBlocks.packed_ice_slab, NiftyBuilders.PACKED_ICE);
        trapdoorWide(consumer, ModBlocks.blue_ice_trapdoor, ModBlocks.blue_ice_slab, NiftyBuilders.BLUE_ICE);

        fencegate(consumer, ModBlocks.iron_fence_gate, Blocks.IRON_BLOCK, Items.IRON_INGOT, NiftyBuilders.IRON);
        fencegate(consumer, ModBlocks.gold_fence_gate, Blocks.GOLD_BLOCK, Items.GOLD_INGOT, NiftyBuilders.GOLD);
        fencegate(consumer, ModBlocks.diamond_fence_gate, Blocks.DIAMOND_BLOCK, Items.DIAMOND, NiftyBuilders.DIAMOND);
        fencegate(consumer, ModBlocks.emerald_fence_gate, Blocks.EMERALD_BLOCK, Items.EMERALD, NiftyBuilders.EMERALD);
        fencegate(consumer, ModBlocks.lapis_fence_gate, Blocks.LAPIS_BLOCK, Items.LAPIS_BLOCK, NiftyBuilders.LAPIS);
        fencegate(consumer, ModBlocks.obsidian_fence_gate, Blocks.OBSIDIAN, ModBlocks.obsidian_slab, NiftyBuilders.OBSIDIAN);
        fencegate(consumer, ModBlocks.coal_fence_gate, Blocks.COAL_BLOCK, Items.COAL, NiftyBuilders.COAL);
        fencegate(consumer, ModBlocks.redstone_fence_gate, Blocks.REDSTONE_BLOCK, Items.REDSTONE, NiftyBuilders.REDSTONE);
        fencegate(consumer, ModBlocks.clay_fence_gate, Blocks.CLAY, Items.CLAY_BALL, NiftyBuilders.CLAY);
        fencegate(consumer, ModBlocks.dirt_fence_gate, Blocks.DIRT, ModBlocks.dirt_slab, NiftyBuilders.DIRT);
        fencegate(consumer, ModBlocks.grass_fence_gate, Blocks.GRASS_BLOCK, ModBlocks.grass_slab, NiftyBuilders.GRASS);
        fencegate(consumer, ModBlocks.hay_fence_gate, Blocks.HAY_BLOCK, Items.WHEAT, NiftyBuilders.HAY);
        fencegate(consumer, ModBlocks.path_fence_gate, Blocks.GRASS_PATH, ModBlocks.path_slab, NiftyBuilders.PATH);
        fencegate(consumer, ModBlocks.brick_fence_gate, Blocks.BRICKS, Items.BRICK, NiftyBuilders.BRICK);
        fencegate(consumer, ModBlocks.quartz_fence_gate, Blocks.QUARTZ_BLOCK, Items.QUARTZ, NiftyBuilders.QUARTZ);
        fencegate(consumer, ModBlocks.bone_fence_gate, Blocks.BONE_BLOCK, Items.BONE_MEAL, NiftyBuilders.BONE);
        fencegate(consumer, ModBlocks.nether_brick_fence_gate, Blocks.NETHER_BRICKS, Items.NETHER_BRICK, NiftyBuilders.NETHER_BRICK);
        fencegate(consumer, ModBlocks.red_nether_brick_fence_gate, Blocks.RED_NETHER_BRICKS, Items.NETHER_WART, NiftyBuilders.RED_NETHER_BRICK);
        fencegate(consumer, ModBlocks.snow_fence_gate, Blocks.SNOW_BLOCK, Items.SNOWBALL, NiftyBuilders.SNOW);
        fencegate(consumer, ModBlocks.ice_fence_gate, Blocks.ICE, ModBlocks.ice_slab, NiftyBuilders.ICE);
        fencegate(consumer, ModBlocks.packed_ice_fence_gate, Blocks.PACKED_ICE, ModBlocks.packed_ice_slab, NiftyBuilders.PACKED_ICE);
        fencegate(consumer, ModBlocks.blue_ice_fence_gate, Blocks.BLUE_ICE, ModBlocks.blue_ice_slab, NiftyBuilders.BLUE_ICE);

        door(consumer, ModBlocks.gold_door, Items.GOLD_INGOT, NiftyBuilders.GOLD);
        door(consumer, ModBlocks.diamond_door, Items.DIAMOND, NiftyBuilders.DIAMOND);
        door(consumer, ModBlocks.emerald_door, Items.EMERALD, NiftyBuilders.EMERALD);
        door(consumer, ModBlocks.lapis_door, Items.LAPIS_LAZULI, NiftyBuilders.LAPIS);
        door(consumer, ModBlocks.obsidian_door, ModBlocks.obsidian_slab, NiftyBuilders.OBSIDIAN);
        door(consumer, ModBlocks.coal_door, Items.COAL, NiftyBuilders.COAL);
        door(consumer, ModBlocks.redstone_door, Items.REDSTONE, NiftyBuilders.REDSTONE);
        door(consumer, ModBlocks.clay_door, Items.CLAY, NiftyBuilders.CLAY);
        door(consumer, ModBlocks.dirt_door, ModBlocks.dirt_slab, NiftyBuilders.DIRT);
        door(consumer, ModBlocks.grass_door, ModBlocks.grass_slab, NiftyBuilders.GRASS);
        door(consumer, ModBlocks.hay_door, Items.WHEAT, NiftyBuilders.HAY);
        door(consumer, ModBlocks.path_door, ModBlocks.path_slab, NiftyBuilders.PATH);
        door(consumer, ModBlocks.brick_door, Items.BRICK, NiftyBuilders.BRICK);
        door(consumer, ModBlocks.quartz_door, Items.QUARTZ, NiftyBuilders.QUARTZ);
        door(consumer, ModBlocks.bone_door, Items.BONE_MEAL, NiftyBuilders.BONE);
        door(consumer, ModBlocks.nether_brick_door, Items.NETHER_BRICK, NiftyBuilders.NETHER_BRICK);
        door(consumer, ModBlocks.red_nether_brick_door, Items.NETHER_WART, NiftyBuilders.RED_NETHER_BRICK);
        door(consumer, ModBlocks.snow_door, Items.SNOWBALL, NiftyBuilders.SNOW);
        door(consumer, ModBlocks.ice_door, ModBlocks.ice_slab, NiftyBuilders.ICE);
        door(consumer, ModBlocks.packed_ice_door, ModBlocks.packed_ice_slab, NiftyBuilders.PACKED_ICE);
        door(consumer, ModBlocks.blue_ice_door, ModBlocks.blue_ice_slab, NiftyBuilders.BLUE_ICE);
    }
}