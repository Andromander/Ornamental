package com.androsa.ornamental.entity;

import com.androsa.ornamental.entity.task.ExplodingFireballAttackGoal;
import com.androsa.ornamental.entity.task.NextMeleeAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NetheriteGolemEntity extends AbstractGolemEntity {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.defineId(NetheriteGolemEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> FIREBALLS = EntityDataManager.defineId(NetheriteGolemEntity.class, DataSerializers.INT);

    private int rechargeTimer = 1200;

    public NetheriteGolemEntity(EntityType<? extends NetheriteGolemEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ExplodingFireballAttackGoal(this));
        this.goalSelector.addGoal(2, new NextMeleeAttackGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.4F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, false, false));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 180.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TARGETING, false);
        this.entityData.define(FIREBALLS, 6);
    }

    public boolean isTargeting() {
        return entityData.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        entityData.set(TARGETING, flag);
    }

    public int getFireballs() {
        return entityData.get(FIREBALLS);
    }

    public void addFireball() {
        if (getFireballs() < 6)
            entityData.set(FIREBALLS, getFireballs() + 1);
    }

    public void shootFireball() {
        if (getFireballs() > 0) {
            entityData.set(FIREBALLS, getFireballs() - 1);
        }
    }

    public void setFireballs(int count) {
        if (count > 6)
            count = 6;
        entityData.set(FIREBALLS, count);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Fireballs", this.getFireballs());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setFireballs(compound.getInt("Fireballs"));
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void doPush(Entity target) {
        if (target instanceof IMob && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)target);
        }

        super.doPush(target);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        setTargeting(target != null);
        super.setTarget(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.PLAYER || target.getCategory() != EntityClassification.MISC;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float damage = this.getAttackDamage();
        float multiplier = damage > 0.0F ? damage / 2.0F + (float)this.random.nextInt((int)damage) : 0.0F;
        boolean flag = target.hurt(DamageSource.mobAttack(this), multiplier);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.5F, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        //Recharged. Add a fireball
        if (rechargeTimer < 0) {
            addFireball();
            rechargeTimer = 1200;
        } else {
            if (getFireballs() < 6) {
                if (!isTargeting()) {
                    rechargeTimer -= 2;
                } else {
                    rechargeTimer -= 1;
                }
            }
        }
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.NETHERITE_INGOT) {
            return ActionResultType.PASS;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return ActionResultType.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.abilities.instabuild) {
                    itemstack.shrink(1);
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.0F;
    }

    @Override
    public boolean hurt(DamageSource source, float multiplier) {
        return source != DamageSource.WITHER && !source.isExplosion() && !source.isFire() && super.hurt(source, multiplier);
    }
}
