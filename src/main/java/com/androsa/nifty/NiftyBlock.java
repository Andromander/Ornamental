package com.androsa.nifty;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public enum NiftyBlock {
    IRON(Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 1, 1.0F, () -> Item.getItemFromBlock(ModBlocks.iron_slab)),
    GOLD(Material.IRON, MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2, 1.0F, () -> Item.getItemFromBlock(ModBlocks.gold_slab)),
    DIAMOND(Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2, 1.0F, () -> Item.getItemFromBlock(ModBlocks.diamond_slab)),
    EMERALD(Material.IRON, MapColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2, 1.0F, () -> Item.getItemFromBlock(ModBlocks.emerald_slab)),
    LAPIS(Material.IRON, MapColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, "pickaxe", 1, 1.0F, () -> Item.getItemFromBlock(ModBlocks.lapis_slab)),
    OBSIDIAN(Material.ROCK, MapColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, "pickaxe", 3, 1.0F, () -> Item.getItemFromBlock(ModBlocks.obsidian_slab)),
    COAL(Material.ROCK, MapColor.BLACK, SoundType.STONE, 5.0F, 10.0F, "pickaxe", 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.coal_slab)),
    REDSTONE(Material.IRON, MapColor.TNT, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.redstone_slab)),
    MISSINGNO(Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2, 1.0F, () -> Item.getItemFromBlock(ModBlocks.missingno_slab)),
    CLAY(Material.GROUND, MapColor.CLAY, SoundType.GROUND, 0.6F, 0.6F, "shovel", 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.clay_slab)),
    DIRT(Material.GROUND, MapColor.DIRT, SoundType.GROUND, 0.5F, 0.0F, "shovel", 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.dirt_slab)),
    GRASS(Material.GRASS, MapColor.GRASS, SoundType.PLANT, 0.6F, 0.0F, "shovel", 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.grass_slab)),
    HAY(Material.GRASS, MapColor.YELLOW, SoundType.PLANT, 0.5F, 0.0F, null, 0, 0.2F, () -> Item.getItemFromBlock(ModBlocks.hay_slab)),

    IRONWOOD(Material.WOOD, MapColor.WOOD, SoundType.WOOD, 5.0F, 10.0F, null, 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.ironwood_slab)),
    FIERY(Material.IRON, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.METAL, 5.0F, 10.0F, null, 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.fiery_slab)),
    STEELEAF(Material.LEAVES, MapColor.FOLIAGE, SoundType.PLANT, 5.0F, 10.0F, null, 0, 0.75F, () -> Item.getItemFromBlock(ModBlocks.steeleaf_slab)),
    ARCTIC(Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F, 10.0F, null, 0, 0.1F, () -> Item.getItemFromBlock(ModBlocks.arctic_fur_slab)),
    CARMINITE(Material.CLAY, MapColor.RED, SoundType.SLIME, 0.0F, 10.0F, null, 0, 1.0F, () -> Item.getItemFromBlock(ModBlocks.carminite_slab));

    public final Material material;
    public final MapColor color;
    public final SoundType sound;
    public final float hardness;
    public final float resistance;
    public final String tool;
    public final int level;
    public final float multiplier;
    public final Supplier<Item> dropItem;

    NiftyBlock(Material material, MapColor color, SoundType sound, float hardness, float resistance, String tool, int level, float fall, Supplier<Item> slabItem) {
        this.material = material;
        this.color = color;
        this.sound = sound;
        this.hardness = hardness;
        this.resistance = resistance;
        this.tool = tool;
        this.level = level;
        this.multiplier = fall;
        this.dropItem = slabItem;
    }
}
