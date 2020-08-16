package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.entity.FlowerGolemEntity;
import com.androsa.ornamental.entity.model.FlowerGolemModel;
import com.androsa.ornamental.entity.renderer.layer.GolemFlowerLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlowerGolemRenderer<T extends FlowerGolemEntity, M extends FlowerGolemModel<T>> extends HeavyGolemRenderer<T,M> {

    public FlowerGolemRenderer(EntityRendererManager manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new GolemFlowerLayer<>(this));
    }
}
