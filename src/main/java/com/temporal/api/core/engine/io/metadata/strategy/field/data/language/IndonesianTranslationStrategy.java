package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.IndonesianTranslation;
import com.temporal.api.core.event.data.language.IndonesianProvider;

import java.lang.reflect.Field;

public class IndonesianTranslationStrategy extends TranslationStrategy {
    public IndonesianTranslationStrategy() {
        super(IndonesianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(IndonesianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            IndonesianTranslation translation = field.getDeclaredAnnotation(IndonesianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
