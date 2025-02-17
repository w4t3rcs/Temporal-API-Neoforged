package com.temporal.api.core.engine.io.metadata;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.Injected;
import com.temporal.api.core.engine.io.metadata.strategy.field.*;
import com.temporal.api.core.engine.io.metadata.strategy.method.ExecutionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.*;

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
        final List<ClassAnnotationStrategy> classStrategies = List.of(
                new TagContainerStrategy(),
                new DefinedRecipeStrategy(),
                new DefinedGlobalLootModifierStrategy(),
                new DefinedAdvancementStrategy(),
                new DefinedEnchantmentEntityEffectStrategy()
        );
        final List<FieldAnnotationStrategy> fieldStrategies = List.of(
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
                new RecipeStrategy(),
                new ArmorAssetStrategy(),
                new OreGenerationStrategy(),
                new BlockTagComponentStrategy(),
                new ItemTagComponentStrategy(),
                new FurnaceFuelStrategy(),
                new CompostableStrategy(),
                new OxidizableStrategy(),
                new WaxableStrategy(),
                new RaidHeroGiftStrategy(),
                new MonsterRoomMobStrategy(),
                new ParrotImitationStrategy(),
                new SoundDescriptionStrategy(),
                new ParticleSpriteSetStrategy(),
                new EnchantmentDefinitionStrategy()
        );

        this.classes.forEach(clazz -> classStrategies.forEach(strategy -> strategyExecutor.executeClass(strategy, clazz)));
        this.classes.forEach(clazz -> fieldStrategies.forEach(strategy -> strategyExecutor.executeStaticField(strategy, clazz)));
    }
}
