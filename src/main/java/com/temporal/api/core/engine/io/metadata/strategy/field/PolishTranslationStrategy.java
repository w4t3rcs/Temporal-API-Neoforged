package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.PolishTranslation;
import com.temporal.api.core.event.data.language.PolishProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class PolishTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(PolishTranslation.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            PolishTranslation translation = field.getDeclaredAnnotation(PolishTranslation.class);
            switch (translation.type()) {
                case OTHER -> PolishProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> PolishProvider.ITEM_TRANSLATIONS.put((RegistryObject<? extends Item>) registryObject, translation.value());
                case BLOCK -> PolishProvider.BLOCK_TRANSLATIONS.put((RegistryObject<? extends Block>) registryObject, translation.value());
                case ENTITY -> PolishProvider.ENTITY_TRANSLATIONS.put((RegistryObject<? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> PolishProvider.EFFECT_TRANSLATIONS.put((RegistryObject<? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> PolishProvider.ENCHANTMENT_TRANSLATIONS.put((RegistryObject<? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
