package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.UkrainianTranslation;
import com.temporal.api.core.event.data.language.UkrainianProvider;
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

@SuppressWarnings("unchecked")
public class UkrainianTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(UkrainianTranslation.class)) {
            field.setAccessible(true);
            Holder<?> registryObject = (Holder<?>) field.get(object);
            UkrainianTranslation translation = field.getDeclaredAnnotation(UkrainianTranslation.class);
            switch (translation.type()) {
                case OTHER -> UkrainianProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> UkrainianProvider.ITEM_TRANSLATIONS.put((DeferredItem<? extends Item>) registryObject, translation.value());
                case BLOCK -> UkrainianProvider.BLOCK_TRANSLATIONS.put((DeferredBlock<? extends Block>) registryObject, translation.value());
                case ENTITY -> UkrainianProvider.ENTITY_TRANSLATIONS.put((DeferredHolder<? extends EntityType<?>, ? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> UkrainianProvider.EFFECT_TRANSLATIONS.put((DeferredHolder<? extends MobEffect, ? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> UkrainianProvider.ENCHANTMENT_TRANSLATIONS.put((DeferredHolder<? extends Enchantment, ? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
