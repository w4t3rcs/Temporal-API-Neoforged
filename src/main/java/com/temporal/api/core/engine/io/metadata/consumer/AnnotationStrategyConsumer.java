package com.temporal.api.core.engine.io.metadata.consumer;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

import java.util.List;
import java.util.Set;

public interface AnnotationStrategyConsumer {
    <T extends ObjectStrategy<?>> void execute(AnnotationExecutor<T> executor, List<T> strategies, Set<Class<?>> source);
}
