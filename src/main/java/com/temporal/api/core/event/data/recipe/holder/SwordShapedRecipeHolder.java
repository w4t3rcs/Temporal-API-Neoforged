package com.temporal.api.core.event.data.recipe.holder;

public interface SwordShapedRecipeHolder extends ShapedRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "X",
                "X",
                "S"
        };
    }
}
