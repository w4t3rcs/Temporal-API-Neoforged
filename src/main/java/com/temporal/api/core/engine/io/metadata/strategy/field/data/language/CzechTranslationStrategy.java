package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.CzechTranslation;
import com.temporal.api.core.event.data.language.CzechProvider;

import java.lang.reflect.Field;

public class CzechTranslationStrategy extends TranslationStrategy {
    public CzechTranslationStrategy() {
        super(CzechProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CzechTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            CzechTranslation translation = field.getDeclaredAnnotation(CzechTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
