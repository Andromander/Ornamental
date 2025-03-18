package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.CopperGolem;
import com.androsa.ornamental.entity.model.CopperGolemModel;
import com.androsa.ornamental.entity.model.renderstate.CopperGolemRenderState;
import com.androsa.ornamental.entity.renderer.layer.CopperGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CopperGolemRenderer<T extends CopperGolem, M extends CopperGolemModel> extends AbstractGolemRenderer<T, CopperGolemRenderState, M> {

    public static final String CHARGED_TEXTURE = makeTexturePath("charged");
    public static final String EXPOSED_TEXTURE = makeTexturePath("exposed");
    public static final String WEATHERED_TEXTURE = makeTexturePath("weathered");
    public static final String ERODED_TEXTURE = makeTexturePath("eroded");

    public CopperGolemRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, "copper_golem", shadow, false);
        this.addLayer(new CopperGlowLayer<>(this));
    }

    @Override
    public CopperGolemRenderState createRenderState() {
        return new CopperGolemRenderState();
    }

    @Override
    public void extractRenderState(T entity, CopperGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.erosion = entity.getErosion();
        state.isCharged = entity.isCharged();
        state.charges = entity.getCharges();
    }

    private static String makeTexturePath(String type) {
        return "textures/entity/copper_golem/copper_golem_" + type + ".png";
    }

    @Override
    public ResourceLocation getTextureLocation(CopperGolemRenderState entity) {
        if (entity.isCharged) {
            return getLocation(CHARGED_TEXTURE);
        } else {
            return switch (entity.erosion) {
                case 1 -> getLocation(EXPOSED_TEXTURE);
                case 2 -> getLocation(WEATHERED_TEXTURE);
                case 3 -> getLocation(ERODED_TEXTURE);
                default -> getLocation("textures/entity/copper_golem/copper_golem.png");
            };
        }
    }

    private static ResourceLocation getLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(OrnamentalMod.MODID, path);
    }
}
