package com.androsa.ornamental.registry;

import com.androsa.ornamental.builder.OrnamentBuilder;
import net.minecraft.world.item.CreativeModeTab;
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
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of(builder.material, builder.color)
                .strength(builder.hardness, builder.resistance)
                .sound(builder.sound)
                .friction(builder.slipperiness);
        if (builder.doesTick) props.randomTicks();
        if (!builder.isSolid) props.noOcclusion();
        if (builder.requiresTool) props.requiresCorrectToolForDrops();
        if (builder.entitySpawnPredicate != null) props.isValidSpawn(builder.entitySpawnPredicate);

        return props;
    }

    /**
     * Helper for creating Properties for Items.
     * @param builder the material being used for that item or the item's block.
     * @param group the ItemGroup the item should appear in.
     */
    public static Item.Properties createProps(OrnamentBuilder builder, CreativeModeTab group) {
        Item.Properties props = new Item.Properties()
                .tab(group);
        if (builder.fireproof) props.fireResistant();

        return props;
    }
}
