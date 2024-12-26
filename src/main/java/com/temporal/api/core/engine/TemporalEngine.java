package com.temporal.api.core.engine;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.io.context.ContextInitializer;
import com.temporal.api.core.engine.io.context.ExtraContextInitializer;

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

    public static LayerContainer run(Class<?> modClass) {
        return config()
                .addLayer(new IOLayer())
                .processIOLayer(modClass, new ExtraContextInitializer())
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

        public Configurator processIOLayer(Class<?> modClass, ContextInitializer... contextInitializers) {
            Task ioSetupTask = () -> {
                IOLayer ioLayer = layerContainer.getLayer(IOLayer.class);
                ioLayer.setModClass(modClass);
                ioLayer.setContextInitializers(List.of(contextInitializers));
                this.logLayerProcession(ioLayer);
            };

            tasks.add(ioSetupTask);
            return this;
        }

        public LayerContainer build() {
            System.out.println(BANNER);
            tasks.forEach(Task::execute);
            ApiMod.LOGGER.info("Mod: {} has been registered as a TemporalEngine component!", IOLayer.FORGE_MOD);
            return this.layerContainer;
        }

        private void logLayerProcession(EngineLayer engineLayer) {
            engineLayer.processAllTasks();
            ApiMod.LOGGER.info(LOAD_MESSAGE, engineLayer.getClass().getSimpleName());
        }
    }

    @FunctionalInterface
    public interface Task {
        void execute();
    }
}
