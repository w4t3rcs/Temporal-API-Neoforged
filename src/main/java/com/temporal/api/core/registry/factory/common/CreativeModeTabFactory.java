package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class CreativeModeTabFactory implements ObjectFactory<CreativeModeTab> {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = IOHelper.createRegistry(Registries.CREATIVE_MODE_TAB);

    public Holder<CreativeModeTab> create(String name, Item icon, String translationId, Item... items) {
        return create(name, () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable(translationId))
                .displayItems((displayParameters, output) -> {
                    for (Item item : items) {
                        output.accept(item);
                    }
                }).build());
    }

    public Holder<CreativeModeTab> create(String name, Item icon, String translationId, Collection<ItemStack> items) {
        return create(name, () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(icon))
                .title(Component.translatable(translationId))
                .displayItems((displayParameters, output) -> output.acceptAll(items))
                .build());
    }

    @Override
    public Holder<CreativeModeTab> create(String name, Supplier<CreativeModeTab> creativeModeTabSupplier) {
        return CREATIVE_MODE_TABS.register(name, creativeModeTabSupplier);
    }

    @Override
    public void register() {
        CREATIVE_MODE_TABS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
