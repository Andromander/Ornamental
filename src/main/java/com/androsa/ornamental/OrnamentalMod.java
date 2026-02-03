package com.androsa.ornamental;

import com.androsa.ornamental.data.*;
import com.androsa.ornamental.data.provider.OrnamentalModelProvider;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModCreativeTabs;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import com.androsa.ornamental.registry.handler.RemapHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(OrnamentalMod.MODID)
public class OrnamentalMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ornamental";

    public OrnamentalMod(IEventBus bus) {
        bus.addListener(this::clientData);
        bus.addListener(this::serverData);

        ModBlocks.BLOCKS.register(bus);
        ModBlocks.ITEMS.register(bus);
        ModCreativeTabs.CREATIVE_TABS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModParticles.PARTICLE_TYPE.register(bus);

        RemapHandler.remapEntries();
    }

    public void clientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(true, new OrnamentalModelProvider(output));
    }

    public void serverData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        generator.addProvider(true, new OrnamentalLootTables(output, provider));
        generator.addProvider(true, new OrnamentalRecipes.Runner(output, provider));
        BlockTagsProvider blockTags = new OrnamentalBlockTags(output, provider);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new OrnamentalItemTags(output, provider, blockTags));
    }
}