package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.androsa.ornamental.entity.model.renderstate.FlowerGolemRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class GrassFlowerLayer<T extends FlowerGolemRenderState, M extends DirtGolemModel<T>> extends RenderLayer<T, M> {

    public GrassFlowerLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack stack, SubmitNodeCollector buffer, int light, T entity, float v1, float v2) {
        if (!entity.isInvisible && entity.flower != null) {
            stack.pushPose();
            this.getParentModel().getHead().translateAndRotate(stack);
            stack.translate(0.0D, -0.43D, -0.05D);
            stack.mulPose(Axis.YP.rotationDegrees(-78.0F));
            stack.scale(-0.5F, -0.5F, 0.5F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            buffer.submitBlock(stack, entity.flower, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), entity.outlineColor);
            stack.popPose();
        }
    }
}
