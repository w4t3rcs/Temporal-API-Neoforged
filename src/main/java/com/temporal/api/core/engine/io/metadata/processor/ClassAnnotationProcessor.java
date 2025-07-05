package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.ClassExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.injection.InjectedStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.injection.RegistryClassStrategy;

import java.util.List;

public class ClassAnnotationProcessor implements AnnotationProcessor<ClassAnnotationStrategy> {
    private final AnnotationExecutor<ClassAnnotationStrategy> executor = new ClassExecutor();
    private final List<ClassAnnotationStrategy> strategies = List.of(
            new InjectedStrategy(),
            new RegistryClassStrategy()
    );

    @Override
    public AnnotationExecutor<ClassAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<ClassAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
