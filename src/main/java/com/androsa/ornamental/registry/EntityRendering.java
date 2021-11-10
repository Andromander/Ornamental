package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.model.*;
import com.androsa.ornamental.entity.renderer.*;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRendering {

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

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GOLD_GOLEM, GoldGolemModel::createBodyLayer);
        event.registerLayerDefinition(DIAMOND_GOLEM, DiamondGolemModel::createBodyLayer);
        event.registerLayerDefinition(EMERALD_GOLEM, EmeraldGolemModel::createBodyLayer);
        event.registerLayerDefinition(LAPIS_GOLEM, LapisGolemModel::createBodyLayer);
        event.registerLayerDefinition(OBSIDIAN_GOLEM, ObsidianGolemModel::createBodyLayer);
        event.registerLayerDefinition(COAL_GOLEM, CoalGolemModel::createBodyLayer);
        event.registerLayerDefinition(REDSTONE_GOLEM, RedstoneGolemModel::createBodyLayer);
        event.registerLayerDefinition(CLAY_GOLEM, ClayGolemModel::createBodyLayer);
        event.registerLayerDefinition(DIRT_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(GRASS_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(HAY_GOLEM, HayGolemModel::createBodyLayer);
        event.registerLayerDefinition(DIRT_PATH_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(BRICK_GOLEM, ClayGolemModel::createBodyLayer);
        event.registerLayerDefinition(QUARTZ_GOLEM, QuartzGolemModel::createBodyLayer);
        event.registerLayerDefinition(BONE_GOLEM, BoneGolemModel::createBodyLayer);
        event.registerLayerDefinition(NETHER_BRICK_GOLEM, NetherBrickGolemModel::createBodyLayer);
        event.registerLayerDefinition(RED_NETHER_BRICK_GOLEM, NetherBrickGolemModel::createBodyLayer);
        event.registerLayerDefinition(ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(PACKED_ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(BLUE_ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(NETHERITE_GOLEM, NetheriteGolemModel::createBodyLayer);
        event.registerLayerDefinition(COPPER_GOLEM, CopperGolemModel::createBodyLayer);
        event.registerLayerDefinition(AMETHYST_GOLEM, AmethystGolemModel::createBodyLayer);
    }

    private static ModelLayerLocation makeEntity(String name) {
        return modelLocation(name, "main");
    }

    private static ModelLayerLocation modelLocation(String entity, String layer) {
        return new ModelLayerLocation(new ResourceLocation(OrnamentalMod.MODID, entity), layer);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.GOLD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new GoldGolemModel<>(m.bakeLayer(GOLD_GOLEM)), 0.6F));
        event.registerEntityRenderer(ModEntities.DIAMOND_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new DiamondGolemModel<>(m.bakeLayer(DIAMOND_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.EMERALD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new EmeraldGolemModel<>(m.bakeLayer(EMERALD_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.LAPIS_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new LapisGolemModel<>(m.bakeLayer(LAPIS_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.OBSIDIAN_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ObsidianGolemModel<>(m.bakeLayer(OBSIDIAN_GOLEM)), 0.8F));
        event.registerEntityRenderer(ModEntities.COAL_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new CoalGolemModel<>(m.bakeLayer(COAL_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.REDSTONE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new RedstoneGolemModel<>(m.bakeLayer(REDSTONE_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.CLAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new ClayGolemModel<>(m.bakeLayer(CLAY_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.DIRT_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(DIRT_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.GRASS_GOLEM.get(), m -> new GrassGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(GRASS_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.HAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new HayGolemModel<>(m.bakeLayer(HAY_GOLEM)), 0.3F));
        event.registerEntityRenderer(ModEntities.PATH_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(DIRT_PATH_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ClayGolemModel<>(m.bakeLayer(BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.QUARTZ_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new QuartzGolemModel<>(m.bakeLayer(QUARTZ_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.BONE_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new BoneGolemModel<>(m.bakeLayer(BONE_GOLEM)), 0.6F));
        event.registerEntityRenderer(ModEntities.NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(m.bakeLayer(NETHER_BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.RED_NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(m.bakeLayer(RED_NETHER_BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.ICE_GOLEM.get(), IceGolemTransparentRenderer::new);
        event.registerEntityRenderer(ModEntities.PACKED_ICE_GOLEM.get(), m -> new IceGolemRenderer<>(m, PACKED_ICE_GOLEM));
        event.registerEntityRenderer(ModEntities.BLUE_ICE_GOLEM.get(), m -> new IceGolemRenderer<>(m, BLUE_ICE_GOLEM));
        event.registerEntityRenderer(ModEntities.NETHERITE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new NetheriteGolemModel<>(m.bakeLayer(NETHERITE_GOLEM)), 1.0F));
        event.registerEntityRenderer(ModEntities.COPPER_GOLEM.get(), m -> new CopperGolemRenderer<>(m, new CopperGolemModel<>(m.bakeLayer(COPPER_GOLEM)), 1.0F));
        event.registerEntityRenderer(ModEntities.AMETHYST_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new AmethystGolemModel<>(m.bakeLayer(AMETHYST_GOLEM)), 1.0F));

        event.registerEntityRenderer(ModEntities.LAPIS_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.REDSTONE_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWN_BRICK.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWN_NETHER_BRICK.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PACKED_ICEBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLUE_ICEBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CHARGE_BALL.get(), ChargeBallRenderer::new);
    }
}
