package com.temporal.api.core.engine.io.metadata.strategy.field.injection;

import com.temporal.api.core.engine.io.context.Context;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.annotation.injection.Injection;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;

import java.lang.reflect.Field;

public class InjectionStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Injection.class)) {
            field.setAccessible(true);
            Context context = InjectionContext.getInstance();
            field.set(object, context.getObject(field.getType()));
            context.putObject(object);
        }
    }
}
