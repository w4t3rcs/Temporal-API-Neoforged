package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.EnglishTranslation;
import com.temporal.api.core.event.data.language.EnglishProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class EnglishTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(EnglishTranslation.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            EnglishTranslation translation = field.getDeclaredAnnotation(EnglishTranslation.class);
            switch (translation.type()) {
                case OTHER -> EnglishProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> EnglishProvider.ITEM_TRANSLATIONS.put((RegistryObject<? extends Item>) registryObject, translation.value());
                case BLOCK -> EnglishProvider.BLOCK_TRANSLATIONS.put((RegistryObject<? extends Block>) registryObject, translation.value());
                case ENTITY -> EnglishProvider.ENTITY_TRANSLATIONS.put((RegistryObject<? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> EnglishProvider.EFFECT_TRANSLATIONS.put((RegistryObject<? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> EnglishProvider.ENCHANTMENT_TRANSLATIONS.put((RegistryObject<? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
