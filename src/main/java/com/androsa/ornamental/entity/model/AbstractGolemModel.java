package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.model.renderstate.GolemRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/**
 * Abstract class holding basic parts: Head, Torso, Body, 2 Arms, and 2 Legs. These are only fields
 * Not all fields are required. Special cases can ignore certain fields
 */
public abstract class AbstractGolemModel<T extends GolemRenderState> extends EntityModel<T> {

    public ModelPart head;
    public ModelPart legL;
    public ModelPart legR;
    public ModelPart armL;
    public ModelPart armR;
    private boolean hasArms = false;
    private final boolean useTimer;

    public AbstractGolemModel(ModelPart root, boolean hasHead, boolean hasArms, boolean hasLegs, boolean useTimer) {
        super(root);
        if (hasHead) {
            this.head = root.getChild("head");
        }
        if (hasArms) {
            this.armL = root.getChild("arm_left");
            this.armR = root.getChild("arm_right");
            this.hasArms = true;
        }
        if (hasLegs) {
            this.legL = root.getChild("leg_left");
            this.legR = root.getChild("leg_right");
        }
        this.useTimer = useTimer;
    }

    @Override
    public void setupAnim(T entity) {
        super.setupAnim(entity);
        this.head.yRot = entity.yRot * ((float)Math.PI / 180F);
        this.head.xRot = entity.xRot * ((float)Math.PI / 180F);
        if (hasArms) {
            float attack = entity.attackTicksRemaining;
            if (attack > 0 && useTimer) {
                this.armR.xRot = -2.0F + 1.5F * Mth.triangleWave(attack, 10.0F);
                this.armL.xRot = -2.0F + 1.5F * Mth.triangleWave(attack, 10.0F);
            } else {
                swingArms(entity, entity.walkAnimationPos, entity.walkAnimationSpeed);
            }
        }
        this.legL.xRot = -1.5F * Mth.triangleWave(entity.walkAnimationPos, 13.0F) * entity.walkAnimationSpeed;
        this.legR.xRot = 1.5F * Mth.triangleWave(entity.walkAnimationPos, 13.0F) * entity.walkAnimationSpeed;
        this.legL.yRot = 0.0F;
        this.legR.yRot = 0.0F;
    }

    protected void swingArms(T entity, float limbSwing, float limbSwingAmount) {
        this.armR.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.armL.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
    }
}
