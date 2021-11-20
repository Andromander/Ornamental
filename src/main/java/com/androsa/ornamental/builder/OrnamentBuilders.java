package com.androsa.ornamental.builder;

import com.androsa.ornamental.OrnamentalConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

public class OrnamentBuilders {

    public static final OrnamentBuilder IRON = new OrnamentBuilder("iron")
            .properties(Material.METAL)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showIronBlocks);

    public static final OrnamentBuilder GOLD = new OrnamentBuilder("gold")
            .properties(Material.METAL, MaterialColor.GOLD)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showGoldBlocks);

    public static final OrnamentBuilder DIAMOND = new OrnamentBuilder("diamond")
            .properties(Material.METAL, MaterialColor.DIAMOND)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showDiamondBlocks);

    public static final OrnamentBuilder EMERALD = new OrnamentBuilder("emerald")
            .properties(Material.METAL, MaterialColor.EMERALD)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showEmeraldBlocks);

    public static final OrnamentBuilder LAPIS = new OrnamentBuilder("lapis")
            .properties(Material.METAL, MaterialColor.LAPIS)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showLapisBlocks);

    public static final OrnamentBuilder OBSIDIAN = new OrnamentBuilder("obsidian")
            .properties(Material.STONE, MaterialColor.COLOR_BLACK)
            .hardnessAndResistance(50.0F, 2000.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showObsidianBlocks);

    public static final OrnamentBuilder COAL = new OrnamentBuilder("coal")
            .properties(Material.STONE, MaterialColor.COLOR_BLACK)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .burnTime(10500, 5250, 4000, 8000, 12000, 5250, 4000, 4000, 12000)
            .canOpen()
            .config(() -> OrnamentalConfig.showCoalBlocks);

    public static final OrnamentBuilder REDSTONE = new OrnamentBuilder("redstone")
            .properties(Material.METAL, MaterialColor.FIRE)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .hasPower()
            .config(() -> OrnamentalConfig.showRedstoneBlocks);

    public static final OrnamentBuilder MISSINGNO = new OrnamentBuilder("missingno")
            .properties(Material.METAL, MaterialColor.COLOR_MAGENTA)
            .sound(SoundType.METAL)
            .hardnessAndResistance(5.0F, 10.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showMissingnoBlocks);

    public static final OrnamentBuilder CLAY = new OrnamentBuilder("clay")
            .properties(Material.CLAY)
            .sound(SoundType.GRAVEL)
            .hardnessAndResistance(0.6F)
            .canOpen()
            .config(() -> OrnamentalConfig.showClayBlocks);

    public static final OrnamentBuilder DIRT = new OrnamentBuilder("dirt")
            .properties(Material.DIRT)
            .sound(SoundType.GRAVEL)
            .hardness(0.5F)
            .canOpen()
            .boneMealToGrass()
            .config(() -> OrnamentalConfig.showDirtBlocks);

    public static final OrnamentBuilder GRASS = new OrnamentBuilder("grass")
            .properties(Material.GRASS)
            .sound(SoundType.GRASS)
            .hardness(0.6F)
            .canOpen()
            .hoeToDirt()
            .shovelToPath()
            .config(() -> OrnamentalConfig.showGrassBlocks);

    public static final OrnamentBuilder HAY = new OrnamentBuilder("hay")
            .properties(Material.GRASS, MaterialColor.COLOR_YELLOW)
            .sound(SoundType.GRASS)
            .hardness(0.5F)
            .fall(0.2F)
            .canOpen()
            .config(() -> OrnamentalConfig.showHayBlocks);

    public static final OrnamentBuilder PATH = new OrnamentBuilder("dirt_path")
            .properties(Material.DIRT)
            .sound(SoundType.GRASS)
            .hardness(0.6F)
            .canOpen()
            .hoeToGrass()
            .usePathShapes()
            .config(() -> OrnamentalConfig.showPathBlocks);

    public static final OrnamentBuilder BRICK = new OrnamentBuilder("brick")
            .properties(Material.STONE, MaterialColor.COLOR_RED)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showBrickBlocks);

    public static final OrnamentBuilder QUARTZ = new OrnamentBuilder("quartz")
            .properties(Material.STONE, MaterialColor.QUARTZ)
            .hardnessAndResistance(0.8F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showQuartzBlocks);

    public static final OrnamentBuilder BONE = new OrnamentBuilder("bone")
            .properties(Material.STONE, MaterialColor.SAND)
            .hardnessAndResistance(2.0F)
            .requiresTool()
            .canOpen()
            .config(() -> OrnamentalConfig.showBoneBlocks);

    public static final OrnamentBuilder NETHER_BRICK = new OrnamentBuilder("nether_brick")
            .properties(Material.STONE, MaterialColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showNetherBrickBlocks);

    public static final OrnamentBuilder RED_NETHER_BRICK = new OrnamentBuilder("red_nether_brick")
            .properties(Material.STONE, MaterialColor.NETHER)
            .hardnessAndResistance(2.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showRedNetherBrickBlocks);

    public static final OrnamentBuilder SNOW = new OrnamentBuilder("snow")
            .properties(Material.SNOW)
            .sound(SoundType.SNOW)
            .hardnessAndResistance(0.1F)
            .requiresTool()
            .canOpen()
            .config(() -> OrnamentalConfig.showSnowBlocks);

    public static final OrnamentBuilder ICE = new OrnamentBuilder("ice")
            .properties(Material.ICE)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .canOpen()
            .ticks()
            .canMelt(Blocks.WATER, true)
            .notSolid()
            .doBreakableBlockCull()
            .pushReactOverride(PushReaction.NORMAL)
            .setCanEntitySpawn((state, reader, pos, type) -> type == EntityType.POLAR_BEAR && state.isFaceSturdy(reader, pos, Direction.UP))
            .config(() -> OrnamentalConfig.showIceBlocks);

    public static final OrnamentBuilder PACKED_ICE = new OrnamentBuilder("packed_ice")
            .properties(Material.ICE_SOLID)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(0.5F)
            .slip(0.98F)
            .canOpen()
            .config(() -> OrnamentalConfig.showPackedIceBlocks);

    public static final OrnamentBuilder BLUE_ICE = new OrnamentBuilder("blue_ice")
            .properties(Material.ICE_SOLID)
            .sound(SoundType.GLASS)
            .hardnessAndResistance(2.8F)
            .slip(0.989F)
            .canOpen()
            .config(() -> OrnamentalConfig.showBlueIceBlocks);

    public static final OrnamentBuilder NETHERITE = new OrnamentBuilder("netherite")
            .properties(Material.METAL, MaterialColor.COLOR_BLACK)
            .sound(SoundType.NETHERITE_BLOCK)
            .hardnessAndResistance(50.0F, 1200.0F)
            .requiresTool()
            .isFireproof()
            .config(() -> OrnamentalConfig.showNetheriteBlocks);

    public static final OrnamentBuilder COPPER = new OrnamentBuilder("copper")
            .properties(Material.METAL, MaterialColor.COLOR_ORANGE)
            .sound(SoundType.COPPER)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showCopperBlocks);

    public static final OrnamentBuilder EXPOSED_COPPER = new OrnamentBuilder("exposed_copper")
            .properties(Material.METAL, MaterialColor.TERRACOTTA_LIGHT_GRAY)
            .sound(SoundType.COPPER)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showCopperBlocks);

    public static final OrnamentBuilder WEATHERED_COPPER = new OrnamentBuilder("weathered_copper")
            .properties(Material.METAL, MaterialColor.WARPED_STEM)
            .sound(SoundType.COPPER)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showCopperBlocks);

    public static final OrnamentBuilder OXIDIZED_COPPER = new OrnamentBuilder("oxidized_copper")
            .properties(Material.METAL, MaterialColor.WARPED_NYLIUM)
            .sound(SoundType.COPPER)
            .hardnessAndResistance(3.0F, 6.0F)
            .requiresTool()
            .config(() -> OrnamentalConfig.showCopperBlocks);

    public static final OrnamentBuilder AMETHYST = new OrnamentBuilder("amethyst")
            .properties(Material.AMETHYST, MaterialColor.COLOR_PURPLE)
            .sound(SoundType.AMETHYST)
            .hardnessAndResistance(1.5F)
            .requiresTool()
            .projectileHitSound(ImmutableList.of(SoundEvents.AMETHYST_BLOCK_HIT, SoundEvents.AMETHYST_BLOCK_CHIME))
            .config(() -> OrnamentalConfig.showAmethystBlocks);
}
