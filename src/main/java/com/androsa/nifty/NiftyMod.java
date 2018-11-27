package com.androsa.nifty;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = NiftyMod.MODID,
        name = NiftyMod.NAME,
        version = NiftyMod.VERSION,
        updateJSON = "https://raw.githubusercontent.com/Andromander/Nifty/master/update.json",
        dependencies = "required:forge@[14.23.5.2768,);")
public class NiftyMod {
    public static final String MODID = "nifty";
    public static final String NAME = "Nifty";
    public static final String VERSION = "1.2";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("Nifty is a mod by ???");
    }
}
