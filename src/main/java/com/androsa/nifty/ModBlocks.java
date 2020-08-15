package com.androsa.nifty;

import com.androsa.nifty.blocks.*;
import com.androsa.nifty.items.NiftyBlockItem;
import com.androsa.nifty.items.NiftyTallBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess"})
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NiftyMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NiftyMod.MODID);

    public static final RegistryObject<NiftyStairs> iron_stairs = registerStairs(NiftyBuilders.IRON);
    public static final RegistryObject<NiftyStairs> gold_stairs = registerStairs(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftyStairs> diamond_stairs = registerStairs(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftyStairs> emerald_stairs = registerStairs(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftyStairs> lapis_stairs = registerStairs(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftyStairs> obsidian_stairs = registerStairs(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftyStairs> coal_stairs = registerStairs(NiftyBuilders.COAL);
    public static final RegistryObject<NiftyStairs> redstone_stairs = registerStairs(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftyStairs> missingno_stairs = registerStairs(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftyStairs> clay_stairs = registerStairs(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftyStairs> dirt_stairs = registerStairs(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftyStairs> grass_stairs = registerStairs(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftyStairs> hay_stairs = registerStairs(NiftyBuilders.HAY);
    public static final RegistryObject<NiftyStairs> path_stairs = registerStairs(NiftyBuilders.PATH);
    public static final RegistryObject<NiftyStairs> bone_stairs = registerStairs(NiftyBuilders.BONE);
    public static final RegistryObject<NiftyStairs> snow_stairs = registerStairs(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftyStairs> ice_stairs = registerStairs(NiftyBuilders.ICE);
    public static final RegistryObject<NiftyStairs> packed_ice_stairs = registerStairs(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftyStairs> blue_ice_stairs = registerStairs(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftyStairs> netherite_stairs = registerStairs(NiftyBuilders.NETHERITE);

    public static final RegistryObject<NiftySlab> iron_slab = registerSlab(NiftyBuilders.IRON);
    public static final RegistryObject<NiftySlab> gold_slab = registerSlab(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftySlab> diamond_slab = registerSlab(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftySlab> emerald_slab = registerSlab(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftySlab> lapis_slab = registerSlab(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftySlab> obsidian_slab = registerSlab(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftySlab> coal_slab = registerSlab(NiftyBuilders.COAL);
    public static final RegistryObject<NiftySlab> redstone_slab = registerSlab(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftySlab> missingno_slab = registerSlab(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftySlab> clay_slab = registerSlab(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftySlab> dirt_slab = registerSlab(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftySlab> grass_slab = registerSlab(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftySlab> hay_slab = registerSlab(NiftyBuilders.HAY);
    public static final RegistryObject<NiftySlab> path_slab = registerSlab(NiftyBuilders.PATH);
    public static final RegistryObject<NiftySlab> bone_slab = registerSlab(NiftyBuilders.BONE);
    public static final RegistryObject<NiftySlab> snow_slab = registerSlab(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftySlab> ice_slab = registerSlab(NiftyBuilders.ICE);
    public static final RegistryObject<NiftySlab> packed_ice_slab = registerSlab(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftySlab> blue_ice_slab = registerSlab(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftySlab> netherite_slab = registerSlab(NiftyBuilders.NETHERITE);

    public static final RegistryObject<NiftyFence> iron_fence = registerFence(NiftyBuilders.IRON);
    public static final RegistryObject<NiftyFence> gold_fence = registerFence(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftyFence> diamond_fence = registerFence(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftyFence> emerald_fence = registerFence(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftyFence> lapis_fence = registerFence(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftyFence> obsidian_fence = registerFence(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftyFence> coal_fence = registerFence(NiftyBuilders.COAL);
    public static final RegistryObject<NiftyFence> redstone_fence = registerFence(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftyFence> missingno_fence = registerFence(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftyFence> clay_fence = registerFence(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftyFence> dirt_fence = registerFence(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftyFence> grass_fence = registerFence(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftyFence> hay_fence = registerFence(NiftyBuilders.HAY);
    public static final RegistryObject<NiftyFence> path_fence = registerFence(NiftyBuilders.PATH);
    public static final RegistryObject<NiftyFence> brick_fence = registerFence(NiftyBuilders.BRICK);
    public static final RegistryObject<NiftyFence> quartz_fence = registerFence(NiftyBuilders.QUARTZ);
    public static final RegistryObject<NiftyFence> bone_fence = registerFence(NiftyBuilders.BONE);
    public static final RegistryObject<NiftyFence> red_nether_brick_fence = registerFence(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<NiftyFence> snow_fence = registerFence(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftyFence> ice_fence = registerFence(NiftyBuilders.ICE);
    public static final RegistryObject<NiftyFence> packed_ice_fence = registerFence(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftyFence> blue_ice_fence = registerFence(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftyFence> netherite_fence = registerFence(NiftyBuilders.NETHERITE);

    public static final RegistryObject<NiftyTrapDoor> gold_trapdoor = registerTrapdoor(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftyTrapDoor> diamond_trapdoor = registerTrapdoor(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftyTrapDoor> emerald_trapdoor = registerTrapdoor(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftyTrapDoor> lapis_trapdoor = registerTrapdoor(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftyTrapDoor> obsidian_trapdoor = registerTrapdoor(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftyTrapDoor> coal_trapdoor = registerTrapdoor(NiftyBuilders.COAL);
    public static final RegistryObject<NiftyTrapDoor> redstone_trapdoor = registerTrapdoor(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftyTrapDoor> missingno_trapdoor = registerTrapdoor(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftyTrapDoor> clay_trapdoor = registerTrapdoor(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftyTrapDoor> dirt_trapdoor = registerTrapdoor(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftyTrapDoor> grass_trapdoor = registerTrapdoor(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftyTrapDoor> hay_trapdoor = registerTrapdoor(NiftyBuilders.HAY);
    public static final RegistryObject<NiftyTrapDoor> path_trapdoor = registerTrapdoor(NiftyBuilders.PATH);
    public static final RegistryObject<NiftyTrapDoor> brick_trapdoor = registerTrapdoor(NiftyBuilders.BRICK);
    public static final RegistryObject<NiftyTrapDoor> quartz_trapdoor = registerTrapdoor(NiftyBuilders.QUARTZ);
    public static final RegistryObject<NiftyTrapDoor> bone_trapdoor = registerTrapdoor(NiftyBuilders.BONE);
    public static final RegistryObject<NiftyTrapDoor> nether_brick_trapdoor = registerTrapdoor(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<NiftyTrapDoor> red_nether_brick_trapdoor = registerTrapdoor(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<NiftyTrapDoor> snow_trapdoor = registerTrapdoor(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftyTrapDoor> ice_trapdoor = registerTrapdoor(NiftyBuilders.ICE);
    public static final RegistryObject<NiftyTrapDoor> packed_ice_trapdoor = registerTrapdoor(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftyTrapDoor> blue_ice_trapdoor = registerTrapdoor(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftyTrapDoor> netherite_trapdoor = registerTrapdoor(NiftyBuilders.NETHERITE);

    public static final RegistryObject<NiftyFenceGate> iron_fence_gate = registerFenceGate(NiftyBuilders.IRON);
    public static final RegistryObject<NiftyFenceGate> gold_fence_gate = registerFenceGate(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftyFenceGate> diamond_fence_gate = registerFenceGate(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftyFenceGate> emerald_fence_gate = registerFenceGate(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftyFenceGate> lapis_fence_gate = registerFenceGate(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftyFenceGate> obsidian_fence_gate = registerFenceGate(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftyFenceGate> coal_fence_gate = registerFenceGate(NiftyBuilders.COAL);
    public static final RegistryObject<NiftyFenceGate> redstone_fence_gate = registerFenceGate(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftyFenceGate> missingno_fence_gate = registerFenceGate(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftyFenceGate> clay_fence_gate = registerFenceGate(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftyFenceGate> dirt_fence_gate = registerFenceGate(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftyFenceGate> grass_fence_gate = registerFenceGate(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftyFenceGate> hay_fence_gate = registerFenceGate(NiftyBuilders.HAY);
    public static final RegistryObject<NiftyFenceGate> path_fence_gate = registerFenceGate(NiftyBuilders.PATH);
    public static final RegistryObject<NiftyFenceGate> brick_fence_gate = registerFenceGate(NiftyBuilders.BRICK);
    public static final RegistryObject<NiftyFenceGate> quartz_fence_gate = registerFenceGate(NiftyBuilders.QUARTZ);
    public static final RegistryObject<NiftyFenceGate> bone_fence_gate = registerFenceGate(NiftyBuilders.BONE);
    public static final RegistryObject<NiftyFenceGate> nether_brick_fence_gate = registerFenceGate(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<NiftyFenceGate> red_nether_brick_fence_gate = registerFenceGate(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<NiftyFenceGate> snow_fence_gate = registerFenceGate(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftyFenceGate> ice_fence_gate = registerFenceGate(NiftyBuilders.ICE);
    public static final RegistryObject<NiftyFenceGate> packed_ice_fence_gate = registerFenceGate(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftyFenceGate> blue_ice_fence_gate = registerFenceGate(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftyFenceGate> netherite_fence_gate = registerFenceGate(NiftyBuilders.NETHERITE);

    public static final RegistryObject<NiftyDoor> gold_door = registerDoor(NiftyBuilders.GOLD);
    public static final RegistryObject<NiftyDoor> diamond_door = registerDoor(NiftyBuilders.DIAMOND);
    public static final RegistryObject<NiftyDoor> emerald_door = registerDoor(NiftyBuilders.EMERALD);
    public static final RegistryObject<NiftyDoor> lapis_door = registerDoor(NiftyBuilders.LAPIS);
    public static final RegistryObject<NiftyDoor> obsidian_door = registerDoor(NiftyBuilders.OBSIDIAN);
    public static final RegistryObject<NiftyDoor> coal_door = registerDoor(NiftyBuilders.COAL);
    public static final RegistryObject<NiftyDoor> redstone_door = registerDoor(NiftyBuilders.REDSTONE);
    public static final RegistryObject<NiftyDoor> missingno_door = registerDoor(NiftyBuilders.MISSINGNO);
    public static final RegistryObject<NiftyDoor> clay_door = registerDoor(NiftyBuilders.CLAY);
    public static final RegistryObject<NiftyDoor> dirt_door = registerDoor(NiftyBuilders.DIRT);
    public static final RegistryObject<NiftyDoor> grass_door = registerDoor(NiftyBuilders.GRASS);
    public static final RegistryObject<NiftyDoor> hay_door = registerDoor(NiftyBuilders.HAY);
    public static final RegistryObject<NiftyDoor> path_door = registerDoor(NiftyBuilders.PATH);
    public static final RegistryObject<NiftyDoor> brick_door = registerDoor(NiftyBuilders.BRICK);
    public static final RegistryObject<NiftyDoor> quartz_door = registerDoor(NiftyBuilders.QUARTZ);
    public static final RegistryObject<NiftyDoor> bone_door = registerDoor(NiftyBuilders.BONE);
    public static final RegistryObject<NiftyDoor> nether_brick_door = registerDoor(NiftyBuilders.NETHER_BRICK);
    public static final RegistryObject<NiftyDoor> red_nether_brick_door = registerDoor(NiftyBuilders.RED_NETHER_BRICK);
    public static final RegistryObject<NiftyDoor> snow_door = registerDoor(NiftyBuilders.SNOW);
    public static final RegistryObject<NiftyDoor> ice_door = registerDoor(NiftyBuilders.ICE);
    public static final RegistryObject<NiftyDoor> packed_ice_door = registerDoor(NiftyBuilders.PACKED_ICE);
    public static final RegistryObject<NiftyDoor> blue_ice_door = registerDoor(NiftyBuilders.BLUE_ICE);
    public static final RegistryObject<NiftyDoor> netherite_door = registerDoor(NiftyBuilders.NETHERITE);

    /*public static final List<RegistryObject<Block>> test = registerArray("wool", "fence", ItemGroup.DECORATIONS);

    private static List<RegistryObject<Block>> registerArray(String matName, String blockName, ItemGroup group) {
        List<RegistryObject<Block>> blocks = new ArrayList<>();

        for (DyeColor i : DyeColor.values()) {
            String name = i.getName() + "_" + matName + "_" + blockName;
            RegistryObject<Block> reg = BLOCKS.register(name, () -> new NiftyFence(MISSINGNO));
            ITEMS.register(name, () -> new BlockItem(reg.get(), new Item.Properties().group(group)));
            blocks.add(reg);
        }

        return blocks;
    }*/

    private static RegistryObject<NiftyStairs> registerStairs(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_stairs", () -> new NiftyStairs(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 4));
    }

    private static RegistryObject<NiftySlab> registerSlab(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_slab", () -> new NiftySlab(props, builder), item ->
                registerBlockItem(item, ItemGroup.BUILDING_BLOCKS, builder, 3));
    }

    private static RegistryObject<NiftyFence> registerFence(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence", () -> new NiftyFence(props, builder), item ->
                registerBlockItem(item, ItemGroup.DECORATIONS, builder, 1));
    }

    private static RegistryObject<NiftyTrapDoor> registerTrapdoor(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder)
                .notSolid()
                .setAllowsSpawn((state, reader, pos, type) -> false);

        return registerBlock(builder.name + "_trapdoor", () -> new NiftyTrapDoor(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 5));
    }

    private static RegistryObject<NiftyFenceGate> registerFenceGate(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder);

        return registerBlock(builder.name + "_fence_gate", () -> new NiftyFenceGate(props, builder), item ->
                registerBlockItem(item, ItemGroup.REDSTONE, builder, 2));
    }

    private static RegistryObject<NiftyDoor> registerDoor(NiftyBuilder builder) {
        AbstractBlock.Properties props = PropertiesHelper.createProps(builder).notSolid();

        return registerBlock(builder.name + "_door", () -> new NiftyDoor(props, builder), item ->
                registerBlockItemDoor(item, builder, 0));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, ItemGroup group, NiftyBuilder nifty, int fuelindex) {
        return () -> new NiftyBlockItem(block.get(), PropertiesHelper.createProps(nifty, group), nifty, fuelindex);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, NiftyBuilder nifty, int fuelindex) {
        return () -> new NiftyTallBlockItem(block.get(), PropertiesHelper.createProps(nifty, ItemGroup.REDSTONE), nifty, fuelindex);
    }
}