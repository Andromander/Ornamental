package com.androsa.nifty.builder;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

/**
 * NiftyBuilder is a system designed to create "templates" of materials for the various decoration blocks.
 * This is to save on copy-pasting values, while also only requiring certain values if they require altering.
 * If any method is not used, a default value will be assigned. No important value is left null, the only requirement is a name for the material.
 *
 * HOW TO USE:
 * 1. Create a new NiftyBuilder, supplying a String for the name.
 * 2. Follow the documentation provided by each method and call each appropriate one where required.
 * 3. Once sufficiently created, pass through a Nifty block appropriately.
 *
 * Registration of the blocks can be done however you please.
 * Field "name" can be used in conjunction with a block suffix to quickly create a registry name for ease of registration.
 * This class can be extended to add custom setters for use in other mods for compatibility.
 */
public class NiftyBuilder {

    public final String name;
    public Material material = Material.ROCK;
    public MaterialColor color = material.getColor();
    public SoundType sound = SoundType.STONE;
    public float hardness = 0.0F;
    public float resistance = 0.0F;
    public ToolType harvestTool = null;
    public int harvestLevel = 0;
    public float fallMultiplier = 1.0F;
    public float slipperiness = 0.6F;
    public int[] burnTime = new int[]{0,0,0,0,0,0};
    public boolean canOpen = false;
    public boolean hasPower = false;
    public boolean doesTick = false;
    public boolean requiresTool = false;
    public boolean fireproof = false;
    public AbstractBlock.IExtendedPositionPredicate<EntityType<?>> entitySpawnPredicate = null;
    /** This should only be altered by {@link NiftyBuilder#config(Supplier)}. CHANGING THIS WITHOUT AN ENTRY WILL CAUSE A CRASH */
    public boolean hasConfig = false;
    public boolean isDirt = false;
    public boolean isGrass = false;
    public boolean isPath = false;
    public boolean isIce = false;
    public Supplier<ForgeConfigSpec.BooleanValue> booleanValue = null;

    /**
     * Create a template material for a Nifty block
     * @param name The name to supply through registration. Used for ease of naming
     */
    public NiftyBuilder(String name) {
        this.name = name;
    }

    /**
     * Setter for block's properties. Will take the block's Material to determine MaterialColor
     * @param material The Material of the block
     */
    public NiftyBuilder properties(Material material) {
        this.material = material;
        this.color = material.getColor();
        return this;
    }

    /**
     * Setter for the block's properties.
     * @param material The Material of the block
     * @param color The MaterialColor of the block
     */
    public NiftyBuilder properties(Material material, MaterialColor color) {
        this.material = material;
        this.color = color;
        return this;
    }

    /**
     * Setter for the block's sound. Will default to SoundType.ROCK if unused.
     * @param sound The SoundType of the block
     */
    public NiftyBuilder sound(SoundType sound) {
        this.sound = sound;
        return this;
    }

    /**
     * Setter for the block's hardness. Will leave the block's resistance to 0.0F.
     * For setting hardness and resistance, use {@link NiftyBuilder#hardnessAndResistance(float)} or {@link NiftyBuilder#hardnessAndResistance(float, float)}
     * @param amount The block's hardness.
     */
    public NiftyBuilder hardness(float amount) {
        this.hardness = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance. Both values will match.
     * For setting separate hardness and resistance, use {@link NiftyBuilder#hardnessAndResistance(float, float)}
     * @param amount The block's hardness and resistance
     */
    public NiftyBuilder hardnessAndResistance(float amount) {
        this.hardness = amount;
        this.resistance = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance.
     * For ease of use, to set matching hardness and resistance, use {@link NiftyBuilder#hardnessAndResistance(float)}
     * @param hard The block's hardness
     * @param resist The block's resistance
     */
    public NiftyBuilder hardnessAndResistance(float hard, float resist) {
        this.hardness = hard;
        this.resistance = resist;
        return this;
    }

    /**
     * Setter for the block's ToolType. Harvest level will be 0.
     * To set a harvest level, use {@link NiftyBuilder#tool(ToolType, int, boolean)}
     * @param tool The block's ToolType
     * @param required If the block requires this tool. Used to enable {@link AbstractBlock.Properties#setRequiresTool()}
     */
    public NiftyBuilder tool(ToolType tool, boolean required) {
        this.harvestTool = tool;
        this.requiresTool = required;
        return this;
    }

    /**
     * Setter for the blocks' harvest tool and level.
     * To set only a harvst tool, use {@link NiftyBuilder#tool(ToolType, boolean)}
     * @param tool The block's ToolType
     * @param level The block's harvest level
     * @param required If the block requires this tool. Used to enable {@link AbstractBlock.Properties#setRequiresTool()}
     */
    public NiftyBuilder tool(ToolType tool, int level, boolean required) {
        this.harvestTool = tool;
        this.harvestLevel = level;
        this.requiresTool = required;
        return this;
    }

    /**
     * Setter for the damage multiplier when taking fall damage on the block. Defaults to 1.0F (standard handling)
     * @param amount The damage multiplier
     */
    public NiftyBuilder fall(float amount) {
        this.fallMultiplier = amount;
        return this;
    }

    /**
     * Setter for the slipperiness of a block when moving on top of it. Defaults to 0.6F (average movement)
     * NOTE: On blocks like lower half Slabs, slipperiness is undetected. This is how vanilla behaves.
     * @param amount The amount of slip
     */
    public NiftyBuilder slip(float amount) {
        this.slipperiness = amount;
        return this;
    }

    /**
     * Setters for a block's burn time (measured by ticks). This is applied to the block's item. Defaults to all 0 (does not burn)
     * For default behaviour, set value to -1. Any other value above 0, 1 second = 20 ticks
     * @param door Door burn time
     * @param fence Fence burn time
     * @param gate Fence Gate burn time
     * @param slab Slab burn time
     * @param stairs Stairs burn time
     * @param trap Trap Door burn time
     */
    public NiftyBuilder burnTime(int door, int fence, int gate, int slab, int stairs, int trap) {
        this.burnTime = new int[]{door, fence, gate, slab, stairs, trap};
        return this;
    }

    /**
     * Sets the block to be opened by hand. Used in blocks with an open/close state. Redstone power still applies if false.
     */
    public NiftyBuilder canOpen() {
        this.canOpen = true;
        return this;
    }

    /**
     * Sets the block to emit Redstone power. Power output is automatically determined per block.
     */
    public NiftyBuilder hasPower() {
        this.hasPower = true;
        return this;
    }

    /**
     * Sets the block to tick randomly. Used to enable {@link AbstractBlock.Properties#tickRandomly()}
     */
    public NiftyBuilder ticks() {
        this.doesTick = true;
        return this;
    }

    /**
     * Sets the block's item to resist being destroyed in fire or lava. Used to enable {@link Item.Properties#isImmuneToFire()}
     */
    public NiftyBuilder isFireproof() {
        this.fireproof = true;
        return this;
    }

    /**
     * Sets the entity spawn logic on the block. Used in {@link AbstractBlock.Properties#setAllowsSpawn(AbstractBlock.IExtendedPositionPredicate)}
     * If left null (unused), will default to regular placement logic.
     * @param predicate The spawn predicate to test. If true, an entity can spawn on it.
     */
    public NiftyBuilder setCanEntitySpawn(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> predicate) {
        this.entitySpawnPredicate = predicate;
        return this;
    }

    /**
     * Sets if the block is Dirt. Used to turn Dirt into Grass blocks. THIS IS FOR DIRT BLOCKS ONLY
     */
    public NiftyBuilder isDirtMaterial() {
        this.isDirt = true;
        return this;
    }

    /**
     * Sets if the block is Grass. Used to turn Grass into Dirt or Grass Path. THIS IS FOR GRASS BLOCKS ONLY
     */
    public NiftyBuilder isGrassMaterial() {
        this.isGrass = true;
        return this;
    }

    /**
     * Sets if the block is Grass Path. Used to turn Grass Path into Grass and alter block shape. THIS IS FOR GRASS PATH BLOCKS ONLY
     */
    public NiftyBuilder isPathMaterial() {
        this.isPath = true;
        return this;
    }

    /**
     * Sets if the block is Ice. Used for harvesting and melting the blocks. THIS IS FOR ICE BLOCKS ONLY
     */
    public NiftyBuilder isIceMaterial() {
        this.isIce = true;
        return this;
    }

    /**
     * Sets the config value of a block. Used for showing/hiding blocks by material, as well as crafting and harvesting.
     * If left unused, all config checks will be ignored
     * @param entry The supplied BooleanValue for the block to use
     */
    public NiftyBuilder config(Supplier<ForgeConfigSpec.BooleanValue> entry) {
        this.booleanValue = entry;
        this.hasConfig = true;
        return this;
    }
}
