package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.OrnamentalBlockStates;
import com.androsa.ornamental.data.OrnamentalItemModels;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public class OrnamentalModelProvider extends ModelProvider {

    public OrnamentalModelProvider(PackOutput output) {
        super(output, OrnamentalMod.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new OrnamentalBlockStates(blockModels).runBlockGen();
        new OrnamentalItemModels(itemModels).runItemGen();
    }
}
