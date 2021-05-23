package com.androsa.ornamental.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class IceGolemEntity extends GolemEntity implements IForgeShearable {
    private static final DataParameter<Boolean> PUMPKIN_EQUIPPED = EntityDataManager.defineId(IceGolemEntity.class, DataSerializers.BOOLEAN);
    protected boolean canMelt;

    public IceGolemEntity(EntityType<? extends IceGolemEntity> entity, World world) {
        super(entity, world);
        canMelt = true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUMPKIN_EQUIPPED, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Pumpkin", this.isPumpkinEquipped());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Pumpkin")) {
            this.setPumpkinEquipped(nbt.getBoolean("Pumpkin"));
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY());
            int k = MathHelper.floor(this.getZ());

            if (this.level.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
                this.hurt(DamageSource.ON_FIRE, 1.0F);
            }
        }
    }

    public boolean isPumpkinEquipped() {
        return this.entityData.get(PUMPKIN_EQUIPPED);
    }

    public void setPumpkinEquipped(boolean equipped) {
        this.entityData.set(PUMPKIN_EQUIPPED, equipped);
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GLASS_HIT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.GLASS_BREAK;
    }

    @Override
    public boolean isShearable(ItemStack item, World world, BlockPos pos) {
        return this.isPumpkinEquipped();
    }

    @Override
    public List<ItemStack> onSheared(PlayerEntity player, ItemStack item, World world, BlockPos pos, int fortune) {
        this.setPumpkinEquipped(false);
        return new ArrayList<>();
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.7F;
    }
}
