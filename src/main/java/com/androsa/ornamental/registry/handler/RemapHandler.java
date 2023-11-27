package com.androsa.ornamental.registry.handler;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RemapHandler {

    /*
     * If you are reading this for some reason, this is a remapping class to take anything with "nifty" as the mod id and change it to "ornamental"
     * This will only happen if a world had Nifty installed and was replaced with Ornamental.
     * NOTE: Errors may occur if an existing mod takes the "nifty" mod id and is then removed before Ornamental runs.
     * BACK UP YOUR WORLDS.
     * If you are making addons: DO NOT USE THIS.
     */
    public static void remapEntries() {
        remapRegistry(ModBlocks.BLOCKS);
        remapRegistry(ModBlocks.ITEMS);
        remapRegistry(ModEntities.ENTITIES);
    }

    /**
     * Remap objects from "nifty" to "ornamental"
     * Remap Grass Path to Dirt Path
     */
    private static void remapRegistry(DeferredRegister<?> registry) {
        for (String name : registry.getEntries().stream().map((f) -> f.getKey().location().getPath()).toList()) {
            registry.addAlias(new ResourceLocation("nifty", name), new ResourceLocation(OrnamentalMod.MODID, name));

            if (name.contains("dirt_path")) {
                String oldname = name.replace("dirt", "grass");
                registry.addAlias(new ResourceLocation(OrnamentalMod.MODID, oldname), new ResourceLocation(OrnamentalMod.MODID, name));
            }
        }
    }
}
