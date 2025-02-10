package com.temporal.api.core.engine.io.metadata.annotation;

import net.minecraft.tags.ItemTags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemTagComponent {
    String tag() default "";
    Class<?> tagContainer() default ItemTags.class;
}