package com.androsa.ornamental.entity.model;

import com.androsa.ornamental.entity.AbstractGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ClayGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ClayGolemModel<T extends AbstractGolemEntity> extends AbstractGolemModel<T> {

    public ClayGolemModel() {
        super(64, 48, true);

        this.torso = new ModelRenderer(this, 27, 13);
        this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.torso.addBox(-5.0F, 0.0F, -2.5F, 10, 6, 5, 0.0F);
        this.armL = new ModelRenderer(this, 0, 14);
        this.armL.setRotationPoint(5.0F, 0.5F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.armR = new ModelRenderer(this, 12, 14);
        this.armR.setRotationPoint(-5.0F, 0.5F, 0.0F);
        this.armR.addBox(-3.0F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.head = new ModelRenderer(this, 19, 24);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -6.0F, -4.0F, 5, 6, 5, 0.0F);
        this.legR = new ModelRenderer(this, 16, 0);
        this.legR.setRotationPoint(-0.5F, 14.0F, 0.0F);
        this.legR.addBox(-4.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(0.5F, 14.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.body = new ModelRenderer(this, 32, 0);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.5F, 8, 8, 5, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(head, torso, armL, armR, body, legL, legR);
    }
}
