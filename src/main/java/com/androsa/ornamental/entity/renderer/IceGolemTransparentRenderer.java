package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.IceGolem;
import com.androsa.ornamental.registry.ModelLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class IceGolemTransparentRenderer<T extends IceGolem> extends IceGolemRenderer<T> {

    public static final Identifier texIce = Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/ice_golem_body.png");

    public IceGolemTransparentRenderer(EntityRendererProvider.Context manager) {
        super(manager, ModelLocations.ICE_GOLEM, "ice_golem");
        this.addLayer(new SnowGolemHeadLayer(this));
        this.addLayer(new IceGolemLayer<>(this));
    }

    class IceGolemLayer<I extends SnowGolemRenderState> extends RenderLayer<I, SnowGolemModel> {

        public IceGolemLayer(RenderLayerParent<I, SnowGolemModel> renderer) {
            super(renderer);
        }

        public void submit(PoseStack stack, SubmitNodeCollector buffer, int light, I entity, float v1, float v2) {
            buffer.order(1)
                    .submitModel(
                            IceGolemTransparentRenderer.this.model,
                            entity,
                            stack,
                            RenderTypes.entityTranslucent(texIce),
                            light,
                            OverlayTexture.NO_OVERLAY,
                            -1,
                            null,
                            entity.outlineColor,
                            null
                    );
        }
    }
}
