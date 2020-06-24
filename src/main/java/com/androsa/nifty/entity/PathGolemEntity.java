package com.androsa.nifty.entity;

import com.androsa.nifty.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PathGolemEntity extends DirtGolemEntity {

    public PathGolemEntity(EntityType<? extends PathGolemEntity> entity, World world) {
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
                target instanceof IMob && !(target instanceof CreeperEntity)));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_GRASS_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GRASS_BREAK;
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (item instanceof HoeItem) {
            GrassGolemEntity grass = ModEntities.GRASS_GOLEM.get().create(this.world);
            grass.copyLocationAndAnglesFrom(this);
            this.remove();
            grass.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(grass)), SpawnReason.CONVERSION, null, null);
            grass.setNoAI(this.isAIDisabled());
            if (this.hasCustomName()) {
                grass.setCustomName(this.getCustomName());
                grass.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isNoDespawnRequired()) {
                grass.enablePersistence();
            }

            grass.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(grass);
            this.world.playSound(null, new BlockPos(this), SoundEvents.BLOCK_GRAVEL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        } else if (item == Items.GRASS_PATH)  {
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

        return false;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_GRASS_STEP, 1.0F, 1.0F);
    }
}