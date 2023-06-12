package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class OrnamentalRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private final String modID;

    public OrnamentalRecipeProvider(PackOutput output, String modid) {
        super(output);
        this.modID = modid;
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(modID, name);
    }

    public void stairs(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentStair> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_stairs");
    }

    /*
     * In the event a Stair recipe is conflicting with another, this is almost guaranteed to work
     */
    public void stairsOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentStair> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("# /")
                .pattern("## ")
                .pattern("###")
                .define('/', ItemTags.STAIRS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_stairs");
    }

    public void slab(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_slab");
    }

    /*
     * In the event a Slab recipe is conflicting with another, this is almost guaranteed to work
     */
    public void slabOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
		ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
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
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence");
    }

    /*
     * In the event a Fence recipe is conflicting with another, this is almost guaranteed to work
     */
    public void fenceOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 3)
                .pattern(" * ")
                .pattern("#/#")
                .pattern("#/#")
                .define('*', ItemTags.FENCES)
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence");
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoor(consumer, result, ingredient.get());
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    /*
     * In the event a Trapdoor recipe is conflicting with another, this is almost guaranteed to work
     */
    public void trapdoorOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" /")
                .pattern("##")
                .pattern("##")
                .define('/', ItemTags.TRAPDOORS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoorWide(consumer, result, ingredient.get());
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    /*
     * In the event a wide Trapdoor recipe is conflicting with another, this is almost guaranteed to work
     */
    public void trapdoorWideOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" / ")
                .pattern("###")
                .pattern("###")
                .define('/', ItemTags.TRAPDOORS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, Supplier<? extends OrnamentSlab> smallItem) {
        fencegate(consumer, result, bigItem, smallItem.get());
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("/#/")
                .pattern("/#/")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence_gate");
    }

    /*
     * In the event a Fence Gate recipe is conflicting with another, this is almost guaranteed to work
     */
    public void fencegateOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" * ")
                .pattern("/#/")
                .pattern("/#/")
                .define('*', Tags.Items.FENCE_GATES)
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(consumer, recipe, result.get(), bigItem, "_fence_gate");
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        door(consumer, result, ingredient.get());
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_door");
    }

    /*
     * In the event a Door recipe is conflicting with another, this is almost guaranteed to work
     */
    public void doorOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("## ")
                .pattern("##/")
                .pattern("## ")
                .define('/', ItemTags.DOORS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_door");
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, Supplier<? extends OrnamentSlab> ingredient) {
        pole(consumer, result, ingredient.get());
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_pole");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void poleOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("# ")
                .pattern("#/")
                .pattern("# ")
                .define('/', ModTags.Items.POLES)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_pole");
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, Supplier<? extends OrnamentSlab> ingredient) {
        beam(consumer, result, ingredient.get());
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_beam");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void beamOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern(" / ")
                .pattern("###")
                .define('/', ModTags.Items.BEAMS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_beam");
    }

    public void convertPoleBeam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, beam.get())
                .requires(pole.get());
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pole.get())
                .requires(beam.get());

        internalRecipeBuild(consumer, polerecipe, beam.get(), pole.get(), "_pole_to_beam");
        internalRecipeBuild(consumer, beamrecipe, pole.get(), beam.get(), "_beam_to_pole");
    }

    public void wall(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_wall");
    }

    /*
     * In the event a Wall recipe is conflicting with another, this is almost guaranteed to work
     */
    public void wallOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .pattern(" / ")
                .pattern("###")
                .pattern("###")
                .define('/', ItemTags.WALLS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_wall");
    }

    public void saddleDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentTrapDoor> ingredient) {
        saddleDoor(consumer, result, ingredient.get());
    }

    public void saddleDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 2)
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_saddle_door");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void saddleDoorOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 2)
                .pattern(" / ")
                .pattern("#")
                .pattern("#")
                .define('/', ModTags.Items.SADDLE_DOORS)
                .define('#', ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_saddle_door");
    }

    public void saddleDoorFromDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentDoor> ingredient) {
        saddleDoorFromDoor(consumer, result, ingredient.get());
    }

    public void saddleDoorFromDoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapelessRecipeBuilder recipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result.get(), 2)
                .requires(ingredient);

        internalRecipeBuild(consumer, recipe, result.get(), ingredient, "_saddle_door_from_door");
    }

    private void internalRecipeBuild(Consumer<FinishedRecipe> consumer, RecipeBuilder recipe, OrnamentalBlock result, ItemLike criteria, String name) {
        OrnamentBuilder builder = result.getBuilder();
        recipe = recipe.unlockedBy("has_" + builder.name, has(criteria));
        ResourceLocation location = loc(builder.name + name);

        recipe.save(consumer, location);
    }
}