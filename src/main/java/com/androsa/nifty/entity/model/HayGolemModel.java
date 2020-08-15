package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.HayGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * HayGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class HayGolemModel<T extends HayGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer skirt;

    public HayGolemModel() {
        super(64, 48, false);

        this.body = new ModelRenderer(this, 24, 13);
        this.body.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5, 0.0F);
        this.skirt = new ModelRenderer(this, 24, 0);
        this.skirt.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.skirt.addBox(-3.5F, 0.0F, -3.5F, 7, 6, 7, 0.0F);
        this.torso = new ModelRenderer(this, 0, 24);
        this.torso.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.torso.addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -2.0F, 2, 16, 4, 0.0F);
        this.legR = new ModelRenderer(this, 12, 0);
        this.legR.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.legR.addBox(-2.0F, 0.0F, -2.0F, 2, 16, 4, 0.0F);
        this.armL = new ModelRenderer(this, 30, 24);
        this.armL.setRotationPoint(5.0F, -8.0F, 0.0F);
        this.armL.addBox(0.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 35);
        this.head.setRotationPoint(0.0F, -10.0F, -1.0F);
        this.head.addBox(-2.5F, -6.0F, -3.0F, 5, 6, 5, 0.0F);
        this.armR = new ModelRenderer(this, 30, 30);
        this.armR.setRotationPoint(-5.0F, -8.0F, 0.0F);
        this.armR.addBox(-12.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity.isBurning()) {
            armR.rotateAngleZ = 0.7F;
            armL.rotateAngleZ = -0.7F;
        } else {
            armR.rotateAngleZ = 0.0F;
            armL.rotateAngleZ = 0.0F;
        }
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                body,
                skirt,
                torso,
                legL,
                legR,
                armL,
                head,
                armR
        );
    }
}
