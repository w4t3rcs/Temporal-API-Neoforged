package com.temporal.api.core.engine.io.metadata.strategy.type.data.defined;

import com.temporal.api.core.engine.io.metadata.annotation.data.defined.DefinedEnchantmentEntityEffect;
import com.temporal.api.core.engine.io.metadata.strategy.type.ClassAnnotationStrategy;
import com.temporal.api.core.event.data.enchantment.ApiEnchantmentProvider;
import com.temporal.api.core.event.data.enchantment.EnchantmentEntityEffectHolder;

import java.lang.reflect.Constructor;

public class DefinedEnchantmentEntityEffectStrategy implements ClassAnnotationStrategy {
    @Override
    public void execute(Class<?> clazz, Object object) throws Exception {
        if (clazz.isAnnotationPresent(DefinedEnchantmentEntityEffect.class)) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            EnchantmentEntityEffectHolder effectHolder = (EnchantmentEntityEffectHolder) constructor.newInstance();
            ApiEnchantmentProvider.ENTITY_EFFECTS.put(effectHolder.getKey(), effectHolder);
        }
    }
}
