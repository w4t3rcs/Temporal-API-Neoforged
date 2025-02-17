package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.CroatianTranslation;
import com.temporal.api.core.event.data.language.CroatianProvider;

import java.lang.reflect.Field;

public class CroatianTranslationStrategy extends TranslationStrategy {
    public CroatianTranslationStrategy() {
        super(CroatianProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CroatianTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            CroatianTranslation translation = field.getDeclaredAnnotation(CroatianTranslation.class);
            putTranslation(translation.type(), translation.id(), translation.value(), o);
        }
    }
}
