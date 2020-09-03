package com.androsa.ornamental.builder;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

/**
 * OrnamentBuilder is a system designed to create "templates" of materials for the various decoration blocks.
 * This is to save on copy-pasting values, while also only requiring certain values if they require altering.
 * If any method is not used, a default value will be assigned. No important value is left null, the only requirement is a name for the material.
 *
 * HOW TO USE:
 * 1. Create a new OrnamentBuilder, supplying a String for the name.
 * 2. Follow the documentation provided by each method and call each appropriate one where required.
 * 3. Once sufficiently created, pass through an Ornament block appropriately.
 *
 * Registration of the blocks can be done however you please.
 * Field "name" can be used in conjunction with a block suffix to quickly create a registry name for ease of registration.
 * This class can be extended to add custom setters for use in other mods for compatibility.
 */
public class OrnamentBuilder {

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
    /** This should only be altered by {@link OrnamentBuilder#config(Supplier)}. CHANGING THIS WITHOUT AN ENTRY WILL CAUSE A CRASH */
    public boolean hasConfig = false;
    @Deprecated
    public boolean isDirt = false;
    public boolean mealGrass = false;
    @Deprecated
    public boolean isGrass = false;
    public boolean hoeDirt = false;
    public boolean shovelPath = false;
    @Deprecated
    public boolean isPath = false;
    public boolean pathShape = false;
    public boolean hoeGrass = false;
    @Deprecated
    public boolean isIce = false;
    public boolean canMelt = false;
    public Block meltResult = Blocks.WATER;
    public boolean canVaporise = false;
    public boolean isSolid = true;
    public boolean breakableCull = false;
    public PushReaction pushReaction = material.getPushReaction();
    public Supplier<ForgeConfigSpec.BooleanValue> booleanValue = null;

    /**
     * Create a template material for an Ornament block
     * @param name The name to supply through registration. Used for ease of naming
     */
    public OrnamentBuilder(String name) {
        this.name = name;
    }

    /**
     * Setter for block's properties. Will take the block's Material to determine MaterialColor
     * @param material The Material of the block
     */
    public OrnamentBuilder properties(Material material) {
        this.material = material;
        this.color = material.getColor();
        return this;
    }

    /**
     * Setter for the block's properties.
     * @param material The Material of the block
     * @param color The MaterialColor of the block
     */
    public OrnamentBuilder properties(Material material, MaterialColor color) {
        this.material = material;
        this.color = color;
        return this;
    }

    /**
     * Setter for the block's sound. Will default to SoundType.ROCK if unused.
     * @param sound The SoundType of the block
     */
    public OrnamentBuilder sound(SoundType sound) {
        this.sound = sound;
        return this;
    }

    /**
     * Setter for the block's hardness. Will leave the block's resistance to 0.0F.
     * For setting hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float)} or {@link OrnamentBuilder#hardnessAndResistance(float, float)}
     * @param amount The block's hardness.
     */
    public OrnamentBuilder hardness(float amount) {
        this.hardness = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance. Both values will match.
     * For setting separate hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float, float)}
     * @param amount The block's hardness and resistance
     */
    public OrnamentBuilder hardnessAndResistance(float amount) {
        this.hardness = amount;
        this.resistance = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance.
     * For ease of use, to set matching hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float)}
     * @param hard The block's hardness
     * @param resist The block's resistance
     */
    public OrnamentBuilder hardnessAndResistance(float hard, float resist) {
        this.hardness = hard;
        this.resistance = resist;
        return this;
    }

    /**
     * Setter for the block's ToolType. Harvest level will be 0.
     * To set a harvest level, use {@link OrnamentBuilder#tool(ToolType, int, boolean)}
     * @param tool The block's ToolType
     * @param required If the block requires this tool. Used to enable {@link AbstractBlock.Properties#setRequiresTool()}
     */
    public OrnamentBuilder tool(ToolType tool, boolean required) {
        this.harvestTool = tool;
        this.requiresTool = required;
        return this;
    }

    /**
     * Setter for the blocks' harvest tool and level.
     * To set only a harvst tool, use {@link OrnamentBuilder#tool(ToolType, boolean)}
     * @param tool The block's ToolType
     * @param level The block's harvest level
     * @param required If the block requires this tool. Used to enable {@link AbstractBlock.Properties#setRequiresTool()}
     */
    public OrnamentBuilder tool(ToolType tool, int level, boolean required) {
        this.harvestTool = tool;
        this.harvestLevel = level;
        this.requiresTool = required;
        return this;
    }

    /**
     * Setter for the damage multiplier when taking fall damage on the block. Defaults to 1.0F (standard handling)
     * @param amount The damage multiplier
     */
    public OrnamentBuilder fall(float amount) {
        this.fallMultiplier = amount;
        return this;
    }

    /**
     * Setter for the slipperiness of a block when moving on top of it. Defaults to 0.6F (average movement)
     * NOTE: On blocks like lower half Slabs, slipperiness is undetected. This is how vanilla behaves.
     * @param amount The amount of slip
     */
    public OrnamentBuilder slip(float amount) {
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
    public OrnamentBuilder burnTime(int door, int fence, int gate, int slab, int stairs, int trap) {
        this.burnTime = new int[]{door, fence, gate, slab, stairs, trap};
        return this;
    }

    /**
     * Sets the block to be opened by hand. Used in blocks with an open/close state. Redstone power still applies if false.
     */
    public OrnamentBuilder canOpen() {
        this.canOpen = true;
        return this;
    }

    /**
     * Sets the block to emit Redstone power. Power output is automatically determined per block.
     */
    public OrnamentBuilder hasPower() {
        this.hasPower = true;
        return this;
    }

    /**
     * Sets the block to tick randomly. Used to enable {@link AbstractBlock.Properties#tickRandomly()}
     */
    public OrnamentBuilder ticks() {
        this.doesTick = true;
        return this;
    }

    /**
     * Sets the block's item to resist being destroyed in fire or lava. Used to enable {@link Item.Properties#isImmuneToFire()}
     */
    public OrnamentBuilder isFireproof() {
        this.fireproof = true;
        return this;
    }

    /**
     * Sets the entity spawn logic on the block. Used in {@link AbstractBlock.Properties#setAllowsSpawn(AbstractBlock.IExtendedPositionPredicate)}
     * If left null (unused), will default to regular placement logic.
     * @param predicate The spawn predicate to test. If true, an entity can spawn on it.
     */
    public OrnamentBuilder setCanEntitySpawn(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> predicate) {
        this.entitySpawnPredicate = predicate;
        return this;
    }

    /**
     * Sets if the block is Dirt. Used to turn Dirt into Grass blocks. THIS IS FOR DIRT BLOCKS ONLY.
     * @deprecated use {@link OrnamentBuilder#boneMealToGrass()} to allow X -> Path conversion.
     */
    @Deprecated
    public OrnamentBuilder isDirtMaterial() {
        this.isDirt = true;
        return this;
    }

    /**
     * Sets if the block can become Grass with Bone Meal. If set, the block will become the respective Grass block when Bone Meal is used on it.
     */
    public OrnamentBuilder boneMealToGrass() {
        this.mealGrass = true;
        return this;
    }

    /**
     * Sets if the block is Grass. Used to turn Grass into Dirt or Grass Path. THIS IS FOR GRASS BLOCKS ONLY
     * @deprecated use {@link OrnamentBuilder#hoeToDirt()} to allow X -> Dirt conversion and {@link OrnamentBuilder#shovelToPath()} to allow X -> Path conversion
     */
    @Deprecated
    public OrnamentBuilder isGrassMaterial() {
        this.isGrass = true;
        return this;
    }

    /**
     * Sets if the block can become Dirt with a Hoe. If set, the block will become the respective Dirt block when a Hoe is used on it.
     */
    public OrnamentBuilder hoeToDirt() {
        this.hoeDirt = true;
        return this;
    }

    /**
     * Sets if the block can become Path with a Shovel. If set, the block will become the respective Path block when a Shovel is used on it.
     */
    public OrnamentBuilder shovelToPath() {
        this.shovelPath = true;
        return this;
    }

    /**
     * Sets if the block is Grass Path. Used to turn Grass Path into Grass and alter block shape. THIS IS FOR GRASS PATH BLOCKS ONLY.
     * @deprecated use {@link OrnamentBuilder#usePathShapes()} for altered height and {@link OrnamentBuilder#hoeToGrass()} ()} to allow X -> Grass conversion.
     */
    @Deprecated
    public OrnamentBuilder isPathMaterial() {
        this.isPath = true;
        return this;
    }

    /**
     * Sets if the block's VoxelShape should be altered. If true, the height of the blocks will be lowered by 1.
     */
    public OrnamentBuilder usePathShapes() {
        this.pathShape = true;
        return this;
    }

    /**
     * Sets if the block can become Grass with a Hoe. If set, the block will become the respective Grass block when a Hoe is used.
     */
    public OrnamentBuilder hoeToGrass() {
        this.hoeGrass = true;
        return this;
    }

    /**
     * Sets if the block is Ice. Used for harvesting and melting the blocks. THIS IS FOR ICE BLOCKS ONLY.
     * @deprecated Use {@link #canMelt(Block, boolean)} for melting logic, {@link OrnamentBuilder#notSolid()} to make the block a non-solid, and {@link OrnamentBuilder#doBreakableBlockCull()} for translucent block culling.
     */
    @Deprecated
    public OrnamentBuilder isIceMaterial() {
        this.isIce = true;
        return this;
    }

    /**
     * Sets if the block can melt. When this is set, the block will melt in high light levels at random by default.
     * The block may also vaporise if the dimension does so and is set to here.
     * Ideally, a Fluid should be set here. If not, will default to Water.
     * @param melt The resulting block if the block melts. Best use is a fluid.
     * @param vaporise Sets if the block may vaporise in certain dimensions. If true, will turn to Air in said dimensions.
     */
    public OrnamentBuilder canMelt(Block melt, boolean vaporise) {
        this.canMelt = true;
        this.meltResult = melt;
        if (!meltResult.getDefaultState().getMaterial().isLiquid()) {
            OrnamentalMod.LOGGER.warn("Supplied melt result for {} was not a liquid, was {}! Defaulting to Water.", name, melt.getDefaultState().getMaterial());
            meltResult = Blocks.WATER;
        }
        this.canVaporise = vaporise;
        return this;
    }

    /**
     * Sets if the block is not solid if the block would normally be solid. Used to enable {@link AbstractBlock.Properties#notSolid()}.
     */
    public OrnamentBuilder notSolid() {
        this.isSolid = false;
        return this;
    }

    /**
     * Sets if the block should have invisible faces. If true, neighboring blocks of the same ornament material will cull their face, akin to BreakableBlock.
     * This logic applies to Slabs by default, where it will only check if the neighbor block shares the same material and is Double.
     * Note: this will only logically work with translucent or transparent blocks. Fully opaque blocks will not see any change.
     */
    public OrnamentBuilder doBreakableBlockCull() {
        this.breakableCull = true;
        return this;
    }

    /**
     * Sets an overriding PushReaction for a block if the block normally checks based on Material. Will check the Material's reaction by default.
     * Some blocks may not follow this, ie. Doors always have PushReaction.DESTROY.
     * @param reaction The push reaction this material should use.
     */
    public OrnamentBuilder pushReactOverride(PushReaction reaction) {
        this.pushReaction = reaction;
        return this;
    }

    /**
     * Sets the config value of a block. Used for showing/hiding blocks by material, as well as crafting and harvesting.
     * If left unused, all config checks will be ignored.
     * Adding a config value will also add it to a ConfigCondition.
     * @param entry The supplied BooleanValue for the block to use.
     */
    public OrnamentBuilder config(Supplier<ForgeConfigSpec.BooleanValue> entry) {
        this.booleanValue = entry;
        this.hasConfig = true;
        ConfigCondition.putValue(this, entry);
        return this;
    }
}
