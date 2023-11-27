package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, OrnamentalMod.MODID);

    public static final Supplier<SimpleParticleType> ITEM_LAPIS = PARTICLE_TYPE.register("item_lapis", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> ITEM_REDSTONE = PARTICLE_TYPE.register("item_redstone", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> ITEM_BRICK = PARTICLE_TYPE.register("item_brick", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> ITEM_NETHER_BRICK = PARTICLE_TYPE.register("item_nether_brick", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> CHARGE_SPARK = PARTICLE_TYPE.register("charge_spark", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> AMETHYST_CAST = PARTICLE_TYPE.register("amethyst_cast", () -> new SimpleParticleType(false));
}
