package com.temporal.api.core.event.data.language.transformer;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class PaintingTransformer implements KeyTransformer<ResourceKey<PaintingVariant>> {
    @Override
    public String transform(ResourceKey<PaintingVariant> paintingVariantResourceKey) {
        return this.transformResourceKey("painting", paintingVariantResourceKey);
    }
}
