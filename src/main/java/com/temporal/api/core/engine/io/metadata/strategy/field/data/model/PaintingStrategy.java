package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.metadata.annotation.data.model.Painting;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.painting.ApiPaintingVariantProvider;
import com.temporal.api.core.event.data.painting.PaintingHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.lang.reflect.Field;

public class PaintingStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Painting.class)) {
            field.setAccessible(true);
            ResourceKey<PaintingVariant> paintingVariant = (ResourceKey<PaintingVariant>) field.get(object);
            Painting annotation = field.getDeclaredAnnotation(Painting.class);
            PaintingHolder paintingHolder = new PaintingHolder(paintingVariant, annotation.width(), annotation.height());
            ApiPaintingVariantProvider.PAINTINGS.add(paintingHolder);
        }
    }
}
