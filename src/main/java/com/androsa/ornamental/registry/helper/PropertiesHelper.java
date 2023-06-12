package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Helper class for creating Properties quickly.
 */
public class PropertiesHelper {

    /**
     * Helper for creating Properties for Blocks.
     * @param builder the material being used for that block.
     */
    public static BlockBehaviour.Properties createProps(OrnamentBuilder builder) {
        OrnamentBuilder getbuilder = builder.getBuilder();
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of()
                .mapColor(getbuilder.color)
                .strength(getbuilder.hardness, getbuilder.resistance)
                .sound(getbuilder.blockSetType.soundType())
                .friction(getbuilder.slipperiness)
                .pushReaction(getbuilder.pushReaction);
        if (getbuilder.light > 0) props.lightLevel((state) -> getbuilder.light);
        if (getbuilder.doesTick) props.randomTicks();
        if (!getbuilder.isSolid) props.noOcclusion();
        if (getbuilder.postProcess) props.hasPostProcess((state, getter, pos) -> true);
        if (getbuilder.emissiveRender) props.emissiveRendering((state, getter, pos) -> true);
        if (getbuilder.requiresTool) props.requiresCorrectToolForDrops();
        if (getbuilder.conductsRedstone != null) props.isRedstoneConductor(getbuilder.conductsRedstone);
        if (getbuilder.entitySpawnPredicate != null) props.isValidSpawn(getbuilder.entitySpawnPredicate);

        return props;
    }

    /**
     * Helper for creating Properties for Items.
     * @param builder the material being used for that item or the item's block.
     */
    public static Item.Properties createItem(OrnamentBuilder builder) {
        Item.Properties props = new Item.Properties();
        if (builder.fireproof) props.fireResistant();

        return props;
    }
}
