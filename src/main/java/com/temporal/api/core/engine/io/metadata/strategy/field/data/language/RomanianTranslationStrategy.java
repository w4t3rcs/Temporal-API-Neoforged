package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.RomanianTranslation;
import com.temporal.api.core.event.data.language.RomanianProvider;

import java.lang.reflect.Field;

public class RomanianTranslationStrategy extends TranslationStrategy {
    public RomanianTranslationStrategy() {
        super(RomanianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(RomanianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            RomanianTranslation translation = field.getDeclaredAnnotation(RomanianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
