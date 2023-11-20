package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
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

    public void stairs(RecipeOutput output, Supplier<? extends OrnamentStair> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_stairs");
    }

    /*
     * In the event a Stair recipe is conflicting with another, this is almost guaranteed to work
     */
    public void stairsOverride(RecipeOutput output, Supplier<? extends OrnamentStair> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("# /")
                .pattern("## ")
                .pattern("###")
                .define('/', ItemTags.STAIRS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_stairs");
    }

    public void slab(RecipeOutput output, Supplier<? extends OrnamentSlab> result, Block ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_slab");
    }

    /*
     * In the event a Slab recipe is conflicting with another, this is almost guaranteed to work
     */
    public void slabOverride(RecipeOutput output, Supplier<? extends OrnamentSlab> result, Block ingredient) {
		ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
				.pattern(" / ")
				.pattern("###")
				.define('/', ItemTags.SLABS)
				.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_slab");
	}

    public void fence(RecipeOutput output, Supplier<? extends OrnamentFence> result, Block bigItem, Supplier<? extends SlabBlock> smallItem) {
        fence(output, result, bigItem, smallItem.get());
    }

    public void fence(RecipeOutput output, Supplier<? extends OrnamentFence> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence");
    }

    /*
     * In the event a Fence recipe is conflicting with another, this is almost guaranteed to work
     */
    public void fenceOverride(RecipeOutput output, Supplier<? extends OrnamentFence> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 3)
                .pattern(" * ")
                .pattern("#/#")
                .pattern("#/#")
                .define('*', ItemTags.FENCES)
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence");
    }

    public void trapdoor(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoor(output, result, ingredient.get());
    }

    public void trapdoor(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    /*
     * In the event a Trapdoor recipe is conflicting with another, this is almost guaranteed to work
     */
    public void trapdoorOverride(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" /")
                .pattern("##")
                .pattern("##")
                .define('/', ItemTags.TRAPDOORS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void trapdoorWide(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoorWide(output, result, ingredient.get());
    }

    public void trapdoorWide(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    /*
     * In the event a wide Trapdoor recipe is conflicting with another, this is almost guaranteed to work
     */
    public void trapdoorWideOverride(RecipeOutput output, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" / ")
                .pattern("###")
                .pattern("###")
                .define('/', ItemTags.TRAPDOORS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_trapdoor");
    }

    public void fencegate(RecipeOutput output, Supplier<? extends OrnamentFenceGate> result, Block bigItem, Supplier<? extends OrnamentSlab> smallItem) {
        fencegate(output, result, bigItem, smallItem.get());
    }

    public void fencegate(RecipeOutput output, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("/#/")
                .pattern("/#/")
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence_gate");
    }

    /*
     * In the event a Fence Gate recipe is conflicting with another, this is almost guaranteed to work
     */
    public void fencegateOverride(RecipeOutput output, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern(" * ")
                .pattern("/#/")
                .pattern("/#/")
                .define('*', Tags.Items.FENCE_GATES)
                .define('#', bigItem)
                .define('/', smallItem);

        internalRecipeBuild(output, recipe, result.get(), bigItem, "_fence_gate");
    }

    public void door(RecipeOutput output, Supplier<? extends OrnamentDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        door(output, result, ingredient.get());
    }

    public void door(RecipeOutput output, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_door");
    }

    /*
     * In the event a Door recipe is conflicting with another, this is almost guaranteed to work
     */
    public void doorOverride(RecipeOutput output, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get())
                .pattern("## ")
                .pattern("##/")
                .pattern("## ")
                .define('/', ItemTags.DOORS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_door");
    }

    public void pole(RecipeOutput output, Supplier<? extends OrnamentPole> result, Supplier<? extends OrnamentSlab> ingredient) {
        pole(output, result, ingredient.get());
    }

    public void pole(RecipeOutput output, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_pole");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void poleOverride(RecipeOutput output, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("# ")
                .pattern("#/")
                .pattern("# ")
                .define('/', ModTags.Items.POLES)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_pole");
    }

    public void beam(RecipeOutput output, Supplier<? extends OrnamentBeam> result, Supplier<? extends OrnamentSlab> ingredient) {
        beam(output, result, ingredient.get());
    }

    public void beam(RecipeOutput output, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_beam");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void beamOverride(RecipeOutput output, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern(" / ")
                .pattern("###")
                .define('/', ModTags.Items.BEAMS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_beam");
    }

    public void convertPoleBeam(RecipeOutput output, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, beam.get())
                .requires(pole.get());
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pole.get())
                .requires(beam.get());

        internalRecipeBuild(output, polerecipe, beam.get(), pole.get(), "_pole_to_beam");
        internalRecipeBuild(output, beamrecipe, pole.get(), beam.get(), "_beam_to_pole");
    }

    public void wall(RecipeOutput output, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .pattern("###")
                .pattern("###")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_wall");
    }

    /*
     * In the event a Wall recipe is conflicting with another, this is almost guaranteed to work
     */
    public void wallOverride(RecipeOutput output, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), 6)
                .pattern(" / ")
                .pattern("###")
                .pattern("###")
                .define('/', ItemTags.WALLS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_wall");
    }

    public void saddleDoor(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentTrapDoor> ingredient) {
        saddleDoor(output, result, ingredient.get());
    }

    public void saddleDoor(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 2)
                .pattern("#")
                .pattern("#")
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_saddle_door");
    }

    /*
     * This wouldn't be necessary, but in the event this recipe shape is occupied, this exists
     */
    public void saddleDoorOverride(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, ItemLike ingredient) {
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result.get(), 2)
                .pattern(" / ")
                .pattern("#")
                .pattern("#")
                .define('/', ModTags.Items.SADDLE_DOORS)
                .define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), ingredient, "_saddle_door");
    }

    public void saddleDoorFromDoor(RecipeOutput output, Supplier<? extends OrnamentSaddleDoor> result, Supplier<? extends OrnamentDoor> ingredient) {
        saddleDoorFromDoor(output, result, ingredient.get());
    }

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