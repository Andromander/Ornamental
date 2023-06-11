package com.androsa.ornamental;

import com.androsa.ornamental.data.*;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModCreativeTabs;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(OrnamentalMod.MODID)
@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrnamentalMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ornamental";

    public OrnamentalMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModCreativeTabs.CREATIVE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModParticles.PARTICLE_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        BlockTagsProvider blockTags = new OrnamentalBlockTags(output, provider, helper);

        generator.addProvider(event.includeClient(), new OrnamentalBlockStates(output, helper));
        generator.addProvider(event.includeClient(), new OrnamentalItemModels(output, helper));
        generator.addProvider(event.includeServer(), new OrnamentalLootTables(output));
        generator.addProvider(event.includeServer(), new OrnamentalRecipes(output));
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new OrnamentalItemTags(output, provider, blockTags, helper));
    }
}