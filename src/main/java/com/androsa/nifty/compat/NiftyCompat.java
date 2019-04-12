package com.androsa.nifty.compat;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.androsa.nifty.NiftyBlock.*;

@ParametersAreNonnullByDefault
public enum NiftyCompat {
    TWILIGHT_FOREST("Twilight Forest") {
        @Override
        protected void registerBlocks(ModBlocks.BlockRegistryHelper blocks) {
            Block ironwood = new Block(Material.WOOD, MapColor.WOOD);
            Block fiery = new Block(Material.IRON, MapColor.IRON);
            Block steeleaf = new Block(Material.LEAVES, MapColor.FOLIAGE);
            Block arctic = new Block(Material.CLOTH, MapColor.CLOTH);
            Block carminite = new Block(Material.CLAY, MapColor.CLAY);

            blocks.registerBlock("ironwood_stairs",   new NiftyTFStairs(ironwood.getDefaultState(), IRONWOOD));
            blocks.registerBlock("fiery_stairs",      new NiftyTFFieryStairs(fiery.getDefaultState()));
            blocks.registerBlock("steeleaf_stairs",   new NiftyTFStairs(steeleaf.getDefaultState(), STEELEAF));
            blocks.registerBlock("arctic_fur_stairs", new NiftyTFStairs(arctic.getDefaultState(), ARCTIC));
            blocks.registerBlock("carminite_stairs",  new NiftyTFStairs(carminite.getDefaultState(), CARMINITE));
            blocks.registerBlock("ironwood_slab",   new NiftyTFSlab(false, IRONWOOD));
            blocks.registerBlock("fiery_slab",      new NiftyTFFierySlab(false));
            blocks.registerBlock("steeleaf_slab",   new NiftyTFSlab(false, STEELEAF));
            blocks.registerBlock("arctic_fur_slab", new NiftyTFSlab(false, ARCTIC));
            blocks.registerBlock("carminite_slab",  new NiftyTFSlab(false, CARMINITE));
            blocks.registerBlock("double_ironwood_slab",   new NiftyTFSlab(true, IRONWOOD));
            blocks.registerBlock("double_fiery_slab",      new NiftyTFFierySlab(true));
            blocks.registerBlock("double_steeleaf_slab",   new NiftyTFSlab(true, STEELEAF));
            blocks.registerBlock("double_arctic_fur_slab", new NiftyTFSlab(true, ARCTIC));
            blocks.registerBlock("double_carminite_slab",  new NiftyTFSlab(true, CARMINITE));
            blocks.registerBlock("ironwood_fence",   new NiftyTFFence(IRONWOOD));
            blocks.registerBlock("fiery_fence",      new NiftyTFFieryFence());
            blocks.registerBlock("steeleaf_fence",   new NiftyTFFence(STEELEAF));
            blocks.registerBlock("arctic_fur_fence", new NiftyTFFence(ARCTIC));
            blocks.registerBlock("carminite_fence",  new NiftyTFFence(CARMINITE));
            blocks.registerBlock("ironwood_trapdoor",   new NiftyTFTrapDoor(IRONWOOD));
            blocks.registerBlock("fiery_trapdoor",      new NiftyTFFieryTrapDoor());
            blocks.registerBlock("steeleaf_trapdoor",   new NiftyTFTrapDoor(STEELEAF));
            blocks.registerBlock("arctic_fur_trapdoor", new NiftyTFTrapDoor(ARCTIC));
            blocks.registerBlock("carminite_trapdoor",  new NiftyTFTrapDoor(CARMINITE));
            blocks.registerBlock("ironwood_fence_gate",   new NiftyTFFenceGate(ironwood::getDefaultState, IRONWOOD));
            blocks.registerBlock("fiery_fence_gate",      new NiftyTFFieryFenceGate(fiery::getDefaultState));
            blocks.registerBlock("steeleaf_fence_gate",   new NiftyTFFenceGate(steeleaf::getDefaultState, STEELEAF));
            blocks.registerBlock("arctic_fur_fence_gate", new NiftyTFFenceGate(arctic::getDefaultState, ARCTIC));
            blocks.registerBlock("carminite_fence_gate",  new NiftyTFFenceGate(carminite::getDefaultState, CARMINITE));
        }

        @Override
        protected void registerItems(ModBlocks.ItemRegistryHelper items) {
            items.register(new ItemBlock(ModBlocks.ironwood_stairs) {
                @Override
                public int getItemBurnTime(ItemStack stack) {
                    return 0;
                }
            });
            items.registerBlock(ModBlocks.fiery_stairs);
            items.registerBlock(ModBlocks.steeleaf_stairs);
            items.registerBlock(ModBlocks.arctic_fur_stairs);
            items.registerBlock(ModBlocks.carminite_stairs);
            items.register(new ItemSlab(ModBlocks.ironwood_slab, ModBlocks.ironwood_slab, ModBlocks.double_ironwood_slab) {
                @Override
                public int getItemBurnTime(ItemStack stack) {
                    return 0;
                }
            });
            items.register(new ItemSlab(ModBlocks.fiery_slab, ModBlocks.fiery_slab, ModBlocks.double_fiery_slab));
            items.register(new ItemSlab(ModBlocks.steeleaf_slab, ModBlocks.steeleaf_slab, ModBlocks.double_steeleaf_slab));
            items.register(new ItemSlab(ModBlocks.arctic_fur_slab, ModBlocks.arctic_fur_slab, ModBlocks.double_arctic_fur_slab));
            items.register(new ItemSlab(ModBlocks.carminite_slab, ModBlocks.carminite_slab, ModBlocks.double_carminite_slab));
            items.register(new ItemBlock(ModBlocks.ironwood_fence) {
                @Override
                public int getItemBurnTime(ItemStack stack) {
                    return 0;
                }
            });
            items.registerBlock(ModBlocks.fiery_fence);
            items.registerBlock(ModBlocks.steeleaf_fence);
            items.registerBlock(ModBlocks.arctic_fur_fence);
            items.registerBlock(ModBlocks.carminite_fence);
            items.register(new ItemBlock(ModBlocks.ironwood_trapdoor) {
                @Override
                public int getItemBurnTime(ItemStack stack) {
                    return 0;
                }
            });
            items.registerBlock(ModBlocks.fiery_trapdoor);
            items.registerBlock(ModBlocks.steeleaf_trapdoor);
            items.registerBlock(ModBlocks.arctic_fur_trapdoor);
            items.registerBlock(ModBlocks.carminite_trapdoor);
            items.register(new ItemBlock(ModBlocks.ironwood_fence_gate) {
                @Override
                public int getItemBurnTime(ItemStack stack) {
                    return 0;
                }
            });
            items.registerBlock(ModBlocks.fiery_fence_gate);
            items.registerBlock(ModBlocks.steeleaf_fence_gate);
            items.registerBlock(ModBlocks.arctic_fur_fence_gate);
            items.registerBlock(ModBlocks.carminite_fence_gate);
        }
    },
    THE_BETWEENLANDS("The Betweenlands") {
        @Override
        protected void registerBlocks(ModBlocks.BlockRegistryHelper blocks) {
            Block octine = new Block(Material.IRON, MapColor.IRON);
            Block syrmorite = new Block(Material.IRON, MapColor.IRON);
            Block valonite = new Block(Material.IRON, MapColor.IRON);
            Block scabyst = new Block(Material.ROCK, MapColor.STONE);
            Block sulfur = new Block(Material.ROCK, MapColor.STONE);

            blocks.registerBlock("octine_stairs",    new NiftyBLStairs(octine.getDefaultState(), OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("syrmorite_stairs", new NiftyBLStairs(syrmorite.getDefaultState(), SYRMORITE));
            blocks.registerBlock("valonite_stairs",  new NiftyBLStairs(valonite.getDefaultState(), VALONITE));
            blocks.registerBlock("scabyst_stairs",   new NiftyBLStairs(scabyst.getDefaultState(), SCABYST));
            blocks.registerBlock("sulfur_stairs",    new NiftyBLStairs(sulfur.getDefaultState(), SULFUR));
            blocks.registerBlock("octine_slab",    new NiftyBLSlab(false, OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("syrmorite_slab", new NiftyBLSlab(false, SYRMORITE));
            blocks.registerBlock("valonite_slab",  new NiftyBLSlab(false, VALONITE));
            blocks.registerBlock("scabyst_slab",   new NiftyBLSlab(false, SCABYST));
            blocks.registerBlock("sulfur_slab",    new NiftyBLSlab(false, SULFUR));
            blocks.registerBlock("double_octine_slab",    new NiftyBLSlab(true, OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("double_syrmorite_slab", new NiftyBLSlab(true, SYRMORITE));
            blocks.registerBlock("double_valonite_slab",  new NiftyBLSlab(true, VALONITE));
            blocks.registerBlock("double_scabyst_slab",   new NiftyBLSlab(true, SCABYST));
            blocks.registerBlock("double_sulfur_slab",    new NiftyBLSlab(true, SULFUR));
            blocks.registerBlock("octine_fence",    new NiftyBLFence(OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("syrmorite_fence", new NiftyBLFence(SYRMORITE));
            blocks.registerBlock("valonite_fence",  new NiftyBLFence(VALONITE));
            blocks.registerBlock("scabyst_fence",   new NiftyBLFence(SCABYST));
            blocks.registerBlock("sulfur_fence",    new NiftyBLFence(SULFUR));
            blocks.registerBlock("octine_trapdoor",    new NiftyBLTrapDoor(OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("valonite_trapdoor",  new NiftyBLTrapDoor(VALONITE));
            blocks.registerBlock("sulfur_trapdoor",    new NiftyBLTrapDoor(SULFUR));
            blocks.registerBlock("octine_fence_gate",    new NiftyBLFenceGate(octine::getDefaultState, OCTINE).setLightLevel(0.875F));
            blocks.registerBlock("syrmorite_fence_gate", new NiftyBLFenceGate(syrmorite::getDefaultState, SYRMORITE));
            blocks.registerBlock("valonite_fence_gate",  new NiftyBLFenceGate(valonite::getDefaultState, VALONITE));
            blocks.registerBlock("scabyst_fence_gate",   new NiftyBLFenceGate(scabyst::getDefaultState, SCABYST));
            blocks.registerBlock("sulfur_fence_gate",    new NiftyBLFenceGate(sulfur::getDefaultState, SULFUR));
        }

        @Override
        protected void registerItems(ModBlocks.ItemRegistryHelper items) {
            items.registerBlock(ModBlocks.octine_stairs);
            items.registerBlock(ModBlocks.syrmorite_stairs);
            items.registerBlock(ModBlocks.valonite_stairs);
            items.registerBlock(ModBlocks.scabyst_stairs);
            items.registerBlock(ModBlocks.sulfur_stairs);
            items.register(new ItemSlab(ModBlocks.octine_slab, ModBlocks.octine_slab, ModBlocks.double_octine_slab));
            items.register(new ItemSlab(ModBlocks.syrmorite_slab, ModBlocks.syrmorite_slab, ModBlocks.double_syrmorite_slab));
            items.register(new ItemSlab(ModBlocks.valonite_slab, ModBlocks.valonite_slab, ModBlocks.double_valonite_slab));
            items.register(new ItemSlab(ModBlocks.scabyst_slab, ModBlocks.scabyst_slab, ModBlocks.double_scabyst_slab));
            items.register(new ItemSlab(ModBlocks.sulfur_slab, ModBlocks.sulfur_slab, ModBlocks.double_sulfur_slab));
            items.registerBlock(ModBlocks.octine_fence);
            items.registerBlock(ModBlocks.syrmorite_fence);
            items.registerBlock(ModBlocks.valonite_fence);
            items.registerBlock(ModBlocks.scabyst_fence);
            items.registerBlock(ModBlocks.sulfur_fence);
            items.registerBlock(ModBlocks.octine_trapdoor);
            items.registerBlock(ModBlocks.valonite_trapdoor);
            items.registerBlock(ModBlocks.sulfur_trapdoor);
            items.registerBlock(ModBlocks.octine_fence_gate);
            items.registerBlock(ModBlocks.syrmorite_fence_gate);
            items.registerBlock(ModBlocks.valonite_fence_gate);
            items.registerBlock(ModBlocks.scabyst_fence_gate);
            items.registerBlock(ModBlocks.sulfur_fence_gate);
        }
    };

    protected void registerBlocks(ModBlocks.BlockRegistryHelper blocks) {}
    protected void registerItems(ModBlocks.ItemRegistryHelper items) {}

    final private String mod;
    private boolean active = true;

    public boolean isActivated() {
        return active;
    }

    NiftyCompat(String mod) {
        this.mod = mod;
    }

    public static void initCompatBlocks(ModBlocks.BlockRegistryHelper blocks) {
        for (NiftyCompat compat : NiftyCompat.values()) {
            if (compat.isActivated()) {
                try {
                    compat.registerBlocks(blocks);
                } catch (Exception e) {
                    compat.active = false;
                    NiftyMod.logger.error("An error has occurred during mod compat for " + compat.mod + ".");
                    NiftyMod.logger.catching(e.fillInStackTrace());
                }
            }
        }
    }

    public static void initCompatItems(ModBlocks.ItemRegistryHelper items) {
        for (NiftyCompat compat : NiftyCompat.values()) {
            if (compat.isActivated()) {
                try {
                    compat.registerItems(items);
                } catch (Exception e) {
                    compat.active = false;
                    NiftyMod.logger.error("An error has occurred during mod compat for " + compat.mod + ".");
                    NiftyMod.logger.catching(e.fillInStackTrace());
                }
            }
        }
    }
}
