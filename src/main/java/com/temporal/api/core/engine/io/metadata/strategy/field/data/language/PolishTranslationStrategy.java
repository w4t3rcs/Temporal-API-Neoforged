package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.PolishTranslation;
import com.temporal.api.core.event.data.language.PolishProvider;

import java.lang.reflect.Field;

public class PolishTranslationStrategy extends TranslationStrategy {
    public PolishTranslationStrategy() {
        super(PolishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(PolishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            PolishTranslation translation = field.getDeclaredAnnotation(PolishTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
