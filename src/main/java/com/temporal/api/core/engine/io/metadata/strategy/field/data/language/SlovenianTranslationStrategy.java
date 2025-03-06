package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.SlovenianTranslation;
import com.temporal.api.core.event.data.language.SlovenianProvider;

import java.lang.reflect.Field;

public class SlovenianTranslationStrategy extends TranslationStrategy {
    public SlovenianTranslationStrategy() {
        super(SlovenianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SlovenianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            SlovenianTranslation translation = field.getDeclaredAnnotation(SlovenianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
