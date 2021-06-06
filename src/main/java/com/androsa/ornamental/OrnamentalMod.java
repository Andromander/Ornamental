package com.androsa.ornamental;

import com.androsa.ornamental.data.*;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import com.androsa.ornamental.registry.ModBlocks;
import com.androsa.ornamental.registry.ModEntities;
import com.androsa.ornamental.registry.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(OrnamentalMod.MODID)
@Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OrnamentalMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ornamental";
    public static OrnamentalConfig config;

    public OrnamentalMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModParticles.PARTICLE_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());

        final Pair<OrnamentalConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(OrnamentalConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, specPair.getRight());
        config = specPair.getLeft();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> OrnamentalMod::registerRenders);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ColourHandler::registerBlockColors);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ColourHandler::registerItemColors);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModEntities::registerRenders);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModParticles::registerClientParticles);
    }

    @SubscribeEvent
    public static void registerSerializer(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(new ConfigCondition.Serializer());
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        BlockTagsProvider blockTags = new OrnamentalBlockTags(generator, helper);

        if (event.includeClient()) {
            generator.addProvider(new OrnamentalBlockStates(generator, helper));
            generator.addProvider(new OrnamentalItemModels(generator, helper));
        }
        if (event.includeServer()) {
            generator.addProvider(new OrnamentalLootTables(generator));
            generator.addProvider(new OrnamentalRecipes(generator));
            generator.addProvider(blockTags);
            generator.addProvider(new OrnamentalItemTags(generator, blockTags, helper));
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerRenders() {
        renderBlock(ModBlocks.grass_stairs, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_stairs, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_stairs, RenderType.translucent());

        renderBlock(ModBlocks.grass_slab, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_slab, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_slab, RenderType.translucent());

        renderBlock(ModBlocks.grass_fence, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_fence, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_fence, RenderType.translucent());

        renderBlock(ModBlocks.gold_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.diamond_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.emerald_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.lapis_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.obsidian_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.coal_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.redstone_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.missingno_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.clay_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.dirt_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.grass_trapdoor, RenderType.cutoutMipped());
        renderBlock(ModBlocks.hay_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.path_trapdoor, RenderType.cutoutMipped());
        renderBlock(ModBlocks.brick_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.quartz_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.bone_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.nether_brick_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.red_nether_brick_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.snow_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.ice_trapdoor, RenderType.translucent());
        renderBlock(ModBlocks.packed_ice_trapdoor, RenderType.cutout());
        renderBlock(ModBlocks.blue_ice_trapdoor, RenderType.cutout());

        renderBlock(ModBlocks.grass_fence_gate, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_fence_gate, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_fence_gate, RenderType.translucent());

        renderBlock(ModBlocks.gold_door, RenderType.cutout());
        renderBlock(ModBlocks.diamond_door, RenderType.cutout());
        renderBlock(ModBlocks.emerald_door, RenderType.cutout());
        renderBlock(ModBlocks.lapis_door, RenderType.cutout());
        renderBlock(ModBlocks.obsidian_door, RenderType.cutout());
        renderBlock(ModBlocks.coal_door, RenderType.cutout());
        renderBlock(ModBlocks.redstone_door, RenderType.cutout());
        renderBlock(ModBlocks.missingno_door, RenderType.cutout());
        renderBlock(ModBlocks.clay_door, RenderType.cutout());
        renderBlock(ModBlocks.dirt_door, RenderType.cutout());
        renderBlock(ModBlocks.grass_door, RenderType.cutoutMipped());
        renderBlock(ModBlocks.hay_door, RenderType.cutout());
        renderBlock(ModBlocks.path_door, RenderType.cutoutMipped());
        renderBlock(ModBlocks.brick_door, RenderType.cutout());
        renderBlock(ModBlocks.quartz_door, RenderType.cutout());
        renderBlock(ModBlocks.bone_door, RenderType.cutout());
        renderBlock(ModBlocks.nether_brick_door, RenderType.cutout());
        renderBlock(ModBlocks.red_nether_brick_door, RenderType.cutout());
        renderBlock(ModBlocks.snow_door, RenderType.cutout());
        renderBlock(ModBlocks.ice_door, RenderType.translucent());
        renderBlock(ModBlocks.packed_ice_door, RenderType.cutout());
        renderBlock(ModBlocks.blue_ice_door, RenderType.cutout());

        renderBlock(ModBlocks.grass_pole, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_pole, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_pole, RenderType.translucent());

        renderBlock(ModBlocks.grass_beam, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_beam, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_beam, RenderType.translucent());

        renderBlock(ModBlocks.grass_wall, RenderType.cutoutMipped());
        renderBlock(ModBlocks.path_wall, RenderType.cutoutMipped());
        renderBlock(ModBlocks.ice_wall, RenderType.translucent());
    }

    private static void renderBlock(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
}