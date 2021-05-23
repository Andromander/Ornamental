package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.PoleType;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.DoubleBlockHalf;

import java.util.function.Supplier;

public abstract class OrnamentLootTableProvider extends BlockLootTables {

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        add(block.get(), BlockLootTables::createSlabItemTable);
    }

    public void dropDoor(Supplier<? extends Block> block) {
        add(block.get(), (result) -> createSinglePropConditionTable(result, DoorBlock.HALF, DoubleBlockHalf.LOWER));
    }

    public void dropPole(Supplier<? extends Block> block) {
        add(block.get(), OrnamentLootTableProvider::droppingPole);
    }

    public void dropBeam(Supplier<? extends Block> block) {
        add(block.get(), OrnamentLootTableProvider::droppingBeam);
    }

    protected static LootTable.Builder droppingPole(Block pole) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(applyExplosionDecay(pole, ItemLootEntry.lootTableItem(pole)
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.T_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.L_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.R_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.B_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TL_BR))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TR_BL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TL_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TR_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.BL_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.BR_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(4))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }

    protected static LootTable.Builder droppingBeam(Block pole) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(applyExplosionDecay(pole, ItemLootEntry.lootTableItem(pole)
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.T_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.L_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.R_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.B_HALF))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TL_BR))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(2))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TR_BL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TL_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TR_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.BL_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(3))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.BR_FILL))))
                                        .apply(SetCount.setCount(ConstantRange.exactly(4))
                                                .when(BlockStateProperty.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }
}
