package com.temporal.api.core.event.data.recipe.holder;

public interface HelmetShapedRecipeHolder extends ShapedOneMaterialRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "XXX",
                "X X"
        };
    }
}
