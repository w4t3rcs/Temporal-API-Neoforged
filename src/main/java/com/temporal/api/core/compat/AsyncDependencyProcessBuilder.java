package com.temporal.api.core.compat;

import com.temporal.api.ApiMod;
import com.temporal.api.core.engine.event.handler.EventHandler;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncDependencyProcessBuilder extends AbstractDependencyProcessBuilder<AsyncDependencyProcessBuilder> {
    private static final int FIXED_THREAD_NUMBER = 3;

    protected AsyncDependencyProcessBuilder(List<String> dependencyIds) {
        super(dependencyIds);
    }

    public static AsyncDependencyProcessBuilder create(String dependencyId, String... additionalDependencyIds) {
        List<String> ids = new ArrayList<>(additionalDependencyIds.length + 1);
        ids.add(dependencyId);
        ids.addAll(List.of(additionalDependencyIds));
        return new AsyncDependencyProcessBuilder(ids);
    }

    @Override
    public AsyncDependencyProcessBuilder addEventProcess(EventHandler eventHandler) {
        return this.addProcess(eventHandler::handle);
    }

    @Override
    public AsyncDependencyProcessBuilder addProcess(DependencyFunction callback) {
        for (String dependencyId : this.getDependencyIds()) {
            if (!ModList.get().isLoaded(dependencyId)) {
                ApiMod.LOGGER.warn("Dependency: \"{}\" is not found, so dependency process won't be added to callbacks: {}", dependencyId, callback.toString());
                return this;
            }
        }

        this.addCallback(callback);
        return this;
    }

    @Override
    public AsyncDependencyProcessBuilder startNext(String dependencyId, String... additionalDependencyIds) {
        this.build();
        return create(dependencyId, additionalDependencyIds);
    }

    @Override
    public void build() {
        ExecutorService pool = Executors.newFixedThreadPool(FIXED_THREAD_NUMBER);
        try {
            List<CompletableFuture<Void>> futures = this.getCallbacks().stream()
                    .map(callback -> CompletableFuture.runAsync(callback::execute, pool))
                    .toList();
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } finally {
            pool.shutdown();
        }
    }
}
