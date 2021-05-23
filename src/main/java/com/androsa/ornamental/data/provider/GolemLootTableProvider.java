package com.androsa.ornamental.data.provider;

import net.minecraft.block.Block;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

public class GolemLootTableProvider extends EntityLootTables {

    public void add(Supplier<? extends EntityType<?>> entity, LootTable.Builder table) {
        super.add(entity.get(), table);
    }

    public LootTable.Builder flowerGolemTable(Block flower, IItemProvider drop) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(flower)
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(drop)
                                .apply(SetCount.setCount(RandomValueRange.between(3.0F, 5.0F)))));
    }

    public LootTable.Builder golemTable(IItemProvider drop) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(drop)
                                .apply(SetCount.setCount(RandomValueRange.between(3.0F, 5.0F)))));
    }

    public LootTable.Builder golemTableBlock(IItemProvider drop) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(drop)
                                .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))));
    }
}
