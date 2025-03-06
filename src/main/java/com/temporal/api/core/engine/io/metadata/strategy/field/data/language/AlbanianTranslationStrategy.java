package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.AlbanianTranslation;
import com.temporal.api.core.event.data.language.AlbanianProvider;

import java.lang.reflect.Field;

public class AlbanianTranslationStrategy extends TranslationStrategy {
    public AlbanianTranslationStrategy() {
        super(AlbanianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(AlbanianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            AlbanianTranslation translation = field.getDeclaredAnnotation(AlbanianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
