package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.Compostable;
import com.temporal.api.core.event.data.map.ApiDataMapProvider;
import com.temporal.api.core.event.data.map.CompostableDto;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;

public class CompostableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Compostable.class)) {
            field.setAccessible(true);
            Compostable compostable = field.getAnnotation(Compostable.class);
            CompostableDto compostableDto;
            Object o = field.get(object);
            if (o instanceof DeferredItem<?> item) {
                compostableDto = new CompostableDto(item, compostable.chance(), compostable.replace());
            } else if (o instanceof DeferredBlock<?> block) {
                compostableDto = new CompostableDto(Holder.direct(block.asItem()), compostable.chance(), compostable.replace());
            } else {
                throw new RuntimeException();
            }

            ApiDataMapProvider.COMPOSTABLES.add(compostableDto);
        }
    }
}
