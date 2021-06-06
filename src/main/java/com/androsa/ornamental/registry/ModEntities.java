package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.*;
import com.androsa.ornamental.entity.model.*;
import com.androsa.ornamental.entity.projectile.*;
import com.androsa.ornamental.entity.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, OrnamentalMod.MODID);

    public static final RegistryObject<EntityType<GoldGolemEntity>> GOLD_GOLEM = makeEntity("gold", GoldGolemEntity::new, 1.3F, 2.7F, false);
    public static final RegistryObject<EntityType<DiamondGolemEntity>> DIAMOND_GOLEM = makeEntity("diamond", DiamondGolemEntity::new, 1.0F, 1.5F, false);
    public static final RegistryObject<EntityType<EmeraldGolemEntity>> EMERALD_GOLEM = makeEntity("emerald", EmeraldGolemEntity::new, 1.3F, 2.6F, false);
    public static final RegistryObject<EntityType<LapisGolemEntity>> LAPIS_GOLEM = makeEntity("lapis", LapisGolemEntity::new, 1.2F, 2.3F, false);
    public static final RegistryObject<EntityType<ObsidianGolemEntity>> OBSIDIAN_GOLEM = makeEntity("obsidian", ObsidianGolemEntity::new, 2.0F, 2.9F, true);
    public static final RegistryObject<EntityType<CoalGolemEntity>> COAL_GOLEM = makeEntity("coal", CoalGolemEntity::new, 0.9F, 2.0F, false);
    public static final RegistryObject<EntityType<RedstoneGolemEntity>> REDSTONE_GOLEM = makeEntity("redstone", RedstoneGolemEntity::new, 0.8F, 1.8F, false);
    public static final RegistryObject<EntityType<ClayGolemEntity>> CLAY_GOLEM = makeEntity("clay", ClayGolemEntity::new, 1.0F, 1.9F, false);
    public static final RegistryObject<EntityType<DirtGolemEntity>> DIRT_GOLEM = makeEntity("dirt", DirtGolemEntity::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<GrassGolemEntity>> GRASS_GOLEM = makeEntity("grass", GrassGolemEntity::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<HayGolemEntity>> HAY_GOLEM = makeEntity("hay", HayGolemEntity::new, 0.7F, 2.5F, false);
    public static final RegistryObject<EntityType<PathGolemEntity>> PATH_GOLEM = makeEntity("grass_path", PathGolemEntity::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<BrickGolemEntity>> BRICK_GOLEM = makeEntity("brick", BrickGolemEntity::new, 1.0F, 1.9F, false);
    public static final RegistryObject<EntityType<QuartzGolemEntity>> QUARTZ_GOLEM = makeEntity("quartz", QuartzGolemEntity::new, 1.2F, 2.2F, true);
    public static final RegistryObject<EntityType<BoneGolemEntity>> BONE_GOLEM = makeEntity("bone", BoneGolemEntity::new, 1.0F, 3.1F, false);
    public static final RegistryObject<EntityType<NetherBrickGolemEntity>> NETHER_BRICK_GOLEM = makeEntity("nether_brick", NetherBrickGolemEntity::new, 1.0F, 2.5F, true);
    public static final RegistryObject<EntityType<RedNetherBrickGolemEntity>> RED_NETHER_BRICK_GOLEM = makeEntity("red_nether_brick", RedNetherBrickGolemEntity::new, 1.0F, 2.5F, true);
    public static final RegistryObject<EntityType<IceGolemEntity>> ICE_GOLEM = makeEntity("ice", IceGolemEntity::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<PackedIceGolemEntity>> PACKED_ICE_GOLEM = makeEntity("packed_ice", PackedIceGolemEntity::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<BlueIceGolemEntity>> BLUE_ICE_GOLEM = makeEntity("blue_ice", BlueIceGolemEntity::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<NetheriteGolemEntity>> NETHERITE_GOLEM = makeEntity("netherite", NetheriteGolemEntity::new, 1.8F, 3.1F, true);

    public static final RegistryObject<EntityType<LapisBulletEntity>> LAPIS_BULLET = makeProjectile("lapis_bullet", LapisBulletEntity::new, 0.25F, 0.25F, 4, 10);
    public static final RegistryObject<EntityType<RedstoneBulletEntity>> REDSTONE_BULLET = makeProjectile("redstone_bullet", RedstoneBulletEntity::new, 0.25F, 0.25F, 4, 10);
    public static final RegistryObject<EntityType<ThrownBrickEntity>> THROWN_BRICK = makeProjectile("thrown_brick", ThrownBrickEntity::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<ThrownNetherBrickEntity>> THROWN_NETHER_BRICK = makeProjectile("thrown_nether_brick", ThrownNetherBrickEntity::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<PackedIceEntity>> PACKED_ICEBALL = makeProjectile("packed_iceball", PackedIceEntity::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<BlueIceEntity>> BLUE_ICEBALL = makeProjectile("blue_iceball", BlueIceEntity::new, 0.25F, 0.25F, 150, 2);

    private static <T extends Entity> RegistryObject<EntityType<T>> makeEntity(String name, EntityType.IFactory<T> entity, float width, float height, boolean fireRes) {
        String regname = name + "_golem";
        EntityType.Builder<T> builder = EntityType.Builder.of(entity, EntityClassification.MISC)
                .sized(width, height);
        if (fireRes) builder.fireImmune();

        return ENTITIES.register(regname, () -> builder.build(regname));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> makeProjectile(String name, EntityType.IFactory<T> entity, float width, float height, int tracking, int interval) {
        return ENTITIES.register(name, () ->
                EntityType.Builder.of(entity, EntityClassification.MISC)
                        .sized(width, height)
                        .setTrackingRange(tracking)
                        .setUpdateInterval(interval)
                        .setShouldReceiveVelocityUpdates(true)
                        .build(name)
        );
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(GOLD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new GoldGolemModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(DIAMOND_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new DiamondGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(EMERALD_GOLEM.get(), m -> new FlowerGolemRenderer<>(m, new EmeraldGolemModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LAPIS_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new LapisGolemModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(OBSIDIAN_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ObsidianGolemModel<>(), 0.8F));
        RenderingRegistry.registerEntityRenderingHandler(COAL_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new CoalGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(REDSTONE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new RedstoneGolemModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(CLAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new ClayGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(DIRT_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(GRASS_GOLEM.get(), m -> new GrassGolemRenderer<>(m, new DirtGolemModel<>(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(HAY_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new HayGolemModel<>(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(PATH_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new DirtGolemModel<>(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new ClayGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(QUARTZ_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new QuartzGolemModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(BONE_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new BoneGolemModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(RED_NETHER_BRICK_GOLEM.get(), m -> new HeavyGolemRenderer<>(m, new NetherBrickGolemModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(ICE_GOLEM.get(), IceGolemTransparentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PACKED_ICE_GOLEM.get(), IceGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BLUE_ICE_GOLEM.get(), IceGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NETHERITE_GOLEM.get(), m -> new AbstractGolemRenderer<>(m, new NetheriteGolemModel<>(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(LAPIS_BULLET.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(REDSTONE_BULLET.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(THROWN_BRICK.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(THROWN_NETHER_BRICK.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(PACKED_ICEBALL.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(BLUE_ICEBALL.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GOLD_GOLEM.get(), GoldGolemEntity.registerAttributes().build());
        event.put(DIAMOND_GOLEM.get(), DiamondGolemEntity.registerAttributes().build());
        event.put(EMERALD_GOLEM.get(), EmeraldGolemEntity.registerAttributes().build());
        event.put(LAPIS_GOLEM.get(), LapisGolemEntity.registerAttributes().build());
        event.put(OBSIDIAN_GOLEM.get(), ObsidianGolemEntity.registerAttributes().build());
        event.put(COAL_GOLEM.get(), CoalGolemEntity.registerAttributes().build());
        event.put(REDSTONE_GOLEM.get(), RedstoneGolemEntity.registerAttributes().build());
        event.put(CLAY_GOLEM.get(), ClayGolemEntity.registerAttributes().build());
        event.put(DIRT_GOLEM.get(), DirtGolemEntity.registerAttributes().build());
        event.put(GRASS_GOLEM.get(), GrassGolemEntity.registerAttributes().build());
        event.put(HAY_GOLEM.get(), HayGolemEntity.registerAttributes().build());
        event.put(PATH_GOLEM.get(), PathGolemEntity.registerAttributes().build());
        event.put(BRICK_GOLEM.get(), BrickGolemEntity.registerAttributes().build());
        event.put(QUARTZ_GOLEM.get(), QuartzGolemEntity.registerAttributes().build());
        event.put(BONE_GOLEM.get(), BoneGolemEntity.registerAttributes().build());
        event.put(NETHER_BRICK_GOLEM.get(), NetherBrickGolemEntity.registerAttributes().build());
        event.put(RED_NETHER_BRICK_GOLEM.get(), RedNetherBrickGolemEntity.registerAttributes().build());
        event.put(ICE_GOLEM.get(), IceGolemEntity.registerAttributes().build());
        event.put(PACKED_ICE_GOLEM.get(), IceGolemEntity.registerAttributes().build());
        event.put(BLUE_ICE_GOLEM.get(), IceGolemEntity.registerAttributes().build());
        event.put(NETHERITE_GOLEM.get(), NetheriteGolemEntity.registerAttributes().build());
    }
}
