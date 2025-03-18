package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.GrassGolem;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.androsa.ornamental.entity.model.renderstate.FlowerGolemRenderState;
import com.androsa.ornamental.entity.renderer.layer.GrassFlowerLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GrassGolemRenderer<T extends GrassGolem, M extends DirtGolemModel<FlowerGolemRenderState>> extends AbstractGolemRenderer<T, FlowerGolemRenderState,M> {

    public GrassGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, "grass_golem", shadow, false);
        this.addLayer(new GrassFlowerLayer<>(this));
    }

    @Override
    public FlowerGolemRenderState createRenderState() {
        return new FlowerGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, FlowerGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.flower = entity.getFlower();
    }
}
