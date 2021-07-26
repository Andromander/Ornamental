package com.androsa.ornamental.registry;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.builder.OrnamentBuilders;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess"})
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OrnamentalMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OrnamentalMod.MODID);

    public static final RegistryObject<OrnamentStair> iron_stairs = registerStairs(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentStair> gold_stairs = registerStairs(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentStair> diamond_stairs = registerStairs(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentStair> emerald_stairs = registerStairs(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentStair> lapis_stairs = registerStairs(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentStair> obsidian_stairs = registerStairs(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentStair> coal_stairs = registerStairs(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentStair> redstone_stairs = registerStairs(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentStair> missingno_stairs = registerStairs(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentStair> clay_stairs = registerStairs(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentStair> dirt_stairs = registerStairs(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentStair> grass_stairs = registerStairs(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentStair> hay_stairs = registerStairs(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentStair> path_stairs = registerStairs(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentStair> bone_stairs = registerStairs(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentStair> snow_stairs = registerStairs(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentStair> ice_stairs = registerStairs(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentStair> packed_ice_stairs = registerStairs(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentStair> blue_ice_stairs = registerStairs(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentStair> netherite_stairs = registerStairs(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentSlab> iron_slab = registerSlab(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentSlab> gold_slab = registerSlab(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentSlab> diamond_slab = registerSlab(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentSlab> emerald_slab = registerSlab(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentSlab> lapis_slab = registerSlab(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentSlab> obsidian_slab = registerSlab(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentSlab> coal_slab = registerSlab(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentSlab> redstone_slab = registerSlab(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentSlab> missingno_slab = registerSlab(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentSlab> clay_slab = registerSlab(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentSlab> dirt_slab = registerSlab(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentSlab> grass_slab = registerSlab(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentSlab> hay_slab = registerSlab(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentSlab> path_slab = registerSlab(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentSlab> bone_slab = registerSlab(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentSlab> snow_slab = registerSlab(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentSlab> ice_slab = registerSlab(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentSlab> packed_ice_slab = registerSlab(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentSlab> blue_ice_slab = registerSlab(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentSlab> netherite_slab = registerSlab(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentFence> iron_fence = registerFence(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentFence> gold_fence = registerFence(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentFence> diamond_fence = registerFence(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentFence> emerald_fence = registerFence(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentFence> lapis_fence = registerFence(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentFence> obsidian_fence = registerFence(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentFence> coal_fence = registerFence(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentFence> redstone_fence = registerFence(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentFence> missingno_fence = registerFence(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentFence> clay_fence = registerFence(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentFence> dirt_fence = registerFence(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentFence> grass_fence = registerFence(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentFence> hay_fence = registerFence(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentFence> path_fence = registerFence(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentFence> brick_fence = registerFence(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentFence> quartz_fence = registerFence(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentFence> bone_fence = registerFence(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentFence> red_nether_brick_fence = registerFence(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentFence> snow_fence = registerFence(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentFence> ice_fence = registerFence(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentFence> packed_ice_fence = registerFence(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentFence> blue_ice_fence = registerFence(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentFence> netherite_fence = registerFence(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentTrapDoor> gold_trapdoor = registerTrapdoor(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentTrapDoor> diamond_trapdoor = registerTrapdoor(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentTrapDoor> emerald_trapdoor = registerTrapdoor(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentTrapDoor> lapis_trapdoor = registerTrapdoor(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentTrapDoor> obsidian_trapdoor = registerTrapdoor(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentTrapDoor> coal_trapdoor = registerTrapdoor(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentTrapDoor> redstone_trapdoor = registerTrapdoor(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentTrapDoor> missingno_trapdoor = registerTrapdoor(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentTrapDoor> clay_trapdoor = registerTrapdoor(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentTrapDoor> dirt_trapdoor = registerTrapdoor(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentTrapDoor> grass_trapdoor = registerTrapdoor(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentTrapDoor> hay_trapdoor = registerTrapdoor(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentTrapDoor> path_trapdoor = registerTrapdoor(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentTrapDoor> brick_trapdoor = registerTrapdoor(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentTrapDoor> quartz_trapdoor = registerTrapdoor(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentTrapDoor> bone_trapdoor = registerTrapdoor(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentTrapDoor> nether_brick_trapdoor = registerTrapdoor(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentTrapDoor> red_nether_brick_trapdoor = registerTrapdoor(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentTrapDoor> snow_trapdoor = registerTrapdoor(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentTrapDoor> ice_trapdoor = registerTrapdoor(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentTrapDoor> packed_ice_trapdoor = registerTrapdoor(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentTrapDoor> blue_ice_trapdoor = registerTrapdoor(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentTrapDoor> netherite_trapdoor = registerTrapdoor(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentFenceGate> iron_fence_gate = registerFenceGate(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentFenceGate> gold_fence_gate = registerFenceGate(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentFenceGate> diamond_fence_gate = registerFenceGate(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentFenceGate> emerald_fence_gate = registerFenceGate(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentFenceGate> lapis_fence_gate = registerFenceGate(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentFenceGate> obsidian_fence_gate = registerFenceGate(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentFenceGate> coal_fence_gate = registerFenceGate(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentFenceGate> redstone_fence_gate = registerFenceGate(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentFenceGate> missingno_fence_gate = registerFenceGate(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentFenceGate> clay_fence_gate = registerFenceGate(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentFenceGate> dirt_fence_gate = registerFenceGate(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentFenceGate> grass_fence_gate = registerFenceGate(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentFenceGate> hay_fence_gate = registerFenceGate(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentFenceGate> path_fence_gate = registerFenceGate(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentFenceGate> brick_fence_gate = registerFenceGate(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentFenceGate> quartz_fence_gate = registerFenceGate(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentFenceGate> bone_fence_gate = registerFenceGate(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentFenceGate> nether_brick_fence_gate = registerFenceGate(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentFenceGate> red_nether_brick_fence_gate = registerFenceGate(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentFenceGate> snow_fence_gate = registerFenceGate(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentFenceGate> ice_fence_gate = registerFenceGate(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentFenceGate> packed_ice_fence_gate = registerFenceGate(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentFenceGate> blue_ice_fence_gate = registerFenceGate(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentFenceGate> netherite_fence_gate = registerFenceGate(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentDoor> gold_door = registerDoor(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentDoor> diamond_door = registerDoor(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentDoor> emerald_door = registerDoor(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentDoor> lapis_door = registerDoor(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentDoor> obsidian_door = registerDoor(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentDoor> coal_door = registerDoor(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentDoor> redstone_door = registerDoor(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentDoor> missingno_door = registerDoor(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentDoor> clay_door = registerDoor(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentDoor> dirt_door = registerDoor(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentDoor> grass_door = registerDoor(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentDoor> hay_door = registerDoor(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentDoor> path_door = registerDoor(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentDoor> brick_door = registerDoor(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentDoor> quartz_door = registerDoor(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentDoor> bone_door = registerDoor(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentDoor> nether_brick_door = registerDoor(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentDoor> red_nether_brick_door = registerDoor(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentDoor> snow_door = registerDoor(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentDoor> ice_door = registerDoor(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentDoor> packed_ice_door = registerDoor(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentDoor> blue_ice_door = registerDoor(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentDoor> netherite_door = registerDoor(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentPole> iron_pole = registerPole(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentPole> gold_pole = registerPole(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentPole> diamond_pole = registerPole(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentPole> emerald_pole = registerPole(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentPole> lapis_pole = registerPole(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentPole> obsidian_pole = registerPole(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentPole> coal_pole = registerPole(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentPole> redstone_pole = registerPole(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentPole> missingno_pole = registerPole(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentPole> clay_pole = registerPole(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentPole> dirt_pole = registerPole(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentPole> grass_pole = registerPole(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentPole> hay_pole = registerPole(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentPole> path_pole = registerPole(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentPole> brick_pole = registerPole(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentPole> quartz_pole = registerPole(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentPole> bone_pole = registerPole(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentPole> nether_brick_pole = registerPole(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentPole> red_nether_brick_pole = registerPole(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentPole> snow_pole = registerPole(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentPole> ice_pole = registerPole(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentPole> packed_ice_pole = registerPole(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentPole> blue_ice_pole = registerPole(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentPole> netherite_pole = registerPole(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentBeam> iron_beam = registerBeam(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentBeam> gold_beam = registerBeam(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentBeam> diamond_beam = registerBeam(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentBeam> emerald_beam = registerBeam(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentBeam> lapis_beam = registerBeam(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentBeam> obsidian_beam = registerBeam(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentBeam> coal_beam = registerBeam(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentBeam> redstone_beam = registerBeam(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentBeam> missingno_beam = registerBeam(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentBeam> clay_beam = registerBeam(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentBeam> dirt_beam = registerBeam(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentBeam> grass_beam = registerBeam(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentBeam> hay_beam = registerBeam(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentBeam> path_beam = registerBeam(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentBeam> brick_beam = registerBeam(OrnamentBuilders.BRICK);
    public static final RegistryObject<OrnamentBeam> quartz_beam = registerBeam(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentBeam> bone_beam = registerBeam(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentBeam> nether_brick_beam = registerBeam(OrnamentBuilders.NETHER_BRICK);
    public static final RegistryObject<OrnamentBeam> red_nether_brick_beam = registerBeam(OrnamentBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<OrnamentBeam> snow_beam = registerBeam(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentBeam> ice_beam = registerBeam(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentBeam> packed_ice_beam = registerBeam(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentBeam> blue_ice_beam = registerBeam(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentBeam> netherite_beam = registerBeam(OrnamentBuilders.NETHERITE);

    public static final RegistryObject<OrnamentWall> iron_wall = registerWall(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentWall> gold_wall = registerWall(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentWall> diamond_wall = registerWall(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentWall> emerald_wall = registerWall(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentWall> lapis_wall = registerWall(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentWall> obsidian_wall = registerWall(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentWall> coal_wall = registerWall(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentWall> redstone_wall = registerWall(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentWall> missingno_wall = registerWall(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentWall> clay_wall = registerWall(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentWall> dirt_wall = registerWall(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentWall> grass_wall = registerWall(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentWall> hay_wall = registerWall(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentWall> path_wall = registerWall(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentWall> quartz_wall = registerWall(OrnamentBuilders.QUARTZ);
    public static final RegistryObject<OrnamentWall> bone_wall = registerWall(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentWall> snow_wall = registerWall(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentWall> ice_wall = registerWall(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentWall> packed_ice_wall = registerWall(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentWall> blue_ice_wall = registerWall(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentWall> netherite_wall = registerWall(OrnamentBuilders.NETHERITE);

    private static RegistryObject<OrnamentStair> registerStairs(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_stairs", () -> new OrnamentStair(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_BUILDING_BLOCKS, builder, 4));
    }

    private static RegistryObject<OrnamentSlab> registerSlab(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_slab", () -> new OrnamentSlab(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_BUILDING_BLOCKS, builder, 3));
    }

    private static RegistryObject<OrnamentFence> registerFence(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence", () -> new OrnamentFence(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_DECORATIONS, builder, 1));
    }

    private static RegistryObject<OrnamentTrapDoor> registerTrapdoor(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder)
                .noOcclusion()
                .isValidSpawn((state, reader, pos, type) -> false);

        return registerBlock(builder.name + "_trapdoor", () -> new OrnamentTrapDoor(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_REDSTONE, builder, 5));
    }

    private static RegistryObject<OrnamentFenceGate> registerFenceGate(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence_gate", () -> new OrnamentFenceGate(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_REDSTONE, builder, 2));
    }

    private static RegistryObject<OrnamentDoor> registerDoor(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder).noOcclusion();

        return registerBlock(builder.name + "_door", () -> new OrnamentDoor(props, builder), item ->
                registerBlockItemDoor(item, builder, 0));
    }

    private static RegistryObject<OrnamentPole> registerPole(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_pole", () -> new OrnamentPole(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_BUILDING_BLOCKS, builder, 6));
    }

    private static RegistryObject<OrnamentBeam> registerBeam(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_beam", () -> new OrnamentBeam(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_BUILDING_BLOCKS, builder, 7));
    }

    private static RegistryObject<OrnamentWall> registerWall(OrnamentBuilder builder) {
        BlockBehaviour.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_wall", () -> new OrnamentWall(props, builder), item ->
                registerBlockItem(item, CreativeModeTab.TAB_DECORATIONS, builder, 8));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, CreativeModeTab group, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentBlockItem(block.get(), PropertiesHelper.createProps(ornament, group), ornament, fuelindex);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentTallBlockItem(block.get(), PropertiesHelper.createProps(ornament, CreativeModeTab.TAB_REDSTONE), ornament, fuelindex);
    }
}