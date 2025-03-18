package com.temporal.api.core.compat;

import java.util.function.Function;

public interface DependencyProcessBuilder<T extends DependencyProcessBuilderFactory<T, B>, B extends DependencyProcessBuilder<T, B>> {
    DependencyProcessBuilder<T, B> addProcess(Function<Void, Void> callback);

    DependencyProcessBuilderFactory<T, B> build();
}
