package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.language.provider.ApiLanguageProvider;
import com.temporal.api.core.event.data.language.transformer.KeyTransformer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
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
                    ResourceKey<?> key = holder.getKey();
                    String path = Objects.requireNonNull(key).registry().getPath();
                    if (path.contains(Registries.ENTITY_TYPE.location().getPath())) {
                        putTranslation(((ResourceKey<EntityType<?>>) key), value, ApiLanguageProvider.ENTITY_TYPE_TRANSFORMER);
                    } else if (path.contains(Registries.MOB_EFFECT.location().getPath())) {
                        putTranslation((ResourceKey<MobEffect>) key, value, ApiLanguageProvider.MOB_EFFECT_TRANSFORMER);
                    } else if (path.contains(Registries.SOUND_EVENT.location().getPath())) {
                        putTranslation(((ResourceKey<SoundEvent>) key), value, ApiLanguageProvider.SOUND_EVENT_TRANSFORMER);
                    } else if (path.contains(Registries.PAINTING_VARIANT.location().getPath())) {
                        putTranslation(((ResourceKey<PaintingVariant>) key), value, ApiLanguageProvider.PAINTING_TRANSFORMER);
                    } else if (path.contains(Registries.CREATIVE_MODE_TAB.location().getPath())) {
                        putTranslation(((ResourceKey<CreativeModeTab>) key), value, ApiLanguageProvider.CREATIVE_MODE_TAB_TRANSFORMER);
                    }
                }
                case ResourceKey<?> key -> {
                    String path = key.registry().getPath();
                    if (path.contains(Registries.ENCHANTMENT.location().getPath())) {
                        putTranslation(((ResourceKey<Enchantment>) key), value, ApiLanguageProvider.ENCHANTMENT_TRANSFORMER);
                    } else if (path.contains(Registries.TRIM_MATERIAL.location().getPath())) {
                        putTranslation(((ResourceKey<TrimMaterial>) key), value, ApiLanguageProvider.TRIM_MATERIAL_TRANSFORMER);
                    } else if (path.contains(Registries.TRIM_PATTERN.location().getPath())) {
                        putTranslation(((ResourceKey<TrimPattern>) key), value, ApiLanguageProvider.TRIM_PATTERN_TRANSFORMER);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + o);
            }
        }
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
