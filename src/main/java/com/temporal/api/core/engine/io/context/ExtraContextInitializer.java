package com.temporal.api.core.engine.io.context;

import com.temporal.api.core.engine.io.metadata.DefaultAnnotationExecutor;
import com.temporal.api.core.event.data.ApiDataGenerator;
import com.temporal.api.core.registry.factory.common.*;
import com.temporal.api.core.tag.factory.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ExtraContextInitializer implements ContextInitializer {
    public void initialize() {
        Context context = InjectionContext.getInstance();
        context.putObject(IEventBus.class, FMLJavaModLoadingContext.get().getModEventBus());

        context.putObject(new DefaultAnnotationExecutor());
        context.putObject(new ApiDataGenerator());

        context.putObject(new ItemFactory());
        context.putObject(new BlockFactory());
        context.putObject(new BiomeFactory());
        context.putObject(new CreativeModeTabFactory());
        context.putObject(new EffectFactory());
        context.putObject(new EntityTypeFactory());
        context.putObject(new BlockEntityTypeFactory());
        context.putObject(new PaintingFactory());
        context.putObject(new ParticleFactory());
        context.putObject(new PoiTypeFactory());
        context.putObject(new PotionFactory());
        context.putObject(new RecipeSerializerFactory());
        context.putObject(new SoundEventFactory());
        context.putObject(new VillagerProfessionFactory());
        context.putObject(new ArmorMaterialFactory());

        context.putObject(new ItemTagFactory());
        context.putObject(new BlockTagFactory());
        context.putObject(new BannerPatternTagFactory());
        context.putObject(new BiomeTagFactory());
        context.putObject(new EntityTypeTagFactory());
        context.putObject(new FluidTagFactory());
        context.putObject(new StructureTagFactory());
    }
}
