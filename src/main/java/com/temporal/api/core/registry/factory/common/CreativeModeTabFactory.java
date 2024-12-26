package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.function.Supplier;

public class CreativeModeTabFactory implements ObjectFactory<CreativeModeTab> {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = IOHelper.createRegistry(Registries.CREATIVE_MODE_TAB);

    public RegistryObject<CreativeModeTab> create(String name, Item icon, String translationId, Item... items) {
        return create(name, () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable(translationId))
                .displayItems((displayParameters, output) -> {
                    for (Item item : items) {
                        output.accept(item);
                    }
                }).build());
    }

    public RegistryObject<CreativeModeTab> create(String name, Item icon, String translationId, Collection<ItemStack> items) {
        return create(name, () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable(translationId))
                .displayItems((displayParameters, output) -> output.acceptAll(items))
                .build());
    }

    @Override
    public RegistryObject<CreativeModeTab> create(String name, Supplier<CreativeModeTab> creativeModeTabSupplier) {
        return CREATIVE_MODE_TABS.register(name, creativeModeTabSupplier);
    }

    @Override
    public void register() {
        CREATIVE_MODE_TABS.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
