package com.androsa.ornamental.entity.projectile;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ChargeBall extends AbstractHurtingProjectile {

    public ChargeBall(EntityType<? extends ChargeBall> entity, Level level) {
        super(entity, level);
    }

    public ChargeBall(Level level, LivingEntity owner, double x, double y, double z) {
        super(ModEntities.CHARGE_BALL.get(), owner, x, y, z, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.level.explode(null, getX(), getY(), getZ(), 1, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level.isClientSide) {
            Entity target = result.getEntity();
            Entity owner = getOwner();
            target.hurt(OrnamentalMod.shocked(this, owner), 5.0F);
            if (target instanceof LivingEntity) {
                ((LivingEntity) target).knockback(0.8F, Mth.sin(this.getYRot() * ((float)Math.PI / 180F)), -Mth.cos(this.getYRot() * ((float)Math.PI / 180F)));
            }
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ModParticles.CHARGE_SPARK.get();
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
