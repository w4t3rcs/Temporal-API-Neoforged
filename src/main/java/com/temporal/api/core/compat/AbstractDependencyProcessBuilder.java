package com.temporal.api.core.compat;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDependencyProcessBuilder<T extends DependencyProcessBuilderFactory<T, B>, B extends AbstractDependencyProcessBuilder<T, B>> implements DependencyProcessBuilder<T, B> {
    private final List<String> dependencyIds;
    private final T director;
    private final List<DependencyFunction> callbacks = new ArrayList<>();

    protected AbstractDependencyProcessBuilder(List<String> dependencyIds, T director) {
        this.dependencyIds = dependencyIds;
        this.director = director;
    }

    protected List<String> getDependencyIds() {
        return dependencyIds;
    }

    protected T getDirector() {
        return director;
    }

    protected List<DependencyFunction> getCallbacks() {
        return callbacks;
    }

    protected void addCallback(DependencyFunction callback) {
        callbacks.add(callback);
    }
}
