package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.FlowerGolem;
import com.androsa.ornamental.entity.model.FlowerGolemModel;
import com.androsa.ornamental.entity.model.renderstate.FlowerGolemRenderState;
import com.androsa.ornamental.entity.model.renderstate.OfferingGolemRenderState;
import com.androsa.ornamental.entity.renderer.layer.GolemFlowerLayer;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class FlowerGolemRenderer<T extends FlowerGolem, M extends FlowerGolemModel> extends AbstractGolemRenderer<T, OfferingGolemRenderState, M> {
    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public FlowerGolemRenderer(EntityRendererProvider.Context manager, M model, String texture, float shadow) {
        super(manager, model, texture, shadow, true);
        this.addLayer(new GolemFlowerLayer<>(this));
        this.blockModelResolver = manager.getBlockModelResolver();
    }

    @Override
    public OfferingGolemRenderState createRenderState() {
        return new OfferingGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, OfferingGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.flower = entity.getFlower();
        state.flowerPos = entity.getFlowerPos();
        state.offerFlowerTick = entity.getHoldFlowerTick();
        if (state.flower != null) {
            blockModelResolver.update(state.flowerModel, state.flower, BLOCK_DISPLAY_CONTEXT);
        }
    }
}
