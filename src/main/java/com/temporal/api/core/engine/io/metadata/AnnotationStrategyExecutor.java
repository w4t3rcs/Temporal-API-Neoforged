package com.temporal.api.core.engine.io.metadata;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;

public interface AnnotationStrategyExecutor {
    void executeClass(ClassAnnotationStrategy strategy, Class<?> clazz);

    void executeStaticField(FieldAnnotationStrategy strategy, Class<?> clazz);

    void executeField(FieldAnnotationStrategy strategy, Class<?> clazz);

    void executeMethod(MethodAnnotationStrategy strategy, Class<?> clazz);
}
