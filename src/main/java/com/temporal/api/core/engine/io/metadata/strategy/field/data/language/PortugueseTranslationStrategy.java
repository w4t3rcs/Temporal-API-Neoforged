package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.PortugueseTranslation;
import com.temporal.api.core.event.data.language.provider.PortugueseProvider;

import java.lang.reflect.Field;

public class PortugueseTranslationStrategy extends TranslationStrategy {
    public PortugueseTranslationStrategy() {
        super(PortugueseProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(PortugueseTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            PortugueseTranslation translation = field.getDeclaredAnnotation(PortugueseTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
