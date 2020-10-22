package com.androsa.ornamental.data;

import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.provider.OrnamentalBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

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
        stairsColumn(ModBlocks.hay_stairs, "hay_block_side", "hay_block_top");
        stairsColumn(ModBlocks.bone_stairs, "bone_block_side", "bone_block_top");
        stairsBasic(ModBlocks.snow_stairs, "snow");
        stairsBasic(ModBlocks.packed_ice_stairs, "packed_ice");
        stairsBasic(ModBlocks.blue_ice_stairs, "blue_ice");
        stairsBasic(ModBlocks.netherite_stairs, "netherite_block");

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
        slabColumn(ModBlocks.hay_slab, "hay_block", "hay_block_side", "hay_block_top");
        slabColumn(ModBlocks.bone_slab, "bone_block", "bone_block_side", "bone_block_top");
        slabModel(ModBlocks.snow_slab, "snow_block", "snow");
        slabBasic(ModBlocks.ice_slab, "ice");
        slabBasic(ModBlocks.packed_ice_slab, "packed_ice");
        slabBasic(ModBlocks.blue_ice_slab, "blue_ice");
        slabBasic(ModBlocks.netherite_slab, "netherite_block");

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
        fenceColumn(ModBlocks.hay_fence, "hay_block_side", "hay_block_top");
        fenceBasic(ModBlocks.brick_fence, "bricks");
        fenceColumn(ModBlocks.quartz_fence, "quartz_block_side", "quartz_block_top");
        fenceColumn(ModBlocks.bone_fence, "bone_block_side", "bone_block_top");
        fenceBasic(ModBlocks.red_nether_brick_fence, "red_nether_bricks");
        fenceBasic(ModBlocks.snow_fence, "snow");
        fenceBasic(ModBlocks.packed_ice_fence, "packed_ice");
        fenceBasic(ModBlocks.blue_ice_fence, "blue_ice");
        fenceBasic(ModBlocks.netherite_fence, "netherite_block");

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
        trapdoorBasic(ModBlocks.hay_trapdoor, "hay");
        trapdoorVanilla(ModBlocks.brick_trapdoor, "bricks");
        trapdoorBasic(ModBlocks.quartz_trapdoor, "quartz");
        trapdoorBasic(ModBlocks.bone_trapdoor, "bone");
        trapdoorBasic(ModBlocks.nether_brick_trapdoor, "nether_brick");
        trapdoorBasic(ModBlocks.red_nether_brick_trapdoor, "red_nether_brick");
        trapdoorBasic(ModBlocks.snow_trapdoor, "snow");
        trapdoorBasic(ModBlocks.ice_trapdoor, "ice");
        trapdoorBasic(ModBlocks.packed_ice_trapdoor, "packed_ice");
        trapdoorBasic(ModBlocks.blue_ice_trapdoor, "blue_ice");
        trapdoorBasic(ModBlocks.netherite_trapdoor, "netherite");

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
        fenceGateColumn(ModBlocks.hay_fence_gate, "hay_block_side", "hay_block_top");
        fenceGateBasic(ModBlocks.brick_fence_gate, "bricks");
        fenceGateColumn(ModBlocks.quartz_fence_gate, "quartz_block_side", "quartz_block_top");
        fenceGateColumn(ModBlocks.bone_fence_gate, "bone_block_side", "bone_block_top");
        fenceGateBasic(ModBlocks.nether_brick_fence_gate, "nether_bricks");
        fenceGateBasic(ModBlocks.red_nether_brick_fence_gate, "red_nether_bricks");
        fenceGateBasic(ModBlocks.snow_fence_gate, "snow");
        fenceGateBasic(ModBlocks.ice_fence_gate, "ice");
        fenceGateBasic(ModBlocks.packed_ice_fence_gate, "packed_ice");
        fenceGateBasic(ModBlocks.blue_ice_fence_gate, "blue_ice");
        fenceGateBasic(ModBlocks.netherite_fence_gate, "netherite_block");

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
        doorBasic(ModBlocks.hay_door, "hay");
        doorBasicVanilla(ModBlocks.brick_door, "bricks");
        doorBasic(ModBlocks.quartz_door, "quartz");
        doorBasic(ModBlocks.bone_door, "bone");
        doorBasic(ModBlocks.nether_brick_door, "nether_brick");
        doorBasic(ModBlocks.red_nether_brick_door, "red_nether_brick");
        doorBasic(ModBlocks.snow_door, "snow");
        doorBasic(ModBlocks.ice_door, "ice");
        doorBasic(ModBlocks.packed_ice_door, "packed_ice");
        doorBasic(ModBlocks.blue_ice_door, "blue_ice");
        doorBasic(ModBlocks.netherite_door, "netherite");

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
        poleColumn(ModBlocks.hay_pole, "hay_block");
        poleBasic(ModBlocks.brick_pole, "bricks");
        poleColumn(ModBlocks.quartz_pole, "quartz_block");
        poleColumn(ModBlocks.bone_pole, "bone_block");
        poleBasic(ModBlocks.nether_brick_pole, "nether_bricks");
        poleBasic(ModBlocks.red_nether_brick_pole, "red_nether_bricks");
        poleBasic(ModBlocks.snow_pole, "snow", "snow_block");
        poleBasic(ModBlocks.packed_ice_pole, "packed_ice");
        poleBasic(ModBlocks.blue_ice_pole, "blue_ice");
        poleBasic(ModBlocks.netherite_pole, "netherite_block");

        beamBasic(ModBlocks.iron_beam, "iron", "iron_block", false, false);
        beamBasic(ModBlocks.gold_beam, "gold", "gold_block", false, false);
        beamBasic(ModBlocks.diamond_beam, "diamond", "diamond_block", false, false);
        beamBasic(ModBlocks.emerald_beam, "emerald", "emerald_block", false, false);
        beamBasic(ModBlocks.lapis_beam, "lapis", "lapis_block", false, false);
        beamBasic(ModBlocks.obsidian_beam, "obsidian", "obsidian", false, false);
        beamBasic(ModBlocks.coal_beam, "coal", "coal_block", false, false);
        beamBasic(ModBlocks.redstone_beam, "redstone", "redstone_block", false, false);
        beamMissing(ModBlocks.missingno_beam, "missingno");
        beamBasic(ModBlocks.clay_beam, "clay", "clay", false, false);
        beamBasic(ModBlocks.dirt_beam, "dirt", "dirt", false, false);
        beamColumn(ModBlocks.hay_beam, "hay", "hay_block", false, false);
        beamBasic(ModBlocks.brick_beam, "brick", "bricks", true, true);
        beamColumn(ModBlocks.quartz_beam, "quartz", "quartz_block", true, true);
        beamColumn(ModBlocks.bone_beam, "bone", "bone_block", false, false);
        beamBasic(ModBlocks.nether_brick_beam, "nether_brick", "nether_bricks", true, true);
        beamBasic(ModBlocks.red_nether_brick_beam, "red_nether_brick", "red_nether_bricks", true, true);
        beamBasic(ModBlocks.snow_beam, "snow", "snow", "snow_block", false, false);
        beamBasic(ModBlocks.packed_ice_beam, "packed_ice", "packed_ice", false, false);
        beamBasic(ModBlocks.blue_ice_beam, "blue_ice", "blue_ice", false, false);
        beamBasic(ModBlocks.netherite_beam, "netherite", "netherite_block", false, false);
    }
}
