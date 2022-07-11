package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.entity.GrassGolem;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.ModelData;

public class GrassFlowerLayer<T extends GrassGolem, M extends DirtGolemModel<T>> extends RenderLayer<T, M> {

    public GrassFlowerLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        if (!entity.isInvisible() && entity.hasPoppy()) {
            stack.pushPose();
            this.getParentModel().getHead().translateAndRotate(stack);
            stack.translate(0.0D, -0.43D, -0.05D);
            stack.mulPose(Vector3f.YP.rotationDegrees(-78.0F));
            stack.scale(-0.5F, -0.5F, 0.5F);
            stack.translate(-0.5D, -0.5D, -0.5D);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.POPPY.defaultBlockState(), stack, buffer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), ModelData.EMPTY, null);
            stack.popPose();
        }
    }
}
