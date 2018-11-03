package com.androsa.nifty.util;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = NiftyMod.MODID, value = Side.CLIENT)
public class ModelRenderer {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (BlockModelHelper b : ModBlocks.getBlockModels()) b.registerModel();
    }
}
