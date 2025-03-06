package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SlovakTranslation;
import com.temporal.api.core.event.data.language.SlovakProvider;

import java.lang.reflect.Field;

public class SlovakTranslationStrategy extends TranslationStrategy {
    public SlovakTranslationStrategy() {
        super(SlovakProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SlovakTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SlovakTranslation translation = field.getDeclaredAnnotation(SlovakTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
