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
