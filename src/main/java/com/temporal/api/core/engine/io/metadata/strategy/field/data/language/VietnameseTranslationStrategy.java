package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.VietnameseTranslation;
import com.temporal.api.core.event.data.language.VietnameseProvider;

import java.lang.reflect.Field;

public class VietnameseTranslationStrategy extends TranslationStrategy {
    public VietnameseTranslationStrategy() {
        super(VietnameseProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(VietnameseTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            VietnameseTranslation translation = field.getDeclaredAnnotation(VietnameseTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
