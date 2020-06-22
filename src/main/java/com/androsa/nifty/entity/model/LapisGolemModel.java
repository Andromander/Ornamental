package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.LapisGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * LapisGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class LapisGolemModel<T extends LapisGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer lowerArmL;
    public ModelRenderer lowerArmR;
    public ModelRenderer nose;

    public LapisGolemModel() {
        super(64, 64, false);

        this.armL = new ModelRenderer(this, 40, 21);
        this.armL.setRotationPoint(6.5F, -4.5F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F);
        this.lowerArmR = new ModelRenderer(this, 0, 47);
        this.lowerArmR.setRotationPoint(-1.5F, 10.0F, 0.0F);
        this.lowerArmR.addCuboid(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.legR = new ModelRenderer(this, 28, 0);
        this.legR.setRotationPoint(-3.0F, 16.0F, 0.0F);
        this.legR.addCuboid(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.addCuboid(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
        this.armR = new ModelRenderer(this, 0, 33);
        this.armR.setRotationPoint(-6.5F, -4.5F, 0.0F);
        this.armR.addCuboid(-3.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F);
        this.legL = new ModelRenderer(this, 43, 8);
        this.legL.setRotationPoint(3.0F, 16.0F, 0.0F);
        this.legL.addCuboid(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.torso = new ModelRenderer(this, 0, 17);
        this.torso.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.torso.addCuboid(-6.5F, 0.0F, -3.5F, 13, 9, 7, 0.0F);
        this.head = new ModelRenderer(this, 14, 33);
        this.head.setRotationPoint(0.0F, -4.8F, -3.0F);
        this.head.addCuboid(-3.5F, -8.0F, -2.5F, 7, 8, 6, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 40, 35);
        this.lowerArmL.setRotationPoint(1.5F, 10.0F, 0.0F);
        this.lowerArmL.addCuboid(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.nose = new ModelRenderer(this, 48, 0);
        this.nose.setRotationPoint(0.0F, -3.0F, -2.5F);
        this.nose.addCuboid(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);

        this.armR.addChild(this.lowerArmR);
        this.armL.addChild(this.lowerArmL);
        this.head.addChild(this.nose);
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isTargeting()) {
            this.armR.rotateAngleX = -1.5F;
            this.armL.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        } else {
            swingArms(limbSwing, limbSwingAmount);
        }
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                head,
                torso,
                body,
                armL,
                armR,
                legL,
                legR
        );
    }
}
