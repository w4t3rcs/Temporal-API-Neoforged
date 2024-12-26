package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.GermanTranslation;
import com.temporal.api.core.event.data.language.GermanProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class GermanTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(GermanTranslation.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            GermanTranslation translation = field.getDeclaredAnnotation(GermanTranslation.class);
            switch (translation.type()) {
                case OTHER -> GermanProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> GermanProvider.ITEM_TRANSLATIONS.put((RegistryObject<? extends Item>) registryObject, translation.value());
                case BLOCK -> GermanProvider.BLOCK_TRANSLATIONS.put((RegistryObject<? extends Block>) registryObject, translation.value());
                case ENTITY -> GermanProvider.ENTITY_TRANSLATIONS.put((RegistryObject<? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> GermanProvider.EFFECT_TRANSLATIONS.put((RegistryObject<? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> GermanProvider.ENCHANTMENT_TRANSLATIONS.put((RegistryObject<? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
