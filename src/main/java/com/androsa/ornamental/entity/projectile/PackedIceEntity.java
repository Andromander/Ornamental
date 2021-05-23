package com.androsa.ornamental.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class PackedIceEntity extends ProjectileItemEntity {

    public PackedIceEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
        super(type, world);
    }

    public PackedIceEntity(EntityType<? extends ProjectileItemEntity> type, World world, LivingEntity entity) {
        super(type, entity, world);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte data) {
        if (data == 3) {
            IParticleData iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            int i = entity instanceof BlazeEntity ? 5 : 2;
            entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20 * 5, getAmplifier()));
            }
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }

    protected int getAmplifier() {
        return 0;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
