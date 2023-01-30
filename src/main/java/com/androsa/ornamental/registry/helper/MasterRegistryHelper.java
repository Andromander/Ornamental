package com.androsa.ornamental.registry.helper;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.items.OrnamentBlockItem;
import com.androsa.ornamental.items.OrnamentTallBlockItem;
import com.androsa.ornamental.registry.handler.CreativeTabHandler;
import com.google.common.collect.Lists;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

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
        return PropertiesHelper.createProps(builder).noOcclusion();
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
     * For creating a list of default Block Tags for an OrnamentStair.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> stairBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentStair.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> stairItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentSlab.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> slabBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentSlab.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> slabItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentFence.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> fenceBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentFence.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> fenceItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentTrapDoor.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> trapdoorBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentTrapDoor.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> trapdoorItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentFenceGate.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> fencegateBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentFenceGate.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> fencegateItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentDoor.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> doorBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentDoor.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> doorItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentPole.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> poleBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentPole.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> poleItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentBeam.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> beamBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentBeam.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> beamItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentWall.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> wallBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentWall.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> wallItemTags();

    /**
     * For creating a list of default Block Tags for an OrnamentSaddleDoor.
     * @return ArrayList of Block Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> saddledoorBlockTags();

    /**
     * For creating a list of default Item Tags for an OrnamentSaddleDoor.
     * @return ArrayList of Item Tags.
     */
    protected abstract ArrayList<List<RegistryObject<? extends Block>>> saddledoorItemTags();

    /**
     * For registering an OrnamentStair. This method contains everything required for registration.
     * @param base The base block. All Stair blocks require this.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentStair object.
     * @return RegistryObject for an OrnamentStair.
     */
    public <T extends OrnamentStair> RegistryObject<T> stairs(Block base, OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, StairFactory<T> factory) {
        BlockBehaviour.Properties props = stairProperties(builder);
        blocktags.addAll(stairBlockTags());
        itemtags.addAll(stairItemTags());

        return registerBlock(builder, "_stairs", () -> factory.create(base::defaultBlockState, props, builder), item ->
                registerBlockItem(item, builder, 4), CreativeTabHandler.STAIR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentSlab. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentSlab object.
     * @return RegistryObject for an OrnamentSlab.
     */
    public <T extends OrnamentSlab> RegistryObject<T> slab(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = slabProperties(builder);
        blocktags.addAll(slabBlockTags());
        itemtags.addAll(slabItemTags());

        return registerBlock(builder, "_slab", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 3), CreativeTabHandler.SLAB_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentFence. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentFence object.
     * @return RegistryObject for an OrnamentFence.
     */
    public <T extends OrnamentFence> RegistryObject<T> fence(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = fenceProperties(builder);
        blocktags.addAll(fenceBlockTags());
        itemtags.addAll(fenceItemTags());

        return registerBlock(builder, "_fence", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 1), CreativeTabHandler.FENCE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentTrapDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentTrapDoor object.
     * @return RegistryObject for an OrnamentTrapDoor.
     */
    public <T extends OrnamentTrapDoor> RegistryObject<T> trapdoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = trapdoorProperties(builder);
        blocktags.addAll(trapdoorBlockTags());
        itemtags.addAll(trapdoorItemTags());

        return registerBlock(builder, "_trapdoor", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 5), CreativeTabHandler.TRAPDOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentFenceGate. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentFenceGate object.
     * @return RegistryObject for an OrnamentFenceGate.
     */
    public <T extends OrnamentFenceGate> RegistryObject<T> fencegate(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = fencegateProperties(builder);
        blocktags.addAll(fencegateBlockTags());
        itemtags.addAll(fencegateItemTags());

        return registerBlock(builder, "_fence_gate", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 2), CreativeTabHandler.FENCE_GATE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentDoor object.
     * @return RegistryObject for an OrnamentDoor.
     */
    public <T extends OrnamentDoor> RegistryObject<T> door(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = doorProperties(builder);
        blocktags.addAll(doorBlockTags());
        itemtags.addAll(doorItemTags());

        return registerBlock(builder, "_door", () -> factory.create(props, builder), item ->
                registerBlockItemDoor(item, builder, 0), CreativeTabHandler.DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentPole. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentPole object.
     * @return RegistryObject for an OrnamentPole.
     */
    public <T extends OrnamentPole> RegistryObject<T> pole(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = poleProperties(builder);
        blocktags.addAll(poleBlockTags());
        itemtags.addAll(poleItemTags());

        return registerBlock(builder, "_pole", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 6), CreativeTabHandler.POLE_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentBeam. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentBeam object.
     * @return RegistryObject for an OrnamentBeam.
     */
    public <T extends OrnamentBeam> RegistryObject<T> beam(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = beamProperties(builder);
        blocktags.addAll(beamBlockTags());
        itemtags.addAll(beamItemTags());

        return registerBlock(builder, "_beam", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 7), CreativeTabHandler.BEAM_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentWall. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentWall object.
     * @return RegistryObject for an OrnamentWall.
     */
    public <T extends OrnamentWall> RegistryObject<T> wall(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = wallProperties(builder);
        blocktags.addAll(wallBlockTags());
        itemtags.addAll(wallItemTags());

        return registerBlock(builder, "_wall", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 8), CreativeTabHandler.WALL_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * For registering an OrnamentSaddleDoor. This method contains everything required for registration.
     * @param builder OrnamentBuilder.
     * @param blocktags A list of tags for the individual block.
     * @param itemtags A list of tags for the individual item.
     * @param factory The block class required to create an OrnamentSaddleDoor object.
     * @return RegistryObject for an OrnamentSaddleDoor.
     */
    public <T extends OrnamentSaddleDoor> RegistryObject<T> saddledoor(OrnamentBuilder builder, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags, BlockFactory<T> factory) {
        BlockBehaviour.Properties props = saddledoorProperties(builder);
        blocktags.addAll(saddledoorBlockTags());
        itemtags.addAll(saddledoorItemTags());

        return registerBlock(builder, "_saddle_door", () -> factory.create(props, builder), item ->
                registerBlockItem(item, builder, 9), CreativeTabHandler.SADDLE_DOOR_ORNAMENTS, blocktags, itemtags);
    }

    /**
     * INTERNAL USE
     */
    private <T extends Block> RegistryObject<T> registerBlock(OrnamentBuilder builder, String suffix, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item, List<RegistryObject<? extends Block>> tab, ArrayList<List<RegistryObject<? extends Block>>> blocktags, ArrayList<List<RegistryObject<? extends Block>>> itemtags) {
        String name = builder.name + suffix;
        RegistryObject<T> reg = blockRegistry.register(name, block);
        itemRegistry.register(name, item.apply(reg));
        tab.add(reg);

        blocktags.addAll(builder.blockTags);
        for (List<RegistryObject<? extends Block>> tag : blocktags) {
            tag.add(reg);
        }
        itemtags.addAll(builder.itemTags);
        for (List<RegistryObject<? extends Block>> tag : itemtags) {
            tag.add(reg);
        }

        return reg;
    }

    /**
     * The BlockItem for an ornament. This handles all single-block ornaments, such as Stair or Slab.
     * @param block The Block ornament.
     * @param ornament The OrnamentBuilder.
     * @param fuelindex The fuel index for the {@link OrnamentBuilder#burnTime}. See {@link OrnamentBuilder#burnTime(int...)} for block to index.
     * @return The BlockItem for the ornament.
     */
    protected <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }

    /**
     * The TallBlockItem for an ornament. This handles all ornaments that are 2 blocks tall, such as Door.
     * @param block The Block ornament.
     * @param ornament The OrnamentBuilder.
     * @param fuelindex The fuel index for the {@link OrnamentBuilder#burnTime}. See {@link OrnamentBuilder#burnTime(int...)} for block to index.
     * @return  The BlockItem for the ornament. Note that while the return value is any BlockItem, it is recommended for only handling blocks intended to be 2 blocks tall.
     */
    protected <T extends Block> Supplier<BlockItem> registerBlockItemDoor(final RegistryObject<T> block, OrnamentBuilder ornament, int fuelindex) {
        return () -> new OrnamentTallBlockItem(block.get(), PropertiesHelper.createItem(ornament), ornament, fuelindex);
    }

    @SafeVarargs
    public final ArrayList<List<RegistryObject<? extends Block>>> array(List<RegistryObject<? extends Block>>... lists) {
        return Lists.newArrayList(lists);
    }

    @FunctionalInterface
    public interface BlockFactory<T extends OrnamentalBlock> {
        T create(BlockBehaviour.Properties props, OrnamentBuilder builder);
    }

    @FunctionalInterface
    public interface StairFactory<T extends OrnamentStair> extends BlockFactory<T> {
        @Override
        default T create(BlockBehaviour.Properties props, OrnamentBuilder builder) {
            return create(Blocks.AIR::defaultBlockState, props, builder);
        }

        T create(Supplier<BlockState> base, BlockBehaviour.Properties props, OrnamentBuilder builder);
    }
}