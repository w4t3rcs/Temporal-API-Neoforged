package com.temporal.api.core.engine.io.metadata.annotation.data.language;

import com.temporal.api.core.engine.io.metadata.constant.TranslationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FinnishTranslation {
    TranslationType type() default TranslationType.OTHER;
    String id() default "";
    String value();
}