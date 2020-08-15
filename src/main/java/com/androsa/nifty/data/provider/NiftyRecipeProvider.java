package com.androsa.nifty.data.provider;

import com.androsa.nifty.builder.NiftyBuilder;
import com.androsa.nifty.NiftyMod;
import com.androsa.nifty.blocks.*;
import com.androsa.nifty.data.conditions.ConfigCondition;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class NiftyRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public NiftyRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(NiftyMod.MODID, name);
    }

    public void stairs(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyStairs> result, Block ingredient) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get(), 8)
                        .patternLine("#  ")
                        .patternLine("## ")
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + builder.name, hasItem(ingredient))
                        ::build)
                .build(consumer, loc(builder.name + "_stairs"));
    }

    public void slab(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftySlab> result, Block ingredient) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + builder.name, hasItem(ingredient))
                        ::build)
                .build(consumer, loc(builder.name + "_slab"));
    }

    public void fence(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyFence> result, Block bigItem, Supplier<? extends SlabBlock> smallItem) {
        fence(consumer, result, bigItem, smallItem.get());
    }

    public void fence(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyFence> result, Block bigItem, IItemProvider smallItem) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get(), 3)
                        .patternLine("#/#")
                        .patternLine("#/#")
                        .key('#', bigItem)
                        .key('/', smallItem)
                        .addCriterion("has_" + builder.name, hasItem(bigItem))
                        ::build)
                .build(consumer, loc(builder.name + "_fence"));
    }

    public void trapdoor(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyTrapDoor> result, Supplier<? extends NiftySlab> ingredient) {
        trapdoor(consumer, result, ingredient.get());
    }

    public void trapdoor(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyTrapDoor> result, IItemProvider ingredient) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get())
                        .patternLine("##")
                        .patternLine("##")
                        .key('#', ingredient)
                        .addCriterion("has_" + builder.name, hasItem(ingredient))
                        ::build)
                .build(consumer, loc(builder.name + "_trapdoor"));
    }

    public void trapdoorWide(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyTrapDoor> result, Supplier<? extends NiftySlab> ingredient) {
        trapdoorWide(consumer, result, ingredient.get());
    }

    public void trapdoorWide(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyTrapDoor> result, IItemProvider ingredient) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get())
                        .patternLine("###")
                        .patternLine("###")
                        .key('#', ingredient)
                        .addCriterion("has_" + builder.name, hasItem(ingredient))
                        ::build)
                .build(consumer, loc(builder.name + "_trapdoor"));
    }

    public void fencegate(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyFenceGate> result, Block bigItem, Supplier<? extends NiftySlab> smallItem) {
        fencegate(consumer, result, bigItem, smallItem.get());
    }

    public void fencegate(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyFenceGate> result, Block bigItem, IItemProvider smallItem) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get())
                        .patternLine("/#/")
                        .patternLine("/#/")
                        .key('#', bigItem)
                        .key('/', smallItem)
                        .addCriterion("has_" + builder.name, hasItem(bigItem))
                        ::build)
                .build(consumer, loc(builder.name + "_fence_gate"));
    }

    public void door(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyDoor> result, Supplier<? extends NiftySlab> ingredient) {
        door(consumer, result, ingredient.get());
    }

    public void door(Consumer<IFinishedRecipe> consumer, Supplier<? extends NiftyDoor> result, IItemProvider ingredient) {
        NiftyBuilder builder = result.get().getBuilder();
        ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                .addRecipe(ShapedRecipeBuilder.shapedRecipe(result.get())
                        .patternLine("##")
                        .patternLine("##")
                        .patternLine("##")
                        .key('#', ingredient)
                        .addCriterion("has_" + builder.name, hasItem(ingredient))
                        ::build)
                .build(consumer, loc(builder.name + "_door"));
    }
}
