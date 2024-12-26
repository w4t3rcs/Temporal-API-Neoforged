package com.temporal.api.core.event.data.recipe;

import com.temporal.api.core.event.data.recipe.holder.*;
import com.temporal.api.core.event.data.recipe.strategy.*;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ApiRecipeProvider extends RecipeProvider {
    public static final List<RecipeHolder> RECIPES = new ArrayList<>();
    private static final RecipeStrategy<ShapelessRecipeHolder> SHAPELESS_RECIPE_STRATEGY = new ShapelessRecipeStrategy();
    private static final RecipeStrategy<ShapedRecipeHolder> SHAPED_RECIPE_STRATEGY = new ShapedRecipeStrategy();
    private static final RecipeStrategy<BlastingRecipeHolder> BLASTING_RECIPE_STRATEGY = new BlastingRecipeStrategy();
    private static final RecipeStrategy<SmeltingRecipeHolder> SMELTING_RECIPE_STRATEGY = new SmeltingRecipeStrategy();
    private static final RecipeStrategy<SmokingRecipeHolder> SMOKING_RECIPE_STRATEGY = new SmokingRecipeStrategy();
    private static final RecipeStrategy<CampfireCookingRecipeHolder> CAMPFIRE_COOKING_RECIPE_STRATEGY = new CampfireCookingRecipeStrategy();
    private static final RecipeStrategy<SmithingTrimRecipeHolder> SMITHING_TRIM_RECIPE_STRATEGY = new SmithingTrimRecipeStrategy();
    private static final RecipeStrategy<SmithingTransformRecipeHolder> SMITHING_TRANSFORM_RECIPE_STRATEGY = new SmithingTransformRecipeStrategy();
    private static final RecipeStrategy<StoneCuttingRecipeHolder> STONE_CUTTING_RECIPE_STRATEGY_RECIPE_STRATEGY = new StoneCuttingRecipeStrategy();
    private final HolderGetter<Item> items;

    public ApiRecipeProvider(HolderLookup.Provider registries, RecipeOutput recipeOutput) {
        super(registries, recipeOutput);
        items = registries.lookupOrThrow(Registries.ITEM);
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
        if (items == null) throw new RuntimeException("No items found");
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(items, new ItemLike[]{itemLike})
                .build());
    }

    public HolderGetter<Item> getItems() {
        return items;
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
