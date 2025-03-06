package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.util.other.IOUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public abstract class TranslationStrategy implements FieldAnnotationStrategy {
    private final Class<?> translationProvider;

    protected TranslationStrategy(Class<?> translationProvider) {
        this.translationProvider = translationProvider;
    }

    protected void putDynamicTranslation(String possibleKey, String value, Object o) throws Exception {
        switch (o) {
            case DeferredItem<?> item -> putTranslation("ITEM_TRANSLATIONS", item, value);
            case DeferredBlock<?> block -> putTranslation("BLOCK_TRANSLATIONS", block, value);
            case String stringField -> putTranslation("OTHER_TRANSLATIONS", stringField, value);
            case Component component -> putTranslation("OTHER_TRANSLATIONS", component.getContents().type().id(), value);
            default -> {
                if (putDeferredHolderTranslation("ENTITY_TRANSLATIONS", EntityType.class, o, value)) {
                }
                else if (putDeferredHolderTranslation("EFFECT_TRANSLATIONS", MobEffect.class, o, value)) {
                }
                else if (putResourceKeyTranslation("ENCHANTMENT_TRANSLATIONS", Enchantment.class, o, value)) {
                }
                else if (putResourceKeyTranslation("TRIM_MATERIAL_TRANSLATIONS", TrimMaterial.class, o, value)) {
                }
                else if (putResourceKeyTranslation("TRIM_PATTERN_TRANSLATIONS", TrimPattern.class, o, value)) {
                }
                else putTranslation("OTHER_TRANSLATIONS", possibleKey, value);
            }
        }
    }

    protected <T> boolean putDeferredHolderTranslation(String fieldName, Class<? extends T> clazz, Object o, String value) throws Exception {
        DeferredHolder<? extends T, ? extends T> holder = IOUtils.<DeferredHolder<T, T>>tryCast(o).orElse(null);
        if (holder != null) {
            putTranslation(fieldName, holder, value);
            return true;
        }

        return false;
    }

    protected <T> boolean putResourceKeyTranslation(String fieldName, Class<? extends T> clazz, Object o, String value) throws Exception {
        ResourceKey<T> resourceKey = IOUtils.<ResourceKey<T>>tryCast(o).orElse(null);
        if (resourceKey != null) {
            putTranslation(fieldName, resourceKey, value);
            return true;
        }

        return false;
    }

    protected <T> void putTranslation(String fieldName, T key, String value) throws Exception {
        Map<T, String> translationMap = (Map<T, String>) this.translationProvider.getDeclaredField(fieldName).get(null);
        translationMap.put(key, value);
    }

    protected Class<?> getTranslationProvider() {
        return translationProvider;
    }
}
