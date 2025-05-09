package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.ShootingGolem;
import com.androsa.ornamental.entity.model.AbstractGolemModel;
import com.androsa.ornamental.entity.model.renderstate.ShootingGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ShootingGolemRender<T extends ShootingGolem, M extends AbstractGolemModel<ShootingGolemRenderState>> extends AbstractGolemRenderer<T, ShootingGolemRenderState, M> {

    public ShootingGolemRender(EntityRendererProvider.Context manager, M model, String texture, float shadow, boolean heavy) {
        super(manager, model, texture, shadow, heavy);
    }

    @Override
    public ShootingGolemRenderState createRenderState() {
        return new ShootingGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, ShootingGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isShooting = entity.isTargeting();
    }
}
