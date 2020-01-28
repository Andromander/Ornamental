package com.androsa.nifty.data.conditions;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.NiftyMod;
import com.google.gson.JsonObject;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ConfigCondition implements ICondition {

    private final String config;

    public ConfigCondition(String config) {
        this.config = config;
    }

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(NiftyMod.MODID, "config");
    }

    @Override
    public boolean test() {
        for (NiftyBlock block : NiftyBlock.values()) {
            String bool = block.booleanValue.get().getPath().get(0);
            if (bool.equals(config)) {
                return block.booleanValue.get().get();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "config";
    }

    public static class Serializer implements IConditionSerializer<ConfigCondition> {
        @Override
        public void write(JsonObject json, ConfigCondition value) {
            json.addProperty("config", value.config);
        }

        @Override
        public ConfigCondition read(JsonObject json) {
            return new ConfigCondition(JSONUtils.getString(json, "config"));
        }

        @Override
        public ResourceLocation getID() {
            return new ResourceLocation(NiftyMod.MODID, "config");
        }
    }
}
