package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.Oxidizable;
import com.temporal.api.core.event.data.map.ApiDataMapProvider;
import com.temporal.api.core.event.data.map.OxidizableDto;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class OxidizableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Oxidizable.class)) {
            field.setAccessible(true);
            Oxidizable oxidizable = field.getAnnotation(Oxidizable.class);
            DeferredBlock<?> block = (DeferredBlock<?>) field.get(object);
            OxidizableDto oxidizableDto = new OxidizableDto(block, oxidizable.nextBlockStageId(), oxidizable.replace());
            ApiDataMapProvider.OXIDIZABLES.add(oxidizableDto);
        }
    }
}
