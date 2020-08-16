package com.androsa.ornamental.registry;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.builder.OrnamentBuilders;
import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess"})
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OrnamentalMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OrnamentalMod.MODID);

    public static final RegistryObject<OrnamentStairs> iron_stairs = registerStairs(OrnamentBuilders.IRON);
    public static final RegistryObject<OrnamentStairs> gold_stairs = registerStairs(OrnamentBuilders.GOLD);
    public static final RegistryObject<OrnamentStairs> diamond_stairs = registerStairs(OrnamentBuilders.DIAMOND);
    public static final RegistryObject<OrnamentStairs> emerald_stairs = registerStairs(OrnamentBuilders.EMERALD);
    public static final RegistryObject<OrnamentStairs> lapis_stairs = registerStairs(OrnamentBuilders.LAPIS);
    public static final RegistryObject<OrnamentStairs> obsidian_stairs = registerStairs(OrnamentBuilders.OBSIDIAN);
    public static final RegistryObject<OrnamentStairs> coal_stairs = registerStairs(OrnamentBuilders.COAL);
    public static final RegistryObject<OrnamentStairs> redstone_stairs = registerStairs(OrnamentBuilders.REDSTONE);
    public static final RegistryObject<OrnamentStairs> missingno_stairs = registerStairs(OrnamentBuilders.MISSINGNO);
    public static final RegistryObject<OrnamentStairs> clay_stairs = registerStairs(OrnamentBuilders.CLAY);
    public static final RegistryObject<OrnamentStairs> dirt_stairs = registerStairs(OrnamentBuilders.DIRT);
    public static final RegistryObject<OrnamentStairs> grass_stairs = registerStairs(OrnamentBuilders.GRASS);
    public static final RegistryObject<OrnamentStairs> hay_stairs = registerStairs(OrnamentBuilders.HAY);
    public static final RegistryObject<OrnamentStairs> path_stairs = registerStairs(OrnamentBuilders.PATH);
    public static final RegistryObject<OrnamentStairs> bone_stairs = registerStairs(OrnamentBuilders.BONE);
    public static final RegistryObject<OrnamentStairs> snow_stairs = registerStairs(OrnamentBuilders.SNOW);
    public static final RegistryObject<OrnamentStairs> ice_stairs = registerStairs(OrnamentBuilders.ICE);
    public static final RegistryObject<OrnamentStairs> packed_ice_stairs = registerStairs(OrnamentBuilders.PACKED_ICE);
    public static final RegistryObject<OrnamentStairs> blue_ice_stairs = registerStairs(OrnamentBuilders.BLUE_ICE);
    public static final RegistryObject<OrnamentStairs> netherite_stairs = registerStairs(OrnamentBuilders.NETHERITE);

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

    private static RegistryObject<OrnamentStairs> registerStairs(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_stairs", () -> new OrnamentStairs(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 4));
    }

    private static RegistryObject<OrnamentSlab> registerSlab(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_slab", () -> new OrnamentSlab(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 3));
    }

    private static RegistryObject<OrnamentFence> registerFence(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence", () -> new OrnamentFence(props, builder), item ->
                registerBlockItem(item, ItemGroup.DECORATIONS, builder, 1));
    }

    private static RegistryObject<OrnamentTrapDoor> registerTrapdoor(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder)
                .notSolid()
                .setAllowsSpawn((state, reader, pos, type) -> false);

        return registerBlock(builder.name + "_trapdoor", () -> new OrnamentTrapDoor(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 5));
    }

    private static RegistryObject<OrnamentFenceGate> registerFenceGate(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence_gate", () -> new OrnamentFenceGate(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 2));
    }

    private static RegistryObject<OrnamentDoor> registerDoor(OrnamentBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder).notSolid();

        return registerBlock(builder.name + "_door", () -> new OrnamentDoor(props, builder), item ->
                registerBlockItemDoor(item, builder, 0));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentBlockItem(block.get(), PropertiesHelper.createProps(ornament, group), ornament, fuelindex);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentTallBlockItem(block.get(), PropertiesHelper.createProps(ornament, ItemGroup.REDSTONE), ornament, fuelindex);
    }
}