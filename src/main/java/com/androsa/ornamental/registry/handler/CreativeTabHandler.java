package com.androsa.ornamental.registry.handler;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.registry.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CreativeTabHandler {

    public static final List<RegistryObject<? extends Block>> STAIR_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> SLAB_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> FENCE_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> TRAPDOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> FENCE_GATE_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> DOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> POLE_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> BEAM_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> WALL_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Block>> SADDLE_DOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<RegistryObject<? extends Item>> SPAWN_EGGS = Lists.newArrayList();

    @Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class AddContent {

        @SubscribeEvent
        public static void addContent(CreativeModeTabEvent.Register event) {
            CreativeModeTab stairOrnaments = createTab(event, "stair_ornaments", () -> new ItemStack(ModBlocks.diamond_stairs.get()), null, null, STAIR_ORNAMENTS);
            CreativeModeTab slabOrnaments = createTab(event, "slab_ornaments", () -> new ItemStack(ModBlocks.diamond_slab.get()), List.of(), List.of(stairOrnaments), SLAB_ORNAMENTS);
            CreativeModeTab fenceOrnaments = createTab(event, "fence_ornaments", () -> new ItemStack(ModBlocks.diamond_fence.get()), List.of(), List.of(slabOrnaments), FENCE_ORNAMENTS);
            CreativeModeTab trapdoorOrnaments = createTab(event, "trapdoor_ornaments", () -> new ItemStack(ModBlocks.diamond_trapdoor.get()), List.of(), List.of(fenceOrnaments), TRAPDOOR_ORNAMENTS);
            CreativeModeTab fencegateOrnaments = createTab(event, "fence_gate_ornaments", () -> new ItemStack(ModBlocks.diamond_fence_gate.get()), List.of(), List.of(trapdoorOrnaments), FENCE_GATE_ORNAMENTS);
            CreativeModeTab doorOrnaments = createTab(event, "door_ornaments", () -> new ItemStack(ModBlocks.diamond_door.get()), List.of(), List.of(fencegateOrnaments), DOOR_ORNAMENTS);
            CreativeModeTab poleOrnaments = createTab(event, "pole_ornaments", () -> new ItemStack(ModBlocks.diamond_pole.get()), List.of(), List.of(doorOrnaments), POLE_ORNAMENTS);
            CreativeModeTab beamOrnaments = createTab(event, "beam_ornaments", () -> new ItemStack(ModBlocks.diamond_beam.get()), List.of(), List.of(poleOrnaments), BEAM_ORNAMENTS);
            CreativeModeTab wallOrnaments = createTab(event, "wall_ornaments", () -> new ItemStack(ModBlocks.diamond_wall.get()), List.of(), List.of(beamOrnaments), WALL_ORNAMENTS);
            createTab(event, "saddle_door_ornaments", () -> new ItemStack(ModBlocks.diamond_saddle_door.get()), List.of(), List.of(wallOrnaments), SADDLE_DOOR_ORNAMENTS);
        }

        private static CreativeModeTab createTab(CreativeModeTabEvent.Register evt, String name, Supplier<ItemStack> icon, List<Object> before, List<Object> after, List<RegistryObject<? extends Block>> list) {
            if (before != null && after != null) {
                return evt.registerCreativeModeTab(new ResourceLocation(OrnamentalMod.MODID, name), before, after, buildTab(name, icon, list));
            }
            return evt.registerCreativeModeTab(new ResourceLocation(OrnamentalMod.MODID, name), buildTab(name, icon, list));
        }

        private static Consumer<CreativeModeTab.Builder> buildTab(String name, Supplier<ItemStack> icon, List<RegistryObject<? extends Block>> list) {
            return builder -> builder
                    .title(Component.translatable("ornamental.tab." + name))
                    .icon(icon)
                    .displayItems((flag, output, operator) -> {
                        for (RegistryObject<? extends Block> block : list) {
                            output.accept(block.get());
                        }
                    });
        }
    }

    @Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class BuildContent {
        @SubscribeEvent
        public static void buildContent(CreativeModeTabEvent.BuildContents event) {
            if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
                for (RegistryObject<? extends Item> item : SPAWN_EGGS) {
                    event.accept(item);
                }
            }
        }
    }
}
