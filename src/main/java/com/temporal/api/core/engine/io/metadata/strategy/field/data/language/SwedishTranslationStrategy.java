package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SwedishTranslation;
import com.temporal.api.core.event.data.language.SwedishProvider;

import java.lang.reflect.Field;

public class SwedishTranslationStrategy extends TranslationStrategy {
    public SwedishTranslationStrategy() {
        super(SwedishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SwedishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SwedishTranslation translation = field.getDeclaredAnnotation(SwedishTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
