package com.androsa.ornamental.entity.renderer;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.entity.AmethystGolem;
import com.androsa.ornamental.entity.model.AmethystGolemModel;
import com.androsa.ornamental.entity.model.renderstate.AmethystGolemRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class AmethystGolemRenderer extends AbstractGolemRenderer<AmethystGolem, AmethystGolemRenderState, AmethystGolemModel> {

    public static final String NORMAL_NAME_TEXTURE = makeTexturePath("normal");
    public static final String MAGIC_NAME_TEXTURE = makeTexturePath("tricky");

    public AmethystGolemRenderer(EntityRendererProvider.Context manager, AmethystGolemModel model, float shadow) {
        super(manager, model, "amethyst_golem", shadow, false);
    }

    private static String makeTexturePath(String type) {
        return "textures/entity/amethyst_golem/amethyst_golem_" + type + ".png";
    }

    @Override
    public AmethystGolemRenderState createRenderState() {
        return new AmethystGolemRenderState();
    }

    @Override
    public void extractRenderState(AmethystGolem entity, AmethystGolemRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isCasting = entity.isCasting();
        state.isTiky = checkMagicName(entity, "tiky") || checkMagicName(entity, "Tricky");
    }

    @Override
    public Identifier getTextureLocation(AmethystGolemRenderState entity) {
        return Identifier.fromNamespaceAndPath(OrnamentalMod.MODID, entity.isTiky ? MAGIC_NAME_TEXTURE : NORMAL_NAME_TEXTURE);
    }
}
