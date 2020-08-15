package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.ObsidianGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ObsidianGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ObsidianGolemModel<T extends ObsidianGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer chin;
    public ModelRenderer lowerArmL;
    public ModelRenderer lowerArmR;

    public ObsidianGolemModel() {
        super(96, 96, true);

        this.armR = new ModelRenderer(this, 64, 38);
        this.armR.setRotationPoint(-11.0F, -12.0F, 0.0F);
        this.armR.addBox(-6.0F, 0.0F, -3.5F, 6, 15, 7, 0.0F);
        this.armL = new ModelRenderer(this, 57, 16);
        this.armL.setRotationPoint(11.0F, -12.0F, 0.0F);
        this.armL.addBox(0.0F, 0.0F, -3.5F, 6, 15, 7, 0.0F);
        this.head = new ModelRenderer(this, 64, 0);
        this.head.setRotationPoint(0.0F, -13.0F, -4.0F);
        this.head.addBox(-4.0F, -9.0F, -4.5F, 8, 9, 7, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(1.5F, 12.0F, 0.0F);
        this.legL.addBox(0.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.torso = new ModelRenderer(this, 0, 40);
        this.torso.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.torso.addBox(-11.0F, 0.0F, -5.0F, 22, 13, 10, 0.0F);
        this.lowerArmL = new ModelRenderer(this, 0, 63);
        this.lowerArmL.setRotationPoint(3.0F, 15.0F, 0.0F);
        this.lowerArmL.addBox(-4.0F, 0.0F, -4.5F, 8, 11, 9, 0.0F);
        this.chin = new ModelRenderer(this, 59, 60);
        this.chin.setRotationPoint(0.0F, -2.0F, -5.5F);
        this.chin.addBox(-5.0F, 0.0F, 0.0F, 10, 3, 5, 0.0F);
        this.lowerArmR = new ModelRenderer(this, 34, 63);
        this.lowerArmR.setRotationPoint(-3.0F, 15.0F, 0.0F);
        this.lowerArmR.addBox(-4.0F, 0.0F, -4.5F, 8, 11, 9, 0.0F);
        this.legR = new ModelRenderer(this, 0, 0);
        this.legR.setRotationPoint(-1.5F, 12.0F, 0.0F);
        this.legR.addBox(-8.0F, 0.0F, -4.0F, 8, 12, 8, 0.0F);
        this.body = new ModelRenderer(this, 0, 20);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-7.5F, 0.0F, -4.0F, 15, 12, 8, 0.0F);

        this.armL.addChild(this.lowerArmL);
        this.head.addChild(this.chin);
        this.armR.addChild(this.lowerArmR);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                armR,
                armL,
                head,
                legL,
                torso,
                legR,
                body
        );
    }
}
