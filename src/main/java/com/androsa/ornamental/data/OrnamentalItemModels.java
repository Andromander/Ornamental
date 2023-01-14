package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.data.provider.OrnamentalItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Optional;

public class OrnamentalItemModels extends OrnamentalItemModelProvider {

    public OrnamentalItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, OrnamentalMod.MODID, helper);
    }

    @Override
    public String getName() {
        return "Ornamental Item Models";
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.iron_stairs);
        blockItem(ModBlocks.gold_stairs);
        blockItem(ModBlocks.diamond_stairs);
        blockItem(ModBlocks.emerald_stairs);
        blockItem(ModBlocks.lapis_stairs);
        blockItem(ModBlocks.obsidian_stairs);
        blockItem(ModBlocks.coal_stairs);
        blockItem(ModBlocks.redstone_stairs);
        blockItem(ModBlocks.missingno_stairs);
        blockItem(ModBlocks.clay_stairs);
        blockItem(ModBlocks.dirt_stairs);
        existingParent(ModBlocks.grass_stairs, modLoc("block/grass/grass_stairs_bottom_straight"));
        blockItem(ModBlocks.hay_stairs);
        existingParent(ModBlocks.path_stairs, modLoc("block/path/dirt_path_stairs_bottom_straight"));
        blockItem(ModBlocks.bone_stairs);
        blockItem(ModBlocks.snow_stairs);
        existingParent(ModBlocks.ice_stairs, modLoc("block/ice/ice_stairs"));
        blockItem(ModBlocks.packed_ice_stairs);
        blockItem(ModBlocks.blue_ice_stairs);
        blockItem(ModBlocks.netherite_stairs);
        blockItem(ModBlocks.amethyst_stairs);
        blockItem(ModBlocks.copper_stairs);
        blockItem(ModBlocks.exposed_copper_stairs);
        blockItem(ModBlocks.weathered_copper_stairs);
        blockItem(ModBlocks.oxidized_copper_stairs);

        blockItem(ModBlocks.iron_slab);
        blockItem(ModBlocks.gold_slab);
        blockItem(ModBlocks.diamond_slab);
        blockItem(ModBlocks.emerald_slab);
        blockItem(ModBlocks.lapis_slab);
        blockItem(ModBlocks.obsidian_slab);
        blockItem(ModBlocks.coal_slab);
        blockItem(ModBlocks.redstone_slab);
        blockItem(ModBlocks.missingno_slab);
        blockItem(ModBlocks.clay_slab);
        blockItem(ModBlocks.dirt_slab);
        existingParent(ModBlocks.grass_slab, modLoc("block/grass/grass_slab"));
        blockItem(ModBlocks.hay_slab);
        existingParent(ModBlocks.path_slab, modLoc("block/path/dirt_path_slab"));
        blockItem(ModBlocks.bone_slab);
        blockItem(ModBlocks.snow_slab);
        blockItem(ModBlocks.ice_slab);
        blockItem(ModBlocks.packed_ice_slab);
        blockItem(ModBlocks.blue_ice_slab);
        blockItem(ModBlocks.netherite_slab);
        blockItem(ModBlocks.amethyst_slab);
        blockItem(ModBlocks.copper_slab);
        blockItem(ModBlocks.exposed_copper_slab);
        blockItem(ModBlocks.weathered_copper_slab);
        blockItem(ModBlocks.oxidized_copper_slab);

        blockItemFence(ModBlocks.iron_fence, "iron_block");
        blockItemFence(ModBlocks.gold_fence, "gold_block");
        blockItemFence(ModBlocks.diamond_fence, "diamond_block");
        blockItemFence(ModBlocks.emerald_fence, "emerald_block");
        blockItemFence(ModBlocks.lapis_fence, "lapis_block");
        blockItemFence(ModBlocks.obsidian_fence, "obsidian");
        blockItemFence(ModBlocks.coal_fence, "coal_block");
        blockItemFence(ModBlocks.redstone_fence, "redstone_block");
        blockItemFenceMissing(ModBlocks.missingno_fence);
        blockItemFence(ModBlocks.clay_fence, "clay");
        blockItemFence(ModBlocks.dirt_fence, "dirt");
        existingParent(ModBlocks.grass_fence, modLoc("block/grass/grass_fence_inventory"));
        blockItemFence(ModBlocks.hay_fence, "hay_block_side", "hay_block_top");
        existingParent(ModBlocks.path_fence, modLoc("block/path/dirt_path_fence_inventory"));
        blockItemFence(ModBlocks.brick_fence, "bricks");
        blockItemFence(ModBlocks.quartz_fence, "quartz_block_side", "quartz_block_top");
        blockItemFence(ModBlocks.bone_fence, "bone_block_side", "bone_block_top");
        blockItemFence(ModBlocks.red_nether_brick_fence, "red_nether_bricks");
        blockItemFence(ModBlocks.snow_fence, "snow");
        blockItemFence(ModBlocks.ice_fence, "ice");
        blockItemFence(ModBlocks.packed_ice_fence, "packed_ice");
        blockItemFence(ModBlocks.blue_ice_fence, "blue_ice");
        blockItemFence(ModBlocks.netherite_fence, "netherite_block");
        blockItemFence(ModBlocks.amethyst_fence, "amethyst_block");
        blockItemFence(ModBlocks.copper_fence, "copper_block");
        blockItemFence(ModBlocks.exposed_copper_fence, "exposed_copper");
        blockItemFence(ModBlocks.weathered_copper_fence, "weathered_copper");
        blockItemFence(ModBlocks.oxidized_copper_fence, "oxidized_copper");

        blockItemTrapdoor(ModBlocks.gold_trapdoor);
        blockItemTrapdoor(ModBlocks.diamond_trapdoor);
        blockItemTrapdoor(ModBlocks.emerald_trapdoor);
        blockItemTrapdoor(ModBlocks.lapis_trapdoor);
        blockItemTrapdoor(ModBlocks.obsidian_trapdoor);
        blockItemTrapdoor(ModBlocks.coal_trapdoor);
        blockItemTrapdoor(ModBlocks.redstone_trapdoor);
        blockItemTrapdoor(ModBlocks.missingno_trapdoor);
        blockItemTrapdoor(ModBlocks.clay_trapdoor);
        blockItemTrapdoor(ModBlocks.dirt_trapdoor);
        existingParent(ModBlocks.grass_trapdoor, modLoc("block/grass/grass_trapdoor_bottom"));
        blockItemTrapdoor(ModBlocks.hay_trapdoor);
        existingParent(ModBlocks.path_trapdoor, modLoc("block/path/dirt_path_trapdoor_bottom"));
        blockItemTrapdoor(ModBlocks.brick_trapdoor);
        blockItemTrapdoor(ModBlocks.quartz_trapdoor);
        blockItemTrapdoor(ModBlocks.bone_trapdoor);
        blockItemTrapdoor(ModBlocks.nether_brick_trapdoor);
        blockItemTrapdoor(ModBlocks.red_nether_brick_trapdoor);
        blockItemTrapdoor(ModBlocks.snow_trapdoor);
        blockItemTrapdoor(ModBlocks.ice_trapdoor);
        blockItemTrapdoor(ModBlocks.packed_ice_trapdoor);
        blockItemTrapdoor(ModBlocks.blue_ice_trapdoor);
        blockItemTrapdoor(ModBlocks.netherite_trapdoor);
        blockItemTrapdoor(ModBlocks.amethyst_trapdoor);
        blockItemTrapdoor(ModBlocks.copper_trapdoor);
        blockItemTrapdoor(ModBlocks.exposed_copper_trapdoor);
        blockItemTrapdoor(ModBlocks.weathered_copper_trapdoor);
        blockItemTrapdoor(ModBlocks.oxidized_copper_trapdoor);

        blockItem(ModBlocks.iron_fence_gate);
        blockItem(ModBlocks.gold_fence_gate);
        blockItem(ModBlocks.diamond_fence_gate);
        blockItem(ModBlocks.emerald_fence_gate);
        blockItem(ModBlocks.lapis_fence_gate);
        blockItem(ModBlocks.obsidian_fence_gate);
        blockItem(ModBlocks.coal_fence_gate);
        blockItem(ModBlocks.redstone_fence_gate);
        blockItem(ModBlocks.missingno_fence_gate);
        blockItem(ModBlocks.clay_fence_gate);
        blockItem(ModBlocks.dirt_fence_gate);
        existingParent(ModBlocks.grass_fence_gate, modLoc("block/grass/grass_fence_gate"));
        blockItem(ModBlocks.hay_fence_gate);
        existingParent(ModBlocks.path_fence_gate, modLoc("block/path/dirt_path_fence_gate"));
        blockItem(ModBlocks.brick_fence_gate);
        blockItem(ModBlocks.quartz_fence_gate);
        blockItem(ModBlocks.bone_fence_gate);
        blockItem(ModBlocks.nether_brick_fence_gate);
        blockItem(ModBlocks.red_nether_brick_fence_gate);
        blockItem(ModBlocks.snow_fence_gate);
        blockItem(ModBlocks.ice_fence_gate);
        blockItem(ModBlocks.packed_ice_fence_gate);
        blockItem(ModBlocks.blue_ice_fence_gate);
        blockItem(ModBlocks.netherite_fence_gate);
        blockItem(ModBlocks.amethyst_fence_gate);
        blockItem(ModBlocks.copper_fence_gate);
        blockItem(ModBlocks.exposed_copper_fence_gate);
        blockItem(ModBlocks.weathered_copper_fence_gate);
        blockItem(ModBlocks.oxidized_copper_fence_gate);

        blockItemModel(ModBlocks.gold_door);
        blockItemModel(ModBlocks.diamond_door);
        blockItemModel(ModBlocks.emerald_door);
        blockItemModel(ModBlocks.lapis_door);
        blockItemModel(ModBlocks.obsidian_door);
        blockItemModel(ModBlocks.coal_door);
        blockItemModel(ModBlocks.redstone_door);
        blockItemModel(ModBlocks.missingno_door);
        blockItemModel(ModBlocks.clay_door);
        blockItemModel(ModBlocks.dirt_door);
        blockItemModel(ModBlocks.grass_door);
        blockItemModel(ModBlocks.hay_door);
        blockItemModel(ModBlocks.path_door, "path_door");
        blockItemModel(ModBlocks.brick_door);
        blockItemModel(ModBlocks.quartz_door);
        blockItemModel(ModBlocks.bone_door);
        blockItemModel(ModBlocks.nether_brick_door);
        blockItemModel(ModBlocks.red_nether_brick_door);
        blockItemModel(ModBlocks.snow_door);
        blockItemModel(ModBlocks.ice_door);
        blockItemModel(ModBlocks.packed_ice_door);
        blockItemModel(ModBlocks.blue_ice_door);
        blockItemModel(ModBlocks.netherite_door);
        blockItemModel(ModBlocks.amethyst_door);
        blockItemModel(ModBlocks.copper_door);
        blockItemModel(ModBlocks.exposed_copper_door);
        blockItemModel(ModBlocks.weathered_copper_door);
        blockItemModel(ModBlocks.oxidized_copper_door);

        blockItemPole(ModBlocks.iron_pole, "iron_block");
        blockItemPole(ModBlocks.gold_pole, "gold_block");
        blockItemPole(ModBlocks.diamond_pole, "diamond_block");
        blockItemPole(ModBlocks.emerald_pole, "emerald_block");
        blockItemPole(ModBlocks.lapis_pole, "lapis_block");
        blockItemPole(ModBlocks.obsidian_pole, "obsidian");
        blockItemPole(ModBlocks.coal_pole, "coal_block");
        blockItemPole(ModBlocks.redstone_pole, "redstone_block");
        blockItemPoleMissing(ModBlocks.missingno_pole);
        blockItemPole(ModBlocks.clay_pole, "clay");
        blockItemPole(ModBlocks.dirt_pole, "dirt");
        existingParent(ModBlocks.grass_pole, modLoc("block/grass/grass_pole_inventory"));
        blockItemPole(ModBlocks.hay_pole, "hay_block_top", "hay_block_side");
        existingParent(ModBlocks.path_pole, modLoc("block/path/dirt_path_pole_inventory"));
        blockItemPole(ModBlocks.brick_pole, "bricks");
        blockItemPole(ModBlocks.quartz_pole, "quartz_block_top", "quartz_block_side");
        blockItemPole(ModBlocks.bone_pole, "bone_block_top", "bone_block_side");
        blockItemPole(ModBlocks.nether_brick_pole, "nether_bricks");
        blockItemPole(ModBlocks.red_nether_brick_pole, "red_nether_bricks");
        blockItemPole(ModBlocks.snow_pole, "snow");
        blockItemPole(ModBlocks.ice_pole, "ice", Optional.of(TRANSLUCENT));
        blockItemPole(ModBlocks.packed_ice_pole, "packed_ice");
        blockItemPole(ModBlocks.blue_ice_pole, "blue_ice");
        blockItemPole(ModBlocks.netherite_pole, "netherite_block");
        blockItemPole(ModBlocks.amethyst_pole, "amethyst_block");
        blockItemPole(ModBlocks.copper_pole, "copper_block");
        blockItemPole(ModBlocks.exposed_copper_pole, "exposed_copper");
        blockItemPole(ModBlocks.weathered_copper_pole, "weathered_copper");
        blockItemPole(ModBlocks.oxidized_copper_pole, "oxidized_copper");

        blockItemBeam(ModBlocks.iron_beam, "iron_block");
        blockItemBeam(ModBlocks.gold_beam, "gold_block");
        blockItemBeam(ModBlocks.diamond_beam, "diamond_block");
        blockItemBeam(ModBlocks.emerald_beam, "emerald_block");
        blockItemBeam(ModBlocks.lapis_beam, "lapis_block");
        blockItemBeam(ModBlocks.obsidian_beam, "obsidian");
        blockItemBeam(ModBlocks.coal_beam, "coal_block");
        blockItemBeam(ModBlocks.redstone_beam, "redstone_block");
        blockItemBeamMissing(ModBlocks.missingno_beam);
        blockItemBeam(ModBlocks.clay_beam, "clay");
        blockItemBeam(ModBlocks.dirt_beam, "dirt");
        existingParent(ModBlocks.grass_beam, modLoc("block/grass/grass_beam_inventory"));
        blockItemBeam(ModBlocks.hay_beam, "hay_block_top", "hay_block_side");
        existingParent(ModBlocks.path_beam, modLoc("block/path/dirt_path_beam_inventory"));
        blockItemBeam(ModBlocks.brick_beam, "bricks");
        blockItemBeam(ModBlocks.quartz_beam, "quartz_block_top", "quartz_block_side");
        blockItemBeam(ModBlocks.bone_beam, "bone_block_top", "bone_block_side");
        blockItemBeam(ModBlocks.nether_brick_beam, "nether_bricks");
        blockItemBeam(ModBlocks.red_nether_brick_beam, "red_nether_bricks");
        blockItemBeam(ModBlocks.snow_beam, "snow");
        blockItemBeam(ModBlocks.ice_beam, "ice");
        blockItemBeam(ModBlocks.packed_ice_beam, "packed_ice");
        blockItemBeam(ModBlocks.blue_ice_beam, "blue_ice");
        blockItemBeam(ModBlocks.netherite_beam, "netherite_block");
        blockItemBeam(ModBlocks.amethyst_beam, "amethyst_block");
        blockItemBeam(ModBlocks.copper_beam, "copper_block");
        blockItemBeam(ModBlocks.exposed_copper_beam, "exposed_copper");
        blockItemBeam(ModBlocks.weathered_copper_beam, "weathered_copper");
        blockItemBeam(ModBlocks.oxidized_copper_beam, "oxidized_copper");

        blockItemWall(ModBlocks.iron_wall, "iron_block");
        blockItemWall(ModBlocks.gold_wall, "gold_block");
        blockItemWall(ModBlocks.diamond_wall, "diamond_block");
        blockItemWall(ModBlocks.emerald_wall, "emerald_block");
        blockItemWall(ModBlocks.lapis_wall, "lapis_block");
        blockItemWall(ModBlocks.obsidian_wall, "obsidian");
        blockItemWall(ModBlocks.coal_wall, "coal_block");
        blockItemWall(ModBlocks.redstone_wall, "redstone_block");
        blockItemWallMissing(ModBlocks.missingno_wall);
        blockItemWall(ModBlocks.clay_wall, "clay");
        blockItemWall(ModBlocks.dirt_wall, "dirt");
        existingParent(ModBlocks.grass_wall, modLoc("block/grass/grass_wall_inventory"));
        blockItemWall(ModBlocks.hay_wall, "hay_block_side", "hay_block_top");
        existingParent(ModBlocks.path_wall, modLoc("block/path/dirt_path_wall_inventory"));
        blockItemWall(ModBlocks.quartz_wall, "quartz_block_side", "quartz_block_top");
        blockItemWall(ModBlocks.bone_wall, "bone_block_side", "bone_block_top");
        blockItemWall(ModBlocks.snow_wall, "snow");
        blockItemWall(ModBlocks.ice_wall, "ice");
        blockItemWall(ModBlocks.packed_ice_wall, "packed_ice");
        blockItemWall(ModBlocks.blue_ice_wall, "blue_ice");
        blockItemWall(ModBlocks.netherite_wall, "netherite_block");
        blockItemWall(ModBlocks.amethyst_wall, "amethyst_block");
        blockItemWall(ModBlocks.copper_wall, "copper_block");
        blockItemWall(ModBlocks.exposed_copper_wall, "exposed_copper");
        blockItemWall(ModBlocks.weathered_copper_wall, "weathered_copper");
        blockItemWall(ModBlocks.oxidized_copper_wall, "oxidized_copper");

        blockItemSaddleDoorV(ModBlocks.iron_saddle_door, "iron_trapdoor");
        blockItemSaddleDoor(ModBlocks.gold_saddle_door, "gold");
        blockItemSaddleDoor(ModBlocks.diamond_saddle_door, "diamond");
        blockItemSaddleDoor(ModBlocks.emerald_saddle_door, "emerald");
        blockItemSaddleDoor(ModBlocks.lapis_saddle_door, "lapis");
        blockItemSaddleDoor(ModBlocks.obsidian_saddle_door, "obsidian");
        blockItemSaddleDoor(ModBlocks.coal_saddle_door, "coal");
        blockItemSaddleDoor(ModBlocks.redstone_saddle_door, "redstone");
        blockItemSaddleDoorM(ModBlocks.missingno_saddle_door);
        blockItemSaddleDoor(ModBlocks.clay_saddle_door, "clay");
        blockItemSaddleDoorV(ModBlocks.dirt_saddle_door, "dirt");
        existingParent(ModBlocks.grass_saddle_door, modLoc("block/grass/grass_saddle_door_inventory"));
        blockItemSaddleDoor(ModBlocks.hay_saddle_door, "hay");
        existingParent(ModBlocks.path_saddle_door, modLoc("block/path/dirt_path_saddle_door_inventory"));
        blockItemSaddleDoorV(ModBlocks.brick_saddle_door, "bricks");
        blockItemSaddleDoor(ModBlocks.quartz_saddle_door, "quartz");
        blockItemSaddleDoor(ModBlocks.bone_saddle_door, "bone");
        blockItemSaddleDoor(ModBlocks.nether_brick_saddle_door, "nether_brick");
        blockItemSaddleDoor(ModBlocks.red_nether_brick_saddle_door, "red_nether_brick");
        blockItemSaddleDoor(ModBlocks.snow_saddle_door, "snow");
        blockItemSaddleDoor(ModBlocks.ice_saddle_door, "ice");
        blockItemSaddleDoor(ModBlocks.packed_ice_saddle_door, "packed_ice");
        blockItemSaddleDoor(ModBlocks.blue_ice_saddle_door, "blue_ice");
        blockItemSaddleDoor(ModBlocks.netherite_saddle_door, "netherite");
        blockItemSaddleDoor(ModBlocks.amethyst_saddle_door, "amethyst");
        blockItemSaddleDoor(ModBlocks.copper_saddle_door, "copper");
        blockItemSaddleDoor(ModBlocks.exposed_copper_saddle_door, "exposed_copper");
        blockItemSaddleDoor(ModBlocks.weathered_copper_saddle_door, "weathered_copper");
        blockItemSaddleDoor(ModBlocks.oxidized_copper_saddle_door, "oxidized_copper");
    }
}
