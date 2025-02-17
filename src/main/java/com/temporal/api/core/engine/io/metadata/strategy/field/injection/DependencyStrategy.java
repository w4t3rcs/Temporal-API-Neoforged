package com.temporal.api.core.engine.io.metadata.strategy.field.injection;

import com.temporal.api.core.engine.io.context.Context;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.annotation.injection.Dependency;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import net.neoforged.fml.ModList;

import java.lang.reflect.Field;

public class DependencyStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(Dependency.class)) {
            field.setAccessible(true);
            Dependency dependency = field.getDeclaredAnnotation(Dependency.class);
            field.setBoolean(object, ModList.get().isLoaded(dependency.value()));
            Context context = InjectionContext.getInstance();
            context.putObject(object);
        }
    }
}
