package com.androsa.ornamental.entity;

import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.entity.projectile.PackedIceEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PackedIceGolemEntity extends IceGolemEntity implements IRangedAttackMob {

    public PackedIceGolemEntity(EntityType<? extends PackedIceGolemEntity> entity, World world) {
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

    //TODO: Slip (Attributes from Ice Golem)

    @Override
    public void performRangedAttack(LivingEntity entity, float multiplier) {
        PackedIceEntity snowballentity = new PackedIceEntity(ModEntities.PACKED_ICEBALL.get(), this.level, this);
        double d0 = entity.getEyeY() - (double)1.1F;
        double d1 = entity.getX() - this.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = entity.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowballentity);
    }
}
