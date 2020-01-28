package com.androsa.nifty;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

import static net.minecraftforge.common.ToolType.*;

public enum NiftyBlock {
    IRON(Material.IRON, MaterialColor.IRON, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 1, 1.0F, false, () -> NiftyConfig.showIronBlocks),
    GOLD(Material.IRON, MaterialColor.GOLD, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2, 1.0F, false, () -> NiftyConfig.showGoldBlocks),
    DIAMOND(Material.IRON, MaterialColor.DIAMOND, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2, 1.0F, false, () -> NiftyConfig.showDiamondBlocks),
    EMERALD(Material.IRON, MaterialColor.EMERALD, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2, 1.0F, false, () -> NiftyConfig.showEmeraldBlocks),
    LAPIS(Material.IRON, MaterialColor.LAPIS, SoundType.STONE, 5.0F, 10.0F, PICKAXE, 1, 1.0F, false, () -> NiftyConfig.showLapisBlocks),
    OBSIDIAN(Material.ROCK, MaterialColor.BLACK, SoundType.STONE, 50.0F, 2000.0F, PICKAXE, 3, 1.0F, false, () -> NiftyConfig.showObsidianBlocks),
    COAL(Material.ROCK, MaterialColor.BLACK, SoundType.STONE, 5.0F, 10.0F, PICKAXE, 0, 1.0F, true, () -> NiftyConfig.showCoalBlocks),
    REDSTONE(Material.IRON, MaterialColor.TNT, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 0, 1.0F, false, () -> NiftyConfig.showRedstoneBlocks),
    MISSINGNO(Material.IRON, MaterialColor.MAGENTA, SoundType.METAL, 5.0F, 10.0F, PICKAXE, 2, 1.0F, false, () -> NiftyConfig.showMissingnoBlocks),
    CLAY(Material.CLAY, MaterialColor.CLAY, SoundType.GROUND, 0.6F, 0.6F, SHOVEL, 0, 1.0F, true, () -> NiftyConfig.showClayBlocks),
    DIRT(Material.EARTH, MaterialColor.DIRT, SoundType.GROUND, 0.5F, 0.0F, SHOVEL, 0, 1.0F, true, () -> NiftyConfig.showDirtBlocks),
    GRASS(Material.ORGANIC, MaterialColor.GRASS, SoundType.PLANT, 0.6F, 0.0F, SHOVEL, 0, 1.0F, true, () -> NiftyConfig.showGrassBlocks),
    HAY(Material.ORGANIC, MaterialColor.YELLOW, SoundType.PLANT, 0.5F, 0.0F, null, 0, 0.2F, true, () -> NiftyConfig.showHayBlocks),
    PATH(Material.EARTH, MaterialColor.DIRT, SoundType.PLANT, 0.6F, 0.0F, SHOVEL, 0, 1.0F, true, () -> NiftyConfig.showPathBlocks),
    BRICK(Material.ROCK, MaterialColor.RED, SoundType.STONE, 2.0F, 6.0F, PICKAXE, 0, 1.0F, false, () -> NiftyConfig.showBrickBlocks),
    QUARTZ(Material.ROCK, MaterialColor.QUARTZ, SoundType.STONE, 0.8F, 0.8F, PICKAXE, 0, 1.0F, false, () -> NiftyConfig.showQuartzBlocks),
    BONE(Material.ROCK, MaterialColor.SAND, SoundType.STONE, 2.0F, 2.0F, PICKAXE, 0, 1.0F, true, () -> NiftyConfig.showBoneBlocks),
    NETHER_BRICK(Material.ROCK, MaterialColor.NETHERRACK, SoundType.STONE, 2.0F, 6.0F, PICKAXE, 0, 1.0F, false, () -> NiftyConfig.showNetherBrickBlocks),
    RED_NETHER_BRICK(Material.ROCK, MaterialColor.NETHERRACK, SoundType.STONE, 2.0F, 6.0F, PICKAXE, 0, 1.0F, false, () -> NiftyConfig.showRedNetherBrickBlocks),
    SNOW(Material.SNOW_BLOCK, MaterialColor.SNOW, SoundType.SNOW, 0.1F, 0.1F, SHOVEL, 0, 1.0F, true, () -> NiftyConfig.showSnowBlocks),
    ICE(Material.ICE, MaterialColor.ICE, SoundType.GLASS, 0.5F, 0.5F, PICKAXE, 0, 1.0F, true, () -> NiftyConfig.showIceBlocks),
    PACKED_ICE(Material.PACKED_ICE, MaterialColor.ICE, SoundType.GLASS, 0.5F, 0.5F, PICKAXE, 0, 1.0F, true, () -> NiftyConfig.showPackedIceBlocks),
    BLUE_ICE(Material.PACKED_ICE, MaterialColor.ICE, SoundType.GLASS, 2.8F, 2.8F, PICKAXE, 0, 1.0F, true, () -> NiftyConfig.showBlueIceBlocks);

    public final Material material;
    public final MaterialColor color;
    public final SoundType sound;
    public final float hardness;
    public final float resistance;
    public final ToolType tool;
    public final int level;
    public final float multiplier;
    public final boolean canOpen;
    public final Supplier<ForgeConfigSpec.BooleanValue> booleanValue;

    NiftyBlock(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level, float fall, boolean open, Supplier<ForgeConfigSpec.BooleanValue> value) {
        this.material = material;
        this.color = color;
        this.sound = sound;
        this.hardness = hardness;
        this.resistance = resistance;
        this.tool = tool;
        this.level = level;
        this.multiplier = fall;
        this.canOpen = open;
        this.booleanValue = value;
    }
}
