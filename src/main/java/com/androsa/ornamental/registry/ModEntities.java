package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.*;
import com.androsa.ornamental.entity.projectile.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, OrnamentalMod.MODID);

    public static final RegistryObject<EntityType<GoldGolem>> GOLD_GOLEM = makeEntity("gold", GoldGolem::new, 1.3F, 2.7F, false);
    public static final RegistryObject<EntityType<DiamondGolem>> DIAMOND_GOLEM = makeEntity("diamond", DiamondGolem::new, 1.0F, 1.5F, false);
    public static final RegistryObject<EntityType<EmeraldGolem>> EMERALD_GOLEM = makeEntity("emerald", EmeraldGolem::new, 1.3F, 2.6F, false);
    public static final RegistryObject<EntityType<LapisGolem>> LAPIS_GOLEM = makeEntity("lapis", LapisGolem::new, 1.2F, 2.3F, false);
    public static final RegistryObject<EntityType<ObsidianGolem>> OBSIDIAN_GOLEM = makeEntity("obsidian", ObsidianGolem::new, 2.0F, 2.9F, true);
    public static final RegistryObject<EntityType<CoalGolem>> COAL_GOLEM = makeEntity("coal", CoalGolem::new, 0.9F, 2.0F, false);
    public static final RegistryObject<EntityType<RedstoneGolem>> REDSTONE_GOLEM = makeEntity("redstone", RedstoneGolem::new, 0.8F, 1.8F, false);
    public static final RegistryObject<EntityType<ClayGolem>> CLAY_GOLEM = makeEntity("clay", ClayGolem::new, 1.0F, 1.9F, false);
    public static final RegistryObject<EntityType<DirtGolem>> DIRT_GOLEM = makeEntity("dirt", DirtGolem::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<GrassGolem>> GRASS_GOLEM = makeEntity("grass", GrassGolem::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<HayGolem>> HAY_GOLEM = makeEntity("hay", HayGolem::new, 0.7F, 2.5F, false);
    public static final RegistryObject<EntityType<PathGolem>> PATH_GOLEM = makeEntity("dirt_path", PathGolem::new, 0.6F, 0.8F, false);
    public static final RegistryObject<EntityType<BrickGolem>> BRICK_GOLEM = makeEntity("brick", BrickGolem::new, 1.0F, 1.9F, false);
    public static final RegistryObject<EntityType<QuartzGolem>> QUARTZ_GOLEM = makeEntity("quartz", QuartzGolem::new, 1.2F, 2.2F, true);
    public static final RegistryObject<EntityType<BoneGolem>> BONE_GOLEM = makeEntity("bone", BoneGolem::new, 1.0F, 3.1F, false);
    public static final RegistryObject<EntityType<NetherBrickGolem>> NETHER_BRICK_GOLEM = makeEntity("nether_brick", NetherBrickGolem::new, 1.0F, 2.5F, true);
    public static final RegistryObject<EntityType<RedNetherBrickGolem>> RED_NETHER_BRICK_GOLEM = makeEntity("red_nether_brick", RedNetherBrickGolem::new, 1.0F, 2.5F, true);
    public static final RegistryObject<EntityType<IceGolem>> ICE_GOLEM = makeEntity("ice", IceGolem::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<PackedIceGolem>> PACKED_ICE_GOLEM = makeEntity("packed_ice", PackedIceGolem::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<BlueIceGolem>> BLUE_ICE_GOLEM = makeEntity("blue_ice", BlueIceGolem::new, 0.7F, 1.9F, false);
    public static final RegistryObject<EntityType<NetheriteGolem>> NETHERITE_GOLEM = makeEntity("netherite", NetheriteGolem::new, 1.8F, 3.1F, true);
    public static final RegistryObject<EntityType<CopperGolem>> COPPER_GOLEM = makeEntity("copper", CopperGolem::new, 1.5F, 3.5F, false);

    public static final RegistryObject<EntityType<LapisBullet>> LAPIS_BULLET = makeProjectile("lapis_bullet", LapisBullet::new, 0.25F, 0.25F, 4, 10);
    public static final RegistryObject<EntityType<RedstoneBullet>> REDSTONE_BULLET = makeProjectile("redstone_bullet", RedstoneBullet::new, 0.25F, 0.25F, 4, 10);
    public static final RegistryObject<EntityType<Brick>> THROWN_BRICK = makeProjectile("thrown_brick", Brick::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<NetherBrick>> THROWN_NETHER_BRICK = makeProjectile("thrown_nether_brick", NetherBrick::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<PackedIce>> PACKED_ICEBALL = makeProjectile("packed_iceball", PackedIce::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<BlueIce>> BLUE_ICEBALL = makeProjectile("blue_iceball", BlueIce::new, 0.25F, 0.25F, 150, 2);
    public static final RegistryObject<EntityType<ChargeBall>> CHARGE_BALL = makeProjectile("charge_ball", ChargeBall::new, 0.25F, 0.35F, 150, 2);

    private static <T extends Entity> RegistryObject<EntityType<T>> makeEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, boolean fireRes) {
        String regname = name + "_golem";
        EntityType.Builder<T> builder = EntityType.Builder.of(entity, MobCategory.MISC)
                .sized(width, height);
        if (fireRes) builder.fireImmune();

        return ENTITIES.register(regname, () -> builder.build(regname));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> makeProjectile(String name, EntityType.EntityFactory<T> entity, float width, float height, int tracking, int interval) {
        return ENTITIES.register(name, () ->
                EntityType.Builder.of(entity, MobCategory.MISC)
                        .sized(width, height)
                        .clientTrackingRange(tracking)
                        .updateInterval(interval)
                        .build(name)
        );
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GOLD_GOLEM.get(), GoldGolem.registerAttributes().build());
        event.put(DIAMOND_GOLEM.get(), DiamondGolem.registerAttributes().build());
        event.put(EMERALD_GOLEM.get(), EmeraldGolem.registerAttributes().build());
        event.put(LAPIS_GOLEM.get(), LapisGolem.registerAttributes().build());
        event.put(OBSIDIAN_GOLEM.get(), ObsidianGolem.registerAttributes().build());
        event.put(COAL_GOLEM.get(), CoalGolem.registerAttributes().build());
        event.put(REDSTONE_GOLEM.get(), RedstoneGolem.registerAttributes().build());
        event.put(CLAY_GOLEM.get(), ClayGolem.registerAttributes().build());
        event.put(DIRT_GOLEM.get(), DirtGolem.registerAttributes().build());
        event.put(GRASS_GOLEM.get(), GrassGolem.registerAttributes().build());
        event.put(HAY_GOLEM.get(), HayGolem.registerAttributes().build());
        event.put(PATH_GOLEM.get(), PathGolem.registerAttributes().build());
        event.put(BRICK_GOLEM.get(), BrickGolem.registerAttributes().build());
        event.put(QUARTZ_GOLEM.get(), QuartzGolem.registerAttributes().build());
        event.put(BONE_GOLEM.get(), BoneGolem.registerAttributes().build());
        event.put(NETHER_BRICK_GOLEM.get(), NetherBrickGolem.registerAttributes().build());
        event.put(RED_NETHER_BRICK_GOLEM.get(), RedNetherBrickGolem.registerAttributes().build());
        event.put(ICE_GOLEM.get(), IceGolem.registerAttributes().build());
        event.put(PACKED_ICE_GOLEM.get(), IceGolem.registerAttributes().build());
        event.put(BLUE_ICE_GOLEM.get(), IceGolem.registerAttributes().build());
        event.put(NETHERITE_GOLEM.get(), NetheriteGolem.registerAttributes().build());
        event.put(COPPER_GOLEM.get(), CopperGolem.registerAttributes().build());
    }
}
