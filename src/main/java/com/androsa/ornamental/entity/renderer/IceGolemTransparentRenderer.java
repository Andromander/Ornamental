package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import com.androsa.ornamental.entity.renderer.layer.IceGolemHeadLayer;
import com.androsa.ornamental.registry.ModelLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class IceGolemTransparentRenderer<T extends IceGolem> extends IceGolemRenderer<T> {

    public static final ResourceLocation texIce = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem_body.png");
    public static final ResourceLocation texGolem = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem.png");

    public IceGolemTransparentRenderer(EntityRendererProvider.Context manager) {
        super(manager, ModelLocations.ICE_GOLEM);
        this.addLayer(new IceGolemHeadLayer<>(this));
        this.addLayer(new IceGolemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texGolem;
    }

    class IceGolemLayer<I extends IceGolem> extends RenderLayer<I, SnowGolemModel<I>> {

        public IceGolemLayer(RenderLayerParent<I, SnowGolemModel<I>> renderer) {
            super(renderer);
        }

        public void render(PoseStack stack, MultiBufferSource buffer, int light, I entity, float v1, float v2, float v3, float v4, float v5, float v6) {
            VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucent(texIce));
            IceGolemTransparentRenderer.this.model.renderToBuffer(stack, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
