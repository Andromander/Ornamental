package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.registry.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.Optional;
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

    public <T extends Block> Optional<ManagerEntry<? extends T>> entry(Supplier<T> block, boolean override) {
        return Optional.of(new ManagerEntry<>(block, override));
    }

    /**
     * Using an AutoRecipeManager, generates an array of recipes without the need for manual input
     * @param output The RecipeOutput from the data generator
     * @param manager An AutoRecipeManager containing all the required ingredients, outputs, and flags for overrides
     */
    public void autoRecipe(RecipeOutput output, AutoRecipeManager manager) {
        manager.stair().ifPresent(result -> stairs(output, result.block(), manager.bigIngredient(), result.override(), manager.stonecutter()));
        manager.slab().ifPresent(result -> slab(output, result.block(), manager.bigIngredient(), result.override(), manager.stonecutter()));
        manager.fence().ifPresent(result -> fence(output, result.block(), manager.bigIngredient(), manager.smallIngredient(), result.override()));
        manager.trapdoor().ifPresent(result -> {
            if (manager.trapdoorWide()) {
                trapdoorWide(output, result.block(), manager.smallIngredient(), result.override());
            } else {
                trapdoor(output, result.block(), manager.smallIngredient(), result.override());
            }
        });
        manager.fencegate().ifPresent(result -> fencegate(output, result.block(), manager.bigIngredient(), manager.smallIngredient(), result.override()));
        manager.door().ifPresent(result -> door(output, result.block(), manager.smallIngredient(), result.override()));
        manager.pole().ifPresent(result -> manager.slab().ifPresent(s -> pole(output, result.block(), manager.bigIngredient(), s.block().get(), result.override(), manager.stonecutter())));
        manager.beam().ifPresent(result -> manager.slab().ifPresent(s -> beam(output, result.block(), manager.bigIngredient(), s.block().get(), result.override(), manager.stonecutter())));
        if (manager.pole().isPresent() && manager.beam().isPresent()) {
            convertPoleBeam(output, manager.pole().get().block(), manager.beam().get().block());
        }
        manager.wall().ifPresent(result -> wall(output, result.block(), manager.bigIngredient(), result.override(), manager.stonecutter()));
        manager.saddledoor().ifPresent(result -> {
            manager.trapdoor().ifPresent(sd -> saddleDoor(output, result.block(), sd.block().get(), result.override()));
            manager.door().ifPresent(d -> saddleDoorFromDoor(output, result.block(), d.block().get()));
        });
        manager.support().ifPresent(result -> {
            if (manager.pole().isPresent() && manager.beam().isPresent()) {
                support(output, result.block(), manager.bigIngredient(), manager.pole().get().block().get(), manager.beam().get().block().get(), result.override(), manager.stonecutter());
            }
        });
    }

    /**
     * Generates Stairs recipes
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentStair
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe. If there's no Stone Cutter recipe, the recipe output will be 8
     */
    public void stairs(RecipeOutput output, Supplier<? extends OrnamentStair> result, ItemLike ingredient, boolean override, boolean stonecutter) {
        String suffix = "_stairs";
        RecipeCategory category = RecipeCategory.BUILDING_BLOCKS;
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(category, result.get(), stonecutter ? 4 : 8);
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

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), suffix);

        if (stonecutter) {
            stoneCutting(output, category, result, ingredient, 1, suffix);
        }
    }

    /**
     * Generates a Slab recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentSlab
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe
     */
    public void slab(RecipeOutput output, Supplier<? extends Block> result, ItemLike ingredient, boolean override, boolean stonecutter) {
        if (result.get() instanceof OrnamentalBlock ornament) {
            String suffix = "_slab";
            RecipeCategory category = RecipeCategory.BUILDING_BLOCKS;
            ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6);
            if (override) {
                recipe.define('/', ItemTags.SLABS)
                        .pattern(" / ")
                        .pattern("###");
            } else {
                recipe.pattern("###");
            }
            recipe.define('#', ingredient);

            internalRecipeBuild(output, recipe, ornament, List.of(ingredient), suffix);

            if (stonecutter) {
                stoneCutting(output, category, result, ingredient, 2, suffix);
            }
        }
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

        internalRecipeBuild(output, recipe, result.get(), List.of(bigItem), "_fence");
    }

    /**
     * Generates a Trapdoor recipe. This formation is for "small" forms (2x2)
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentTrapDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void trapdoor(RecipeOutput output, Supplier<? extends Block> result, ItemLike ingredient, boolean override) {
        if (result.get() instanceof OrnamentalBlock ornament) {
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

            internalRecipeBuild(output, recipe, ornament, List.of(ingredient), "_trapdoor");
        }
    }

    /**
     * Generates a Trapdoor recipe. This formation is for "wide" forms (2x3)
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentTrapDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void trapdoorWide(RecipeOutput output, Supplier<? extends Block> result, ItemLike ingredient, boolean override) {
        if (result.get() instanceof OrnamentalBlock ornament) {
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

            internalRecipeBuild(output, recipe, ornament, List.of(ingredient), "_trapdoor");
        }
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

        internalRecipeBuild(output, recipe, result.get(), List.of(bigItem), "_fence_gate");
    }

    /**
     * Generates a Door recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentDoor
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     */
    public void door(RecipeOutput output, Supplier<? extends Block> result, ItemLike ingredient, boolean override) {
        if (result.get() instanceof OrnamentalBlock ornament) {
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

            internalRecipeBuild(output, recipe, ornament, List.of(ingredient), "_door");
        }
    }

    /**
     * Generates a Pole recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentPole
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe
     */
    public void pole(RecipeOutput output, Supplier<? extends OrnamentPole> result, ItemLike block, ItemLike ingredient, boolean override, boolean stonecutter) {
        String suffix = "_pole";
        RecipeCategory category = RecipeCategory.BUILDING_BLOCKS;
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(category, result.get(), 6);
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

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), suffix);

        if (stonecutter) {
            stoneCutting(output, category, result, block, 4, suffix);
        }
    }

    /**
     * Generates a Beam recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentBeam
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe
     */
    public void beam(RecipeOutput output, Supplier<? extends OrnamentBeam> result, ItemLike block, ItemLike ingredient, boolean override, boolean stonecutter) {
        String suffix = "_beam";
        RecipeCategory category = RecipeCategory.BUILDING_BLOCKS;
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(category, result.get(), 6);
        if (override) {
            recipe.define('/', ModTags.Items.BEAMS)
                    .pattern(" / ")
                    .pattern("###");
        } else {
            recipe.pattern("###");
        }
        recipe.define('#', ingredient);

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), suffix);

        if (stonecutter) {
            stoneCutting(output, category, result, block, 4, suffix);
        }
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

        internalRecipeBuild(output, polerecipe, beam.get(), List.of(pole.get()), "_pole_to_beam");
        internalRecipeBuild(output, beamrecipe, pole.get(), List.of(beam.get()), "_beam_to_pole");
    }

    /**
     * Generates a Wall recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentWall
     * @param ingredient The ingredient required to craft this recipe
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe
     */
    public void wall(RecipeOutput output, Supplier<? extends OrnamentWall> result, ItemLike ingredient, boolean override, boolean stonecutter) {
        String suffix = "_wall";
        RecipeCategory category = RecipeCategory.DECORATIONS;
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(category, result.get(), 6);
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

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), suffix);

        if (stonecutter) {
            stoneCutting(output, category, result, ingredient, 1, suffix);
        }
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

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), "_saddle_door");
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

        internalRecipeBuild(output, recipe, result.get(), List.of(ingredient), "_saddle_door_from_door");
    }

    /**
     * Generates a Support recipe
     * @param output The RecipeOutput from the data generator
     * @param result The result of the recipe. This must be an OrnamentSupport
     * @param bigItem The ingredient required to craft this recipe. This is typically a "big" ingredient
     * @param pole One ingredient necessary to craft a Support. An OrnamentPole is recommended
     * @param beam One ingredient necessary to craft a Support. An OrnamentBeam is recommended
     * @param override If the recipe is likely to conflict with another recipe, this will secure a less conflicting recipe
     * @param stonecutter If the block can have a Stone Cutter recipe
     */
    public void support(RecipeOutput output, Supplier<? extends OrnamentSupport> result, ItemLike bigItem, ItemLike pole, ItemLike beam, boolean override, boolean stonecutter) {
        RecipeCategory category = RecipeCategory.BUILDING_BLOCKS;
        String suffix = "_support";
        ShapelessRecipeBuilder recipe = ShapelessRecipeBuilder.shapeless(category, result.get(), 2);
        if (override) {
            recipe.requires(ModTags.Items.SUPPORTS);
        }
        recipe.requires(pole);
        recipe.requires(beam);

        internalRecipeBuild(output, recipe, result.get(), List.of(pole, beam), suffix);

        if (stonecutter) {
            stoneCutting(output, category, result, bigItem, 1, suffix);
        }
    }

    /**
     * Generates a Stonecutter recipe. This does not apply to any specific block and can generate whatever input and output.
     * Note that AutoRecipeManager will only output Stairs, Slabs, Poles, Beams, and Walls. Any other blocks will have to be made manually.
     * @param output The RecipeOutput from the data generator
     * @param category The RecipeCategory the recipe belongs to
     * @param result The result of the recipe. Any block may be used as the output
     * @param ingredient The ingredient required to stonecut this recipe
     * @param count How many the recipe will output
     * @param name A prefix for the recipe name
     */
    public void stoneCutting(RecipeOutput output, RecipeCategory category, Supplier<? extends Block> result, ItemLike ingredient, int count, String name) {
        if (result.get() instanceof OrnamentalBlock ornament) {
            SingleItemRecipeBuilder recipe = SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, result.get(), count);
            internalRecipeBuild(output, recipe, ornament, List.of(ingredient), name + "_stone_cutting");
        }
    }

    private void internalRecipeBuild(RecipeOutput output, RecipeBuilder recipe, OrnamentalBlock result, List<ItemLike> criteria, String name) {
        OrnamentBuilder builder = result.getBuilder();
        recipe = recipe.unlockedBy("has_" + builder.name, inventoryTrigger(ItemPredicate.Builder.item().of(criteria.toArray(new ItemLike[0]))));
        ResourceLocation location = loc(builder.name + name);

        recipe.save(output, location);
    }

    /**
     * A Record helper for generating recipes in a batch. An Optional ensures no nulls and efficient method calling.
     * bigIngredient is for ingredients considered "large" such as blocks, while a smallIngredient is for ingredients considered "small" such as items or slab variants.
     * Ingredients accepting Slabs, Trap Doors, and Doors are for more specified recipes that don't use big or small ingredients.
     */
    public record AutoRecipeManager(ItemLike bigIngredient, ItemLike smallIngredient, boolean stonecutter,
                                    Optional<? extends ManagerEntry<? extends OrnamentStair>> stair,
                                    Optional<? extends ManagerEntry<? extends Block>> slab,
                                    Optional<? extends ManagerEntry<? extends OrnamentFence>> fence,
                                    Optional<? extends ManagerEntry<? extends Block>> trapdoor, boolean trapdoorWide,
                                    Optional<? extends ManagerEntry<? extends OrnamentFenceGate>> fencegate,
                                    Optional<? extends ManagerEntry<? extends Block>> door,
                                    Optional<? extends ManagerEntry<? extends OrnamentPole>> pole,
                                    Optional<? extends ManagerEntry<? extends OrnamentBeam>> beam,
                                    Optional<? extends ManagerEntry<? extends OrnamentWall>> wall,
                                    Optional<? extends ManagerEntry<? extends OrnamentSaddleDoor>> saddledoor,
                                    Optional<? extends ManagerEntry<? extends OrnamentSupport>> support) { }

    public record ManagerEntry<T extends Block>(Supplier<? extends T> block, boolean override) {
    }
}