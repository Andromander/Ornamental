package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class OrnamentalRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    private final String modID;

    public OrnamentalRecipeProvider(DataGenerator generator, String modid) {
        super(generator);
        this.modID = modid;
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(modID, name);
    }

    public void stairs(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentStairs> result, Block ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get(), 8)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_stairs"));
        } else {
            recipe.build(consumer, loc(builder.name + "_stairs"));
        }
    }

    public void slab(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_slab"));
        } else {
            recipe.build(consumer, loc(builder.name + "_slab"));
        }
    }

    public void fence(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, Supplier<? extends SlabBlock> smallItem) {
        fence(consumer, result, bigItem, smallItem.get());
    }

    public void fence(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, IItemProvider smallItem) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get(), 3)
                .patternLine("#/#")
                .patternLine("#/#")
                .key('#', bigItem)
                .key('/', smallItem)
                .addCriterion("has_" + builder.name, hasItem(bigItem));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_fence"));
        } else {
            recipe.build(consumer, loc(builder.name + "_fence"));
        }
    }

    public void trapdoor(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoor(consumer, result, ingredient.get());
    }

    public void trapdoor(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, IItemProvider ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_trapdoor"));
        } else {
            recipe.build(consumer, loc(builder.name + "_trapdoor"));
        }
    }

    public void trapdoorWide(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoorWide(consumer, result, ingredient.get());
    }

    public void trapdoorWide(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, IItemProvider ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_trapdoor"));
        } else {
            recipe.build(consumer, loc(builder.name + "_trapdoor"));
        }
    }

    public void fencegate(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, Supplier<? extends OrnamentSlab> smallItem) {
        fencegate(consumer, result, bigItem, smallItem.get());
    }

    public void fencegate(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, IItemProvider smallItem) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("/#/")
                .patternLine("/#/")
                .key('#', bigItem)
                .key('/', smallItem)
                .addCriterion("has_" + builder.name, hasItem(bigItem));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_fence_gate"));
        } else {
            recipe.build(consumer, loc(builder.name + "_fence_gate"));
        }
    }

    public void door(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        door(consumer, result, ingredient.get());
    }

    public void door(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, IItemProvider ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_door"));
        } else {
            recipe.build(consumer, loc(builder.name + "_door"));
        }
    }

    public void pole(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, Supplier<? extends OrnamentSlab> ingredient) {
        pole(consumer, result, ingredient.get());
    }

    public void pole(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, IItemProvider ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_pole"));
        } else {
            recipe.build(consumer, loc(builder.name + "_pole"));
        }
    }

    public void beam(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, Supplier<? extends OrnamentSlab> ingredient) {
        beam(consumer, result, ingredient.get());
    }

    public void beam(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, IItemProvider ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + builder.name, hasItem(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::build)
                    .build(consumer, loc(builder.name + "_beam"));
        } else {
            recipe.build(consumer, loc(builder.name + "_beam"));
        }
    }

    public void convertPoleBeam(Consumer<IFinishedRecipe> consumer, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        OrnamentBuilder polebuilder = pole.get().getBuilder();
        OrnamentBuilder beambuilder = beam.get().getBuilder();

        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapelessRecipe(beam.get())
                .addIngredient(pole.get())
                .addCriterion("has_" + polebuilder.name, hasItem(pole.get()));
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapelessRecipe(pole.get())
                .addIngredient(beam.get())
                .addCriterion("has_" + beambuilder.name, hasItem(beam.get()));

        if (polebuilder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(polebuilder.booleanValue.get().getPath().get(0)))
                    .addRecipe(polerecipe::build)
                    .build(consumer, loc(polebuilder.name + "_pole_to_beam"));
        } else {
            polerecipe.build(consumer, loc(polebuilder.name + "_pole_to_beam"));
        }

        if (beambuilder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(beambuilder.booleanValue.get().getPath().get(0)))
                    .addRecipe(beamrecipe::build)
                    .build(consumer, loc(beambuilder.name + "_beam_to_pole"));
        } else {
            beamrecipe.build(consumer, loc(beambuilder.name + "_beam_to_pole"));
        }
    }
}
