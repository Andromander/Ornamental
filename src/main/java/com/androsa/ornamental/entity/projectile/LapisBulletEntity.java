package com.androsa.ornamental.entity.projectile;

import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class LapisBulletEntity extends ProjectileItemEntity {

    public LapisBulletEntity(EntityType<? extends LapisBulletEntity> type, World world) {
        super(type, world);
    }

    public LapisBulletEntity(World world, LivingEntity entity) {
        super(ModEntities.LAPIS_BULLET.get(), entity, world);
    }

    @OnlyIn(Dist.CLIENT)
    protected IParticleData makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ModParticles.ITEM_LAPIS.get() : new ItemParticleData(ParticleTypes.ITEM, itemstack);
    }

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
    protected float getGravity() {
        return 0.01F;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.LAPIS_LAZULI;
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.hurt(DamageSource.thrown(this, this.getOwner()), 3.0F);
        }

        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
