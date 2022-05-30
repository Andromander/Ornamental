package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class OrnamentalRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private final String modID;

    public OrnamentalRecipeProvider(DataGenerator generator, String modid) {
        super(generator);
        this.modID = modid;
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(modID, name);
    }

    public void stairs(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentStair> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_stairs");
    }

    public void slab(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_slab");
    }

    public void slabOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
		ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
				.pattern(" / ")
				.pattern("###")
				.define('/', ItemTags.SLABS)
				.define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_slab");
	}

    public void fence(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, Supplier<? extends SlabBlock> smallItem) {
        fence(consumer, result, bigItem, smallItem.get());
    }

    public void fence(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence");
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoor(consumer, result, ingredient.get());
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoorWide(consumer, result, ingredient.get());
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, Supplier<? extends OrnamentSlab> smallItem) {
        fencegate(consumer, result, bigItem, smallItem.get());
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("/#/")
                .pattern("/#/")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence_gate");
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        door(consumer, result, ingredient.get());
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_door");
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, Supplier<? extends OrnamentSlab> ingredient) {
        pole(consumer, result, ingredient.get());
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_pole");
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, Supplier<? extends OrnamentSlab> ingredient) {
        beam(consumer, result, ingredient.get());
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_beam");
    }

    public void convertPoleBeam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapeless(beam.get())
                .requires(pole.get());
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapeless(pole.get())
                .requires(beam.get());

        internalRecipeBuild(consumer, polerecipe, beam.get(), pole.get(), "_pole_to_beam");
        internalRecipeBuild(consumer, beamrecipe, pole.get(), beam.get(), "_beam_to_pole");
    }

    public void wall(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_wall");
    }

    public void saddleDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentTrapDoor> ingredient) {
        saddleDoor(consumer, result, ingredient.get());
    }

    public void saddleDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 2)
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_saddle_door");
    }

    public void saddleDoorFromDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentDoor> ingredient) {
        saddleDoorFromDoor(consumer, result, ingredient.get());
    }

    public void saddleDoorFromDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapelessRecipeBuilder recipe = ShapelessRecipeBuilder.shapeless(result.get(), 2)
                .requires(ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_saddle_door_from_door");
    }

    private void internalRecipeBuild(Consumer<FinishedRecipe> consumer, RecipeBuilder recipe, OrnamentalBlock result, ItemLike criteria, String name) {
        OrnamentBuilder builder = result.getBuilder();
        recipe = recipe.unlockedBy("has_" + builder.name, has(criteria));
        ResourceLocation location = loc(builder.name + name);

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, location);
        } else {
            recipe.save(consumer, location);
        }
    }
}