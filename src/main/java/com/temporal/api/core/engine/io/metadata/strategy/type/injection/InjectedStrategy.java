package com.temporal.api.core.engine.io.metadata.strategy.type.injection;

import com.temporal.api.core.engine.io.context.Context;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.annotation.injection.Injected;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import net.neoforged.fml.ModList;

public class InjectedStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        Injected injected = clazz.getDeclaredAnnotation(Injected.class);
        String modCondition = injected.injectionOnModCondition();
        if (injected.value() && injected.isInjection() && (modCondition.isBlank() || ModList.get().isLoaded(modCondition))) {
            Context context = InjectionContext.getInstance();
            context.putObject(clazz);
        }
    }
}
