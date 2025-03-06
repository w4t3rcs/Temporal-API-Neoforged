package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.IrishTranslation;
import com.temporal.api.core.event.data.language.IrishProvider;

import java.lang.reflect.Field;

public class IrishTranslationStrategy extends TranslationStrategy {
    public IrishTranslationStrategy() {
        super(IrishProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(IrishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            IrishTranslation translation = field.getDeclaredAnnotation(IrishTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
