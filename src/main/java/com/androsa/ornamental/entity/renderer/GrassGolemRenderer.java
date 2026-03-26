package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.GrassGolem;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.androsa.ornamental.entity.model.renderstate.FlowerGolemRenderState;
import com.androsa.ornamental.entity.renderer.layer.GrassFlowerLayer;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GrassGolemRenderer<T extends GrassGolem, M extends DirtGolemModel<FlowerGolemRenderState>> extends AbstractGolemRenderer<T, FlowerGolemRenderState,M> {

    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT  = BlockDisplayContext.create();

    public GrassGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, "grass_golem", shadow, false);
        this.addLayer(new GrassFlowerLayer<>(this));
        this.blockModelResolver = manager.getBlockModelResolver();
    }

    @Override
    public FlowerGolemRenderState createRenderState() {
        return new FlowerGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, FlowerGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.flower = entity.getFlower();
        if (state.flower != null) {
            blockModelResolver.update(state.flowerModel, state.flower, BLOCK_DISPLAY_CONTEXT);
        }
    }
}
