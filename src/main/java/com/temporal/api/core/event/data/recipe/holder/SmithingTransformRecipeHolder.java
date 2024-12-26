package com.temporal.api.core.event.data.recipe.holder;

import net.minecraft.world.level.ItemLike;

public interface SmithingTransformRecipeHolder extends RecipeHolder {
    ItemLike getTemplate();

    ItemLike getBase();

    ItemLike getAddition();
}
