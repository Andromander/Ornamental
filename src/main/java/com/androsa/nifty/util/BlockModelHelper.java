package com.androsa.nifty.util;

import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface BlockModelHelper {

    @SideOnly(Side.CLIENT)
    default void registerModel() {
        ModelUtil.registerToState((Block) this, 0, ((Block) this).getDefaultState());
    }
}
