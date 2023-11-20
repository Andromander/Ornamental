package com.androsa.ornamental.registry.handler;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.IForgeRegistry;
import net.neoforged.neoforge.registries.MissingMappingsEvent;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RemapHandler {

    /*
     * If you are reading this for some reason, this is a remapping event to take anything with "nifty" as the mod id and change it to "ornamental"
     * This will only happen if a world had Nifty installed and was replaced with Ornamental.
     * NOTE: Errors may occur if an existing mod takes the "nifty" mod id and is then removed before Ornamental runs.
     * BACK UP YOUR WORLDS.
     * If you are making addons: DO NOT USE THIS.
     */

    /**
     * Remap objects from "nifty" to "ornamental"
     * Remap Grass Path to Dirt Path
     */
    @SubscribeEvent
    public static void remap(MissingMappingsEvent event) {
        remap(event, ForgeRegistries.Keys.BLOCKS, ForgeRegistries.BLOCKS, true);
        remap(event, ForgeRegistries.Keys.ITEMS, ForgeRegistries.ITEMS, true);
        remap(event, ForgeRegistries.Keys.ENTITY_TYPES, ForgeRegistries.ENTITY_TYPES, true);
        remap(event, ForgeRegistries.Keys.PARTICLE_TYPES, ForgeRegistries.PARTICLE_TYPES, false);
    }

    private static <T> void remap(MissingMappingsEvent event, ResourceKey<Registry<T>> regkey, IForgeRegistry<T> registry, boolean path) {
        if (event.getKey() == regkey) {
            for (MissingMappingsEvent.Mapping<T> mapping : event.getAllMappings(regkey)) {
                ResourceLocation key = mapping.getKey();

                if (key.getNamespace().equals("nifty"))
                    remap(mapping, registry, mapping.getKey().getPath());

                if (path)
                    if (key.getNamespace().equals(OrnamentalMod.MODID) && key.getPath().contains("grass_path"))
                        remap(mapping, registry, mapping.getKey().getPath().replace("grass", "dirt")); //we already know the context is "grass_path"
            }
        }
    }

    private static <T> void remap(MissingMappingsEvent.Mapping<T> mapping, IForgeRegistry<T> registry, String path) {
        ResourceLocation remap = new ResourceLocation(OrnamentalMod.MODID, path);
        mapping.remap(registry.getValue(remap));
    }
}
