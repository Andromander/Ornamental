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

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.createKey(NetheriteGolemEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> FIREBALLS = EntityDataManager.createKey(NetheriteGolemEntity.class, DataSerializers.VARINT);

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
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 180.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TARGETING, false);
        this.dataManager.register(FIREBALLS, 6);
    }

    public boolean isTargeting() {
        return dataManager.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        dataManager.set(TARGETING, flag);
    }

    public int getFireballs() {
        return dataManager.get(FIREBALLS);
    }

    public void addFireball() {
        if (getFireballs() < 6)
            dataManager.set(FIREBALLS, getFireballs() + 1);
    }

    public void shootFireball() {
        if (getFireballs() > 0) {
            dataManager.set(FIREBALLS, getFireballs() - 1);
        }
    }

    public void setFireballs(int count) {
        if (count > 6)
            count = 6;
        dataManager.set(FIREBALLS, count);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Fireballs", this.getFireballs());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setFireballs(compound.getInt("Fireballs"));
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void collideWithEntity(Entity target) {
        if (target instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((LivingEntity)target);
        }

        super.collideWithEntity(target);
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity target) {
        setTargeting(target != null);
        super.setAttackTarget(target);
    }

    @Override
    public boolean canAttack(EntityType<?> target) {
        return target != EntityType.PLAYER || target.getClassification() != EntityClassification.MISC;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public boolean attackEntityAsMob(Entity target) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte)4);
        float damage = this.getAttackDamage();
        float multiplier = damage > 0.0F ? damage / 2.0F + (float)this.rand.nextInt((int)damage) : 0.0F;
        boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), multiplier);
        if (flag) {
            target.setMotion(target.getMotion().add(0.0D, (double)0.5F, 0.0D));
            this.applyEnchantments(this, target);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected float getSoundPitch() {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void livingTick() {
        super.livingTick();

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
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.NETHERITE_INGOT) {
            return ActionResultType.PASS;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return ActionResultType.PASS;
            } else {
                float f1 = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.ENTITY_IRON_GOLEM_REPAIR, 1.0F, f1);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return ActionResultType.func_233537_a_(this.world.isRemote);
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
    public boolean attackEntityFrom(DamageSource source, float multiplier) {
        return source != DamageSource.WITHER && !source.isExplosion() && !source.isFireDamage() && super.attackEntityFrom(source, multiplier);
    }
}
