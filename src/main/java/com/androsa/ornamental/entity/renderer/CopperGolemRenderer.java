package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.CopperGolem;
import com.androsa.ornamental.entity.model.CopperGolemModel;
import com.androsa.ornamental.entity.renderer.layer.CopperGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CopperGolemRenderer<T extends CopperGolem, M extends CopperGolemModel<T>> extends AbstractGolemRenderer<T,M> {

    public static final String CHARGED_TEXTURE = makeTexturePath("charged");
    public static final String EXPOSED_TEXTURE = makeTexturePath("exposed");
    public static final String WEATHERED_TEXTURE = makeTexturePath("weathered");
    public static final String ERODED_TEXTURE = makeTexturePath("eroded");

    public CopperGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new CopperGlowLayer<>(this));
    }

    private static String makeTexturePath(String type) {
        return "textures/entity/copper_golem/copper_golem_" + type + ".png";
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entity.isCharged()) {
            return getLocation(CHARGED_TEXTURE);
        } else {
            return switch (entity.getErosion()) {
                case 1 -> getLocation(EXPOSED_TEXTURE);
                case 2 -> getLocation(WEATHERED_TEXTURE);
                case 3 -> getLocation(ERODED_TEXTURE);
                default -> getLocation("textures/entity/copper_golem/copper_golem.png");
            };
        }
    }

    private static ResourceLocation getLocation(String path) {
        return new ResourceLocation(OrnamentalMod.MODID, path);
    }
}
