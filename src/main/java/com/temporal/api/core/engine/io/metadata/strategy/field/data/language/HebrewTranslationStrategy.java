package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.HebrewTranslation;
import com.temporal.api.core.event.data.language.HebrewProvider;

import java.lang.reflect.Field;

public class HebrewTranslationStrategy extends TranslationStrategy {
    public HebrewTranslationStrategy() {
        super(HebrewProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(HebrewTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            HebrewTranslation translation = field.getDeclaredAnnotation(HebrewTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
