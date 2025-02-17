package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.TurkishTranslation;
import com.temporal.api.core.event.data.language.TurkishProvider;

import java.lang.reflect.Field;

public class TurkishTranslationStrategy extends TranslationStrategy {
    public TurkishTranslationStrategy() {
        super(TurkishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(TurkishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            TurkishTranslation translation = field.getDeclaredAnnotation(TurkishTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
