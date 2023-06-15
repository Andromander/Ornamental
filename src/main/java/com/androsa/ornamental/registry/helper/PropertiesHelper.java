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
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of()
                .mapColor(builder.color)
                .strength(builder.hardness, builder.resistance)
                .sound(builder.blockSetType.soundType())
                .friction(builder.slipperiness)
                .pushReaction(builder.pushReaction);
        if (builder.light > 0) props.lightLevel((state) -> builder.light);
        if (builder.doesTick) props.randomTicks();
        if (!builder.isSolid) props.noOcclusion();
        if (builder.postProcess) props.hasPostProcess((state, getter, pos) -> true);
        if (builder.emissiveRender) props.emissiveRendering((state, getter, pos) -> true);
        if (builder.requiresTool) props.requiresCorrectToolForDrops();
        if (builder.conductsRedstone != null) props.isRedstoneConductor(builder.conductsRedstone);
        if (builder.entitySpawnPredicate != null) props.isValidSpawn(builder.entitySpawnPredicate);

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
