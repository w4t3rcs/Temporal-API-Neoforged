package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.engine.io.metadata.annotation.data.other.TrimMaterialDescription;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.trim.material.ApiTrimMaterialProvider;
import com.temporal.api.core.event.data.trim.material.TrimMaterialDescriptionHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.lang.reflect.Field;

public class TrimMaterialDescriptionStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(TrimMaterialDescription.class)) {
            field.setAccessible(true);
            TrimMaterialDescription annotation = field.getDeclaredAnnotation(TrimMaterialDescription.class);
            ResourceKey<TrimMaterial> trimMaterial = (ResourceKey<TrimMaterial>) field.get(object);
            TrimMaterialDescriptionHolder descriptionHolder = new TrimMaterialDescriptionHolder(annotation.itemId(), annotation.color(), annotation.itemModelIndex());
            ApiTrimMaterialProvider.TRIM_MATERIALS.put(trimMaterial, descriptionHolder);
        }
    }
}
