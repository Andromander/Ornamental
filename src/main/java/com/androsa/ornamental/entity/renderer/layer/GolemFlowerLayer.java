package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.model.FlowerGolemModel;
import com.androsa.ornamental.entity.model.renderstate.OfferingGolemRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class GolemFlowerLayer<T extends OfferingGolemRenderState, M extends FlowerGolemModel> extends RenderLayer<T, M> {

    public GolemFlowerLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack stack, SubmitNodeCollector buffer, int i, T entity, float v1, float v2) {
        double[] pos = entity.flowerPos;
        if (entity.offerFlowerTick != 0) {
            stack.pushPose();
            ModelPart part = this.getParentModel().getArmHoldingFlower();
            part.translateAndRotate(stack);
            stack.translate(pos[0], pos[1], pos[2]);
            stack.translate(0.5D, 0.5D, 0.5D);
            stack.scale(0.5F, 0.5F, 0.5F);
            stack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            stack.translate(-0.5D, -0.5D, -0.5D);
            buffer.submitBlock(stack, entity.flower, i, OverlayTexture.NO_OVERLAY, entity.outlineColor);
            stack.popPose();
        }
    }
}
