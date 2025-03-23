package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.AustrianGermanTranslation;
import com.temporal.api.core.event.data.language.provider.AustrianGermanProvider;

import java.lang.reflect.Field;

public class AustrianGermanTranslationStrategy extends TranslationStrategy {
    public AustrianGermanTranslationStrategy() {
        super(AustrianGermanProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(AustrianGermanTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            AustrianGermanTranslation translation = field.getDeclaredAnnotation(AustrianGermanTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
