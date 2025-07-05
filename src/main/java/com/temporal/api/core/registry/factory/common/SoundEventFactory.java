package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SoundEventFactory implements ObjectFactory<SoundEvent> {
    private final DeferredRegister<SoundEvent> soundEvents;

    public SoundEventFactory() {
        this(InjectionPool.getFromInstance("$SoundEvents"));
    }

    public SoundEventFactory(DeferredRegister<SoundEvent> soundEvents) {
        this.soundEvents = soundEvents;
    }

    public Holder<SoundEvent> create(String name) {
        return create(name, () -> SoundEvent.createVariableRangeEvent(ResourceUtils.createResourceLocation(name)));
    }

    @Override
    public Holder<SoundEvent> create(String name, Supplier<SoundEvent> soundEventSupplier) {
        return soundEvents.register(name, soundEventSupplier);
    }

    @Override
    public DeferredRegister<SoundEvent> getRegistry() {
        return soundEvents;
    }
}
