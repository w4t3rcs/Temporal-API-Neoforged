package com.temporal.api.core.event.data.music.instrument;

import com.temporal.api.core.collection.TemporalArrayDeque;
import com.temporal.api.core.util.other.RegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;

import java.util.Queue;

public class ApiInstrumentProvider implements InstrumentProvider {
    public static final Queue<InstrumentDescriptionHolder> INSTRUMENTS = new TemporalArrayDeque<>();

    @Override
    public void registerInstrument(BootstrapContext<Instrument> context) {
        INSTRUMENTS.forEach(description -> {
            ResourceKey<Instrument> instrument = description.instrument();
            SoundEvent soundEvent = RegistryUtils.getSoundEventById(description.soundEvent());
            context.register(instrument, new Instrument(Holder.direct(soundEvent), (int)description.useDuration(), description.range()));
        });
    }

    public static void bootstrap(BootstrapContext<Instrument> context) {
        InstrumentProvider provider = new ApiInstrumentProvider();
        provider.registerInstrument(context);
    }
}
