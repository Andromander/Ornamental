package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

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
     * Remap blocks from "nifty" to "ornamental"
     */
    @SubscribeEvent
    public static void remapBlocks(RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
            if (mapping.key.getNamespace().equals("nifty")) {
                ResourceLocation remap = new ResourceLocation(OrnamentalMod.MODID, mapping.key.getPath());
                mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
            }
        }
    }

    /**
     * Remap items from "nifty" to "ornamental"
     */
    @SubscribeEvent
    public static void remapItems(RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
            if (mapping.key.getNamespace().equals("nifty")) {
                ResourceLocation remap = new ResourceLocation(OrnamentalMod.MODID, mapping.key.getPath());
                mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
            }
        }
    }

    /**
     * Remap particles from "nifty" to "ornamental"
     */
    @SubscribeEvent
    public static void remapParticles(RegistryEvent.MissingMappings<ParticleType<?>> event) {
        for (RegistryEvent.MissingMappings.Mapping<ParticleType<?>> mapping : event.getAllMappings()) {
            if (mapping.key.getNamespace().equals("nifty")) {
                ResourceLocation remap = new ResourceLocation(OrnamentalMod.MODID, mapping.key.getPath());
                mapping.remap(ForgeRegistries.PARTICLE_TYPES.getValue(remap));
            }
        }
    }

    /**
     * Remap entities from "nifty" to "ornamental"
     */
    @SubscribeEvent
    public static void remapEntities(RegistryEvent.MissingMappings<EntityType<?>> event) {
        for (RegistryEvent.MissingMappings.Mapping<EntityType<?>> mapping : event.getAllMappings()) {
            if (mapping.key.getNamespace().equals("nifty")) {
                ResourceLocation remap = new ResourceLocation(OrnamentalMod.MODID, mapping.key.getPath());
                mapping.remap(ForgeRegistries.ENTITIES.getValue(remap));
            }
        }
    }
}
