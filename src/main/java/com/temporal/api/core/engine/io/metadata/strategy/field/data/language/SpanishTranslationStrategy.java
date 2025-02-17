package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SpanishTranslation;
import com.temporal.api.core.event.data.language.SpanishProvider;

import java.lang.reflect.Field;

public class SpanishTranslationStrategy extends TranslationStrategy {
    public SpanishTranslationStrategy() {
        super(SpanishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SpanishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SpanishTranslation translation = field.getDeclaredAnnotation(SpanishTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
