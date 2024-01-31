package com.androsa.ornamental.entity.projectile;

import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

public class Brick extends ThrowableItemProjectile {

    public Brick(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    public Brick(EntityType<? extends Brick> type, Level world, LivingEntity entity) {
        super(type, entity, world);
    }

    protected ParticleOptions makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ModParticles.ITEM_BRICK.get() : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    public void handleEntityEvent(byte data) {
        if (data == 3) {
            ParticleOptions iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    @Nonnull
    protected Item getDefaultItem() {
        return Items.BRICK;
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), damage());
    }

    protected float damage() {
        return 3.0F;
    }
}
