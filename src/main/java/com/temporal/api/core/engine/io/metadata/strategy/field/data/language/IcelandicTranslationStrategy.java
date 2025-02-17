package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.IcelandicTranslation;
import com.temporal.api.core.event.data.language.IcelandicProvider;

import java.lang.reflect.Field;

public class IcelandicTranslationStrategy extends TranslationStrategy {
    public IcelandicTranslationStrategy() {
        super(IcelandicProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(IcelandicTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            IcelandicTranslation translation = field.getDeclaredAnnotation(IcelandicTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
