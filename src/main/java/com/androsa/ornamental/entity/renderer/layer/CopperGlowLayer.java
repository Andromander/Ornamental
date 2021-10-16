package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.CopperGolem;
import com.androsa.ornamental.entity.model.CopperGolemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CopperGlowLayer<T extends CopperGolem, M extends CopperGolemModel<T>> extends RenderLayer<T,M> {

    private static final RenderType GLOW_LAYER = RenderType.eyes(new ResourceLocation(OrnamentalMod.MODID, "textures/entity/copper_golem/copper_golem_charged_glow.png"));

    public CopperGlowLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    public void render(PoseStack stack, MultiBufferSource multibuffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        if (entity.isCharged()) {
            VertexConsumer vertexconsumer = multibuffer.getBuffer(GLOW_LAYER);
            this.getParentModel().renderToBuffer(stack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
