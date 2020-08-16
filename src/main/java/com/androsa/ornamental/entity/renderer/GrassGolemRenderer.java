package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.GrassGolemEntity;
import com.androsa.ornamental.entity.model.DirtGolemModel;
import com.androsa.ornamental.entity.renderer.layer.GrassFlowerLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassGolemRenderer<T extends GrassGolemEntity, M extends DirtGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public GrassGolemRenderer(EntityRendererManager manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new GrassFlowerLayer<>(this));
    }
}
