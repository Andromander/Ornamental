package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.androsa.nifty.NiftyBlock.*;

@ObjectHolder(NiftyMod.MODID)
@Mod.EventBusSubscriber(modid = NiftyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    /** Blockstates */
    private static Block iron =      new Block(Block.Properties.create(Material.IRON, MaterialColor.IRON));
    private static Block gold =      new Block(Block.Properties.create(Material.IRON, MaterialColor.GOLD));
    private static Block diamond =   new Block(Block.Properties.create(Material.IRON, MaterialColor.DIAMOND));
    private static Block emerald =   new Block(Block.Properties.create(Material.IRON, MaterialColor.EMERALD));
    private static Block lapis =     new Block(Block.Properties.create(Material.IRON, MaterialColor.LAPIS));
    private static Block obsidian =  new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLACK));
    private static Block coal =      new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLACK));
    private static Block redstone =  new Block(Block.Properties.create(Material.IRON, MaterialColor.TNT));
    private static Block missingno = new Block(Block.Properties.create(Material.IRON, MaterialColor.MAGENTA));
    private static Block clay =      new Block(Block.Properties.create(Material.CLAY, MaterialColor.CLAY));
    private static Block dirt =      new Block(Block.Properties.create(Material.GROUND, MaterialColor.DIRT));
    private static Block grass =     new Block(Block.Properties.create(Material.GRASS, MaterialColor.GRASS));

    public static final Block iron_stairs      = new NiftyStairs(iron.getDefaultState(), IRON, false).setRegistryName("iron_stairs");
    public static final Block gold_stairs      = new NiftyStairs(gold.getDefaultState(), GOLD, true).setRegistryName("gold_stairs");
    public static final Block diamond_stairs   = new NiftyStairs(diamond.getDefaultState(), DIAMOND, true).setRegistryName("diamond_stairs");
    public static final Block emerald_stairs   = new NiftyStairs(emerald.getDefaultState(), EMERALD, true).setRegistryName("emerald_stairs");
    public static final Block lapis_stairs     = new NiftyStairs(lapis.getDefaultState(), LAPIS, false).setRegistryName("lapis_stairs");
    public static final Block obsidian_stairs  = new NiftyStairs(obsidian.getDefaultState(), OBSIDIAN, false).setRegistryName("obsidian_stairs");
    public static final Block coal_stairs      = new NiftyStairs(coal.getDefaultState(), COAL, false).setRegistryName("coal_stairs");
    public static final Block redstone_stairs  = new NiftyRedstoneStairs(redstone.getDefaultState()).setRegistryName("redstone_stairs");
    public static final Block missingno_stairs = new NiftyStairs(missingno.getDefaultState(), MISSINGNO, false).setRegistryName("missingno_stairs");
    public static final Block clay_stairs      = new NiftyStairs(clay.getDefaultState(), CLAY, false).setRegistryName("clay_stairs");
    public static final Block dirt_stairs      = new NiftyDirtStairs(dirt.getDefaultState()).setRegistryName("dirt_stairs");
    public static final Block grass_stairs     = new NiftyGrassStairs(grass.getDefaultState()).setRegistryName("grass_stairs");

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

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> e) {
        final Block blocks[] = new Block[] {
                //Stairs
                iron_stairs, gold_stairs, diamond_stairs, emerald_stairs, lapis_stairs, obsidian_stairs, coal_stairs, redstone_stairs, missingno_stairs, clay_stairs, dirt_stairs, grass_stairs,
                //Slabs
                iron_slab, gold_slab, diamond_slab, emerald_slab, lapis_slab, obsidian_slab, coal_slab, redstone_slab, missingno_slab, clay_slab, dirt_slab, grass_slab,
                //Fences
                iron_fence, gold_fence, diamond_fence, emerald_fence, lapis_fence, obsidian_fence, coal_fence, redstone_fence, missingno_fence, clay_fence, dirt_fence, grass_fence,
                //Trapdoors
                gold_trapdoor, diamond_trapdoor, emerald_trapdoor, lapis_trapdoor, obsidian_trapdoor, coal_trapdoor, redstone_trapdoor, missingno_trapdoor, clay_trapdoor, dirt_trapdoor, grass_trapdoor,
                //Fence Gates
                iron_fence_gate, gold_fence_gate, diamond_fence_gate, emerald_fence_gate, lapis_fence_gate, obsidian_fence_gate, coal_fence_gate, redstone_fence_gate, missingno_fence_gate, clay_fence_gate, dirt_fence_gate, grass_fence_gate
        };

        e.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e) {
        final IForgeRegistry<Item> registry = e.getRegistry();

        registry.register(new ItemBlock(iron_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("iron_stairs"));
        registry.register(new ItemBlock(gold_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("gold_stairs"));
        registry.register(new ItemBlock(diamond_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("diamond_stairs"));
        registry.register(new ItemBlock(emerald_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("emerald_stairs"));
        registry.register(new ItemBlock(lapis_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("lapis_stairs"));
        registry.register(new ItemBlock(obsidian_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("obsidian_stairs"));
        registry.register(new ItemBlock(coal_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack stack) {
                return 12000;
            }
        }.setRegistryName("coal_stairs"));
        registry.register(new ItemBlock(redstone_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("redstone_stairs"));
        registry.register(new ItemBlock(missingno_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("missingno_stairs"));
        registry.register(new ItemBlock(clay_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("clay_stairs"));
        registry.register(new ItemBlock(dirt_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("dirt_stairs"));
        registry.register(new ItemBlock(grass_stairs, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("grass_stairs"));

        registry.register(new ItemBlock(iron_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("iron_slab"));
        registry.register(new ItemBlock(gold_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("gold_slab"));
        registry.register(new ItemBlock(diamond_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("diamond_slab"));
        registry.register(new ItemBlock(emerald_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("emerald_slab"));
        registry.register(new ItemBlock(lapis_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("lapis_slab"));
        registry.register(new ItemBlock(obsidian_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("obsidian_slab"));
        registry.register(new ItemBlock(coal_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack stack) {
                return 8000;
            }
        }.setRegistryName("coal_slab"));
        registry.register(new ItemBlock(redstone_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("redstone_slab"));
        registry.register(new ItemBlock(missingno_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("missingno_slab"));
        registry.register(new ItemBlock(clay_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("clay_slab"));
        registry.register(new ItemBlock(dirt_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("dirt_slab"));
        registry.register(new ItemBlock(grass_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("grass_slab"));

        registry.register(new ItemBlock(iron_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("iron_fence"));
        registry.register(new ItemBlock(gold_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("gold_fence"));
        registry.register(new ItemBlock(diamond_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("diamond_fence"));
        registry.register(new ItemBlock(emerald_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("emerald_fence"));
        registry.register(new ItemBlock(lapis_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("lapis_fence"));
        registry.register(new ItemBlock(obsidian_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("obsidian_fence"));
        registry.register(new ItemBlock(coal_fence, new Item.Properties().group(ItemGroup.DECORATIONS)) {
            @Override
            public int getBurnTime(ItemStack stack) {
                return 5250;
            }
        }.setRegistryName("coal_fence"));
        registry.register(new ItemBlock(redstone_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("redstone_fence"));
        registry.register(new ItemBlock(missingno_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("missingno_fence"));
        registry.register(new ItemBlock(clay_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("clay_fence"));
        registry.register(new ItemBlock(dirt_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("dirt_fence"));
        registry.register(new ItemBlock(grass_fence, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("grass_fence"));

        registry.register(new ItemBlock(gold_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("gold_trapdoor"));
        registry.register(new ItemBlock(diamond_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("diamond_trapdoor"));
        registry.register(new ItemBlock(emerald_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("emerald_trapdoor"));
        registry.register(new ItemBlock(lapis_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("lapis_trapdoor"));
        registry.register(new ItemBlock(obsidian_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("obsidian_trapdoor"));
        registry.register(new ItemBlock(coal_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)) {
            @Override
            public int getBurnTime(ItemStack stack) {
                return 5250;
            }
        }.setRegistryName("coal_trapdoor"));
        registry.register(new ItemBlock(redstone_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("redstone_trapdoor"));
        registry.register(new ItemBlock(missingno_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("missingno_trapdoor"));
        registry.register(new ItemBlock(clay_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("clay_trapdoor"));
        registry.register(new ItemBlock(dirt_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("dirt_trapdoor"));
        registry.register(new ItemBlock(grass_trapdoor, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("grass_trapdoor"));

        registry.register(new ItemBlock(iron_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("iron_fence_gate"));
        registry.register(new ItemBlock(gold_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("gold_fence_gate"));
        registry.register(new ItemBlock(diamond_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("diamond_fence_gate"));
        registry.register(new ItemBlock(emerald_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("emerald_fence_gate"));
        registry.register(new ItemBlock(lapis_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("lapis_fence_gate"));
        registry.register(new ItemBlock(obsidian_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("obsidian_fence_gate"));
        registry.register(new ItemBlock(coal_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)) {
            @Override
            public int getBurnTime(ItemStack stack) {
                return 4000;
            }
        }.setRegistryName("coal_fence_gate"));
        registry.register(new ItemBlock(redstone_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("redstone_fence_gate"));
        registry.register(new ItemBlock(missingno_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("missingno_fence_gate"));
        registry.register(new ItemBlock(clay_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("clay_fence_gate"));
        registry.register(new ItemBlock(dirt_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("dirt_fence_gate"));
        registry.register(new ItemBlock(grass_fence_gate, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("grass_fence_gate"));
    }
}
