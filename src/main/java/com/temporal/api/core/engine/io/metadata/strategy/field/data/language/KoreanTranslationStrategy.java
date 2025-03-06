package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.annotation.data.language.KoreanTranslation;
import com.temporal.api.core.event.data.language.KoreanProvider;

import java.lang.reflect.Field;

public class KoreanTranslationStrategy extends TranslationStrategy {
    public KoreanTranslationStrategy() {
        super(KoreanProvider.class);
    }

    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(KoreanTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            KoreanTranslation translation = field.getDeclaredAnnotation(KoreanTranslation.class);
            putDynamicTranslation(translation.id(), translation.value(), o);
        }
    }
}
