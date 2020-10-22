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
        super.registerDropSelfLootTable(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        registerLootTable(block.get(), BlockLootTables::droppingSlab);
    }

    public void dropDoor(Supplier<? extends Block> block) {
        registerLootTable(block.get(), (result) -> droppingWhen(result, DoorBlock.HALF, DoubleBlockHalf.LOWER));
    }

    public void dropPole(Supplier<? extends Block> block) {
        registerLootTable(block.get(), OrnamentLootTableProvider::droppingPole);
    }

    public void dropBeam(Supplier<? extends Block> block) {
        registerLootTable(block.get(), OrnamentLootTableProvider::droppingBeam);
    }

    protected static LootTable.Builder droppingPole(Block pole) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(withExplosionDecay(pole, ItemLootEntry.builder(pole)
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.T_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.L_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.R_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.B_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.TL_BR))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.TR_BL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.TL_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.TR_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.BL_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.BR_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(4))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentPole.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }

    protected static LootTable.Builder droppingBeam(Block pole) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(withExplosionDecay(pole, ItemLootEntry.builder(pole)
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.T_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.L_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.R_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.B_HALF))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.TL_BR))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.TR_BL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.TL_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.TR_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.BL_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(3))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.BR_FILL))))
                                        .acceptFunction(SetCount.builder(ConstantRange.of(4))
                                                .acceptCondition(BlockStateProperty.builder(pole).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(OrnamentBeam.TYPE, PoleType.FULL))))
                                )
                        )
                );
    }
}
