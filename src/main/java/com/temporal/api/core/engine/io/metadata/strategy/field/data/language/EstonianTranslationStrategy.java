package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.EstonianTranslation;
import com.temporal.api.core.event.data.language.EstonianProvider;

import java.lang.reflect.Field;

public class EstonianTranslationStrategy extends TranslationStrategy {
    public EstonianTranslationStrategy() {
        super(EstonianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(EstonianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            EstonianTranslation translation = field.getDeclaredAnnotation(EstonianTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
