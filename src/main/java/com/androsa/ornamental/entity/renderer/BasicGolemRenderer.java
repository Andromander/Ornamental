package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.OrnamentalGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.androsa.ornamental.entity.model.renderstate.GolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BasicGolemRenderer<T extends OrnamentalGolem, M extends AbstractGolemModel<GolemRenderState>> extends AbstractGolemRenderer<T, GolemRenderState, M>{

    public BasicGolemRenderer(EntityRendererProvider.Context context, M model, String texture, float shadow, boolean heavy) {
        super(context, model, texture, shadow, heavy);
    }

    @Override
    public GolemRenderState createRenderState() {
        return new GolemRenderState();
    }
}
