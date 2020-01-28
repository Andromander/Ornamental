package com.androsa.nifty.data;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.data.provider.NiftyItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class NiftyItemModels extends NiftyItemModelProvider {

    public NiftyItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, helper);
    }

    @Override
    public String getName() {
        return "Nifty Item Models";
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
    }
}
