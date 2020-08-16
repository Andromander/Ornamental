package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolemEntity;
import com.androsa.ornamental.entity.renderer.layer.IceGolemHeadLayer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceGolemTransparentRenderer<T extends IceGolemEntity> extends IceGolemRenderer<T> {

    public static final ResourceLocation texIce = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem_body.png");
    public static final ResourceLocation texGolem = new ResourceLocation(OrnamentalMod.MODID, "textures/entity/ice_golem.png");

    public IceGolemTransparentRenderer(EntityRendererManager manager) {
        super(manager);
        this.addLayer(new IceGolemHeadLayer<>(this));
        this.addLayer(new IceGolemLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return texGolem;
    }

    @OnlyIn(Dist.CLIENT)
    class IceGolemLayer<T extends IceGolemEntity> extends LayerRenderer<T, SnowManModel<T>> {

        public IceGolemLayer(IEntityRenderer<T, SnowManModel<T>> renderer) {
            super(renderer);
        }

        public void render(MatrixStack stack, IRenderTypeBuffer buffer, int light, T entity, float v1, float v2, float v3, float v4, float v5, float v6) {
            IVertexBuilder vertex = buffer.getBuffer(RenderType.getEntityTranslucent(texIce));
            IceGolemTransparentRenderer.this.entityModel.render(stack, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
