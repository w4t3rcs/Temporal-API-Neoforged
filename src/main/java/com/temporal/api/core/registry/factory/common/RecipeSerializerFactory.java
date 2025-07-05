package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeSerializerFactory implements ObjectFactory<RecipeSerializer<?>> {
    private final DeferredRegister<RecipeSerializer<?>> recipeSerializers;

    public RecipeSerializerFactory() {
        this(InjectionPool.getFromInstance("$RecipeSerializers"));
    }

    public RecipeSerializerFactory(DeferredRegister<RecipeSerializer<?>> recipeSerializers) {
        this.recipeSerializers = recipeSerializers;
    }

    public Holder<RecipeSerializer<?>> create(String name, RecipeSerializer<?> recipeSerializer) {
        return this.create(name, () -> recipeSerializer);
    }

    @Override
    public Holder<RecipeSerializer<?>> create(String name, Supplier<RecipeSerializer<?>> recipeSerializerSupplier) {
        return recipeSerializers.register(name, recipeSerializerSupplier);
    }

    @Override
    public DeferredRegister<RecipeSerializer<?>> getRegistry() {
        return recipeSerializers;
    }
}