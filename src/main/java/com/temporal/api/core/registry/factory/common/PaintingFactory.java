package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

public class PaintingFactory implements TypedFactory<PaintingVariant> {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = IOHelper.createRegistry(Registries.PAINTING_VARIANT);

    public RegistryObject<PaintingVariant> create16x16(String name, String title, String author) {
        return create(name, 16, 16, title, author);
    }

    public RegistryObject<PaintingVariant> create16x32(String name, String title, String author) {
        return create(name, 16, 32, title, author);
    }

    public RegistryObject<PaintingVariant> create32x16(String name, String title, String author) {
        return create(name, 32, 16, title, author);
    }

    public RegistryObject<PaintingVariant> create32x32(String name, String title, String author) {
        return create(name, 32, 32, title, author);
    }

    public RegistryObject<PaintingVariant> create(String name, int width, int height, String title, String author) {
        return create(name, () -> new PaintingVariant(width, height, IOHelper.createResourceLocation(name),
                Optional.of(Component.translatable(title)), Optional.of(Component.translatable(author))));
    }

    @Override
    public RegistryObject<PaintingVariant> create(String name, Supplier<PaintingVariant> paintingVariantSupplier) {
        return PAINTING_VARIANTS.register(name, paintingVariantSupplier);
    }

    @Override
    public RegistryObject<? extends PaintingVariant> createTyped(String name, Supplier<? extends PaintingVariant> tSupplier) {
        return PAINTING_VARIANTS.register(name, tSupplier);
    }

    @Override
    public void register() {
        PAINTING_VARIANTS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
