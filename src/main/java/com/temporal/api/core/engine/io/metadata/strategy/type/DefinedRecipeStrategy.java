package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.annotation.DefinedRecipe;
import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.RecipeHolder;

import java.lang.reflect.Constructor;

public class DefinedRecipeStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(DefinedRecipe.class)) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            RecipeHolder recipeHolder = (RecipeHolder) constructor.newInstance();
            ApiRecipeProvider.RECIPES.add(recipeHolder);
        }
    }
}
