package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.MethodExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.method.ExecutionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;

import java.util.List;

public class MethodAnnotationProcessor implements AnnotationProcessor<MethodAnnotationStrategy> {
    private final AnnotationExecutor<MethodAnnotationStrategy> executor = new MethodExecutor();
    private final List<MethodAnnotationStrategy> strategies = List.of(
            new ExecutionStrategy()
    );

    @Override
    public AnnotationExecutor<MethodAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<MethodAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
