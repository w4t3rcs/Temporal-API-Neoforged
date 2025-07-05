package com.temporal.api.core.engine.io.metadata.executor;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;

import java.lang.reflect.Method;

public class MethodExecutor implements AnnotationExecutor<MethodAnnotationStrategy> {
    @Override
    public void execute(MethodAnnotationStrategy strategy, Class<?> clazz) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            try {
                strategy.execute(method, InjectionPool.getFromInstance(clazz));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
