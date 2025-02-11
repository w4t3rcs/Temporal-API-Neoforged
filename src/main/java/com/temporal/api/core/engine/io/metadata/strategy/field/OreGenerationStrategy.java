package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.OreGeneration;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.tag.biome.BiomeTagGenerationPreparer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class OreGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(OreGeneration.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            OreGeneration generationDescription = field.getDeclaredAnnotation(OreGeneration.class);
            Class<?> tagContainer = generationDescription.biomeModifier().biomeTagContainer();
            if (tagContainer != null) {
                BiomeTagGenerationPreparer.TAG_CONTAINERS.add(tagContainer);
            }
            GenerationFeaturesDescriptionContainer.ORES.put(registryObject, generationDescription);
        }
    }
}
