package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.CalciteGolem;
import com.androsa.ornamental.entity.model.CalciteGolemModel;
import com.androsa.ornamental.entity.model.renderstate.CalciteGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class CalciteGolemRenderer extends AbstractGolemRenderer<CalciteGolem, CalciteGolemRenderState, CalciteGolemModel> {

    public CalciteGolemRenderer(EntityRendererProvider.Context manager, CalciteGolemModel model, float shadow) {
        super(manager, model, "calcite_golem", shadow, false);
    }

    @Override
    public CalciteGolemRenderState createRenderState() {
        return new CalciteGolemRenderState();
    }

    @Override
    public void extractRenderState(CalciteGolem entity, CalciteGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.chargeState = entity.getChargeState();
        state.isResonating = entity.isResonating();
    }
}
