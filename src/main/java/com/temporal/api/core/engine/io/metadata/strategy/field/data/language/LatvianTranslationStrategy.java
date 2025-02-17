package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.LatvianTranslation;
import com.temporal.api.core.event.data.language.LatvianProvider;

import java.lang.reflect.Field;

public class LatvianTranslationStrategy extends TranslationStrategy {
    public LatvianTranslationStrategy() {
        super(LatvianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(LatvianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            LatvianTranslation translation = field.getDeclaredAnnotation(LatvianTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
