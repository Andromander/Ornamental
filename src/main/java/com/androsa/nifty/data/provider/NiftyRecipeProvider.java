package com.androsa.nifty.data.provider;

import com.androsa.nifty.NiftyBlock;
import com.androsa.nifty.data.conditions.ConfigCondition;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

public abstract class NiftyRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public NiftyRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    public ConditionalRecipe.Builder stairsRecipe(IItemProvider result, IItemProvider ingredient, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result, 8)
                        .patternLine("#  ")
                        .patternLine("## ")
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + criterion, hasItem(ingredient))
                        ::build);
    }

    public ConditionalRecipe.Builder slabRecipe(IItemProvider result, IItemProvider ingredient, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result, 6)
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + criterion, hasItem(ingredient))
                        ::build);
    }

    public ConditionalRecipe.Builder fenceRecipe(IItemProvider result, IItemProvider bigItem, IItemProvider smallItem, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result, 3)
                        .patternLine("#/#")
                        .patternLine("#/#")
                        .key('#', bigItem)
                        .key('/', smallItem)
                        .addCriterion("has_" + criterion, hasItem(bigItem))
                        ::build);
    }

    public ConditionalRecipe.Builder trapdoorRecipe(IItemProvider result, IItemProvider ingredient, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result)
                        .patternLine("##")
                        .patternLine("##")
                        .key('#', ingredient)
                        .addCriterion("has_" + criterion, hasItem(ingredient))
                        ::build);
    }

    public ConditionalRecipe.Builder trapdoorRecipeWide(IItemProvider result, IItemProvider ingredient, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result)
                        .patternLine("###")
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + criterion, hasItem(ingredient))
                        ::build);
    }

    public ConditionalRecipe.Builder fenceGateRecipe(IItemProvider result, IItemProvider bigItem, IItemProvider smallItem, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result)
                        .patternLine("/#/")
                        .patternLine("/#/")
                        .key('#', bigItem)
                        .key('/', smallItem)
                        .addCriterion("has_" + criterion, hasItem(bigItem))
                        ::build);
    }

    public ConditionalRecipe.Builder doorRecipe(IItemProvider result, IItemProvider ingredient, String criterion, NiftyBlock config) {
        return ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(config.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result)
                        .patternLine("##")
                        .patternLine("##")
                        .patternLine("##")
                        .key('#', ingredient)
                        .addCriterion("has_" + criterion, hasItem(ingredient))
                        ::build);
    }
}
