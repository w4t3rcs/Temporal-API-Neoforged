package com.temporal.api.core.engine.io.metadata;

import com.temporal.api.core.engine.io.metadata.annotation.injection.Injected;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.biome.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.language.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.model.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.other.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.properties.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.tag.BlockTagComponentStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.tag.ItemTagComponentStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.DependencyStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.InjectionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.injection.RegistryFieldStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.ExecutionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.method.MethodAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedAdvancementStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedEnchantmentEntityEffectStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedGlobalLootModifierStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.defined.DefinedRecipeStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.data.tag.TagContainerStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.injection.InjectedStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.type.injection.RegistryClassStrategy;
import com.temporal.api.core.util.other.IOUtils;

import java.util.List;
import java.util.Set;

public class DefaultAnnotationExecutor implements AnnotationExecutor {
    private volatile Set<Class<?>> classes;
    private volatile AnnotationStrategyExecutor strategyExecutor;

    @Override
    public void prepareBeforeExecution(Class<?> dependencyClass) {
        this.classes = IOUtils.getAllClasses(dependencyClass, Injected.class);
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
                new CustomBlockModelStrategy(),
                new ItemModelStrategy(),
                new CustomItemModelStrategy(),
                new BlockLootTableStrategy(),
                new CustomBlockLootTableStrategy(),
                new RecipeStrategy(),
                new ArmorAssetStrategy(),
                new BlockTagComponentStrategy(),
                new ItemTagComponentStrategy(),
                new OreGenerationStrategy(),
                new TreeGenerationStrategy(),
                new FlowerGenerationStrategy(),
                new GrassGenerationStrategy(),
                new WorldFeatureGenerationStrategy(),
                new FurnaceFuelStrategy(),
                new CompostableStrategy(),
                new OxidizableStrategy(),
                new WaxableStrategy(),
                new RaidHeroGiftStrategy(),
                new MonsterRoomMobStrategy(),
                new ParrotImitationStrategy(),
                new SoundDescriptionStrategy(),
                new ParticleSpriteSetStrategy(),
                new EnchantmentDefinitionStrategy(),
                new DamageTypeDescriptionStrategy(),
                new TrimMaterialDescriptionStrategy(),
                new TrimPatternDescriptionStrategy(),
                new EnglishTranslationStrategy(),
                new UkrainianTranslationStrategy(),
                new PolishTranslationStrategy(),
                new GermanTranslationStrategy(),
                new FrenchTranslationStrategy(),
                new ItalianTranslationStrategy(),
                new SpanishTranslationStrategy(),
                new AlbanianTranslationStrategy(),
                new AustrianGermanTranslationStrategy(),
                new BelarusianTranslationStrategy(),
                new BosnianTranslationStrategy(),
                new BulgarianTranslationStrategy(),
                new CroatianTranslationStrategy(),
                new CzechTranslationStrategy(),
                new DanishTranslationStrategy(),
                new DutchTranslationStrategy(),
                new EstonianTranslationStrategy(),
                new FilipinoTranslationStrategy(),
                new FinnishTranslationStrategy(),
                new GreekTranslationStrategy(),
                new HebrewTranslationStrategy(),
                new HindiTranslationStrategy(),
                new HungarianTranslationStrategy(),
                new IcelandicTranslationStrategy(),
                new IndonesianTranslationStrategy(),
                new IrishTranslationStrategy(),
                new JapaneseTranslationStrategy(),
                new KazakhTranslationStrategy(),
                new KoreanTranslationStrategy(),
                new LatvianTranslationStrategy(),
                new LithuanianTranslationStrategy(),
                new MandarinTranslationStrategy(),
                new PersianTranslationStrategy(),
                new PortugueseTranslationStrategy(),
                new RomanianTranslationStrategy(),
                new SerbianTranslationStrategy(),
                new SlovakTranslationStrategy(),
                new SlovenianTranslationStrategy(),
                new SwedishTranslationStrategy(),
                new SwissGermanTranslationStrategy(),
                new ThaiTranslationStrategy(),
                new TurkishTranslationStrategy(),
                new VietnameseTranslationStrategy()
        );

        this.classes.forEach(clazz -> classStrategies.forEach(strategy -> strategyExecutor.executeClass(strategy, clazz)));
        this.classes.forEach(clazz -> fieldStrategies.forEach(strategy -> strategyExecutor.executeStaticField(strategy, clazz)));
    }
}
