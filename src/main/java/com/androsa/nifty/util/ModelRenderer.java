package com.androsa.nifty.util;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyMod;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = NiftyMod.MODID, value = Side.CLIENT)
public class ModelRenderer {

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block e) {
        BlockColors blocks = e.getBlockColors();

        blocks.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D),
                ModBlocks.grass_fence,
                ModBlocks.grass_fence_gate,
                ModBlocks.grass_slab,
                ModBlocks.double_grass_slab,
                ModBlocks.grass_stairs,
                ModBlocks.grass_trapdoor);
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item e) {
        BlockColors blocks = e.getBlockColors();
        ItemColors items = e.getItemColors();

        items.registerItemColorHandler((stack, tintIndex) -> blocks.colorMultiplier(((ItemBlock)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ModBlocks.grass_fence,
                ModBlocks.grass_fence_gate,
                ModBlocks.grass_slab,
                ModBlocks.double_grass_slab,
                ModBlocks.grass_stairs,
                ModBlocks.grass_trapdoor);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (BlockModelHelper b : ModBlocks.getBlockModels()) b.registerModel();
    }
}
