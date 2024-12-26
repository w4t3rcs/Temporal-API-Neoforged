package com.temporal.api.core.engine.io.metadata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Injected {
    boolean value() default true;
    boolean isInjection() default true;
    @Deprecated(since = "1.6.5")
    boolean isContextObject() default true;
    String injectionOnModCondition() default "";
}
