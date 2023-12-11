package com.androsa.ornamental;

import com.androsa.ornamental.data.*;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModCreativeTabs;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import com.androsa.ornamental.registry.handler.RemapHandler;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mod(OrnamentalMod.MODID)
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

        RemapHandler.remapEntries();
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
        generator.addProvider(event.includeServer(), new OrnamentalRecipes(output, provider));
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new OrnamentalItemTags(output, provider, blockTags, helper));
        generator.addProvider(true, new PackMetadataGenerator(output).add(
                PackMetadataSection.TYPE,
                new PackMetadataSection(
                        Component.literal("Ornamental Resources"),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                        Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion)))));
    }
}