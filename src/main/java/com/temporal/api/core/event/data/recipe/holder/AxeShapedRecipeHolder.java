package com.temporal.api.core.event.data.recipe.holder;

public interface AxeShapedRecipeHolder extends ShapedRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "XX",
                "SX",
                "S"
        };
    }
}
