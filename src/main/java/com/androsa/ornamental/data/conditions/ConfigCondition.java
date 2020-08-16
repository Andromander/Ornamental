package com.androsa.ornamental.data.conditions;

import com.androsa.ornamental.OrnamentalConfig;
import com.androsa.ornamental.OrnamentalMod;
import com.google.gson.JsonObject;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.function.Supplier;

public class ConfigCondition implements ICondition {

    private final String config;

    public ConfigCondition(String config) {
        this.config = config;
    }

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(OrnamentalMod.MODID, "config");
    }

    @Override
    public boolean test() {
        for (ConfigType type : ConfigType.values()) {
            if (type.getConfigName().equals(config)) {
                return type.getConfig();
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
            return new ResourceLocation(OrnamentalMod.MODID, "config");
        }
    }

    private enum ConfigType {
        IRON(() -> OrnamentalConfig.showIronBlocks),
        GOLD(() -> OrnamentalConfig.showGoldBlocks),
        DIAMOND(() -> OrnamentalConfig.showDiamondBlocks),
        EMERALD(() -> OrnamentalConfig.showEmeraldBlocks),
        LAPIS(() -> OrnamentalConfig.showLapisBlocks),
        OBSIDIAN(() -> OrnamentalConfig.showObsidianBlocks),
        COAL(() -> OrnamentalConfig.showCoalBlocks),
        REDSTONE(() -> OrnamentalConfig.showRedstoneBlocks),
        CLAY(() -> OrnamentalConfig.showClayBlocks),
        DIRT(() -> OrnamentalConfig.showDirtBlocks),
        GRASS(() -> OrnamentalConfig.showGrassBlocks),
        HAY(() -> OrnamentalConfig.showHayBlocks),
        PATH(() -> OrnamentalConfig.showPathBlocks),
        BRICK(() -> OrnamentalConfig.showBrickBlocks),
        QUARTZ(() -> OrnamentalConfig.showQuartzBlocks),
        BONE(() -> OrnamentalConfig.showBoneBlocks),
        NETHER_BRICK(() -> OrnamentalConfig.showNetherBrickBlocks),
        RED_NETHER_BRICK(() -> OrnamentalConfig.showRedNetherBrickBlocks),
        SNOW(() -> OrnamentalConfig.showSnowBlocks),
        ICE(() -> OrnamentalConfig.showIceBlocks),
        PACKED_ICE(() -> OrnamentalConfig.showPackedIceBlocks),
        BLUE_ICE(() -> OrnamentalConfig.showBlueIceBlocks),
        NETHERITE(() -> OrnamentalConfig.showNetheriteBlocks);

        private Supplier<ForgeConfigSpec.BooleanValue> configValue;

        ConfigType(Supplier<ForgeConfigSpec.BooleanValue> config) {
            configValue = config;
        }

        public String getConfigName() {
            return configValue.get().getPath().get(0);
        }

        public boolean getConfig() {
            return configValue.get().get();
        }
    }
}
