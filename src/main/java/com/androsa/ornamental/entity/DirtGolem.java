package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DirtGolem extends OrnamentalGolem {

    public DirtGolem(EntityType<? extends DirtGolem> entity, Level world) {
        super(entity, world);
        this.targetCreeper = true;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.8D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
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
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 1.2F + 0.6F;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(Items.BONE_MEAL)) {

            if (!this.level.isClientSide()) {
                GrassGolem grass = ModEntities.GRASS_GOLEM.get().create(this.level);
                grass.copyPosition(this);
                grass.finalizeSpawn((ServerLevel)this.level, this.level.getCurrentDifficultyAt(grass.blockPosition()), MobSpawnType.CONVERSION, null, null);
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

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.discard();
            this.level.playSound(null, this.blockPosition(), SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

        } else {
            return this.repairGolem(player, hand);
        }

        return InteractionResult.PASS;
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.DIRT);
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
