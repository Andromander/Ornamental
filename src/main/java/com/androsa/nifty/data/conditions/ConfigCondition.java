package com.androsa.nifty.data.conditions;

import com.androsa.nifty.NiftyConfig;
import com.androsa.nifty.NiftyMod;
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
        return new ResourceLocation(NiftyMod.MODID, "config");
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
            return new ResourceLocation(NiftyMod.MODID, "config");
        }
    }

    private enum ConfigType {
        IRON(() -> NiftyConfig.showIronBlocks),
        GOLD(() -> NiftyConfig.showGoldBlocks),
        DIAMOND(() -> NiftyConfig.showDiamondBlocks),
        EMERALD(() -> NiftyConfig.showEmeraldBlocks),
        LAPIS(() -> NiftyConfig.showLapisBlocks),
        OBSIDIAN(() -> NiftyConfig.showObsidianBlocks),
        COAL(() -> NiftyConfig.showCoalBlocks),
        REDSTONE(() -> NiftyConfig.showRedstoneBlocks),
        CLAY(() -> NiftyConfig.showClayBlocks),
        DIRT(() -> NiftyConfig.showDirtBlocks),
        GRASS(() -> NiftyConfig.showGrassBlocks),
        HAY(() -> NiftyConfig.showHayBlocks),
        PATH(() -> NiftyConfig.showPathBlocks),
        BRICK(() -> NiftyConfig.showBrickBlocks),
        QUARTZ(() -> NiftyConfig.showQuartzBlocks),
        BONE(() -> NiftyConfig.showBoneBlocks),
        NETHER_BRICK(() -> NiftyConfig.showNetherBrickBlocks),
        RED_NETHER_BRICK(() -> NiftyConfig.showRedNetherBrickBlocks),
        SNOW(() -> NiftyConfig.showSnowBlocks),
        ICE(() -> NiftyConfig.showIceBlocks),
        PACKED_ICE(() -> NiftyConfig.showPackedIceBlocks),
        BLUE_ICE(() -> NiftyConfig.showBlueIceBlocks);

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
