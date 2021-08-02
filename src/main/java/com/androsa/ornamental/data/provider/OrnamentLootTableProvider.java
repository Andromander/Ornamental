package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.OrnamentBeam;
import com.androsa.ornamental.blocks.OrnamentPole;
import com.androsa.ornamental.blocks.PoleType;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.Supplier;

public abstract class OrnamentLootTableProvider extends BlockLoot {

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        add(block.get(), BlockLoot::createSlabItemTable);
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
                        .setRolls(ConstantValue.exactly(1))
                        .add(applyExplosionDecay(pole, LootItem.lootTableItem(pole)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.T_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.L_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.R_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.B_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TL_BR))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TR_BL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TL_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.TR_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.BL_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.BR_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentPole.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }

    protected static LootTable.Builder droppingBeam(Block pole) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(applyExplosionDecay(pole, LootItem.lootTableItem(pole)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.T_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.L_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.R_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.B_HALF))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TL_BR))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TR_BL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TL_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.TR_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.BL_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.BR_FILL))))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pole).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OrnamentBeam.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }
}
