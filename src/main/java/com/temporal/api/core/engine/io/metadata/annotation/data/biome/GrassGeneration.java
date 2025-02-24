package com.temporal.api.core.engine.io.metadata.annotation.data.biome;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GrassGeneration {
    int tries() default 32;
    int count() default 5;
    String[] biomeTags() default "is_overworld";
    Class<?> biomeTagContainer() default Object.class;
}
