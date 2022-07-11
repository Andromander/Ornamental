package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.particle.CastingParticle;
import com.androsa.ornamental.particle.ChargeSparkParticle;
import com.androsa.ornamental.particle.OrnamentalBreakingParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OrnamentalMod.MODID);

    public static final RegistryObject<SimpleParticleType> ITEM_LAPIS = PARTICLE_TYPE.register("item_lapis", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_REDSTONE = PARTICLE_TYPE.register("item_redstone", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_BRICK = PARTICLE_TYPE.register("item_brick", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_NETHER_BRICK = PARTICLE_TYPE.register("item_nether_brick", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CHARGE_SPARK = PARTICLE_TYPE.register("charge_spark", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> AMETHYST_CAST = PARTICLE_TYPE.register("amethyst_cast", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(ITEM_LAPIS.get(), new OrnamentalBreakingParticle.LapisFactory());
        event.register(ITEM_REDSTONE.get(), new OrnamentalBreakingParticle.RedstoneFactory());
        event.register(ITEM_BRICK.get(), new OrnamentalBreakingParticle.BrickFactory());
        event.register(ITEM_NETHER_BRICK.get(), new OrnamentalBreakingParticle.NetherBrickFactory());

        event.register(CHARGE_SPARK.get(), ChargeSparkParticle.ChargeSparkFactory::new);
        event.register(AMETHYST_CAST.get(), CastingParticle.AmethystFactory::new);
    }
}
