package com.temporal.api.core.engine.io.metadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlockModel {
    enum Type {
        CUBED,
        BUTTON,
        DOOR,
        FENCE,
        FENCE_GATE,
        PRESSURE_PLATE,
        SLAB,
        STAIRS,
        TRAPDOOR,
        WALL,
        LOG,
        ROTATED_PILLAR
    }

    Type type() default Type.CUBED;
}