package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.language.provider.ApiLanguageProvider;
import com.temporal.api.core.event.data.language.transformer.KeyTransformer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class TranslationStrategy implements FieldAnnotationStrategy {
    private final Class<?> translationProvider;

    protected TranslationStrategy(Class<?> translationProvider) {
        this.translationProvider = translationProvider;
    }

    protected void putDynamicTranslation(String possibleKey, String value, Object o) throws Exception {
        if (!possibleKey.isBlank()) {
            putTranslation(possibleKey, value, ApiLanguageProvider.STRING_TRANSFORMER);
        } else {
            switch (o) {
                case DeferredItem<?> item -> putTranslation(item.getKey(), value, ApiLanguageProvider.ITEM_TRANSFORMER);
                case DeferredBlock<?> block -> {
                    putTranslation(block.getKey(), value, ApiLanguageProvider.BLOCK_TRANSFORMER);
                    putTranslation(block.getKey(), value, ApiLanguageProvider.BLOCK_ITEM_TRANSFORMER);
                }
                case String stringField -> putTranslation(stringField, value, ApiLanguageProvider.STRING_TRANSFORMER);
                case Component component -> putTranslation(component, value, ApiLanguageProvider.COMPONENT_TRANSFORMER);
                case Holder<?> holder -> {
                    ResourceKey<?> key = Objects.requireNonNull(holder.getKey());
                    putTranslationIfNeeded(key, Registries.ENTITY_TYPE, value, ApiLanguageProvider.ENTITY_TYPE_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.MOB_EFFECT, value, ApiLanguageProvider.MOB_EFFECT_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.SOUND_EVENT, value, ApiLanguageProvider.SOUND_EVENT_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.PAINTING_VARIANT, value, ApiLanguageProvider.PAINTING_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.CREATIVE_MODE_TAB, value, ApiLanguageProvider.CREATIVE_MODE_TAB_TRANSFORMER);
                }
                case ResourceKey<?> key -> {
                    putTranslationIfNeeded(key, Registries.ENCHANTMENT, value, ApiLanguageProvider.ENCHANTMENT_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.TRIM_MATERIAL, value, ApiLanguageProvider.TRIM_MATERIAL_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.TRIM_PATTERN, value, ApiLanguageProvider.TRIM_PATTERN_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.BANNER_PATTERN, value, ApiLanguageProvider.BANNER_PATTERN_TRANSFORMER);
                    putTranslationIfNeeded(key, Registries.DAMAGE_TYPE, value, ApiLanguageProvider.DAMAGE_TYPE_TRANSFORMER);
                }
                default -> throw new IllegalStateException("Unexpected value: " + o);
            }
        }
    }

    protected <T> void putTranslationIfNeeded(ResourceKey<?> key, ResourceKey<Registry<T>> registry, String value, KeyTransformer<ResourceKey<T>> transformer) throws Exception {
        String path = key.registry().getPath();
        if (isNeededKey(path, registry)) {
            putTranslation(((ResourceKey<T>) key), value, transformer);
        }
    }

    protected boolean isNeededKey(String path, ResourceKey<?> key) {
        return path.contains(key.location().getPath());
    }

    protected <T> void putTranslation(T key, String value, KeyTransformer<T> keyTransformer) throws Exception {
        Map<String, String> translationMap = (Map<String, String>) this.translationProvider.getDeclaredField("TRANSLATIONS").get(null);
        String transformedKey = keyTransformer.transform(key);
        translationMap.put(transformedKey, value);
    }

    protected Class<?> getTranslationProvider() {
        return translationProvider;
    }
}
