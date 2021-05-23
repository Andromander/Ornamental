package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.CoalGolemEntity;
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
        this.body.setPos(0.0F, 4.0F, 0.0F);
        this.body.addBox(-3.0F, 0.0F, -2.0F, 6, 9, 4, 0.0F);
        this.torso = new ModelRenderer(this, 19, 13);
        this.torso.setPos(0.0F, -2.0F, 0.0F);
        this.torso.addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5, 0.0F);
        this.armL = new ModelRenderer(this, 44, 0);
        this.armL.setPos(5.0F, -1.0F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 19);
        this.head.setPos(0.0F, -2.0F, -1.5F);
        this.head.addBox(-2.5F, -6.0F, -3.0F, 5, 6, 5, 0.0F);
        this.armR = new ModelRenderer(this, 52, 0);
        this.armR.setPos(-5.0F, -1.0F, 0.0F);
        this.armR.addBox(-2.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setPos(1.0F, 13.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -1.5F, 3, 11, 3, 0.0F);
        this.legR = new ModelRenderer(this, 12, 0);
        this.legR.setPos(-1.0F, 13.0F, 0.0F);
        this.legR.addBox(-3.0F, 0.0F, -1.5F, 3, 11, 3, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(head, torso, body, armL, armR, legL, legR);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isOnFire()) {
            armR.zRot = 2.3F;
            armL.zRot = -2.3F;
        } else {
            armR.zRot = 0.0F;
            armL.zRot = 0.0F;
            swingArms(limbSwing, limbSwingAmount);
        }
    }
}
