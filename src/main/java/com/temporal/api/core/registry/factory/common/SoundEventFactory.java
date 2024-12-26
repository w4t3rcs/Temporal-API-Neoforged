package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SoundEventFactory implements TypedFactory<SoundEvent> {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = IOHelper.createRegistry(Registries.SOUND_EVENT);

    public RegistryObject<SoundEvent> create(String name) {
        return create(name, () -> SoundEvent.createVariableRangeEvent(IOHelper.createResourceLocation(name)));
    }

    @Override
    public RegistryObject<SoundEvent> create(String name, Supplier<SoundEvent> soundEventSupplier) {
        return SOUND_EVENTS.register(name, soundEventSupplier);
    }

    @Override
    public RegistryObject<? extends SoundEvent> createTyped(String name, Supplier<? extends SoundEvent> tSupplier) {
        return SOUND_EVENTS.register(name, tSupplier);
    }

    @Override
    public void register() {
        SOUND_EVENTS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
