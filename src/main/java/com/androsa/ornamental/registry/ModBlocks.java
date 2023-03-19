package com.androsa.ornamental.registry;

import com.androsa.ornamental.builder.OrnamentBuilders;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.data.OrnamentalBlockTags;
import com.androsa.ornamental.data.OrnamentalItemTags;
import com.androsa.ornamental.registry.helper.RegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.google.common.collect.Lists;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OrnamentalMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OrnamentalMod.MODID);
    private static final RegistryHelper HELPER = new RegistryHelper(BLOCKS, ITEMS);

    public static final RegistryObject<OrnamentStair> iron_stairs = HELPER.stairs(OrnamentBuilders.IRON, HELPER.array(OrnamentalBlockTags.BEACON_BASES), Lists.newArrayList());
    public static final RegistryObject<OrnamentStair> gold_stairs = HELPER.stairs(OrnamentBuilders.GOLD, HELPER.array(OrnamentalBlockTags.BEACON_BASES), Lists.newArrayList());
    public static final RegistryObject<OrnamentStair> diamond_stairs = HELPER.stairs(OrnamentBuilders.DIAMOND, HELPER.array(OrnamentalBlockTags.BEACON_BASES), Lists.newArrayList());
    public static final RegistryObject<OrnamentStair> emerald_stairs = HELPER.stairs(OrnamentBuilders.EMERALD, HELPER.array(OrnamentalBlockTags.BEACON_BASES), Lists.newArrayList());
    public static final RegistryObject<OrnamentStair> lapis_stairs = HELPER.stairs(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentStair> obsidian_stairs = HELPER.stairs(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentStair> coal_stairs = HELPER.stairs(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentStair> redstone_stairs = HELPER.stairs(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentStair> missingno_stairs = HELPER.stairs(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentStair> clay_stairs = HELPER.stairs(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentStair> dirt_stairs = HELPER.stairs(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentStair> grass_stairs = HELPER.stairs(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentStair> hay_stairs = HELPER.stairs(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentStair> path_stairs = HELPER.stairs(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentStair> bone_stairs = HELPER.stairs(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentStair> snow_stairs = HELPER.stairs(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentStair> ice_stairs = HELPER.stairs(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentStair> packed_ice_stairs = HELPER.stairs(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentStair> blue_ice_stairs = HELPER.stairs(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentStair> netherite_stairs = HELPER.stairs(OrnamentBuilders.NETHERITE, HELPER.array(OrnamentalBlockTags.BEACON_BASES), Lists.newArrayList());
    public static final RegistryObject<OrnamentStair> amethyst_stairs = HELPER.stairs(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentStair> copper_stairs = HELPER.stairs(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentStair> exposed_copper_stairs = HELPER.stairs(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentStair> weathered_copper_stairs = HELPER.stairs(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentStair> oxidized_copper_stairs = HELPER.stairs(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentSlab> iron_slab = HELPER.slab(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentSlab> gold_slab = HELPER.slab(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentSlab> diamond_slab = HELPER.slab(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentSlab> emerald_slab = HELPER.slab(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentSlab> lapis_slab = HELPER.slab(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentSlab> obsidian_slab = HELPER.slab(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentSlab> coal_slab = HELPER.slab(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentSlab> redstone_slab = HELPER.slab(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentSlab> missingno_slab = HELPER.slab(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentSlab> clay_slab = HELPER.slab(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentSlab> dirt_slab = HELPER.slab(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentSlab> grass_slab = HELPER.slab(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentSlab> hay_slab = HELPER.slab(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentSlab> path_slab = HELPER.slab(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentSlab> bone_slab = HELPER.slab(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentSlab> snow_slab = HELPER.slab(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentSlab> ice_slab = HELPER.slab(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentSlab> packed_ice_slab = HELPER.slab(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentSlab> blue_ice_slab = HELPER.slab(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentSlab> netherite_slab = HELPER.slab(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentSlab> amethyst_slab = HELPER.slab(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentSlab> copper_slab = HELPER.slab(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentSlab> exposed_copper_slab = HELPER.slab(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentSlab> weathered_copper_slab = HELPER.slab(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentSlab> oxidized_copper_slab = HELPER.slab(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentFence> iron_fence = HELPER.fence(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentFence> gold_fence = HELPER.fence(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentFence> diamond_fence = HELPER.fence(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentFence> emerald_fence = HELPER.fence(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentFence> lapis_fence = HELPER.fence(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentFence> obsidian_fence = HELPER.fence(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentFence> coal_fence = HELPER.fence(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentFence> redstone_fence = HELPER.fence(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentFence> missingno_fence = HELPER.fence(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentFence> clay_fence = HELPER.fence(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentFence> dirt_fence = HELPER.fence(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentFence> grass_fence = HELPER.fence(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentFence> hay_fence = HELPER.fence(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentFence> path_fence = HELPER.fence(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentFence> brick_fence = HELPER.fence(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentFence> quartz_fence = HELPER.fence(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentFence> bone_fence = HELPER.fence(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentFence> red_nether_brick_fence = HELPER.fence(OrnamentBuilders.RED_NETHER_BRICK, HELPER.array(OrnamentalBlockTags.NETHER_BRICK_FENCE), HELPER.array(OrnamentalItemTags.NETHER_BRICK_FENCES));
    public static final RegistryObject<OrnamentFence> snow_fence = HELPER.fence(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentFence> ice_fence = HELPER.fence(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentFence> packed_ice_fence = HELPER.fence(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentFence> blue_ice_fence = HELPER.fence(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentFence> netherite_fence = HELPER.fence(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentFence> amethyst_fence = HELPER.fence(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentFence> copper_fence = HELPER.fence(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentFence> exposed_copper_fence = HELPER.fence(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentFence> weathered_copper_fence = HELPER.fence(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentFence> oxidized_copper_fence = HELPER.fence(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentTrapDoor> gold_trapdoor = HELPER.trapdoor(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentTrapDoor> diamond_trapdoor = HELPER.trapdoor(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentTrapDoor> emerald_trapdoor = HELPER.trapdoor(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentTrapDoor> lapis_trapdoor = HELPER.trapdoor(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentTrapDoor> obsidian_trapdoor = HELPER.trapdoor(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentTrapDoor> coal_trapdoor = HELPER.trapdoor(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentTrapDoor> redstone_trapdoor = HELPER.trapdoor(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentTrapDoor> missingno_trapdoor = HELPER.trapdoor(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentTrapDoor> clay_trapdoor = HELPER.trapdoor(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentTrapDoor> dirt_trapdoor = HELPER.trapdoor(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentTrapDoor> grass_trapdoor = HELPER.trapdoor(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentTrapDoor> hay_trapdoor = HELPER.trapdoor(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentTrapDoor> path_trapdoor = HELPER.trapdoor(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentTrapDoor> brick_trapdoor = HELPER.trapdoor(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentTrapDoor> quartz_trapdoor = HELPER.trapdoor(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentTrapDoor> bone_trapdoor = HELPER.trapdoor(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentTrapDoor> nether_brick_trapdoor = HELPER.trapdoor(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentTrapDoor> red_nether_brick_trapdoor = HELPER.trapdoor(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentTrapDoor> snow_trapdoor = HELPER.trapdoor(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentTrapDoor> ice_trapdoor = HELPER.trapdoor(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentTrapDoor> packed_ice_trapdoor = HELPER.trapdoor(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentTrapDoor> blue_ice_trapdoor = HELPER.trapdoor(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentTrapDoor> netherite_trapdoor = HELPER.trapdoor(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentTrapDoor> amethyst_trapdoor = HELPER.trapdoor(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentTrapDoor> copper_trapdoor = HELPER.trapdoor(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentTrapDoor> exposed_copper_trapdoor = HELPER.trapdoor(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentTrapDoor> weathered_copper_trapdoor = HELPER.trapdoor(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentTrapDoor> oxidized_copper_trapdoor = HELPER.trapdoor(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentFenceGate> iron_fence_gate = HELPER.fencegate(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentFenceGate> gold_fence_gate = HELPER.fencegate(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentFenceGate> diamond_fence_gate = HELPER.fencegate(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentFenceGate> emerald_fence_gate = HELPER.fencegate(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentFenceGate> lapis_fence_gate = HELPER.fencegate(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentFenceGate> obsidian_fence_gate = HELPER.fencegate(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentFenceGate> coal_fence_gate = HELPER.fencegate(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentFenceGate> redstone_fence_gate = HELPER.fencegate(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentFenceGate> missingno_fence_gate = HELPER.fencegate(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentFenceGate> clay_fence_gate = HELPER.fencegate(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentFenceGate> dirt_fence_gate = HELPER.fencegate(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentFenceGate> grass_fence_gate = HELPER.fencegate(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentFenceGate> hay_fence_gate = HELPER.fencegate(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentFenceGate> path_fence_gate = HELPER.fencegate(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentFenceGate> brick_fence_gate = HELPER.fencegate(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentFenceGate> quartz_fence_gate = HELPER.fencegate(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentFenceGate> bone_fence_gate = HELPER.fencegate(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentFenceGate> nether_brick_fence_gate = HELPER.fencegate(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentFenceGate> red_nether_brick_fence_gate = HELPER.fencegate(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentFenceGate> snow_fence_gate = HELPER.fencegate(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentFenceGate> ice_fence_gate = HELPER.fencegate(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentFenceGate> packed_ice_fence_gate = HELPER.fencegate(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentFenceGate> blue_ice_fence_gate = HELPER.fencegate(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentFenceGate> netherite_fence_gate = HELPER.fencegate(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentFenceGate> amethyst_fence_gate = HELPER.fencegate(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentFenceGate> copper_fence_gate = HELPER.fencegate(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentFenceGate> exposed_copper_fence_gate = HELPER.fencegate(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentFenceGate> weathered_copper_fence_gate = HELPER.fencegate(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentFenceGate> oxidized_copper_fence_gate = HELPER.fencegate(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentDoor> gold_door = HELPER.door(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentDoor> diamond_door = HELPER.door(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentDoor> emerald_door = HELPER.door(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentDoor> lapis_door = HELPER.door(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentDoor> obsidian_door = HELPER.door(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentDoor> coal_door = HELPER.door(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentDoor> redstone_door = HELPER.door(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentDoor> missingno_door = HELPER.door(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentDoor> clay_door = HELPER.door(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentDoor> dirt_door = HELPER.door(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentDoor> grass_door = HELPER.door(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentDoor> hay_door = HELPER.door(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentDoor> path_door = HELPER.door(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentDoor> brick_door = HELPER.door(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentDoor> quartz_door = HELPER.door(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentDoor> bone_door = HELPER.door(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentDoor> nether_brick_door = HELPER.door(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentDoor> red_nether_brick_door = HELPER.door(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentDoor> snow_door = HELPER.door(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentDoor> ice_door = HELPER.door(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentDoor> packed_ice_door = HELPER.door(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentDoor> blue_ice_door = HELPER.door(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentDoor> netherite_door = HELPER.door(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentDoor> amethyst_door = HELPER.door(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentDoor> copper_door = HELPER.door(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentDoor> exposed_copper_door = HELPER.door(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentDoor> weathered_copper_door = HELPER.door(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentDoor> oxidized_copper_door = HELPER.door(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentPole> iron_pole = HELPER.pole(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentPole> gold_pole = HELPER.pole(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentPole> diamond_pole = HELPER.pole(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentPole> emerald_pole = HELPER.pole(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentPole> lapis_pole = HELPER.pole(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentPole> obsidian_pole = HELPER.pole(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentPole> coal_pole = HELPER.pole(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentPole> redstone_pole = HELPER.pole(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentPole> missingno_pole = HELPER.pole(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentPole> clay_pole = HELPER.pole(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentPole> dirt_pole = HELPER.pole(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentPole> grass_pole = HELPER.pole(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentPole> hay_pole = HELPER.pole(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentPole> path_pole = HELPER.pole(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentPole> brick_pole = HELPER.pole(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentPole> quartz_pole = HELPER.pole(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentPole> bone_pole = HELPER.pole(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentPole> nether_brick_pole = HELPER.pole(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentPole> red_nether_brick_pole = HELPER.pole(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentPole> snow_pole = HELPER.pole(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentPole> ice_pole = HELPER.pole(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentPole> packed_ice_pole = HELPER.pole(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentPole> blue_ice_pole = HELPER.pole(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentPole> netherite_pole = HELPER.pole(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentPole> amethyst_pole = HELPER.pole(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentPole> copper_pole = HELPER.pole(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentPole> exposed_copper_pole = HELPER.pole(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentPole> weathered_copper_pole = HELPER.pole(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentPole> oxidized_copper_pole = HELPER.pole(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentBeam> iron_beam = HELPER.beam(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentBeam> gold_beam = HELPER.beam(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentBeam> diamond_beam = HELPER.beam(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentBeam> emerald_beam = HELPER.beam(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentBeam> lapis_beam = HELPER.beam(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentBeam> obsidian_beam = HELPER.beam(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentBeam> coal_beam = HELPER.beam(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentBeam> redstone_beam = HELPER.beam(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentBeam> missingno_beam = HELPER.beam(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentBeam> clay_beam = HELPER.beam(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentBeam> dirt_beam = HELPER.beam(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentBeam> grass_beam = HELPER.beam(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentBeam> hay_beam = HELPER.beam(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentBeam> path_beam = HELPER.beam(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentBeam> brick_beam = HELPER.beam(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentBeam> quartz_beam = HELPER.beam(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentBeam> bone_beam = HELPER.beam(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentBeam> nether_brick_beam = HELPER.beam(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentBeam> red_nether_brick_beam = HELPER.beam(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentBeam> snow_beam = HELPER.beam(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentBeam> ice_beam = HELPER.beam(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentBeam> packed_ice_beam = HELPER.beam(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentBeam> blue_ice_beam = HELPER.beam(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentBeam> netherite_beam = HELPER.beam(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentBeam> amethyst_beam = HELPER.beam(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentBeam> copper_beam = HELPER.beam(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentBeam> exposed_copper_beam = HELPER.beam(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentBeam> weathered_copper_beam = HELPER.beam(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentBeam> oxidized_copper_beam = HELPER.beam(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentWall> iron_wall = HELPER.wall(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentWall> gold_wall = HELPER.wall(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentWall> diamond_wall = HELPER.wall(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentWall> emerald_wall = HELPER.wall(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentWall> lapis_wall = HELPER.wall(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentWall> obsidian_wall = HELPER.wall(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentWall> coal_wall = HELPER.wall(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentWall> redstone_wall = HELPER.wall(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentWall> missingno_wall = HELPER.wall(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentWall> clay_wall = HELPER.wall(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentWall> dirt_wall = HELPER.wall(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentWall> grass_wall = HELPER.wall(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentWall> hay_wall = HELPER.wall(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentWall> path_wall = HELPER.wall(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentWall> quartz_wall = HELPER.wall(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentWall> bone_wall = HELPER.wall(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentWall> snow_wall = HELPER.wall(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentWall> ice_wall = HELPER.wall(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentWall> packed_ice_wall = HELPER.wall(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentWall> blue_ice_wall = HELPER.wall(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentWall> netherite_wall = HELPER.wall(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentWall> amethyst_wall = HELPER.wall(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentWall> copper_wall = HELPER.wall(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentWall> exposed_copper_wall = HELPER.wall(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentWall> weathered_copper_wall = HELPER.wall(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentWall> oxidized_copper_wall = HELPER.wall(OrnamentBuilders.OXIDIZED_COPPER);

    public static final RegistryObject<OrnamentSaddleDoor> iron_saddle_door = HELPER.saddledoor(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentSaddleDoor> gold_saddle_door = HELPER.saddledoor(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentSaddleDoor> diamond_saddle_door = HELPER.saddledoor(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentSaddleDoor> emerald_saddle_door = HELPER.saddledoor(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentSaddleDoor> lapis_saddle_door = HELPER.saddledoor(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentSaddleDoor> obsidian_saddle_door = HELPER.saddledoor(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentSaddleDoor> coal_saddle_door = HELPER.saddledoor(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentSaddleDoor> redstone_saddle_door = HELPER.saddledoor(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentSaddleDoor> missingno_saddle_door = HELPER.saddledoor(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentSaddleDoor> clay_saddle_door = HELPER.saddledoor(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentSaddleDoor> dirt_saddle_door = HELPER.saddledoor(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentSaddleDoor> grass_saddle_door = HELPER.saddledoor(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentSaddleDoor> hay_saddle_door = HELPER.saddledoor(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentSaddleDoor> path_saddle_door = HELPER.saddledoor(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentSaddleDoor> brick_saddle_door = HELPER.saddledoor(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentSaddleDoor> quartz_saddle_door = HELPER.saddledoor(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentSaddleDoor> bone_saddle_door = HELPER.saddledoor(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentSaddleDoor> nether_brick_saddle_door = HELPER.saddledoor(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentSaddleDoor> red_nether_brick_saddle_door = HELPER.saddledoor(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentSaddleDoor> snow_saddle_door = HELPER.saddledoor(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentSaddleDoor> ice_saddle_door = HELPER.saddledoor(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentSaddleDoor> packed_ice_saddle_door = HELPER.saddledoor(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentSaddleDoor> blue_ice_saddle_door = HELPER.saddledoor(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentSaddleDoor> netherite_saddle_door = HELPER.saddledoor(OrnamentBuilders.NETHERITE);
    public static final RegistryObject<OrnamentSaddleDoor> amethyst_saddle_door = HELPER.saddledoor(OrnamentBuilders.AMETHYST);
    public static final RegistryObject<OrnamentSaddleDoor> copper_saddle_door = HELPER.saddledoor(OrnamentBuilders.COPPER);
    public static final RegistryObject<OrnamentSaddleDoor> exposed_copper_saddle_door = HELPER.saddledoor(OrnamentBuilders.EXPOSED_COPPER);
    public static final RegistryObject<OrnamentSaddleDoor> weathered_copper_saddle_door = HELPER.saddledoor(OrnamentBuilders.WEATHERED_COPPER);
    public static final RegistryObject<OrnamentSaddleDoor> oxidized_copper_saddle_door = HELPER.saddledoor(OrnamentBuilders.OXIDIZED_COPPER);
}