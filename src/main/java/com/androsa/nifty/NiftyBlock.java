package com.androsa.nifty;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import static net.minecraftforge.common.ToolType.*;

public enum NiftyBlock {
    IRON(Material.IRON, MaterialColor.IRON, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 1),
    GOLD(Material.IRON, MaterialColor.GOLD, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2),
    DIAMOND(Material.IRON, MaterialColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2),
    EMERALD(Material.IRON, MaterialColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2),
    LAPIS(Material.IRON, MaterialColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, PICKAXE, 1),
    OBSIDIAN(Material.ROCK, MaterialColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, PICKAXE, 3),
    COAL(Material.ROCK, MaterialColor.BLACK, SoundType.STONE, 5.0F, 10.0F, PICKAXE, 0),
    REDSTONE(Material.IRON, MaterialColor.TNT, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 0),
    MISSINGNO(Material.IRON, MaterialColor.MAGENTA, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2),
    CLAY(Material.GROUND, MaterialColor.CLAY, SoundType.GROUND, 0.6F, 0.6F, SHOVEL, 0),
    DIRT(Material.GROUND, MaterialColor.DIRT, SoundType.GROUND, 0.5F, 0.0F, SHOVEL, 0),
    GRASS(Material.GRASS, MaterialColor.GRASS, SoundType.PLANT, 0.6F, 0.0F, SHOVEL, 0);

    public final Material material;
    public final MaterialColor color;
    public final SoundType sound;
    public final float hardness;
    public final float resistance;
    public final ToolType tool;
    public final int level;

    NiftyBlock(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level) {
        this.material = material;
        this.color = color;
        this.sound = sound;
        this.hardness = hardness;
        this.resistance = resistance;
        this.tool = tool;
        this.level = level;
    }
}
