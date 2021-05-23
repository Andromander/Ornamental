package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GrassGolemEntity extends DirtGolemEntity {

    protected static final DataParameter<Boolean> HAS_POPPY = EntityDataManager.defineId(GrassGolemEntity.class, DataSerializers.BOOLEAN);

    public GrassGolemEntity(EntityType<? extends GrassGolemEntity> entity, World world) {
        super(entity, world);
    }

    //TODO: Can I throw my poppy?
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (target) ->
                target instanceof IMob && !(target instanceof CreeperEntity)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_POPPY, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("has_poppy", this.hasPoppy());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setPoppy(nbt.getBoolean("has_poppy"));
    }

    public boolean hasPoppy() {
        return this.entityData.get(HAS_POPPY);
    }

    public void setPoppy(boolean flag) {
        this.entityData.set(HAS_POPPY, flag);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GRASS_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GRASS_BREAK;
    }

    //processInteract
    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (item == Items.POPPY && !this.hasPoppy()) {
            setPoppy(true);
            if (!player.abilities.instabuild) {
                itemstack.shrink(1);
            }
        } else if (item instanceof ShovelItem) {
            PathGolemEntity path = ModEntities.PATH_GOLEM.get().create(this.level);
            addFreshEntity(path);
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
            this.level.playSound(null, this.blockPosition(), SoundEvents.GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        } else if (item instanceof HoeItem) {
            DirtGolemEntity dirt = ModEntities.DIRT_GOLEM.get().create(this.level);
            addFreshEntity(dirt);
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
            this.level.playSound(null, this.blockPosition(), SoundEvents.GRAVEL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        } else if (item == Items.GRASS_BLOCK) {
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
    protected void dropAllDeathLoot(DamageSource source) {
        super.dropAllDeathLoot(source);
        if (hasPoppy()) {
            spawnAtLocation(new ItemStack(Blocks.POPPY), 1);
        }
    }

    private void addFreshEntity(MobEntity entity) {
        if (this.hasPoppy()) {
            this.spawnAtLocation(new ItemStack(Items.POPPY));
        }

        if (!this.level.isClientSide()) {
            entity.copyPosition(this);
            this.remove();
            entity.finalizeSpawn((ServerWorld)this.level, this.level.getCurrentDifficultyAt(entity.blockPosition()), SpawnReason.CONVERSION, null, null);
            entity.setNoAi(this.isNoAi());
            if (this.hasCustomName()) {
                entity.setCustomName(this.getCustomName());
                entity.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isPersistenceRequired()) {
                entity.setPersistenceRequired();
            }

            entity.setInvulnerable(this.isInvulnerable());
            this.level.addFreshEntity(entity);
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.GRASS_STEP, 1.0F, 1.0F);
    }
}
