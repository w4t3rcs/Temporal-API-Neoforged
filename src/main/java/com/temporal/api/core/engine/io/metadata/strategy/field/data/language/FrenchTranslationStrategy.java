package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.FrenchTranslation;
import com.temporal.api.core.event.data.language.provider.FrenchProvider;

import java.lang.reflect.Field;

public class FrenchTranslationStrategy extends TranslationStrategy {
    public FrenchTranslationStrategy() {
        super(FrenchProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(FrenchTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            FrenchTranslation translation = field.getDeclaredAnnotation(FrenchTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
