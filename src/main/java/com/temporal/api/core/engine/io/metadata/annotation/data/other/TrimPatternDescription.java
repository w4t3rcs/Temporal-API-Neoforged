package com.temporal.api.core.engine.io.metadata.annotation.data.other;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TrimPatternDescription {
    String itemId();
    boolean decal() default false;
}