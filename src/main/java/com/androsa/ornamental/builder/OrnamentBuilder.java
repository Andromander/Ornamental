package com.androsa.ornamental.builder;

import com.androsa.ornamental.OrnamentalMod;
import com.google.common.collect.Lists;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OrnamentBuilder is a system designed to create "templates" of materials for the various decoration blocks.<br>
 * This is to save on copy-pasting values, while also only requiring certain values if they require altering.<br>
 * If any method is not used, a default value will be assigned. No important value is left null, the only requirement is a name for the material.<br><br>
 *
 * HOW TO USE:<br>
 * 1. Create a new OrnamentBuilder, supplying a String for the name.<br>
 * 2. Follow the documentation provided by each method and call each appropriate one where required.<br>
 * 3. Once sufficiently created, pass through an Ornament block appropriately.<br><br>
 *
 * Registration of the blocks can be done however you please.<br>
 * Field "name" can be used in conjunction with a block suffix to quickly create a registry name for ease of registration.<br>
 * This class can be extended to add custom setters for use in other mods for compatibility.<br>
 */
public class OrnamentBuilder {

    public final String name;
    public Material material = Material.STONE;
    public MaterialColor color = material.getColor();
    public SoundType sound = SoundType.STONE;
    public float hardness = 0.0F;
    public float resistance = 0.0F;
    public float fallMultiplier = 1.0F;
    public float slipperiness = 0.6F;
    public int[] burnTime = new int[]{0,0,0,0,0,0,0,0,0};
    public boolean canOpen = false;
    public SoundEvent[] trapdoorSounds = new SoundEvent[]{SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE};
    public SoundEvent[] fencegateSounds = new SoundEvent[]{SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE};
    public SoundEvent[] doorSounds = new SoundEvent[]{SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE};
    public SoundEvent[] saddledoorSounds = new SoundEvent[]{SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE};
    public boolean hasPower = false;
    public boolean doesTick = false;
    public boolean requiresTool = false;
    public boolean fireproof = false;
    public BlockBehaviour.StateArgumentPredicate<EntityType<?>> entitySpawnPredicate = null;
    public boolean mealGrass = false;
    public boolean hoeDirt = false;
    public boolean shovelPath = false;
    public boolean pathShape = false;
    public boolean hoeGrass = false;
    public boolean canMelt = false;
    public Block meltResult = Blocks.WATER;
    public boolean canVaporise = false;
    public boolean isSolid = true;
    public boolean breakableCull = false;
    public PushReaction pushReaction = material.getPushReaction();
    public List<SoundEvent> projectileHitSounds = new ArrayList<>();
    public List<List<RegistryObject<? extends Block>>> blockTags = new ArrayList<>();
    public List<List<RegistryObject<? extends Block>>> itemTags = new ArrayList<>();

    /**
     * Create a template material for an Ornament block
     * @param name The name to supply through registration. Used for ease of naming
     */
    public OrnamentBuilder(String name) {
        this.name = name;
    }

    /**
     * When extending OrnamentBuilder, access to the OrnamentBuilder properties will be lost. This method can be used to get access to properties here.
     */
    public OrnamentBuilder getBuilder() {
        return this;
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
     * Setter for the block's sound. Will default to SoundType.STONE if unused.
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
     * Setter for whether a block needs the correct tool to be harvested. Used to enable {@link BlockBehaviour.Properties#requiresCorrectToolForDrops()}
     */
    public OrnamentBuilder requiresTool() {
        this.requiresTool = true;
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
     *
     * Indexes are as follows:
     * 0 = Door
     * 1 = Fence
     * 2 = Fence Gate
     * 3 = Slab
     * 4 = Stairs
     * 5 = Trap Door
     * 6 = Pole
     * 7 = Beam
     * 8 = Wall
     * 9 = Saddle Door
     */
    public OrnamentBuilder burnTime(int... times) {
        this.burnTime = Arrays.stream(times).toArray();
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
     * Sets the sounds for opening and closing a Trapdoor.
     * @param open The open sound.
     * @param close The close sound;
     */
    public OrnamentBuilder trapdoorSounds(SoundEvent open, SoundEvent close) {
        this.trapdoorSounds = new SoundEvent[]{ open, close };
        return this;
    }

    /**
     * Sets the sounds for opening and closing a Fence Gate.
     * @param open The open sound.
     * @param close The close sound;
     */
    public OrnamentBuilder fencegateSounds(SoundEvent open, SoundEvent close) {
        this.fencegateSounds = new SoundEvent[]{ open, close };
        return this;
    }

    /**
     * Sets the sounds for opening and closing a Door.
     * @param open The open sound.
     * @param close The close sound;
     */
    public OrnamentBuilder doorSounds(SoundEvent open, SoundEvent close) {
        this.doorSounds = new SoundEvent[]{ open, close };
        return this;
    }

    /**
     * Sets the sounds for opening and closing a Saddle Door.
     * @param open The open sound.
     * @param close The close sound;
     */
    public OrnamentBuilder saddledoorSounds(SoundEvent open, SoundEvent close) {
        this.saddledoorSounds = new SoundEvent[]{ open, close };
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
     * Sets the block to tick randomly. Used to enable {@link BlockBehaviour.Properties#randomTicks()}
     */
    public OrnamentBuilder ticks() {
        this.doesTick = true;
        return this;
    }

    /**
     * Sets the block's item to resist being destroyed in fire or lava. Used to enable {@link Item.Properties#fireResistant()}
     */
    public OrnamentBuilder isFireproof() {
        this.fireproof = true;
        return this;
    }

    /**
     * Sets the entity spawn logic on the block. Used in {@link BlockBehaviour.Properties#isValidSpawn(BlockBehaviour.StateArgumentPredicate)}
     * If left null (unused), will default to regular placement logic.
     * @param predicate The spawn predicate to test. If true, an entity can spawn on it.
     */
    public OrnamentBuilder setCanEntitySpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> predicate) {
        this.entitySpawnPredicate = predicate;
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
     * Sets if the block can melt. When this is set, the block will melt in high light levels at random by default.
     * The block may also vaporise if the dimension does so and is set to here.
     * Ideally, a Fluid should be set here. If not, will default to Water.
     * @param melt The resulting block if the block melts. Best use is a fluid.
     * @param vaporise Sets if the block may vaporise in certain dimensions. If true, will turn to Air in said dimensions.
     */
    public OrnamentBuilder canMelt(Block melt, boolean vaporise) {
        this.canMelt = true;
        this.meltResult = melt;
        if (!meltResult.defaultBlockState().getMaterial().isLiquid()) {
            OrnamentalMod.LOGGER.warn("Supplied melt result for {} was not a liquid, was {}! Defaulting to Water.", name, melt.defaultBlockState().getMaterial());
            meltResult = Blocks.WATER;
        }
        this.canVaporise = vaporise;
        return this;
    }

    /**
     * Sets if the block is not solid if the block would normally be solid. Used to enable {@link BlockBehaviour.Properties#noOcclusion()}.
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
     * Sets the sounds the ornament will make when hit by a projectile.
     * @param sounds The SoundEvents that play when the ornament is hit by a projectile.
     */
    public OrnamentBuilder projectileHitSound(List<SoundEvent> sounds) {
        this.projectileHitSounds = sounds;
        return this;
    }

    /**
     * Data generation only. This will put all ornaments of a material into an array to generate into Block Tags.
     * For ornament-based tags, this is handled via registration.
     * @param tags The list of tags provided for data generation. These will apply to all ornaments of the specified OrnamentBuilder material.
     */
    public OrnamentBuilder addBlockTags(List<List<RegistryObject<? extends Block>>> tags) {
        this.blockTags = Lists.newArrayList(tags);
        return this;
    }

    /**
     * Data generation only. This will put all ornaments of a material into an array to generate into Item Tags.
     * For ornament-based tags, this is handled via registration.
     * @param tags The list of tags provided for data generation. These will apply to all ornaments of the specified OrnamentBuilder material.
     */
    public OrnamentBuilder addItemTags(List<List<RegistryObject<? extends Block>>> tags) {
        this.itemTags = Lists.newArrayList(tags);
        return this;
    }
}
