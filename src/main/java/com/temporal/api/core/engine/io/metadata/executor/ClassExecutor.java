package com.temporal.api.core.engine.io.metadata.executor;

import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;

public class ClassExecutor implements AnnotationExecutor<ClassAnnotationStrategy> {
    @Override
    public void execute(ClassAnnotationStrategy strategy, Class<?> clazz) throws Exception {
        try {
            strategy.execute(clazz, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
