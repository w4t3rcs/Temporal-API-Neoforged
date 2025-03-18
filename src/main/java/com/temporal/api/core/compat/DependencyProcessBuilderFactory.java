package com.temporal.api.core.compat;

public interface DependencyProcessBuilderFactory<T extends DependencyProcessBuilderFactory<T, B>, B extends DependencyProcessBuilder<T, B>> {
    B createBuilder(String dependencyId, String... additionalDependencyIds);
}
