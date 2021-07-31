package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OrnamentalBreakingParticle extends BreakingItemParticle {

    protected OrnamentalBreakingParticle(ClientLevel world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public static class LapisFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.LAPIS_LAZULI));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class RedstoneFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.REDSTONE));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class BrickFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.BRICK));
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class NetherBrickFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.NETHER_BRICK));
        }
    }
}