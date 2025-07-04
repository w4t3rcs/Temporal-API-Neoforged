package com.temporal.api.core.engine.io.metadata.annotation.data.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Painting {
    int width() default 1;

    int height() default 1;

    boolean hasAuthor() default true;
}