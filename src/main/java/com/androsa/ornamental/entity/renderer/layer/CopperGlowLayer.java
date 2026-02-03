package com.androsa.ornamental.entity.renderer.layer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.model.CopperGolemModel;
import com.androsa.ornamental.entity.model.renderstate.CopperGolemRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class CopperGlowLayer<T extends CopperGolemRenderState, M extends CopperGolemModel> extends RenderLayer<T, M> {

    private static final RenderType GLOW_LAYER = RenderTypes.eyes(Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, "textures/entity/copper_golem/copper_golem_charged_glow.png"));

    public CopperGlowLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    public void submit(PoseStack stack, SubmitNodeCollector collector, int light, CopperGolemRenderState entity, float v1, float v2) {
        if (entity.isCharged) {
            collector.order(1)
                    .submitModel(
                            this.getParentModel(),
                            entity,
                            stack,
                            GLOW_LAYER,
                            light,
                            OverlayTexture.NO_OVERLAY,
                            -1,
                            null,
                            entity.outlineColor,
                            null);
        }
    }
}
