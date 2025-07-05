package com.temporal.api.core.engine.io.metadata.executor;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;

import java.lang.reflect.Field;

public class FieldExecutor implements AnnotationExecutor<FieldAnnotationStrategy> {
    @Override
    public void execute(FieldAnnotationStrategy strategy, Class<?> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                strategy.execute(field, InjectionPool.getFromInstance(clazz));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
