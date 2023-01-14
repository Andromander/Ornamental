package com.androsa.ornamental.data.provider;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.Set;
import java.util.function.Supplier;

public abstract class OrnamentLootTableProvider extends BlockLootSubProvider {

    protected OrnamentLootTableProvider(Set<Item> resistant, FeatureFlagSet features) {
        super(resistant, features);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        add(block.get(), this::createSlabItemTable);
    }

    public void dropDoor(Supplier<? extends Block> block) {
        add(block.get(), (result) -> createSinglePropConditionTable(result, DoorBlock.HALF, DoubleBlockHalf.LOWER));
    }
}
