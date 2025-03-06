package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.FinnishTranslation;
import com.temporal.api.core.event.data.language.FinnishProvider;

import java.lang.reflect.Field;

public class FinnishTranslationStrategy extends TranslationStrategy {
    public FinnishTranslationStrategy() {
        super(FinnishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(FinnishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            FinnishTranslation translation = field.getDeclaredAnnotation(FinnishTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
