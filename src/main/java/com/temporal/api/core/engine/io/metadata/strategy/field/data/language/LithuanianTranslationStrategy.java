package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.LithuanianTranslation;
import com.temporal.api.core.event.data.language.provider.LithuanianProvider;

import java.lang.reflect.Field;

public class LithuanianTranslationStrategy extends TranslationStrategy {
    public LithuanianTranslationStrategy() {
        super(LithuanianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(LithuanianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            LithuanianTranslation translation = field.getDeclaredAnnotation(LithuanianTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
