package com.androsa.nifty;

import com.androsa.nifty.data.NiftyBlockStates;
import com.androsa.nifty.data.NiftyItemModels;
import com.androsa.nifty.data.NiftyLootTables;
import com.androsa.nifty.data.NiftyRecipes;
import com.androsa.nifty.data.conditions.ConfigCondition;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(NiftyMod.MODID)
@Mod.EventBusSubscriber(modid = NiftyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NiftyMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "nifty";
    public static NiftyConfig config;

    public NiftyMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        final Pair<NiftyConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(NiftyConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, specPair.getRight());
        config = specPair.getLeft();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("Nifty is a mod by ???");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> NiftyMod::registerRenders);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ColourHandler::registerBlockColors);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ColourHandler::registerItemColors);
    }

    @SubscribeEvent
    public static void registerSerializer(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(new ConfigCondition.Serializer());
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeClient()) {
            generator.addProvider(new NiftyBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new NiftyItemModels(generator, event.getExistingFileHelper()));
        }
        if (event.includeServer()) {
            generator.addProvider(new NiftyLootTables(generator));
            generator.addProvider(new NiftyRecipes(generator));
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerRenders() {
        renderBlock(ModBlocks.grass_stairs, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.path_stairs, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.ice_stairs, RenderType.getTranslucent());

        renderBlock(ModBlocks.grass_slab, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.path_slab, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.ice_slab, RenderType.getTranslucent());

        renderBlock(ModBlocks.grass_fence, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.path_fence, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.ice_fence, RenderType.getTranslucent());

        renderBlock(ModBlocks.gold_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.diamond_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.emerald_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.lapis_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.obsidian_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.coal_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.redstone_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.missingno_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.clay_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.dirt_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.grass_trapdoor, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.hay_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.path_trapdoor, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.brick_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.quartz_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.bone_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.nether_brick_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.red_nether_brick_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.snow_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.ice_trapdoor, RenderType.getTranslucent());
        renderBlock(ModBlocks.packed_ice_trapdoor, RenderType.getCutout());
        renderBlock(ModBlocks.blue_ice_trapdoor, RenderType.getCutout());

        renderBlock(ModBlocks.grass_fence_gate, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.path_fence_gate, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.ice_fence_gate, RenderType.getTranslucent());

        renderBlock(ModBlocks.gold_door, RenderType.getCutout());
        renderBlock(ModBlocks.diamond_door, RenderType.getCutout());
        renderBlock(ModBlocks.emerald_door, RenderType.getCutout());
        renderBlock(ModBlocks.lapis_door, RenderType.getCutout());
        renderBlock(ModBlocks.obsidian_door, RenderType.getCutout());
        renderBlock(ModBlocks.coal_door, RenderType.getCutout());
        renderBlock(ModBlocks.redstone_door, RenderType.getCutout());
        renderBlock(ModBlocks.missingno_door, RenderType.getCutout());
        renderBlock(ModBlocks.clay_door, RenderType.getCutout());
        renderBlock(ModBlocks.dirt_door, RenderType.getCutout());
        renderBlock(ModBlocks.grass_door, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.hay_door, RenderType.getCutout());
        renderBlock(ModBlocks.path_door, RenderType.getCutoutMipped());
        renderBlock(ModBlocks.brick_door, RenderType.getCutout());
        renderBlock(ModBlocks.quartz_door, RenderType.getCutout());
        renderBlock(ModBlocks.bone_door, RenderType.getCutout());
        renderBlock(ModBlocks.nether_brick_door, RenderType.getCutout());
        renderBlock(ModBlocks.red_nether_brick_door, RenderType.getCutout());
        renderBlock(ModBlocks.snow_door, RenderType.getCutout());
        renderBlock(ModBlocks.ice_door, RenderType.getTranslucent());
        renderBlock(ModBlocks.packed_ice_door, RenderType.getCutout());
        renderBlock(ModBlocks.blue_ice_door, RenderType.getCutout());
    }

    private static void renderBlock(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
}