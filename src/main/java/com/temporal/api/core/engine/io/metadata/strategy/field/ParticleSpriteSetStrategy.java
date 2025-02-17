package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.ParticleSpriteSet;
import com.temporal.api.core.event.data.particle.ApiParticleProvider;
import com.temporal.api.core.event.data.particle.ParticleSprite;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;

import java.lang.reflect.Field;

public class ParticleSpriteSetStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ParticleSpriteSet.class)) {
            field.setAccessible(true);
            Holder<ParticleType<?>> particleType = (Holder<ParticleType<?>>) field.get(object);
            ParticleSpriteSet annotation = field.getAnnotation(ParticleSpriteSet.class);
            ParticleSprite particleSprite = new ParticleSprite(annotation.id(), annotation.count(), annotation.reverse());
            ApiParticleProvider.PARTICLE_SPRITES.put(particleType, particleSprite);
        }
    }
}
