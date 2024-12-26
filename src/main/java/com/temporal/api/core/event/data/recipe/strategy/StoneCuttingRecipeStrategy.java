package com.temporal.api.core.event.data.recipe.strategy;

import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.StoneCuttingRecipeHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class StoneCuttingRecipeStrategy implements RecipeStrategy<StoneCuttingRecipeHolder> {
    @Override
    public void saveRecipe(StoneCuttingRecipeHolder recipeHolder, ApiRecipeProvider recipeProvider, @NotNull RecipeOutput recipeOutput) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(recipeHolder.getIngredient()), recipeHolder.getRecipeCategory(), recipeHolder.getResult(), recipeHolder.getCount())
                .save(recipeOutput);
    }
}
