package com.androsa.nifty.entity.model;

import com.androsa.nifty.entity.AbstractGolemEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * NetherBrickGolemModel - Androsa
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NetherBrickGolemModel<T extends AbstractGolemEntity> extends AbstractGolemModel<T> {
    public ModelRenderer forehead;
    public ModelRenderer hornLL;
    public ModelRenderer hornLR;
    public ModelRenderer hornUL;
    public ModelRenderer hornUR;

    public NetherBrickGolemModel() {
        super(64, 48, false);

        this.torso = new ModelRenderer(this, 26, 11);
        this.torso.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.torso.addCuboid(-6.0F, 0.0F, -3.0F, 12, 8, 6, 0.0F);
        this.armL = new ModelRenderer(this, 0, 17);
        this.armL.setRotationPoint(6.0F, -3.3F, 0.0F);
        this.armL.addCuboid(0.0F, 0.0F, -2.0F, 3, 16, 4, 0.0F);
        this.forehead = new ModelRenderer(this, 28, 33);
        this.forehead.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.forehead.addCuboid(-2.5F, -3.0F, -3.0F, 5, 3, 6, 0.0F);
        this.hornUR = new ModelRenderer(this, 0, 37);
        this.hornUR.setRotationPoint(0.0F, -8.0F, 1.0F);
        this.hornUR.addCuboid(-1.0F, 0.0F, 0.0F, 1, 2, 5, 0.0F);
        this.hornLR = new ModelRenderer(this, 54, 25);
        this.hornLR.setRotationPoint(-1.3F, -4.0F, -1.0F);
        this.hornLR.addCuboid(-1.0F, -8.0F, -1.0F, 1, 8, 2, 0.0F);
        this.setRotateAngle(hornLR, 0.7853981633974483F, 0.4363323129985824F, 0.0F);
        this.hornUL = new ModelRenderer(this, 50, 35);
        this.hornUL.setRotationPoint(0.0F, -8.0F, 1.0F);
        this.hornUL.addCuboid(0.0F, 0.0F, 0.0F, 1, 2, 5, 0.0F);
        this.legL = new ModelRenderer(this, 0, 0);
        this.legL.setRotationPoint(1.0F, 11.0F, 0.0F);
        this.legL.addCuboid(0.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.legR = new ModelRenderer(this, 16, 0);
        this.legR.setRotationPoint(-1.0F, 11.0F, 0.0F);
        this.legR.addCuboid(-4.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.armR = new ModelRenderer(this, 14, 21);
        this.armR.setRotationPoint(-6.0F, -3.3F, 0.0F);
        this.armR.addCuboid(-3.0F, 0.0F, -2.0F, 3, 16, 4, 0.0F);
        this.head = new ModelRenderer(this, 28, 25);
        this.head.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.head.addCuboid(-2.5F, -3.0F, -3.0F, 5, 3, 5, 0.0F);
        this.hornLL = new ModelRenderer(this, 48, 25);
        this.hornLL.setRotationPoint(1.3F, -4.0F, -1.0F);
        this.hornLL.addCuboid(0.0F, -8.0F, -1.0F, 1, 8, 2, 0.0F);
        this.setRotateAngle(hornLL, 0.7853981633974483F, -0.4363323129985824F, 0.0F);
        this.body = new ModelRenderer(this, 32, 0);
        this.body.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.body.addCuboid(-4.0F, 0.0F, -2.5F, 8, 6, 5, 0.0F);

        this.head.addChild(this.forehead);
        this.hornLR.addChild(this.hornUR);
        this.head.addChild(this.hornLR);
        this.hornLL.addChild(this.hornUL);
        this.head.addChild(this.hornLL);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(
                torso,
                armL,
                legL,
                legR,
                armR,
                head,
                body
        );
    }
}
