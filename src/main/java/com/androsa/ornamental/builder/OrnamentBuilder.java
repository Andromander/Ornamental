package com.androsa.ornamental.builder;

import com.androsa.ornamental.OrnamentalMod;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

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
    public MapColor color = MapColor.NONE;
    public float hardness = 0.0F;
    public float resistance = 0.0F;
    public int light = 0;
    public float fallMultiplier = 1.0F;
    public float slipperiness = 0.6F;
    public int[] burnTime = new int[]{0,0,0,0,0,0,0,0,0};
    public Supplier<? extends Block> baseBlock = () -> Blocks.AIR;
    public SoundEvent[] fencegateSounds = new SoundEvent[]{SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE};
    public SoundEvent[] saddledoorSounds = new SoundEvent[]{SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE};
    public BlockSetType blockSetType = BlockSetType.OAK;
    public boolean canOpen = blockSetType.canOpenByHand();
    public boolean fallThrough = false;
    public boolean hasPower = false;
    public boolean doesTick = false;
    public boolean requiresTool = false;
    public boolean fireproof = false;
    public BlockBehaviour.StatePredicate conductsRedstone = null;
    public BlockBehaviour.StateArgumentPredicate<EntityType<?>> entitySpawnPredicate = null;
    public boolean pathShape = false;
    public boolean canMelt = false;
    public Block meltResult = Blocks.WATER;
    public boolean canVaporise = false;
    public boolean isSolid = true;
    public boolean breakableCull = false;
    public boolean postProcess = false;
    public boolean emissiveRender = false;
    public NoteBlockInstrument instrument = NoteBlockInstrument.HARP;
    public PushReaction pushReaction = PushReaction.NORMAL;
    public List<SoundEvent> projectileHitSounds = new ArrayList<>();
    public List<BlockConverter> convertPredicates = null;
    public FloorHazard floorHazard = null;
    public boolean createBubbles = false;
    public boolean extinguishes = false;
    public boolean bubbleDragDown = false;
    public int tickSchedule = 0;
    public List<List<RegistryObject<? extends Block>>> blockTags = new ArrayList<>();
    public List<List<RegistryObject<? extends Block>>> itemTags = new ArrayList<>();

    /**
     * Create a template material for an Ornament block.
     * @param name The name to supply through registration. Used for ease of naming.
     */
    public OrnamentBuilder(String name) {
        this.name = name;
    }

    /**
     * A Copy Constructor for OrnamentBuilder. This calls {@link OrnamentBuilder#OrnamentBuilder(String)}, but will also copy all field values over.
     * In the event of extending OrnamentBuilder, use this to create properties for OrnamentBuilder.
     * @param builder The pre-defined OrnamentBuilder to use.
     */
    public OrnamentBuilder(OrnamentBuilder builder) {
        this(builder.name);
        this.color = builder.color;
        this.hardness = builder.hardness;
        this.resistance = builder.resistance;
        this.light = builder.light;
        this.fallMultiplier = builder.fallMultiplier;
        this.slipperiness = builder.slipperiness;
        this.burnTime = builder.burnTime;
        this.baseBlock = builder.baseBlock;
        this.fencegateSounds = builder.fencegateSounds;
        this.saddledoorSounds = builder.saddledoorSounds;
        this.blockSetType = builder.blockSetType;
        this.canOpen = builder.canOpen;
        this.fallThrough = builder.fallThrough;
        this.hasPower = builder.hasPower;
        this.doesTick = builder.doesTick;
        this.requiresTool = builder.requiresTool;
        this.fireproof = builder.fireproof;
        this.conductsRedstone = builder.conductsRedstone;
        this.entitySpawnPredicate = builder.entitySpawnPredicate;
        this.pathShape = builder.pathShape;
        this.canMelt = builder.canMelt;
        this.meltResult = builder.meltResult;
        this.canVaporise = builder.canVaporise;
        this.isSolid = builder.isSolid;
        this.breakableCull = builder.breakableCull;
        this.postProcess = builder.postProcess;
        this.emissiveRender = builder.emissiveRender;
        this.instrument = builder.instrument;
        this.pushReaction = builder.pushReaction;
        this.projectileHitSounds = builder.projectileHitSounds;
        this.convertPredicates = builder.convertPredicates;
        this.floorHazard = builder.floorHazard;
        this.createBubbles = builder.createBubbles;
        this.extinguishes = builder.extinguishes;
        this.bubbleDragDown = builder.bubbleDragDown;
        this.tickSchedule = builder.tickSchedule;
        this.blockTags = builder.blockTags;
        this.itemTags = builder.itemTags;
    }

    /**
     * Setter for the block's MapColor. Used to set {@link BlockBehaviour.Properties#mapColor(MapColor)}.
     * @param color The MapColor of the block.
     */
    public OrnamentBuilder mapColor(MapColor color) {
        this.color = color;
        return this;
    }

    /**
     * Setter for the block's hardness. Will leave the block's resistance to 0.0F. Used to set {@link BlockBehaviour.Properties#strength(float, float)}.
     * For setting hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float)} or {@link OrnamentBuilder#hardnessAndResistance(float, float)}.
     * @param amount The block's hardness.
     */
    public OrnamentBuilder hardness(float amount) {
        this.hardness = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance. Both values will match. Used to set {@link BlockBehaviour.Properties#strength(float, float)}.
     * For setting separate hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float, float)}.
     * @param amount The block's hardness and resistance
     */
    public OrnamentBuilder hardnessAndResistance(float amount) {
        this.hardness = amount;
        this.resistance = amount;
        return this;
    }

    /**
     * Setter for the block's hardness and resistance. Used to set {@link BlockBehaviour.Properties#strength(float, float)}.
     * For ease of use, to set matching hardness and resistance, use {@link OrnamentBuilder#hardnessAndResistance(float)}.
     * @param hard The block's hardness.
     * @param resist The block's resistance.
     */
    public OrnamentBuilder hardnessAndResistance(float hard, float resist) {
        this.hardness = hard;
        this.resistance = resist;
        return this;
    }

    /**
     * Setter for the block's light level. Used to set {@link BlockBehaviour.Properties#lightLevel(ToIntFunction)}.
     * @param level the light level to emit.
     */
    public OrnamentBuilder lightLevel(int level) {
        this.light = level;
        return this;
    }

    /**
     * Setter for whether a block needs the correct tool to be harvested. Used to enable {@link BlockBehaviour.Properties#requiresCorrectToolForDrops()}.
     */
    public OrnamentBuilder requiresTool() {
        this.requiresTool = true;
        return this;
    }

    /**
     * Setter for the damage multiplier when taking fall damage on the block. Defaults to 1.0F (standard handling).
     * @param amount The damage multiplier.
     */
    public OrnamentBuilder fall(float amount) {
        this.fallMultiplier = amount;
        return this;
    }

    /**
     * Setter for the slipperiness of a block when moving on top of it. Defaults to 0.6F (average movement). Used to set {@link BlockBehaviour.Properties#friction(float)}.
     * NOTE: On blocks like lower half Slabs, slipperiness is undetected. This is how vanilla behaves.
     * @param amount The amount of slip.
     */
    public OrnamentBuilder slip(float amount) {
        this.slipperiness = amount;
        return this;
    }

    /**
     * Setters for a block's burn time, measured in ticks. This is applied to the block's item. Defaults to all 0 (does not burn).
     * For default behaviour, set value to -1. Any other value above 0, 20 ticks = 1 second.
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
     * Note: BlockSetType sets this value, but in the event the desired behaviour is incorrect, this may be used.
     * Author's Note: This is actually because Mojang has set Stone and Polished Blackstone's boolean value to false, which contradicts what Ornamental has set, as well as {@link net.minecraft.world.level.block.DoorBlock#isWoodenDoor(Level, BlockPos)}.
     * It is very likely if a Stone door is added, this method will be removed.
     */
    public OrnamentBuilder canOpen(boolean flag) {
        this.canOpen = flag;
        return this;
    }

    /**
     * Sets the block to open if walked on. Used in blocks with an open/close state and can open underneath players, such as Trapdoors.
     */
    public OrnamentBuilder canFallThrough() {
        this.fallThrough = true;
        return this;
    }

    /**
     * Sets a base block for an OrnamentStair. Not necessary to set if no OrnamentStair is getting registered, but is highly advised.
     * @param block The Supplier of a Block to provide.
     */
    public OrnamentBuilder stairBaseBlock(Supplier<Block> block) {
        this.baseBlock = block;
        return this;
    }

    /**
     * Sets the sounds for opening and closing a Fence Gate.
     * @param open The open sound.
     * @param close The close sound.
     */
    public OrnamentBuilder fencegateSounds(SoundEvent open, SoundEvent close) {
        this.fencegateSounds = new SoundEvent[]{ open, close };
        return this;
    }

    /**
     * Sets the sounds for opening and closing a Saddle Door.
     * @param open The open sound.
     * @param close The close sound.
     */
    public OrnamentBuilder saddledoorSounds(SoundEvent open, SoundEvent close) {
        this.saddledoorSounds = new SoundEvent[]{ open, close };
        return this;
    }

    /**
     * Sets a BlockSetType with the OrnamentBuilder name. This is used to handle SoundType, Door opening and closing, and Trapdoor Opening and closing.
     * Pressure Plates and Buttons are currently not handled, but values should be provided otherwise.
     * For Fence Gate sounds, use {@link OrnamentBuilder#fencegateSounds(SoundEvent, SoundEvent)}.
     * For Saddle Door sounds, use {@link OrnamentBuilder#saddledoorSounds(SoundEvent, SoundEvent)}
     * See {@link OrnamentBuilder#blockSetType(String, boolean, SoundType, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent)} for full parameter breakdown.
     */
    public OrnamentBuilder blockSetType(SoundType sound, SoundEvent closedoor, SoundEvent opendoor, SoundEvent closetrap, SoundEvent opentrap, SoundEvent plateoff, SoundEvent plateon, SoundEvent buttonoff, SoundEvent buttonon) {
        return this.blockSetType(name, false, sound, closedoor, opendoor, closetrap, opentrap, plateoff, plateon, buttonoff, buttonon);
    }

    /**
     * Sets a BlockSetType with the OrnamentBuilder name and allows opening by hand. This is used to handle SoundType, Door opening and closing, and Trapdoor Opening and closing.
     * Pressure Plates and Buttons are currently not handled, but values should be provided otherwise.
     * For Fence Gate sounds, use {@link OrnamentBuilder#fencegateSounds(SoundEvent, SoundEvent)}.
     * For Saddle Door sounds, use {@link OrnamentBuilder#saddledoorSounds(SoundEvent, SoundEvent)}
     * See {@link OrnamentBuilder#blockSetType(String, boolean, SoundType, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent, SoundEvent)} for full parameter breakdown.
     */
    public OrnamentBuilder blockSetTypeByHand(SoundType sound, SoundEvent closedoor, SoundEvent opendoor, SoundEvent closetrap, SoundEvent opentrap, SoundEvent plateoff, SoundEvent plateon, SoundEvent buttonoff, SoundEvent buttonon) {
        return this.blockSetType(name, true, sound, closedoor, opendoor, closetrap, opentrap, plateoff, plateon, buttonoff, buttonon);
    }

    /**
     * Sets a BlockSetType from scratch. This is used to handle SoundType, opening by hand, Door opening and closing, and Trapdoor Opening and closing.
     * Pressure Plates and Buttons are currently not handled, but values should be provided otherwise.
     * Opening the door by hand may be overridden with {@link OrnamentBuilder#canOpen(boolean)} should behaviour contradict logic.
     * For Fence Gate sounds, use {@link OrnamentBuilder#fencegateSounds(SoundEvent, SoundEvent)}.
     * For Saddle Door sounds, use {@link OrnamentBuilder#saddledoorSounds(SoundEvent, SoundEvent)}
     * @param name The name for the BlockSetType.
     * @param hand If the block can be opened by hand.
     * @param sound The sound the block makes.
     * @param closedoor The close sound of a Door.
     * @param opendoor The open sound of a Door.
     * @param closetrap The close sound of a Trapdoor.
     * @param opentrap The open sound of a Trapdoor.
     * @param plateoff The off sound of a Pressure Plate.
     * @param plateon The on sound of a Pressure Plate.
     * @param buttonoff The off sound of a Button.
     * @param buttonon The on sound of a Button.
     */
    public OrnamentBuilder blockSetType(String name, boolean hand, SoundType sound, SoundEvent closedoor, SoundEvent opendoor, SoundEvent closetrap, SoundEvent opentrap, SoundEvent plateoff, SoundEvent plateon, SoundEvent buttonoff, SoundEvent buttonon) {
        return this.blockSetType(new BlockSetType(name, hand, sound, closedoor, opendoor, closetrap, opentrap, plateoff, plateon, buttonoff, buttonon));
    }

    /**
     * Sets a BlockSetType with a preset one.
     * @param type the BlockSetType.
     */
    public OrnamentBuilder blockSetType(BlockSetType type) {
        this.blockSetType = BlockSetType.register(type);
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
     * Sets the block to tick randomly. Used to enable {@link BlockBehaviour.Properties#randomTicks()}.
     */
    public OrnamentBuilder ticks() {
        this.doesTick = true;
        return this;
    }

    /**
     * Sets the block's item to resist being destroyed in fire or lava. Used to enable {@link Item.Properties#fireResistant()}.
     */
    public OrnamentBuilder isFireproof() {
        this.fireproof = true;
        return this;
    }

    /**
     * Sets a block's ability to convert into other blocks. Multiple predicates are allowed for different conversions.
     * For more information, see {@link BlockConverter#BlockConverter(BlockConverter.BlockConvertPredicate, Supplier, SoundEvent)}.
     * @param predicates The predicates the block can check by.
     */
    public OrnamentBuilder setConversionPredicates(List<BlockConverter> predicates) {
        this.convertPredicates = predicates;
        return this;
    }

    /**
     * Sets if a block can conduct Redstone. Used in {@link BlockBehaviour.Properties#isRedstoneConductor(BlockBehaviour.StatePredicate)}.
     * If left null, default behaviour is assigned. Note that default behaviour checks for whether a block has a full collision shape.
     * Most blocks in Ornamental make use of non-full shapes, however do note states that allow this such as Double Slab, Full Poles, and Full Beams.
     * @param predicate The state predicate to test. If true, the block can conduct redstone.
     */
    public OrnamentBuilder setRedstoneConduction(BlockBehaviour.StatePredicate predicate) {
        this.conductsRedstone = predicate;
        return this;
    }

    /**
     * Sets the entity spawn logic on the block. Used in {@link BlockBehaviour.Properties#isValidSpawn(BlockBehaviour.StateArgumentPredicate)}.
     * If left null (unused), will default to regular placement logic.
     * @param predicate The spawn predicate to test. If true, an entity can spawn on it.
     */
    public OrnamentBuilder setCanEntitySpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> predicate) {
        this.entitySpawnPredicate = predicate;
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
     * Sets if the block can melt. When this is set, the block will melt in high light levels at random by default.
     * The block may also vaporise if the dimension does so and is set to here.
     * Ideally, a Fluid should be set here. If not, will default to Water.
     * @param melt The resulting block if the block melts. Best use is a fluid.
     * @param vaporise Sets if the block may vaporise in certain dimensions. If true, will turn to Air in said dimensions.
     */
    public OrnamentBuilder canMelt(Block melt, boolean vaporise) {
        this.canMelt = true;
        this.meltResult = melt;
        if (!meltResult.defaultBlockState().liquid()) {
            OrnamentalMod.LOGGER.warn("Supplied melt result for {} was not a liquid, was {}! Defaulting to Water.", name, melt.defaultBlockState().liquid());
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
     * Sets if the block should be marked for post-processing. Used in {@link BlockBehaviour.Properties#hasPostProcess(BlockBehaviour.StatePredicate)}.
     * Note that this property only takes effect for world generation, however should be considered anyway.
     */
    public OrnamentBuilder doPostProcessing() {
        this.postProcess = true;
        return this;
    }

    /**
     * Sets if the block has emissive rendering. Used to enable {@link BlockBehaviour.Properties#emissiveRendering(BlockBehaviour.StatePredicate)}.
     */
    public OrnamentBuilder doEmissiveRendering() {
        this.emissiveRender = true;
        return this;
    }

    /**
     * Sets a NoteBlockInstrument for a block. Used to set {@link BlockBehaviour.Properties#instrument(NoteBlockInstrument)}.
     * @param instrument The sound the block will make when a NoteBlock is struck.
     */
    public OrnamentBuilder instrument(NoteBlockInstrument instrument) {
        this.instrument = instrument;
        return this;
    }

    /**
     * Sets a PushReaction for a block. Used to set {@link BlockBehaviour.Properties#pushReaction(PushReaction)}.
     * Some blocks may not follow this, ie. Doors always have PushReaction.DESTROY.
     * @param reaction The push reaction this material should use.
     */
    public OrnamentBuilder pushReact(PushReaction reaction) {
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
     * Sets a floor hazard for the ornaments when an entity steps on them.
     * For more information, see {@link FloorHazard#FloorHazard(FloorHazard.FloorHazardPredicate, FloorHazard.HazardDamagePredicate, float)}.
     * @param hazard the FloorHazard to check and apply against.
     */
    public OrnamentBuilder floorHazard(FloorHazard hazard) {
        this.floorHazard = hazard;
        return this;
    }

    /**
     * Sets the block to emit bubble columns when underwater. Optionally, emit smoke and emit extinguish noises.
     * Note that the bubble column created is not a BubbleColumnBlock but a fake copy of one. Don't blame me, the logic is hard-coded.
     * @param tick the amount of ticks to schedule an update.
     * @param extinguish if the block emits smoke and extinguish noises. If true, the OrnamentBuilder must enable {@link OrnamentBuilder#ticks()}.
     * @param dragdown if the bubble column pulls down entities.
     */
    public OrnamentBuilder bubbleUnderwater(int tick, boolean extinguish, boolean dragdown) {
        this.createBubbles = true;
        this.extinguishes = extinguish;
        this.bubbleDragDown = dragdown;
        this.tickSchedule = tick;
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
