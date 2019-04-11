package com.androsa.nifty.blocks;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.util.BlockModelHelper;
import net.minecraft.block.BlockFence;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class NiftyFence extends BlockFence implements BlockModelHelper {

    private final float fallDamage;

    public NiftyFence(NiftyBlock block) {
        super(block.material, block.color);

        this.setSoundType(block.sound);
        this.setHardness(block.hardness);
        this.setResistance(block.resistance);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHarvestLevel(block.tool, block.level);

        fallDamage = block.multiplier;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, fallDamage);
    }

    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
