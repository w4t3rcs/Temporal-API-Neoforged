package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public abstract class TranslationStrategy implements FieldAnnotationStrategy {
    private final Class<?> translationProvider;

    protected TranslationStrategy(Class<?> translationProvider) {
        this.translationProvider = translationProvider;
    }

    protected void putDynamicTranslation(String possibleKey, String value, Object o) throws Exception {
        if (!possibleKey.isBlank()) {
            putTranslation("OTHER_TRANSLATIONS", possibleKey, value);
        } else {
            switch (o) {
                case DeferredItem<?> item -> putTranslation("ITEM_TRANSLATIONS", item, value);
                case DeferredBlock<?> block -> putTranslation("BLOCK_TRANSLATIONS", block, value);
                case String stringField -> putTranslation("OTHER_TRANSLATIONS", stringField, value);
                case Component component -> putTranslation("OTHER_TRANSLATIONS", component.getContents().type().id(), value);
                case Holder<?> holder -> {
                    Object holderValue = holder.value();
                    switch (holderValue) {
                        case EntityType<?> ignored -> putTranslation("ENTITY_TRANSLATIONS", ((Holder<EntityType<?>>) holder), value);
                        case MobEffect ignored -> putTranslation("EFFECT_TRANSLATIONS", ((Holder<MobEffect>) holder), value);
                        default -> throw new IllegalStateException("Unexpected value: " + holderValue);
                    }
                }
                case ResourceKey<?> key -> {
                    String path = key.registry().getPath();
                    if (path.contains(Registries.ENCHANTMENT.registry().getPath())) {
                        putTranslation("ENCHANTMENT_TRANSLATIONS", ((ResourceKey<Enchantment>) key), value);
                    } else if (path.contains(Registries.TRIM_MATERIAL.registry().getPath())) {
                        putTranslation("TRIM_MATERIAL_TRANSLATIONS", ((ResourceKey<TrimMaterial>) key), value);
                    } else if (path.contains(Registries.TRIM_PATTERN.registry().getPath())) {
                        putTranslation("TRIM_PATTERN_TRANSLATIONS", ((ResourceKey<TrimPattern>) key), value);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + o);
            }
        }
    }

    protected <T> void putTranslation(String fieldName, T key, String value) throws Exception {
        Map<T, String> translationMap = (Map<T, String>) this.translationProvider.getDeclaredField(fieldName).get(null);
        translationMap.put(key, value);
    }

    protected Class<?> getTranslationProvider() {
        return translationProvider;
    }
}
