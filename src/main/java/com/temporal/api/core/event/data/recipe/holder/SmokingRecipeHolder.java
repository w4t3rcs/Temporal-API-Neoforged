package com.temporal.api.core.event.data.recipe.holder;

public interface SmokingRecipeHolder extends CookingRecipeHolder {
    @Override
    default String getRecipeName() {
        return "_from_smoking";
    }

    @Override
    default int getCount() {
        return 1;
    }
}
