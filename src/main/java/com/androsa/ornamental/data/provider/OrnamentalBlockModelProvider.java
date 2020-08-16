package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public abstract class OrnamentalBlockModelProvider extends BlockModelProvider {

    private static final String ORNAMENT_MODELS = "ornamental:block/util/";

    public OrnamentalBlockModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, OrnamentalMod.MODID, helper);
    }

    public BlockModelBuilder fencePostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_post_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_wall_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, ORNAMENT_MODELS + "fence_gate_wall_open_column")
                .texture("end", end)
                .texture("side", side);
    }
}
