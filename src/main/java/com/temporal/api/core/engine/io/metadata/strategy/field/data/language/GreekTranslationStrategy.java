package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.GreekTranslation;
import com.temporal.api.core.event.data.language.provider.GreekProvider;

import java.lang.reflect.Field;

public class GreekTranslationStrategy extends TranslationStrategy {
    public GreekTranslationStrategy() {
        super(GreekProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(GreekTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            GreekTranslation translation = field.getDeclaredAnnotation(GreekTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
