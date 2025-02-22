package com.temporal.api.core.engine.io.metadata.annotation.data.resource;

import com.temporal.api.core.engine.io.metadata.constant.ResourceContainerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ResourceContainer {
    ResourceContainerType value();
}