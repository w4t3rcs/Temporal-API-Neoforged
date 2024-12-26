package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

public interface ClassAnnotationStrategy extends ObjectStrategy<Class<?>> {
    void execute(Class<?> clazz, Object object) throws Exception;
}
