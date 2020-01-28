package com.androsa.nifty.data.provider;

import com.androsa.nifty.NiftyMod;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public abstract class NiftyBlockStateProvider extends BlockStateProvider {

    private final NiftyBlockModelProvider blockModels;

    public NiftyBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);

        blockModels = new NiftyBlockModelProvider(generator, helper) {
            @Override
            protected void registerModels() { }

            @Override
            public String getName() {
                return NiftyBlockStateProvider.this.getName();
            }
        };
    }

    @Nonnull
    @Override
    public String getName() {
        return "Nifty Blockstates and Block Models";
    }

    public NiftyBlockModelProvider models() {
        return blockModels;
    }

    protected ResourceLocation locationVanilla(String name) {
        return new ResourceLocation("block/" + name);
    }

    protected ResourceLocation locationNifty(String name) {
        return new ResourceLocation(NiftyMod.MODID, "block/" + name);
    }

    public void stairsBasic(Supplier<? extends StairsBlock> block, String name) {
        stairsBlock(block.get(), locationVanilla(name));
    }

    public void stairsMissing(Supplier<? extends StairsBlock> block) {
        stairsBlock(block.get(), locationNifty("missingno"));
    }

    public void stairsColumn(Supplier<? extends StairsBlock> block, String side, String end) {
        stairsBlock(block.get(), locationVanilla(side), locationVanilla(end), locationVanilla(end));
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, String name) {
        slabBlock(block.get(), locationVanilla(name), locationVanilla(name));
    }

    public void slabMissing(Supplier<? extends SlabBlock> block) {
        slabBlock(block.get(), locationNifty("missingno"), locationNifty("missingno"));
    }

    public void slabModel(Supplier<? extends SlabBlock> block, String model, String name) {
        slabBlock(block.get(), locationVanilla(model), locationVanilla(name));
    }

    public void slabColumn(Supplier<? extends SlabBlock> block, String blockname, String side, String end) {
        slabBlock(block.get(), locationVanilla(blockname), locationVanilla(side), locationVanilla(end), locationVanilla(end));
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBlock(block.get(), locationVanilla(name));
    }

    public void fenceMissing(Supplier<? extends FenceBlock> block) {
        fenceBlock(block.get(), locationNifty("missingno"));
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().fencePostColumn(baseName + "_post", locationVanilla(side), locationVanilla(top)),
                models().fenceSide(baseName + "_side", locationVanilla(side)));
    }

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locationNifty(name + "_trapdoor"), true);
    }

    public void trapdoorMissing(Supplier<? extends TrapDoorBlock> block) {
        trapdoorBlock(block.get(), locationNifty("missingno"), false);
    }

    public void trapdoorVanilla(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locationVanilla(name), false);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBlock(block.get(), locationVanilla(name));
    }

    public void fenceGateMissing(Supplier<? extends FenceGateBlock> block) {
        fenceGateBlock(block.get(), locationNifty("missingno"));
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top) {
        ModelFile gate =         models().fenceGateColumn(block.get().getRegistryName().toString(), locationVanilla(side), locationVanilla(top));
        ModelFile gateOpen =     models().fenceGateOpenColumn(block.get().getRegistryName().toString() + "_open", locationVanilla(side), locationVanilla(top));
        ModelFile gateWall =     models().fenceGateWallColumn(block.get().getRegistryName().toString() + "_wall", locationVanilla(side), locationVanilla(top));
        ModelFile gateWallOpen = models().fenceGateWallOpenColumn(block.get().getRegistryName().toString() + "_wall_open", locationVanilla(side), locationVanilla(top));

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locationNifty(name + "_door_bottom"), locationNifty(name + "_door_top"));
    }

    public void doorMissing(Supplier<? extends DoorBlock> block) {
        doorBlock(block.get(), locationNifty("missingno"), locationNifty("missingno"));
    }

    public void doorBasicVanilla(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locationVanilla(name), locationVanilla(name));
    }
}
