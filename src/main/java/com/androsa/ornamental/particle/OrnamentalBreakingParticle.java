package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OrnamentalBreakingParticle extends BreakingItemParticle {

    protected OrnamentalBreakingParticle(ClientLevel world, double x, double y, double z, ItemStackRenderState stack) {
        super(world, x, y, z, stack);
    }

    public static class LapisFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, calculateState(new ItemStack(Items.LAPIS_LAZULI), world));
        }
    }

    public static class RedstoneFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, calculateState(new ItemStack(Items.REDSTONE), world));
        }
    }

    public static class BrickFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, calculateState(new ItemStack(Items.BRICK), world));
        }
    }

    public static class NetherBrickFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new OrnamentalBreakingParticle(world, x, y, z, calculateState(new ItemStack(Items.NETHER_BRICK), world));
        }
    }
}