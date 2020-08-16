package com.androsa.ornamental.entity.task;

import com.androsa.ornamental.entity.QuartzGolemEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

//VanillaCopy of VexEntity.MoveHelperController, modified for QuartzGolemEntity
public class QuartzMoveController extends MovementController {
    private final QuartzGolemEntity parentEntity;

    public QuartzMoveController(QuartzGolemEntity golem) {
        super(golem);
        this.parentEntity = golem;
    }

    public void tick() {
        if (this.action == MovementController.Action.MOVE_TO) {
            Vector3d vec3d = new Vector3d(this.posX - parentEntity.getPosX(), this.posY - parentEntity.getPosY(), this.posZ - parentEntity.getPosZ());
            double d0 = vec3d.length();
            if (d0 < parentEntity.getBoundingBox().getAverageEdgeLength()) {
                this.action = MovementController.Action.WAIT;
                parentEntity.setMotion(parentEntity.getMotion().scale(0.5D));
            } else {
                parentEntity.setMotion(parentEntity.getMotion().add(vec3d.scale(this.speed * 0.05D / d0)));
                if (parentEntity.getAttackTarget() == null) {
                    Vector3d vec3d1 = parentEntity.getMotion();
                    parentEntity.rotationYaw = -((float)MathHelper.atan2(vec3d1.x, vec3d1.z)) * (180F / (float)Math.PI);
                    parentEntity.renderYawOffset = parentEntity.rotationYaw;
                } else {
                    double d2 = parentEntity.getAttackTarget().getPosX() - parentEntity.getPosX();
                    double d1 = parentEntity.getAttackTarget().getPosZ() - parentEntity.getPosZ();
                    parentEntity.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                    parentEntity.renderYawOffset = parentEntity.rotationYaw;
                }
            }

        }
    }
}
