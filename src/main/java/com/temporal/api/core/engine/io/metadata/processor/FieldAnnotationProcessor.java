package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.FieldExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.DependencyStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.InjectionStrategy;

import java.util.List;

public class FieldAnnotationProcessor implements AnnotationProcessor<FieldAnnotationStrategy> {
    private final AnnotationExecutor<FieldAnnotationStrategy> executor = new FieldExecutor();
    private final List<FieldAnnotationStrategy> strategies = List.of(
            new InjectionStrategy(),
            new DependencyStrategy()
    );

    @Override
    public AnnotationExecutor<FieldAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<FieldAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
