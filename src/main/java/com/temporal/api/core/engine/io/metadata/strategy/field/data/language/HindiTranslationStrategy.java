package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.HindiTranslation;
import com.temporal.api.core.event.data.language.HindiProvider;

import java.lang.reflect.Field;

public class HindiTranslationStrategy extends TranslationStrategy {
    public HindiTranslationStrategy() {
        super(HindiProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(HindiTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            HindiTranslation translation = field.getDeclaredAnnotation(HindiTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
