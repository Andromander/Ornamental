package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.GrassGolem;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.androsa.ornamental.entity.renderer.layer.GrassFlowerLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GrassGolemRenderer<T extends GrassGolem, M extends DirtGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public GrassGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new GrassFlowerLayer<>(this));
    }
}
