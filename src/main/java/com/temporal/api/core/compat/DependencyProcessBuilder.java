package com.temporal.api.core.compat;

public interface DependencyProcessBuilder<B extends DependencyProcessBuilder<B>> {
    B addProcess(DependencyFunction callback);

    B startNext(String dependencyId, String... additionalDependencyIds);

    void build();
}
