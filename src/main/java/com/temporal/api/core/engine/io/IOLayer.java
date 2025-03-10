package com.temporal.api.core.engine.io;

import com.temporal.api.core.engine.EngineLayer;
import com.temporal.api.core.engine.io.context.ContextInitializer;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.engine.io.metadata.DefaultAnnotationExecutor;
import com.temporal.api.core.engine.io.resource.NeoMod;

import java.util.List;

public class IOLayer implements EngineLayer {
    public static volatile NeoMod NEO_MOD;
    private Class<?> modClass;
    private List<ContextInitializer> contextInitializers;
    private List<?> externalSource;

    @Override
    public void processAllTasks() {
        NEO_MOD = NeoMod.create(this.modClass);
        contextInitializers.forEach(initializer -> initializer.initialize(this.externalSource));
        InjectionContext.getInstance()
                .getObjects(ContextInitializer.class)
                .forEach(initializer -> initializer.initialize(this.externalSource));
        InjectionContext.getFromInstance(DefaultAnnotationExecutor.class)
                .execute(this.modClass);
    }

    public void setModClass(Class<?> modClass) {
        this.modClass = modClass;
    }

    public void setContextInitializers(List<ContextInitializer> contextInitializers) {
        this.contextInitializers = contextInitializers;
    }

    public void setExternalSource(List<?> externalSource) {
        this.externalSource = externalSource;
    }
}
