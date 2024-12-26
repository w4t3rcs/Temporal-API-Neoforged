package com.temporal.api.core.engine.io.metadata.strategy.type;

import com.temporal.api.core.engine.io.context.Context;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.annotation.Injected;
import net.minecraftforge.fml.ModList;

public class InjectedStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        Injected injected = clazz.getDeclaredAnnotation(Injected.class);
        String modCondition = injected.injectionOnModCondition();
        if (injected.value() && injected.isInjection() && injected.isContextObject()
                && (modCondition.isBlank() || ModList.get().isLoaded(modCondition))) {
            Context context = InjectionContext.getInstance();
            context.putObject(clazz);
        }
    }
}
