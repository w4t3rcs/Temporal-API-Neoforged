package com.temporal.api.core.engine.io.metadata.strategy.field.data.language;

import com.temporal.api.core.engine.io.metadata.constant.TranslationType;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public abstract class TranslationStrategy implements FieldAnnotationStrategy {
    private final Class<?> translationProvider;

    protected TranslationStrategy(Class<?> translationProvider) {
        this.translationProvider = translationProvider;
    }

    protected void putTranslation(TranslationType type, String possibleKey, String value, Object o) throws Exception {
        switch (type) {
            case OTHER -> {
                String id;
                switch (o) {
                    case String stringField -> id = stringField;
                    case Component component -> id = component.getContents().type().id();
                    case null, default -> id = possibleKey;
                }

                putTranslation("OTHER_TRANSLATIONS", id, value);
            }

            case ITEM -> putTranslation("ITEM_TRANSLATIONS", (DeferredItem<? extends Item>) o, value);
            case BLOCK -> putTranslation("BLOCK_TRANSLATIONS", (DeferredBlock<? extends Block>) o, value);
            case ENTITY -> putTranslation("ENTITY_TRANSLATIONS", (DeferredHolder<? extends EntityType<?>, ? extends EntityType<?>>) o, value);
            case EFFECT -> putTranslation("EFFECT_TRANSLATIONS", (DeferredHolder<? extends MobEffect, ? extends MobEffect>) o, value);
            case ENCHANTMENT -> putTranslation("ENCHANTMENT_TRANSLATIONS", (ResourceKey<Enchantment>) o, value);
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
