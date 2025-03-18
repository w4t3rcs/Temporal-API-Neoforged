package com.temporal.api.core.compat;

public interface DependencyProcessBuilder<T extends DependencyProcessBuilderFactory<T, B>, B extends DependencyProcessBuilder<T, B>> {
    DependencyProcessBuilder<T, B> addProcess(DependencyFunction callback);

    DependencyProcessBuilderFactory<T, B> build();
}
