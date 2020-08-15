package com.androsa.nifty;

import com.androsa.nifty.builder.NiftyBuilder;
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
    public static AbstractBlock.Properties createProps(NiftyBuilder builder) {
        AbstractBlock.Properties props = AbstractBlock.Properties.create(builder.material, builder.color)
                .hardnessAndResistance(builder.hardness, builder.resistance)
                .sound(builder.sound)
                .harvestTool(builder.harvestTool)
                .harvestLevel(builder.harvestLevel)
                .slipperiness(builder.slipperiness);
        if (builder.doesTick) props.tickRandomly();
        if (builder.isIce) props.notSolid();
        if (builder.requiresTool) props.setRequiresTool();
        if (builder.entitySpawnPredicate != null) props.setAllowsSpawn(builder.entitySpawnPredicate);

        return props;
    }

    /**
     * Helper for creating Properties for Items.
     * @param builder the material being used for that item or the item's block.
     * @param group the ItemGroup the item should appear in.
     */
    public static Item.Properties createProps(NiftyBuilder builder, ItemGroup group) {
        Item.Properties props = new Item.Properties()
                .group(group);
        if (builder.fireproof) props.isImmuneToFire();

        return props;
    }
}
