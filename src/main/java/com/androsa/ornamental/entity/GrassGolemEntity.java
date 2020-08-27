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

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.7D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
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

    //processInteract
    @Override
    protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();

        if (item == Items.POPPY && !this.hasPoppy()) {
            setPoppy(true);
            if (!player.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }
        } else if (item instanceof ShovelItem) {
            PathGolemEntity path = ModEntities.PATH_GOLEM.get().create(this.world);
            addEntity(path);
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
            this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        } else if (item instanceof HoeItem) {
            DirtGolemEntity dirt = ModEntities.DIRT_GOLEM.get().create(this.world);
            addEntity(dirt);
            itemstack.damageItem(1, player, (user) -> user.sendBreakAnimation(hand));
            this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_GRAVEL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        } else if (item == Items.GRASS_BLOCK) {
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

        return ActionResultType.PASS;
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

        if (!world.isRemote()) {
            entity.copyLocationAndAnglesFrom(this);
            this.remove();
            entity.onInitialSpawn((ServerWorld)this.world, this.world.getDifficultyForLocation(entity.getPosition()), SpawnReason.CONVERSION, null, null);
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
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.BLOCK_GRASS_STEP, 1.0F, 1.0F);
    }
}
