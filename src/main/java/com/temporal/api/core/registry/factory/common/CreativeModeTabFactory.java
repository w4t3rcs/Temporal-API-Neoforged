package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionPool;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Supplier;

public class CreativeModeTabFactory implements ObjectFactory<CreativeModeTab> {
    private final DeferredRegister<CreativeModeTab> creativeModeTabs;

    public CreativeModeTabFactory() {
        this(InjectionPool.getFromInstance("$CreativeModeTabs"));
    }

    public CreativeModeTabFactory(DeferredRegister<CreativeModeTab> creativeModeTabs) {
        this.creativeModeTabs = creativeModeTabs;
    }

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
        return creativeModeTabs.register(name, creativeModeTabSupplier);
    }

    @Override
    public DeferredRegister<CreativeModeTab> getRegistry() {
        return creativeModeTabs;
    }
}
