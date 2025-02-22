package com.temporal.api.core.engine.io.metadata.strategy.type.data.resource;

import com.temporal.api.core.engine.io.metadata.annotation.data.resource.ResourceContainer;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.event.data.preparer.resource.equipment.EquipmentResourceDynamicPreparer;

public class ResourceContainerStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(ResourceContainer.class)) {
            ResourceContainer annotation = clazz.getDeclaredAnnotation(ResourceContainer.class);
            switch (annotation.value()) {
                case EQUIPMENT_ASSET -> EquipmentResourceDynamicPreparer.RESOURCE_CONTAINERS.add(clazz);
            }
        }
    }
}
