package com.temporal.api.core.engine.io.metadata.processor;

import com.temporal.api.core.engine.io.metadata.executor.AnnotationExecutor;
import com.temporal.api.core.engine.io.metadata.executor.StaticFieldExecutor;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.biome.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.language.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.model.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.music.JukeboxSongDescriptionStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.other.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.properties.*;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.tag.BlockTagComponentStrategy;
import com.temporal.api.core.engine.io.metadata.strategy.field.data.tag.ItemTagComponentStrategy;

import java.util.List;

public class DataFieldAnnotationProcessor implements AnnotationProcessor<FieldAnnotationStrategy> {
    private final AnnotationExecutor<FieldAnnotationStrategy> executor = new StaticFieldExecutor();
    private final List<FieldAnnotationStrategy> strategies = List.of(
            new BlockModelStrategy(),
            new CustomBlockModelStrategy(),
            new ItemModelStrategy(),
            new CustomItemModelStrategy(),
            new BlockLootTableStrategy(),
            new CustomBlockLootTableStrategy(),
            new RecipeStrategy(),
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
            new JukeboxSongDescriptionStrategy(),
            new PaintingStrategy(),
            new ParticleSpriteSetStrategy(),
            new EnchantmentDefinitionStrategy(),
            new DamageTypeDescriptionStrategy(),
            new TrimMaterialDescriptionStrategy(),
            new TrimPatternDescriptionStrategy(),
            new WolfVariantDescriptionStrategy(),
            new BannerPatternDescriptionStrategy(),
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

    @Override
    public AnnotationExecutor<FieldAnnotationStrategy> getExecutor() {
        return executor;
    }

    @Override
    public List<FieldAnnotationStrategy> getStrategies() {
        return strategies;
    }
}
