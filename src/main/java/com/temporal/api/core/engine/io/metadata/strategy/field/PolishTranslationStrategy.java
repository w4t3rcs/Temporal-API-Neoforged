package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.PolishTranslation;
import com.temporal.api.core.event.data.language.PolishProvider;
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
public class PolishTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(PolishTranslation.class)) {
            field.setAccessible(true);
            Object o = field.get(object);
            Holder<?> registryObject;
            if (o instanceof Holder<?>) registryObject = (Holder<?>) o;
            else registryObject = null;
            PolishTranslation translation = field.getDeclaredAnnotation(PolishTranslation.class);
            switch (translation.type()) {
                case OTHER -> {
                    String id;
                    switch (o) {
                        case String stringField -> id = stringField;
                        case Component component -> id = component.getContents().type().id();
                        case null, default -> id = translation.id();
                    }

                    PolishProvider.OTHER_TRANSLATIONS.put(id, translation.value());
                }
                case ITEM -> PolishProvider.ITEM_TRANSLATIONS.put((DeferredItem<? extends Item>) registryObject, translation.value());
                case BLOCK -> PolishProvider.BLOCK_TRANSLATIONS.put((DeferredBlock<? extends Block>) registryObject, translation.value());
                case ENTITY -> PolishProvider.ENTITY_TRANSLATIONS.put((DeferredHolder<? extends EntityType<?>, ? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> PolishProvider.EFFECT_TRANSLATIONS.put((DeferredHolder<? extends MobEffect, ? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> PolishProvider.ENCHANTMENT_TRANSLATIONS.put((DeferredHolder<? extends Enchantment, ? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
