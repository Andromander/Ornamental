package com.androsa.ornamental.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;

public class BlueIce extends PackedIce {

    public BlueIce(EntityType<? extends ThrowableItemProjectile> type, Level world) {
        super(type, world);
    }

    public BlueIce(EntityType<? extends ThrowableItemProjectile> type, Level world, LivingEntity entity) {
        super(type, world, entity);
    }

    protected int getAmplifier() {
        return 1;
    }
}
