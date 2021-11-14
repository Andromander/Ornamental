package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.data.provider.OrnamentalItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OrnamentalItemModels extends OrnamentalItemModelProvider {

    public OrnamentalItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper);
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
        blockItem(ModBlocks.hay_stairs);
        blockItem(ModBlocks.bone_stairs);
        blockItem(ModBlocks.snow_stairs);
        blockItem(ModBlocks.packed_ice_stairs);
        blockItem(ModBlocks.blue_ice_stairs);
        blockItem(ModBlocks.netherite_stairs);
        blockItem(ModBlocks.amethyst_stairs);

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
        blockItem(ModBlocks.hay_slab);
        blockItem(ModBlocks.bone_slab);
        blockItem(ModBlocks.snow_slab);
        blockItem(ModBlocks.ice_slab);
        blockItem(ModBlocks.packed_ice_slab);
        blockItem(ModBlocks.blue_ice_slab);
        blockItem(ModBlocks.netherite_slab);
        blockItem(ModBlocks.amethyst_slab);

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
        blockItemFenceColumn(ModBlocks.hay_fence, "hay_block_side", "hay_block_top");
        blockItemFence(ModBlocks.brick_fence, "bricks");
        blockItemFenceColumn(ModBlocks.quartz_fence, "quartz_block_side", "quartz_block_top");
        blockItemFenceColumn(ModBlocks.bone_fence, "bone_block_side", "bone_block_top");
        blockItemFence(ModBlocks.red_nether_brick_fence, "red_nether_bricks");
        blockItemFence(ModBlocks.snow_fence, "snow");
        blockItemFence(ModBlocks.ice_fence, "ice");
        blockItemFence(ModBlocks.packed_ice_fence, "packed_ice");
        blockItemFence(ModBlocks.blue_ice_fence, "blue_ice");
        blockItemFence(ModBlocks.netherite_fence, "netherite_block");
        blockItemFence(ModBlocks.amethyst_fence, "amethyst_block");

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
        blockItemTrapdoor(ModBlocks.hay_trapdoor);
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
        blockItem(ModBlocks.hay_fence_gate);
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
        blockItemPole(ModBlocks.hay_pole, "hay_block_top", "hay_block_side");
        blockItemPole(ModBlocks.brick_pole, "bricks");
        blockItemPole(ModBlocks.quartz_pole, "quartz_block_top", "quartz_block_side");
        blockItemPole(ModBlocks.bone_pole, "bone_block_top", "bone_block_side");
        blockItemPole(ModBlocks.nether_brick_pole, "nether_bricks");
        blockItemPole(ModBlocks.red_nether_brick_pole, "red_nether_bricks");
        blockItemPole(ModBlocks.snow_pole, "snow");
        blockItemPole(ModBlocks.ice_pole, "ice");
        blockItemPole(ModBlocks.packed_ice_pole, "packed_ice");
        blockItemPole(ModBlocks.blue_ice_pole, "blue_ice");
        blockItemPole(ModBlocks.netherite_pole, "netherite_block");
        blockItemPole(ModBlocks.amethyst_pole, "amethyst_block");

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
        blockItemBeam(ModBlocks.hay_beam, "hay_block_top", "hay_block_side");
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
        blockItemWallColumn(ModBlocks.hay_wall, "hay_block_side", "hay_block_top");
        blockItemWallColumn(ModBlocks.quartz_wall, "quartz_block_side", "quartz_block_top");
        blockItemWallColumn(ModBlocks.bone_wall, "bone_block_side", "bone_block_top");
        blockItemWall(ModBlocks.snow_wall, "snow");
        blockItemWall(ModBlocks.ice_wall, "ice");
        blockItemWall(ModBlocks.packed_ice_wall, "packed_ice");
        blockItemWall(ModBlocks.blue_ice_wall, "blue_ice");
        blockItemWall(ModBlocks.netherite_wall, "netherite_block");
        blockItemWall(ModBlocks.amethyst_wall, "amethyst_block");
    }
}
