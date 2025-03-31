package com.temporal.api.core.engine.io.metadata.strategy.field.data.music;

import com.temporal.api.core.engine.io.metadata.annotation.data.music.InstrumentDescription;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.music.instrument.ApiInstrumentProvider;
import com.temporal.api.core.event.data.music.instrument.InstrumentDescriptionHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Instrument;

import java.lang.reflect.Field;

public class InstrumentDescriptionStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(InstrumentDescription.class)) {
            field.setAccessible(true);
            InstrumentDescription annotation = field.getDeclaredAnnotation(InstrumentDescription.class);
            ResourceKey<Instrument> instrument = (ResourceKey<Instrument>) field.get(object);
            InstrumentDescriptionHolder descriptionHolder = new InstrumentDescriptionHolder(instrument, annotation.soundEvent(), annotation.useDuration(), annotation.range());
            ApiInstrumentProvider.INSTRUMENTS.add(descriptionHolder);
        }
    }
}
