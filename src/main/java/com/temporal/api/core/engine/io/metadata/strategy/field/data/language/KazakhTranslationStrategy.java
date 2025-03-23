package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.KazakhTranslation;
import com.temporal.api.core.event.data.language.provider.KazakhProvider;

import java.lang.reflect.Field;

public class KazakhTranslationStrategy extends TranslationStrategy {
    public KazakhTranslationStrategy() {
        super(KazakhProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(KazakhTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            KazakhTranslation translation = field.getDeclaredAnnotation(KazakhTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
