package com.androsa.ornamental.data;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.OrnamentSaddleDoor;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class OrnamentalBlockStates extends OrnamentalBlockStateProvider {

    public OrnamentalBlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        stairsBasic(ModBlocks.iron_stairs, "iron_block");
        stairsBasic(ModBlocks.gold_stairs, "gold_block");
        stairsBasic(ModBlocks.diamond_stairs, "diamond_block");
        stairsBasic(ModBlocks.emerald_stairs, "emerald_block");
        stairsBasic(ModBlocks.lapis_stairs, "lapis_block");
        stairsBasic(ModBlocks.obsidian_stairs, "obsidian");
        stairsBasic(ModBlocks.coal_stairs, "coal_block");
        stairsBasic(ModBlocks.redstone_stairs, "redstone_block");
        stairsMissing(ModBlocks.missingno_stairs);
        stairsBasic(ModBlocks.clay_stairs, "clay");
        stairsBasic(ModBlocks.dirt_stairs, "dirt");
        hexamodelStairsBlock(ModBlocks.grass_stairs, "grass");
        stairsColumn(ModBlocks.hay_stairs, "hay_block_side", "hay_block_top");
        hexamodelStairsBlock(ModBlocks.path_stairs, "path");
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

        slabBasic(ModBlocks.iron_slab, "iron_block");
        slabBasic(ModBlocks.gold_slab, "gold_block");
        slabBasic(ModBlocks.diamond_slab, "diamond_block");
        slabBasic(ModBlocks.emerald_slab, "emerald_block");
        slabBasic(ModBlocks.lapis_slab, "lapis_block");
        slabBasic(ModBlocks.obsidian_slab, "obsidian");
        slabBasic(ModBlocks.coal_slab, "coal_block");
        slabBasic(ModBlocks.redstone_slab, "redstone_block");
        slabMissing(ModBlocks.missingno_slab);
        slabBasic(ModBlocks.clay_slab, "clay");
        slabBasic(ModBlocks.dirt_slab, "dirt");
        modelSlabBlock(ModBlocks.grass_slab, "grass_block", "grass");
        slabColumn(ModBlocks.hay_slab, "hay_block", "hay_block_side", "hay_block_top");
        modelSlabBlock(ModBlocks.path_slab, "dirt_path", "path");
        slabColumn(ModBlocks.bone_slab, "bone_block", "bone_block_side", "bone_block_top");
        slabModel(ModBlocks.snow_slab, "snow_block", "snow");
        slabBasic(ModBlocks.ice_slab, "ice", TRANSLUCENT);
        slabBasic(ModBlocks.packed_ice_slab, "packed_ice");
        slabBasic(ModBlocks.blue_ice_slab, "blue_ice");
        slabBasic(ModBlocks.netherite_slab, "netherite_block");
        slabBasic(ModBlocks.amethyst_slab, "amethyst_block");
        slabBasic(ModBlocks.copper_slab, "copper_block");
        slabBasic(ModBlocks.exposed_copper_slab, "exposed_copper");
        slabBasic(ModBlocks.weathered_copper_slab, "weathered_copper");
        slabBasic(ModBlocks.oxidized_copper_slab, "oxidized_copper");

        fenceBasic(ModBlocks.iron_fence, "iron_block");
        fenceBasic(ModBlocks.gold_fence, "gold_block");
        fenceBasic(ModBlocks.diamond_fence, "diamond_block");
        fenceBasic(ModBlocks.emerald_fence, "emerald_block");
        fenceBasic(ModBlocks.lapis_fence, "lapis_block");
        fenceBasic(ModBlocks.obsidian_fence, "obsidian");
        fenceBasic(ModBlocks.coal_fence, "coal_block");
        fenceBasic(ModBlocks.redstone_fence, "redstone_block");
        fenceMissing(ModBlocks.missingno_fence);
        fenceBasic(ModBlocks.clay_fence, "clay");
        fenceBasic(ModBlocks.dirt_fence, "dirt");
        modelFenceBlock(ModBlocks.grass_fence, "grass");
        fenceColumn(ModBlocks.hay_fence, "hay_block_side", "hay_block_top");
        modelFenceBlock(ModBlocks.path_fence, "path");
        fenceBasic(ModBlocks.brick_fence, "bricks");
        fenceColumn(ModBlocks.quartz_fence, "quartz_block_side", "quartz_block_top");
        fenceColumn(ModBlocks.bone_fence, "bone_block_side", "bone_block_top");
        fenceBasic(ModBlocks.red_nether_brick_fence, "red_nether_bricks");
        fenceBasic(ModBlocks.snow_fence, "snow");
        modelFenceBlock(ModBlocks.ice_fence, "ice");
        fenceBasic(ModBlocks.packed_ice_fence, "packed_ice");
        fenceBasic(ModBlocks.blue_ice_fence, "blue_ice");
        fenceBasic(ModBlocks.netherite_fence, "netherite_block");
        fenceBasic(ModBlocks.amethyst_fence, "amethyst_block");
        fenceBasic(ModBlocks.copper_fence, "copper_block");
        fenceBasic(ModBlocks.exposed_copper_fence, "exposed_copper");
        fenceBasic(ModBlocks.weathered_copper_fence, "weathered_copper");
        fenceBasic(ModBlocks.oxidized_copper_fence, "oxidized_copper");

        trapdoorBasic(ModBlocks.gold_trapdoor, "gold");
        trapdoorBasic(ModBlocks.diamond_trapdoor, "diamond");
        trapdoorBasic(ModBlocks.emerald_trapdoor, "emerald");
        trapdoorBasic(ModBlocks.lapis_trapdoor, "lapis");
        trapdoorBasic(ModBlocks.obsidian_trapdoor, "obsidian");
        trapdoorBasic(ModBlocks.coal_trapdoor, "coal");
        trapdoorBasic(ModBlocks.redstone_trapdoor, "redstone");
        trapdoorMissing(ModBlocks.missingno_trapdoor);
        trapdoorBasic(ModBlocks.clay_trapdoor, "clay");
        trapdoorVanilla(ModBlocks.dirt_trapdoor, "dirt");
        modelTrapdoorBlock(ModBlocks.grass_trapdoor, "grass");
        trapdoorBasic(ModBlocks.hay_trapdoor, "hay");
        modelTrapdoorBlock(ModBlocks.path_trapdoor, "path");
        trapdoorVanilla(ModBlocks.brick_trapdoor, "bricks");
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
        trapdoorBasic(ModBlocks.copper_trapdoor, "copper");
        trapdoorBasic(ModBlocks.exposed_copper_trapdoor, "exposed_copper");
        trapdoorBasic(ModBlocks.weathered_copper_trapdoor, "weathered_copper");
        trapdoorBasic(ModBlocks.oxidized_copper_trapdoor, "oxidized_copper");

        fenceGateBasic(ModBlocks.iron_fence_gate, "iron_block");
        fenceGateBasic(ModBlocks.gold_fence_gate, "gold_block");
        fenceGateBasic(ModBlocks.diamond_fence_gate, "diamond_block");
        fenceGateBasic(ModBlocks.emerald_fence_gate, "emerald_block");
        fenceGateBasic(ModBlocks.lapis_fence_gate, "lapis_block");
        fenceGateBasic(ModBlocks.obsidian_fence_gate, "obsidian");
        fenceGateBasic(ModBlocks.coal_fence_gate, "coal_block");
        fenceGateBasic(ModBlocks.redstone_fence_gate, "redstone_block");
        fenceGateMissing(ModBlocks.missingno_fence_gate);
        fenceGateBasic(ModBlocks.clay_fence_gate, "clay");
        fenceGateBasic(ModBlocks.dirt_fence_gate, "dirt");
        modelFenceGateBlock(ModBlocks.grass_fence_gate, "grass");
        fenceGateColumn(ModBlocks.hay_fence_gate, "hay_block_side", "hay_block_top");
        modelFenceGateBlock(ModBlocks.path_fence_gate, "path");
        fenceGateBasic(ModBlocks.brick_fence_gate, "bricks");
        fenceGateColumn(ModBlocks.quartz_fence_gate, "quartz_block_side", "quartz_block_top");
        fenceGateColumn(ModBlocks.bone_fence_gate, "bone_block_side", "bone_block_top");
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

        doorBasic(ModBlocks.gold_door, "gold");
        doorBasic(ModBlocks.diamond_door, "diamond");
        doorBasic(ModBlocks.emerald_door, "emerald");
        doorBasic(ModBlocks.lapis_door, "lapis");
        doorBasic(ModBlocks.obsidian_door, "obsidian");
        doorBasic(ModBlocks.coal_door, "coal");
        doorBasic(ModBlocks.redstone_door, "redstone");
        doorMissing(ModBlocks.missingno_door);
        doorBasic(ModBlocks.clay_door, "clay");
        doorBasicVanilla(ModBlocks.dirt_door, "dirt");
        halfDirtDoorBlock(ModBlocks.grass_door, "grass");
        doorBasic(ModBlocks.hay_door, "hay");
        halfDirtDoorBlock(ModBlocks.path_door, "path");
        doorBasicVanilla(ModBlocks.brick_door, "bricks");
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
        doorBasic(ModBlocks.copper_door, "copper");
        doorBasic(ModBlocks.exposed_copper_door, "exposed_copper");
        doorBasic(ModBlocks.weathered_copper_door, "weathered_copper");
        doorBasic(ModBlocks.oxidized_copper_door, "oxidized_copper");

        poleBasic(ModBlocks.iron_pole, "iron_block");
        poleBasic(ModBlocks.gold_pole, "gold_block");
        poleBasic(ModBlocks.diamond_pole, "diamond_block");
        poleBasic(ModBlocks.emerald_pole, "emerald_block");
        poleBasic(ModBlocks.lapis_pole, "lapis_block");
        poleBasic(ModBlocks.obsidian_pole, "obsidian");
        poleBasic(ModBlocks.coal_pole, "coal_block");
        poleBasic(ModBlocks.redstone_pole, "redstone_block");
        poleMissing(ModBlocks.missingno_pole, "missingno");
        poleBasic(ModBlocks.clay_pole, "clay");
        poleBasic(ModBlocks.dirt_pole, "dirt");
        modelPoleBlock(ModBlocks.grass_pole, "grass", "grass_block");
        poleColumn(ModBlocks.hay_pole, "hay_block", "hay_block_side", "hay_block_top");
        modelPoleBlock(ModBlocks.path_pole, "path", "dirt_path");
        poleBasic(ModBlocks.brick_pole, "bricks");
        poleColumn(ModBlocks.quartz_pole, "quartz_block", "quartz_block_side", "quartz_block_top");
        poleColumn(ModBlocks.bone_pole, "bone_block", "bone_block_side", "bone_block_top");
        poleBasic(ModBlocks.nether_brick_pole, "nether_bricks");
        poleBasic(ModBlocks.red_nether_brick_pole, "red_nether_bricks");
        poleBasic(ModBlocks.snow_pole, "snow_block", "snow", SOLID);
        poleBasic(ModBlocks.ice_pole, "ice", TRANSLUCENT);
        poleBasic(ModBlocks.packed_ice_pole, "packed_ice");
        poleBasic(ModBlocks.blue_ice_pole, "blue_ice");
        poleBasic(ModBlocks.netherite_pole, "netherite_block");
        poleBasic(ModBlocks.amethyst_pole, "amethyst_block");
        poleBasic(ModBlocks.copper_pole, "copper_block");
        poleBasic(ModBlocks.exposed_copper_pole, "exposed_copper");
        poleBasic(ModBlocks.weathered_copper_pole, "weathered_copper");
        poleBasic(ModBlocks.oxidized_copper_pole, "oxidized_copper");

        beamBasic(ModBlocks.iron_beam, "iron_block");
        beamBasic(ModBlocks.gold_beam, "gold_block");
        beamBasic(ModBlocks.diamond_beam, "diamond_block");
        beamBasic(ModBlocks.emerald_beam, "emerald_block");
        beamBasic(ModBlocks.lapis_beam, "lapis_block");
        beamBasic(ModBlocks.obsidian_beam, "obsidian");
        beamBasic(ModBlocks.coal_beam, "coal_block");
        beamBasic(ModBlocks.redstone_beam, "redstone_block");
        beamMissing(ModBlocks.missingno_beam, "missingno");
        beamBasic(ModBlocks.clay_beam, "clay");
        beamBasic(ModBlocks.dirt_beam, "dirt");
        halfDirtBeamBlock(ModBlocks.grass_beam, "grass_block", "grass", true);
        beamColumn(ModBlocks.hay_beam, "hay_block", "hay_block_top", "hay_block_side");
        halfDirtBeamBlock(ModBlocks.path_beam, "dirt_path", "path", false);
        beamBasic(ModBlocks.brick_beam, "bricks");
        beamColumn(ModBlocks.quartz_beam, "quartz_block", "quartz_block_top", "quartz_block_side");
        beamColumn(ModBlocks.bone_beam, "bone_block", "bone_block_top", "bone_block_side");
        beamBasic(ModBlocks.nether_brick_beam, "nether_bricks");
        beamBasic(ModBlocks.red_nether_brick_beam, "red_nether_bricks");
        beamBasic(ModBlocks.snow_beam, "snow", "snow_block", SOLID);
        beamBasic(ModBlocks.ice_beam, "ice", TRANSLUCENT);
        beamBasic(ModBlocks.packed_ice_beam, "packed_ice");
        beamBasic(ModBlocks.blue_ice_beam, "blue_ice");
        beamBasic(ModBlocks.netherite_beam, "netherite_block");
        beamBasic(ModBlocks.amethyst_beam, "amethyst_block");
        beamBasic(ModBlocks.copper_beam, "copper_block");
        beamBasic(ModBlocks.exposed_copper_beam, "exposed_copper");
        beamBasic(ModBlocks.weathered_copper_beam, "weathered_copper");
        beamBasic(ModBlocks.oxidized_copper_beam, "oxidized_copper");

        wallBasic(ModBlocks.iron_wall, "iron_block");
        wallBasic(ModBlocks.gold_wall, "gold_block");
        wallBasic(ModBlocks.diamond_wall, "diamond_block");
        wallBasic(ModBlocks.emerald_wall, "emerald_block");
        wallBasic(ModBlocks.lapis_wall, "lapis_block");
        wallBasic(ModBlocks.obsidian_wall, "obsidian");
        wallBasic(ModBlocks.coal_wall, "coal_block");
        wallBasic(ModBlocks.redstone_wall, "redstone_block");
        wallMissing(ModBlocks.missingno_wall, "missingno");
        wallBasic(ModBlocks.clay_wall, "clay");
        wallBasic(ModBlocks.dirt_wall, "dirt");
        modelWallBlock(ModBlocks.grass_wall, "grass");
        wallColumn(ModBlocks.hay_wall, "hay_block_side", "hay_block_top");
        modelWallBlock(ModBlocks.path_wall, "path");
        wallColumn(ModBlocks.quartz_wall, "quartz_block_side", "quartz_block_top");
        wallColumn(ModBlocks.bone_wall, "bone_block_side", "bone_block_top");
        wallBasic(ModBlocks.snow_wall, "snow");
        wallBasic(ModBlocks.packed_ice_wall, "packed_ice");
        wallBasic(ModBlocks.blue_ice_wall, "blue_ice");
        wallBasic(ModBlocks.netherite_wall, "netherite_block");
        wallBasic(ModBlocks.amethyst_wall, "amethyst_block");
        wallBasic(ModBlocks.copper_wall, "copper_block");
        wallBasic(ModBlocks.exposed_copper_wall, "exposed_copper");
        wallBasic(ModBlocks.weathered_copper_wall, "weathered_copper");
        wallBasic(ModBlocks.oxidized_copper_wall, "oxidized_copper");

        saddleDoorVanilla(ModBlocks.iron_saddle_door, "iron_trapdoor");
        saddleDoorBasic(ModBlocks.gold_saddle_door, "gold");
        saddleDoorBasic(ModBlocks.diamond_saddle_door, "diamond");
        saddleDoorBasic(ModBlocks.emerald_saddle_door, "emerald");
        saddleDoorBasic(ModBlocks.lapis_saddle_door, "lapis");
        saddleDoorBasic(ModBlocks.obsidian_saddle_door, "obsidian");
        saddleDoorBasic(ModBlocks.coal_saddle_door, "coal");
        saddleDoorBasic(ModBlocks.redstone_saddle_door, "redstone");
        saddleDoorMissing(ModBlocks.missingno_saddle_door, "missingno");
        saddleDoorBasic(ModBlocks.clay_saddle_door, "clay");
        saddleDoorVanilla(ModBlocks.dirt_saddle_door, "dirt");
        modelSaddleDoorBlock(ModBlocks.grass_saddle_door, "grass");
        saddleDoorBasic(ModBlocks.hay_saddle_door, "hay");
        modelSaddleDoorBlock(ModBlocks.path_saddle_door, "path");
        saddleDoorVanilla(ModBlocks.brick_saddle_door, "bricks");
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
        saddleDoorBasic(ModBlocks.copper_saddle_door, "copper");
        saddleDoorBasic(ModBlocks.exposed_copper_saddle_door, "exposed_copper");
        saddleDoorBasic(ModBlocks.weathered_copper_saddle_door, "weathered_copper");
        saddleDoorBasic(ModBlocks.oxidized_copper_saddle_door, "oxidized_copper");
    }

    public void modelStairsBlock(RegistryObject<? extends StairBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile stairs = models().getExistingFile(locOrnament(dir));
        ModelFile innerstairs = models().getExistingFile(locOrnament(dir + "_inner"));
        ModelFile outerstairs = models().getExistingFile(locOrnament(dir + "_outer"));

        stairsBlock(block.get(), stairs, innerstairs, outerstairs);
    }

    public void hexamodelStairsBlock(RegistryObject<? extends StairBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile bottomstraight = models().getExistingFile(locOrnament(dir + "_bottom_straight"));
        ModelFile topstraight = models().getExistingFile(locOrnament(dir + "_top_straight"));
        ModelFile bottominner = models().getExistingFile(locOrnament(dir + "_bottom_inner"));
        ModelFile topinner = models().getExistingFile(locOrnament(dir + "_top_inner"));
        ModelFile bottomouter = models().getExistingFile(locOrnament(dir + "_bottom_outer"));
        ModelFile topouter = models().getExistingFile(locOrnament(dir + "_top_outer"));
        stairsBlock(block.get(), bottomstraight, topstraight, bottominner, topinner, bottomouter, topouter);
    }

    public void modelSlabBlock(RegistryObject<? extends SlabBlock> block, String full, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile slab = models().getExistingFile(locOrnament(dir));
        ModelFile slabtop = models().getExistingFile(locOrnament(dir + "_top"));
        ModelFile fullblock = models().forceRenderType(full, locVanilla(full), CUTOUT_MIPPED);

        slabBlock(block.get(), slab, slabtop, fullblock);
    }

    public void modelFenceBlock(RegistryObject<? extends FenceBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile post = models().getExistingFile(locOrnament(dir + "_post"));
        ModelFile side = models().getExistingFile(locOrnament(dir + "_side"));

        fourWayBlock(block.get(), post, side);
    }

    public void modelTrapdoorBlock(RegistryObject<? extends TrapDoorBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile bottom = models().getExistingFile(locOrnament(dir + "_bottom"));
        ModelFile top = models().getExistingFile(locOrnament(dir + "_top"));
        ModelFile open = models().getExistingFile(locOrnament(dir + "_open"));

        trapdoorBlock(block.get(), bottom, top, open, true);
    }

    public void modelFenceGateBlock(RegistryObject<? extends FenceGateBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile gate = models().getExistingFile(locOrnament(dir));
        ModelFile open = models().getExistingFile(locOrnament(dir + "_open"));
        ModelFile wall = models().getExistingFile(locOrnament(dir + "_wall"));
        ModelFile wallopen = models().getExistingFile(locOrnament(dir + "_wall_open"));

        fenceGateBlock(block.get(), gate, open, wall, wallopen);
    }

    public void halfDirtDoorBlock(RegistryObject<? extends DoorBlock> block, String path) {
        String dirt = ModBlocks.dirt_door.getId().getPath();
        String dir = path + "/" + block.getId().getPath();
        ModelFile bottomleft = models().getExistingFile(locOrnament(dirt + "_bottom_left"));
        ModelFile bottomleftopen = models().getExistingFile(locOrnament(dirt + "_bottom_left_open"));
        ModelFile bottomright = models().getExistingFile(locOrnament(dirt + "_bottom_right"));
        ModelFile bottomrightopen = models().getExistingFile(locOrnament(dirt + "_bottom_right_open"));
        ModelFile topleft = models().getExistingFile(locOrnament(dir + "_top_left"));
        ModelFile topleftopen = models().getExistingFile(locOrnament(dir + "_top_left_open"));
        ModelFile topright = models().getExistingFile(locOrnament(dir + "_top_right"));
        ModelFile toprightopen = models().getExistingFile(locOrnament(dir + "_top_right_open"));

        doorBlock(block.get(), bottomleft, bottomleftopen, bottomright, bottomrightopen, topleft, topleftopen, topright, toprightopen);
    }

    public void modelPoleBlock(RegistryObject<? extends OrnamentPole> block, String path, String full) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile whole = models().getExistingFile(locOrnament(dir + "_whole"));
        ModelFile horizon = models().getExistingFile(locOrnament(dir + "_horizontal"));
        ModelFile vertical = models().getExistingFile(locOrnament(dir + "_vertical"));
        ModelFile corner = models().getExistingFile(locOrnament(dir + "_corner"));
        ModelFile fullblock = models().forceRenderType(full, locVanilla(full), CUTOUT_MIPPED);

        poleBlock(block, whole, horizon, vertical, corner, fullblock);
    }

    public void halfDirtBeamBlock(RegistryObject<? extends OrnamentBeam> block, String full, String path, boolean noface) {
        String name = path + "/" + block.getId().getPath();
        ModelFile wholetop = models().getExistingFile(locOrnament(name + "_whole_top"));
        ModelFile wholebottom = models().getExistingFile(locOrnament(name + "_whole_bottom"));
        ModelFile horizontop = models().getExistingFile(locOrnament(name + "_horizontal_top"));
        ModelFile horizonbottom = models().getExistingFile(locOrnament(name + "_horizontal_bottom"));
        ModelFile verticaltop = models().getExistingFile(locOrnament(name + "_vertical"));
        ModelFile verticalbottom = models().getExistingFile(locOrnament("dirt_pole_vertical"));
        ModelFile cornertop = models().getExistingFile(locOrnament(name + "_corner"));
        ModelFile cornerbottom = models().getExistingFile(locOrnament(noface ? "dirt_pole_corner" : "dirt_pole_whole"));
        ModelFile fullblock = models().forceRenderType(full, locVanilla(full), CUTOUT);

        beamBlock(block, wholetop, wholebottom, horizontop, horizonbottom, verticaltop, verticalbottom, cornertop, cornerbottom, fullblock);
    }

    public void modelWallBlock(RegistryObject<? extends WallBlock> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile post = models().getExistingFile(locOrnament(dir + "_post"));
        ModelFile side = models().getExistingFile(locOrnament(dir + "_side"));
        ModelFile tall = models().getExistingFile(locOrnament(dir + "_side_tall"));

        wallBlock(block.get(), post, side, tall);
    }

    public void modelSaddleDoorBlock(RegistryObject<? extends OrnamentSaddleDoor> block, String path) {
        String dir = path + "/" + block.getId().getPath();
        ModelFile left = models().getExistingFile(locOrnament(dir + "_left"));
        ModelFile leftOpen = models().getExistingFile(locOrnament(dir + "_left_open"));
        ModelFile right = models().getExistingFile(locOrnament(dir + "_right"));
        ModelFile rightOpen = models().getExistingFile(locOrnament(dir + "_right_open"));

        saddleDoorBlock(block, left, leftOpen, right, rightOpen);
    }

    public void stairsBlock(StairBlock block, ModelFile bs, ModelFile ts, ModelFile bi, ModelFile ti, ModelFile bo, ModelFile to) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction facing = state.getValue(StairBlock.FACING);
                    Half half = state.getValue(StairBlock.HALF);
                    StairsShape shape = state.getValue(StairBlock.SHAPE);
                    int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some reason
                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
                        yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
                    }
                    if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
                        yRot += 90; // Top stairs are rotated 90 degrees clockwise
                    }
                    yRot %= 360;
                    boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
                    ModelFile model = bs;
                    switch (shape) {
                        case STRAIGHT -> model = half == Half.TOP ? ts : bs;
                        case INNER_LEFT, INNER_RIGHT -> model = half == Half.TOP ? ti : bi;
                        case OUTER_LEFT, OUTER_RIGHT -> model = half == Half.TOP ? to : bo;
                    }
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationX(half == Half.BOTTOM ? 0 : 180)
                            .rotationY(yRot)
                            .uvLock(uvlock)
                            .build();
                }, StairBlock.WATERLOGGED);
    }

    public void beamBlock(RegistryObject<? extends OrnamentBeam> block, ModelFile wt, ModelFile wb, ModelFile ht, ModelFile hb, ModelFile vt, ModelFile vb, ModelFile ct, ModelFile cb, ModelFile fullblock) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        beamModelWhole(builder, wt, 0, 0, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, wt, 0, 270, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, wt, 0, 180, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, wt, 0, 90, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, 0, 0, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, 0, 270, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelWhole(builder, wb, 0, 180, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelWhole(builder, wb, 0, 90, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, ht, 0, 0, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, ht, 0, 270, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, ht, 0, 180, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, ht, 0, 90, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, hb, 0, 0, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, hb, 0, 270, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, hb, 0, 180, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, hb, 0, 90, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vt, 0, 0, Direction.Axis.X, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vt, 0, 270, Direction.Axis.Z, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_RIGHT);
        beamModelLength(builder, vt, 0, 180, Direction.Axis.X, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, vt, 0, 90, Direction.Axis.Z, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_LEFT);
        beamModelLength(builder, vb, 90, 90, Direction.Axis.X, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, vb, 90, 0, Direction.Axis.Z, OrnamentPole.BOTTOM_LEFT, OrnamentPole.TOP_LEFT, OrnamentPole.BOTTOM_RIGHT);
        beamModelLength(builder, vb, 90, 270, Direction.Axis.X, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelLength(builder, vb, 90, 180, Direction.Axis.Z, OrnamentPole.BOTTOM_RIGHT, OrnamentPole.TOP_RIGHT, OrnamentPole.BOTTOM_LEFT);
        beamModelCorner(builder, ct, 0, 0, Direction.Axis.X, true, true, true, false);
        beamModelCorner(builder, ct, 0, 270, Direction.Axis.Z, true, true, true, false);
        beamModelCorner(builder, ct, 0, 180, Direction.Axis.X, true, true, false, true);
        beamModelCorner(builder, ct, 0, 90, Direction.Axis.Z, true, true, false, true);
        beamModelCorner(builder, cb, 90, 270, Direction.Axis.X, false, true, true, true);
        beamModelCorner(builder, cb, 90, 180, Direction.Axis.Z, false, true, true, true);
        beamModelCorner(builder, cb, 90, 90, Direction.Axis.X, true, false, true, true);
        beamModelCorner(builder, cb, 90, 0, Direction.Axis.Z, true, false, true, true);

        builder.part()
                .modelFile(fullblock)
                .addModel()
                .condition(OrnamentPole.TOP_LEFT, true)
                .condition(OrnamentPole.TOP_RIGHT, true)
                .condition(OrnamentPole.BOTTOM_LEFT, true)
                .condition(OrnamentPole.BOTTOM_RIGHT, true);
    }
}
