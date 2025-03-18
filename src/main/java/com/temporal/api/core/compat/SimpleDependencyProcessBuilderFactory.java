package com.temporal.api.core.compat;

import java.util.ArrayList;
import java.util.List;

public class SimpleDependencyProcessBuilderFactory implements DependencyProcessBuilderFactory<SimpleDependencyProcessBuilderFactory, SimpleDependencyProcessBuilder> {
    @Override
    public SimpleDependencyProcessBuilder createBuilder(String dependencyId, String... additionalDependencyIds) {
        List<String> ids = new ArrayList<>(additionalDependencyIds.length + 1);
        ids.add(dependencyId);
        ids.addAll(List.of(additionalDependencyIds));
        return new SimpleDependencyProcessBuilder(ids, this);
    }
}
