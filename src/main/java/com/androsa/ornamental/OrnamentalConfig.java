package com.androsa.ornamental;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class OrnamentalConfig {

    public static BooleanValue showIronBlocks;
    public static BooleanValue showGoldBlocks;
    public static BooleanValue showDiamondBlocks;
    public static BooleanValue showEmeraldBlocks;
    public static BooleanValue showLapisBlocks;
    public static BooleanValue showObsidianBlocks;
    public static BooleanValue showCoalBlocks;
    public static BooleanValue showRedstoneBlocks;
    public static BooleanValue showMissingnoBlocks;
    public static BooleanValue showClayBlocks;
    public static BooleanValue showDirtBlocks;
    public static BooleanValue showGrassBlocks;
    public static BooleanValue showHayBlocks;
    public static BooleanValue showPathBlocks;
    public static BooleanValue showBrickBlocks;
    public static BooleanValue showQuartzBlocks;
    public static BooleanValue showBoneBlocks;
    public static BooleanValue showNetherBrickBlocks;
    public static BooleanValue showRedNetherBrickBlocks;
    public static BooleanValue showSnowBlocks;
    public static BooleanValue showIceBlocks;
    public static BooleanValue showPackedIceBlocks;
    public static BooleanValue showBlueIceBlocks;
    public static BooleanValue showNetheriteBlocks;

    public OrnamentalConfig(ForgeConfigSpec.Builder builder) {
        showIronBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_iron_blocks")
                .comment("Should Iron blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showIronBlocks", true);
        showGoldBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_gold_blocks")
                .comment("Should Gold blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showGoldBlocks", true);
        showDiamondBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_diamond_blocks")
                .comment("Should Diamond blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showDiamondBlocks", true);
        showEmeraldBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_emerald_blocks")
                .comment("Should Emerald blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showEmeraldBlocks", true);
        showLapisBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_lapis_blocks")
                .comment("Should Lapis Lazuli blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showLapisBlocks", true);
        showObsidianBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_obsidian_blocks")
                .comment("Should Obsidian blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showObsidianBlocks", true);
        showCoalBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_coal_blocks")
                .comment("Should Coal blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showCoalBlocks", true);
        showRedstoneBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_redstone_blocks")
                .comment("Should Redstone blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showRedstoneBlocks", true);
        showMissingnoBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_missingno_blocks")
                .comment("Should Missingno blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showMissingnoBlocks", true);
        showClayBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_clay_blocks")
                .comment("Should Clay blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showClayBlocks", true);
        showDirtBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_dirt_blocks")
                .comment("Should Dirt blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showDirtBlocks", true);
        showGrassBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_grass_blocks")
                .comment("Should Grass blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showGrassBlocks", true);
        showHayBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_hay_blocks")
                .comment("Should Hay blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showHayBlocks", true);
        showPathBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_path_blocks")
                .comment("Should Path blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showPathBlocks", true);
        showBrickBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_brick_blocks")
                .comment("Should Brick blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showBrickBlocks", true);
        showQuartzBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_quartz_blocks")
                .comment("Should Quartz blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showQuartzBlocks", true);
        showBoneBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_bone_blocks")
                .comment("Should Bone blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showBoneBlocks", true);
        showNetherBrickBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_nether_brick_blocks")
                .comment("Should Nether Brick blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showNetherBrickBlocks", true);
        showRedNetherBrickBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_red_nether_brick_blocks")
                .comment("Should Red Nether Brick blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showRedNetherBrickBlocks", true);
        showSnowBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_snow_blocks")
                .comment("Should Snow blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showSnowBlocks", true);
        showIceBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_ice_blocks")
                .comment("Should Ice blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showIceBlocks", true);
        showPackedIceBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_packed_ice_blocks")
                .comment("Should Packed Ice blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showPackedIceBlocks", true);
        showBlueIceBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_blue_ice_blocks")
                .comment("Should Blue Ice blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showBlueIceBlocks", true);
        showNetheriteBlocks = builder
                .translation(OrnamentalMod.MODID + ".config.show_netherite_blocks")
                .comment("Should Netherite blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showNetheriteBlocks", true);
    }
}
