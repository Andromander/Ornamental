package com.androsa.ornamental.data.conditions;

import com.androsa.ornamental.OrnamentalMod;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.Map;
import java.util.function.Supplier;

public class ConfigCondition implements ICondition {

    private final String config;
    private static Map<OrnamentBuilder, Supplier<ForgeConfigSpec.BooleanValue>> configMap = Maps.newHashMap();

    public ConfigCondition(String config) {
        this.config = config;
    }

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(OrnamentalMod.MODID, "config");
    }

    @Override
    public boolean test() {
        for (Supplier<ForgeConfigSpec.BooleanValue> type : configMap.values()) {
            if (this.getConfigName(type).equals(config)) {
                return type.get().get();
            }
        }
        return false;
    }

    private String getConfigName(Supplier<ForgeConfigSpec.BooleanValue> type) {
        return type.get().getPath().get(0);
    }

    @Override
    public String toString() {
        return "config";
    }

    public static void putValue(OrnamentBuilder builder, Supplier<ForgeConfigSpec.BooleanValue> value) {
        configMap.put(builder, value);
    }

    public static class Serializer implements IConditionSerializer<ConfigCondition> {
        @Override
        public void write(JsonObject json, ConfigCondition value) {
            json.addProperty("config", value.config);
        }

        @Override
        public ConfigCondition read(JsonObject json) {
            return new ConfigCondition(JSONUtils.getAsString(json, "config"));
        }

        @Override
        public ResourceLocation getID() {
            return new ResourceLocation(OrnamentalMod.MODID, "config");
        }
    }
}
