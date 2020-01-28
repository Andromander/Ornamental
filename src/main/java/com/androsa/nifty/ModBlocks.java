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

import static com.androsa.nifty.NiftyBlock.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NiftyMod.MODID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NiftyMod.MODID);

    public static final RegistryObject<StairsBlock> iron_stairs = registerStairs("iron", IRON, 0);
    public static final RegistryObject<StairsBlock> gold_stairs = registerStairs("gold", GOLD, 0);
    public static final RegistryObject<StairsBlock> diamond_stairs = registerStairs("diamond", DIAMOND, 0);
    public static final RegistryObject<StairsBlock> emerald_stairs = registerStairs("emerald", EMERALD, 0);
    public static final RegistryObject<StairsBlock> lapis_stairs = registerStairs("lapis", LAPIS, 0);
    public static final RegistryObject<StairsBlock> obsidian_stairs = registerStairs("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<StairsBlock> coal_stairs = registerStairs("coal", COAL, 12000);
    public static final RegistryObject<StairsBlock> redstone_stairs = registerStairs("redstone", REDSTONE, 0);
    public static final RegistryObject<StairsBlock> missingno_stairs = registerStairs("missingno", MISSINGNO, 0);
    public static final RegistryObject<StairsBlock> clay_stairs = registerStairs("clay", CLAY, 0);
    public static final RegistryObject<StairsBlock> dirt_stairs = registerStairs("dirt", DIRT, 0);
    public static final RegistryObject<StairsBlock> grass_stairs = registerStairs("grass", GRASS, 0);
    public static final RegistryObject<StairsBlock> hay_stairs = registerStairs("hay", HAY, 0);
    public static final RegistryObject<StairsBlock> path_stairs = registerStairs("grass_path", PATH, 0);
    public static final RegistryObject<StairsBlock> bone_stairs = registerStairs("bone", BONE, 0);
    public static final RegistryObject<StairsBlock> snow_stairs = registerStairs("snow", SNOW, 0);
    public static final RegistryObject<StairsBlock> ice_stairs = registerStairs("ice", ICE, 0);
    public static final RegistryObject<StairsBlock> packed_ice_stairs = registerStairs("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<StairsBlock> blue_ice_stairs = registerStairs("blue_ice", BLUE_ICE, 0);

    public static final RegistryObject<SlabBlock> iron_slab = registerSlab("iron", IRON, 0);
    public static final RegistryObject<SlabBlock> gold_slab = registerSlab("gold", GOLD, 0);
    public static final RegistryObject<SlabBlock> diamond_slab = registerSlab("diamond", DIAMOND, 0);
    public static final RegistryObject<SlabBlock> emerald_slab = registerSlab("emerald", EMERALD, 0);
    public static final RegistryObject<SlabBlock> lapis_slab = registerSlab("lapis", LAPIS, 0);
    public static final RegistryObject<SlabBlock> obsidian_slab = registerSlab("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<SlabBlock> coal_slab = registerSlab("coal", COAL, 8000);
    public static final RegistryObject<SlabBlock> redstone_slab = registerSlab("redstone", REDSTONE, 0);
    public static final RegistryObject<SlabBlock> missingno_slab = registerSlab("missingno", MISSINGNO, 0);
    public static final RegistryObject<SlabBlock> clay_slab = registerSlab("clay", CLAY, 0);
    public static final RegistryObject<SlabBlock> dirt_slab = registerSlab("dirt", DIRT, 0);
    public static final RegistryObject<SlabBlock> grass_slab = registerSlab("grass", GRASS, 0);
    public static final RegistryObject<SlabBlock> hay_slab = registerSlab("hay", HAY, 0);
    public static final RegistryObject<SlabBlock> path_slab = registerSlab("grass_path", PATH, 0);
    public static final RegistryObject<SlabBlock> bone_slab = registerSlab("bone", BONE, 0);
    public static final RegistryObject<SlabBlock> snow_slab = registerSlab("snow", SNOW, 0);
    public static final RegistryObject<SlabBlock> ice_slab = registerSlab("ice", ICE, 0);
    public static final RegistryObject<SlabBlock> packed_ice_slab = registerSlab("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<SlabBlock> blue_ice_slab = registerSlab("blue_ice", BLUE_ICE, 0);

    public static final RegistryObject<FenceBlock> iron_fence = registerFence("iron", IRON, 0);
    public static final RegistryObject<FenceBlock> gold_fence = registerFence("gold", GOLD, 0);
    public static final RegistryObject<FenceBlock> diamond_fence = registerFence("diamond", DIAMOND, 0);
    public static final RegistryObject<FenceBlock> emerald_fence = registerFence("emerald", EMERALD, 0);
    public static final RegistryObject<FenceBlock> lapis_fence = registerFence("lapis", LAPIS, 0);
    public static final RegistryObject<FenceBlock> obsidian_fence = registerFence("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<FenceBlock> coal_fence = registerFence("coal", COAL, 5250);
    public static final RegistryObject<FenceBlock> redstone_fence = registerFence("redstone", REDSTONE, 0);
    public static final RegistryObject<FenceBlock> missingno_fence = registerFence("missingno", MISSINGNO, 0);
    public static final RegistryObject<FenceBlock> clay_fence = registerFence("clay", CLAY, 0);
    public static final RegistryObject<FenceBlock> dirt_fence = registerFence("dirt", DIRT, 0);
    public static final RegistryObject<FenceBlock> grass_fence = registerFence("grass", GRASS, 0);
    public static final RegistryObject<FenceBlock> hay_fence = registerFence("hay", HAY, 0);
    public static final RegistryObject<FenceBlock> path_fence = registerFence("grass_path", PATH, 0);
    public static final RegistryObject<FenceBlock> brick_fence = registerFence("brick", BRICK, 0);
    public static final RegistryObject<FenceBlock> quartz_fence = registerFence("quartz", QUARTZ, 0);
    public static final RegistryObject<FenceBlock> bone_fence = registerFence("bone", BONE, 0);
    public static final RegistryObject<FenceBlock> red_nether_brick_fence = registerFence("red_nether_brick", RED_NETHER_BRICK, 0);
    public static final RegistryObject<FenceBlock> snow_fence = registerFence("snow", SNOW, 0);
    public static final RegistryObject<FenceBlock> ice_fence = registerFence("ice", ICE, 0);
    public static final RegistryObject<FenceBlock> packed_ice_fence = registerFence("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<FenceBlock> blue_ice_fence = registerFence("blue_ice", BLUE_ICE, 0);

    public static final RegistryObject<TrapDoorBlock> gold_trapdoor = registerTrapdoor("gold", GOLD, 0);
    public static final RegistryObject<TrapDoorBlock> diamond_trapdoor = registerTrapdoor("diamond", DIAMOND, 0);
    public static final RegistryObject<TrapDoorBlock> emerald_trapdoor = registerTrapdoor("emerald", EMERALD, 0);
    public static final RegistryObject<TrapDoorBlock> lapis_trapdoor = registerTrapdoor("lapis", LAPIS, 0);
    public static final RegistryObject<TrapDoorBlock> obsidian_trapdoor = registerTrapdoor("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<TrapDoorBlock> coal_trapdoor = registerTrapdoor("coal", COAL, 5250);
    public static final RegistryObject<TrapDoorBlock> redstone_trapdoor = registerTrapdoor("redstone", REDSTONE, 0);
    public static final RegistryObject<TrapDoorBlock> missingno_trapdoor = registerTrapdoor("missingno", MISSINGNO, 0);
    public static final RegistryObject<TrapDoorBlock> clay_trapdoor = registerTrapdoor("clay", CLAY, 0);
    public static final RegistryObject<TrapDoorBlock> dirt_trapdoor = registerTrapdoor("dirt", DIRT, 0);
    public static final RegistryObject<TrapDoorBlock> grass_trapdoor = registerTrapdoor("grass", GRASS, 0);
    public static final RegistryObject<TrapDoorBlock> hay_trapdoor = registerTrapdoor("hay", HAY, 0);
    public static final RegistryObject<TrapDoorBlock> path_trapdoor = registerTrapdoor("grass_path", PATH, 0);
    public static final RegistryObject<TrapDoorBlock> brick_trapdoor = registerTrapdoor("brick", BRICK, 0);
    public static final RegistryObject<TrapDoorBlock> quartz_trapdoor = registerTrapdoor("quartz", QUARTZ, 0);
    public static final RegistryObject<TrapDoorBlock> bone_trapdoor = registerTrapdoor("bone", BONE, 0);
    public static final RegistryObject<TrapDoorBlock> nether_brick_trapdoor = registerTrapdoor("nether_brick", NETHER_BRICK, 0);
    public static final RegistryObject<TrapDoorBlock> red_nether_brick_trapdoor = registerTrapdoor("red_nether_brick", RED_NETHER_BRICK, 0);
    public static final RegistryObject<TrapDoorBlock> snow_trapdoor = registerTrapdoor("snow", SNOW, 0);
    public static final RegistryObject<TrapDoorBlock> ice_trapdoor = registerTrapdoor("ice", ICE, 0);
    public static final RegistryObject<TrapDoorBlock> packed_ice_trapdoor = registerTrapdoor("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<TrapDoorBlock> blue_ice_trapdoor = registerTrapdoor("blue_ice", BLUE_ICE, 0);

    public static final RegistryObject<FenceGateBlock> iron_fence_gate = registerFenceGate("iron", IRON, 0);
    public static final RegistryObject<FenceGateBlock> gold_fence_gate = registerFenceGate("gold", GOLD, 0);
    public static final RegistryObject<FenceGateBlock> diamond_fence_gate = registerFenceGate("diamond", DIAMOND, 0);
    public static final RegistryObject<FenceGateBlock> emerald_fence_gate = registerFenceGate("emerald", EMERALD, 0);
    public static final RegistryObject<FenceGateBlock> lapis_fence_gate = registerFenceGate("lapis", LAPIS, 0);
    public static final RegistryObject<FenceGateBlock> obsidian_fence_gate = registerFenceGate("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<FenceGateBlock> coal_fence_gate = registerFenceGate("coal", COAL, 4000);
    public static final RegistryObject<FenceGateBlock> redstone_fence_gate = registerFenceGate("redstone", REDSTONE, 0);
    public static final RegistryObject<FenceGateBlock> missingno_fence_gate = registerFenceGate("missingno", MISSINGNO, 0);
    public static final RegistryObject<FenceGateBlock> clay_fence_gate = registerFenceGate("clay", CLAY, 0);
    public static final RegistryObject<FenceGateBlock> dirt_fence_gate = registerFenceGate("dirt", DIRT, 0);
    public static final RegistryObject<FenceGateBlock> grass_fence_gate = registerFenceGate("grass", GRASS, 0);
    public static final RegistryObject<FenceGateBlock> hay_fence_gate = registerFenceGate("hay", HAY, 0);
    public static final RegistryObject<FenceGateBlock> path_fence_gate = registerFenceGate("grass_path", PATH, 0);
    public static final RegistryObject<FenceGateBlock> brick_fence_gate = registerFenceGate("brick", BRICK, 0);
    public static final RegistryObject<FenceGateBlock> quartz_fence_gate = registerFenceGate("quartz", QUARTZ, 0);
    public static final RegistryObject<FenceGateBlock> bone_fence_gate = registerFenceGate("bone", BONE, 0);
    public static final RegistryObject<FenceGateBlock> nether_brick_fence_gate = registerFenceGate("nether_brick", NETHER_BRICK, 0);
    public static final RegistryObject<FenceGateBlock> red_nether_brick_fence_gate = registerFenceGate("red_nether_brick", RED_NETHER_BRICK, 0);
    public static final RegistryObject<FenceGateBlock> snow_fence_gate = registerFenceGate("snow", SNOW, 0);
    public static final RegistryObject<FenceGateBlock> ice_fence_gate = registerFenceGate("ice", ICE, 0);
    public static final RegistryObject<FenceGateBlock> packed_ice_fence_gate = registerFenceGate("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<FenceGateBlock> blue_ice_fence_gate = registerFenceGate("blue_ice", BLUE_ICE, 0);

    public static final RegistryObject<DoorBlock> gold_door = registerDoor("gold", GOLD, 0);
    public static final RegistryObject<DoorBlock> diamond_door = registerDoor("diamond", DIAMOND, 0);
    public static final RegistryObject<DoorBlock> emerald_door = registerDoor("emerald", EMERALD, 0);
    public static final RegistryObject<DoorBlock> lapis_door = registerDoor("lapis", LAPIS, 0);
    public static final RegistryObject<DoorBlock> obsidian_door = registerDoor("obsidian", OBSIDIAN, 0);
    public static final RegistryObject<DoorBlock> coal_door = registerDoor("coal", COAL, 10500);
    public static final RegistryObject<DoorBlock> redstone_door = registerDoor("redstone", REDSTONE, 0);
    public static final RegistryObject<DoorBlock> missingno_door = registerDoor("missingno", MISSINGNO, 0);
    public static final RegistryObject<DoorBlock> clay_door = registerDoor("clay", CLAY, 0);
    public static final RegistryObject<DoorBlock> dirt_door = registerDoor("dirt", DIRT, 0);
    public static final RegistryObject<DoorBlock> grass_door = registerDoor("grass", GRASS, 0);
    public static final RegistryObject<DoorBlock> hay_door = registerDoor("hay", HAY, 0);
    public static final RegistryObject<DoorBlock> path_door = registerDoor("grass_path", PATH, 0);
    public static final RegistryObject<DoorBlock> brick_door = registerDoor("brick", BRICK, 0);
    public static final RegistryObject<DoorBlock> quartz_door = registerDoor("quartz", QUARTZ, 0);
    public static final RegistryObject<DoorBlock> bone_door = registerDoor("bone", BONE, 0);
    public static final RegistryObject<DoorBlock> nether_brick_door = registerDoor("nether_brick", RED_NETHER_BRICK, 0);
    public static final RegistryObject<DoorBlock> red_nether_brick_door = registerDoor("red_nether_brick", NETHER_BRICK, 0);
    public static final RegistryObject<DoorBlock> snow_door = registerDoor("snow", SNOW, 0);
    public static final RegistryObject<DoorBlock> ice_door = registerDoor("ice", ICE, 0);
    public static final RegistryObject<DoorBlock> packed_ice_door = registerDoor("packed_ice", PACKED_ICE, 0);
    public static final RegistryObject<DoorBlock> blue_ice_door = registerDoor("blue_ice", BLUE_ICE, 0);

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

    private static RegistryObject<StairsBlock> registerStairs(String name, NiftyBlock nifty, int fuelTime) {
        Block.Properties props = Block.Properties.create(nifty.material, nifty.color);
        if (nifty == NiftyBlock.ICE) props.nonOpaque();

        return registerBlock(name + "_stairs", () -> new NiftyStairs(props, nifty), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, nifty, fuelTime));
    }

    private static RegistryObject<SlabBlock> registerSlab(String name, NiftyBlock nifty, int fuelTime) {
        Block.Properties props = Block.Properties.create(nifty.material, nifty.color);
        if (nifty == NiftyBlock.ICE) props.nonOpaque();

        return registerBlock(name + "_slab", () -> new NiftySlab(props, nifty), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, nifty, fuelTime));
    }

    private static RegistryObject<FenceBlock> registerFence(String name, NiftyBlock nifty, int fuelTime) {
        return registerBlock(name + "_fence", () -> new NiftyFence(Block.Properties.create(nifty.material, nifty.color), nifty), item ->
                registerBlockItem(item, ItemGroup.DECORATIONS, nifty, fuelTime));
    }

    private static RegistryObject<TrapDoorBlock> registerTrapdoor(String name, NiftyBlock nifty, int fuelTime) {
        return registerBlock(name + "_trapdoor", () -> new NiftyTrapDoor(Block.Properties.create(nifty.material, nifty.color), nifty), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, nifty, fuelTime));
    }

    private static RegistryObject<FenceGateBlock> registerFenceGate(String name, NiftyBlock nifty, int fuelTime) {
        return registerBlock(name + "_fence_gate", () -> new NiftyFenceGate(Block.Properties.create(nifty.material, nifty.color), nifty), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, nifty, fuelTime));
    }

    private static RegistryObject<DoorBlock> registerDoor(String name, NiftyBlock nifty, int fuelTime) {
        return registerBlock(name + "_door", () -> new NiftyDoor(Block.Properties.create(nifty.material, nifty.color), nifty), item ->
                registerBlockItemDoor(item, nifty, fuelTime));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group, NiftyBlock nifty, int fuelTime) {
        return () -> new NiftyBlockItem(block.get(), new Item.Properties().group(group), nifty, fuelTime);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, NiftyBlock nifty, int burnTime) {
        return () -> new NiftyTallBlockItem(block.get(), new Item.Properties().group(ItemGroup.REDSTONE), burnTime, nifty);
    }
}