package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.engine.io.metadata.annotation.data.other.SoundDescription;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.sound.ApiSoundProvider;
import com.temporal.api.core.event.data.sound.SoundGenerationDescription;
import com.temporal.api.core.event.data.sound.SoundHolder;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class SoundDescriptionStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SoundDescription.class)) {
            field.setAccessible(true);
            SoundDescription annotation = field.getDeclaredAnnotation(SoundDescription.class);
            Holder<SoundEvent> soundEvent = (Holder<SoundEvent>) field.get(object);
            SoundGenerationDescription description = new SoundGenerationDescription(soundEvent, annotation.replace());
            List<SoundHolder> soundHolders = Arrays.stream(annotation.value())
                    .map(sound -> new SoundHolder(sound.fileName(), sound.type(),
                            sound.volume(), sound.pitch(),
                            sound.weight(), sound.attenuationDistance(),
                            sound.stream(), sound.preload()))
                    .toList();
            ApiSoundProvider.SOUNDS.put(description, soundHolders);
        }
    }
}
