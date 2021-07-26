package com.androsa.ornamental.entity.projectile;

import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NetherBrick extends Brick {

    public NetherBrick(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    public NetherBrick(EntityType<? extends NetherBrick> type, Level world, LivingEntity entity) {
        super(type, world, entity);
    }

    @OnlyIn(Dist.CLIENT)
    protected ParticleOptions makeParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ModParticles.ITEM_NETHER_BRICK.get() : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.NETHER_BRICK;
    }

    @Override
    protected float damage() {
        return 5.0F;
    }
}
