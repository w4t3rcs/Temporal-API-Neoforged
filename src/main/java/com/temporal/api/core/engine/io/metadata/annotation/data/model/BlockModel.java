package com.temporal.api.core.engine.io.metadata.annotation.data.model;

import com.temporal.api.core.engine.io.metadata.constant.BlockModelType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlockModel {
    BlockModelType value() default BlockModelType.CUBED;
}