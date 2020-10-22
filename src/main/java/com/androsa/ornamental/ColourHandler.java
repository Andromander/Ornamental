package com.androsa.ornamental;

import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColourHandler {

    public static void registerBlockColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColors.getGrassColor(worldIn, pos) : GrassColors.get(0.5D, 1.0D),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_door.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get());
    }

    public static void registerItemColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();
        ItemColors items = Minecraft.getInstance().getItemColors();

        items.register((stack, tintIndex) -> blocks.getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get());
    }
}
