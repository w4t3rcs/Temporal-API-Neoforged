package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.BosnianTranslation;
import com.temporal.api.core.event.data.language.BosnianProvider;

import java.lang.reflect.Field;

public class BosnianTranslationStrategy extends TranslationStrategy {
    public BosnianTranslationStrategy() {
        super(BosnianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BosnianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            BosnianTranslation translation = field.getDeclaredAnnotation(BosnianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
