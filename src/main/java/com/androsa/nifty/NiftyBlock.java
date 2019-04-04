package com.androsa.nifty;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public enum NiftyBlock {
    IRON(Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 1),
    GOLD(Material.IRON, MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2),
    DIAMOND(Material.IRON, MapColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2),
    EMERALD(Material.IRON, MapColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2),
    LAPIS(Material.IRON, MapColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, "pickaxe", 1),
    OBSIDIAN(Material.ROCK, MapColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, "pickaxe", 3),
    COAL(Material.ROCK, MapColor.BLACK, SoundType.STONE, 5.0F, 10.0F, "pickaxe", 0),
    REDSTONE(Material.IRON, MapColor.TNT, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 0),
    MISSINGNO(Material.IRON, MapColor.MAGENTA, SoundType.METAL, 5.0F, 10.0F, "pickaxe", 2),

    IRONWOOD(Material.WOOD, MapColor.WOOD, SoundType.WOOD, 5.0F, 10.0F, null, 0),
    FIERY(Material.IRON, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.METAL, 5.0F, 10.0F, null, 0),
    STEELEAF(Material.LEAVES, MapColor.FOLIAGE, SoundType.PLANT, 5.0F, 10.0F, null, 0),
    ARCTIC(Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F, 10.0F, null, 0),
    CARMINITE(Material.CLAY, MapColor.RED, SoundType.SLIME, 0.0F, 10.0F, null, 0);

    public final Material material;
    public final MapColor color;
    public final SoundType sound;
    public final float hardness;
    public final float resistance;
    public final String tool;
    public final int level;

    NiftyBlock(Material material, MapColor color, SoundType sound, float hardness, float resistance, String tool, int level) {
        this.material = material;
        this.color = color;
        this.sound = sound;
        this.hardness = hardness;
        this.resistance = resistance;
        this.tool = tool;
        this.level = level;
    }
}
