package com.androsa.ornamental.data;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalBlockStateProvider;
import com.google.errorprone.annotations.Var;
import com.mojang.datafixers.util.Either;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import java.util.function.Supplier;

public class OrnamentalBlockStates extends OrnamentalBlockStateProvider {

    public OrnamentalBlockStates(BlockModelGenerators blockModels) {
        super(blockModels, OrnamentalMod.MODID, "minecraft");
    }

    @Override
    public void runBlockGen() {
        stairsBasic(ModBlocks.iron_stairs, "iron_block");
        stairsBasic(ModBlocks.gold_stairs, "gold_block");
        stairsBasic(ModBlocks.diamond_stairs, "diamond_block");
        stairsBasic(ModBlocks.emerald_stairs, "emerald_block");
        stairsBasic(ModBlocks.lapis_stairs, "lapis_block");
        stairsBasic(ModBlocks.obsidian_stairs, "obsidian");
        stairsBasic(ModBlocks.coal_stairs, "coal_block");
        stairsBasic(ModBlocks.redstone_stairs, "redstone_block");
        stairsBasic(ModBlocks.missingno_stairs, locMod("missingno"), ModelTemplates.STAIRS_INNER, ModelTemplates.STAIRS_STRAIGHT, ModelTemplates.STAIRS_OUTER);
        stairsBasic(ModBlocks.clay_stairs, "clay");
        stairsBasic(ModBlocks.dirt_stairs, "dirt");
        hexamodelStairsBlock(ModBlocks.grass_stairs, "grass", true);
        stairsColumn(ModBlocks.hay_stairs, "hay_block_side", "hay_block_top");
        hexamodelStairsBlock(ModBlocks.path_stairs, "path", false);
        stairsColumn(ModBlocks.bone_stairs, "bone_block_side", "bone_block_top");
        stairsBasic(ModBlocks.snow_stairs, "snow");
        modelStairsBlock(ModBlocks.ice_stairs, "ice");
        stairsBasic(ModBlocks.packed_ice_stairs, "packed_ice");
        stairsBasic(ModBlocks.blue_ice_stairs, "blue_ice");
        stairsBasic(ModBlocks.netherite_stairs, "netherite_block");
        stairsBasic(ModBlocks.amethyst_stairs, "amethyst_block");
        stairsBasic(ModBlocks.copper_stairs, "copper_block");
        stairsBasic(ModBlocks.exposed_copper_stairs, "exposed_copper");
        stairsBasic(ModBlocks.weathered_copper_stairs, "weathered_copper");
        stairsBasic(ModBlocks.oxidized_copper_stairs, "oxidized_copper");
        stairsBasic(ModBlocks.magma_stairs, "magma");
        stairsBasic(ModBlocks.calcite_stairs, "calcite");

        slabBasic(ModBlocks.iron_slab, () -> Blocks.IRON_BLOCK);
        slabBasic(ModBlocks.gold_slab, () -> Blocks.GOLD_BLOCK);
        slabBasic(ModBlocks.diamond_slab, () -> Blocks.DIAMOND_BLOCK);
        slabBasic(ModBlocks.emerald_slab, () -> Blocks.EMERALD_BLOCK);
        slabBasic(ModBlocks.lapis_slab, () -> Blocks.LAPIS_BLOCK);
        slabBasic(ModBlocks.obsidian_slab, () -> Blocks.OBSIDIAN);
        slabBasic(ModBlocks.coal_slab, () -> Blocks.COAL_BLOCK);
        slabBasic(ModBlocks.redstone_slab, () -> Blocks.REDSTONE_BLOCK);
        slabModel(ModBlocks.missingno_slab, "missingno", locMod("missingno"), SOLID);
        slabBasic(ModBlocks.clay_slab, () -> Blocks.CLAY);
        slabBasic(ModBlocks.dirt_slab, () -> Blocks.DIRT);
        modelSlabBlock(ModBlocks.grass_slab, () -> Blocks.GRASS_BLOCK, "grass", true, CUTOUT_MIPPED);
        slabColumn(ModBlocks.hay_slab, () -> Blocks.HAY_BLOCK, "hay_block_side", "hay_block_top", SOLID);
        modelSlabBlock(ModBlocks.path_slab, () -> Blocks.DIRT_PATH, "path", false, CUTOUT);
        slabColumn(ModBlocks.bone_slab, () -> Blocks.BONE_BLOCK, "bone_block_side", "bone_block_top", SOLID);
        slabModel(ModBlocks.snow_slab, () -> Blocks.SNOW_BLOCK, "snow", SOLID);
        slabBasic(ModBlocks.ice_slab, () -> Blocks.ICE, TRANSLUCENT);
        slabBasic(ModBlocks.packed_ice_slab, () -> Blocks.PACKED_ICE);
        slabBasic(ModBlocks.blue_ice_slab, () -> Blocks.BLUE_ICE);
        slabBasic(ModBlocks.netherite_slab, () -> Blocks.NETHERITE_BLOCK);
        slabBasic(ModBlocks.amethyst_slab, () -> Blocks.AMETHYST_BLOCK);
        slabBasic(ModBlocks.copper_slab, () -> Blocks.COPPER_BLOCK);
        slabBasic(ModBlocks.exposed_copper_slab, () -> Blocks.EXPOSED_COPPER);
        slabBasic(ModBlocks.weathered_copper_slab, () -> Blocks.WEATHERED_COPPER);
        slabBasic(ModBlocks.oxidized_copper_slab, () -> Blocks.OXIDIZED_COPPER);
        slabModel(ModBlocks.magma_slab, () -> Blocks.MAGMA_BLOCK, "magma", SOLID);
        slabBasic(ModBlocks.calcite_slab, () -> Blocks.CALCITE);

        fenceBasic(ModBlocks.iron_fence, "iron_block");
        fenceBasic(ModBlocks.gold_fence, "gold_block");
        fenceBasic(ModBlocks.diamond_fence, "diamond_block");
        fenceBasic(ModBlocks.emerald_fence, "emerald_block");
        fenceBasic(ModBlocks.lapis_fence, "lapis_block");
        fenceBasic(ModBlocks.obsidian_fence, "obsidian");
        fenceBasic(ModBlocks.coal_fence, "coal_block");
        fenceBasic(ModBlocks.redstone_fence, "redstone_block");
        fenceBasic(ModBlocks.missingno_fence, locMod("missingno"));
        fenceBasic(ModBlocks.clay_fence, "clay");
        fenceBasic(ModBlocks.dirt_fence, "dirt");
        modelFenceBlock(ModBlocks.grass_fence, "grass", true);
        fenceColumn(ModBlocks.hay_fence, "hay_block_side", "hay_block_top");
        modelFenceBlock(ModBlocks.path_fence, "path", false);
        fenceBasic(ModBlocks.brick_fence, "bricks");
        fenceColumn(ModBlocks.quartz_fence, "quartz_block_side", "quartz_block_top");
        fenceColumn(ModBlocks.bone_fence, "bone_block_side", "bone_block_top");
        fenceBasic(ModBlocks.red_nether_brick_fence, "red_nether_bricks");
        fenceBasic(ModBlocks.snow_fence, "snow");
        modelFenceBlock(ModBlocks.ice_fence, "ice", false);
        fenceBasic(ModBlocks.packed_ice_fence, "packed_ice");
        fenceBasic(ModBlocks.blue_ice_fence, "blue_ice");
        fenceBasic(ModBlocks.netherite_fence, "netherite_block");
        fenceBasic(ModBlocks.amethyst_fence, "amethyst_block");
        fenceBasic(ModBlocks.copper_fence, "copper_block");
        fenceBasic(ModBlocks.exposed_copper_fence, "exposed_copper");
        fenceBasic(ModBlocks.weathered_copper_fence, "weathered_copper");
        fenceBasic(ModBlocks.oxidized_copper_fence, "oxidized_copper");
        fenceBasic(ModBlocks.magma_fence, "magma");
        fenceBasic(ModBlocks.calcite_fence, "calcite");
        fenceBasic(ModBlocks.cut_copper_fence, "cut_copper");
        fenceBasic(ModBlocks.exposed_cut_copper_fence, "exposed_cut_copper");
        fenceBasic(ModBlocks.weathered_cut_copper_fence, "weathered_cut_copper");
        fenceBasic(ModBlocks.oxidized_cut_copper_fence, "oxidized_cut_copper");

        trapdoorBasic(ModBlocks.gold_trapdoor, "gold");
        trapdoorBasic(ModBlocks.diamond_trapdoor, "diamond");
        trapdoorBasic(ModBlocks.emerald_trapdoor, "emerald");
        trapdoorBasic(ModBlocks.lapis_trapdoor, "lapis");
        trapdoorBasic(ModBlocks.obsidian_trapdoor, "obsidian");
        trapdoorBasic(ModBlocks.coal_trapdoor, "coal");
        trapdoorBasic(ModBlocks.redstone_trapdoor, "redstone");
        trapdoor(ModBlocks.missingno_trapdoor, locMod("missingno"), false, CUTOUT);
        trapdoorBasic(ModBlocks.clay_trapdoor, "clay");
        trapdoorParent(ModBlocks.dirt_trapdoor, "dirt");
        modelTrapdoorBlock(ModBlocks.grass_trapdoor, "grass", true);
        trapdoorBasic(ModBlocks.hay_trapdoor, "hay");
        modelTrapdoorBlock(ModBlocks.path_trapdoor, "path", false);
        trapdoorParent(ModBlocks.brick_trapdoor, "bricks");
        trapdoorBasic(ModBlocks.quartz_trapdoor, "quartz");
        trapdoorBasic(ModBlocks.bone_trapdoor, "bone");
        trapdoorBasic(ModBlocks.nether_brick_trapdoor, "nether_brick");
        trapdoorBasic(ModBlocks.red_nether_brick_trapdoor, "red_nether_brick");
        trapdoorBasic(ModBlocks.snow_trapdoor, "snow");
        trapdoorBasic(ModBlocks.ice_trapdoor, "ice", TRANSLUCENT);
        trapdoorBasic(ModBlocks.packed_ice_trapdoor, "packed_ice");
        trapdoorBasic(ModBlocks.blue_ice_trapdoor, "blue_ice");
        trapdoorBasic(ModBlocks.netherite_trapdoor, "netherite");
        trapdoorBasic(ModBlocks.amethyst_trapdoor, "amethyst");
        trapdoorBasic(ModBlocks.magma_trapdoor, "magma");
        trapdoorBasic(ModBlocks.calcite_trapdoor, "calcite");
        trapdoorBasic(ModBlocks.cut_copper_trapdoor, "cut_copper");
        trapdoorBasic(ModBlocks.exposed_cut_copper_trapdoor, "exposed_cut_copper");
        trapdoorBasic(ModBlocks.weathered_cut_copper_trapdoor, "weathered_cut_copper");
        trapdoorBasic(ModBlocks.oxidized_cut_copper_trapdoor, "oxidized_cut_copper");

        fenceGateBasic(ModBlocks.iron_fence_gate, "iron_block");
        fenceGateBasic(ModBlocks.gold_fence_gate, "gold_block");
        fenceGateBasic(ModBlocks.diamond_fence_gate, "diamond_block");
        fenceGateBasic(ModBlocks.emerald_fence_gate, "emerald_block");
        fenceGateBasic(ModBlocks.lapis_fence_gate, "lapis_block");
        fenceGateBasic(ModBlocks.obsidian_fence_gate, "obsidian");
        fenceGateBasic(ModBlocks.coal_fence_gate, "coal_block");
        fenceGateBasic(ModBlocks.redstone_fence_gate, "redstone_block");
        fenceGateBasic(ModBlocks.missingno_fence_gate, locMod("missingno"), SOLID);
        fenceGateBasic(ModBlocks.clay_fence_gate, "clay");
        fenceGateBasic(ModBlocks.dirt_fence_gate, "dirt");
        modelFenceGateBlock(ModBlocks.grass_fence_gate, "grass", true);
        fenceGateColumn(ModBlocks.hay_fence_gate, "hay_block_side", "hay_block_top", SOLID);
        modelFenceGateBlock(ModBlocks.path_fence_gate, "path", false);
        fenceGateBasic(ModBlocks.brick_fence_gate, "bricks");
        fenceGateColumn(ModBlocks.quartz_fence_gate, "quartz_block_side", "quartz_block_top", SOLID);
        fenceGateColumn(ModBlocks.bone_fence_gate, "bone_block_side", "bone_block_top", SOLID);
        fenceGateBasic(ModBlocks.nether_brick_fence_gate, "nether_bricks");
        fenceGateBasic(ModBlocks.red_nether_brick_fence_gate, "red_nether_bricks");
        fenceGateBasic(ModBlocks.snow_fence_gate, "snow");
        fenceGateBasic(ModBlocks.ice_fence_gate, "ice", TRANSLUCENT);
        fenceGateBasic(ModBlocks.packed_ice_fence_gate, "packed_ice");
        fenceGateBasic(ModBlocks.blue_ice_fence_gate, "blue_ice");
        fenceGateBasic(ModBlocks.netherite_fence_gate, "netherite_block");
        fenceGateBasic(ModBlocks.amethyst_fence_gate, "amethyst_block");
        fenceGateBasic(ModBlocks.copper_fence_gate, "copper_block");
        fenceGateBasic(ModBlocks.exposed_copper_fence_gate, "exposed_copper");
        fenceGateBasic(ModBlocks.weathered_copper_fence_gate, "weathered_copper");
        fenceGateBasic(ModBlocks.oxidized_copper_fence_gate, "oxidized_copper");
        fenceGateBasic(ModBlocks.magma_fence_gate, "magma");
        fenceGateBasic(ModBlocks.calcite_fence_gate, "calcite");
        fenceGateBasic(ModBlocks.cut_copper_fence_gate, "cut_copper");
        fenceGateBasic(ModBlocks.exposed_cut_copper_fence_gate, "exposed_cut_copper");
        fenceGateBasic(ModBlocks.weathered_cut_copper_fence_gate, "weathered_cut_copper");
        fenceGateBasic(ModBlocks.oxidized_cut_copper_fence_gate, "oxidized_cut_copper");

        doorBasic(ModBlocks.gold_door, "gold");
        doorBasic(ModBlocks.diamond_door, "diamond");
        doorBasic(ModBlocks.emerald_door, "emerald");
        doorBasic(ModBlocks.lapis_door, "lapis");
        doorBasic(ModBlocks.obsidian_door, "obsidian");
        doorBasic(ModBlocks.coal_door, "coal");
        doorBasic(ModBlocks.redstone_door, "redstone");
        doorBasic(ModBlocks.missingno_door, locMod("missingno"), CUTOUT);
        doorBasic(ModBlocks.clay_door, "clay");
        doorHidden(ModBlocks.dirt_door, "dirt");
        halfDirtDoorBlock(ModBlocks.grass_door, "grass");
        doorBasic(ModBlocks.hay_door, "hay");
        halfDirtDoorBlock(ModBlocks.path_door, "path");
        doorHidden(ModBlocks.brick_door, "bricks");
        doorBasic(ModBlocks.quartz_door, "quartz");
        doorBasic(ModBlocks.bone_door, "bone");
        doorBasic(ModBlocks.nether_brick_door, "nether_brick");
        doorBasic(ModBlocks.red_nether_brick_door, "red_nether_brick");
        doorBasic(ModBlocks.snow_door, "snow");
        doorBasic(ModBlocks.ice_door, "ice", TRANSLUCENT);
        doorBasic(ModBlocks.packed_ice_door, "packed_ice");
        doorBasic(ModBlocks.blue_ice_door, "blue_ice");
        doorBasic(ModBlocks.netherite_door, "netherite");
        doorBasic(ModBlocks.amethyst_door, "amethyst");
        doorBasic(ModBlocks.magma_door, "magma");
        doorBasic(ModBlocks.calcite_door, "calcite");
        doorBasic(ModBlocks.cut_copper_door, "cut_copper");
        doorBasic(ModBlocks.exposed_cut_copper_door, "exposed_cut_copper");
        doorBasic(ModBlocks.weathered_cut_copper_door, "weathered_cut_copper");
        doorBasic(ModBlocks.oxidized_cut_copper_door, "oxidized_cut_copper");

        poleBasic(ModBlocks.iron_pole, () -> Blocks.IRON_BLOCK, "iron_block");
        poleBasic(ModBlocks.gold_pole, () -> Blocks.GOLD_BLOCK, "gold_block");
        poleBasic(ModBlocks.diamond_pole, () -> Blocks.DIAMOND_BLOCK, "diamond_block");
        poleBasic(ModBlocks.emerald_pole, () -> Blocks.EMERALD_BLOCK, "emerald_block");
        poleBasic(ModBlocks.lapis_pole, () -> Blocks.LAPIS_BLOCK, "lapis_block");
        poleBasic(ModBlocks.obsidian_pole, () -> Blocks.OBSIDIAN, "obsidian");
        poleBasic(ModBlocks.coal_pole, () -> Blocks.COAL_BLOCK, "coal_block");
        poleBasic(ModBlocks.redstone_pole, () -> Blocks.REDSTONE_BLOCK, "redstone_block");
        poleBasic(ModBlocks.missingno_pole, "missingno", "missingno");
        poleBasic(ModBlocks.clay_pole, () -> Blocks.CLAY, "clay");
        poleBasic(ModBlocks.dirt_pole, () -> Blocks.DIRT, "dirt");
        modelPoleBlock(ModBlocks.grass_pole, "grass", () -> Blocks.GRASS_BLOCK, true);
        poleColumn(ModBlocks.hay_pole, Either.right(() -> Blocks.HAY_BLOCK), "hay_block_side", "hay_block_top", SOLID);
        modelPoleBlock(ModBlocks.path_pole, "path", () -> Blocks.DIRT_PATH, false);
        poleBasic(ModBlocks.brick_pole, () -> Blocks.BRICKS, "bricks");
        poleColumn(ModBlocks.quartz_pole, Either.right(() -> Blocks.QUARTZ_BLOCK), "quartz_block_side", "quartz_block_top", SOLID);
        poleColumn(ModBlocks.bone_pole, Either.right(() -> Blocks.BONE_BLOCK), "bone_block_side", "bone_block_top", SOLID);
        poleBasic(ModBlocks.nether_brick_pole, () -> Blocks.NETHER_BRICKS, "nether_bricks");
        poleBasic(ModBlocks.red_nether_brick_pole, () -> Blocks.RED_NETHER_BRICKS, "red_nether_bricks");
        poleBasic(ModBlocks.snow_pole, () -> Blocks.SNOW_BLOCK, "snow");
        poleBasic(ModBlocks.ice_pole, () -> Blocks.ICE, "ice", TRANSLUCENT);
        poleBasic(ModBlocks.packed_ice_pole, () -> Blocks.PACKED_ICE, "packed_ice");
        poleBasic(ModBlocks.blue_ice_pole, () -> Blocks.BLUE_ICE, "blue_ice");
        poleBasic(ModBlocks.netherite_pole, () -> Blocks.NETHERITE_BLOCK, "netherite_block");
        poleBasic(ModBlocks.amethyst_pole, () -> Blocks.AMETHYST_BLOCK, "amethyst_block");
        poleBasic(ModBlocks.copper_pole, () -> Blocks.COPPER_BLOCK, "copper_block");
        poleBasic(ModBlocks.exposed_copper_pole, () -> Blocks.EXPOSED_COPPER, "exposed_copper");
        poleBasic(ModBlocks.weathered_copper_pole, () -> Blocks.WEATHERED_COPPER, "weathered_copper");
        poleBasic(ModBlocks.oxidized_copper_pole, () -> Blocks.OXIDIZED_COPPER, "oxidized_copper");
        poleBasic(ModBlocks.magma_pole, () -> Blocks.MAGMA_BLOCK, "magma");
        poleBasic(ModBlocks.calcite_pole, () -> Blocks.CALCITE, "calcite");
        poleBasic(ModBlocks.cut_copper_pole, () -> Blocks.CUT_COPPER, "cut_copper");
        poleBasic(ModBlocks.exposed_cut_copper_pole, () -> Blocks.EXPOSED_CUT_COPPER, "exposed_cut_copper");
        poleBasic(ModBlocks.weathered_cut_copper_pole, () -> Blocks.WEATHERED_CUT_COPPER, "weathered_cut_copper");
        poleBasic(ModBlocks.oxidized_cut_copper_pole, () -> Blocks.OXIDIZED_CUT_COPPER, "oxidized_cut_copper");

        beamBasic(ModBlocks.iron_beam, () -> Blocks.IRON_BLOCK, "iron_block");
        beamBasic(ModBlocks.gold_beam, () -> Blocks.GOLD_BLOCK, "gold_block");
        beamBasic(ModBlocks.diamond_beam, () -> Blocks.DIAMOND_BLOCK, "diamond_block");
        beamBasic(ModBlocks.emerald_beam, () -> Blocks.EMERALD_BLOCK, "emerald_block");
        beamBasic(ModBlocks.lapis_beam, () -> Blocks.LAPIS_BLOCK, "lapis_block");
        beamBasic(ModBlocks.obsidian_beam, () -> Blocks.OBSIDIAN, "obsidian");
        beamBasic(ModBlocks.coal_beam, () -> Blocks.COAL_BLOCK, "coal_block");
        beamBasic(ModBlocks.redstone_beam, () -> Blocks.REDSTONE_BLOCK, "redstone_block");
        beamBasic(ModBlocks.missingno_beam, "missingno", "missingno");
        beamBasic(ModBlocks.clay_beam, () -> Blocks.CLAY, "clay");
        beamBasic(ModBlocks.dirt_beam, () -> Blocks.DIRT, "dirt");
        halfDirtBeamBlock(ModBlocks.grass_beam, () -> Blocks.GRASS_BLOCK, "grass", true, true);
        beamColumn(ModBlocks.hay_beam, Either.right(() -> Blocks.HAY_BLOCK), "hay_block_top", "hay_block_side", SOLID);
        halfDirtBeamBlock(ModBlocks.path_beam, () -> Blocks.DIRT_PATH, "path", false, false);
        beamBasic(ModBlocks.brick_beam, () -> Blocks.BRICKS, "bricks");
        beamColumn(ModBlocks.quartz_beam, Either.right(() -> Blocks.QUARTZ_BLOCK), "quartz_block_top", "quartz_block_side", SOLID);
        beamColumn(ModBlocks.bone_beam, Either.right(() -> Blocks.BONE_BLOCK), "bone_block_top", "bone_block_side", SOLID);
        beamBasic(ModBlocks.nether_brick_beam, () -> Blocks.NETHER_BRICKS, "nether_bricks");
        beamBasic(ModBlocks.red_nether_brick_beam, () -> Blocks.RED_NETHER_BRICKS, "red_nether_bricks");
        beamBasic(ModBlocks.snow_beam, () -> Blocks.SNOW_BLOCK, "snow");
        beamBasic(ModBlocks.ice_beam, () -> Blocks.ICE, "ice", TRANSLUCENT);
        beamBasic(ModBlocks.packed_ice_beam, () -> Blocks.PACKED_ICE, "packed_ice");
        beamBasic(ModBlocks.blue_ice_beam, () -> Blocks.BLUE_ICE, "blue_ice");
        beamBasic(ModBlocks.netherite_beam, () -> Blocks.NETHERITE_BLOCK, "netherite_block");
        beamBasic(ModBlocks.amethyst_beam, () -> Blocks.AMETHYST_BLOCK, "amethyst_block");
        beamBasic(ModBlocks.copper_beam, () -> Blocks.COPPER_BLOCK, "copper_block");
        beamBasic(ModBlocks.exposed_copper_beam, () -> Blocks.EXPOSED_COPPER, "exposed_copper");
        beamBasic(ModBlocks.weathered_copper_beam, () -> Blocks.WEATHERED_COPPER, "weathered_copper");
        beamBasic(ModBlocks.oxidized_copper_beam, () -> Blocks.OXIDIZED_COPPER, "oxidized_copper");
        beamBasic(ModBlocks.magma_beam, () -> Blocks.MAGMA_BLOCK, "magma");
        beamBasic(ModBlocks.calcite_beam, () -> Blocks.CALCITE, "calcite");
        beamBasic(ModBlocks.cut_copper_beam, () -> Blocks.CUT_COPPER, "cut_copper");
        beamBasic(ModBlocks.exposed_cut_copper_beam, () -> Blocks.EXPOSED_CUT_COPPER, "exposed_cut_copper");
        beamBasic(ModBlocks.weathered_cut_copper_beam, () -> Blocks.WEATHERED_CUT_COPPER, "weathered_cut_copper");
        beamBasic(ModBlocks.oxidized_cut_copper_beam, () -> Blocks.OXIDIZED_CUT_COPPER, "oxidized_cut_copper");

        wallBasic(ModBlocks.iron_wall, "iron_block");
        wallBasic(ModBlocks.gold_wall, "gold_block");
        wallBasic(ModBlocks.diamond_wall, "diamond_block");
        wallBasic(ModBlocks.emerald_wall, "emerald_block");
        wallBasic(ModBlocks.lapis_wall, "lapis_block");
        wallBasic(ModBlocks.obsidian_wall, "obsidian");
        wallBasic(ModBlocks.coal_wall, "coal_block");
        wallBasic(ModBlocks.redstone_wall, "redstone_block");
        wallBasic(ModBlocks.missingno_wall, locMod("missingno"));
        wallBasic(ModBlocks.clay_wall, "clay");
        wallBasic(ModBlocks.dirt_wall, "dirt");
        modelWallBlock(ModBlocks.grass_wall, "grass", true);
        wallColumn(ModBlocks.hay_wall, "hay_block_side", "hay_block_top");
        modelWallBlock(ModBlocks.path_wall, "path", false);
        wallColumn(ModBlocks.quartz_wall, "quartz_block_side", "quartz_block_top");
        wallColumn(ModBlocks.bone_wall, "bone_block_side", "bone_block_top");
        wallBasic(ModBlocks.snow_wall, "snow");
        modelIceWall(ModBlocks.ice_wall);
        wallBasic(ModBlocks.packed_ice_wall, "packed_ice");
        wallBasic(ModBlocks.blue_ice_wall, "blue_ice");
        wallBasic(ModBlocks.netherite_wall, "netherite_block");
        wallBasic(ModBlocks.amethyst_wall, "amethyst_block");
        wallBasic(ModBlocks.copper_wall, "copper_block");
        wallBasic(ModBlocks.exposed_copper_wall, "exposed_copper");
        wallBasic(ModBlocks.weathered_copper_wall, "weathered_copper");
        wallBasic(ModBlocks.oxidized_copper_wall, "oxidized_copper");
        wallBasic(ModBlocks.magma_wall, "magma");
        wallBasic(ModBlocks.calcite_wall, "calcite");
        wallBasic(ModBlocks.cut_copper_wall, "cut_copper");
        wallBasic(ModBlocks.exposed_cut_copper_wall, "exposed_cut_copper");
        wallBasic(ModBlocks.weathered_cut_copper_wall, "weathered_cut_copper");
        wallBasic(ModBlocks.oxidized_cut_copper_wall, "oxidized_cut_copper");

        saddleDoorHidden(ModBlocks.iron_saddle_door, "iron_trapdoor");
        saddleDoorBasic(ModBlocks.gold_saddle_door, "gold");
        saddleDoorBasic(ModBlocks.diamond_saddle_door, "diamond");
        saddleDoorBasic(ModBlocks.emerald_saddle_door, "emerald");
        saddleDoorBasic(ModBlocks.lapis_saddle_door, "lapis");
        saddleDoorBasic(ModBlocks.obsidian_saddle_door, "obsidian");
        saddleDoorBasic(ModBlocks.coal_saddle_door, "coal");
        saddleDoorBasic(ModBlocks.redstone_saddle_door, "redstone");
        saddleDoorBasic(ModBlocks.missingno_saddle_door, locMod("missingno"));
        saddleDoorBasic(ModBlocks.clay_saddle_door, "clay");
        saddleDoorHidden(ModBlocks.dirt_saddle_door, "dirt");
        modelSaddleDoorBlock(ModBlocks.grass_saddle_door, "grass", true);
        saddleDoorBasic(ModBlocks.hay_saddle_door, "hay");
        modelSaddleDoorBlock(ModBlocks.path_saddle_door, "path", false);
        saddleDoorHidden(ModBlocks.brick_saddle_door, "bricks");
        saddleDoorBasic(ModBlocks.quartz_saddle_door, "quartz");
        saddleDoorBasic(ModBlocks.bone_saddle_door, "bone");
        saddleDoorBasic(ModBlocks.nether_brick_saddle_door, "nether_brick");
        saddleDoorBasic(ModBlocks.red_nether_brick_saddle_door, "red_nether_brick");
        saddleDoorBasic(ModBlocks.snow_saddle_door, "snow");
        saddleDoorBasic(ModBlocks.ice_saddle_door, "ice", TRANSLUCENT);
        saddleDoorBasic(ModBlocks.packed_ice_saddle_door, "packed_ice");
        saddleDoorBasic(ModBlocks.blue_ice_saddle_door, "blue_ice");
        saddleDoorBasic(ModBlocks.netherite_saddle_door, "netherite");
        saddleDoorBasic(ModBlocks.amethyst_saddle_door, "amethyst");
        saddleDoorHidden(ModBlocks.copper_saddle_door, "copper_trapdoor");
        saddleDoorHidden(ModBlocks.exposed_copper_saddle_door, "exposed_copper_trapdoor");
        saddleDoorHidden(ModBlocks.weathered_copper_saddle_door, "weathered_copper_trapdoor");
        saddleDoorHidden(ModBlocks.oxidized_copper_saddle_door, "oxidized_copper_trapdoor");
        saddleDoorBasic(ModBlocks.magma_saddle_door, "magma");
        saddleDoorBasic(ModBlocks.calcite_saddle_door, "calcite");
        saddleDoorBasic(ModBlocks.cut_copper_saddle_door, "cut_copper");
        saddleDoorBasic(ModBlocks.exposed_cut_copper_saddle_door, "exposed_cut_copper");
        saddleDoorBasic(ModBlocks.weathered_cut_copper_saddle_door, "weathered_cut_copper");
        saddleDoorBasic(ModBlocks.oxidized_cut_copper_saddle_door, "oxidized_cut_copper");

        supportBasic(ModBlocks.iron_support, "iron_block");
        supportBasic(ModBlocks.gold_support, "gold_block");
        supportBasic(ModBlocks.diamond_support, "diamond_block");
        supportBasic(ModBlocks.emerald_support, "emerald_block");
        supportBasic(ModBlocks.lapis_support, "lapis_block");
        supportBasic(ModBlocks.obsidian_support, "obsidian");
        supportBasic(ModBlocks.coal_support, "coal_block");
        supportBasic(ModBlocks.redstone_support, "redstone_block");
        supportBasic(ModBlocks.missingno_support, locMod("missingno"), SOLID);
        supportBasic(ModBlocks.clay_support, "clay");
        supportBasic(ModBlocks.dirt_support, "dirt");
        dirtSupportBlock(ModBlocks.grass_support, "grass", true);
        supportColumn(ModBlocks.hay_support, "hay_block_side", "hay_block_top", SOLID);
        dirtSupportBlock(ModBlocks.path_support, "path", false);
        supportBasic(ModBlocks.brick_support, "bricks");
        supportColumn(ModBlocks.quartz_support, "quartz_block_side", "quartz_block_top", SOLID);
        supportColumn(ModBlocks.bone_support, "bone_block_side", "bone_block_top", SOLID);
        supportBasic(ModBlocks.nether_brick_support, "nether_bricks");
        supportBasic(ModBlocks.red_nether_brick_support, "red_nether_bricks");
        supportBasic(ModBlocks.snow_support, "snow");
        supportBasic(ModBlocks.ice_support, "ice", TRANSLUCENT);
        supportBasic(ModBlocks.packed_ice_support, "packed_ice");
        supportBasic(ModBlocks.blue_ice_support, "blue_ice");
        supportBasic(ModBlocks.netherite_support, "netherite_block");
        supportBasic(ModBlocks.copper_support, "copper_block");
        supportBasic(ModBlocks.exposed_copper_support, "exposed_copper");
        supportBasic(ModBlocks.weathered_copper_support, "weathered_copper");
        supportBasic(ModBlocks.oxidized_copper_support, "oxidized_copper");
        supportBasic(ModBlocks.amethyst_support, "amethyst_block");
        supportBasic(ModBlocks.magma_support, "magma");
        supportBasic(ModBlocks.calcite_support, "calcite");
        supportBasic(ModBlocks.cut_copper_support, "cut_copper");
        supportBasic(ModBlocks.exposed_cut_copper_support, "exposed_cut_copper");
        supportBasic(ModBlocks.weathered_cut_copper_support, "weathered_cut_copper");
        supportBasic(ModBlocks.oxidized_cut_copper_support, "oxidized_cut_copper");
    }

    public void modelStairsBlock(Supplier<? extends OrnamentStair> block, String path) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation stairs = key.withPath(s -> dir + s);
        ResourceLocation innerstairs = key.withPath(s -> dir + s + "_inner");
        ResourceLocation outerstairs = key.withPath(s -> dir + s + "_outer");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(), innerstairs, stairs, outerstairs));
        this.blockModels.registerSimpleItemModel(block.get().asItem(), stairs);
    }

    public void hexamodelStairsBlock(Supplier<? extends OrnamentStair> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation bottomstraight = key.withPath(s -> dir + s + "_bottom_straight");
        ResourceLocation topstraight = key.withPath(s -> dir + s + "_top_straight");
        ResourceLocation bottominner = key.withPath(s -> dir + s + "_bottom_inner");
        ResourceLocation topinner = key.withPath(s -> dir + s + "_top_inner");
        ResourceLocation bottomouter = key.withPath(s -> dir + s + "_bottom_outer");
        ResourceLocation topouter = key.withPath(s -> dir + s + "_top_outer");

        this.blockModels.blockStateOutput.accept(stairsBlock(block.get(), bottomstraight, topstraight, bottominner, topinner, bottomouter, topouter));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), bottomstraight, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), bottomstraight);
        }
    }

    public void modelSlabBlock(Supplier<? extends SlabBlock> block, Supplier<? extends Block> full, String path, boolean tint, ResourceLocation type) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation slab = key.withPath(s -> dir + s);
        ResourceLocation slabtop = key.withPath(s -> dir + s + "_top");
        ResourceLocation fullblock = new ExtendedModelTemplateBuilder()
                .parent(ModelLocationUtils.getModelLocation(full.get()))
                .suffix("_double")
                .renderType(type)
                .build().create(block.get(), new TextureMapping().put(TextureSlot.PARTICLE, ResourceLocation.withDefaultNamespace("dirt")), blockModels.modelOutput);

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSlab(block.get(), slab, slabtop, fullblock));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), slab, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), slab);
        }
    }

    public void modelFenceBlock(Supplier<? extends FenceBlock> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation post = key.withPath(s -> dir + s + "_post");
        ResourceLocation side = key.withPath(s -> dir + s + "_side");
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFence(block.get(), post, side));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public void modelTrapdoorBlock(Supplier<? extends TrapDoorBlock> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation bottom = key.withPath(s -> dir + s + "_bottom");
        ResourceLocation top = key.withPath(s -> dir + s + "_top");
        ResourceLocation open = key.withPath(s -> dir + s + "_open");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createTrapdoor(block.get(), bottom, top, open));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), bottom, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), bottom);
        }
    }

    public void modelFenceGateBlock(Supplier<? extends FenceGateBlock> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation gate = key.withPath(s -> dir + s);
        ResourceLocation open = key.withPath(s -> dir + s + "_open");
        ResourceLocation wall = key.withPath(s -> dir + s + "_wall");
        ResourceLocation wallopen = key.withPath(s -> dir + s + "_wall_open");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFenceGate(block.get(), gate, open, wall, wallopen, false));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), gate, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), gate);
        }
    }

    public void halfDirtDoorBlock(Supplier<? extends DoorBlock> block, String path) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation bottomleft = ModelLocationUtils.getModelLocation(ModBlocks.dirt_door.get(), "_bottom_left");
        ResourceLocation bottomleftopen = ModelLocationUtils.getModelLocation(ModBlocks.dirt_door.get(), "_bottom_left_open");
        ResourceLocation bottomright = ModelLocationUtils.getModelLocation(ModBlocks.dirt_door.get(), "_bottom_right");
        ResourceLocation bottomrightopen = ModelLocationUtils.getModelLocation(ModBlocks.dirt_door.get(), "_bottom_right_open");
        ResourceLocation topleft = key.withPath(s -> dir + s + "_top_left");
        ResourceLocation topleftopen = key.withPath(s -> dir + s + "_top_left_open");
        ResourceLocation topright = key.withPath(s -> dir + s + "_top_right");
        ResourceLocation toprightopen = key.withPath(s -> dir + s + "_top_right_open");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(block.get(), bottomleft, bottomleftopen, bottomright, bottomrightopen, topleft, topleftopen, topright, toprightopen));
        this.blockModels.registerSimpleFlatItemModel(block.get().asItem());
    }

    public void modelPoleBlock(Supplier<? extends OrnamentPole> block, String path, Supplier<? extends Block> full, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation whole = key.withPath(s -> dir + s + "_whole");
        ResourceLocation horizon = key.withPath(s -> dir + s + "_horizontal");
        ResourceLocation vertical = key.withPath(s -> dir + s + "_vertical");
        ResourceLocation corner = key.withPath(s -> dir + s + "_corner");
        ResourceLocation fullblock = ModelLocationUtils.getModelLocation(full.get());
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(poleBlock(block, whole, horizon, vertical, corner, fullblock));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public void halfDirtBeamBlock(Supplier<? extends OrnamentBeam> block, Supplier<? extends Block> full, String path, boolean noface, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation wholetop = key.withPath(s -> dir + s + "_whole_top");
        ResourceLocation wholebottom = key.withPath(s -> dir + s + "_whole_bottom");
        ResourceLocation horizontop = key.withPath(s -> dir + s + "_horizontal_top");
        ResourceLocation horizonbottom = key.withPath(s -> dir + s + "_horizontal_bottom");
        ResourceLocation verticaltop = key.withPath(s -> dir + s + "_vertical");
        ResourceLocation verticalbottom = ModelLocationUtils.getModelLocation(ModBlocks.dirt_beam.get(), "_vertical");
        ResourceLocation cornertop = key.withPath(s -> dir + s + "_corner");
        ResourceLocation cornerbottom = ModelLocationUtils.getModelLocation(ModBlocks.dirt_beam.get(), noface ? "_corner" : "_whole");
        ResourceLocation fullblock = ModelLocationUtils.getModelLocation(full.get());
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(beamBlock(block, wholetop, wholebottom, horizontop, horizonbottom, verticaltop, verticalbottom, cornertop, cornerbottom, fullblock));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public void modelWallBlock(Supplier<? extends WallBlock> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation post = key.withPath(s -> dir + s + "_post");
        ResourceLocation side = key.withPath(s -> dir + s + "_side");
        ResourceLocation tall = key.withPath(s -> dir + s + "_side_tall");
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(BlockModelGenerators.createWall(block.get(), post, side, tall));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public void modelIceWall(Supplier<? extends OrnamentWall> block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/ice/";
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(iceWall(block));
        blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
    }

    public void modelSaddleDoorBlock(Supplier<? extends OrnamentSaddleDoor> block, String path, boolean tint) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation left = key.withPath(s -> dir + s + "_left");
        ResourceLocation leftOpen = key.withPath(s -> dir + s + "_left_open");
        ResourceLocation right = key.withPath(s -> dir + s + "_right");
        ResourceLocation rightOpen = key.withPath(s -> dir + s + "_right_open");
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(saddleDoorBlock(block, left, leftOpen, right, rightOpen));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public void dirtSupportBlock(Supplier<? extends OrnamentSupport> block, String path, boolean tint) {
        ResourceLocation dirt = BuiltInRegistries.BLOCK.getKey(ModBlocks.dirt_support.get());
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/" + path + "/";
        ResourceLocation base = key.withPath(s -> dir + s + "_base");
        ResourceLocation basetop = key.withPath(s -> dir + s + "_base_top");
        ResourceLocation vertical = key.withPath(s -> dir + s + "_vertical");
        ResourceLocation verticaltop = key.withPath(s -> dir + s + "_vertical_top");
        ResourceLocation x = key.withPath(s -> dir + s + "_horizontal_x");
        ResourceLocation xtop = key.withPath(s -> dir + s + "_horizontal_x_top");
        ResourceLocation z = key.withPath(s -> dir + s + "_horizontal_z");
        ResourceLocation ztop = key.withPath(s -> dir + s + "_horizontal_z_top");
        ResourceLocation inventory = key.withPath(s -> dir + s + "_inventory");

        this.blockModels.blockStateOutput.accept(dirtSupportBlock(block, base, basetop, vertical, verticaltop, x, xtop, z, ztop, dirt.withPath(s -> "block/" + s + "_base")));
        if (tint) {
            blockModels.registerSimpleTintedItemModel(block.get(), inventory, new GrassColorSource());
        } else {
            blockModels.registerSimpleItemModel(block.get().asItem(), inventory);
        }
    }

    public BlockStateGenerator stairsBlock(StairBlock block, ResourceLocation bs, ResourceLocation ts, ResourceLocation bi, ResourceLocation ti, ResourceLocation bo, ResourceLocation to) {
        PropertyDispatch.C3<Direction, Half, StairsShape> props = PropertyDispatch.properties(StairBlock.FACING, StairBlock.HALF, StairBlock.SHAPE);

        for (Direction dir : Direction.values()) {
            if (!dir.getAxis().isVertical()) {
                for (Half half : Half.values()) {
                    for (StairsShape shape: StairsShape.values()) {
                        if (shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT)
                            selectStair(props, dir, half, shape, half == Half.TOP ? ti : bi);
                        if (shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT)
                            selectStair(props, dir, half, shape, half == Half.TOP ? to : bo);
                        if (shape == StairsShape.STRAIGHT)
                            selectStair(props, dir, half, shape, half == Half.TOP ? ts : bs);
                    }
                }
            }
        }

        return MultiVariantGenerator.multiVariant(block).with(props);
    }

    public void selectStair(PropertyDispatch.C3<Direction, Half, StairsShape> props, Direction dir, Half half, StairsShape shape, ResourceLocation model) {
        int rot = (int) dir.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
        if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT)
            rot += 270; // Left facing stairs are rotated 90 degrees clockwise
        if (shape == StairsShape.STRAIGHT && half == Half.TOP)
            rot += 90; // Top stairs are rotated 90 degrees clockwise
        VariantProperties.Rotation yRot = INT_TO_ROT.get(rot % 360);

        props.select(
                dir, half, shape,
                Variant.variant()
                        .with(VariantProperties.MODEL, model)
                        .with(VariantProperties.X_ROT, half == Half.BOTTOM ? VariantProperties.Rotation.R0 : VariantProperties.Rotation.R180)
                        .with(VariantProperties.Y_ROT, yRot)
                        .with(VariantProperties.UV_LOCK, yRot != VariantProperties.Rotation.R0 || half == Half.TOP)); // Don't set uvlock for states that have no rotation
    }

    public BlockStateGenerator beamBlock(Supplier<? extends OrnamentBeam> block, ResourceLocation wt, ResourceLocation wb, ResourceLocation ht, ResourceLocation hb, ResourceLocation vt, ResourceLocation vb, ResourceLocation ct, ResourceLocation cb, ResourceLocation fullblock) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        beamModelWhole(builder, wt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, wt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, wt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, wt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelWhole(builder, wb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, ht, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, ht, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, ht, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, ht, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, hb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, hb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, hb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, hb, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_RIGHT);
        beamModelLength(builder, vt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vt, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_LEFT);
        beamModelLength(builder, vb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R90, Direction.Axis.X, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R0, Direction.Axis.Z, OrnamentBeam.BOTTOM_LEFT, OrnamentBeam.TOP_LEFT, OrnamentBeam.BOTTOM_RIGHT);
        beamModelLength(builder, vb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R270, Direction.Axis.X, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelLength(builder, vb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R180, Direction.Axis.Z, OrnamentBeam.BOTTOM_RIGHT, OrnamentBeam.TOP_RIGHT, OrnamentBeam.BOTTOM_LEFT);
        beamModelCorner(builder, ct, VariantProperties.Rotation.R0, VariantProperties.Rotation.R0, Direction.Axis.X, true, true, true, false);
        beamModelCorner(builder, ct, VariantProperties.Rotation.R0, VariantProperties.Rotation.R270, Direction.Axis.Z, true, true, true, false);
        beamModelCorner(builder, ct, VariantProperties.Rotation.R0, VariantProperties.Rotation.R180, Direction.Axis.X, true, true, false, true);
        beamModelCorner(builder, ct, VariantProperties.Rotation.R0, VariantProperties.Rotation.R90, Direction.Axis.Z, true, true, false, true);
        beamModelCorner(builder, cb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R270, Direction.Axis.X, false, true, true, true);
        beamModelCorner(builder, cb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R180, Direction.Axis.Z, false, true, true, true);
        beamModelCorner(builder, cb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R90, Direction.Axis.X, true, false, true, true);
        beamModelCorner(builder, cb, VariantProperties.Rotation.R90, VariantProperties.Rotation.R0, Direction.Axis.Z, true, false, true, true);

        return builder.with(
                Condition.condition().term(OrnamentBeam.TOP_LEFT, true).term(OrnamentBeam.TOP_RIGHT, true).term(OrnamentBeam.BOTTOM_LEFT, true).term(OrnamentBeam.BOTTOM_RIGHT, true),
                Variant.variant().with(VariantProperties.MODEL, fullblock));
    }

    public BlockStateGenerator iceWall(Supplier<? extends OrnamentWall> block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block.get());
        String dir = "block/ice/";

        ResourceLocation post = key.withPath(s -> dir + s + "_post");
        ResourceLocation postside = key.withPath(s -> dir + s + "_post_side");
        ResourceLocation postsidetall = key.withPath(s -> dir + s + "_side");
        ResourceLocation side = key.withPath(s -> dir + s + "_side");
        ResourceLocation sidetall = key.withPath(s -> dir + s + "_side_tall");
        ResourceLocation sidetallp = key.withPath(s -> dir + s + "_side_tall_piece");
        ResourceLocation seg = key.withPath(s -> dir + s + "_segment");

        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());

        builder.with(
                Condition.condition().term(OrnamentWall.UP, true),
                Variant.variant().with(VariantProperties.MODEL, post));
        builder.with(
                Condition.condition().term(OrnamentWall.UP, false),
                Variant.variant().with(VariantProperties.MODEL, seg));
        iceWallRotation(builder, OrnamentWall.EAST_WALL, VariantProperties.Rotation.R90, postside, side, postsidetall, sidetall, sidetallp);
        iceWallRotation(builder, OrnamentWall.NORTH_WALL, VariantProperties.Rotation.R0, postside, side, postsidetall, sidetall, sidetallp);
        iceWallRotation(builder, OrnamentWall.SOUTH_WALL, VariantProperties.Rotation.R180, postside, side, postsidetall, sidetall, sidetallp);
        iceWallRotation(builder, OrnamentWall.WEST_WALL, VariantProperties.Rotation.R270, postside, side, postsidetall, sidetall, sidetallp);

        return builder;
    }

    public void iceWallRotation(MultiPartGenerator builder, EnumProperty<WallSide> wallside, VariantProperties.Rotation rotation, ResourceLocation postside, ResourceLocation side, ResourceLocation postsidetall, ResourceLocation sidetall, ResourceLocation sidetallp) {
        builder.with(
                Condition.condition().term(OrnamentWall.UP, true).term(wallside, WallSide.LOW),
                Variant.variant().with(VariantProperties.MODEL, postside).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentWall.UP, false).term(wallside, WallSide.LOW),
                Variant.variant().with(VariantProperties.MODEL, side).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentWall.UP, true).term(wallside, WallSide.TALL),
                Variant.variant().with(VariantProperties.MODEL, postsidetall).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentWall.UP, false).term(wallside, WallSide.TALL),
                Variant.variant().with(VariantProperties.MODEL, sidetall).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentWall.UP, false).term(wallside, WallSide.TALL),
                Variant.variant().with(VariantProperties.MODEL, sidetallp).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
    }

    public BlockStateGenerator dirtSupportBlock(Supplier<? extends OrnamentSupport> block, ResourceLocation base, ResourceLocation basetop, ResourceLocation vertical, ResourceLocation verticaltop, ResourceLocation horizontalX, ResourceLocation horizontalXtop, ResourceLocation horizontalZ, ResourceLocation horizontalZtop, ResourceLocation dirt) {
        MultiPartGenerator builder = MultiPartGenerator.multiPart(block.get());
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, false),
                Variant.variant().with(VariantProperties.MODEL, base).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, false),
                Variant.variant().with(VariantProperties.MODEL, base).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, false),
                Variant.variant().with(VariantProperties.MODEL, base).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, false),
                Variant.variant().with(VariantProperties.MODEL, base).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, true),
                Variant.variant().with(VariantProperties.MODEL, dirt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, true),
                Variant.variant().with(VariantProperties.MODEL, dirt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, true),
                Variant.variant().with(VariantProperties.MODEL, dirt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.UPPER_HALF, false).term(OrnamentSupport.TB_CONNECT, true),
                Variant.variant().with(VariantProperties.MODEL, dirt).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, basetop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, basetop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, basetop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, basetop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, vertical).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, vertical).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, vertical).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, vertical).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, verticaltop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, verticaltop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, verticaltop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.TB_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, verticaltop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalZ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalX).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalZ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalX).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalZtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalXtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalZtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.NS_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalXtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalX).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalZ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalX).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, false),
                Variant.variant().with(VariantProperties.MODEL, horizontalZ).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_LEFT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalXtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.TOP_RIGHT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalZtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_RIGHT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalXtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true));
        builder.with(
                Condition.condition().term(OrnamentSupport.CORNER, OrnamentSupport.CornerType.BOTTOM_LEFT).term(OrnamentSupport.EW_CONNECT, true).term(OrnamentSupport.UPPER_HALF, true),
                Variant.variant().with(VariantProperties.MODEL, horizontalZtop).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true));

        return builder;
    }

}
