package com.temporal.api.core.event.data.recipe.holder;

public interface SmeltingRecipeHolder extends CookingRecipeHolder {
    @Override
    default String getRecipeName() {
        return "_from_smelting";
    }

    @Override
    default int getCount() {
        return 1;
    }
}
