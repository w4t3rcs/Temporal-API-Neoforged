package com.temporal.api.core.engine.io.metadata.strategy.field.data.biome;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.biome.GrassGeneration;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.biome.dto.Grass;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class GrassGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(GrassGeneration.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            GrassGeneration grassGeneration = field.getDeclaredAnnotation(GrassGeneration.class);
            Class<?> tagContainer = grassGeneration.biomeTagContainer();
            if (!tagContainer.equals(Object.class)) BiomeTagDynamicPreparer.TAG_CONTAINERS.add(tagContainer);
            var configuration = new Grass.Configuration(grassGeneration.tries());
            var placement = new Grass.Placement(grassGeneration.count());
            var biomeModifier = new Grass.BiomeModifier(grassGeneration.biomeTag());
            Grass grass = new Grass(IOHelper.getResourceId(registryObject.getKey()), configuration, placement, biomeModifier);
            GenerationFeaturesDescriptionContainer.GRASSES.put(registryObject, grass);
        }
    }
}
