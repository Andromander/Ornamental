package com.androsa.ornamental.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class PackedIce extends ThrowableItemProjectile {

    public PackedIce(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    public PackedIce(EntityType<? extends ThrowableItemProjectile> type, Level world, LivingEntity entity) {
        super(type, entity, world);
    }

    @Override
    @Nonnull
    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    private ParticleOptions makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
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
        int i = entity instanceof Blaze ? 5 : 2;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 5, getAmplifier()));
        }
    }

    protected int getAmplifier() {
        return 0;
    }

    @Override
    public void sendPairingData(ServerPlayer serverPlayer, Consumer<CustomPacketPayload> bundleBuilder) {
        super.sendPairingData(serverPlayer, bundleBuilder);
    }
}
