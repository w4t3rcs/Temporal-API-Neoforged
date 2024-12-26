package com.temporal.api.core.event.data.recipe.holder;

public interface CampfireCookingRecipeHolder extends CookingRecipeHolder {
    @Override
    default String getRecipeName() {
        return "_from_campfire_cooking";
    }

    @Override
    default int getCount() {
        return 1;
    }
}
