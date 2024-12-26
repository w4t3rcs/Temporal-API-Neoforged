package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class PotionFactory implements TypedFactory<Potion> {
    public static final DeferredRegister<Potion> POTIONS = IOHelper.createRegistry(Registries.POTION);

    public RegistryObject<Potion> create(String name, MobEffectInstance mobEffectInstance) {
        return create(name, () -> new Potion(name, mobEffectInstance));
    }

    public RegistryObject<Potion> create(String name, Holder<MobEffect> effect, int duration) {
        return create(name, () -> new Potion(name, new MobEffectInstance(effect, duration)));
    }

    @Override
    public RegistryObject<Potion> create(String name, Supplier<Potion> potionSupplier) {
        return POTIONS.register(name, potionSupplier);
    }

    @Override
    public RegistryObject<? extends Potion> createTyped(String name, Supplier<? extends Potion> tSupplier) {
        return POTIONS.register(name, tSupplier);
    }

    @Override
    public void register() {
        POTIONS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
