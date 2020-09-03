package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.OrnamentalMod;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public abstract class OrnamentalBlockStateProvider extends BlockStateProvider {

    private final OrnamentalBlockModelProvider blockModels;

    public OrnamentalBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);

        blockModels = new OrnamentalBlockModelProvider(generator, helper) {
            @Override
            protected void registerModels() { }

            @Override
            public String getName() {
                return OrnamentalBlockStateProvider.this.getName();
            }
        };
    }

    @Nonnull
    @Override
    public String getName() {
        return "Ornamental Blockstates and Block Models";
    }

    public OrnamentalBlockModelProvider models() {
        return blockModels;
    }

    protected ResourceLocation locVanilla(String name) {
        return new ResourceLocation("block/" + name);
    }

    protected ResourceLocation locOrnament(String name) {
        return new ResourceLocation(OrnamentalMod.MODID, "block/" + name);
    }

    public void stairsBasic(Supplier<? extends StairsBlock> block, String name) {
        stairsBlock(block.get(), locVanilla(name));
    }

    public void stairsMissing(Supplier<? extends StairsBlock> block) {
        stairsBlock(block.get(), locOrnament("missingno"));
    }

    public void stairsColumn(Supplier<? extends StairsBlock> block, String side, String end) {
        stairsBlock(block.get(), locVanilla(side), locVanilla(end), locVanilla(end));
    }

    public void slabBasic(Supplier<? extends SlabBlock> block, String name) {
        slabBlock(block.get(), locVanilla(name), locVanilla(name));
    }

    public void slabMissing(Supplier<? extends SlabBlock> block) {
        slabBlock(block.get(), locOrnament("missingno"), locOrnament("missingno"));
    }

    public void slabModel(Supplier<? extends SlabBlock> block, String model, String name) {
        slabBlock(block.get(), locVanilla(model), locVanilla(name));
    }

    public void slabColumn(Supplier<? extends SlabBlock> block, String blockname, String side, String end) {
        slabBlock(block.get(), locVanilla(blockname), locVanilla(side), locVanilla(end), locVanilla(end));
    }

    public void fenceBasic(Supplier<? extends FenceBlock> block, String name) {
        fenceBlock(block.get(), locVanilla(name));
    }

    public void fenceMissing(Supplier<? extends FenceBlock> block) {
        fenceBlock(block.get(), locOrnament("missingno"));
    }

    public void fenceColumn(Supplier<? extends FenceBlock> block, String side, String top) {
        String baseName = block.get().getRegistryName().toString();
        fourWayBlock(block.get(),
                models().fencePostColumn(baseName + "_post", locVanilla(side), locVanilla(top)),
                models().fenceSide(baseName + "_side", locVanilla(side)));
    }

    public void trapdoorBasic(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locOrnament(name + "_trapdoor"), true);
    }

    public void trapdoorMissing(Supplier<? extends TrapDoorBlock> block) {
        trapdoorBlock(block.get(), locOrnament("missingno"), false);
    }

    public void trapdoorVanilla(Supplier<? extends TrapDoorBlock> block, String name) {
        trapdoorBlock(block.get(), locVanilla(name), false);
    }

    public void fenceGateBasic(Supplier<? extends FenceGateBlock> block, String name) {
        fenceGateBlock(block.get(), locVanilla(name));
    }

    public void fenceGateMissing(Supplier<? extends FenceGateBlock> block) {
        fenceGateBlock(block.get(), locOrnament("missingno"));
    }

    public void fenceGateColumn(Supplier<? extends FenceGateBlock> block, String side, String top) {
        ModelFile gate =         models().fenceGateColumn(block.get().getRegistryName().toString(), locVanilla(side), locVanilla(top));
        ModelFile gateOpen =     models().fenceGateOpenColumn(block.get().getRegistryName().toString() + "_open", locVanilla(side), locVanilla(top));
        ModelFile gateWall =     models().fenceGateWallColumn(block.get().getRegistryName().toString() + "_wall", locVanilla(side), locVanilla(top));
        ModelFile gateWallOpen = models().fenceGateWallOpenColumn(block.get().getRegistryName().toString() + "_wall_open", locVanilla(side), locVanilla(top));

        fenceGateBlock(block.get(), gate, gateOpen, gateWall, gateWallOpen);
    }

    public void doorBasic(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locOrnament(name + "_door_bottom"), locOrnament(name + "_door_top"));
    }

    public void doorMissing(Supplier<? extends DoorBlock> block) {
        doorBlock(block.get(), locOrnament("missingno"), locOrnament("missingno"));
    }

    public void doorBasicVanilla(Supplier<? extends DoorBlock> block, String name) {
        doorBlock(block.get(), locVanilla(name), locVanilla(name));
    }
}
