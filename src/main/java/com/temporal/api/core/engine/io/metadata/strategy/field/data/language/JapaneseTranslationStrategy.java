package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.JapaneseTranslation;
import com.temporal.api.core.event.data.language.JapaneseProvider;

import java.lang.reflect.Field;

public class JapaneseTranslationStrategy extends TranslationStrategy {
    public JapaneseTranslationStrategy() {
        super(JapaneseProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(JapaneseTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            JapaneseTranslation translation = field.getDeclaredAnnotation(JapaneseTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
