package com.temporal.api.core.engine;

import com.temporal.api.core.engine.event.handler.DataEventHandler;
import com.temporal.api.core.engine.event.handler.EventHandler;
import com.temporal.api.core.engine.event.handler.FMLClientSetupEventHandler;
import com.temporal.api.core.engine.event.handler.FovModifierEventHandler;
import com.temporal.api.core.engine.io.context.*;
import com.temporal.api.core.engine.io.metadata.processor.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;

import java.util.Collections;
import java.util.List;

public class TemporalEngine {
    public static final List<ObjectPoolInitializer> INITIALIZERS = List.of(new DeferredRegisterPoolInitializer(), new FactoryPoolInitializer(), new EventBusPoolInitializer(), new ModContainerPoolInitializer());
    public static final List<AnnotationProcessor<?>> SIMPLE_PROCESSORS = List.of(new ClassAnnotationProcessor(), new StaticFieldAnnotationProcessor(), new FieldAnnotationProcessor(), new MethodAnnotationProcessor());
    public static final List<EventHandler> HANDLERS = List.of(new FMLClientSetupEventHandler(), new DataEventHandler(), new FovModifierEventHandler());
    protected static final String BANNER = """
                       _________ _________ ___     ___ _________ _________ _________ _________ ____
                       ---- ---- |   ----| |  \\   / | |  ___  | |  ___  | |  ___  | |  ___  | |  |
                          | |    |  |      | | \\ /| | |  | |  | |  | |  | |  | |  | |  | |  | |  |
                          | |    |   --|   | |    | | |  | |  | |  | |  | |  |-|  | |  | |  | |  |
                          | |    |   --|   | |    | | |  -----| |  | |  | |   ---|  |  |-|  | |  |
                          | |    |  |      | |    | | | |       |  | |  | |  | |--| |  |-|  | |  |
                          | |    |  -----| | |    | | | |       |  ---  | |  | |  | |  | |  | |  -----|
                          |-|    --------| |-|    |-| |-|       --------- |--| |--| |--| |--| --------|
                    """;

    public static LayerContainer run(Class<?> modClass, IEventBus eventBus, ModContainer modContainer) {
        synchronized (TemporalEngine.class) {
            return builder()
                    .configureIOLayer()
                    .modClass(modClass)
                    .initializers(INITIALIZERS)
                    .externalSource(List.of(eventBus, modContainer))
                    .simpleProcessors(SIMPLE_PROCESSORS)
                    .asyncProcessors(Collections.emptyList())
                    .cleaners(Collections.emptyList())
                    .and()
                    .configureEventLayer()
                    .handlers(HANDLERS)
                    .and()
                    .build();
        }
    }

    public static EngineBuilder builder() {
        return new EngineBuilder();
    }
}
