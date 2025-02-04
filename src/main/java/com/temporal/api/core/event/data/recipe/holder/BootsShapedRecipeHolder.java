package com.temporal.api.core.event.data.recipe.holder;

public interface BootsShapedRecipeHolder extends ShapedOneMaterialRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "X X",
                "X X"
        };
    }
}
