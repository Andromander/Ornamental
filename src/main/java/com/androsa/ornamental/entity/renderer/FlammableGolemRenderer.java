package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.OrnamentalGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.androsa.ornamental.entity.model.renderstate.FlammableGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class FlammableGolemRenderer<T extends OrnamentalGolem, M extends AbstractGolemModel<FlammableGolemRenderState>> extends AbstractGolemRenderer<T, FlammableGolemRenderState, M> {

    public FlammableGolemRenderer(EntityRendererProvider.Context manager, M model, String texture, float shadow) {
        super(manager, model, texture, shadow, false);
    }

    @Override
    public FlammableGolemRenderState createRenderState() {
        return new FlammableGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, FlammableGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isOnFire = entity.isOnFire();
    }
}
