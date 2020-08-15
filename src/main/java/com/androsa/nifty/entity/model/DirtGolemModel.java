package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.DirtGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * DirtGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class DirtGolemModel<T extends DirtGolemEntity> extends AbstractGolemModel<T> {

    public DirtGolemModel() {
        super(64, 32, true);

        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(0.5F, 21.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
        this.armR = new ModelRenderer(this, 52, 0);
        this.armR.setRotationPoint(-2.5F, 15.5F, 0.0F);
        this.armR.addBox(-2.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.legR = new ModelRenderer(this, 8, 0);
        this.legR.setRotationPoint(-0.5F, 21.0F, 0.0F);
        this.legR.addBox(-2.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
        this.armL = new ModelRenderer(this, 44, 0);
        this.armL.setRotationPoint(2.5F, 15.5F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.body = new ModelRenderer(this, 16, 0);
        this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -1.5F, 5, 6, 3, 0.0F);
        this.head = new ModelRenderer(this, 32, 0);
        this.head.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.head.addBox(-1.5F, -3.0F, -2.0F, 3, 3, 3, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(head, body, armL, armR, legL, legR);
    }

    public ModelRenderer getHead() {
        return head;
    }
}
