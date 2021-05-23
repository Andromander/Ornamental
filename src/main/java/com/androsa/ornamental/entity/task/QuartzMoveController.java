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
        if (this.operation == MovementController.Action.MOVE_TO) {
            Vector3d vec3d = new Vector3d(this.wantedX - parentEntity.getX(), this.wantedY - parentEntity.getY(), this.wantedZ - parentEntity.getZ());
            double d0 = vec3d.length();
            if (d0 < parentEntity.getBoundingBox().getSize()) {
                this.operation = MovementController.Action.WAIT;
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().scale(0.5D));
            } else {
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().add(vec3d.scale(this.speedModifier * 0.05D / d0)));
                if (parentEntity.getTarget() == null) {
                    Vector3d vec3d1 = parentEntity.getDeltaMovement();
                    parentEntity.yRot = -((float)MathHelper.atan2(vec3d1.x, vec3d1.z)) * (180F / (float)Math.PI);
                    parentEntity.yBodyRot = parentEntity.yRot;
                } else {
                    double d2 = parentEntity.getTarget().getX() - parentEntity.getX();
                    double d1 = parentEntity.getTarget().getZ() - parentEntity.getZ();
                    parentEntity.yRot = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                    parentEntity.yBodyRot = parentEntity.yRot;
                }
            }

        }
    }
}
