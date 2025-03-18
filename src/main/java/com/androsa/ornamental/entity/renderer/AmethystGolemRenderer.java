package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.AmethystGolem;
import com.androsa.ornamental.entity.model.AmethystGolemModel;
import com.androsa.ornamental.entity.model.renderstate.AmethystGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AmethystGolemRenderer extends AbstractGolemRenderer<AmethystGolem, AmethystGolemRenderState, AmethystGolemModel> {


    public AmethystGolemRenderer(EntityRendererProvider.Context manager, AmethystGolemModel model, float shadow) {
        super(manager, model, "amethyst_golem", shadow, false);
    }

    @Override
    public AmethystGolemRenderState createRenderState() {
        return new AmethystGolemRenderState();
    }

    @Override
    public void extractRenderState(AmethystGolem entity, AmethystGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isCasting = entity.isCasting();
    }
}
