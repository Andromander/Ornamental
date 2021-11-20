package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import com.androsa.ornamental.entity.renderer.layer.IceGolemHeadLayer;
import com.androsa.ornamental.registry.EntityRendering;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IceGolemTransparentRenderer<T extends IceGolem> extends IceGolemRenderer<T> {

    public static final ResourceLocation texIce = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem_body.png");
    public static final ResourceLocation texGolem = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem.png");

    public IceGolemTransparentRenderer(EntityRendererProvider.Context manager) {
        super(manager, EntityRendering.ICE_GOLEM);
        this.addLayer(new IceGolemHeadLayer<>(this));
        this.addLayer(new IceGolemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texGolem;
    }

    @OnlyIn(Dist.CLIENT)
    class IceGolemLayer<T extends IceGolem> extends RenderLayer<T, SnowGolemModel<T>> {

        public IceGolemLayer(RenderLayerParent<T, SnowGolemModel<T>> renderer) {
            super(renderer);
        }

        public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
            VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucent(texIce));
            IceGolemTransparentRenderer.this.model.renderToBuffer(stack, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
