package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.engine.io.metadata.DefaultAnnotationExecutor;
import com.temporal.api.core.event.data.ApiDataGenerator;
import com.temporal.api.core.registry.factory.common.*;

import java.util.List;

public class ExtraContextInitializer implements ContextInitializer {
    @Override
    public void initialize(List<?> externalObjects) {
        Context context = InjectionContext.getInstance();
        context.putObject(new DefaultAnnotationExecutor());
        context.putObject(new ApiDataGenerator());
        context.putObject(new ItemFactory());
        context.putObject(new BlockFactory());
        context.putObject(new BiomeFactory());
        context.putObject(new CreativeModeTabFactory());
        context.putObject(new EffectFactory());
        context.putObject(new EntityTypeFactory());
        context.putObject(new BlockEntityTypeFactory());
        context.putObject(new ParticleFactory());
        context.putObject(new PoiTypeFactory());
        context.putObject(new PotionFactory());
        context.putObject(new RecipeSerializerFactory());
        context.putObject(new SoundEventFactory());
        context.putObject(new VillagerProfessionFactory());
        context.putObject(new LootModifierSerializerFactory());
        context.putObject(new MenuTypeFactory());
    }
}
