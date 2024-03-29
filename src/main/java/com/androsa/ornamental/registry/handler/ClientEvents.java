package com.androsa.ornamental.registry.handler;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.model.*;
import com.androsa.ornamental.entity.renderer.*;
import com.androsa.ornamental.particle.CastingParticle;
import com.androsa.ornamental.particle.ChargeSparkParticle;
import com.androsa.ornamental.particle.OrnamentalBreakingParticle;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import com.androsa.ornamental.registry.ModelLocations;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void blockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, index) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.get(0.5D, 1.0D),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_door.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get(),
                ModBlocks.grass_wall.get(),
                ModBlocks.grass_saddle_door.get(),
                ModBlocks.grass_support.get());
        event.register((state, level, pos, index) -> level != null && pos != null ? BiomeColors.getAverageWaterColor(level, pos) : -1,
                ModBlocks.fake_bubble_column.get());
    }

    @SubscribeEvent
    public static void itemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, index) -> event.getBlockColors().getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, index),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get(),
                ModBlocks.grass_wall.get(),
                ModBlocks.grass_saddle_door.get(),
                ModBlocks.grass_support.get());
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLocations.GOLD_GOLEM, GoldGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.DIAMOND_GOLEM, DiamondGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.EMERALD_GOLEM, EmeraldGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.LAPIS_GOLEM, LapisGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.OBSIDIAN_GOLEM, ObsidianGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.COAL_GOLEM, CoalGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.REDSTONE_GOLEM, RedstoneGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.CLAY_GOLEM, ClayGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.DIRT_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.GRASS_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.HAY_GOLEM, HayGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.DIRT_PATH_GOLEM, DirtGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.BRICK_GOLEM, ClayGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.QUARTZ_GOLEM, QuartzGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.BONE_GOLEM, BoneGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.NETHER_BRICK_GOLEM, NetherBrickGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.RED_NETHER_BRICK_GOLEM, NetherBrickGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.PACKED_ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.BLUE_ICE_GOLEM, SnowGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.NETHERITE_GOLEM, NetheriteGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.COPPER_GOLEM, CopperGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.AMETHYST_GOLEM, AmethystGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.MAGMA_GOLEM, MagmaGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModelLocations.CALCITE_GOLEM, CalciteGolemModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.GOLD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new GoldGolemModel<>(m.bakeLayer(ModelLocations.GOLD_GOLEM)), 0.6F));
        event.registerEntityRenderer(ModEntities.DIAMOND_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new DiamondGolemModel<>(m.bakeLayer(ModelLocations.DIAMOND_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.EMERALD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new EmeraldGolemModel<>(m.bakeLayer(ModelLocations.EMERALD_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.LAPIS_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new LapisGolemModel<>(m.bakeLayer(ModelLocations.LAPIS_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.OBSIDIAN_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ObsidianGolemModel<>(m.bakeLayer(ModelLocations.OBSIDIAN_GOLEM)), 0.8F));
        event.registerEntityRenderer(ModEntities.COAL_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new CoalGolemModel<>(m.bakeLayer(ModelLocations.COAL_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.REDSTONE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new RedstoneGolemModel<>(m.bakeLayer(ModelLocations.REDSTONE_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.CLAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new ClayGolemModel<>(m.bakeLayer(ModelLocations.CLAY_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.DIRT_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(ModelLocations.DIRT_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.GRASS_GOLEM.get(), m -> new GrassGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(ModelLocations.GRASS_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.HAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new HayGolemModel<>(m.bakeLayer(ModelLocations.HAY_GOLEM)), 0.3F));
        event.registerEntityRenderer(ModEntities.PATH_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(m.bakeLayer(ModelLocations.DIRT_PATH_GOLEM)), 0.2F));
        event.registerEntityRenderer(ModEntities.BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ClayGolemModel<>(m.bakeLayer(ModelLocations.BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.QUARTZ_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new QuartzGolemModel<>(m.bakeLayer(ModelLocations.QUARTZ_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.BONE_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new BoneGolemModel<>(m.bakeLayer(ModelLocations.BONE_GOLEM)), 0.6F));
        event.registerEntityRenderer(ModEntities.NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(m.bakeLayer(ModelLocations.NETHER_BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.RED_NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(m.bakeLayer(ModelLocations.RED_NETHER_BRICK_GOLEM)), 0.4F));
        event.registerEntityRenderer(ModEntities.ICE_GOLEM.get(), IceGolemTransparentRenderer::new);
        event.registerEntityRenderer(ModEntities.PACKED_ICE_GOLEM.get(), m -> new IceGolemRenderer<>(m, ModelLocations.PACKED_ICE_GOLEM));
        event.registerEntityRenderer(ModEntities.BLUE_ICE_GOLEM.get(), m -> new IceGolemRenderer<>(m, ModelLocations.BLUE_ICE_GOLEM));
        event.registerEntityRenderer(ModEntities.NETHERITE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new NetheriteGolemModel<>(m.bakeLayer(ModelLocations.NETHERITE_GOLEM)), 1.0F));
        event.registerEntityRenderer(ModEntities.COPPER_GOLEM.get(), m -> new CopperGolemRenderer<>(m, new CopperGolemModel<>(m.bakeLayer(ModelLocations.COPPER_GOLEM)), 1.0F));
        event.registerEntityRenderer(ModEntities.AMETHYST_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new AmethystGolemModel<>(m.bakeLayer(ModelLocations.AMETHYST_GOLEM)), 1.0F));
        event.registerEntityRenderer(ModEntities.MAGMA_GOLEM.get(), m -> new MagmaGolemRenderer<>(m, new MagmaGolemModel<>(m.bakeLayer(ModelLocations.MAGMA_GOLEM)), 0.5F));
        event.registerEntityRenderer(ModEntities.CALCITE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new CalciteGolemModel<>(m.bakeLayer(ModelLocations.CALCITE_GOLEM)), 0.5F));

        event.registerEntityRenderer(ModEntities.LAPIS_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.REDSTONE_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWN_BRICK.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.THROWN_NETHER_BRICK.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PACKED_ICEBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLUE_ICEBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CHARGE_BALL.get(), ChargeBallRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpecial(ModParticles.ITEM_LAPIS.get(), new OrnamentalBreakingParticle.LapisFactory());
        event.registerSpecial(ModParticles.ITEM_REDSTONE.get(), new OrnamentalBreakingParticle.RedstoneFactory());
        event.registerSpecial(ModParticles.ITEM_BRICK.get(), new OrnamentalBreakingParticle.BrickFactory());
        event.registerSpecial(ModParticles.ITEM_NETHER_BRICK.get(), new OrnamentalBreakingParticle.NetherBrickFactory());

        event.registerSpriteSet(ModParticles.CHARGE_SPARK.get(), ChargeSparkParticle.ChargeSparkFactory::new);
        event.registerSpriteSet(ModParticles.AMETHYST_CAST.get(), CastingParticle.AmethystFactory::new);
    }
}
