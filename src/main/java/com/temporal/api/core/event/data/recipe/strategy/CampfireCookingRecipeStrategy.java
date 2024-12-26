package com.temporal.api.core.event.data.recipe.strategy;

import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.CampfireCookingRecipeHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class CampfireCookingRecipeStrategy implements RecipeStrategy<CampfireCookingRecipeHolder> {
    @Override
    public void saveRecipe(CampfireCookingRecipeHolder recipeHolder, ApiRecipeProvider recipeProvider, @NotNull RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(recipeHolder.getIngredient()), recipeHolder.getRecipeCategory(), recipeHolder.getResult(),
                        recipeHolder.getExperience(), recipeHolder.getCookingTime())
                .group(recipeHolder.getGroup())
                .unlockedBy(ApiRecipeProvider.getHasName(recipeHolder.getIngredient()), recipeProvider.has(recipeHolder.getIngredient()))
                .save(recipeOutput, IOLayer.FORGE_MOD.getModId() + ":" + ApiRecipeProvider.getItemName(recipeHolder.getResult()) + recipeHolder.getRecipeName() + "_" + ApiRecipeProvider.getItemName(recipeHolder.getIngredient()));
    }
}
