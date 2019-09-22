package com.androsa.nifty;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NiftyMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColourHandler {

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block e) {
        BlockColors blocks = e.getBlockColors();

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColors.getGrassColor(worldIn, pos) : GrassColors.get(0.5D, 1.0D),
                ModBlocks.grass_fence,
                ModBlocks.grass_fence_gate,
                ModBlocks.grass_slab,
                ModBlocks.grass_stairs,
                ModBlocks.grass_trapdoor,
                ModBlocks.grass_door);
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item e) {
        BlockColors blocks = e.getBlockColors();
        ItemColors items = e.getItemColors();

        items.register((stack, tintIndex) -> blocks.getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ModBlocks.grass_fence,
                ModBlocks.grass_fence_gate,
                ModBlocks.grass_slab,
                ModBlocks.grass_stairs,
                ModBlocks.grass_trapdoor);
    }
}
