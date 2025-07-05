package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.io.metadata.consumer.AnnotationStrategyConsumer;
import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

import java.util.List;
import java.util.Set;

public interface AnnotationProcessor<S extends ObjectStrategy<?>> {
    default void tryProcess(Set<Class<?>> classes, AnnotationStrategyConsumer consumer) {
        try {
            process(classes, consumer);
        } catch (Exception e) {
            ApiMod.LOGGER.error(e.getMessage());
        }
    }

    default void process(Set<Class<?>> classes, AnnotationStrategyConsumer consumer) {
        consumer.execute(getExecutor(), getStrategies(), classes);
    }

    AnnotationExecutor<S> getExecutor();

    List<S> getStrategies();
}
