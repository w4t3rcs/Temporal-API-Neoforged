package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.DutchTranslation;
import com.temporal.api.core.event.data.language.provider.DutchProvider;

import java.lang.reflect.Field;

public class DutchTranslationStrategy extends TranslationStrategy {
    public DutchTranslationStrategy() {
        super(DutchProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(DutchTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            DutchTranslation translation = field.getDeclaredAnnotation(DutchTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
