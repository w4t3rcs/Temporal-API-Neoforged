package com.temporal.api.core.engine.io.metadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlockLootTable {
    enum Type {
        SELF,
        SILK_TOUCH,
        POTTED_CONTENT,
        OTHER
    }

    Type type() default Type.SELF;
    String itemId() default "";
}