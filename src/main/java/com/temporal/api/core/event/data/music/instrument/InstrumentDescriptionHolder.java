package com.temporal.api.core.event.data.music.instrument;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Instrument;

public record InstrumentDescriptionHolder(ResourceKey<Instrument> instrument, String soundEvent, float useDuration, float range) {
}
