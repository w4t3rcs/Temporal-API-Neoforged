package com.temporal.api.core.engine.io.metadata.annotation;

import net.minecraft.tags.BlockTags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlockTagComponent {
    String tag() default "";
    Class<?> tagContainer() default BlockTags.class;
}