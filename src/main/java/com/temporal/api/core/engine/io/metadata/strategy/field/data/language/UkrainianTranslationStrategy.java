package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.UkrainianTranslation;
import com.temporal.api.core.event.data.language.UkrainianProvider;

import java.lang.reflect.Field;

public class UkrainianTranslationStrategy extends TranslationStrategy {
    public UkrainianTranslationStrategy() {
        super(UkrainianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(UkrainianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            UkrainianTranslation translation = field.getDeclaredAnnotation(UkrainianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
