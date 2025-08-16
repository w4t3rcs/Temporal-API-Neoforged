package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.StaticFieldExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.fml.SetupBowClientStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.fml.SetupCrossbowClientStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.fml.SetupShieldClientStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.fml.SetupWoodTypeClientStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.render.RegisterHangingSignRendererStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.event.render.RegisterSignRendererStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.RegistryFieldStrategy;

import java.util.List;

public class StaticFieldAnnotationProcessor implements AnnotationProcessor<FieldAnnotationStrategy> {
    private final AnnotationExecutor<FieldAnnotationStrategy> executor = new StaticFieldExecutor();
    private final List<FieldAnnotationStrategy> strategies = List.of(
            new RegistryFieldStrategy(),
            new SetupBowClientStrategy(),
            new SetupCrossbowClientStrategy(),
            new SetupShieldClientStrategy(),
            new SetupWoodTypeClientStrategy(),
            new RegisterSignRendererStrategy(),
            new RegisterHangingSignRendererStrategy()
    );

    @Override
    public AnnotationExecutor<FieldAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<FieldAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
