package com.temporal.api.core.engine.io.metadata;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.Injected;
import com.temporal.api.core.engine.io.metadata.strategy.field.*;
import com.temporal.api.core.engine.io.metadata.strategy.method.ExecutionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.InjectedStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.RegistryClassStrategy;

import java.util.List;
import java.util.Set;

public class DefaultAnnotationExecutor implements AnnotationExecutor {
    private volatile Set<Class<?>> classes;
    private volatile AnnotationStrategyExecutor strategyExecutor;

    @Override
    public void prepareBeforeExecution(Class<?> dependencyClass) {
        this.classes = IOHelper.getAllClasses(dependencyClass, Injected.class);
        this.strategyExecutor = DefaultAnnotationStrategyExecutor.getInstance();
    }

    @Override
    public void executeClassAnnotations() {
        List<ClassAnnotationStrategy> strategies = List.of(
                new InjectedStrategy(),
                new RegistryClassStrategy()
        );

        this.classes.forEach(clazz -> strategies.forEach(strategy -> strategyExecutor.executeClass(strategy, clazz)));
    }

    @Override
    public void executeStaticFieldAnnotations() {
        final List<FieldAnnotationStrategy> strategies = List.of(
                new RegistryFieldStrategy()
        );

        this.classes.forEach(clazz -> strategies.forEach(strategy -> strategyExecutor.executeStaticField(strategy, clazz)));
    }

    @Override
    public void executeFieldAnnotations() {
        final List<FieldAnnotationStrategy> strategies = List.of(
                new InjectionStrategy(),
                new DependencyStrategy()
        );

        this.classes.forEach(clazz -> strategies.forEach(strategy -> strategyExecutor.executeField(strategy, clazz)));
    }

    @Override
    public void executeMethodAnnotations() {
        final List<MethodAnnotationStrategy> strategies = List.of(
                new ExecutionStrategy()
        );

        this.classes.forEach(clazz -> strategies.forEach(strategy -> strategyExecutor.executeMethod(strategy, clazz)));
    }

    @Override
    public void executeDataGenerationAnnotations() {
        final List<FieldAnnotationStrategy> strategies = List.of(
                new BlockModelStrategy(),
                new ItemModelStrategy(),
                new BlockLootTableStrategy(),
                new EnglishTranslationStrategy(),
                new UkrainianTranslationStrategy(),
                new PolishTranslationStrategy(),
                new GermanTranslationStrategy(),
                new FrenchTranslationStrategy(),
                new ItalianTranslationStrategy(),
                new SpanishTranslationStrategy(),
                new EnglishTranslationsStrategy(),
                new UkrainianTranslationsStrategy(),
                new PolishTranslationsStrategy(),
                new GermanTranslationsStrategy(),
                new FrenchTranslationsStrategy(),
                new ItalianTranslationsStrategy(),
                new SpanishTranslationsStrategy(),
                new RecipeStrategy(),
                new ArmorAssetStrategy()
        );

        this.classes.forEach(clazz -> strategies.forEach(strategy -> strategyExecutor.executeStaticField(strategy, clazz)));
    }
}
