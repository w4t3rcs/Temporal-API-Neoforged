package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.DanishTranslation;
import com.temporal.api.core.event.data.language.DanishProvider;

import java.lang.reflect.Field;

public class DanishTranslationStrategy extends TranslationStrategy {
    public DanishTranslationStrategy() {
        super(DanishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(DanishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            DanishTranslation translation = field.getDeclaredAnnotation(DanishTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
