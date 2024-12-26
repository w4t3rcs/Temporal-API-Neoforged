package com.temporal.api.core.engine;

import com.temporal.api.core.engine.io.context.ContextInitializer;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.DefaultAnnotationExecutor;
import com.temporal.api.core.engine.io.resource.ForgeMod;

import java.util.List;

public class IOLayer implements EngineLayer {
    public static volatile ForgeMod FORGE_MOD;
    private Class<?> modClass;
    private List<ContextInitializer> contextInitializers;

    @Override
    public void processAllTasks() {
        FORGE_MOD = ForgeMod.create(this.modClass);
        contextInitializers.forEach(ContextInitializer::initialize);
        InjectionContext.getInstance()
                .getObjects(ContextInitializer.class)
                .forEach(ContextInitializer::initialize);
        InjectionContext.getInstance().getObject(DefaultAnnotationExecutor.class)
                .execute(this.modClass);
    }

    public void setModClass(Class<?> modClass) {
        this.modClass = modClass;
    }

    public void setContextInitializers(List<ContextInitializer> contextInitializers) {
        this.contextInitializers = contextInitializers;
    }
}
