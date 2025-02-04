package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.SpanishTranslation;
import com.temporal.api.core.engine.io.metadata.annotation.SpanishTranslations;
import com.temporal.api.core.event.data.language.SpanishProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class SpanishTranslationsStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SpanishTranslations.class)) {
            field.setAccessible(true);
            Holder<?> registryObject = (Holder<?>) field.get(object);
            Arrays.stream(field.getAnnotationsByType(SpanishTranslation.class)).forEach(translation -> {
                switch (translation.type()) {
                    case OTHER -> SpanishProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                    case ITEM -> SpanishProvider.ITEM_TRANSLATIONS.put((DeferredItem<? extends Item>) registryObject, translation.value());
                    case BLOCK -> SpanishProvider.BLOCK_TRANSLATIONS.put((DeferredBlock<? extends Block>) registryObject, translation.value());
                    case ENTITY -> SpanishProvider.ENTITY_TRANSLATIONS.put((DeferredHolder<? extends EntityType<?>, ? extends EntityType<?>>) registryObject, translation.value());
                    case EFFECT -> SpanishProvider.EFFECT_TRANSLATIONS.put((DeferredHolder<? extends MobEffect, ? extends MobEffect>) registryObject, translation.value());
                    case ENCHANTMENT -> SpanishProvider.ENCHANTMENT_TRANSLATIONS.put((DeferredHolder<? extends Enchantment, ? extends Enchantment>) registryObject, translation.value());
                }
            });
        }
    }
}
