package com.temporal.api.core.event.data.recipe.holder;

public interface PickaxeShapedRecipeHolder extends ShapedRecipeHolder {
    @Override
    default String[] getPattern() {
        return new String[]{
                "XXX",
                " S ",
                " S "
        };
    }
}
