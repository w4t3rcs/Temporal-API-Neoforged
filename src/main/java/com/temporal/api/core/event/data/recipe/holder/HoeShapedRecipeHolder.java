package com.temporal.api.core.event.data.recipe.holder;

public interface HoeShapedRecipeHolder extends ShapedRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "XX",
                "S",
                "S"
        };
    }
}
