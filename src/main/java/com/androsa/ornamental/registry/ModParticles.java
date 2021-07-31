package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.particle.OrnamentalBreakingParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OrnamentalMod.MODID);

    public static final RegistryObject<SimpleParticleType> ITEM_LAPIS = PARTICLE_TYPE.register("item_lapis", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_REDSTONE = PARTICLE_TYPE.register("item_redstone", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_BRICK = PARTICLE_TYPE.register("item_brick", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_NETHER_BRICK = PARTICLE_TYPE.register("item_nether_brick", () -> new SimpleParticleType(false));

    @OnlyIn(Dist.CLIENT)
    public static void registerClientParticles() {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;

        engine.register(ITEM_LAPIS.get(), new OrnamentalBreakingParticle.LapisFactory());
        engine.register(ITEM_REDSTONE.get(), new OrnamentalBreakingParticle.RedstoneFactory());
        engine.register(ITEM_BRICK.get(), new OrnamentalBreakingParticle.BrickFactory());
        engine.register(ITEM_NETHER_BRICK.get(), new OrnamentalBreakingParticle.NetherBrickFactory());
    }
}
