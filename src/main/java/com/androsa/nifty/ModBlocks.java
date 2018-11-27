package com.androsa.nifty;

import com.androsa.nifty.blocks.NiftyFence;
import com.androsa.nifty.blocks.NiftySlab;
import com.androsa.nifty.blocks.NiftyStairs;
import com.androsa.nifty.compat.NiftyCompat;
import com.androsa.nifty.util.BlockModelHelper;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(NiftyMod.MODID)
@Mod.EventBusSubscriber(modid = NiftyMod.MODID)
public class ModBlocks {
    public static final BlockStairs iron_stairs = null;
    public static final BlockStairs gold_stairs = null;
    public static final BlockStairs diamond_stairs = null;
    public static final BlockStairs emerald_stairs = null;
    public static final BlockStairs lapis_stairs = null;
    public static final BlockStairs obsidian_stairs = null;
    public static final BlockSlab iron_slab = null;
    public static final BlockSlab gold_slab = null;
    public static final BlockSlab diamond_slab = null;
    public static final BlockSlab emerald_slab = null;
    public static final BlockSlab lapis_slab = null;
    public static final BlockSlab obsidian_slab = null;
    public static final BlockSlab double_iron_slab = null;
    public static final BlockSlab double_gold_slab = null;
    public static final BlockSlab double_diamond_slab = null;
    public static final BlockSlab double_emerald_slab = null;
    public static final BlockSlab double_lapis_slab = null;
    public static final BlockSlab double_obsidian_slab = null;
    public static final BlockFence iron_fence = null;
    public static final BlockFence gold_fence = null;
    public static final BlockFence diamond_fence = null;
    public static final BlockFence emerald_fence = null;
    public static final BlockFence lapis_fence = null;
    public static final BlockFence obsidian_fence = null;

    /**
     * MOD COMPAT BELOW HERE.
     * Note that all blocks are registered, but WILL NOT show unless mod is loaded
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        BlockRegistryHelper blocks = new BlockRegistryHelper(e.getRegistry());

        Block iron =     new Block(Material.IRON, MapColor.IRON);
        Block gold =     new Block(Material.IRON, MapColor.GOLD);
        Block diamond =  new Block(Material.IRON, MapColor.DIAMOND);
        Block emerald =  new Block(Material.IRON, MapColor.EMERALD);
        Block lapis =    new Block(Material.IRON, MapColor.LAPIS);
        Block obsidian = new Block(Material.ROCK, MapColor.BLACK);

        blocks.registerBlock("iron_stairs",     new NiftyStairs(iron.getDefaultState(), SoundType.METAL, 5.0F, 10.0F, 1, true));
        blocks.registerBlock("gold_stairs",     new NiftyStairs(gold.getDefaultState(), SoundType.METAL, 3.0F, 10.0F, 2, true));
        blocks.registerBlock("diamond_stairs",  new NiftyStairs(diamond.getDefaultState(), SoundType.METAL, 5.0F, 10.0F, 2, true));
        blocks.registerBlock("emerald_stairs",  new NiftyStairs(emerald.getDefaultState(), SoundType.METAL, 5.0F, 10.0F, 2, true));
        blocks.registerBlock("lapis_stairs",    new NiftyStairs(lapis.getDefaultState(), SoundType.STONE, 3.0F, 5.0F, 1, false));
        blocks.registerBlock("obsidian_stairs", new NiftyStairs(obsidian.getDefaultState(), SoundType.STONE, 50.0F, 2000.0F, 3, false));
        blocks.registerBlock("iron_slab",     new NiftySlab(false, Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F, 10.0F, 1));
        blocks.registerBlock("gold_slab",     new NiftySlab(false, Material.IRON, MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("diamond_slab",  new NiftySlab(false, Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("emerald_slab",  new NiftySlab(false, Material.IRON, MapColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("lapis_slab",    new NiftySlab(false, Material.IRON, MapColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, 1));
        blocks.registerBlock("obsidian_slab", new NiftySlab(false, Material.ROCK, MapColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, 3));
        blocks.registerBlock("double_iron_slab",     new NiftySlab(true, Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F, 10.0F, 1));
        blocks.registerBlock("double_gold_slab",     new NiftySlab(true, Material.IRON, MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("double_diamond_slab",  new NiftySlab(true, Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("double_emerald_slab",  new NiftySlab(true, Material.IRON, MapColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("double_lapis_slab",    new NiftySlab(true, Material.IRON, MapColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, 1));
        blocks.registerBlock("double_obsidian_slab", new NiftySlab(true, Material.ROCK, MapColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, 3));
        blocks.registerBlock("iron_fence",     new NiftyFence(Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F, 10.0F, 1));
        blocks.registerBlock("gold_fence",     new NiftyFence(Material.IRON, MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("diamond_fence",  new NiftyFence(Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("emerald_fence",  new NiftyFence(Material.IRON, MapColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, 2));
        blocks.registerBlock("lapis_fence",    new NiftyFence(Material.IRON, MapColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, 1));
        blocks.registerBlock("obsidian_fence", new NiftyFence(Material.ROCK, MapColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, 3));

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
        items.register(new ItemSlab(iron_slab, iron_slab, double_iron_slab));
        items.register(new ItemSlab(gold_slab, gold_slab, double_gold_slab));
        items.register(new ItemSlab(diamond_slab, diamond_slab, double_diamond_slab));
        items.register(new ItemSlab(emerald_slab, emerald_slab, double_emerald_slab));
        items.register(new ItemSlab(lapis_slab, lapis_slab, double_lapis_slab));
        items.register(new ItemSlab(obsidian_slab, obsidian_slab, double_obsidian_slab));
        items.registerBlock(iron_fence);
        items.registerBlock(gold_fence);
        items.registerBlock(diamond_fence);
        items.registerBlock(emerald_fence);
        items.registerBlock(lapis_fence);
        items.registerBlock(obsidian_fence);

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
