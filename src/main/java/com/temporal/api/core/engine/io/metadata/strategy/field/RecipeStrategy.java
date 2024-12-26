package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.Recipe;
import com.temporal.api.core.event.data.recipe.ApiRecipeProvider;
import com.temporal.api.core.event.data.recipe.holder.RecipeHolder;

import java.lang.reflect.Field;

public class RecipeStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Recipe.class)) {
            field.setAccessible(true);
            Recipe recipe = field.getDeclaredAnnotation(Recipe.class);
            for (Class<? extends RecipeHolder> clazz : recipe.value())
                ApiRecipeProvider.RECIPES.add(clazz.getConstructor().newInstance());
        }
    }
}
