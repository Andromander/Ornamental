package com.androsa.nifty;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NiftyMod.MODID)
public class NiftyMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "nifty";
    public static NiftyConfig config;

    public NiftyMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        final Pair<NiftyConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(NiftyConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPair.getRight());
        config = specPair.getLeft();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("Nifty is a mod by ???");
    }
}