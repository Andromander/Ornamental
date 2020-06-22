package com.androsa.nifty.entity.renderer;

import com.androsa.nifty.entity.FlowerGolemEntity;
import com.androsa.nifty.entity.model.FlowerGolemModel;
import com.androsa.nifty.entity.renderer.layer.GolemFlowerLayer;
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
