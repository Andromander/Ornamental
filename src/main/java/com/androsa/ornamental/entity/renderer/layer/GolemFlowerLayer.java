package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.FlowerGolem;
import com.androsa.ornamental.entity.model.FlowerGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

public class GolemFlowerLayer<T extends FlowerGolem, M extends FlowerGolemModel<T>> extends RenderLayer<T, M> {

    public GolemFlowerLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int i, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        double[] pos = entity.getFlowerPos();
        if (entity.getHoldFlowerTick() != 0) {
            stack.pushPose();
            ModelPart part = this.getParentModel().getArmHoldingFlower();
            part.translateAndRotate(stack);
            stack.translate(pos[0], pos[1], pos[2]);
            stack.translate(0.5D, 0.5D, 0.5D);
            stack.scale(0.5F, 0.5F, 0.5F);
            stack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            stack.translate(-0.5D, -0.5D, -0.5D);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(entity.getFlower(), stack, buffer, i, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
            stack.popPose();
        }
    }
}
