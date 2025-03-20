package com.temporal.api.core.event.data.recipe;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.event.data.recipe.holder.*;
import com.temporal.api.core.event.data.recipe.strategy.*;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class ApiRecipeProvider extends RecipeProvider {
    public static final Queue<RecipeHolder> RECIPES = new TemporalArrayDeque<>();
    private static final RecipeStrategy<ShapelessRecipeHolder> SHAPELESS_RECIPE_STRATEGY = new ShapelessRecipeStrategy();
    private static final RecipeStrategy<ShapedRecipeHolder> SHAPED_RECIPE_STRATEGY = new ShapedRecipeStrategy();
    private static final RecipeStrategy<BlastingRecipeHolder> BLASTING_RECIPE_STRATEGY = new BlastingRecipeStrategy();
    private static final RecipeStrategy<SmeltingRecipeHolder> SMELTING_RECIPE_STRATEGY = new SmeltingRecipeStrategy();
    private static final RecipeStrategy<SmokingRecipeHolder> SMOKING_RECIPE_STRATEGY = new SmokingRecipeStrategy();
    private static final RecipeStrategy<CampfireCookingRecipeHolder> CAMPFIRE_COOKING_RECIPE_STRATEGY = new CampfireCookingRecipeStrategy();
    private static final RecipeStrategy<SmithingTrimRecipeHolder> SMITHING_TRIM_RECIPE_STRATEGY = new SmithingTrimRecipeStrategy();
    private static final RecipeStrategy<SmithingTransformRecipeHolder> SMITHING_TRANSFORM_RECIPE_STRATEGY = new SmithingTransformRecipeStrategy();
    private static final RecipeStrategy<StoneCuttingRecipeHolder> STONE_CUTTING_RECIPE_STRATEGY_RECIPE_STRATEGY = new StoneCuttingRecipeStrategy();

    public ApiRecipeProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
        super(registries, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        RECIPES.forEach(undefinedRecipe -> {
            switch (undefinedRecipe) {
                case ShapelessRecipeHolder recipe -> SHAPELESS_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case ShapedRecipeHolder recipe -> SHAPED_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case BlastingRecipeHolder recipe -> BLASTING_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case SmeltingRecipeHolder recipe -> SMELTING_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case SmokingRecipeHolder recipe -> SMOKING_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case CampfireCookingRecipeHolder recipe -> CAMPFIRE_COOKING_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case SmithingTrimRecipeHolder recipe -> SMITHING_TRIM_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case SmithingTransformRecipeHolder recipe -> SMITHING_TRANSFORM_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case StoneCuttingRecipeHolder recipe -> STONE_CUTTING_RECIPE_STRATEGY_RECIPE_STRATEGY.saveRecipe(recipe, this, output);
                case null, default -> {}
            }
        });
    }

    public static String getHasName(ItemLike itemLike) {
        return "has_" + getItemName(itemLike);
    }

    public static @NotNull String getItemName(ItemLike itemLike) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemLike.asItem())).getPath();
    }

    @Override
    public @NotNull Criterion<InventoryChangeTrigger.TriggerInstance> has(@NotNull ItemLike itemLike) {
        HolderLookup.RegistryLookup<Item> lookup = registries.lookupOrThrow(Registries.ITEM);
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(lookup, new ItemLike[]{itemLike})
                .build());
    }

    @Override
    @NotNull
    public Ingredient tag(@NotNull TagKey<Item> tagKey) {
        return super.tag(tagKey);
    }

    public HolderLookup.Provider getRegistries() {
        return registries;
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
            super(pPackOutput, pRegistries);

        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ApiRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return ApiRecipeProvider.class.getSimpleName();
        }
    }
}
