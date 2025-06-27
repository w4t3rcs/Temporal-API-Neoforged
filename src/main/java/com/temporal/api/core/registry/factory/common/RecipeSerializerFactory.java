package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeSerializerFactory implements ObjectFactory<RecipeSerializer<?>> {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = InjectionContext.getFromInstance("recipe_serializers");

    public Holder<RecipeSerializer<?>> create(String name, RecipeSerializer<?> recipeSerializer) {
        return this.create(name, () -> recipeSerializer);
    }

    @Override
    public Holder<RecipeSerializer<?>> create(String name, Supplier<RecipeSerializer<?>> recipeSerializerSupplier) {
        return RECIPE_SERIALIZERS.register(name, recipeSerializerSupplier);
    }

    @Override
    public void register() {
        RECIPE_SERIALIZERS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}