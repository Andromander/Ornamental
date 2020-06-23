package com.androsa.nifty.entity;

import com.androsa.nifty.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrassGolemEntity extends DirtGolemEntity {

    protected static final DataParameter<Boolean> HAS_POPPY = EntityDataManager.createKey(GrassGolemEntity.class, DataSerializers.BOOLEAN);

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

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HAS_POPPY, false);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putBoolean("has_poppy", this.hasPoppy());
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        this.setPoppy(nbt.getBoolean("has_poppy"));
    }

    public boolean hasPoppy() {
        return this.dataManager.get(HAS_POPPY);
    }

    public void setPoppy(boolean flag) {
        this.dataManager.set(HAS_POPPY, flag);
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

        if (item == Items.POPPY && !this.hasPoppy()) {
            setPoppy(true);
        } else if (item instanceof ShovelItem) {
            PathGolemEntity path = ModEntities.PATH_GOLEM.get().create(this.world);
            addEntity(path);
        } else if (item instanceof HoeItem) {
            DirtGolemEntity dirt = ModEntities.DIRT_GOLEM.get().create(this.world);
            addEntity(dirt);
        } else if (item == Items.GRASS_BLOCK) {
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
    protected void spawnDrops(DamageSource source) {
        super.spawnDrops(source);
        if (hasPoppy()) {
            entityDropItem(new ItemStack(Blocks.POPPY), 1);
        }
    }

    private void addEntity(MobEntity entity) {
        if (this.hasPoppy()) {
            this.entityDropItem(new ItemStack(Items.POPPY));
        }
        entity.copyLocationAndAnglesFrom(this);
        this.remove();
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.CONVERSION, null, null);
        entity.setNoAI(this.isAIDisabled());
        if (this.hasCustomName()) {
            entity.setCustomName(this.getCustomName());
            entity.setCustomNameVisible(this.isCustomNameVisible());
        }

        if (this.isNoDespawnRequired()) {
            entity.enablePersistence();
        }

        entity.setInvulnerable(this.isInvulnerable());
        this.world.addEntity(entity);
        //this.world.playEvent((PlayerEntity)null, 1026, new BlockPos(this), 0); TODO: Grass sound
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_GRASS_STEP, 1.0F, 1.0F);
    }
}
