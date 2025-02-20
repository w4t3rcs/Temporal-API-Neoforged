package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.other.OreGeneration;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.biome.GenerationFeaturesDescriptionContainer;
import com.temporal.api.core.event.data.biome.dto.Ore;
import com.temporal.api.core.event.data.preparer.tag.biome.BiomeTagDynamicPreparer;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class OreGenerationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(OreGeneration.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            OreGeneration oreGeneration = field.getDeclaredAnnotation(OreGeneration.class);
            Class<?> tagContainer = oreGeneration.biomeTagContainer();
            if (!tagContainer.equals(Object.class)) BiomeTagDynamicPreparer.TAG_CONTAINERS.add(tagContainer);
            Ore.Configuration configuration = new Ore.Configuration(oreGeneration.replaceableBlocks(), oreGeneration.size());
            Ore.Placement placement = new Ore.Placement(oreGeneration.rarity(), oreGeneration.count(), oreGeneration.shape(), oreGeneration.from(), oreGeneration.to());
            Ore.BiomeModifier biomeModifier = new Ore.BiomeModifier(oreGeneration.biomeTag());
            Ore ore = new Ore(IOHelper.getResourceId(registryObject.getKey()), configuration, placement, biomeModifier);
            GenerationFeaturesDescriptionContainer.ORES.put(registryObject, ore);
        }
    }
}
