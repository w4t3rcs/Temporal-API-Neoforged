package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RecipeSerializerFactory implements ObjectFactory<RecipeSerializer<?>> {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = IOHelper.createRegistry(Registries.RECIPE_SERIALIZER);

    public RegistryObject<RecipeSerializer<?>> create(String name, RecipeSerializer<?> recipeSerializer) {
        return this.create(name, () -> recipeSerializer);
    }

    @Override
    public RegistryObject<RecipeSerializer<?>> create(String name, Supplier<RecipeSerializer<?>> recipeSerializerSupplier) {
        return RECIPE_SERIALIZERS.register(name, recipeSerializerSupplier);
    }

    @Override
    public void register() {
        RECIPE_SERIALIZERS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}