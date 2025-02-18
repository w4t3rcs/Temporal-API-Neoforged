package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuTypeFactory implements ObjectFactory<MenuType<?>> {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = IOHelper.createRegistry(Registries.MENU);

    @Override
    public Holder<MenuType<?>> create(String name, Supplier<MenuType<?>> menuTypeSupplier) {
        return MENU_TYPES.register(name, menuTypeSupplier);
    }

    @Override
    public void register() {
        MENU_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
