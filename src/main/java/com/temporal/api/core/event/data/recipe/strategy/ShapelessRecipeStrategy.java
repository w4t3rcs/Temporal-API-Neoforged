package com.temporal.api.core.event.data.recipe.strategy;

import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.ShapelessRecipeHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import org.jetbrains.annotations.NotNull;

public class ShapelessRecipeStrategy implements RecipeStrategy<ShapelessRecipeHolder> {
    @Override
    public void saveRecipe(ShapelessRecipeHolder recipeHolder, ApiRecipeProvider recipeProvider, @NotNull RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(recipeProvider.getItems(), recipeHolder.getRecipeCategory(), recipeHolder.getResult(), recipeHolder.getCount());
        for (var entry : recipeHolder.getItemAndCountMap().entrySet()) builder = builder.requires(entry.getKey(), entry.getValue());
        builder.save(recipeOutput);
    }
}
