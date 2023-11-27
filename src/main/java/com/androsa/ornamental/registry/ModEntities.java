package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.*;
import com.androsa.ornamental.entity.projectile.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, OrnamentalMod.MODID);

    public static final Supplier<EntityType<GoldGolem>> GOLD_GOLEM = makeEntity("gold", GoldGolem::new, 1.3F, 2.7F, false, 16768040, 6502914);
    public static final Supplier<EntityType<DiamondGolem>> DIAMOND_GOLEM = makeEntity("diamond", DiamondGolem::new, 1.0F, 1.5F, false, 7991036, 4548228);
    public static final Supplier<EntityType<EmeraldGolem>> EMERALD_GOLEM = makeEntity("emerald", EmeraldGolem::new, 1.3F, 2.6F, false, 3728178, 4364136);
    public static final Supplier<EntityType<LapisGolem>> LAPIS_GOLEM = makeEntity("lapis", LapisGolem::new, 1.2F, 2.3F, false, 1983113, 2239809);
    public static final Supplier<EntityType<ObsidianGolem>> OBSIDIAN_GOLEM = makeEntity("obsidian", ObsidianGolem::new, 2.0F, 2.9F, true, 1577769, 1975353);
    public static final Supplier<EntityType<CoalGolem>> COAL_GOLEM = makeEntity("coal", CoalGolem::new, 0.9F, 2.0F, false, 1839891, 8275712);
    public static final Supplier<EntityType<RedstoneGolem>> REDSTONE_GOLEM = makeEntity("redstone", RedstoneGolem::new, 0.8F, 1.8F, false, 7285799, 14819374);
    public static final Supplier<EntityType<ClayGolem>> CLAY_GOLEM = makeEntity("clay", ClayGolem::new, 1.0F, 1.9F, false, 10594225, 5857907);
    public static final Supplier<EntityType<DirtGolem>> DIRT_GOLEM = makeEntity("dirt", DirtGolem::new, 0.6F, 0.8F, false, 7951674, 12158300);
    public static final Supplier<EntityType<GrassGolem>> GRASS_GOLEM = makeEntity("grass", GrassGolem::new, 0.6F, 0.8F, false, 7951674, 1676846);
    public static final Supplier<EntityType<HayGolem>> HAY_GOLEM = makeEntity("hay", HayGolem::new, 0.7F, 2.5F, false, 12890413, 7892529);
    public static final Supplier<EntityType<PathGolem>> PATH_GOLEM = makeEntity("dirt_path", PathGolem::new, 0.6F, 0.8F, false, 7951674, 12428896);
    public static final Supplier<EntityType<BrickGolem>> BRICK_GOLEM = makeEntity("brick", BrickGolem::new, 1.0F, 1.9F, false, 126428457, 10651261);
    public static final Supplier<EntityType<QuartzGolem>> QUARTZ_GOLEM = makeEntity("quartz", QuartzGolem::new, 1.2F, 2.2F, true, 15722722, 13382949);
    public static final Supplier<EntityType<BoneGolem>> BONE_GOLEM = makeEntity("bone", BoneGolem::new, 1.0F, 3.1F, false, 14736581, 10656085);
    public static final Supplier<EntityType<NetherBrickGolem>> NETHER_BRICK_GOLEM = makeEntity("nether_brick", NetherBrickGolem::new, 1.0F, 2.5F, true, 4267300, 6181714);
    public static final Supplier<EntityType<RedNetherBrickGolem>> RED_NETHER_BRICK_GOLEM = makeEntity("red_nether_brick", RedNetherBrickGolem::new, 1.0F, 2.5F, true, 7017239, 6181714);
    public static final Supplier<EntityType<IceGolem>> ICE_GOLEM = makeEntity("ice", IceGolem::new, 0.7F, 1.9F, false, 9221118, 6325985);
    public static final Supplier<EntityType<PackedIceGolem>> PACKED_ICE_GOLEM = makeEntity("packed_ice", PackedIceGolem::new, 0.7F, 1.9F, false, 8168948, 4155339);
    public static final Supplier<EntityType<BlueIceGolem>> BLUE_ICE_GOLEM = makeEntity("blue_ice", BlueIceGolem::new, 0.7F, 1.9F, false, 7646206, 3301070);
    public static final Supplier<EntityType<NetheriteGolem>> NETHERITE_GOLEM = makeEntity("netherite", NetheriteGolem::new, 1.8F, 3.1F, true, 5065037, 3221802);
    public static final Supplier<EntityType<CopperGolem>> COPPER_GOLEM = makeEntity("copper", CopperGolem::new, 1.5F, 3.5F, false, 14056283, 5434879);
    public static final Supplier<EntityType<AmethystGolem>> AMETHYST_GOLEM = makeEntity("amethyst", AmethystGolem::new, 1.0F, 2.8F, false, 4472921, 13144304);
    public static final Supplier<EntityType<MagmaGolem>> MAGMA_GOLEM = makeEntity("magma", MagmaGolem::new, 0.9F, 2.5F, true, 16024866, 6629416);
    public static final Supplier<EntityType<CalciteGolem>> CALCITE_GOLEM = makeEntity("calcite", CalciteGolem::new, 1.0F, 3.0F, false, 15791604, 9473182);

    public static final Supplier<EntityType<LapisBullet>> LAPIS_BULLET = makeProjectile("lapis_bullet", LapisBullet::new, 0.25F, 0.25F, 4, 10);
    public static final Supplier<EntityType<RedstoneBullet>> REDSTONE_BULLET = makeProjectile("redstone_bullet", RedstoneBullet::new, 0.25F, 0.25F, 4, 10);
    public static final Supplier<EntityType<Brick>> THROWN_BRICK = makeProjectile("thrown_brick", Brick::new, 0.25F, 0.25F, 150, 2);
    public static final Supplier<EntityType<NetherBrick>> THROWN_NETHER_BRICK = makeProjectile("thrown_nether_brick", NetherBrick::new, 0.25F, 0.25F, 150, 2);
    public static final Supplier<EntityType<PackedIce>> PACKED_ICEBALL = makeProjectile("packed_iceball", PackedIce::new, 0.25F, 0.25F, 150, 2);
    public static final Supplier<EntityType<BlueIce>> BLUE_ICEBALL = makeProjectile("blue_iceball", BlueIce::new, 0.25F, 0.25F, 150, 2);
    public static final Supplier<EntityType<ChargeBall>> CHARGE_BALL = makeProjectile("charge_ball", ChargeBall::new, 0.25F, 0.35F, 150, 2);

    private static <T extends Mob> Supplier<EntityType<T>> makeEntity(String name, EntityType.EntityFactory<T> entity, float width, float height, boolean fireRes, int background, int highlight) {
        return makeEntity(name, entity, item -> makeEgg(item, background, highlight), width, height, fireRes);
    }

    private static <T extends Mob> Supplier<EntityType<T>> makeEntity(String name, EntityType.EntityFactory<T> entity, Function<Supplier<EntityType<T>>, Supplier<DeferredSpawnEggItem>> spawnegg, float width, float height, boolean fireRes) {
        String regname = name + "_golem";
        EntityType.Builder<T> builder = EntityType.Builder.of(entity, MobCategory.MISC).sized(width, height);
        if (fireRes) builder.fireImmune();
        Supplier<EntityType<T>> reg = ENTITIES.register(regname, () -> builder.build(regname));
        Supplier<Item> item = ModBlocks.ITEMS.register(regname + "_spawn_egg", spawnegg.apply(reg));
        ModCreativeTabs.SPAWN_EGGS.add(item);
        return reg;
    }

    private static <T extends Entity> Supplier<EntityType<T>> makeProjectile(String name, EntityType.EntityFactory<T> entity, float width, float height, int tracking, int interval) {
        return ENTITIES.register(name, () ->
                EntityType.Builder.of(entity, MobCategory.MISC)
                        .sized(width, height)
                        .clientTrackingRange(tracking)
                        .updateInterval(interval)
                        .build(name)
        );
    }

    private static Supplier<DeferredSpawnEggItem> makeEgg(Supplier<? extends EntityType<? extends Mob>> entity, int back, int fore) {
        return () -> new DeferredSpawnEggItem(entity, back, fore, new Item.Properties());
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
        event.put(AMETHYST_GOLEM.get(), AmethystGolem.registerAttributes().build());
        event.put(MAGMA_GOLEM.get(), MagmaGolem.registerAttributes().build());
        event.put(CALCITE_GOLEM.get(), CalciteGolem.registerAttributes().build());
    }
}
