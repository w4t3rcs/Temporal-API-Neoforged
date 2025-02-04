package com.temporal.api.core.event.data.recipe.holder;

public interface ChestplateShapedRecipeHolder extends ShapedOneMaterialRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "X X",
                "XXX",
                "XXX"
        };
    }
}
