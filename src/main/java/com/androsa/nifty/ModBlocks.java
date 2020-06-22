package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import com.androsa.nifty.items.NiftyBlockItem;
import com.androsa.nifty.items.NiftyTallBlockItem;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NiftyMod.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NiftyMod.MODID);

    public static final RegistryObject<StairsBlock> iron_stairs = registerStairs(NiftyBuilders.IRON);
    public static final RegistryObject<StairsBlock> gold_stairs = registerStairs(NiftyBuilders.GOLD);
    public static final RegistryObject<StairsBlock> diamond_stairs = registerStairs(NiftyBuilders.DIAMOND);
    public static final RegistryObject<StairsBlock> emerald_stairs = registerStairs(NiftyBuilders.EMERALD);
    public static final RegistryObject<StairsBlock> lapis_stairs = registerStairs(NiftyBuilders.LAPIS);
    public static final RegistryObject<StairsBlock> obsidian_stairs = registerStairs(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<StairsBlock> coal_stairs = registerStairs(NiftyBuilders.COAL);
    public static final RegistryObject<StairsBlock> redstone_stairs = registerStairs(NiftyBuilders.REDSTONE);
    public static final RegistryObject<StairsBlock> missingno_stairs = registerStairs(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<StairsBlock> clay_stairs = registerStairs(NiftyBuilders.CLAY);
    public static final RegistryObject<StairsBlock> dirt_stairs = registerStairs(NiftyBuilders.DIRT);
    public static final RegistryObject<StairsBlock> grass_stairs = registerStairs(NiftyBuilders.GRASS);
    public static final RegistryObject<StairsBlock> hay_stairs = registerStairs(NiftyBuilders.HAY);
    public static final RegistryObject<StairsBlock> path_stairs = registerStairs(NiftyBuilders.PATH);
    public static final RegistryObject<StairsBlock> bone_stairs = registerStairs(NiftyBuilders.BONE);
    public static final RegistryObject<StairsBlock> snow_stairs = registerStairs(NiftyBuilders.SNOW);
    public static final RegistryObject<StairsBlock> ice_stairs = registerStairs(NiftyBuilders.ICE);
    public static final RegistryObject<StairsBlock> packed_ice_stairs = registerStairs(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<StairsBlock> blue_ice_stairs = registerStairs(NiftyBuilders.BLUE_ICE);

    public static final RegistryObject<SlabBlock> iron_slab = registerSlab(NiftyBuilders.IRON);
    public static final RegistryObject<SlabBlock> gold_slab = registerSlab(NiftyBuilders.GOLD);
    public static final RegistryObject<SlabBlock> diamond_slab = registerSlab(NiftyBuilders.DIAMOND);
    public static final RegistryObject<SlabBlock> emerald_slab = registerSlab(NiftyBuilders.EMERALD);
    public static final RegistryObject<SlabBlock> lapis_slab = registerSlab(NiftyBuilders.LAPIS);
    public static final RegistryObject<SlabBlock> obsidian_slab = registerSlab(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<SlabBlock> coal_slab = registerSlab(NiftyBuilders.COAL);
    public static final RegistryObject<SlabBlock> redstone_slab = registerSlab(NiftyBuilders.REDSTONE);
    public static final RegistryObject<SlabBlock> missingno_slab = registerSlab(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<SlabBlock> clay_slab = registerSlab(NiftyBuilders.CLAY);
    public static final RegistryObject<SlabBlock> dirt_slab = registerSlab(NiftyBuilders.DIRT);
    public static final RegistryObject<SlabBlock> grass_slab = registerSlab(NiftyBuilders.GRASS);
    public static final RegistryObject<SlabBlock> hay_slab = registerSlab(NiftyBuilders.HAY);
    public static final RegistryObject<SlabBlock> path_slab = registerSlab(NiftyBuilders.PATH);
    public static final RegistryObject<SlabBlock> bone_slab = registerSlab(NiftyBuilders.BONE);
    public static final RegistryObject<SlabBlock> snow_slab = registerSlab(NiftyBuilders.SNOW);
    public static final RegistryObject<SlabBlock> ice_slab = registerSlab(NiftyBuilders.ICE);
    public static final RegistryObject<SlabBlock> packed_ice_slab = registerSlab(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<SlabBlock> blue_ice_slab = registerSlab(NiftyBuilders.BLUE_ICE);

    public static final RegistryObject<FenceBlock> iron_fence = registerFence(NiftyBuilders.IRON);
    public static final RegistryObject<FenceBlock> gold_fence = registerFence(NiftyBuilders.GOLD);
    public static final RegistryObject<FenceBlock> diamond_fence = registerFence(NiftyBuilders.DIAMOND);
    public static final RegistryObject<FenceBlock> emerald_fence = registerFence(NiftyBuilders.EMERALD);
    public static final RegistryObject<FenceBlock> lapis_fence = registerFence(NiftyBuilders.LAPIS);
    public static final RegistryObject<FenceBlock> obsidian_fence = registerFence(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<FenceBlock> coal_fence = registerFence(NiftyBuilders.COAL);
    public static final RegistryObject<FenceBlock> redstone_fence = registerFence(NiftyBuilders.REDSTONE);
    public static final RegistryObject<FenceBlock> missingno_fence = registerFence(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<FenceBlock> clay_fence = registerFence(NiftyBuilders.CLAY);
    public static final RegistryObject<FenceBlock> dirt_fence = registerFence(NiftyBuilders.DIRT);
    public static final RegistryObject<FenceBlock> grass_fence = registerFence(NiftyBuilders.GRASS);
    public static final RegistryObject<FenceBlock> hay_fence = registerFence(NiftyBuilders.HAY);
    public static final RegistryObject<FenceBlock> path_fence = registerFence(NiftyBuilders.PATH);
    public static final RegistryObject<FenceBlock> brick_fence = registerFence(NiftyBuilders.BRICK);
    public static final RegistryObject<FenceBlock> quartz_fence = registerFence(NiftyBuilders.QUARTZ);
    public static final RegistryObject<FenceBlock> bone_fence = registerFence(NiftyBuilders.BONE);
    public static final RegistryObject<FenceBlock> red_nether_brick_fence = registerFence(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<FenceBlock> snow_fence = registerFence(NiftyBuilders.SNOW);
    public static final RegistryObject<FenceBlock> ice_fence = registerFence(NiftyBuilders.ICE);
    public static final RegistryObject<FenceBlock> packed_ice_fence = registerFence(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<FenceBlock> blue_ice_fence = registerFence(NiftyBuilders.BLUE_ICE);

    public static final RegistryObject<TrapDoorBlock> gold_trapdoor = registerTrapdoor(NiftyBuilders.GOLD);
    public static final RegistryObject<TrapDoorBlock> diamond_trapdoor = registerTrapdoor(NiftyBuilders.DIAMOND);
    public static final RegistryObject<TrapDoorBlock> emerald_trapdoor = registerTrapdoor(NiftyBuilders.EMERALD);
    public static final RegistryObject<TrapDoorBlock> lapis_trapdoor = registerTrapdoor(NiftyBuilders.LAPIS);
    public static final RegistryObject<TrapDoorBlock> obsidian_trapdoor = registerTrapdoor(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<TrapDoorBlock> coal_trapdoor = registerTrapdoor(NiftyBuilders.COAL);
    public static final RegistryObject<TrapDoorBlock> redstone_trapdoor = registerTrapdoor(NiftyBuilders.REDSTONE);
    public static final RegistryObject<TrapDoorBlock> missingno_trapdoor = registerTrapdoor(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<TrapDoorBlock> clay_trapdoor = registerTrapdoor(NiftyBuilders.CLAY);
    public static final RegistryObject<TrapDoorBlock> dirt_trapdoor = registerTrapdoor(NiftyBuilders.DIRT);
    public static final RegistryObject<TrapDoorBlock> grass_trapdoor = registerTrapdoor(NiftyBuilders.GRASS);
    public static final RegistryObject<TrapDoorBlock> hay_trapdoor = registerTrapdoor(NiftyBuilders.HAY);
    public static final RegistryObject<TrapDoorBlock> path_trapdoor = registerTrapdoor(NiftyBuilders.PATH);
    public static final RegistryObject<TrapDoorBlock> brick_trapdoor = registerTrapdoor(NiftyBuilders.BRICK);
    public static final RegistryObject<TrapDoorBlock> quartz_trapdoor = registerTrapdoor(NiftyBuilders.QUARTZ);
    public static final RegistryObject<TrapDoorBlock> bone_trapdoor = registerTrapdoor(NiftyBuilders.BONE);
    public static final RegistryObject<TrapDoorBlock> nether_brick_trapdoor = registerTrapdoor(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<TrapDoorBlock> red_nether_brick_trapdoor = registerTrapdoor(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<TrapDoorBlock> snow_trapdoor = registerTrapdoor(NiftyBuilders.SNOW);
    public static final RegistryObject<TrapDoorBlock> ice_trapdoor = registerTrapdoor(NiftyBuilders.ICE);
    public static final RegistryObject<TrapDoorBlock> packed_ice_trapdoor = registerTrapdoor(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<TrapDoorBlock> blue_ice_trapdoor = registerTrapdoor(NiftyBuilders.BLUE_ICE);

    public static final RegistryObject<FenceGateBlock> iron_fence_gate = registerFenceGate(NiftyBuilders.IRON);
    public static final RegistryObject<FenceGateBlock> gold_fence_gate = registerFenceGate(NiftyBuilders.GOLD);
    public static final RegistryObject<FenceGateBlock> diamond_fence_gate = registerFenceGate(NiftyBuilders.DIAMOND);
    public static final RegistryObject<FenceGateBlock> emerald_fence_gate = registerFenceGate(NiftyBuilders.EMERALD);
    public static final RegistryObject<FenceGateBlock> lapis_fence_gate = registerFenceGate(NiftyBuilders.LAPIS);
    public static final RegistryObject<FenceGateBlock> obsidian_fence_gate = registerFenceGate(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<FenceGateBlock> coal_fence_gate = registerFenceGate(NiftyBuilders.COAL);
    public static final RegistryObject<FenceGateBlock> redstone_fence_gate = registerFenceGate(NiftyBuilders.REDSTONE);
    public static final RegistryObject<FenceGateBlock> missingno_fence_gate = registerFenceGate(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<FenceGateBlock> clay_fence_gate = registerFenceGate(NiftyBuilders.CLAY);
    public static final RegistryObject<FenceGateBlock> dirt_fence_gate = registerFenceGate(NiftyBuilders.DIRT);
    public static final RegistryObject<FenceGateBlock> grass_fence_gate = registerFenceGate(NiftyBuilders.GRASS);
    public static final RegistryObject<FenceGateBlock> hay_fence_gate = registerFenceGate(NiftyBuilders.HAY);
    public static final RegistryObject<FenceGateBlock> path_fence_gate = registerFenceGate(NiftyBuilders.PATH);
    public static final RegistryObject<FenceGateBlock> brick_fence_gate = registerFenceGate(NiftyBuilders.BRICK);
    public static final RegistryObject<FenceGateBlock> quartz_fence_gate = registerFenceGate(NiftyBuilders.QUARTZ);
    public static final RegistryObject<FenceGateBlock> bone_fence_gate = registerFenceGate(NiftyBuilders.BONE);
    public static final RegistryObject<FenceGateBlock> nether_brick_fence_gate = registerFenceGate(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<FenceGateBlock> red_nether_brick_fence_gate = registerFenceGate(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<FenceGateBlock> snow_fence_gate = registerFenceGate(NiftyBuilders.SNOW);
    public static final RegistryObject<FenceGateBlock> ice_fence_gate = registerFenceGate(NiftyBuilders.ICE);
    public static final RegistryObject<FenceGateBlock> packed_ice_fence_gate = registerFenceGate(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<FenceGateBlock> blue_ice_fence_gate = registerFenceGate(NiftyBuilders.BLUE_ICE);

    public static final RegistryObject<DoorBlock> gold_door = registerDoor(NiftyBuilders.GOLD);
    public static final RegistryObject<DoorBlock> diamond_door = registerDoor(NiftyBuilders.DIAMOND);
    public static final RegistryObject<DoorBlock> emerald_door = registerDoor(NiftyBuilders.EMERALD);
    public static final RegistryObject<DoorBlock> lapis_door = registerDoor(NiftyBuilders.LAPIS);
    public static final RegistryObject<DoorBlock> obsidian_door = registerDoor(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<DoorBlock> coal_door = registerDoor(NiftyBuilders.COAL);
    public static final RegistryObject<DoorBlock> redstone_door = registerDoor(NiftyBuilders.REDSTONE);
    public static final RegistryObject<DoorBlock> missingno_door = registerDoor(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<DoorBlock> clay_door = registerDoor(NiftyBuilders.CLAY);
    public static final RegistryObject<DoorBlock> dirt_door = registerDoor(NiftyBuilders.DIRT);
    public static final RegistryObject<DoorBlock> grass_door = registerDoor(NiftyBuilders.GRASS);
    public static final RegistryObject<DoorBlock> hay_door = registerDoor(NiftyBuilders.HAY);
    public static final RegistryObject<DoorBlock> path_door = registerDoor(NiftyBuilders.PATH);
    public static final RegistryObject<DoorBlock> brick_door = registerDoor(NiftyBuilders.BRICK);
    public static final RegistryObject<DoorBlock> quartz_door = registerDoor(NiftyBuilders.QUARTZ);
    public static final RegistryObject<DoorBlock> bone_door = registerDoor(NiftyBuilders.BONE);
    public static final RegistryObject<DoorBlock> nether_brick_door = registerDoor(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<DoorBlock> red_nether_brick_door = registerDoor(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<DoorBlock> snow_door = registerDoor(NiftyBuilders.SNOW);
    public static final RegistryObject<DoorBlock> ice_door = registerDoor(NiftyBuilders.ICE);
    public static final RegistryObject<DoorBlock> packed_ice_door = registerDoor(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<DoorBlock> blue_ice_door = registerDoor(NiftyBuilders.BLUE_ICE);

    /*public static final List<RegistryObject<Block>> test = registerArray("wool", "fence", ItemGroup.DECORATIONS);

    private static List<RegistryObject<Block>> registerArray(String matName, String blockName, ItemGroup group) {
        List<RegistryObject<Block>> blocks = new ArrayList<>();

        for (DyeColor i : DyeColor.values()) {
            String name = i.getName() + "_" + matName + "_" + blockName;
            RegistryObject<Block> reg = BLOCKS.register(name, () -> new NiftyFence(MISSINGNO));
            ITEMS.register(name, () -> new BlockItem(reg.get(), new Item.Properties().group(group)));
            blocks.add(reg);
        }

        return blocks;
    }*/

    private static Block.Properties createProps(NiftyBuilder builder) {
        Block.Properties props = Block.Properties.create(builder.material, builder.color)
                .hardnessAndResistance(builder.hardness, builder.resistance)
                .sound(builder.sound)
                .harvestTool(builder.harvestTool)
                .harvestLevel(builder.harvestLevel)
                .slipperiness(builder.slipperiness);
        if (builder.doesTick) props.tickRandomly();
        if (builder.isIce) props.nonOpaque();

        return props;
    }

    private static RegistryObject<StairsBlock> registerStairs(NiftyBuilder builder) {
        Block.Properties props = createProps(builder);

        return registerBlock(builder.name + "_stairs", () -> new NiftyStairs(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 4));
    }

    private static RegistryObject<SlabBlock> registerSlab(NiftyBuilder builder) {
        Block.Properties props = createProps(builder);

        return registerBlock(builder.name + "_slab", () -> new NiftySlab(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 3));
    }

    private static RegistryObject<FenceBlock> registerFence(NiftyBuilder builder) {
        Block.Properties props = createProps(builder);

        return registerBlock(builder.name + "_fence", () -> new NiftyFence(props, builder), item ->
                registerBlockItem(item, ItemGroup.DECORATIONS, builder, 1));
    }

    private static RegistryObject<TrapDoorBlock> registerTrapdoor(NiftyBuilder builder) {
        Block.Properties props = createProps(builder).nonOpaque();

        return registerBlock(builder.name + "_trapdoor", () -> new NiftyTrapDoor(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 5));
    }

    private static RegistryObject<FenceGateBlock> registerFenceGate(NiftyBuilder builder) {
        Block.Properties props = createProps(builder);

        return registerBlock(builder.name + "_fence_gate", () -> new NiftyFenceGate(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 2));
    }

    private static RegistryObject<DoorBlock> registerDoor(NiftyBuilder builder) {
        Block.Properties props = createProps(builder).nonOpaque();

        return registerBlock(builder.name + "_door", () -> new NiftyDoor(props, builder), item ->
                registerBlockItemDoor(item, builder, 0));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group, NiftyBuilder nifty, int fuelindex) {
        return () -> new NiftyBlockItem(block.get(), new Item.Properties().group(group), nifty, fuelindex);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, NiftyBuilder nifty, int fuelindex) {
        return () -> new NiftyTallBlockItem(block.get(), new Item.Properties().group(ItemGroup.REDSTONE), nifty, fuelindex);
    }
}