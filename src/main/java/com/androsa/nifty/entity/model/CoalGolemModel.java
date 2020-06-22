package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.CoalGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * CoalGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CoalGolemModel<T extends CoalGolemEntity> extends AbstractGolemModel<T> {

    public CoalGolemModel() {
        super(64, 32, true);

        this.body = new ModelRenderer(this, 24, 0);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.addCuboid(-3.0F, 0.0F, -2.0F, 6, 9, 4, 0.0F);
        this.torso = new ModelRenderer(this, 19, 13);
        this.torso.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.torso.addCuboid(-5.0F, 0.0F, -2.5F, 10, 6, 5, 0.0F);
        this.armL = new ModelRenderer(this, 44, 0);
        this.armL.setRotationPoint(5.0F, -1.0F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 19);
        this.head.setRotationPoint(0.0F, -2.0F, -1.5F);
        this.head.addCuboid(-2.5F, -6.0F, -3.0F, 5, 6, 5, 0.0F);
        this.armR = new ModelRenderer(this, 52, 0);
        this.armR.setRotationPoint(-5.0F, -1.0F, 0.0F);
        this.armR.addCuboid(-2.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(1.0F, 13.0F, 0.0F);
        this.legL.addCuboid(0.0F, 0.0F, -1.5F, 3, 11, 3, 0.0F);
        this.legR = new ModelRenderer(this, 12, 0);
        this.legR.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.legR.addCuboid(-3.0F, 0.0F, -1.5F, 3, 11, 3, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(head, torso, body, armL, armR, legL, legR);
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isBurning()) {
            armR.rotateAngleZ = 2.3F;
            armL.rotateAngleZ = -2.3F;
        } else {
            armR.rotateAngleZ = 0.0F;
            armL.rotateAngleZ = 0.0F;
            swingArms(limbSwing, limbSwingAmount);
        }
    }
}
