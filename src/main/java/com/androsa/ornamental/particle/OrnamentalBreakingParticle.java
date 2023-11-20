package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OrnamentalBreakingParticle extends BreakingItemParticle {

    protected OrnamentalBreakingParticle(ClientLevel world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    public static class LapisFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.LAPIS_LAZULI));
        }
    }

    public static class RedstoneFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.REDSTONE));
        }
    }

    public static class BrickFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.BRICK));
        }
    }

    public static class NetherBrickFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, new ItemStack(Items.NETHER_BRICK));
        }
    }
}