package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.GermanTranslation;
import com.temporal.api.core.event.data.language.GermanProvider;

import java.lang.reflect.Field;

public class GermanTranslationStrategy extends TranslationStrategy {
    public GermanTranslationStrategy() {
        super(GermanProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(GermanTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            GermanTranslation translation = field.getDeclaredAnnotation(GermanTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
