package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;

public class OrnamentalBreakingParticle extends BreakingItemParticle {

    protected OrnamentalBreakingParticle(ClientLevel world, double x, double y, double z, TextureAtlasSprite stack) {
        super(world, x, y, z, stack);
    }

    public static class LapisFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand) {
            return new OrnamentalBreakingParticle(world, x, y, z, getSprite(new ItemStackTemplate(Items.LAPIS_LAZULI), world, rand));
        }
    }

    public static class RedstoneFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand) {
            return new OrnamentalBreakingParticle(world, x, y, z, getSprite(new ItemStackTemplate(Items.REDSTONE), world, rand));
        }
    }

    public static class BrickFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand) {
            return new OrnamentalBreakingParticle(world, x, y, z, getSprite(new ItemStackTemplate(Items.BRICK), world, rand));
        }
    }

    public static class NetherBrickFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource rand) {
            return new OrnamentalBreakingParticle(world, x, y, z, getSprite(new ItemStackTemplate(Items.NETHER_BRICK), world, rand));
        }
    }
}