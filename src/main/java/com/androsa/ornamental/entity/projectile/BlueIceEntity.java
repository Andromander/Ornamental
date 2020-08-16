package com.androsa.ornamental.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.world.World;

public class BlueIceEntity extends PackedIceEntity {

    public BlueIceEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
        super(type, world);
    }

    public BlueIceEntity(EntityType<? extends ProjectileItemEntity> type, World world, LivingEntity entity) {
        super(type, world, entity);
    }

    protected int getAmplifier() {
        return 1;
    }
}
