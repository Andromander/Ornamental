package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

public class ClayGolemEntity extends AbstractGolemEntity {

    public ClayGolemEntity(EntityType<? extends ClayGolemEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob && !(target instanceof CreeperEntity)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    @Override
    protected int decreaseAirSupply(int time) {
        return time;
    }

    @Override
    protected void doPush(Entity target) {
        if (target instanceof IMob && !(target instanceof CreeperEntity) && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)target);
        }

        super.doPush(target);
    }

    @Override
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.CREEPER && target != EntityType.PLAYER && super.canAttackType(target);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        boolean flag = target.hurt(DamageSource.mobAttack(this), 0.0F);
        if (flag) {
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.3F, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }

        return flag;
    }

    @Override
    public void aiStep() {
        if (this.isOnFire()) {
            if (!this.level.isClientSide()) {
                BrickGolemEntity brick = ModEntities.BRICK_GOLEM.get().create(this.level);
                brick.copyPosition(this);
                this.remove();
                brick.finalizeSpawn((ServerWorld)this.level, this.level.getCurrentDifficultyAt(brick.blockPosition()), SpawnReason.CONVERSION, null, null);
                brick.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    brick.setCustomName(this.getCustomName());
                    brick.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    brick.setPersistenceRequired();
                }

                brick.setInvulnerable(this.isInvulnerable());
                this.level.addFreshEntity(brick);
            }

            this.level.levelEvent(null, Constants.WorldEvents.BLAZE_SHOOT_SOUND, this.blockPosition(), 0);
        }
        super.aiStep();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GRAVEL_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GRAVEL_BREAK;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (item != Items.CLAY_BALL) {
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
        this.playSound(SoundEvents.GRAVEL_STEP, 1.0F, 1.0F);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.9F;
    }
}
