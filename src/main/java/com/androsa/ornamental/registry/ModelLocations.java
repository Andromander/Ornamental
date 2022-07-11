package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.model.*;
import com.androsa.ornamental.entity.renderer.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelLocations {

    public static final ModelLayerLocation GOLD_GOLEM = makeEntity("gold_golem");
    public static final ModelLayerLocation DIAMOND_GOLEM = makeEntity("diamond_golem");
    public static final ModelLayerLocation EMERALD_GOLEM = makeEntity("emerald_golem");
    public static final ModelLayerLocation LAPIS_GOLEM = makeEntity("lapis_golem");
    public static final ModelLayerLocation OBSIDIAN_GOLEM = makeEntity("obsidian_golem");
    public static final ModelLayerLocation COAL_GOLEM = makeEntity("coal_golem");
    public static final ModelLayerLocation REDSTONE_GOLEM = makeEntity("redstone_golem");
    public static final ModelLayerLocation CLAY_GOLEM = makeEntity("clay_golem");
    public static final ModelLayerLocation DIRT_GOLEM = makeEntity("dirt_golem");
    public static final ModelLayerLocation GRASS_GOLEM = makeEntity("grass_golem");
    public static final ModelLayerLocation HAY_GOLEM = makeEntity("hay_golem");
    public static final ModelLayerLocation DIRT_PATH_GOLEM = makeEntity("dirt_path_golem");
    public static final ModelLayerLocation BRICK_GOLEM = makeEntity("brick_golem");
    public static final ModelLayerLocation QUARTZ_GOLEM = makeEntity("quartz_golem");
    public static final ModelLayerLocation BONE_GOLEM = makeEntity("bone_golem");
    public static final ModelLayerLocation NETHER_BRICK_GOLEM = makeEntity("nether_brick_golem");
    public static final ModelLayerLocation RED_NETHER_BRICK_GOLEM = makeEntity("red_nether_brick_golem");
    public static final ModelLayerLocation ICE_GOLEM = makeEntity("ice_golem");
    public static final ModelLayerLocation PACKED_ICE_GOLEM = makeEntity("packed_ice_golem");
    public static final ModelLayerLocation BLUE_ICE_GOLEM = makeEntity("blue_ice_golem");
    public static final ModelLayerLocation NETHERITE_GOLEM = makeEntity("netherite_golem");
    public static final ModelLayerLocation COPPER_GOLEM = makeEntity("copper_golem");
    public static final ModelLayerLocation AMETHYST_GOLEM = makeEntity("amethyst_golem");

    private static ModelLayerLocation makeEntity(String name) {
        return modelLocation(name, "main");
    }

    private static ModelLayerLocation modelLocation(String entity, String layer) {
        return new ModelLayerLocation(new ResourceLocation(OrnamentalMod.MODID, entity), layer);
    }
}
