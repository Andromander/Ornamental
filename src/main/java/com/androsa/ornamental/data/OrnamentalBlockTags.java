package com.androsa.ornamental.data;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.data.provider.OrnamentalBlockTagProvider;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class OrnamentalBlockTags extends OrnamentalBlockTagProvider {

    private ImmutableSet<Supplier<? extends Block>> beacon_bases = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.gold_stairs, ModBlocks.diamond_stairs, ModBlocks.emerald_stairs, ModBlocks.netherite_stairs);
    private ImmutableSet<Supplier<? extends DoorBlock>> doors = ImmutableSet.of(
            ModBlocks.gold_door, ModBlocks.diamond_door, ModBlocks.emerald_door, ModBlocks.lapis_door, ModBlocks.obsidian_door, ModBlocks.coal_door, ModBlocks.redstone_door,
            ModBlocks.missingno_door, ModBlocks.clay_door, ModBlocks.dirt_door, ModBlocks.grass_door, ModBlocks.hay_door, ModBlocks.path_door, ModBlocks.brick_door, ModBlocks.quartz_door, ModBlocks.bone_door,
            ModBlocks.nether_brick_door, ModBlocks.red_nether_brick_door, ModBlocks.snow_door, ModBlocks.ice_door, ModBlocks.packed_ice_door, ModBlocks.blue_ice_door, ModBlocks.netherite_door);
    private ImmutableSet<Supplier<? extends Block>> dragon_immune = ImmutableSet.of(
            ModBlocks.obsidian_stairs, ModBlocks.obsidian_slab, ModBlocks.obsidian_fence, ModBlocks.obsidian_trapdoor, ModBlocks.obsidian_fence_gate, ModBlocks.obsidian_door, ModBlocks.obsidian_pole, ModBlocks.obsidian_beam, ModBlocks.obsidian_wall);
    private ImmutableSet<Supplier<? extends FenceBlock>> fences = ImmutableSet.of(
            ModBlocks.iron_fence, ModBlocks.gold_fence, ModBlocks.diamond_fence, ModBlocks.emerald_fence, ModBlocks.lapis_fence, ModBlocks.obsidian_fence, ModBlocks.coal_fence, ModBlocks.redstone_fence,
            ModBlocks.missingno_fence, ModBlocks.clay_fence, ModBlocks.dirt_fence, ModBlocks.grass_fence, ModBlocks.hay_fence, ModBlocks.path_fence, ModBlocks.brick_fence, ModBlocks.quartz_fence,
            ModBlocks.bone_fence, ModBlocks.red_nether_brick_fence, ModBlocks.snow_fence, ModBlocks.ice_fence, ModBlocks.packed_ice_fence, ModBlocks.blue_ice_fence, ModBlocks.netherite_fence);
    private ImmutableSet<Supplier<? extends FenceGateBlock>> fence_gates = ImmutableSet.of(
            ModBlocks.iron_fence_gate, ModBlocks.gold_fence_gate, ModBlocks.diamond_fence_gate, ModBlocks.emerald_fence_gate, ModBlocks.lapis_fence_gate, ModBlocks.obsidian_fence_gate, ModBlocks.coal_fence_gate,
            ModBlocks.redstone_fence_gate, ModBlocks.missingno_fence_gate, ModBlocks.clay_fence_gate, ModBlocks.dirt_fence_gate, ModBlocks.grass_fence_gate, ModBlocks.hay_fence_gate, ModBlocks.path_fence_gate,
            ModBlocks.brick_fence_gate, ModBlocks.quartz_fence_gate, ModBlocks.bone_fence_gate, ModBlocks.nether_brick_fence_gate, ModBlocks.red_nether_brick_fence_gate, ModBlocks.snow_fence_gate, ModBlocks.ice_fence_gate,
            ModBlocks.packed_ice_fence_gate, ModBlocks.blue_ice_fence_gate, ModBlocks.netherite_fence_gate);
    private ImmutableSet<Supplier<? extends Block>> piglin_guarded = ImmutableSet.of(
            ModBlocks.gold_stairs, ModBlocks.gold_slab, ModBlocks.gold_fence, ModBlocks.gold_trapdoor, ModBlocks.gold_fence_gate, ModBlocks.gold_door, ModBlocks.gold_pole, ModBlocks.gold_beam, ModBlocks.gold_wall);
    private ImmutableSet<Supplier<? extends SlabBlock>> slabs = ImmutableSet.of(
            ModBlocks.iron_slab, ModBlocks.gold_slab, ModBlocks.diamond_slab, ModBlocks.emerald_slab, ModBlocks.lapis_slab, ModBlocks.obsidian_slab, ModBlocks.coal_slab, ModBlocks.redstone_slab,
            ModBlocks.missingno_slab, ModBlocks.clay_slab, ModBlocks.dirt_slab, ModBlocks.grass_slab, ModBlocks.hay_slab, ModBlocks.path_slab, ModBlocks.bone_slab, ModBlocks.snow_slab, ModBlocks.ice_slab,
            ModBlocks.packed_ice_slab, ModBlocks.blue_ice_slab, ModBlocks.netherite_slab);
    private ImmutableSet<Supplier<? extends StairsBlock>> stairs = ImmutableSet.of(
            ModBlocks.iron_stairs, ModBlocks.gold_stairs, ModBlocks.diamond_stairs, ModBlocks.emerald_stairs, ModBlocks.lapis_stairs, ModBlocks.obsidian_stairs, ModBlocks.coal_stairs, ModBlocks.redstone_stairs,
            ModBlocks.missingno_stairs, ModBlocks.clay_stairs, ModBlocks.dirt_stairs, ModBlocks.grass_stairs, ModBlocks.hay_stairs, ModBlocks.path_stairs, ModBlocks.bone_stairs, ModBlocks.snow_stairs,
            ModBlocks.ice_stairs, ModBlocks.packed_ice_stairs, ModBlocks.blue_ice_stairs, ModBlocks.netherite_stairs);
    private ImmutableSet<Supplier<? extends TrapDoorBlock>> trapdoors = ImmutableSet.of(
            ModBlocks.gold_trapdoor, ModBlocks.diamond_trapdoor, ModBlocks.emerald_trapdoor, ModBlocks.lapis_trapdoor, ModBlocks.obsidian_trapdoor, ModBlocks.coal_trapdoor, ModBlocks.redstone_trapdoor, ModBlocks.missingno_trapdoor,
            ModBlocks.clay_trapdoor, ModBlocks.dirt_trapdoor, ModBlocks.grass_trapdoor, ModBlocks.hay_trapdoor, ModBlocks.path_trapdoor, ModBlocks.brick_trapdoor, ModBlocks.quartz_trapdoor, ModBlocks.bone_trapdoor,
            ModBlocks.nether_brick_trapdoor, ModBlocks.red_nether_brick_trapdoor, ModBlocks.snow_trapdoor, ModBlocks.ice_trapdoor, ModBlocks.packed_ice_trapdoor, ModBlocks.blue_ice_trapdoor, ModBlocks.netherite_trapdoor);
    private ImmutableSet<Supplier<? extends WallBlock>> walls = ImmutableSet.of(
			ModBlocks.iron_wall, ModBlocks.gold_wall, ModBlocks.diamond_wall, ModBlocks.emerald_wall, ModBlocks.lapis_wall, ModBlocks.obsidian_wall, ModBlocks.coal_wall, ModBlocks.redstone_wall, ModBlocks.missingno_wall,
			ModBlocks.clay_wall, ModBlocks.dirt_wall, ModBlocks.grass_wall, ModBlocks.hay_wall, ModBlocks.path_wall, ModBlocks.quartz_wall, ModBlocks.bone_wall, ModBlocks.snow_wall, ModBlocks.ice_wall, ModBlocks.packed_ice_wall,
			ModBlocks.blue_ice_wall, ModBlocks.netherite_wall);

    public OrnamentalBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper);
    }

    @Override
    protected void addTags() {
        beaconBaseTag(beacon_bases);
        doorTag(doors);
        dragonImmuneTag(dragon_immune);
        fenceTag(fences);
        fenceGateTag(fence_gates);
        piglinGuardedTag(piglin_guarded);
        slabTag(slabs);
        stairsTag(stairs);
        trapdoorTag(trapdoors);
        wallTag(walls);

        tag(Tags.Blocks.FENCES_NETHER_BRICK).add(ModBlocks.red_nether_brick_fence.get());
    }
}
