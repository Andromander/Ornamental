package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class CastingParticle extends RisingParticle {

    private final SpriteSet set;

    protected CastingParticle(ClientLevel level, double x, double y, double z, double xVel, double yVel, double zVel, SpriteSet set) {
        super(level, x, y, z, xVel, yVel, zVel, set.first());
        this.set = set;
        this.setSpriteFromAge(set);
    }

    @Override
    public Particle scale(float amount) {
        return super.scale((this.random.nextFloat() * 0.5F) + 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(set);
    }

    @Override
    public Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class AmethystFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public AmethystFactory(SpriteSet set) {
            this.sprite = set;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double velX, double velY, double velZ, RandomSource rand) {
            CastingParticle particle = new CastingParticle(level, x, y, z, velX, velY, velZ, this.sprite);
            particle.setAlpha(1.0F);
            return particle;
        }
    }
}
