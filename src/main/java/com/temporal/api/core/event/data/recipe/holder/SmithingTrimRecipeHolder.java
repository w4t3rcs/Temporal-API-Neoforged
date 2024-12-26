package com.temporal.api.core.event.data.recipe.holder;

import net.minecraft.world.level.ItemLike;

public interface SmithingTrimRecipeHolder extends SmithingTransformRecipeHolder {
    @Override
    default ItemLike getResult() {
        throw new RuntimeException();
    }
}
