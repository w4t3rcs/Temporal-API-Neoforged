package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SerbianTranslation;
import com.temporal.api.core.event.data.language.SerbianProvider;

import java.lang.reflect.Field;

public class SerbianTranslationStrategy extends TranslationStrategy {
    public SerbianTranslationStrategy() {
        super(SerbianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SerbianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SerbianTranslation translation = field.getDeclaredAnnotation(SerbianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
