package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.ItalianTranslation;
import com.temporal.api.core.event.data.language.ItalianProvider;

import java.lang.reflect.Field;

public class ItalianTranslationStrategy extends TranslationStrategy {
    public ItalianTranslationStrategy() {
        super(ItalianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ItalianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            ItalianTranslation translation = field.getDeclaredAnnotation(ItalianTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
