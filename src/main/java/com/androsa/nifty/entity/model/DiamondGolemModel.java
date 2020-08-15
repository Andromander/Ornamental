package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.DiamondGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * DiamondGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class DiamondGolemModel<T extends DiamondGolemEntity> extends FlowerGolemModel<T> {

    public DiamondGolemModel() {
        super(64, 48);

        this.torso = new ModelRenderer(this, 26, 10);
        this.torso.setRotationPoint(0.0F, 8.0F, -0.5F);
        this.torso.addBox(-6.0F, 0.0F, -1.5F, 12, 7, 6, 0.0F);

        this.armL = new ModelRenderer(this, 0, 16);
        this.armL.setRotationPoint(6.0F, 8.5F, 1.0F);
        this.armL.addBox(0.0F, 0.0F, -1.5F, 3, 13, 3, 0.0F);

        this.legL = new ModelRenderer(this, 46, 0);
        this.legL.setRotationPoint(2.4F, 19.0F, 1.0F);
        this.legL.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);

        this.body = new ModelRenderer(this, 24, 0);
        this.body.setRotationPoint(0.0F, 15.0F, 1.0F);
        this.body.addBox(-3.5F, 0.0F, -2.0F, 7, 4, 4, 0.0F);

        this.head = new ModelRenderer(this);
        this.head.setRotationPoint(0.0F, 8.5F, -0.5F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -6.0F, 2, 4, 2, 0.0F);

        this.legR = new ModelRenderer(this, 24, 23);
        this.legR.setRotationPoint(-2.3F, 19.0F, 1.0F);
        this.legR.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);

        this.armR = new ModelRenderer(this, 12, 16);
        this.armR.setRotationPoint(-6.0F, 8.5F, 1.0F);
        this.armR.addBox(-3.0F, 0.0F, -1.5F, 3, 13, 3, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.torso,
                this.armL,
                this.legL,
                this.body,
                this.head,
                this.legR,
                this.armR
        );
    }
}
