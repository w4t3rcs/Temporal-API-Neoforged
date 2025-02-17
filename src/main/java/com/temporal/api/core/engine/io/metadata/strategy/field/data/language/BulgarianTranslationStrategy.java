package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.BulgarianTranslation;
import com.temporal.api.core.event.data.language.BulgarianProvider;

import java.lang.reflect.Field;

public class BulgarianTranslationStrategy extends TranslationStrategy {
    public BulgarianTranslationStrategy() {
        super(BulgarianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BulgarianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            BulgarianTranslation translation = field.getDeclaredAnnotation(BulgarianTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
