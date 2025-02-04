package com.temporal.api.core.event.data.recipe.holder;

public interface ShovelShapedRecipeHolder extends ShapedRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "X",
                "S",
                "S"
        };
    }
}
