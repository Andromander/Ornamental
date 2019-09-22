package com.androsa.nifty;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.minecraftforge.common.ForgeConfigSpec.*;

public class NiftyConfig {

    public static BooleanValue showMissingnoBlocks;

    public NiftyConfig(ForgeConfigSpec.Builder builder) {
        showMissingnoBlocks = builder
                .translation(NiftyMod.MODID + ".config.show_missingno_blocks")
                .worldRestart()
                .comment("Should Missingno blocks be obtainable as items. Will not remove existing blocks from worlds.")
                .define("showMissingnoBlocks", true);
    }
}
