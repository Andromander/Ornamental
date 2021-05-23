package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.particle.OrnamentalBreakingParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, OrnamentalMod.MODID);

    public static final RegistryObject<BasicParticleType> ITEM_LAPIS = PARTICLE_TYPE.register("item_lapis", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> ITEM_REDSTONE = PARTICLE_TYPE.register("item_redstone", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> ITEM_BRICK = PARTICLE_TYPE.register("item_brick", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> ITEM_NETHER_BRICK = PARTICLE_TYPE.register("item_nether_brick", () -> new BasicParticleType(false));

    @OnlyIn(Dist.CLIENT)
    public static void registerClientParticles() {
        ParticleManager manager = Minecraft.getInstance().particleEngine;

        manager.register(ITEM_LAPIS.get(), new OrnamentalBreakingParticle.LapisFactory());
        manager.register(ITEM_REDSTONE.get(), new OrnamentalBreakingParticle.RedstoneFactory());
        manager.register(ITEM_BRICK.get(), new OrnamentalBreakingParticle.BrickFactory());
        manager.register(ITEM_NETHER_BRICK.get(), new OrnamentalBreakingParticle.NetherBrickFactory());
    }
}
