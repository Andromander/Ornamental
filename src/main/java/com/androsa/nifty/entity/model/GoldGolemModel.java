package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.GoldGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * GoldGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class GoldGolemModel<T extends GoldGolemEntity> extends FlowerGolemModel<T> {

    public GoldGolemModel() {
        super(128, 64);

        this.armR = new ModelRenderer(this, 34, 0);
        this.armR.setRotationPoint(0.0F, -7.0F, 0.5F);
        this.armR.addBox(-12.0F, -2.5F, -3.0F, 4, 30, 4, 0.0F);

        this.legL = new ModelRenderer(this, 50, 0);
        this.legL.setRotationPoint(-2.5F, 9.0F, 0.0F);
        this.legL.addBox(-3.5F, -3.0F, -2.5F, 5, 18, 4, 0.0F);

        this.head = new ModelRenderer(this);
        this.head.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.head.setTextureOffset(91, 19).addBox(-3.5F, -12.0F, -5.5F, 7, 9, 7, 0.0F);
        this.head.setTextureOffset(68, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, 0.0F);

        this.torso = new ModelRenderer(this);
        this.torso.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.torso.setTextureOffset(68, 0).addBox(-8.0F, -2.0F, -5.0F, 16, 10, 9, 0.0F);
        this.torso.setTextureOffset(63, 19).addBox(-4.5F, 8.0F, -3.0F, 9, 7, 5, 0.5F);

        this.armL = new ModelRenderer(this, 0, 0);
        this.armL.setRotationPoint(0.0F, -7.0F, 0.4F);
        this.armL.addBox(8.0F, -2.5F, -3.0F, 4, 30, 4, 0.0F);

        this.legR = new ModelRenderer(this, 16, 0);
        this.legR.mirror = true;
        this.legR.setRotationPoint(4.5F, 9.0F, 0.0F);
        this.legR.addBox(-3.5F, -3.0F, -2.5F, 5, 18, 4, 0.0F);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                armR,
                legL,
                head,
                torso,
                armL,
                legR
        );
    }
}
