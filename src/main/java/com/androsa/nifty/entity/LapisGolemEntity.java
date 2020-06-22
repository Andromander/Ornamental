package com.androsa.nifty.entity;

import com.androsa.nifty.entity.projectile.LapisBulletEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class LapisGolemEntity extends AbstractGolemEntity implements IRangedAttackMob {

    private static final DataParameter<Boolean> TARGETING = EntityDataManager.createKey(LapisGolemEntity.class, DataSerializers.BOOLEAN);

    public LapisGolemEntity(EntityType<? extends LapisGolemEntity> entity, World world) {
        super(entity, world);
        this.stepHeight = 1.0F;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.5D, 15, 20.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.7D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TARGETING, false);
    }

    public boolean isTargeting() {
        return dataManager.get(TARGETING);
    }

    public void setTargeting(boolean flag) {
        dataManager.set(TARGETING, flag);
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
    public boolean canAttack(EntityType<?> target) {
        return target != EntityType.PLAYER && super.canAttack(target);
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity target) {
        if (target == null) {
            setTargeting(false);
        } else {
            setTargeting(true);
        }
        super.setAttackTarget(target);
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
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item != Items.LAPIS_LAZULI) {
            return false;
        } else {
            float f = this.getHealth();
            this.heal(25.0F);
            if (this.getHealth() == f) {
                return false;
            } else {
                float f1 = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.field_226143_fP_, 1.0F, f1);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                return true;
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity entity, float multiplier) {
        LapisBulletEntity bullet = new LapisBulletEntity(this.world, this);
        double eye = entity.getEyeY() - (double)1.1F;
        double x = entity.getX() - this.getX();
        double y = eye - bullet.getY();
        double z = entity.getZ() - this.getZ();
        float f = MathHelper.sqrt(x * x + z * z) * 0.2F;

        bullet.shoot(x, y + (double)f, z, 1.6F, 12.0F);
        this.playSound(SoundEvents.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(bullet);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 2.1F;
    }
}
