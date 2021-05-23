package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.LapisGolemEntity;
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
        this.armL.setPos(6.5F, -4.5F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F);
        this.lowerArmR = new ModelRenderer(this, 0, 47);
        this.lowerArmR.setPos(-1.5F, 10.0F, 0.0F);
        this.lowerArmR.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.legR = new ModelRenderer(this, 28, 0);
        this.legR.setPos(-3.0F, 16.0F, 0.0F);
        this.legR.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 4.0F, 0.0F);
        this.body.addBox(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
        this.armR = new ModelRenderer(this, 0, 33);
        this.armR.setPos(-6.5F, -4.5F, 0.0F);
        this.armR.addBox(-3.0F, 0.0F, -2.0F, 3, 10, 4, 0.0F);
        this.legL = new ModelRenderer(this, 43, 8);
        this.legL.setPos(3.0F, 16.0F, 0.0F);
        this.legL.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.torso = new ModelRenderer(this, 0, 17);
        this.torso.setPos(0.0F, -5.0F, 0.0F);
        this.torso.addBox(-6.5F, 0.0F, -3.5F, 13, 9, 7, 0.0F);
        this.head = new ModelRenderer(this, 14, 33);
        this.head.setPos(0.0F, -4.8F, -3.0F);
        this.head.addBox(-3.5F, -8.0F, -2.5F, 7, 8, 6, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 40, 35);
        this.lowerArmL.setPos(1.5F, 10.0F, 0.0F);
        this.lowerArmL.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
        this.nose = new ModelRenderer(this, 48, 0);
        this.nose.setPos(0.0F, -3.0F, -2.5F);
        this.nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);

        this.armR.addChild(this.lowerArmR);
        this.armL.addChild(this.lowerArmL);
        this.head.addChild(this.nose);
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
