package com.temporal.api.core.event.data.music.instrument;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Instrument;

public interface InstrumentProvider {
    void registerInstrument(BootstrapContext<Instrument> context);
}
