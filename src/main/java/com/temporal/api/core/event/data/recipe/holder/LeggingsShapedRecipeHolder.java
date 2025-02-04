package com.temporal.api.core.event.data.recipe.holder;

public interface LeggingsShapedRecipeHolder extends ShapedOneMaterialRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "XXX",
                "X X",
                "X X"
        };
    }
}
