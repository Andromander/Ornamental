package com.androsa.nifty.compat;

import com.androsa.nifty.ModBlocks;
import com.androsa.nifty.NiftyMod;
import com.androsa.nifty.util.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum NiftyCompat {
    TWILIGHT_FOREST("Twilight Forest") {
        @Override
        protected void registerBlocks(ModBlocks.BlockRegistryHelper blocks) {
            //TODO: Change when TF updates
            Block ironwood = new Block(Material.WOOD, MapColor.WOOD);
            Block fiery = new Block(Material.IRON, MapColor.IRON);
            Block steeleaf = new Block(Material.LEAVES, MapColor.FOLIAGE);
            Block arctic = new Block(Material.CLOTH, MapColor.CLOTH);
            Block carminite = new Block(Material.CLAY, MapColor.CLAY);

            blocks.registerBlock("ironwood_stairs",   new NiftyTFStairs(ironwood.getDefaultState(), SoundType.WOOD, 5.0F, 10.0F));
            blocks.registerBlock("fiery_stairs",      new NiftyTFStairs(fiery.getDefaultState(), SoundType.METAL, 5.0F, 10.0F) {
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
                    return 15728880;
                }
                @Override
                @Deprecated
                public boolean getUseNeighborBrightness(IBlockState state) {
                    return true;
                }
                @Override
                public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
                    if ((!entityIn.isImmuneToFire())
                            && entityIn instanceof EntityLivingBase
                            && (!EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))) {
                        entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
                    }
                    super.onEntityWalk(worldIn, pos, entityIn);
                }
                @Override
                public boolean isFireSource(World world, BlockPos pos, EnumFacing face) {
                    return true;
                }
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
                        IBlockState state = blockAccess.getBlockState(pos.offset(side));
                        return state.getBlock() != this;
                }
                @SideOnly(Side.CLIENT)
                @Override
                public void registerModel() {
                    ModelUtil.registerToState(this, 0, getDefaultState().withProperty(FACING, EnumFacing.EAST));
                    ModelResourceLocation mrl = new ModelResourceLocation(this.getRegistryName(), "inventory");
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, mrl);
                }
            });
            blocks.registerBlock("steeleaf_stairs",   new NiftyTFStairs(steeleaf.getDefaultState(), SoundType.PLANT, 5.0F, 10.0F) {
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.75F);
                }
            });
            blocks.registerBlock("arctic_fur_stairs", new NiftyTFStairs(arctic.getDefaultState(), SoundType.CLOTH, 0.8F, 10.0F) {
                @Override
                @Deprecated
                public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
                    return player.getHeldItemMainhand().getItem() instanceof ItemShears ? 0.2F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
                }
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.1F);
                }
            });
            blocks.registerBlock("carminite_stairs",  new NiftyTFStairs(carminite.getDefaultState(), SoundType.SLIME, 0.0F, 10.0F));
            blocks.registerBlock("ironwood_slab",   new NiftyTFSlab(false, Material.WOOD, MapColor.WOOD, SoundType.WOOD, 5.0F));
            blocks.registerBlock("fiery_slab",      new NiftyTFSlab(false, Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F){
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
                    return 15728880;
                }
                @Override
                @Deprecated
                public boolean getUseNeighborBrightness(IBlockState state) {
                    return true;
                }
                @Override
                public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
                    if ((!entityIn.isImmuneToFire())
                            && entityIn instanceof EntityLivingBase
                            && (!EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))) {
                        entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
                    }
                    super.onEntityWalk(worldIn, pos, entityIn);
                }
                @Override
                public boolean isFireSource(World world, BlockPos pos, EnumFacing face) {
                    return true;
                }
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
                    IBlockState state = blockAccess.getBlockState(pos.offset(side));
                    return state.getBlock() != this;
                }
                @SideOnly(Side.CLIENT)
                @Override
                public void registerModel() {
                    ModelResourceLocation mrl = new ModelResourceLocation(this.getRegistryName(), "inventory_fiery");
                    ModelUtil.registerToState(this, 0, getDefaultState());
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, mrl);
                }
            });
            blocks.registerBlock("steeleaf_slab",   new NiftyTFSlab(false, Material.LEAVES, MapColor.FOLIAGE, SoundType.PLANT, 5.0F) {
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.75F);
                }
            });
            blocks.registerBlock("arctic_fur_slab", new NiftyTFSlab(false, Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F) {
                @Override
                @Deprecated
                public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
                    return player.getHeldItemMainhand().getItem() instanceof ItemShears ? 0.2F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
                }
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.1F);
                }
            });
            blocks.registerBlock("carminite_slab",  new NiftyTFSlab(false, Material.CLAY, MapColor.CLAY, SoundType.SLIME, 0.0F));
            blocks.registerBlock("double_ironwood_slab",   new NiftyTFSlab(true, Material.WOOD, MapColor.WOOD, SoundType.WOOD, 5.0F));
            blocks.registerBlock("double_fiery_slab",      new NiftyTFSlab(true, Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F){
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
                    return 15728880;
                }
                @Override
                @Deprecated
                public boolean getUseNeighborBrightness(IBlockState state) {
                    return true;
                }
                @Override
                public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
                    if ((!entityIn.isImmuneToFire())
                            && entityIn instanceof EntityLivingBase
                            && (!EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))) {
                        entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
                    }
                    super.onEntityWalk(worldIn, pos, entityIn);
                }
                @Override
                public boolean isFireSource(World world, BlockPos pos, EnumFacing face) {
                    return true;
                }
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
                    IBlockState state = blockAccess.getBlockState(pos.offset(side));
                    return state.getBlock() != this;
                }
            });
            blocks.registerBlock("double_steeleaf_slab",   new NiftyTFSlab(true, Material.LEAVES, MapColor.FOLIAGE, SoundType.PLANT, 5.0F) {
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.75F);
                }
            });
            blocks.registerBlock("double_arctic_fur_slab", new NiftyTFSlab(true, Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F) {
                @Override

                public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
                    return player.getHeldItemMainhand().getItem() instanceof ItemShears ? 0.2F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
                }
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.1F);
                }
            });
            blocks.registerBlock("double_carminite_slab",  new NiftyTFSlab(true, Material.CLAY, MapColor.CLAY, SoundType.SLIME, 0.0F));
            blocks.registerBlock("ironwood_fence",   new NiftyTFFence(Material.WOOD, MapColor.WOOD, SoundType.WOOD, 5.0F));
            blocks.registerBlock("fiery_fence",      new NiftyTFFence(Material.IRON, MapColor.IRON, SoundType.METAL, 5.0F){
                @Override
                @SideOnly(Side.CLIENT)
                public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
                    return 15728880;
                }
                @Override
                public boolean getUseNeighborBrightness(IBlockState state) {
                    return true;
                }
                @Override
                public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
                    if ((!entityIn.isImmuneToFire())
                            && entityIn instanceof EntityLivingBase
                            && (!EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn))) {
                        entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
                    }
                    super.onEntityWalk(worldIn, pos, entityIn);
                }
                @Override
                public boolean isFireSource(World world, BlockPos pos, EnumFacing face) {
                    return true;
                }
                @Override
                @Deprecated
                @SideOnly(Side.CLIENT)
                public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
                    IBlockState state = blockAccess.getBlockState(pos.offset(side));
                    return state.getBlock() != this;
                }
                @Override
                public void registerModel() {
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
                }
            });
            blocks.registerBlock("steeleaf_fence",   new NiftyTFFence(Material.LEAVES, MapColor.FOLIAGE, SoundType.PLANT, 5.0F) {
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.75F);
                }
            });
            blocks.registerBlock("arctic_fur_fence", new NiftyTFFence(Material.CLOTH, MapColor.CLOTH, SoundType.CLOTH, 0.8F) {
                @Override
                @Deprecated
                public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
                    return player.getHeldItemMainhand().getItem() instanceof ItemShears ? 0.2F : super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
                }
                @Override
                public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
                    entityIn.fall(fallDistance, 0.1F);
                }
            });
            blocks.registerBlock("carminite_fence",  new NiftyTFFence(Material.CLAY, MapColor.CLAY, SoundType.SLIME, 0.0F));
        }

        @Override
        protected void registerItems(ModBlocks.ItemRegistryHelper items) {
            items.registerBlock(ModBlocks.ironwood_stairs);
            items.registerBlock(ModBlocks.fiery_stairs);
            items.registerBlock(ModBlocks.steeleaf_stairs);
            items.registerBlock(ModBlocks.arctic_fur_stairs);
            items.registerBlock(ModBlocks.carminite_stairs);
            items.register(new ItemSlab(ModBlocks.ironwood_slab, ModBlocks.ironwood_slab, ModBlocks.double_ironwood_slab));
            items.register(new ItemSlab(ModBlocks.fiery_slab, ModBlocks.fiery_slab, ModBlocks.double_fiery_slab));
            items.register(new ItemSlab(ModBlocks.steeleaf_slab, ModBlocks.steeleaf_slab, ModBlocks.double_steeleaf_slab));
            items.register(new ItemSlab(ModBlocks.arctic_fur_slab, ModBlocks.arctic_fur_slab, ModBlocks.double_arctic_fur_slab));
            items.register(new ItemSlab(ModBlocks.carminite_slab, ModBlocks.carminite_slab, ModBlocks.double_carminite_slab));
            items.registerBlock(ModBlocks.ironwood_fence);
            items.registerBlock(ModBlocks.fiery_fence);
            items.registerBlock(ModBlocks.steeleaf_fence);
            items.registerBlock(ModBlocks.arctic_fur_fence);
            items.registerBlock(ModBlocks.carminite_fence);
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
