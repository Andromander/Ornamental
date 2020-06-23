package com.androsa.nifty.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.SetCount;

import java.util.function.Supplier;

public class GolemLootTableProvider extends EntityLootTables {

    public void registerLootTable(Supplier<? extends EntityType<?>> entity, LootTable.Builder table) {
        super.registerLootTable(entity.get(), table);
    }

    public LootTable.Builder flowerGolemTable(Block flower, IItemProvider drop) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(flower)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))));
    }

    public LootTable.Builder golemTable(IItemProvider drop) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))));
    }

    public LootTable.Builder golemTableBlock(IItemProvider drop) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))));
    }
}
