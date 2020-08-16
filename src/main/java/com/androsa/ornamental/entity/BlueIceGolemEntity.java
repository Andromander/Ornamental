package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.entity.projectile.BlueIceEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlueIceGolemEntity extends IceGolemEntity implements IRangedAttackMob {

    public BlueIceGolemEntity(EntityType<? extends BlueIceGolemEntity> entity, World world) {
        super(entity, world);
        canMelt = false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, (target) ->
                target instanceof IMob));
    }

    //TODO: Slip slip (Attributes from Ice Golem)

    @Override
    public void attackEntityWithRangedAttack(LivingEntity entity, float multiplier) {
        BlueIceEntity projectile = new BlueIceEntity(ModEntities.BLUE_ICEBALL.get(), this.world, this);
        double d0 = entity.getPosYEye() - (double)1.1F;
        double d1 = entity.getPosX() - this.getPosX();
        double d2 = d0 - projectile.getPosY();
        double d3 = entity.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        projectile.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(projectile);
    }
}
