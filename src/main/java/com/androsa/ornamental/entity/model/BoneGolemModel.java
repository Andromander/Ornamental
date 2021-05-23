package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.BoneGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * BoneGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class BoneGolemModel<T extends BoneGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer waist;
    public ModelRenderer spine;
    public ModelRenderer lowerLegL;
    public ModelRenderer toeL1;
    public ModelRenderer toeL2;
    public ModelRenderer toeL3;
    public ModelRenderer lowerLegR;
    public ModelRenderer toeR1;
    public ModelRenderer toeR2;
    public ModelRenderer toeR3;
    public ModelRenderer upperArmL;
    public ModelRenderer lowerArmL;
    public ModelRenderer clawL1;
    public ModelRenderer clawL2;
    public ModelRenderer clawL3;
    public ModelRenderer clawL4;
    public ModelRenderer upperArmR;
    public ModelRenderer lowerArmR;
    public ModelRenderer clawR1;
    public ModelRenderer clawR2;
    public ModelRenderer clawR3;
    public ModelRenderer clawR4;

    public BoneGolemModel() {
        super(96, 64, false);

        this.lowerArmR = new ModelRenderer(this, 79, 34);
        this.lowerArmR.setPos(0.0F, 6.0F, 0.0F);
        this.lowerArmR.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.clawL3 = new ModelRenderer(this, 20, 31);
        this.clawL3.setPos(-1.5F, 8.0F, -1.5F);
        this.clawL3.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(clawL3, 0.0F, 0.7853981633974483F, 0.0F);
        this.clawL4 = new ModelRenderer(this, 26, 32);
        this.clawL4.setPos(0.0F, 8.0F, 1.5F);
        this.clawL4.addBox(-0.5F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.armR = new ModelRenderer(this, 73, 5);
        this.armR.setPos(-6.9F, -17.0F, 0.0F);
        this.armR.addBox(-5.0F, -1.5F, -2.5F, 5, 5, 5, 0.0F);
        this.toeR1 = new ModelRenderer(this, 20, 25);
        this.toeR1.setPos(1.2F, 14.0F, -2.0F);
        this.toeR1.addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(toeR1, 0.0F, -0.2617993877991494F, 0.0F);
        this.armL = new ModelRenderer(this, 58, 0);
        this.armL.setPos(6.9F, -17.0F, 0.0F);
        this.armL.addBox(0.0F, -1.5F, -2.5F, 5, 5, 5, 0.0F);
        this.clawR4 = new ModelRenderer(this, 50, 39);
        this.clawR4.setPos(0.0F, 8.0F, 1.5F);
        this.clawR4.addBox(-0.5F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.clawR3 = new ModelRenderer(this, 44, 35);
        this.clawR3.setPos(-1.5F, 8.0F, -1.5F);
        this.clawR3.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(clawR3, 0.0F, 0.7853981633974483F, 0.0F);
        this.toeL2 = new ModelRenderer(this, 33, 8);
        this.toeL2.setPos(-1.2F, 14.0F, -2.0F);
        this.toeL2.addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(toeL2, 0.0F, 0.2617993877991494F, 0.0F);
        this.clawL1 = new ModelRenderer(this, 88, 0);
        this.clawL1.setPos(1.5F, 8.0F, -1.5F);
        this.clawL1.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(clawL1, 0.0F, -0.7853981633974483F, 0.0F);
        this.waist = new ModelRenderer(this, 24, 0);
        this.waist.setPos(0.0F, -3.0F, 0.0F);
        this.waist.addBox(-4.5F, 0.0F, -2.0F, 9, 3, 4, 0.0F);
        this.torso = new ModelRenderer(this, 0, 13);
        this.torso.setPos(0.0F, -17.1F, 0.0F);
        this.torso.addBox(-7.0F, 0.0F, -2.5F, 14, 7, 5, 0.0F);
        this.spine = new ModelRenderer(this, 50, 0);
        this.spine.setPos(0.0F, -20.0F, 2.0F);
        this.spine.addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);
        this.upperArmR = new ModelRenderer(this, 71, 34);
        this.upperArmR.setPos(-2.5F, 3.5F, 0.0F);
        this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.clawR2 = new ModelRenderer(this, 38, 35);
        this.clawR2.setPos(0.0F, 8.0F, -1.5F);
        this.clawR2.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.upperArmL = new ModelRenderer(this, 42, 14);
        this.upperArmL.setPos(2.5F, 3.5F, 0.0F);
        this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.lowerLegL = new ModelRenderer(this, 75, 15);
        this.lowerLegL.setPos(1.5F, 10.0F, 0.0F);
        this.lowerLegL.addBox(-2.5F, 0.0F, -2.5F, 5, 14, 5, 0.0F);
        this.lowerLegR = new ModelRenderer(this, 0, 25);
        this.lowerLegR.setPos(-1.5F, 10.0F, 0.0F);
        this.lowerLegR.addBox(-2.5F, 0.0F, -2.5F, 5, 14, 5, 0.0F);
        this.toeR2 = new ModelRenderer(this, 29, 26);
        this.toeR2.setPos(-1.2F, 14.0F, -2.0F);
        this.toeR2.addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(toeR2, 0.0F, 0.2617993877991494F, 0.0F);
        this.clawL2 = new ModelRenderer(this, 48, 25);
        this.clawL2.setPos(0.0F, 8.0F, -1.5F);
        this.clawL2.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.toeL1 = new ModelRenderer(this, 24, 7);
        this.toeL1.setPos(1.2F, 14.0F, -2.0F);
        this.toeL1.addBox(-1.0F, -1.0F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(toeL1, 0.0F, -0.2617993877991494F, 0.0F);
        this.legR = new ModelRenderer(this, 12, 0);
        this.legR.setPos(-2.5F, 0.0F, 0.0F);
        this.legR.addBox(-3.0F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setPos(2.5F, 0.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.head = new ModelRenderer(this, 53, 15);
        this.head.setPos(0.0F, -19.0F, 0.0F);
        this.head.addBox(-3.0F, -6.0F, -3.0F, 6, 7, 5, 0.0F);
        this.clawR1 = new ModelRenderer(this, 32, 35);
        this.clawR1.setPos(1.5F, 8.0F, -1.5F);
        this.clawR1.addBox(-0.5F, 0.0F, -2.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(clawR1, 0.0F, -0.7853981633974483F, 0.0F);
        this.toeR3 = new ModelRenderer(this, 35, 26);
        this.toeR3.setPos(0.0F, 7.0F, 2.5F);
        this.toeR3.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 8, 0.0F);
        this.setRotateAngle(toeR3, -1.1344640137963142F, 0.0F, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 55, 27);
        this.lowerArmL.setPos(0.0F, 6.0F, 0.0F);
        this.lowerArmL.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.toeL3 = new ModelRenderer(this, 30, 17);
        this.toeL3.setPos(0.0F, 7.0F, 2.5F);
        this.toeL3.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 8, 0.0F);
        this.setRotateAngle(toeL3, -1.1344640137963142F, 0.0F, 0.0F);

        this.upperArmR.addChild(this.lowerArmR);
        this.lowerArmL.addChild(this.clawL3);
        this.lowerArmL.addChild(this.clawL4);
        this.lowerLegR.addChild(this.toeR1);
        this.lowerArmR.addChild(this.clawR4);
        this.lowerArmR.addChild(this.clawR3);
        this.lowerLegL.addChild(this.toeL2);
        this.lowerArmL.addChild(this.clawL1);
        this.armR.addChild(this.upperArmR);
        this.lowerArmR.addChild(this.clawR2);
        this.armL.addChild(this.upperArmL);
        this.legL.addChild(this.lowerLegL);
        this.legR.addChild(this.lowerLegR);
        this.lowerLegR.addChild(this.toeR2);
        this.lowerArmL.addChild(this.clawL2);
        this.lowerLegL.addChild(this.toeL1);
        this.lowerArmR.addChild(this.clawR1);
        this.lowerLegR.addChild(this.toeR3);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerLegL.addChild(this.toeL3);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isTargeting()) {
            this.armR.xRot = -1.5F;
            this.armL.xRot = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(head, armL, armR, torso, spine, waist, legL, legR);
    }
}
