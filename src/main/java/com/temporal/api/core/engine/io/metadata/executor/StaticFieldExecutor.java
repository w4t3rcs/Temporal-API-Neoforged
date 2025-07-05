package com.temporal.api.core.engine.io.metadata.executor;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;

import java.lang.reflect.Field;

public class StaticFieldExecutor implements AnnotationExecutor<FieldAnnotationStrategy> {
    @Override
    public void execute(FieldAnnotationStrategy strategy, Class<?> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                strategy.execute(field, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
