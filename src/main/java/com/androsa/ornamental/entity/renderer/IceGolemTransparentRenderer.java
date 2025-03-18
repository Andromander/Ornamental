package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import com.androsa.ornamental.registry.ModelLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class IceGolemTransparentRenderer<T extends IceGolem> extends IceGolemRenderer<T> {

    public static final ResourceLocation texIce = ResourceLocation.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/ice_golem_body.png");

    public IceGolemTransparentRenderer(EntityRendererProvider.Context manager) {
        super(manager, ModelLocations.ICE_GOLEM, "ice_golem");
        this.addLayer(new SnowGolemHeadLayer(this, manager.getBlockRenderDispatcher()));
        this.addLayer(new IceGolemLayer<>(this));
    }

    class IceGolemLayer<I extends SnowGolemRenderState> extends RenderLayer<I, SnowGolemModel> {

        public IceGolemLayer(RenderLayerParent<I, SnowGolemModel> renderer) {
            super(renderer);
        }

        public void render(PoseStack stack, MultiBufferSource buffer, int light, I entity, float v1, float v2) {
            VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucent(texIce));
            IceGolemTransparentRenderer.this.model.renderToBuffer(stack, vertex, light, OverlayTexture.NO_OVERLAY);
        }
    }
}
