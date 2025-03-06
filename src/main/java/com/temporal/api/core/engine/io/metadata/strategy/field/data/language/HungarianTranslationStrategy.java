package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.HungarianTranslation;
import com.temporal.api.core.event.data.language.HungarianProvider;

import java.lang.reflect.Field;

public class HungarianTranslationStrategy extends TranslationStrategy {
    public HungarianTranslationStrategy() {
        super(HungarianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(HungarianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            HungarianTranslation translation = field.getDeclaredAnnotation(HungarianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
