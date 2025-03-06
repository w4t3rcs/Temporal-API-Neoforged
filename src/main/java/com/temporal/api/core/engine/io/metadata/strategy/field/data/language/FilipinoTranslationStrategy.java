package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.FilipinoTranslation;
import com.temporal.api.core.event.data.language.FilipinoProvider;

import java.lang.reflect.Field;

public class FilipinoTranslationStrategy extends TranslationStrategy {
    public FilipinoTranslationStrategy() {
        super(FilipinoProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(FilipinoTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            FilipinoTranslation translation = field.getDeclaredAnnotation(FilipinoTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
