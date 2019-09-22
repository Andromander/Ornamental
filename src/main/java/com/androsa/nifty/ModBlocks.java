package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import com.androsa.nifty.items.NiftyBlockItem;
import com.androsa.nifty.items.NiftyTallBlockItem;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;

import static com.androsa.nifty.NiftyBlock.*;

@ObjectHolder(NiftyMod.MODID)
@Mod.EventBusSubscriber(modid = NiftyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("WeakerAccess")
public class ModBlocks {

    public static final Block iron_stairs      = new NiftyStairs(IRON, false).setRegistryName("iron_stairs");
    public static final Block gold_stairs      = new NiftyStairs(GOLD, true).setRegistryName("gold_stairs");
    public static final Block diamond_stairs   = new NiftyStairs(DIAMOND, true).setRegistryName("diamond_stairs");
    public static final Block emerald_stairs   = new NiftyStairs(EMERALD, true).setRegistryName("emerald_stairs");
    public static final Block lapis_stairs     = new NiftyStairs(LAPIS, false).setRegistryName("lapis_stairs");
    public static final Block obsidian_stairs  = new NiftyStairs(OBSIDIAN, false).setRegistryName("obsidian_stairs");
    public static final Block coal_stairs      = new NiftyStairs(COAL, false).setRegistryName("coal_stairs");
    public static final Block redstone_stairs  = new NiftyRedstoneStairs().setRegistryName("redstone_stairs");
    public static final Block missingno_stairs = new NiftyStairs(MISSINGNO, false).setRegistryName("missingno_stairs");
    public static final Block clay_stairs      = new NiftyStairs(CLAY, false).setRegistryName("clay_stairs");
    public static final Block dirt_stairs      = new NiftyDirtStairs().setRegistryName("dirt_stairs");
    public static final Block grass_stairs     = new NiftyGrassStairs().setRegistryName("grass_stairs");
    public static final Block hay_stairs       = new NiftyStairs(HAY, false).setRegistryName("hay_stairs");
    public static final Block path_stairs      = new NiftyPathStairs().setRegistryName("grass_path_stairs");

    public static final Block iron_slab      = new NiftySlab(IRON).setRegistryName("iron_slab");
    public static final Block gold_slab      = new NiftySlab(GOLD).setRegistryName("gold_slab");
    public static final Block diamond_slab   = new NiftySlab(DIAMOND).setRegistryName("diamond_slab");
    public static final Block emerald_slab   = new NiftySlab(EMERALD).setRegistryName("emerald_slab");
    public static final Block lapis_slab     = new NiftySlab(LAPIS).setRegistryName("lapis_slab");
    public static final Block obsidian_slab  = new NiftySlab(OBSIDIAN).setRegistryName("obsidian_slab");
    public static final Block coal_slab      = new NiftySlab(COAL).setRegistryName("coal_slab");
    public static final Block redstone_slab  = new NiftyRedstoneSlab().setRegistryName("redstone_slab");
    public static final Block missingno_slab = new NiftySlab(MISSINGNO).setRegistryName("missingno_slab");
    public static final Block clay_slab      = new NiftySlab(CLAY).setRegistryName("clay_slab");
    public static final Block dirt_slab      = new NiftyDirtSlab().setRegistryName("dirt_slab");
    public static final Block grass_slab     = new NiftyGrassSlab().setRegistryName("grass_slab");
    public static final Block hay_slab       = new NiftySlab(HAY).setRegistryName("hay_slab");
    public static final Block path_slab      = new NiftyPathSlab().setRegistryName("grass_path_slab");

    public static final Block iron_fence      = new NiftyFence(IRON).setRegistryName("iron_fence");
    public static final Block gold_fence      = new NiftyFence(GOLD).setRegistryName("gold_fence");
    public static final Block diamond_fence   = new NiftyFence(DIAMOND).setRegistryName("diamond_fence");
    public static final Block emerald_fence   = new NiftyFence(EMERALD).setRegistryName("emerald_fence");
    public static final Block lapis_fence     = new NiftyFence(LAPIS).setRegistryName("lapis_fence");
    public static final Block obsidian_fence  = new NiftyFence(OBSIDIAN).setRegistryName("obsidian_fence");
    public static final Block coal_fence      = new NiftyFence(COAL).setRegistryName("coal_fence");
    public static final Block redstone_fence  = new NiftyRedstoneFence().setRegistryName("redstone_fence");
    public static final Block missingno_fence = new NiftyFence(MISSINGNO).setRegistryName("missingno_fence");
    public static final Block clay_fence      = new NiftyFence(CLAY).setRegistryName("clay_fence");
    public static final Block dirt_fence      = new NiftyDirtFence().setRegistryName("dirt_fence");
    public static final Block grass_fence     = new NiftyGrassFence().setRegistryName("grass_fence");
    public static final Block hay_fence       = new NiftyFence(HAY).setRegistryName("hay_fence");
    public static final Block path_fence      = new NiftyPathFence().setRegistryName("grass_path_fence");

    public static final Block gold_trapdoor      = new NiftyTrapDoor(GOLD).setRegistryName("gold_trapdoor");
    public static final Block diamond_trapdoor   = new NiftyTrapDoor(DIAMOND).setRegistryName("diamond_trapdoor");
    public static final Block emerald_trapdoor   = new NiftyTrapDoor(EMERALD).setRegistryName("emerald_trapdoor");
    public static final Block lapis_trapdoor     = new NiftyTrapDoor(LAPIS).setRegistryName("lapis_trapdoor");
    public static final Block obsidian_trapdoor  = new NiftyTrapDoor(OBSIDIAN).setRegistryName("obsidian_trapdoor");
    public static final Block coal_trapdoor      = new NiftyTrapDoor(COAL).setRegistryName("coal_trapdoor");
    public static final Block redstone_trapdoor  = new NiftyRedstoneTrapDoor().setRegistryName("redstone_trapdoor");
    public static final Block missingno_trapdoor = new NiftyTrapDoor(MISSINGNO).setRegistryName("missingno_trapdoor");
    public static final Block clay_trapdoor      = new NiftyTrapDoor(CLAY).setRegistryName("clay_trapdoor");
    public static final Block dirt_trapdoor      = new NiftyDirtTrapDoor().setRegistryName("dirt_trapdoor");
    public static final Block grass_trapdoor     = new NiftyGrassTrapDoor().setRegistryName("grass_trapdoor");
    public static final Block hay_trapdoor       = new NiftyTrapDoor(HAY).setRegistryName("hay_trapdoor");
    public static final Block path_trapdoor      = new NiftyPathTrapDoor().setRegistryName("grass_path_trapdoor");

    public static final Block iron_fence_gate      = new NiftyFenceGate(IRON).setRegistryName("iron_fence_gate");
    public static final Block gold_fence_gate      = new NiftyFenceGate(GOLD).setRegistryName("gold_fence_gate");
    public static final Block diamond_fence_gate   = new NiftyFenceGate(DIAMOND).setRegistryName("diamond_fence_gate");
    public static final Block emerald_fence_gate   = new NiftyFenceGate(EMERALD).setRegistryName("emerald_fence_gate");
    public static final Block lapis_fence_gate     = new NiftyFenceGate(LAPIS).setRegistryName("lapis_fence_gate");
    public static final Block obsidian_fence_gate  = new NiftyFenceGate(OBSIDIAN).setRegistryName("obsidian_fence_gate");
    public static final Block coal_fence_gate      = new NiftyFenceGate(COAL).setRegistryName("coal_fence_gate");
    public static final Block redstone_fence_gate  = new NiftyRedstoneFenceGate().setRegistryName("redstone_fence_gate");
    public static final Block missingno_fence_gate = new NiftyFenceGate(MISSINGNO).setRegistryName("missingno_fence_gate");
    public static final Block clay_fence_gate      = new NiftyFenceGate(CLAY).setRegistryName("clay_fence_gate");
    public static final Block dirt_fence_gate      = new NiftyDirtFenceGate().setRegistryName("dirt_fence_gate");
    public static final Block grass_fence_gate     = new NiftyGrassFenceGate().setRegistryName("grass_fence_gate");
    public static final Block hay_fence_gate       = new NiftyFenceGate(HAY).setRegistryName("hay_fence_gate");
    public static final Block path_fence_gate      = new NiftyPathFenceGate().setRegistryName("grass_path_fence_gate");

    public static final Block gold_door      = new NiftyDoor(GOLD).setRegistryName("gold_door");
    public static final Block diamond_door   = new NiftyDoor(DIAMOND).setRegistryName("diamond_door");
    public static final Block emerald_door   = new NiftyDoor(EMERALD).setRegistryName("emerald_door");
    public static final Block lapis_door     = new NiftyDoor(LAPIS).setRegistryName("lapis_door");
    public static final Block obsidian_door  = new NiftyDoor(OBSIDIAN).setRegistryName("obsidian_door");
    public static final Block coal_door      = new NiftyDoor(COAL).setRegistryName("coal_door");
    public static final Block redstone_door  = new NiftyRedstoneDoor().setRegistryName("redstone_door");
    public static final Block missingno_door = new NiftyDoor(MISSINGNO).setRegistryName("missingno_door");
    public static final Block clay_door      = new NiftyDoor(CLAY).setRegistryName("clay_door");
    public static final Block dirt_door      = new NiftyDirtDoor().setRegistryName("dirt_door");
    public static final Block grass_door     = new NiftyGrassDoor().setRegistryName("grass_door");
    public static final Block hay_door       = new NiftyDoor(HAY).setRegistryName("hay_door");
    public static final Block path_door      = new NiftyPathDoor().setRegistryName("grass_path_door");

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> e) {
        final Block blocks[] = new Block[] {
                //Stairs
                iron_stairs, gold_stairs, diamond_stairs, emerald_stairs, lapis_stairs, obsidian_stairs, coal_stairs, redstone_stairs, missingno_stairs, clay_stairs, dirt_stairs, grass_stairs, hay_stairs, path_stairs,
                //Slabs
                iron_slab, gold_slab, diamond_slab, emerald_slab, lapis_slab, obsidian_slab, coal_slab, redstone_slab, missingno_slab, clay_slab, dirt_slab, grass_slab, hay_slab, path_slab,
                //Fences
                iron_fence, gold_fence, diamond_fence, emerald_fence, lapis_fence, obsidian_fence, coal_fence, redstone_fence, missingno_fence, clay_fence, dirt_fence, grass_fence, hay_fence, path_fence,
                //Trapdoors
                gold_trapdoor, diamond_trapdoor, emerald_trapdoor, lapis_trapdoor, obsidian_trapdoor, coal_trapdoor, redstone_trapdoor, missingno_trapdoor, clay_trapdoor, dirt_trapdoor, grass_trapdoor, hay_trapdoor, path_trapdoor,
                //Fence Gates
                iron_fence_gate, gold_fence_gate, diamond_fence_gate, emerald_fence_gate, lapis_fence_gate, obsidian_fence_gate, coal_fence_gate, redstone_fence_gate, missingno_fence_gate, clay_fence_gate, dirt_fence_gate, grass_fence_gate, hay_fence_gate, path_fence_gate,
                //Doors
                gold_door, diamond_door, emerald_door, lapis_door, obsidian_door, coal_door, redstone_door, missingno_door, clay_door, dirt_door, grass_door, hay_door, path_door
        };

        e.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e) {
        final IForgeRegistry<Item> registry = e.getRegistry();

        registry.register(registerBlock(iron_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(gold_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(diamond_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(emerald_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(lapis_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(obsidian_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(coal_stairs, ItemGroup.BUILDING_BLOCKS, 12000));
        registry.register(registerBlock(redstone_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(missingno_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(clay_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(dirt_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(grass_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(hay_stairs, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(path_stairs, ItemGroup.BUILDING_BLOCKS));

        registry.register(registerBlock(iron_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(gold_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(diamond_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(emerald_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(lapis_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(obsidian_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(coal_slab, ItemGroup.BUILDING_BLOCKS, 8000));
        registry.register(registerBlock(redstone_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(missingno_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(clay_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(dirt_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(grass_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(hay_slab, ItemGroup.BUILDING_BLOCKS));
        registry.register(registerBlock(path_slab, ItemGroup.BUILDING_BLOCKS));

        registry.register(registerBlock(iron_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(gold_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(diamond_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(emerald_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(lapis_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(obsidian_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(coal_fence, ItemGroup.DECORATIONS, 5250));
        registry.register(registerBlock(redstone_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(missingno_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(clay_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(dirt_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(grass_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(hay_fence, ItemGroup.DECORATIONS));
        registry.register(registerBlock(path_fence, ItemGroup.DECORATIONS));

        registry.register(registerBlock(gold_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(diamond_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(emerald_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(lapis_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(obsidian_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(coal_trapdoor, ItemGroup.REDSTONE, 5250));
        registry.register(registerBlock(redstone_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(missingno_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(clay_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(dirt_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(grass_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(hay_trapdoor, ItemGroup.REDSTONE));
        registry.register(registerBlock(path_trapdoor, ItemGroup.REDSTONE));

        registry.register(registerBlock(iron_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(gold_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(diamond_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(emerald_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(lapis_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(obsidian_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(coal_fence_gate, ItemGroup.REDSTONE, 4000));
        registry.register(registerBlock(redstone_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(missingno_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(clay_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(dirt_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(grass_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(hay_fence_gate, ItemGroup.REDSTONE));
        registry.register(registerBlock(path_fence_gate, ItemGroup.REDSTONE));

        registry.register(registerDoorBlock(gold_door, 0));
        registry.register(registerDoorBlock(diamond_door, 0));
        registry.register(registerDoorBlock(emerald_door, 0));
        registry.register(registerDoorBlock(lapis_door, 0));
        registry.register(registerDoorBlock(obsidian_door, 0));
        registry.register(registerDoorBlock(coal_door, 10500));
        registry.register(registerDoorBlock(redstone_door, 0));
        registry.register(registerDoorBlock(clay_door, 0));
        registry.register(registerDoorBlock(dirt_door, 0));
        registry.register(registerDoorBlock(grass_door, 0));
        registry.register(registerDoorBlock(hay_door, 0));
        registry.register(registerDoorBlock(path_door, 0));
    }

    private static Item registerDoorBlock(Block block, int burnTime) {
        if (burnTime == 0) {
            return new TallBlockItem(block, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(block.getRegistryName());
        } else {
            return new TallBlockItem(block, new Item.Properties().group(ItemGroup.REDSTONE)) {
                @Override
                public int getBurnTime(ItemStack stack) {
                    return burnTime;
                }
            }.setRegistryName(block.getRegistryName());
        }
    }
    }

    private static Item registerBlock(Block block, ItemGroup group) {
        return registerBlock(block, group, 0);
    }

    private static Item registerBlock(Block block, ItemGroup group, int burnTime) {
        if (burnTime == 0) {
            return new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName());
        } else {
            return new BlockItem(block, new Item.Properties().group(group)) {
                @Override
                public int getBurnTime(ItemStack stack) {
                    return burnTime;
                }
            }.setRegistryName(block.getRegistryName());
        }
    }
}
