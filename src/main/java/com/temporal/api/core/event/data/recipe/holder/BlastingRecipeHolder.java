package com.temporal.api.core.event.data.recipe.holder;

public interface BlastingRecipeHolder extends CookingRecipeHolder {
    @Override
    default String getRecipeName() {
        return "_from_blasting";
    }

    @Override
    default int getCount() {
        return 1;
    }
}
