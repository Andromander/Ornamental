package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import com.androsa.ornamental.registry.ModCreativeTabs;
import com.google.common.collect.Lists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class MasterRegistryHelper {

    protected final DeferredRegister<Block> blockRegistry;
    protected final DeferredRegister<Item> itemRegistry;

    /**
     * A helper class for registration. This covers the basics, but may also be extended for custom methods.
     * Always create your own RegistryHelper, otherwise blocks and items will get registered to the wrong mod ID.
     * @param blockreg Block DeferredRegister.
     * @param itemreg Item DeferredRegister.
     */
    public MasterRegistryHelper(DeferredRegister<Block> blockreg, DeferredRegister<Item> itemreg) {
        this.blockRegistry = blockreg;
        this.itemRegistry = itemreg;
    }

    /**
     * For creating properties for an OrnamentStair.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentStair.
     */
    protected BlockBehaviour.Properties stairProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentSlab.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentSlab.
     */
    protected BlockBehaviour.Properties slabProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentFence.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentFence.
     */
    protected BlockBehaviour.Properties fenceProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentTrapDoor.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentTrapDoor.
     */
    protected BlockBehaviour.Properties trapdoorProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder).noOcclusion().isValidSpawn((state, reader, pos, type) -> false);
    }

    /**
     * For creating properties for an OrnamentFenceGate.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentFenceGate.
     */
    protected BlockBehaviour.Properties fencegateProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentDoor.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentDoor.
     */
    protected BlockBehaviour.Properties doorProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder).noOcclusion().pushReaction(PushReaction.DESTROY);
    }

    /**
     * For creating properties for an OrnamentPole.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentPole.
     */
    protected BlockBehaviour.Properties poleProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentBeam.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the Ornamentbeam.
     */
    protected BlockBehaviour.Properties beamProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentWall.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentWall.
     */
    protected BlockBehaviour.Properties wallProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder);
    }

    /**
     * For creating properties for an OrnamentSaddleDoor.
     * @param builder OrnamentBuilder in the event of special handling, ie. a subclass of OrnamentBuilder.
     * @return Properties for the OrnamentSaddleDoor.
     */
    protected BlockBehaviour.Properties saddledoorProperties(OrnamentBuilder builder) {
        return PropertiesHelper.createProps(builder).noOcclusion();
    }

    /**
     * For creating lists of default Block and Item Tags for an OrnamentStair.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper stairTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentSlab.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper slabTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentFence.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper fenceTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentTrapDoor.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper trapdoorTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentFenceGate.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper fencegateTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentDoor.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper doorTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentPole.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper poleTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentBeam.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper beamTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentWall.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper wallTags();

    /**
     * For creating lists of default Block and Item Tags for an OrnamentSaddleDoor.
     * @return TagHelper containing Block and Item Tags.
     */
    protected abstract TagHelper saddledoorTags();

    /**
     * For registering an OrnamentStair. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentStair object.
     * @return Supplier for an OrnamentStair.
     */
    public <T extends OrnamentStair, O extends OrnamentBuilder> Supplier<T> stairs(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = stairProperties(builder);
        blocktags.addAll(stairTags().blockTags());
        itemtags.addAll(stairTags().itemTags());

        return registerBlock(builder, "_stairs", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 4, OrnamentBlockItem::new), ModCreativeTabs.STAIR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentSlab. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentSlab object.
     * @return Supplier for an OrnamentSlab.
     */
    public <T extends OrnamentSlab, O extends OrnamentBuilder> Supplier<T> slab(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = slabProperties(builder);
        blocktags.addAll(slabTags().blockTags());
        itemtags.addAll(slabTags().itemTags());

        return registerBlock(builder, "_slab", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 3, OrnamentBlockItem::new), ModCreativeTabs.SLAB_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentFence. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentFence object.
     * @return Supplier for an OrnamentFence.
     */
    public <T extends OrnamentFence, O extends OrnamentBuilder> Supplier<T> fence(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = fenceProperties(builder);
        blocktags.addAll(fenceTags().blockTags());
        itemtags.addAll(fenceTags().itemTags());

        return registerBlock(builder, "_fence", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 1, OrnamentBlockItem::new), ModCreativeTabs.FENCE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentTrapDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentTrapDoor object.
     * @return Supplier for an OrnamentTrapDoor.
     */
    public <T extends OrnamentTrapDoor, O extends OrnamentBuilder> Supplier<T> trapdoor(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = trapdoorProperties(builder);
        blocktags.addAll(trapdoorTags().blockTags());
        itemtags.addAll(trapdoorTags().itemTags());

        return registerBlock(builder, "_trapdoor", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 5, OrnamentBlockItem::new), ModCreativeTabs.TRAPDOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentFenceGate. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentFenceGate object.
     * @return Supplier for an OrnamentFenceGate.
     */
    public <T extends OrnamentFenceGate, O extends OrnamentBuilder> Supplier<T> fencegate(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = fencegateProperties(builder);
        blocktags.addAll(fencegateTags().blockTags());
        itemtags.addAll(fencegateTags().itemTags());

        return registerBlock(builder, "_fence_gate", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 2, OrnamentBlockItem::new), ModCreativeTabs.FENCE_GATE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentDoor object.
     * @return Supplier for an OrnamentDoor.
     */
    public <T extends OrnamentDoor, O extends OrnamentBuilder> Supplier<T> door(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = doorProperties(builder);
        blocktags.addAll(doorTags().blockTags());
        itemtags.addAll(doorTags().itemTags());

        return registerBlock(builder, "_door", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 0, OrnamentTallBlockItem::new), ModCreativeTabs.DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentPole. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentPole object.
     * @return Supplier for an OrnamentPole.
     */
    public <T extends OrnamentPole, O extends OrnamentBuilder> Supplier<T> pole(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = poleProperties(builder);
        blocktags.addAll(poleTags().blockTags());
        itemtags.addAll(poleTags().itemTags());

        return registerBlock(builder, "_pole", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 6, OrnamentBlockItem::new), ModCreativeTabs.POLE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentBeam. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentBeam object.
     * @return Supplier for an OrnamentBeam.
     */
    public <T extends OrnamentBeam, O extends OrnamentBuilder> Supplier<T> beam(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = beamProperties(builder);
        blocktags.addAll(beamTags().blockTags());
        itemtags.addAll(beamTags().itemTags());

        return registerBlock(builder, "_beam", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 7, OrnamentBlockItem::new), ModCreativeTabs.BEAM_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentWall. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentWall object.
     * @return Supplier for an OrnamentWall.
     */
    public <T extends OrnamentWall, O extends OrnamentBuilder> Supplier<T> wall(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = wallProperties(builder);
        blocktags.addAll(wallTags().blockTags());
        itemtags.addAll(wallTags().itemTags());

        return registerBlock(builder, "_wall", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 8, OrnamentBlockItem::new), ModCreativeTabs.WALL_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentSaddleDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentSaddleDoor object.
     * @return Supplier for an OrnamentSaddleDoor.
     */
    public <T extends OrnamentSaddleDoor, O extends OrnamentBuilder> Supplier<T> saddledoor(O builder, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags, BlockFactory<T, O> factory) {
        BlockBehaviour.Properties props = saddledoorProperties(builder);
        blocktags.addAll(saddledoorTags().blockTags());
        itemtags.addAll(saddledoorTags().itemTags());

        return registerBlock(builder, "_saddle_door", () -> factory.create(builder, props), item ->
                registerBlockItem(item, builder, 9, OrnamentBlockItem::new), ModCreativeTabs.SADDLE_DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * INTERNAL USE
     */
    private <T extends Block> Supplier<T> registerBlock(OrnamentBuilder builder, String suffix, Supplier<? extends T> block, Function<Supplier<T>, Supplier<? extends Item>> item, List<Supplier<? extends Block>> tab, ArrayList<List<Supplier<? extends Block>>> blocktags, ArrayList<List<Supplier<? extends Block>>> itemtags) {
        String name = builder.name + suffix;
        Supplier<T> reg = blockRegistry.register(name, block);
        itemRegistry.register(name, item.apply(reg));
        tab.add(reg);

        blocktags.addAll(builder.tags.blockTags());
        for (List<Supplier<? extends Block>> tag : blocktags) {
            tag.add(reg);
        }
        itemtags.addAll(builder.tags.itemTags());
        for (List<Supplier<? extends Block>> tag : itemtags) {
            tag.add(reg);
        }

        return reg;
    }

    /**
     * The BlockItem for an ornament, o long as the BlockItem follows {@link ItemFactory#create(Block, Item.Properties, OrnamentBuilder, int)}.
     * @param block The Block ornament.
     * @param builder The OrnamentBuilder.
     * @param fuelindex The fuel index for the {@link OrnamentBuilder#burnTime}. See {@link OrnamentBuilder#burnTime(int...)} for block to index.
     * @param factory The BlockItem.
     * @return The BlockItem for the ornament.
     */
    protected <T extends Block, I extends BlockItem> Supplier<BlockItem> registerBlockItem(Supplier<T> block, OrnamentBuilder builder, int fuelindex, ItemFactory<I> factory) {
        return () -> factory.create(block.get(), PropertiesHelper.createItem(builder), builder, fuelindex);
    }

    @SafeVarargs
    public final ArrayList<List<Supplier<? extends Block>>> array(List<Supplier<? extends Block>>... lists) {
        return Lists.newArrayList(lists);
    }

    @FunctionalInterface
    public interface BlockFactory<T extends OrnamentalBlock, O extends OrnamentBuilder> {
        T create(O builder, BlockBehaviour.Properties props);
    }

    @FunctionalInterface
    public interface ItemFactory<T extends BlockItem> {
        T create(Block block, Item.Properties props, OrnamentBuilder builder, int index);
    }

    /**
     * For storing all Block and Item Tags. This just keeps everything together in one tidy space.
     * @param blockTags The Block Tags to provide.
     * @param itemTags the Item Tags to provide.
     */
    public record TagHelper(ArrayList<List<Supplier<? extends Block>>> blockTags, ArrayList<List<Supplier<? extends Block>>> itemTags) { }
}
