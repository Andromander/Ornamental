package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.FlowerGolem;
import com.androsa.ornamental.entity.model.FlowerGolemModel;
import com.androsa.ornamental.entity.renderer.layer.GolemFlowerLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class FlowerGolemRenderer<T extends FlowerGolem, M extends FlowerGolemModel<T>> extends HeavyGolemRenderer<T,M> {

    public FlowerGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new GolemFlowerLayer<>(this));
    }
}
