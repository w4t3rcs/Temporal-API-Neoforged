package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SoundEventFactory implements ObjectFactory<SoundEvent> {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = InjectionContext.getFromInstance("sound_events");

    public Holder<SoundEvent> create(String name) {
        return create(name, () -> SoundEvent.createVariableRangeEvent(ResourceUtils.createResourceLocation(name)));
    }

    @Override
    public Holder<SoundEvent> create(String name, Supplier<SoundEvent> soundEventSupplier) {
        return SOUND_EVENTS.register(name, soundEventSupplier);
    }

    @Override
    public void register() {
        SOUND_EVENTS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
