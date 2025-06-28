package com.temporal.api.core.engine.io;

import com.temporal.api.core.engine.EngineLayer;
import com.temporal.api.core.engine.io.context.Context;
import com.temporal.api.core.engine.io.context.ContextCleaner;
import com.temporal.api.core.engine.io.context.ContextInitializer;
import com.temporal.api.core.engine.io.context.InjectionContextContainer;
import com.temporal.api.core.engine.io.metadata.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.DefaultAnnotationExecutor;
import com.temporal.api.core.engine.io.resource.NeoMod;

import java.util.List;

public class IOLayer implements EngineLayer {
    public static volatile NeoMod NEO_MOD;
    private Class<?> modClass;
    private List<ContextInitializer> contextInitializers;
    private List<?> externalSource;
    private List<ContextCleaner> contextCleaners;

    @Override
    public void processAllTasks() {
        NEO_MOD = NeoMod.create(this.modClass);
        Context context = InjectionContextContainer.getInstance()
                .createContext(NEO_MOD.getModId());
        contextInitializers.forEach(initializer -> initializer.initialize(this.externalSource));
        context.getObjects(ContextInitializer.class)
                .forEach(initializer -> initializer.initialize(this.externalSource));
        AnnotationExecutor annotationExecutor = new DefaultAnnotationExecutor();
        annotationExecutor.execute(this.modClass);
        contextCleaners.forEach(ContextCleaner::clear);
        context.getObjects(ContextCleaner.class)
                .forEach(ContextCleaner::clear);
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

    public void setContextCleaners(List<ContextCleaner> contextCleaners) {
        this.contextCleaners = contextCleaners;
    }
}
