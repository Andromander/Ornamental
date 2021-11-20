package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.OrnamentalGolem;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Abstract class holding basic parts: Head, Torso, Body, 2 Arms, and 2 Legs. These are only fields
 * Not all fields are required. Special cases can ignore certain fields
 */
public abstract class AbstractGolemModel<T extends OrnamentalGolem> extends HierarchicalModel<T> {

    public final ModelPart root;
    public ModelPart head;
    public ModelPart legL;
    public ModelPart legR;
    public ModelPart armL;
    public ModelPart armR;
    private final boolean useTimer;

    public AbstractGolemModel(ModelPart root, boolean hasArms, boolean hasLegs, boolean useTimer) {
        this.root = root;
        this.head = root.getChild("head");
        if (hasArms) {
            this.armL = root.getChild("arm_left");
            this.armR = root.getChild("arm_right");
        }
        if (hasLegs) {
            this.legL = root.getChild("leg_left");
            this.legR = root.getChild("leg_right");
        }
        this.useTimer = useTimer;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.legL.xRot = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legR.xRot = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.legL.yRot = 0.0F;
        this.legR.yRot = 0.0F;
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        int attack = entity.getAttackTimer();
        if (attack > 0 && useTimer) {
            this.armR.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
            this.armL.xRot = -2.0F + 1.5F * this.triangleWave((float)attack - partialTicks, 10.0F);
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }

    protected void swingArms(float limbSwing, float limbSwingAmount) {
        this.armR.xRot = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
    }

    protected float triangleWave(float limbSwing, float amount) {
        return (Math.abs(limbSwing % amount - amount * 0.5F) - amount * 0.25F) / (amount * 0.25F);
    }
}
