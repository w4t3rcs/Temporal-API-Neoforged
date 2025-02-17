package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SwissGermanTranslation;
import com.temporal.api.core.event.data.language.SwissGermanProvider;

import java.lang.reflect.Field;

public class SwissGermanTranslationStrategy extends TranslationStrategy {
    public SwissGermanTranslationStrategy() {
        super(SwissGermanProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SwissGermanTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SwissGermanTranslation translation = field.getDeclaredAnnotation(SwissGermanTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
