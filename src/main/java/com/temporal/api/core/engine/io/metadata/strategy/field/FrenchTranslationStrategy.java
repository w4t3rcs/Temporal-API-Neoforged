package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.FrenchTranslation;
import com.temporal.api.core.event.data.language.FrenchProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class FrenchTranslationStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(FrenchTranslation.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            FrenchTranslation translation = field.getDeclaredAnnotation(FrenchTranslation.class);
            switch (translation.type()) {
                case OTHER -> FrenchProvider.OTHER_TRANSLATIONS.put(translation.id(), translation.value());
                case ITEM -> FrenchProvider.ITEM_TRANSLATIONS.put((RegistryObject<? extends Item>) registryObject, translation.value());
                case BLOCK -> FrenchProvider.BLOCK_TRANSLATIONS.put((RegistryObject<? extends Block>) registryObject, translation.value());
                case ENTITY -> FrenchProvider.ENTITY_TRANSLATIONS.put((RegistryObject<? extends EntityType<?>>) registryObject, translation.value());
                case EFFECT -> FrenchProvider.EFFECT_TRANSLATIONS.put((RegistryObject<? extends MobEffect>) registryObject, translation.value());
                case ENCHANTMENT -> FrenchProvider.ENCHANTMENT_TRANSLATIONS.put((RegistryObject<? extends Enchantment>) registryObject, translation.value());
            }
        }
    }
}
