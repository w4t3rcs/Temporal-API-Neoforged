package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Supplier;

public class PaintingFactory implements ObjectFactory<PaintingVariant> {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = IOHelper.createRegistry(Registries.PAINTING_VARIANT);

    public Holder<PaintingVariant> create16x16(String name, String title, String author) {
        return create(name, 16, 16, title, author);
    }

    public Holder<PaintingVariant> create16x32(String name, String title, String author) {
        return create(name, 16, 32, title, author);
    }

    public Holder<PaintingVariant> create32x16(String name, String title, String author) {
        return create(name, 32, 16, title, author);
    }

    public Holder<PaintingVariant> create32x32(String name, String title, String author) {
        return create(name, 32, 32, title, author);
    }

    public Holder<PaintingVariant> create(String name, int width, int height, String title, String author) {
        return create(name, () -> new PaintingVariant(width, height, IOHelper.createResourceLocation(name),
                Optional.of(Component.translatable(title)), Optional.of(Component.translatable(author))));
    }

    @Override
    public Holder<PaintingVariant> create(String name, Supplier<PaintingVariant> paintingVariantSupplier) {
        return PAINTING_VARIANTS.register(name, paintingVariantSupplier);
    }

    @Override
    public void register() {
        PAINTING_VARIANTS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
