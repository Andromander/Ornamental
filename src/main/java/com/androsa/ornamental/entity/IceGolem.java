package com.androsa.ornamental.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class IceGolem extends AbstractGolem implements IForgeShearable {
    private static final EntityDataAccessor<Boolean> PUMPKIN_EQUIPPED = SynchedEntityData.defineId(IceGolem.class, EntityDataSerializers.BOOLEAN);
    protected boolean canMelt;

    public IceGolem(EntityType<? extends IceGolem> entity, Level world) {
        super(entity, world);
        canMelt = true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (target) ->
                target instanceof Enemy));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUMPKIN_EQUIPPED, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Pumpkin", this.isPumpkinEquipped());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Pumpkin")) {
            this.setPumpkinEquipped(nbt.getBoolean("Pumpkin"));
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            int i = Mth.floor(this.getX());
            int j = Mth.floor(this.getY());
            int k = Mth.floor(this.getZ());

            if (this.level.getBiome(new BlockPos(i, 0, k)).shouldSnowGolemBurn(new BlockPos(i, j, k))) {
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
    public boolean isShearable(ItemStack item, Level world, BlockPos pos) {
        return this.isPumpkinEquipped();
    }

    @Override
    public List<ItemStack> onSheared(Player player, ItemStack item, Level world, BlockPos pos, int fortune) {
        this.setPumpkinEquipped(false);
        return new ArrayList<>();
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.7F;
    }
}
