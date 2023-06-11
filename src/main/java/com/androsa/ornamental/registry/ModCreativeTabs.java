package com.androsa.ornamental.registry;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Supplier;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OrnamentalMod.MODID);

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

    public static final RegistryObject<CreativeModeTab> STAIR_TAB = createTab(CREATIVE_TABS, "stair_ornaments", () -> new ItemStack(ModBlocks.diamond_stairs.get()), null, STAIR_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> SLAB_TAB = createTab(CREATIVE_TABS, "slab_ornaments", () -> new ItemStack(ModBlocks.diamond_slab.get()), STAIR_TAB, SLAB_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> FENCE_TAB = createTab(CREATIVE_TABS, "fence_ornaments", () -> new ItemStack(ModBlocks.diamond_fence.get()), SLAB_TAB, FENCE_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> TRAPDOOR_TAB = createTab(CREATIVE_TABS, "trapdoor_ornaments", () -> new ItemStack(ModBlocks.diamond_trapdoor.get()), FENCE_TAB, TRAPDOOR_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> FENCE_GATE_TAB = createTab(CREATIVE_TABS, "fence_gate_ornaments", () -> new ItemStack(ModBlocks.diamond_fence_gate.get()), TRAPDOOR_TAB, FENCE_GATE_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> DOOR_TAB = createTab(CREATIVE_TABS, "door_ornaments", () -> new ItemStack(ModBlocks.diamond_door.get()), FENCE_GATE_TAB, DOOR_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> POLE_TAB = createTab(CREATIVE_TABS, "pole_ornaments", () -> new ItemStack(ModBlocks.diamond_pole.get()), DOOR_TAB, POLE_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> BEAM_TAB = createTab(CREATIVE_TABS, "beam_ornaments", () -> new ItemStack(ModBlocks.diamond_beam.get()), POLE_TAB, BEAM_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> WALL_TAB = createTab(CREATIVE_TABS, "wall_ornaments", () -> new ItemStack(ModBlocks.diamond_wall.get()), BEAM_TAB, WALL_ORNAMENTS);
    public static final RegistryObject<CreativeModeTab> SADDLE_DOOR_TAB = createTab(CREATIVE_TABS, "saddle_door_ornaments", () -> new ItemStack(ModBlocks.diamond_saddle_door.get()), WALL_TAB, SADDLE_DOOR_ORNAMENTS);

    private static RegistryObject<CreativeModeTab> createTab(DeferredRegister<CreativeModeTab> reg, String name, Supplier<ItemStack> icon, RegistryObject<CreativeModeTab> after, List<RegistryObject<? extends Block>> list) {
        CreativeModeTab.Builder tab = buildTab(name, icon, list);
        if (after != null) tab.withTabsAfter(after.getId());
        return reg.register(name, tab::build);
    }

    private static CreativeModeTab.Builder buildTab(String name, Supplier<ItemStack> icon, List<RegistryObject<? extends Block>> list) {
        return CreativeModeTab.builder()
                .title(Component.translatable("ornamental.tab." + name))
                .icon(icon)
                .displayItems((parameters, output) -> {
                    for (RegistryObject<? extends Block> block : list) {
                        output.accept(block.get());
                    }
                });
    }

    @Mod.EventBusSubscriber(modid = OrnamentalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class BuildContent {
        @SubscribeEvent
        public static void buildContent(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
                for (RegistryObject<? extends Item> item : SPAWN_EGGS) {
                    event.accept(item);
                }
            }
        }
    }
}
