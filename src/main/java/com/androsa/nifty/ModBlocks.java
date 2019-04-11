package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import com.androsa.nifty.compat.NiftyCompat;
import com.androsa.nifty.util.BlockModelHelper;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static com.androsa.nifty.NiftyBlock.*;

@GameRegistry.ObjectHolder(NiftyMod.MODID)
@Mod.EventBusSubscriber(modid = NiftyMod.MODID)
public class ModBlocks {
    public static final BlockStairs iron_stairs = null;
    public static final BlockStairs gold_stairs = null;
    public static final BlockStairs diamond_stairs = null;
    public static final BlockStairs emerald_stairs = null;
    public static final BlockStairs lapis_stairs = null;
    public static final BlockStairs obsidian_stairs = null;
    public static final BlockStairs coal_stairs = null;
    public static final BlockStairs redstone_stairs = null;
    public static final BlockStairs missingno_stairs = null;
    public static final BlockStairs dirt_stairs = null;
    public static final BlockStairs grass_stairs = null;
    public static final BlockStairs clay_stairs = null;
    public static final BlockStairs hay_stairs = null;
    public static final BlockSlab iron_slab = null;
    public static final BlockSlab gold_slab = null;
    public static final BlockSlab diamond_slab = null;
    public static final BlockSlab emerald_slab = null;
    public static final BlockSlab lapis_slab = null;
    public static final BlockSlab obsidian_slab = null;
    public static final BlockSlab coal_slab = null;
    public static final BlockSlab redstone_slab = null;
    public static final BlockSlab missingno_slab = null;
    public static final BlockSlab clay_slab = null;
    public static final BlockSlab dirt_slab = null;
    public static final BlockSlab grass_slab = null;
    public static final BlockSlab hay_slab = null;
    public static final BlockSlab double_iron_slab = null;
    public static final BlockSlab double_gold_slab = null;
    public static final BlockSlab double_diamond_slab = null;
    public static final BlockSlab double_emerald_slab = null;
    public static final BlockSlab double_lapis_slab = null;
    public static final BlockSlab double_obsidian_slab = null;
    public static final BlockSlab double_coal_slab = null;
    public static final BlockSlab double_redstone_slab = null;
    public static final BlockSlab double_missingno_slab = null;
    public static final BlockSlab double_clay_slab = null;
    public static final BlockSlab double_dirt_slab = null;
    public static final BlockSlab double_grass_slab = null;
    public static final BlockSlab double_hay_slab = null;
    public static final BlockFence iron_fence = null;
    public static final BlockFence gold_fence = null;
    public static final BlockFence diamond_fence = null;
    public static final BlockFence emerald_fence = null;
    public static final BlockFence lapis_fence = null;
    public static final BlockFence obsidian_fence = null;
    public static final BlockFence coal_fence = null;
    public static final BlockFence redstone_fence = null;
    public static final BlockFence missingno_fence = null;
    public static final BlockFence clay_fence = null;
    public static final BlockFence dirt_fence = null;
    public static final BlockFence grass_fence = null;
    public static final BlockFence hay_fence = null;
    public static final BlockTrapDoor gold_trapdoor = null;
    public static final BlockTrapDoor diamond_trapdoor = null;
    public static final BlockTrapDoor emerald_trapdoor = null;
    public static final BlockTrapDoor lapis_trapdoor = null;
    public static final BlockTrapDoor obsidian_trapdoor = null;
    public static final BlockTrapDoor coal_trapdoor = null;
    public static final BlockTrapDoor redstone_trapdoor = null;
    public static final BlockTrapDoor missingno_trapdoor = null;
    public static final BlockTrapDoor clay_trapdoor = null;
    public static final BlockTrapDoor dirt_trapdoor = null;
    public static final BlockTrapDoor grass_trapdoor = null;
    public static final BlockTrapDoor hay_trapdoor = null;
    public static final BlockFenceGate iron_fence_gate = null;
    public static final BlockFenceGate gold_fence_gate = null;
    public static final BlockFenceGate diamond_fence_gate = null;
    public static final BlockFenceGate emerald_fence_gate = null;
    public static final BlockFenceGate lapis_fence_gate = null;
    public static final BlockFenceGate obsidian_fence_gate = null;
    public static final BlockFenceGate coal_fence_gate = null;
    public static final BlockFenceGate redstone_fence_gate = null;
    public static final BlockFenceGate missingno_fence_gate = null;
    public static final BlockFenceGate clay_fence_gate = null;
    public static final BlockFenceGate dirt_fence_gate = null;
    public static final BlockFenceGate grass_fence_gate = null;
    public static final BlockFenceGate hay_fence_gate = null;

    /**
     * MOD COMPAT BELOW HERE.
     * Note that all blocks are registered, but WILL NOT show unless and until mod is loaded
     * */
    /*TWILIGHT FOREST*/
    public static final BlockStairs ironwood_stairs = null;
    public static final BlockStairs fiery_stairs = null;
    public static final BlockStairs steeleaf_stairs = null;
    public static final BlockStairs arctic_fur_stairs = null;
    public static final BlockStairs carminite_stairs = null;
    public static final BlockSlab ironwood_slab = null;
    public static final BlockSlab fiery_slab = null;
    public static final BlockSlab steeleaf_slab = null;
    public static final BlockSlab arctic_fur_slab = null;
    public static final BlockSlab carminite_slab = null;
    public static final BlockSlab double_ironwood_slab = null;
    public static final BlockSlab double_fiery_slab = null;
    public static final BlockSlab double_steeleaf_slab = null;
    public static final BlockSlab double_arctic_fur_slab = null;
    public static final BlockSlab double_carminite_slab = null;
    public static final BlockFence ironwood_fence = null;
    public static final BlockFence fiery_fence = null;
    public static final BlockFence steeleaf_fence = null;
    public static final BlockFence arctic_fur_fence = null;
    public static final BlockFence carminite_fence = null;
    public static final BlockTrapDoor ironwood_trapdoor = null;
    public static final BlockTrapDoor fiery_trapdoor = null;
    public static final BlockTrapDoor steeleaf_trapdoor = null;
    public static final BlockTrapDoor arctic_fur_trapdoor = null;
    public static final BlockTrapDoor carminite_trapdoor = null;
    public static final BlockFenceGate ironwood_fence_gate = null;
    public static final BlockFenceGate fiery_fence_gate = null;
    public static final BlockFenceGate steeleaf_fence_gate = null;
    public static final BlockFenceGate arctic_fur_fence_gate = null;
    public static final BlockFenceGate carminite_fence_gate = null;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        BlockRegistryHelper blocks = new BlockRegistryHelper(e.getRegistry());

        Block iron =      new Block(Material.IRON, MapColor.IRON);
        Block gold =      new Block(Material.IRON, MapColor.GOLD);
        Block diamond =   new Block(Material.IRON, MapColor.DIAMOND);
        Block emerald =   new Block(Material.IRON, MapColor.EMERALD);
        Block lapis =     new Block(Material.IRON, MapColor.LAPIS);
        Block obsidian =  new Block(Material.ROCK, MapColor.BLACK);
        Block coal =      new Block(Material.ROCK, MapColor.BLACK);
        Block redstone =  new Block(Material.IRON, MapColor.TNT);
        Block missingno = new Block(Material.IRON, MapColor.MAGENTA);
        Block clay =      new Block(Material.CLAY, MapColor.CLAY);
        Block dirt =      new Block(Material.GROUND, MapColor.DIRT);
        Block grass =     new Block(Material.GRASS, MapColor.GRASS);
        Block hay =       new Block(Material.GRASS, MapColor.YELLOW);

        blocks.registerBlock("iron_stairs",      new NiftyStairs(iron.getDefaultState(), IRON, true));
        blocks.registerBlock("gold_stairs",      new NiftyStairs(gold.getDefaultState(), GOLD, true));
        blocks.registerBlock("diamond_stairs",   new NiftyStairs(diamond.getDefaultState(), DIAMOND, true));
        blocks.registerBlock("emerald_stairs",   new NiftyStairs(emerald.getDefaultState(), EMERALD, true));
        blocks.registerBlock("lapis_stairs",     new NiftyStairs(lapis.getDefaultState(), LAPIS, false));
        blocks.registerBlock("obsidian_stairs",  new NiftyStairs(obsidian.getDefaultState(), OBSIDIAN, false));
        blocks.registerBlock("coal_stairs",      new NiftyStairs(coal.getDefaultState(), COAL, false));
        blocks.registerBlock("redstone_stairs",  new NiftyRedstoneStairs(redstone.getDefaultState()));
        blocks.registerBlock("missingno_stairs", new NiftyStairs(missingno.getDefaultState(), MISSINGNO, false));
        blocks.registerBlock("clay_stairs",      new NiftyStairs(clay.getDefaultState(), CLAY, false));
        blocks.registerBlock("dirt_stairs",      new NiftyDirtStairs(dirt.getDefaultState()));
        blocks.registerBlock("grass_stairs",     new NiftyGrassStairs(grass.getDefaultState()));
        blocks.registerBlock("hay_stairs",       new NiftyStairs(hay.getDefaultState(), HAY, false));
        blocks.registerBlock("iron_slab",      new NiftySlab(false, IRON));
        blocks.registerBlock("gold_slab",      new NiftySlab(false, GOLD));
        blocks.registerBlock("diamond_slab",   new NiftySlab(false, DIAMOND));
        blocks.registerBlock("emerald_slab",   new NiftySlab(false, EMERALD));
        blocks.registerBlock("lapis_slab",     new NiftySlab(false, LAPIS));
        blocks.registerBlock("obsidian_slab",  new NiftySlab(false, OBSIDIAN));
        blocks.registerBlock("coal_slab",      new NiftySlab(false, COAL));
        blocks.registerBlock("redstone_slab",  new NiftyRedstoneSlab(false));
        blocks.registerBlock("missingno_slab", new NiftySlab(false, MISSINGNO));
        blocks.registerBlock("clay_slab",      new NiftySlab(false, CLAY));
        blocks.registerBlock("dirt_slab",      new NiftyDirtSlab(false));
        blocks.registerBlock("grass_slab",     new NiftyGrassSlab(false));
        blocks.registerBlock("hay_slab",       new NiftySlab(false, HAY));
        blocks.registerBlock("double_iron_slab",      new NiftySlab(true, IRON));
        blocks.registerBlock("double_gold_slab",      new NiftySlab(true, GOLD));
        blocks.registerBlock("double_diamond_slab",   new NiftySlab(true, DIAMOND));
        blocks.registerBlock("double_emerald_slab",   new NiftySlab(true, EMERALD));
        blocks.registerBlock("double_lapis_slab",     new NiftySlab(true, LAPIS));
        blocks.registerBlock("double_obsidian_slab",  new NiftySlab(true, OBSIDIAN));
        blocks.registerBlock("double_coal_slab",      new NiftySlab(true, COAL));
        blocks.registerBlock("double_redstone_slab",  new NiftyRedstoneSlab(true));
        blocks.registerBlock("double_missingno_slab", new NiftySlab(true, MISSINGNO));
        blocks.registerBlock("double_clay_slab",      new NiftySlab(true, CLAY));
        blocks.registerBlock("double_dirt_slab",      new NiftyDirtSlab(true));
        blocks.registerBlock("double_grass_slab",     new NiftyGrassSlab(true));
        blocks.registerBlock("double_hay_slab",       new NiftySlab(true, HAY));
        blocks.registerBlock("iron_fence",      new NiftyFence(IRON));
        blocks.registerBlock("gold_fence",      new NiftyFence(GOLD));
        blocks.registerBlock("diamond_fence",   new NiftyFence(DIAMOND));
        blocks.registerBlock("emerald_fence",   new NiftyFence(EMERALD));
        blocks.registerBlock("lapis_fence",     new NiftyFence(LAPIS));
        blocks.registerBlock("obsidian_fence",  new NiftyFence(OBSIDIAN));
        blocks.registerBlock("coal_fence",      new NiftyFence(COAL));
        blocks.registerBlock("redstone_fence",  new NiftyRedstoneFence());
        blocks.registerBlock("missingno_fence", new NiftyFence(MISSINGNO));
        blocks.registerBlock("clay_fence",      new NiftyFence(CLAY));
        blocks.registerBlock("dirt_fence",      new NiftyDirtFence());
        blocks.registerBlock("grass_fence",     new NiftyGrassFence());
        blocks.registerBlock("hay_fence",       new NiftyFence(HAY));
        blocks.registerBlock("gold_trapdoor",      new NiftyTrapDoor(GOLD));
        blocks.registerBlock("diamond_trapdoor",   new NiftyTrapDoor(DIAMOND));
        blocks.registerBlock("emerald_trapdoor",   new NiftyTrapDoor(EMERALD));
        blocks.registerBlock("lapis_trapdoor",     new NiftyTrapDoor(LAPIS));
        blocks.registerBlock("obsidian_trapdoor",  new NiftyTrapDoor(OBSIDIAN));
        blocks.registerBlock("coal_trapdoor",      new NiftyTrapDoor(COAL));
        blocks.registerBlock("redstone_trapdoor",  new NiftyRedstoneTrapDoor());
        blocks.registerBlock("missingno_trapdoor", new NiftyTrapDoor(MISSINGNO));
        blocks.registerBlock("clay_trapdoor",      new NiftyTrapDoor(CLAY));
        blocks.registerBlock("dirt_trapdoor",      new NiftyDirtTrapDoor());
        blocks.registerBlock("grass_trapdoor",     new NiftyGrassTrapDoor());
        blocks.registerBlock("hay_trapdoor",       new NiftyTrapDoor(HAY));
        blocks.registerBlock("iron_fence_gate",      new NiftyFenceGate(iron::getDefaultState, IRON));
        blocks.registerBlock("gold_fence_gate",      new NiftyFenceGate(gold::getDefaultState, GOLD));
        blocks.registerBlock("diamond_fence_gate",   new NiftyFenceGate(diamond::getDefaultState, DIAMOND));
        blocks.registerBlock("emerald_fence_gate",   new NiftyFenceGate(emerald::getDefaultState, EMERALD));
        blocks.registerBlock("lapis_fence_gate",     new NiftyFenceGate(lapis::getDefaultState, LAPIS));
        blocks.registerBlock("obsidian_fence_gate",  new NiftyFenceGate(obsidian::getDefaultState, OBSIDIAN));
        blocks.registerBlock("coal_fence_gate",      new NiftyFenceGate(coal::getDefaultState, COAL));
        blocks.registerBlock("redstone_fence_gate",  new NiftyRedstoneFenceGate(redstone::getDefaultState));
        blocks.registerBlock("missingno_fence_gate", new NiftyFenceGate(missingno::getDefaultState, MISSINGNO));
        blocks.registerBlock("clay_fence_gate",      new NiftyFenceGate(clay::getDefaultState, CLAY));
        blocks.registerBlock("dirt_fence_gate",      new NiftyDirtFenceGate(dirt::getDefaultState));
        blocks.registerBlock("grass_fence_gate",     new NiftyGrassFenceGate(grass::getDefaultState));
        blocks.registerBlock("hay_fence_gate",       new NiftyFenceGate(hay::getDefaultState, HAY));

        NiftyCompat.initCompatBlocks(blocks);
    }

    public static List<BlockModelHelper> getBlockModels() {
        return ImmutableList.copyOf(BlockRegistryHelper.blockModels);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e) {
        ItemRegistryHelper items = new ItemRegistryHelper(e.getRegistry());

        items.registerBlock(iron_stairs);
        items.registerBlock(gold_stairs);
        items.registerBlock(diamond_stairs);
        items.registerBlock(emerald_stairs);
        items.registerBlock(lapis_stairs);
        items.registerBlock(obsidian_stairs);
        items.register(new ItemBlock(coal_stairs) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return 12000;
            }
        });
        items.registerBlock(redstone_stairs);
        items.registerBlock(missingno_stairs);
        items.registerBlock(clay_stairs);
        items.registerBlock(dirt_stairs);
        items.registerBlock(grass_stairs);
        items.registerBlock(hay_stairs);
        items.register(new ItemSlab(iron_slab, iron_slab, double_iron_slab));
        items.register(new ItemSlab(gold_slab, gold_slab, double_gold_slab));
        items.register(new ItemSlab(diamond_slab, diamond_slab, double_diamond_slab));
        items.register(new ItemSlab(emerald_slab, emerald_slab, double_emerald_slab));
        items.register(new ItemSlab(lapis_slab, lapis_slab, double_lapis_slab));
        items.register(new ItemSlab(obsidian_slab, obsidian_slab, double_obsidian_slab));
        items.register(new ItemSlab(coal_slab,coal_slab,double_coal_slab) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return 8000;
            }
        });
        items.register(new ItemSlab(redstone_slab,redstone_slab,double_redstone_slab));
        items.register(new ItemSlab(missingno_slab,missingno_slab,double_missingno_slab));
        items.register(new ItemSlab(clay_slab, clay_slab, double_clay_slab));
        items.register(new ItemSlab(dirt_slab, dirt_slab, double_dirt_slab));
        items.register(new ItemSlab(grass_slab, grass_slab, double_grass_slab));
        items.register(new ItemSlab(hay_slab, hay_slab, double_hay_slab));
        items.registerBlock(iron_fence);
        items.registerBlock(gold_fence);
        items.registerBlock(diamond_fence);
        items.registerBlock(emerald_fence);
        items.registerBlock(lapis_fence);
        items.registerBlock(obsidian_fence);
        items.register(new ItemBlock(coal_fence) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return 5250;
            }
        });
        items.registerBlock(redstone_fence);
        items.registerBlock(missingno_fence);
        items.registerBlock(clay_fence);
        items.registerBlock(dirt_fence);
        items.registerBlock(grass_fence);
        items.registerBlock(hay_fence);
        items.registerBlock(gold_trapdoor);
        items.registerBlock(diamond_trapdoor);
        items.registerBlock(emerald_trapdoor);
        items.registerBlock(lapis_trapdoor);
        items.registerBlock(obsidian_trapdoor);
        items.register(new ItemBlock(coal_trapdoor) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return 5250;
            }
        });
        items.registerBlock(redstone_trapdoor);
        items.registerBlock(missingno_trapdoor);
        items.registerBlock(clay_trapdoor);
        items.registerBlock(dirt_trapdoor);
        items.registerBlock(grass_trapdoor);
        items.registerBlock(hay_trapdoor);
        items.registerBlock(iron_fence_gate);
        items.registerBlock(gold_fence_gate);
        items.registerBlock(diamond_fence_gate);
        items.registerBlock(emerald_fence_gate);
        items.registerBlock(lapis_fence_gate);
        items.registerBlock(obsidian_fence_gate);
        items.register(new ItemBlock(coal_fence_gate) {
            @Override
            public int getItemBurnTime(ItemStack itemStack) {
                return 4000;
            }
        });
        items.registerBlock(redstone_fence_gate);
        items.registerBlock(missingno_fence_gate);
        items.registerBlock(clay_fence_gate);
        items.registerBlock(dirt_fence_gate);
        items.registerBlock(grass_fence_gate);
        items.registerBlock(hay_fence_gate);

        NiftyCompat.initCompatItems(items);
    }

    public static class BlockRegistryHelper {
        private final IForgeRegistry<Block> registry;

        private static List<BlockModelHelper> blockModels = new ArrayList<>();

        BlockRegistryHelper(IForgeRegistry<Block> registry) {
            this.registry = registry;
        }

        public void registerBlock(String name, Block block) {
            block.setRegistryName(NiftyMod.MODID, name);
            block.setTranslationKey(NiftyMod.MODID + "." + name);

            if (block instanceof BlockModelHelper) {
                blockModels.add((BlockModelHelper) block);
            }
            registry.register(block);
        }
    }

    public static class ItemRegistryHelper {
        private final IForgeRegistry<Item> registry;

        ItemRegistryHelper(IForgeRegistry<Item> registry) {
            this.registry = registry;
        }

        public void registerBlock(Block block) {
            ItemBlock metaItemBlock = new ItemBlock(block);
            register(metaItemBlock);
        }

        public void register(ItemBlock item) {
            item.setRegistryName(item.getBlock().getRegistryName());
            item.setTranslationKey(item.getBlock().getTranslationKey());
            registry.register(item);
        }
    }
}
