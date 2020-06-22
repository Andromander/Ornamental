package com.androsa.nifty.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class IceGolemEntity extends GolemEntity implements IShearable {
    private static final DataParameter<Boolean> PUMPKIN_EQUIPPED = EntityDataManager.createKey(IceGolemEntity.class, DataSerializers.BOOLEAN);
    protected boolean canMelt;

    public IceGolemEntity(EntityType<? extends IceGolemEntity> entity, World world) {
        super(entity, world);
        canMelt = true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, (target) ->
                target instanceof IMob));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2F);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(PUMPKIN_EQUIPPED, true);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putBoolean("Pumpkin", this.isPumpkinEquipped());
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        if (nbt.contains("Pumpkin")) {
            this.setPumpkinEquipped(nbt.getBoolean("Pumpkin"));
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY());
            int k = MathHelper.floor(this.getZ());

            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperatureCached(new BlockPos(i, j, k)) > 1.0F) {
                this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
            }
        }
    }

    public boolean isPumpkinEquipped() {
        return this.dataManager.get(PUMPKIN_EQUIPPED);
    }

    public void setPumpkinEquipped(boolean equipped) {
        this.dataManager.set(PUMPKIN_EQUIPPED, equipped);
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_GLASS_HIT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    @Override
    public boolean isShearable(ItemStack item, IWorldReader world, BlockPos pos) {
        return this.isPumpkinEquipped();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        this.setPumpkinEquipped(false);
        return new ArrayList<>();
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return 1.7F;
    }
}
