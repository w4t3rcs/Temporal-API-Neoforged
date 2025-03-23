package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.PersianTranslation;
import com.temporal.api.core.event.data.language.provider.PersianProvider;

import java.lang.reflect.Field;

public class PersianTranslationStrategy extends TranslationStrategy {
    public PersianTranslationStrategy() {
        super(PersianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(PersianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            PersianTranslation translation = field.getDeclaredAnnotation(PersianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
