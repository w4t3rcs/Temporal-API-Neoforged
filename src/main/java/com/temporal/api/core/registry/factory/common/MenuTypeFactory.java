package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuTypeFactory implements ObjectFactory<MenuType<?>> {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = InjectionContext.getFromInstance("menu_types");

    public <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> create(String name, IContainerFactory<T> containerFactory) {
        return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(containerFactory));
    }

    public <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> create(String name, MenuType.MenuSupplier<T> container, FeatureFlagSet featureFlagSet) {
        return MENU_TYPES.register(name, () -> new MenuType<>(container, featureFlagSet));
    }

    @Override
    public Holder<MenuType<?>> create(String name, Supplier<MenuType<?>> menuTypeSupplier) {
        return MENU_TYPES.register(name, menuTypeSupplier);
    }

    @Override
    public void register() {
        MENU_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
