package com.temporal.api.core.event.data.recipe.strategy;

import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.SmithingTransformRecipeHolder;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SmithingTransformRecipeStrategy implements RecipeStrategy<SmithingTransformRecipeHolder> {
    @Override
    public void saveRecipe(SmithingTransformRecipeHolder recipeHolder, ApiRecipeProvider recipeProvider, @NotNull RecipeOutput recipeOutput) {
        String path = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(recipeHolder.getResult().asItem())).getPath();
        ResourceKey<Recipe<?>> resourceKey = ResourceKey.create(Registries.RECIPE, ResourceUtils.createResourceLocation(path));
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(recipeHolder.getTemplate()), Ingredient.of(recipeHolder.getBase()), Ingredient.of(recipeHolder.getAddition()), recipeHolder.getRecipeCategory(), recipeHolder.getResult().asItem())
                .unlocks(ApiRecipeProvider.getHasName(recipeHolder.getTemplate()), recipeProvider.has(recipeHolder.getTemplate()))
                .save(recipeOutput, resourceKey);
    }
}
