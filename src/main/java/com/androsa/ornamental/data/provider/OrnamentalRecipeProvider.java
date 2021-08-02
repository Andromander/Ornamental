package com.androsa.ornamental.data.provider;

import com.androsa.ornamental.builder.OrnamentBuilder;
import com.androsa.ornamental.blocks.*;
import com.androsa.ornamental.data.conditions.ConfigCondition;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_stairs"));
        } else {
            recipe.save(consumer, loc(builder.name + "_stairs"));
        }
    }

    public void slab(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_slab"));
        } else {
            recipe.save(consumer, loc(builder.name + "_slab"));
        }
    }

    public void slabOverride(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentSlab> result, Block ingredient) {
		OrnamentBuilder builder = result.get().getBuilder();

		ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
				.pattern(" / ")
				.pattern("###")
				.define('/', ItemTags.SLABS)
				.define('#', ingredient)
				.unlockedBy("has_" + builder.name, has(ingredient));

		if (builder.hasConfig) {
			ConditionalRecipe.builder()
					.addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
					.addRecipe(recipe::save)
					.build(consumer, loc(builder.name + "_slab"));
		} else {
			recipe.save(consumer, loc(builder.name + "_slab"));
		}
	}

    public void fence(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, Supplier<? extends SlabBlock> smallItem) {
        fence(consumer, result, bigItem, smallItem.get());
    }

    public void fence(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFence> result, Block bigItem, ItemLike smallItem) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', bigItem)
                .define('/', smallItem)
                .unlockedBy("has_" + builder.name, has(bigItem));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_fence"));
        } else {
            recipe.save(consumer, loc(builder.name + "_fence"));
        }
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoor(consumer, result, ingredient.get());
    }

    public void trapdoor(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_trapdoor"));
        } else {
            recipe.save(consumer, loc(builder.name + "_trapdoor"));
        }
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        trapdoorWide(consumer, result, ingredient.get());
    }

    public void trapdoorWide(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentTrapDoor> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_trapdoor"));
        } else {
            recipe.save(consumer, loc(builder.name + "_trapdoor"));
        }
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, Supplier<? extends OrnamentSlab> smallItem) {
        fencegate(consumer, result, bigItem, smallItem.get());
    }

    public void fencegate(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentFenceGate> result, Block bigItem, ItemLike smallItem) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("/#/")
                .pattern("/#/")
                .define('#', bigItem)
                .define('/', smallItem)
                .unlockedBy("has_" + builder.name, has(bigItem));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_fence_gate"));
        } else {
            recipe.save(consumer, loc(builder.name + "_fence_gate"));
        }
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, Supplier<? extends OrnamentSlab> ingredient) {
        door(consumer, result, ingredient.get());
    }

    public void door(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentDoor> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_door"));
        } else {
            recipe.save(consumer, loc(builder.name + "_door"));
        }
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, Supplier<? extends OrnamentSlab> ingredient) {
        pole(consumer, result, ingredient.get());
    }

    public void pole(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_pole"));
        } else {
            recipe.save(consumer, loc(builder.name + "_pole"));
        }
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, Supplier<? extends OrnamentSlab> ingredient) {
        beam(consumer, result, ingredient.get());
    }

    public void beam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentBeam> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_beam"));
        } else {
            recipe.save(consumer, loc(builder.name + "_beam"));
        }
    }

    public void convertPoleBeam(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentPole> pole, Supplier<? extends OrnamentBeam> beam) {
        OrnamentBuilder polebuilder = pole.get().getBuilder();
        OrnamentBuilder beambuilder = beam.get().getBuilder();

        ShapelessRecipeBuilder polerecipe = ShapelessRecipeBuilder.shapeless(beam.get())
                .requires(pole.get())
                .unlockedBy("has_" + polebuilder.name, has(pole.get()));
        ShapelessRecipeBuilder beamrecipe = ShapelessRecipeBuilder.shapeless(pole.get())
                .requires(beam.get())
                .unlockedBy("has_" + beambuilder.name, has(beam.get()));

        if (polebuilder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(polebuilder.booleanValue.get().getPath().get(0)))
                    .addRecipe(polerecipe::save)
                    .build(consumer, loc(polebuilder.name + "_pole_to_beam"));
        } else {
            polerecipe.save(consumer, loc(polebuilder.name + "_pole_to_beam"));
        }

        if (beambuilder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(beambuilder.booleanValue.get().getPath().get(0)))
                    .addRecipe(beamrecipe::save)
                    .build(consumer, loc(beambuilder.name + "_beam_to_pole"));
        } else {
            beamrecipe.save(consumer, loc(beambuilder.name + "_beam_to_pole"));
        }
    }

    public void wall(Consumer<FinishedRecipe> consumer, Supplier<? extends OrnamentWall> result, ItemLike ingredient) {
        OrnamentBuilder builder = result.get().getBuilder();

        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + builder.name, has(ingredient));

        if (builder.hasConfig) {
            ConditionalRecipe.builder()
                    .addCondition(new ConfigCondition(builder.booleanValue.get().getPath().get(0)))
                    .addRecipe(recipe::save)
                    .build(consumer, loc(builder.name + "_wall"));
        } else {
            recipe.save(consumer, loc(builder.name + "_wall"));
        }
    }
}
