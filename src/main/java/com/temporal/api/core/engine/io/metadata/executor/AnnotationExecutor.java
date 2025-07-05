package com.temporal.api.core.engine.io.metadata.executor;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

public interface AnnotationExecutor<S extends ObjectStrategy<?>> {
    default void tryExecute(S strategy, Class<?> clazz) {
        try {
            execute(strategy, clazz);
            logScanning(strategy, clazz);
        } catch (Exception e) {
            logException(e, strategy, clazz);
        }
    }

    void execute(S strategy, Class<?> clazz) throws Exception;

    private void logScanning(ObjectStrategy<?> strategy, Class<?> clazz) {
        ApiMod.LOGGER.info("Scanned: strategy - {}, class - {}", strategy.getClass().getSimpleName(), clazz.getSimpleName());
    }

    private void logException(Exception e, ObjectStrategy<?> strategy, Class<?> clazz) {
        ApiMod.LOGGER.warn("{} in {} went wrong! - {}", strategy.getClass().getSimpleName(), clazz.getSimpleName(), e.getMessage());
    }
}
