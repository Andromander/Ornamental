package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import com.google.common.collect.Lists;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OrnamentalMod.MODID);

    public static final List<Supplier<? extends Block>> STAIR_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SLAB_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> FENCE_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> TRAPDOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> FENCE_GATE_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> DOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> POLE_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> BEAM_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> WALL_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SADDLE_DOOR_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Block>> SUPPORT_ORNAMENTS = Lists.newArrayList();
    public static final List<Supplier<? extends Item>> SPAWN_EGGS = Lists.newArrayList();

    public static final Supplier<CreativeModeTab> STAIR_TAB = createTab("stair_ornaments", () -> new ItemStack(ModBlocks.diamond_stairs.get()), null);
    public static final Supplier<CreativeModeTab> SLAB_TAB = createTab("slab_ornaments", () -> new ItemStack(ModBlocks.diamond_slab.get()), STAIR_TAB);
    public static final Supplier<CreativeModeTab> FENCE_TAB = createTab("fence_ornaments", () -> new ItemStack(ModBlocks.diamond_fence.get()), SLAB_TAB);
    public static final Supplier<CreativeModeTab> TRAPDOOR_TAB = createTab("trapdoor_ornaments", () -> new ItemStack(ModBlocks.diamond_trapdoor.get()), FENCE_TAB);
    public static final Supplier<CreativeModeTab> FENCE_GATE_TAB = createTab("fence_gate_ornaments", () -> new ItemStack(ModBlocks.diamond_fence_gate.get()), TRAPDOOR_TAB);
    public static final Supplier<CreativeModeTab> DOOR_TAB = createTab("door_ornaments", () -> new ItemStack(ModBlocks.diamond_door.get()), FENCE_GATE_TAB);
    public static final Supplier<CreativeModeTab> POLE_TAB = createTab("pole_ornaments", () -> new ItemStack(ModBlocks.diamond_pole.get()), DOOR_TAB);
    public static final Supplier<CreativeModeTab> BEAM_TAB = createTab("beam_ornaments", () -> new ItemStack(ModBlocks.diamond_beam.get()), POLE_TAB);
    public static final Supplier<CreativeModeTab> WALL_TAB = createTab("wall_ornaments", () -> new ItemStack(ModBlocks.diamond_wall.get()), BEAM_TAB);
    public static final Supplier<CreativeModeTab> SADDLE_DOOR_TAB = createTab("saddle_door_ornaments", () -> new ItemStack(ModBlocks.diamond_saddle_door.get()), WALL_TAB);
    public static final Supplier<CreativeModeTab> SUPPORT_TAB = createTab("support_ornaments", () -> new ItemStack(ModBlocks.diamond_support.get()), SADDLE_DOOR_TAB);

    private static Supplier<CreativeModeTab> createTab(String name, Supplier<ItemStack> icon, Supplier<CreativeModeTab> after) {
        return CREATIVE_TABS.register(name, () -> {
            CreativeModeTab.Builder tab = CreativeModeTab.builder()
                    .title(Component.translatable("ornamental.tab." + name))
                    .icon(icon);
            if (after != null)
                tab.withTabsBefore(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(after.get()));
            return tab.build();
        });
    }

    @Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class BuildContent {
        @SubscribeEvent
        public static void buildContent(BuildCreativeModeTabContentsEvent event) {
            if (event.getTab() == STAIR_TAB.get()) {
                for (Supplier<? extends Block> block : STAIR_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == SLAB_TAB.get()) {
                for (Supplier<? extends Block> block : SLAB_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == FENCE_TAB.get()) {
                for (Supplier<? extends Block> block : FENCE_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == TRAPDOOR_TAB.get()) {
                for (Supplier<? extends Block> block : TRAPDOOR_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == FENCE_GATE_TAB.get()) {
                for (Supplier<? extends Block> block : FENCE_GATE_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == DOOR_TAB.get()) {
                for (Supplier<? extends Block> block : DOOR_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == POLE_TAB.get()) {
                for (Supplier<? extends Block> block : POLE_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == BEAM_TAB.get()) {
                for (Supplier<? extends Block> block : BEAM_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == WALL_TAB.get()) {
                for (Supplier<? extends Block> block : WALL_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == SADDLE_DOOR_TAB.get()) {
                for (Supplier<? extends Block> block : SADDLE_DOOR_ORNAMENTS) {
                    event.accept(block.get());
                }
            }
            if (event.getTab() == SUPPORT_TAB.get()) {
                for (Supplier<? extends Block> block : SUPPORT_ORNAMENTS) {
                    event.accept(block.get());
                }
            }

            if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
                for (Supplier<? extends Item> item : SPAWN_EGGS) {
                    event.accept(item.get());
                }
            }
        }
    }
}
