package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GrassGolem extends DirtGolem {

    protected static final EntityDataAccessor<Boolean> HAS_POPPY = SynchedEntityData.defineId(GrassGolem.class, EntityDataSerializers.BOOLEAN);

    public GrassGolem(EntityType<? extends GrassGolem> entity, Level world) {
        super(entity, world);
    }

    //TODO: Can I throw my poppy?
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (target) ->
                target instanceof Enemy && !(target instanceof Creeper)));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
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
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("has_poppy", this.hasPoppy());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
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

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (itemstack.is(Items.POPPY) && !this.hasPoppy()) {
            setPoppy(true);
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        } else if (item instanceof ShovelItem) {
            PathGolem path = ModEntities.PATH_GOLEM.get().create(this.level);
            addFreshEntity(path);
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
            this.level.playSound(null, this.blockPosition(), SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else if (item instanceof HoeItem) {
            DirtGolem dirt = ModEntities.DIRT_GOLEM.get().create(this.level);
            addFreshEntity(dirt);
            itemstack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
            this.level.playSound(null, this.blockPosition(), SoundEvents.GRAVEL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        } else {
            return this.repairGolem(player, hand);
        }

        return InteractionResult.PASS;
    }

    @Override
    protected boolean canRepair(ItemStack stack) {
        return stack.is(Items.GRASS_BLOCK);
    }

    @Override
    protected void dropAllDeathLoot(DamageSource source) {
        super.dropAllDeathLoot(source);
        if (hasPoppy()) {
            spawnAtLocation(new ItemStack(Blocks.POPPY), 1);
        }
    }

    private void addFreshEntity(Mob entity) {
        if (this.hasPoppy()) {
            this.spawnAtLocation(new ItemStack(Items.POPPY));
        }

        if (!this.level.isClientSide()) {
            entity.copyPosition(this);
            entity.finalizeSpawn((ServerLevel)this.level, this.level.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.CONVERSION, null, null);
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
            this.discard();
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.GRASS_STEP, 1.0F, 1.0F);
    }
}
