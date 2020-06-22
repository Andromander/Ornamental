package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.EmeraldGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelIronGolem - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class EmeraldGolemModel<T extends EmeraldGolemEntity> extends FlowerGolemModel<T> {
    public ModelRenderer nose;

    public EmeraldGolemModel() {
        super(128, 64);

        this.nose = new ModelRenderer(this, 76, 0);
        this.nose.setRotationPoint(0.0F, -5.0F, -2.0F);
        this.nose.addCuboid(-1.0F, 0.0F, -5.5F, 2, 4, 2, 0.0F);
        this.legL = new ModelRenderer(this, 56, 0);
        this.legL.setRotationPoint(-4.0F, 13.0F, 0.5F);
        this.legL.addCuboid(-2.5F, -3.0F, -3.0F, 5, 14, 5, 0.0F);
        this.legR = new ModelRenderer(this, 18, 0);
        this.legR.mirror = true;
        this.legR.setRotationPoint(4.0F, 13.0F, 0.5F);
        this.legR.addCuboid(-2.5F, -3.0F, -3.0F, 5, 14, 5, 0.0F);
        this.torso = new ModelRenderer(this, 46, 27);
        this.torso.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.torso.addCuboid(-7.5F, 0.0F, -5.0F, 15, 10, 10, 0.0F);
        this.head = new ModelRenderer(this, 10, 23);
        this.head.setRotationPoint(0.0F, -5.0F, -2.0F);
        this.head.addCuboid(-4.0F, -12.0F, -5.5F, 8, 10, 8, 0.0F);
        this.armL = new ModelRenderer(this, 0, 0);
        this.armL.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.armL.addCuboid(7.5F, -2.5F, -3.0F, 4, 26, 5, 0.0F);
        this.armR = new ModelRenderer(this, 38, 0);
        this.armR.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.armR.addCuboid(-11.5F, -2.5F, -3.0F, 4, 26, 5, 0.0F);
        this.body = new ModelRenderer(this, 76, 0);
        this.body.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.body.addCuboid(-7.5F, -2.0F, -5.0F, 15, 17, 10, 0.0F);

        this.head.addChild(this.nose);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                this.legL,
                this.legR,
                this.torso,
                this.head,
                this.armL,
                this.armR,
                this.body
        );
    }
}
