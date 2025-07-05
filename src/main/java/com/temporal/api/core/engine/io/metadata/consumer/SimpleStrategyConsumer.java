package com.temporal.api.core.engine.io.metadata.consumer;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

import java.util.List;
import java.util.Set;

public class SimpleStrategyConsumer implements AnnotationStrategyConsumer {
    @Override
    public <T extends ObjectStrategy<?>> void execute(AnnotationExecutor<T> executor, List<T> strategies, Set<Class<?>> source) {
        strategies.forEach(strategy -> {
            source.forEach(clazz -> {
                executor.tryExecute(strategy, clazz);
            });
        });
    }
}
