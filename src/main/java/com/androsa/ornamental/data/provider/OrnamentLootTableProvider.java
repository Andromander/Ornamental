package com.androsa.ornamental.data.provider;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.state.properties.DoubleBlockHalf;

import java.util.function.Supplier;

public abstract class OrnamentLootTableProvider extends BlockLootTables {

    public void dropSelf(Supplier<? extends Block> block) {
        super.registerDropSelfLootTable(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        registerLootTable(block.get(), BlockLootTables::droppingSlab);
    }

    public void dropDoor(Supplier<? extends Block> block) {
        registerLootTable(block.get(), (result) -> droppingWhen(result, DoorBlock.HALF, DoubleBlockHalf.LOWER));
    }
}
