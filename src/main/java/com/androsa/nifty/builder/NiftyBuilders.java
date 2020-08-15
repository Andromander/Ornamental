package com.androsa.nifty.builder;

import com.androsa.nifty.NiftyConfig;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

public class NiftyBuilders {

    public static final NiftyBuilder IRON = new NiftyBuilder("iron")
            .properties(Material.IRON)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 1, true)
            .config(() -> NiftyConfig.showIronBlocks);

    public static final NiftyBuilder GOLD = new NiftyBuilder("gold")
            .properties(Material.IRON, MaterialColor.GOLD)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 2, true)
            .config(() -> NiftyConfig.showGoldBlocks);

    public static final NiftyBuilder DIAMOND = new NiftyBuilder("diamond")
            .properties(Material.IRON, MaterialColor.DIAMOND)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 2, true)
            .config(() -> NiftyConfig.showDiamondBlocks);

    public static final NiftyBuilder EMERALD = new NiftyBuilder("emerald")
            .properties(Material.IRON, MaterialColor.EMERALD)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 2, true)
            .config(() -> NiftyConfig.showEmeraldBlocks);

    public static final NiftyBuilder LAPIS = new NiftyBuilder("lapis")
            .properties(Material.IRON, MaterialColor.LAPIS)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 1, true)
            .config(() -> NiftyConfig.showLapisBlocks);

    public static final NiftyBuilder OBSIDIAN = new NiftyBuilder("obsidian")
            .properties(Material.ROCK, MaterialColor.BLACK)
            .hardnessAndResistance(50.0F, 2000.0F)
            .tool(ToolType.PICKAXE, 3, true)
            .config(() -> NiftyConfig.showObsidianBlocks);

    public static final NiftyBuilder COAL = new NiftyBuilder("coal")
            .properties(Material.ROCK, MaterialColor.BLACK)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, true)
            .burnTime(10500, 5250, 4000, 8000, 12000, 5250)
            .canOpen()
            .config(() -> NiftyConfig.showCoalBlocks);

    public static final NiftyBuilder REDSTONE = new NiftyBuilder("redstone")
            .properties(Material.IRON, MaterialColor.TNT)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, true)
            .hasPower()
            .config(() -> NiftyConfig.showRedstoneBlocks);

    public static final NiftyBuilder MISSINGNO = new NiftyBuilder("missingno")
            .properties(Material.IRON, MaterialColor.MAGENTA)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .tool(ToolType.PICKAXE, 2, true)
            .config(() -> NiftyConfig.showMissingnoBlocks);

    public static final NiftyBuilder CLAY = new NiftyBuilder("clay")
            .properties(Material.CLAY)
            .sound(SoundType.GROUND)
            .hardnessAndResistance(0.6F)
            .tool(ToolType.SHOVEL, false)
            .canOpen()
            .config(() -> NiftyConfig.showClayBlocks);

    public static final NiftyBuilder DIRT = new NiftyBuilder("dirt")
            .properties(Material.EARTH)
            .sound(SoundType.GROUND)
            .hardness(0.5F)
            .tool(ToolType.SHOVEL, false)
            .canOpen()
            .isDirtMaterial()
            .config(() -> NiftyConfig.showDirtBlocks);

    public static final NiftyBuilder GRASS = new NiftyBuilder("grass")
            .properties(Material.ORGANIC)
            .sound(SoundType.PLANT)
            .hardness(0.6F)
            .tool(ToolType.SHOVEL, false)
            .canOpen()
            .isGrassMaterial()
            .config(() -> NiftyConfig.showGrassBlocks);

    public static final NiftyBuilder HAY = new NiftyBuilder("hay")
            .properties(Material.ORGANIC, MaterialColor.YELLOW)
            .sound(SoundType.PLANT)
            .hardness(0.5F)
            .fall(0.2F)
            .canOpen()
            .config(() -> NiftyConfig.showHayBlocks);

    public static final NiftyBuilder PATH = new NiftyBuilder("grass_path")
            .properties(Material.EARTH)
            .sound(SoundType.PLANT)
            .hardness(0.6F)
            .tool(ToolType.SHOVEL, false)
            .canOpen()
            .isPathMaterial()
            .config(() -> NiftyConfig.showPathBlocks);

    public static final NiftyBuilder BRICK = new NiftyBuilder("brick")
            .properties(Material.ROCK, MaterialColor.RED)
            .hardnessAndResistance(2.0F, 6.0F)
            .tool(ToolType.PICKAXE, true)
            .config(() -> NiftyConfig.showBrickBlocks);

    public static final NiftyBuilder QUARTZ = new NiftyBuilder("quartz")
            .properties(Material.ROCK, MaterialColor.QUARTZ)
            .hardnessAndResistance(0.8F)
            .tool(ToolType.PICKAXE, true)
            .config(() -> NiftyConfig.showQuartzBlocks);

    public static final NiftyBuilder BONE = new NiftyBuilder("bone")
            .properties(Material.ROCK, MaterialColor.SAND)
            .hardnessAndResistance(2.0F)
            .tool(ToolType.PICKAXE, true)
            .canOpen()
            .config(() -> NiftyConfig.showBoneBlocks);

    public static final NiftyBuilder NETHER_BRICK = new NiftyBuilder("nether_brick")
            .properties(Material.ROCK, MaterialColor.NETHERRACK)
            .hardnessAndResistance(2.0F, 6.0F)
            .tool(ToolType.PICKAXE, true)
            .config(() -> NiftyConfig.showNetherBrickBlocks);

    public static final NiftyBuilder RED_NETHER_BRICK = new NiftyBuilder("red_nether_brick")
            .properties(Material.ROCK, MaterialColor.NETHERRACK)
            .hardnessAndResistance(2.0F, 6.0F)
            .tool(ToolType.PICKAXE, true)
            .config(() -> NiftyConfig.showRedNetherBrickBlocks);

    public static final NiftyBuilder SNOW = new NiftyBuilder("snow")
            .properties(Material.SNOW_BLOCK)
            .sound(SoundType.SNOW)
            .hardnessAndResistance(0.1F)
            .tool(ToolType.SHOVEL, true)
            .canOpen()
            .config(() -> NiftyConfig.showSnowBlocks);

    public static final NiftyBuilder ICE = new NiftyBuilder("ice")
            .properties(Material.ICE)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(0.5F)
            .tool(ToolType.PICKAXE, false)
            .slip(0.98F)
            .canOpen()
            .ticks()
            .isIceMaterial()
            .setCanEntitySpawn((state, reader, pos, type) -> type == EntityType.POLAR_BEAR && state.isSolidSide(reader, pos, Direction.UP))
            .config(() -> NiftyConfig.showIceBlocks);

    public static final NiftyBuilder PACKED_ICE = new NiftyBuilder("packed_ice")
            .properties(Material.PACKED_ICE)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(0.5F)
            .tool(ToolType.PICKAXE, false)
            .slip(0.98F)
            .canOpen()
            .config(() -> NiftyConfig.showPackedIceBlocks);

    public static final NiftyBuilder BLUE_ICE = new NiftyBuilder("blue_ice")
            .properties(Material.PACKED_ICE)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(2.8F)
            .tool(ToolType.PICKAXE, false)
            .slip(0.989F)
            .canOpen()
            .config(() -> NiftyConfig.showBlueIceBlocks);

    public static final NiftyBuilder NETHERITE = new NiftyBuilder("netherite")
            .properties(Material.IRON, MaterialColor.BLACK)
            .sound(SoundType.NETHERITE)
            .hardnessAndResistance(50.0F, 1200.0F)
            .tool(ToolType.PICKAXE, 3, true)
            .isFireproof()
            .config(() -> NiftyConfig.showNetheriteBlocks);
}
