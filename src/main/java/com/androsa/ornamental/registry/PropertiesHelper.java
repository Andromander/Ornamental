package com.androsa.ornamental.registry;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * Helper class for creating Properties quickly.
 */
public class PropertiesHelper {

    /**
     * Helper for creating Properties for Blocks.
     * @param builder the material being used for that block.
     */
    public static AbstractBlock.Properties createProps(OrnamentBuilder builder) {
        AbstractBlock.Properties props = AbstractBlock.Properties.of(builder.material, builder.color)
                .strength(builder.hardness, builder.resistance)
                .sound(builder.sound)
                .harvestTool(builder.harvestTool)
                .harvestLevel(builder.harvestLevel)
                .friction(builder.slipperiness);
        if (builder.doesTick) props.randomTicks();
        if (builder.isIce || !builder.isSolid) props.noOcclusion();
        if (builder.requiresTool) props.requiresCorrectToolForDrops();
        if (builder.entitySpawnPredicate != null) props.isValidSpawn(builder.entitySpawnPredicate);

        return props;
    }

    /**
     * Helper for creating Properties for Items.
     * @param builder the material being used for that item or the item's block.
     * @param group the ItemGroup the item should appear in.
     */
    public static Item.Properties createProps(OrnamentBuilder builder, ItemGroup group) {
        Item.Properties props = new Item.Properties()
                .tab(group);
        if (builder.fireproof) props.fireResistant();

        return props;
    }
}
