package com.temporal.api.core.engine.io.metadata.annotation.data.model;

import com.temporal.api.core.engine.io.metadata.constant.ItemModelType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemModel {
    ItemModelType value() default ItemModelType.BASIC;

    String[] additionalStrings() default {};

    int[] additionalInts() default {};

    Class<?>[] additionalPOJOClasses() default {};
}