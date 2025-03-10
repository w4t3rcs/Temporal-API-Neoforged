package com.temporal.api.core.engine;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.io.IOLayer;
import com.temporal.api.core.engine.io.context.ContextInitializer;
import com.temporal.api.core.engine.io.context.EventBusContextInitializer;
import com.temporal.api.core.engine.io.context.ExtraContextInitializer;
import com.temporal.api.core.engine.io.context.ModContainerContextInitializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;

import java.util.ArrayList;
import java.util.List;

public class TemporalEngine {
    private static final String BANNER = """
                       _________ _________ ___     ___ _________
                       ---- ---- |   ----| |  \\   / | |  ___  |
                          | |    |  |      | | \\ /| | |  | |  |
                          | |    |   --|   | |    | | |  ---  |
                          | |    |   --|   | |    | | |  -----|
                          | |    |  |      | |    | | | |
                          | |    |  -----| | |    | | | |
                          |-|    --------| |-|    |-| |-|
                    """;

    public static LayerContainer run(Class<?> modClass, IEventBus eventBus, ModContainer modContainer) {
        return config()
                .addLayer(new IOLayer())
                .processIOLayer(modClass, List.of(eventBus, modContainer),
                        new ExtraContextInitializer(), new EventBusContextInitializer(), new ModContainerContextInitializer())
                .build();
    }

    public static Configurator config() {
        return new Configurator();
    }

    public static class Configurator {
        private static final String LOAD_MESSAGE = "{} has been loaded!";
        private final LayerContainer layerContainer = LayerContainer.getInstance();
        private final List<Task> tasks = new ArrayList<>(2);

        private Configurator() {
        }

        public Configurator addLayer(EngineLayer engineLayer) {
            tasks.add(() -> layerContainer.add(engineLayer));
            return this;
        }

        public Configurator disableLayer(Class<? extends EngineLayer> engineLayerClass) {
            tasks.add(() -> layerContainer.delete(engineLayerClass));
            return this;
        }

        public Configurator processIOLayer(Class<?> modClass, List<?> externalInitializingSource, ContextInitializer... contextInitializers) {
            Task ioSetupTask = () -> {
                IOLayer ioLayer = layerContainer.getLayer(IOLayer.class);
                ioLayer.setModClass(modClass);
                ioLayer.setContextInitializers(List.of(contextInitializers));
                ioLayer.setExternalSource(externalInitializingSource);
                this.processLayer(ioLayer);
            };

            tasks.add(ioSetupTask);
            return this;
        }

        public LayerContainer build() {
            System.out.println(BANNER);
            tasks.forEach(Task::execute);
            ApiMod.LOGGER.info("Mod: {} has been registered as a TemporalEngine component!", IOLayer.NEO_MOD);
            return this.layerContainer;
        }

        private void processLayer(EngineLayer engineLayer) {
            engineLayer.processAllTasks();
            ApiMod.LOGGER.info(LOAD_MESSAGE, engineLayer.getClass().getSimpleName());
        }
    }

    @FunctionalInterface
    public interface Task {
        void execute();
    }
}
