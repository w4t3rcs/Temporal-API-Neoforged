package com.temporal.api.core.event.data.recipe.holder;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.level.ItemLike;

public interface RecipeHolder {
    RecipeCategory getRecipeCategory();

    ItemLike getResult();

    int getCount();
}
