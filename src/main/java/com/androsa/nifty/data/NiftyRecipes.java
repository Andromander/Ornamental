package com.androsa.nifty.data;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.NiftyMod;
import com.androsa.nifty.data.provider.NiftyRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Consumer;

public class NiftyRecipes extends NiftyRecipeProvider {

    public NiftyRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        stairsRecipe(getItem(ModBlocks.iron_stairs), getItem(Blocks.IRON_BLOCK), "iron", NiftyBlock.IRON)
                .build(consumer, loc("iron_stairs"));
        stairsRecipe(getItem(ModBlocks.gold_stairs), getItem(Blocks.GOLD_BLOCK), "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_stairs"));
        stairsRecipe(getItem(ModBlocks.diamond_stairs), getItem(Blocks.DIAMOND_BLOCK), "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_stairs"));
        stairsRecipe(getItem(ModBlocks.emerald_stairs), getItem(Blocks.EMERALD_BLOCK), "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_stairs"));
        stairsRecipe(getItem(ModBlocks.lapis_stairs), getItem(Blocks.LAPIS_BLOCK), "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_stairs"));
        stairsRecipe(getItem(ModBlocks.obsidian_stairs), getItem(Blocks.OBSIDIAN), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_stairs"));
        stairsRecipe(getItem(ModBlocks.coal_stairs), getItem(Blocks.COAL_BLOCK), "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_stairs"));
        stairsRecipe(getItem(ModBlocks.redstone_stairs), getItem(Blocks.REDSTONE_BLOCK), "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_stairs"));
        stairsRecipe(getItem(ModBlocks.clay_stairs), getItem(Blocks.CLAY), "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_stairs"));
        stairsRecipe(getItem(ModBlocks.dirt_stairs), getItem(Blocks.DIRT), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_stairs"));
        stairsRecipe(getItem(ModBlocks.grass_stairs), getItem(Blocks.GRASS_BLOCK), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_stairs"));
        stairsRecipe(getItem(ModBlocks.hay_stairs), getItem(Blocks.HAY_BLOCK), "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_stairs"));
        stairsRecipe(getItem(ModBlocks.path_stairs), getItem(Blocks.GRASS_PATH), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_stairs"));
        stairsRecipe(getItem(ModBlocks.bone_stairs), getItem(Blocks.BONE_BLOCK), "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_stairs"));
        stairsRecipe(getItem(ModBlocks.snow_stairs), getItem(Blocks.SNOW_BLOCK), "snow", NiftyBlock.SNOW)
                .build(consumer, loc("snow_stairs"));
        stairsRecipe(getItem(ModBlocks.ice_stairs), getItem(Blocks.ICE), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_stairs"));
        stairsRecipe(getItem(ModBlocks.packed_ice_stairs), getItem(Blocks.PACKED_ICE), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_stairs"));
        stairsRecipe(getItem(ModBlocks.blue_ice_stairs), getItem(Blocks.BLUE_ICE), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_stairs"));

        slabRecipe(getItem(ModBlocks.iron_slab), getItem(Blocks.IRON_BLOCK), "iron", NiftyBlock.IRON)
                .build(consumer, loc("iron_slab"));
        slabRecipe(getItem(ModBlocks.gold_slab), getItem(Blocks.GOLD_BLOCK), "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_slab"));
        slabRecipe(getItem(ModBlocks.diamond_slab), getItem(Blocks.DIAMOND_BLOCK), "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_slab"));
        slabRecipe(getItem(ModBlocks.emerald_slab), getItem(Blocks.EMERALD_BLOCK), "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_slab"));
        slabRecipe(getItem(ModBlocks.lapis_slab), getItem(Blocks.LAPIS_BLOCK), "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_slab"));
        slabRecipe(getItem(ModBlocks.obsidian_slab), getItem(Blocks.OBSIDIAN), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_slab"));
        slabRecipe(getItem(ModBlocks.coal_slab), getItem(Blocks.COAL_BLOCK), "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_slab"));
        slabRecipe(getItem(ModBlocks.redstone_slab), getItem(Blocks.REDSTONE_BLOCK), "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_slab"));
        slabRecipe(getItem(ModBlocks.clay_slab), getItem(Blocks.CLAY), "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_slab"));
        slabRecipe(getItem(ModBlocks.dirt_slab), getItem(Blocks.DIRT), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_slab"));
        slabRecipe(getItem(ModBlocks.grass_slab), getItem(Blocks.GRASS_BLOCK), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_slab"));
        slabRecipe(getItem(ModBlocks.hay_slab), getItem(Blocks.HAY_BLOCK), "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_slab"));
        slabRecipe(getItem(ModBlocks.path_slab), getItem(Blocks.GRASS_PATH), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_slab"));
        slabRecipe(getItem(ModBlocks.bone_slab), getItem(Blocks.BONE_BLOCK), "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_slab"));
        slabRecipe(getItem(ModBlocks.snow_slab), getItem(Blocks.SNOW_BLOCK), "snow", NiftyBlock.SNOW)
                .build(consumer, loc("snow_slab"));
        slabRecipe(getItem(ModBlocks.ice_slab), getItem(Blocks.ICE), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_slab"));
        slabRecipe(getItem(ModBlocks.packed_ice_slab), getItem(Blocks.PACKED_ICE), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_slab"));
        slabRecipe(getItem(ModBlocks.blue_ice_slab), getItem(Blocks.BLUE_ICE), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_slab"));

        fenceRecipe(getItem(ModBlocks.iron_fence), getItem(Blocks.IRON_BLOCK), Items.IRON_INGOT, "iron", NiftyBlock.IRON)
                .build(consumer, loc("iron_fence"));
        fenceRecipe(getItem(ModBlocks.gold_fence), getItem(Blocks.GOLD_BLOCK), Items.GOLD_INGOT, "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_fence"));
        fenceRecipe(getItem(ModBlocks.diamond_fence), getItem(Blocks.DIAMOND_BLOCK), Items.DIAMOND, "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_fence"));
        fenceRecipe(getItem(ModBlocks.emerald_fence), getItem(Blocks.EMERALD_BLOCK), Items.EMERALD, "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_fence"));
        fenceRecipe(getItem(ModBlocks.lapis_fence), getItem(Blocks.LAPIS_BLOCK), Items.LAPIS_BLOCK, "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_fence"));
        fenceRecipe(getItem(ModBlocks.obsidian_fence), getItem(Blocks.OBSIDIAN), getItem(ModBlocks.obsidian_slab), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_fence"));
        fenceRecipe(getItem(ModBlocks.coal_fence), getItem(Blocks.COAL_BLOCK), Items.COAL, "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_fence"));
        fenceRecipe(getItem(ModBlocks.redstone_fence), getItem(Blocks.REDSTONE_BLOCK), Items.REDSTONE, "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_fence"));
        fenceRecipe(getItem(ModBlocks.clay_fence), getItem(Blocks.CLAY), Items.CLAY_BALL, "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_fence"));
        fenceRecipe(getItem(ModBlocks.dirt_fence), getItem(Blocks.DIRT), getItem(ModBlocks.dirt_slab), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_fence"));
        fenceRecipe(getItem(ModBlocks.grass_fence), getItem(Blocks.GRASS_BLOCK), getItem(ModBlocks.grass_slab), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_fence"));
        fenceRecipe(getItem(ModBlocks.hay_fence), getItem(Blocks.HAY_BLOCK), Items.WHEAT, "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_fence"));
        fenceRecipe(getItem(ModBlocks.path_fence), getItem(Blocks.GRASS_PATH), getItem(ModBlocks.path_slab), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_fence"));
        fenceRecipe(getItem(ModBlocks.brick_fence), getItem(Blocks.BRICKS), Items.BRICK, "bricks", NiftyBlock.BRICK)
                .build(consumer, loc("brick_fence"));
        fenceRecipe(getItem(ModBlocks.quartz_fence), getItem(Blocks.QUARTZ_BLOCK), Items.QUARTZ, "quartz", NiftyBlock.QUARTZ)
                .build(consumer, loc("quartz_fence"));
        fenceRecipe(getItem(ModBlocks.bone_fence), getItem(Blocks.BONE_BLOCK), Items.BONE_MEAL, "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_fence"));
        fenceRecipe(getItem(ModBlocks.red_nether_brick_fence), getItem(Blocks.RED_NETHER_BRICKS), Items.NETHER_WART, "red_nether_brick", NiftyBlock.RED_NETHER_BRICK)
                .build(consumer, loc("red_nether_brick_fence"));
        fenceRecipe(getItem(ModBlocks.snow_fence), getItem(Blocks.SNOW_BLOCK), Items.SNOWBALL, "snow", NiftyBlock.SNOW)
                .build(consumer, loc("snow_fence"));
        fenceRecipe(getItem(ModBlocks.ice_fence), getItem(Blocks.ICE), getItem(ModBlocks.ice_slab), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_fence"));
        fenceRecipe(getItem(ModBlocks.packed_ice_fence), getItem(Blocks.PACKED_ICE), getItem(ModBlocks.packed_ice_slab), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_fence"));
        fenceRecipe(getItem(ModBlocks.blue_ice_fence), getItem(Blocks.BLUE_ICE), getItem(ModBlocks.blue_ice_slab), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_fence"));

        trapdoorRecipe(getItem(ModBlocks.gold_trapdoor), Items.GOLD_INGOT, "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_trapdoor"));
        trapdoorRecipe(getItem(ModBlocks.diamond_trapdoor), Items.DIAMOND, "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_trapdoor"));
        trapdoorRecipe(getItem(ModBlocks.emerald_trapdoor), Items.EMERALD, "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_trapdoor"));
        trapdoorRecipe(getItem(ModBlocks.lapis_trapdoor), Items.LAPIS_LAZULI, "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_trapdoor"));
        trapdoorRecipe(getItem(ModBlocks.obsidian_trapdoor), getItem(ModBlocks.obsidian_slab), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.coal_trapdoor), Items.COAL, "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.redstone_trapdoor), Items.REDSTONE, "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.clay_trapdoor), Items.CLAY, "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.dirt_trapdoor), getItem(ModBlocks.dirt_slab), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.grass_trapdoor), getItem(ModBlocks.grass_slab), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.hay_trapdoor), Items.HAY_BLOCK, "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.path_trapdoor), getItem(ModBlocks.path_slab), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.brick_trapdoor), Items.BRICK, "brick", NiftyBlock.BRICK)
                .build(consumer, loc("brick_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.quartz_trapdoor), Items.QUARTZ, "quartz", NiftyBlock.QUARTZ)
                .build(consumer, loc("quartz_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.bone_trapdoor), Items.BONE_MEAL, "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.nether_brick_trapdoor), Items.NETHER_BRICK, "nether_brick", NiftyBlock.NETHER_BRICK)
                .build(consumer, loc("nether_brick_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.red_nether_brick_trapdoor), Items.NETHER_WART, "red_nether_brick", NiftyBlock.RED_NETHER_BRICK)
                .build(consumer, loc("red_nether_brick_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.snow_trapdoor), Items.SNOWBALL, "snowball", NiftyBlock.SNOW)
                .build(consumer, loc("snow_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.ice_trapdoor), getItem(ModBlocks.ice_slab), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.packed_ice_trapdoor), getItem(ModBlocks.packed_ice_slab), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_trapdoor"));
        trapdoorRecipeWide(getItem(ModBlocks.blue_ice_trapdoor), getItem(ModBlocks.blue_ice_slab), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_trapdoor"));

        fenceGateRecipe(getItem(ModBlocks.iron_fence_gate), getItem(Blocks.IRON_BLOCK), Items.IRON_INGOT, "iron", NiftyBlock.IRON)
                .build(consumer, loc("iron_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.gold_fence_gate), getItem(Blocks.GOLD_BLOCK), Items.GOLD_INGOT, "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.diamond_fence_gate), getItem(Blocks.DIAMOND_BLOCK), Items.DIAMOND, "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.emerald_fence_gate), getItem(Blocks.EMERALD_BLOCK), Items.EMERALD, "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.lapis_fence_gate), getItem(Blocks.LAPIS_BLOCK), Items.LAPIS_BLOCK, "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.obsidian_fence_gate), getItem(Blocks.OBSIDIAN), getItem(ModBlocks.obsidian_slab), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.coal_fence_gate), getItem(Blocks.COAL_BLOCK), Items.COAL, "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.redstone_fence_gate), getItem(Blocks.REDSTONE_BLOCK), Items.REDSTONE, "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.clay_fence_gate), getItem(Blocks.CLAY), Items.CLAY_BALL, "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.dirt_fence_gate), getItem(Blocks.DIRT), getItem(ModBlocks.dirt_slab), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.grass_fence_gate), getItem(Blocks.GRASS_BLOCK), getItem(ModBlocks.grass_slab), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.hay_fence_gate), getItem(Blocks.HAY_BLOCK), Items.WHEAT, "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.path_fence_gate), getItem(Blocks.GRASS_PATH), getItem(ModBlocks.path_slab), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.brick_fence_gate), getItem(Blocks.BRICKS), Items.BRICK, "bricks", NiftyBlock.BRICK)
                .build(consumer, loc("brick_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.quartz_fence_gate), getItem(Blocks.QUARTZ_BLOCK), Items.QUARTZ, "quartz", NiftyBlock.QUARTZ)
                .build(consumer, loc("quartz_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.bone_fence_gate), getItem(Blocks.BONE_BLOCK), Items.BONE_MEAL, "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.nether_brick_fence_gate), getItem(Blocks.NETHER_BRICKS), Items.NETHER_BRICK, "nether_brick", NiftyBlock.NETHER_BRICK)
                .build(consumer, loc("nether_brick_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.red_nether_brick_fence_gate), getItem(Blocks.RED_NETHER_BRICKS), Items.NETHER_WART, "red_nether_brick", NiftyBlock.RED_NETHER_BRICK)
                .build(consumer, loc("red_nether_brick_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.snow_fence_gate), getItem(Blocks.SNOW_BLOCK), Items.SNOWBALL, "snow", NiftyBlock.SNOW)
                .build(consumer, loc("snow_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.ice_fence_gate), getItem(Blocks.ICE), getItem(ModBlocks.ice_slab), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.packed_ice_fence_gate), getItem(Blocks.PACKED_ICE), getItem(ModBlocks.packed_ice_slab), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_fence_gate"));
        fenceGateRecipe(getItem(ModBlocks.blue_ice_fence_gate), getItem(Blocks.BLUE_ICE), getItem(ModBlocks.blue_ice_slab), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_fence_gate"));

        doorRecipe(getItem(ModBlocks.gold_door), Items.GOLD_INGOT, "gold", NiftyBlock.GOLD)
                .build(consumer, loc("gold_door"));
        doorRecipe(getItem(ModBlocks.diamond_door), Items.DIAMOND, "diamond", NiftyBlock.DIAMOND)
                .build(consumer, loc("diamond_door"));
        doorRecipe(getItem(ModBlocks.emerald_door), Items.EMERALD, "emerald", NiftyBlock.EMERALD)
                .build(consumer, loc("emerald_door"));
        doorRecipe(getItem(ModBlocks.lapis_door), Items.LAPIS_LAZULI, "lapis", NiftyBlock.LAPIS)
                .build(consumer, loc("lapis_door"));
        doorRecipe(getItem(ModBlocks.obsidian_door), getItem(ModBlocks.obsidian_slab), "obsidian", NiftyBlock.OBSIDIAN)
                .build(consumer, loc("obsidian_door"));
        doorRecipe(getItem(ModBlocks.coal_door), Items.COAL, "coal", NiftyBlock.COAL)
                .build(consumer, loc("coal_door"));
        doorRecipe(getItem(ModBlocks.redstone_door), Items.REDSTONE, "redstone", NiftyBlock.REDSTONE)
                .build(consumer, loc("redstone_door"));
        doorRecipe(getItem(ModBlocks.clay_door), Items.CLAY, "clay", NiftyBlock.CLAY)
                .build(consumer, loc("clay_door"));
        doorRecipe(getItem(ModBlocks.dirt_door), getItem(ModBlocks.dirt_slab), "dirt", NiftyBlock.DIRT)
                .build(consumer, loc("dirt_door"));
        doorRecipe(getItem(ModBlocks.grass_door), getItem(ModBlocks.grass_slab), "grass", NiftyBlock.GRASS)
                .build(consumer, loc("grass_door"));
        doorRecipe(getItem(ModBlocks.hay_door), Items.WHEAT, "hay", NiftyBlock.HAY)
                .build(consumer, loc("hay_door"));
        doorRecipe(getItem(ModBlocks.path_door), getItem(ModBlocks.path_slab), "path", NiftyBlock.PATH)
                .build(consumer, loc("path_door"));
        doorRecipe(getItem(ModBlocks.brick_door), Items.BRICK, "bricks", NiftyBlock.BRICK)
                .build(consumer, loc("brick_door"));
        doorRecipe(getItem(ModBlocks.quartz_door), Items.QUARTZ, "quartz", NiftyBlock.QUARTZ)
                .build(consumer, loc("quartz_door"));
        doorRecipe(getItem(ModBlocks.bone_door), Items.BONE_MEAL, "bone", NiftyBlock.BONE)
                .build(consumer, loc("bone_door"));
        doorRecipe(getItem(ModBlocks.nether_brick_door), Items.NETHER_BRICK, "nether_brick", NiftyBlock.NETHER_BRICK)
                .build(consumer, loc("nether_brick_door"));
        doorRecipe(getItem(ModBlocks.red_nether_brick_door), Items.NETHER_WART, "red_nether_brick", NiftyBlock.RED_NETHER_BRICK)
                .build(consumer, loc("red_nether_brick_door"));
        doorRecipe(getItem(ModBlocks.snow_door), Items.SNOWBALL, "snow", NiftyBlock.SNOW)
                .build(consumer, loc("snow_door"));
        doorRecipe(getItem(ModBlocks.ice_door), getItem(ModBlocks.ice_slab), "ice", NiftyBlock.ICE)
                .build(consumer, loc("ice_door"));
        doorRecipe(getItem(ModBlocks.packed_ice_door), getItem(ModBlocks.packed_ice_slab), "packed_ice", NiftyBlock.PACKED_ICE)
                .build(consumer, loc("packed_ice_door"));
        doorRecipe(getItem(ModBlocks.blue_ice_door), getItem(ModBlocks.blue_ice_slab), "blue_ice", NiftyBlock.BLUE_ICE)
                .build(consumer, loc("blue_ice_door"));
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(NiftyMod.MODID, name);
    }

    private IItemProvider getItem(RegistryObject<? extends Block> block) {
        return block.get().asItem();
    }

    private IItemProvider getItem(Block block) {
        return block.asItem();
    }
}