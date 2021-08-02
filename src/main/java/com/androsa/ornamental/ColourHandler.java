package com.androsa.ornamental;

import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColourHandler {

    public static void registerBlockColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColors.getAverageGrassColor(worldIn, pos) : GrassColor.get(0.5D, 1.0D),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_door.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get(),
                ModBlocks.grass_wall.get());
    }

    public static void registerItemColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();
        ItemColors items = Minecraft.getInstance().getItemColors();

        items.register((stack, tintIndex) -> blocks.getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex),
                ModBlocks.grass_fence.get(),
                ModBlocks.grass_fence_gate.get(),
                ModBlocks.grass_slab.get(),
                ModBlocks.grass_stairs.get(),
                ModBlocks.grass_trapdoor.get(),
                ModBlocks.grass_pole.get(),
                ModBlocks.grass_beam.get(),
                ModBlocks.grass_wall.get());
    }
}
