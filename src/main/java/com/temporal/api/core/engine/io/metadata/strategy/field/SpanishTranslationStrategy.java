package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.SpanishTranslation;
import com.temporal.api.core.event.data.language.SpanishProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class SpanishTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SpanishTranslation.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            SpanishTranslation translation = field.getDeclaredAnnotation(SpanishTranslation.class);
            switch (translation.type()) {
                case OTHER -> SpanishProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> SpanishProvider.ITEM_TRANSLATIONS.put((RegistryObject<? extends Item>) registryObject, translation.value());
                case BLOCK -> SpanishProvider.BLOCK_TRANSLATIONS.put((RegistryObject<? extends Block>) registryObject, translation.value());
                case ENTITY -> SpanishProvider.ENTITY_TRANSLATIONS.put((RegistryObject<? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> SpanishProvider.EFFECT_TRANSLATIONS.put((RegistryObject<? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> SpanishProvider.ENCHANTMENT_TRANSLATIONS.put((RegistryObject<? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
