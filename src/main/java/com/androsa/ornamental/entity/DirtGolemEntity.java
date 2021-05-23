package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DirtGolemEntity extends AbstractGolemEntity {

    public DirtGolemEntity(EntityType<? extends DirtGolemEntity> entity, World world) {
        super(entity, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.8D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
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
    public boolean canAttackType(EntityType<?> target) {
        return target != EntityType.PLAYER && super.canAttackType(target);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GRAVEL_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GRAVEL_BREAK;
    }

    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 1.2F + 0.6F;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (item == Items.BONE_MEAL) {

            if (!this.level.isClientSide()) {
                GrassGolemEntity grass = ModEntities.GRASS_GOLEM.get().create(this.level);
                grass.copyPosition(this);
                this.remove();
                grass.finalizeSpawn((ServerWorld)this.level, this.level.getCurrentDifficultyAt(grass.blockPosition()), SpawnReason.CONVERSION, null, null);
                grass.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    grass.setCustomName(this.getCustomName());
                    grass.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    grass.setPersistenceRequired();
                }

                grass.setInvulnerable(this.isInvulnerable());
                this.level.addFreshEntity(grass);
            }

            if (!player.abilities.instabuild) {
                itemstack.shrink(1);
            }

            this.level.playSound(null, this.blockPosition(), SoundEvents.GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);

        } else if (item == Items.DIRT) {
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

        return ActionResultType.PASS;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.GRAVEL_STEP, 1.0F, 1.0F);
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 0.6F;
    }
}
