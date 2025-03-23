package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.MandarinTranslation;
import com.temporal.api.core.event.data.language.provider.MandarinProvider;

import java.lang.reflect.Field;

public class MandarinTranslationStrategy extends TranslationStrategy {
    public MandarinTranslationStrategy() {
        super(MandarinProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(MandarinTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            MandarinTranslation translation = field.getDeclaredAnnotation(MandarinTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
