package com.androsa.nifty.data.provider;

import com.androsa.nifty.NiftyMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public abstract class NiftyBlockModelProvider extends BlockModelProvider {

    private static final String NIFTY_MODELS = "nifty:block/util/";

    public NiftyBlockModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, NiftyMod.MODID, helper);
    }

    public BlockModelBuilder fencePostColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, NIFTY_MODELS + "fence_post_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, NIFTY_MODELS + "fence_gate_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, NIFTY_MODELS + "fence_gate_open_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, NIFTY_MODELS + "fence_gate_wall_column")
                .texture("end", end)
                .texture("side", side);
    }

    public BlockModelBuilder fenceGateWallOpenColumn(String name, ResourceLocation side, ResourceLocation end) {
        return withExistingParent(name, NIFTY_MODELS + "fence_gate_wall_open_column")
                .texture("end", end)
                .texture("side", side);
    }
}
