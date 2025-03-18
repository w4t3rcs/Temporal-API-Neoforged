package com.temporal.api.core.compat;

import com.temporal.api.ApiMod;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.function.Function;

public class SimpleDependencyProcessBuilder extends AbstractDependencyProcessBuilder<SimpleDependencyProcessBuilderFactory, SimpleDependencyProcessBuilder> {
    protected SimpleDependencyProcessBuilder(List<String> dependencyIds, SimpleDependencyProcessBuilderFactory director) {
        super(dependencyIds, director);
    }

    @Override
    public DependencyProcessBuilder<SimpleDependencyProcessBuilderFactory, SimpleDependencyProcessBuilder> addProcess(Function<Void, Void> callback) {
        for (String dependencyId : this.getDependencyIds()) {
            if (!ModList.get().isLoaded(dependencyId)) {
                ApiMod.LOGGER.warn("Dependency: \"{}\" is not found, so dependency process won't be added to callbacks: {}", dependencyId, callback.toString());
                return this;
            }
        }

        this.getCallbacks().add(callback);
        return this;
    }

    @Override
    public DependencyProcessBuilderFactory<SimpleDependencyProcessBuilderFactory, SimpleDependencyProcessBuilder> build() {
        this.getCallbacks().forEach(callback -> callback.apply(null));
        return this.getDirector();
    }
}
