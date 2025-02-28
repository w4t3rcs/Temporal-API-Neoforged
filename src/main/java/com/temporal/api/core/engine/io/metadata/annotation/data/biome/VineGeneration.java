package com.temporal.api.core.engine.io.metadata.annotation.data.biome;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VineGeneration {
    int count() default 127;
    int from() default 54;
    int to() default 100;
    String biomeTag() default "is_overworld";
    Class<?> biomeTagContainer() default Object.class;
}
