package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.NetheriteGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.EnumSet;

//VanillaCopy of GhastEntity.FireballAttackGoal, modified for Netherite Golem
public class ExplodingFireballAttackGoal extends Goal {
    private final NetheriteGolemEntity parentEntity;
    public int attackTimer;

    public ExplodingFireballAttackGoal(NetheriteGolemEntity entity) {
        this.parentEntity = entity;
        setMutexFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        return this.parentEntity.getAttackTarget() != null && this.parentEntity.getFireballs() > 0;
    }

    @Override
    public void startExecuting() {
        this.attackTimer = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.parentEntity.getAttackTarget();
        this.parentEntity.getLookController().setLookPositionWithEntity(target, 10.0F, (float)this.parentEntity.getVerticalFaceSpeed());
        if (target.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(target)) {
            World world = this.parentEntity.world;
            ++this.attackTimer;

            if (this.attackTimer == 20) {
                double x = target.getPosX() - this.parentEntity.getPosX();
                double y = target.getPosYHeight(0.5D) - (0.5D + this.parentEntity.getPosYHeight(0.5D));
                double z = target.getPosZ() - this.parentEntity.getPosZ();

                world.playEvent(null, Constants.WorldEvents.BLAZE_SHOOT_SOUND, parentEntity.getPosition(), 0);
                FireballEntity fireball = new FireballEntity(world, this.parentEntity, x, y, z);
                fireball.explosionPower = 2;
                fireball.setPosition(this.parentEntity.getPosX(), this.parentEntity.getPosYHeight(0.5D) + 0.5D, fireball.getPosZ());
                world.addEntity(fireball);
                parentEntity.shootFireball();
                this.attackTimer = -10;
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }
}
