package com.androsa.ornamental;

import com.androsa.ornamental.data.*;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(OrnamentalMod.MODID)
@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrnamentalMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ornamental";

    public OrnamentalMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModParticles.PARTICLE_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static DamageSource shocked(Entity source, Entity target) {
        return new IndirectEntityDamageSource("ornamental.shocked", source, target).bypassArmor();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ColourHandler::registerBlockColors);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ColourHandler::registerItemColors);
    }

    @SubscribeEvent
    public static void registerSerializer(RegisterEvent event) {
        if (event.getRegistryKey() == ForgeRegistries.Keys.RECIPE_SERIALIZERS) {
            CraftingHelper.register(new ConfigCondition.Serializer());
        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        BlockTagsProvider blockTags = new OrnamentalBlockTags(generator, helper);

        if (event.includeClient()) {
            generator.addProvider(true, new OrnamentalBlockStates(generator, helper));
            generator.addProvider(true, new OrnamentalItemModels(generator, helper));
        }
        if (event.includeServer()) {
            generator.addProvider(true, new OrnamentalLootTables(generator));
            generator.addProvider(true, new OrnamentalRecipes(generator));
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new OrnamentalItemTags(generator, blockTags, helper));
        }
    }
}