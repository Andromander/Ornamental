package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import com.androsa.nifty.blocks.dirt.*;
import com.androsa.nifty.blocks.grass.*;
import com.androsa.nifty.blocks.path.*;
import com.androsa.nifty.blocks.redstone.*;
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

    public static final RegistryObject<Block> iron_stairs = registerBlockGeneral("iron_stairs", () -> new NiftyStairs(IRON, false), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> gold_stairs = registerBlockGeneral("gold_stairs", () -> new NiftyStairs(GOLD, true), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> diamond_stairs = registerBlockGeneral("diamond_stairs", () -> new NiftyStairs(DIAMOND, true), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> emerald_stairs = registerBlockGeneral("emerald_stairs", () -> new NiftyStairs(EMERALD, true), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> lapis_stairs = registerBlockGeneral("lapis_stairs", () -> new NiftyStairs(LAPIS, false), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> obsidian_stairs = registerBlockGeneral("obsidian_stairs", () -> new NiftyStairs(OBSIDIAN, false), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> coal_stairs = registerBlockWithFuel("coal_stairs", () -> new NiftyStairs(COAL, false), ItemGroup.BUILDING_BLOCKS, 12000);
    public static final RegistryObject<Block> redstone_stairs = registerBlockGeneral("redstone_stairs", NiftyRedstoneStairs::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> missingno_stairs = registerBlockGeneral("missingno_stairs", () -> new NiftyStairs(MISSINGNO, false), ItemGroup.BUILDING_BLOCKS, true);
    public static final RegistryObject<Block> clay_stairs = registerBlockGeneral("clay_stairs", () -> new NiftyStairs(CLAY, false), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> dirt_stairs = registerBlockGeneral("dirt_stairs", NiftyDirtStairs::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> grass_stairs = registerBlockGeneral("grass_stairs", NiftyGrassStairs::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> hay_stairs = registerBlockGeneral("hay_stairs", () -> new NiftyStairs(HAY, false), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> path_stairs = registerBlockGeneral("grass_path_stairs", NiftyPathStairs::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> bone_stairs = registerBlockGeneral("bone_stairs", () -> new NiftyStairs(BONE, false), ItemGroup.BUILDING_BLOCKS, false);

    public static final RegistryObject<Block> iron_slab = registerBlockGeneral("iron_slab", () -> new NiftySlab(IRON), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> gold_slab = registerBlockGeneral("gold_slab", () -> new NiftySlab(GOLD), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> diamond_slab = registerBlockGeneral("diamond_slab", () -> new NiftySlab(DIAMOND), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> emerald_slab = registerBlockGeneral("emerald_slab", () -> new NiftySlab(EMERALD), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> lapis_slab = registerBlockGeneral("lapis_slab", () -> new NiftySlab(LAPIS), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> obsidian_slab = registerBlockGeneral("obsidian_slab", () -> new NiftySlab(OBSIDIAN), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> coal_slab = registerBlockWithFuel("coal_slab", () -> new NiftySlab(COAL), ItemGroup.BUILDING_BLOCKS, 8000);
    public static final RegistryObject<Block> redstone_slab  = registerBlockGeneral("redstone_slab", NiftyRedstoneSlab::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> missingno_slab = registerBlockGeneral("missingno_slab", () -> new NiftySlab(MISSINGNO), ItemGroup.BUILDING_BLOCKS, true);
    public static final RegistryObject<Block> clay_slab = registerBlockGeneral("clay_slab", () -> new NiftySlab(CLAY), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> dirt_slab = registerBlockGeneral("dirt_slab", NiftyDirtSlab::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> grass_slab = registerBlockGeneral("grass_slab", NiftyGrassSlab::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> hay_slab = registerBlockGeneral("hay_slab", () -> new NiftySlab(HAY), ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> path_slab = registerBlockGeneral("grass_path_slab", NiftyPathSlab::new, ItemGroup.BUILDING_BLOCKS, false);
    public static final RegistryObject<Block> bone_slab = registerBlockGeneral("bone_slab", () -> new NiftySlab(BONE), ItemGroup.BUILDING_BLOCKS, false);

    public static final RegistryObject<Block> iron_fence = registerBlockGeneral("iron_fence", () -> new NiftyFence(IRON), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> gold_fence = registerBlockGeneral("gold_fence", () -> new NiftyFence(GOLD), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> diamond_fence = registerBlockGeneral("diamond_fence", () -> new NiftyFence(DIAMOND), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> emerald_fence = registerBlockGeneral("emerald_fence", () -> new NiftyFence(EMERALD), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> lapis_fence = registerBlockGeneral("lapis_fence", () -> new NiftyFence(LAPIS), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> obsidian_fence = registerBlockGeneral("obsidian_fence", () -> new NiftyFence(OBSIDIAN), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> coal_fence = registerBlockWithFuel("coal_fence", () -> new NiftyFence(COAL), ItemGroup.DECORATIONS, 5250);
    public static final RegistryObject<Block> redstone_fence = registerBlockGeneral("redstone_fence", NiftyRedstoneFence::new, ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> missingno_fence = registerBlockGeneral("missingno_fence", () -> new NiftyFence(MISSINGNO), ItemGroup.DECORATIONS, true);
    public static final RegistryObject<Block> clay_fence = registerBlockGeneral("clay_fence", () -> new NiftyFence(CLAY), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> dirt_fence = registerBlockGeneral("dirt_fence", NiftyDirtFence::new, ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> grass_fence = registerBlockGeneral("grass_fence", NiftyGrassFence::new, ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> hay_fence = registerBlockGeneral("hay_fence", () -> new NiftyFence(HAY), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> path_fence = registerBlockGeneral("grass_path_fence", NiftyPathFence::new, ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> brick_fence = registerBlockGeneral("brick_fence", () -> new NiftyFence(BRICK), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> quartz_fence = registerBlockGeneral("quartz_fence", () -> new NiftyFence(QUARTZ), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> bone_fence = registerBlockGeneral("bone_fence", () -> new NiftyFence(BONE), ItemGroup.DECORATIONS, false);
    public static final RegistryObject<Block> red_nether_brick_fence = registerBlockGeneral("red_nether_brick_fence", () -> new NiftyFence(NETHER_BRICK), ItemGroup.DECORATIONS, false);

    public static final RegistryObject<Block> gold_trapdoor = registerBlockGeneral("gold_trapdoor", () -> new NiftyTrapDoor(GOLD), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> diamond_trapdoor = registerBlockGeneral("diamond_trapdoor", () -> new NiftyTrapDoor(DIAMOND), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> emerald_trapdoor = registerBlockGeneral("emerald_trapdoor", () -> new NiftyTrapDoor(EMERALD), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> lapis_trapdoor = registerBlockGeneral("lapis_trapdoor", () -> new NiftyTrapDoor(LAPIS), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> obsidian_trapdoor = registerBlockGeneral("obsidian_trapdoor", () -> new NiftyTrapDoor(OBSIDIAN), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> coal_trapdoor = registerBlockWithFuel("coal_trapdoor", () -> new NiftyTrapDoor(COAL), ItemGroup.REDSTONE, 5250);
    public static final RegistryObject<Block> redstone_trapdoor = registerBlockGeneral("redstone_trapdoor", NiftyRedstoneTrapDoor::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> missingno_trapdoor = registerBlockGeneral("missingno_trapdoor", () -> new NiftyTrapDoor(MISSINGNO), ItemGroup.REDSTONE, true);
    public static final RegistryObject<Block> clay_trapdoor = registerBlockGeneral("clay_trapdoor", () -> new NiftyTrapDoor(CLAY), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> dirt_trapdoor = registerBlockGeneral("dirt_trapdoor", NiftyDirtTrapDoor::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> grass_trapdoor = registerBlockGeneral("grass_trapdoor", NiftyGrassTrapDoor::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> hay_trapdoor = registerBlockGeneral("hay_trapdoor", () -> new NiftyTrapDoor(HAY), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> path_trapdoor = registerBlockGeneral("grass_path_trapdoor", NiftyPathTrapDoor::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> brick_trapdoor = registerBlockGeneral("brick_trapdoor", () -> new NiftyTrapDoor(BRICK), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> quartz_trapdoor = registerBlockGeneral("quartz_trapdoor", () -> new NiftyTrapDoor(QUARTZ), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> bone_trapdoor = registerBlockGeneral("bone_trapdoor", () -> new NiftyTrapDoor(BONE), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> nether_brick_trapdoor = registerBlockGeneral("nether_brick_trapdoor", () -> new NiftyTrapDoor(NETHER_BRICK), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> red_nether_brick_trapdoor = registerBlockGeneral("red_nether_brick_trapdoor", () -> new NiftyTrapDoor(NETHER_BRICK), ItemGroup.REDSTONE, false);

    public static final RegistryObject<Block> iron_fence_gate = registerBlockGeneral("iron_fence_gate", () -> new NiftyFenceGate(IRON), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> gold_fence_gate = registerBlockGeneral("gold_fence_gate", () -> new NiftyFenceGate(GOLD), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> diamond_fence_gate = registerBlockGeneral("diamond_fence_gate", () -> new NiftyFenceGate(DIAMOND), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> emerald_fence_gate = registerBlockGeneral("emerald_fence_gate", () -> new NiftyFenceGate(EMERALD), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> lapis_fence_gate = registerBlockGeneral("lapis_fence_gate", () -> new NiftyFenceGate(LAPIS), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> obsidian_fence_gate  = registerBlockGeneral("obsidian_fence_gate", () -> new NiftyFenceGate(OBSIDIAN), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> coal_fence_gate = registerBlockWithFuel("coal_fence_gate", () -> new NiftyFenceGate(COAL), ItemGroup.REDSTONE, 4000);
    public static final RegistryObject<Block> redstone_fence_gate  = registerBlockGeneral("redstone_fence_gate", NiftyRedstoneFenceGate::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> missingno_fence_gate = registerBlockGeneral("missingno_fence_gate", () -> new NiftyFenceGate(MISSINGNO), ItemGroup.REDSTONE, true);
    public static final RegistryObject<Block> clay_fence_gate = registerBlockGeneral("clay_fence_gate", () -> new NiftyFenceGate(CLAY), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> dirt_fence_gate = registerBlockGeneral("dirt_fence_gate", NiftyDirtFenceGate::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> grass_fence_gate = registerBlockGeneral("grass_fence_gate", NiftyGrassFenceGate::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> hay_fence_gate = registerBlockGeneral("hay_fence_gate", () -> new NiftyFenceGate(HAY), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> path_fence_gate = registerBlockGeneral("grass_path_fence_gate", NiftyPathFenceGate::new, ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> brick_fence_gate = registerBlockGeneral("brick_fence_gate", () -> new NiftyFenceGate(BRICK), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> quartz_fence_gate = registerBlockGeneral("quartz_fence_gate", () -> new NiftyFenceGate(QUARTZ), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> bone_fence_gate = registerBlockGeneral("bone_fence_gate", () -> new NiftyFenceGate(BONE), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> nether_brick_fence_gate = registerBlockGeneral("nether_brick_fence_gate", () -> new NiftyFenceGate(NETHER_BRICK), ItemGroup.REDSTONE, false);
    public static final RegistryObject<Block> red_nether_brick_fence_gate = registerBlockGeneral("red_nether_brick_fence_gate", () -> new NiftyFenceGate(NETHER_BRICK), ItemGroup.REDSTONE, false);

    public static final RegistryObject<Block> gold_door = registerBlockDoor("gold_door", () -> new NiftyDoor(GOLD), 0, false);
    public static final RegistryObject<Block> diamond_door = registerBlockDoor("diamond_door", () -> new NiftyDoor(DIAMOND), 0, false);
    public static final RegistryObject<Block> emerald_door = registerBlockDoor("emerald_door", () -> new NiftyDoor(EMERALD), 0, false);
    public static final RegistryObject<Block> lapis_door = registerBlockDoor("lapis_door", () -> new NiftyDoor(LAPIS), 0, false);
    public static final RegistryObject<Block> obsidian_door = registerBlockDoor("obsidian_door", () -> new NiftyDoor(OBSIDIAN), 0, false);
    public static final RegistryObject<Block> coal_door = registerBlockDoor("coal_door", () -> new NiftyDoor(COAL), 10500, false);
    public static final RegistryObject<Block> redstone_door = registerBlockDoor("redstone_door", NiftyRedstoneDoor::new, 0, false);
    public static final RegistryObject<Block> missingno_door = registerBlockDoor("missingno_door", () -> new NiftyDoor(MISSINGNO), 0, true);
    public static final RegistryObject<Block> clay_door = registerBlockDoor("clay_door", () -> new NiftyDoor(CLAY), 0, false);
    public static final RegistryObject<Block> dirt_door = registerBlockDoor("dirt_door", NiftyDirtDoor::new, 0, false);
    public static final RegistryObject<Block> grass_door = registerBlockDoor("grass_door", NiftyGrassDoor::new, 0, false);
    public static final RegistryObject<Block> hay_door = registerBlockDoor("hay_door", () -> new NiftyDoor(HAY), 0, false);
    public static final RegistryObject<Block> path_door = registerBlockDoor("grass_path_door", NiftyPathDoor::new, 0, false);
    public static final RegistryObject<Block> brick_door = registerBlockDoor("brick_door", () -> new NiftyDoor(BRICK), 0, false);
    public static final RegistryObject<Block> quartz_door = registerBlockDoor("quartz_door", () -> new NiftyDoor(QUARTZ), 0, false);
    public static final RegistryObject<Block> bone_door = registerBlockDoor("bone_door", () -> new NiftyDoor(BONE), 0, false);
    public static final RegistryObject<Block> nether_brick_door = registerBlockDoor("nether_brick_door", () -> new NiftyDoor(NETHER_BRICK), 0, false);
    public static final RegistryObject<Block> red_nether_brick_door = registerBlockDoor("red_nether_brick_door", () -> new NiftyDoor(NETHER_BRICK), 0, false);


    private static <T extends Block> RegistryObject<T> registerBlockGeneral(String name, Supplier<? extends T> block, ItemGroup group, boolean isOptional) {
        return registerBlock(name, block, 0, item -> registerBlockItem(item, group, isOptional));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithFuel(String name, Supplier<? extends T> block, ItemGroup group, int burnTime) {
        return registerBlock(name, block, burnTime, item -> registerBlockItemFuel(item, group, burnTime));
    }

    private static <T extends Block> RegistryObject<T> registerBlockDoor(String name, Supplier<? extends T> block, int burnTime, boolean isOptional) {
        return registerBlock(name, block, burnTime, item -> registerBlockItemDoor(item, burnTime, isOptional));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, int burnTime, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group, boolean isOptional) {
        return () -> new NiftyBlockItem(block.get(), new Item.Properties().group(group), isOptional);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final RegistryObject<T> block, ItemGroup group, int burnTime) {
        return () -> new NiftyBlockItem(block.get(), new Item.Properties().group(group), false) {
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return burnTime;
            }
        };
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, int burnTime, boolean isOptional) {
        return () -> new NiftyTallBlockItem(block.get(), new Item.Properties().group(ItemGroup.REDSTONE), burnTime, isOptional);
    }
}