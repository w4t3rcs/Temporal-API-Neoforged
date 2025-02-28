package com.temporal.api.core.engine.io.metadata.strategy.field.data.biome;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.biome.VineGeneration;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.biome.dto.Vine;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class VineGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(VineGeneration.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            VineGeneration vineGeneration = field.getDeclaredAnnotation(VineGeneration.class);
            Class<?> tagContainer = vineGeneration.biomeTagContainer();
            if (!tagContainer.equals(Object.class)) BiomeTagDynamicPreparer.TAG_CONTAINERS.add(tagContainer);
            var placement = new Vine.Placement(vineGeneration.count(), vineGeneration.from(), vineGeneration.to());
            var biomeModifier = new Vine.BiomeModifier(vineGeneration.biomeTag());
            Vine vine = new Vine(IOHelper.getResourceId(registryObject.getKey()), placement, biomeModifier);
            GenerationFeaturesDescriptionContainer.VINES.put(registryObject, vine);
        }
    }
}
