package com.androsa.ornamental.data;

import com.androsa.ornamental.data.provider.OrnamentalItemModelProvider;
import com.androsa.ornamental.registry.ModEntities;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrnamentalItemModels extends OrnamentalItemModelProvider {

    public OrnamentalItemModels(ItemModelGenerators itemModels) {
        super(itemModels);
    }

    @Override
    public void runItemGen() {
        for (Map.Entry<Supplier<Item>, List<Integer>> entry : ModEntities.ITEM_TO_INTS.entrySet()) {
            this.eggItem(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
        }
    }
}
