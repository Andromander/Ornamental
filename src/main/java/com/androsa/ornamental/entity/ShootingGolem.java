package com.androsa.ornamental.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class ShootingGolem extends OrnamentalGolem {

    private static final EntityDataAccessor<Boolean> TARGETING = SynchedEntityData.defineId(ShootingGolem.class, EntityDataSerializers.BOOLEAN);

    protected ShootingGolem(EntityType<? extends AbstractGolem> type, Level world) {
        super(type, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TARGETING, false);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Targeting", isTargeting());
    }

    @Override
    public void readAdditionalSaveData(ValueInput tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(TARGETING, tag.getBooleanOr("Targeting", false));
    }

    public boolean isTargeting() {
        return entityData.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        entityData.set(TARGETING, flag);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        this.setTargeting(target != null);
        super.setTarget(target);
    }
}
