package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.ClassExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedAdvancementStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedEnchantmentEntityEffectStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedGlobalLootModifierStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedRecipeStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.other.CustomAdvancementStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.tag.TagContainerStrategy;

import java.util.List;

public class DataClassAnnotationProcessor implements AnnotationProcessor<ClassAnnotationStrategy> {
    private final AnnotationExecutor<ClassAnnotationStrategy> executor = new ClassExecutor();
    private final List<ClassAnnotationStrategy> strategies = List.of(
            new TagContainerStrategy(),
            new DefinedRecipeStrategy(),
            new DefinedGlobalLootModifierStrategy(),
            new DefinedAdvancementStrategy(),
            new DefinedEnchantmentEntityEffectStrategy(),
            new CustomAdvancementStrategy()
    );

    @Override
    public AnnotationExecutor<ClassAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<ClassAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
