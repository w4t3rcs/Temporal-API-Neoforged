package com.temporal.api.core.engine.io.metadata.annotation.data.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Oxidizable {
    String nextBlockStageId();

    boolean replace() default false;
}