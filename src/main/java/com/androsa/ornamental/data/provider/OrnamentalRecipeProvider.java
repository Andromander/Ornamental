package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class OrnamentalRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private final String modID;

    public OrnamentalRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(output, provider);
        this.modID = modid;
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(modID, name);
    }

    /**
     * Generates a Stairs recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentStair
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void stairs(RecipeOutput output, Supplier<? extends OrnamentStair> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8);
        if (override) {
            recipe.define('/', ItemTags.STAIRS)
                    .pattern("# /")
                    .pattern("## ")
                    .pattern("###");
        } else {
            recipe.pattern("#  ")
                    .pattern("## ")
                    .pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_stairs");
    }

    /**
     * Generates a Slab recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentSlab
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void slab(RecipeOutput output, Supplier<? extends OrnamentSlab> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6);
        if (override) {
            recipe.define('/', ItemTags.SLABS)
                    .pattern(" / ")
                    .pattern("###");
        } else {
            recipe.pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_slab");
    }

    /**
     * Generates a Fence recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentFence
     * @param bigItem The ingredient required to craft this recipe. This is typically a "big" ingredient
     * @param smallItem The ingredient required to craft this recipe. This is typically a "small" ingredient
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void fence(RecipeOutput output, Supplier<? extends OrnamentFence> result, ItemLike bigItem, ItemLike smallItem, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 3);
        if (override) {
            recipe.define('*', ItemTags.FENCES)
                    .pattern(" * ")
                    .pattern("#/#")
                    .pattern("#/#");
        } else {
            recipe.pattern("#/#")
                    .pattern("#/#");
        }
        recipe.define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence");
    }

    /**
     * Generates a Trapdoor recipe. This formation is for "small" forms (2x2)
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentTrapDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void trapdoor(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get());
        if (override) {
            recipe.define('/', ItemTags.TRAPDOORS)
                    .pattern(" /")
                    .pattern("##")
                    .pattern("##");
        } else {
            recipe.pattern("##")
                    .pattern("##");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    /**
     * Generates a Trapdoor recipe. This formation is for "wide" forms (2x3)
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentTrapDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void trapdoorWide(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get());
        if (override) {
            recipe.define('/', ItemTags.TRAPDOORS)
                    .pattern(" / ")
                    .pattern("###")
                    .pattern("###");
        } else {
            recipe.pattern("###")
                    .pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    /**
     * Generates a Fence Gate recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentFenceGate
     * @param bigItem The ingredient required to craft this recipe. This is typically a "big" ingredient
     * @param smallItem The ingredient required to craft this recipe. This is typically a "small" ingredient
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void fencegate(RecipeOutput output, Supplier<? extends OrnamentFenceGate> result, ItemLike bigItem, ItemLike smallItem, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get());
        if (override) {
            recipe.define('*', Tags.Items.FENCE_GATES)
                    .pattern(" * ")
                    .pattern("/#/")
                    .pattern("/#/");
        } else {
            recipe.pattern("/#/")
                    .pattern("/#/");
        }
        recipe.define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence_gate");
    }

    /**
     * Generates a Door recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void door(RecipeOutput output, Supplier<? extends OrnamentDoor> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get());
        if (override) {
            recipe.define('/', ItemTags.DOORS)
                    .pattern("## ")
                    .pattern("##/")
                    .pattern("## ");
        } else {
            recipe.pattern("##")
                    .pattern("##")
                    .pattern("##");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_door");
    }

    /**
     * Generates a Pole recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentPole
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void pole(RecipeOutput output, Supplier<? extends OrnamentPole> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6);
        if (override)  {
            recipe.define('/', ModTags.Items.POLES)
                    .pattern("# ")
                    .pattern("#/")
                    .pattern("# ");
        } else {
            recipe.pattern("#")
                    .pattern("#")
                    .pattern("#");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_pole");
    }

    /**
     * Generates a Beam recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentBeam
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void beam(RecipeOutput output, Supplier<? extends OrnamentBeam> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6);
        if (override) {
            recipe.define('/', ModTags.Items.BEAMS)
                    .pattern(" / ")
                    .pattern("###");
        } else {
            recipe.pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_beam");
    }

    /**
     * Generates two recipes for converting between Poles and Beams
     * @param output The RecipeOutput from the data generator
     * @param pole The OrnamentPole as an ingredient and output
     * @param beam the OrnamentBeam as an ingredient and output
     */
    public void convertPoleBeam(RecipeOutput output, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, beam.get())
                .requires(pole.get());
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pole.get())
                .requires(beam.get());

        internalRecipeBuild(output, polerecipe, beam.get(), pole.get(), "_pole_to_beam");
        internalRecipeBuild(output, beamrecipe, pole.get(), beam.get(), "_beam_to_pole");
    }

    /**
     * Generates a Wall recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentWall
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void wall(RecipeOutput output, Supplier<? extends OrnamentWall> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6);
        if (override) {
            recipe.define('/', ItemTags.WALLS)
                    .pattern(" / ")
                    .pattern("###")
                    .pattern("###");
        } else {
            recipe.pattern("###")
                    .pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_wall");
    }

    /**
     * Generates a Saddle Door recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentSaddleDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void saddleDoor(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient, boolean override) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 2);
        if (override) {
            recipe.define('/', ModTags.Items.SADDLE_DOORS)
                    .pattern(" / ")
                    .pattern("#")
                    .pattern("#");
        } else {
            recipe.pattern("#")
                    .pattern("#");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_saddle_door");
    }

    /**
     * Generates a recipe for converting Doors to SaddleDoors
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentSaddleDoor
     * @param ingredient The ingredient required to craft this recipe
     */
    public void saddleDoorFromDoor(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapelessRecipeBuilder recipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result.get(), 2)
                .requires(ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_saddle_door_from_door");
    }

    private void internalRecipeBuild(RecipeOutput output, RecipeBuilder recipe, OrnamentalBlock result, ItemLike criteria, String name) {
        OrnamentBuilder builder = result.getBuilder();
        recipe = recipe.unlockedBy("has_" + builder.name, has(criteria));
        ResourceLocation location = loc(builder.name + name);

        recipe.save(output, location);
    }
}