package com.androsa.ornamental.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class ChargeSparkParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public ChargeSparkParticle(ClientLevel level, double xPos, double yPos, double zPos, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = sprites;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float level) {
        float f = ((float)this.age + level) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(level);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    public static class ChargeSparkFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public ChargeSparkFactory(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particle, ClientLevel level, double xPos, double yPos, double zPos, double xSpeed, double ySpeed, double zSpeed) {
            ChargeSparkParticle spark = new ChargeSparkParticle(level, xPos, yPos, zPos, 0.0D, 0.0D, 0.0D, this.sprite);
            spark.setParticleSpeed(xSpeed * 0.25D, ySpeed * 0.25D, zSpeed * 0.25D);
            spark.setLifetime(level.random.nextInt(2) + 2);
            return spark;
        }
    }
}
