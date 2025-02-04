package com.temporal.api.core.engine.io.metadata.annotation;

import java.lang.annotation.*;

@Repeatable(EnglishTranslations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnglishTranslation {
    TranslationType type() default TranslationType.OTHER;
    String id() default "";
    String value();
}