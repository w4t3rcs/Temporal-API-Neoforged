package com.temporal.api.core.engine.io.metadata.consumer;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.ObjectStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class AsyncStrategyConsumer implements AnnotationStrategyConsumer {
    @Override
    public <T extends ObjectStrategy<?>> void execute(AnnotationExecutor<T> executor, List<T> strategies, Set<Class<?>> source) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        strategies.forEach(strategy -> {
            source.forEach(clazz -> {
                futures.add(CompletableFuture.runAsync(() -> {
                    executor.tryExecute(strategy, clazz);
                }, pool));
            });
        });

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
