package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.EnglishTranslation;
import com.temporal.api.core.event.data.language.EnglishProvider;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
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
public class EnglishTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(EnglishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            Holder<?> registryObject;
            if (o instanceof Holder<?>) registryObject = (Holder<?>) o;
            else registryObject = null;
            EnglishTranslation translation = field.getDeclaredAnnotation(EnglishTranslation.class);
            switch (translation.type()) {
                case OTHER -> {
                    String id;
                    switch (o) {
                        case String stringField -> id = stringField;
                        case Component component -> id = component.getContents().type().id();
                        case null, default -> id = translation.id();
                    }

                    EnglishProvider.OTHER_TRANSLATIONS.put(id, translation.value());
                }
                case ITEM -> EnglishProvider.ITEM_TRANSLATIONS.put((DeferredItem<? extends Item>) registryObject, translation.value());
                case BLOCK -> EnglishProvider.BLOCK_TRANSLATIONS.put((DeferredBlock<? extends Block>) registryObject, translation.value());
                case ENTITY -> EnglishProvider.ENTITY_TRANSLATIONS.put((DeferredHolder<? extends EntityType<?>, ? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> EnglishProvider.EFFECT_TRANSLATIONS.put((DeferredHolder<? extends MobEffect, ? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> EnglishProvider.ENCHANTMENT_TRANSLATIONS.put((DeferredHolder<? extends Enchantment, ? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
