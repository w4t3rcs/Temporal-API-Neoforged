package com.temporal.api.core.event.tab;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import java.util.function.Supplier;

public interface TabAdder {
    void addAllToTab(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tab, Supplier<? extends ItemLike>... registries);

    void addToTab(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tab, Supplier<? extends ItemLike> registryObject);
}
