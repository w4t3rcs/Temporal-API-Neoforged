package com.temporal.api.core.util.other;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Predicate;

public final class RegistryUtils {
    private RegistryUtils() {
    }

    public static DeferredRegister.Items createItemRegistry() {
        return DeferredRegister.createItems(IOLayer.NEO_MOD.getModId());
    }

    public static DeferredRegister.Blocks createBlockRegistry() {
        return DeferredRegister.createBlocks(IOLayer.NEO_MOD.getModId());
    }

    public static <T> DeferredRegister<T> createRegistry(ResourceKey<Registry<T>> registry) {
        return DeferredRegister.create(registry, IOLayer.NEO_MOD.getModId());
    }

    public static SoundEvent getSoundEventById(String id) {
        return getRegistryByCondition(BuiltInRegistries.SOUND_EVENT, soundEvent -> soundEvent.location()
                .getPath()
                .equals(id));
    }

    public static Item getItemById(String id) {
        return getRegistryByCondition(BuiltInRegistries.ITEM, item -> getIdFromItem(item).equals(id));
    }

    public static String getIdFromItem(Item item) {
        return ResourceUtils.getResourceId(Objects.requireNonNull(item.getDefaultInstance()
                .getItemHolder()
                .getKey()));
    }

    public static Block getBlockById(String id) {
        return getRegistryByCondition(BuiltInRegistries.BLOCK, block -> getIdFromBlock(block).equals(id));
    }

    public static String getIdFromBlock(Block block) {
        return ResourceUtils.getResourceId(Objects.requireNonNull(block.defaultBlockState()
                .getBlockHolder()
                .getKey()));
    }

    public static <T> T getRegistryByCondition(Registry<T> registry, Predicate<T> condition) {
        return registry.stream()
                .filter(condition)
                .findFirst()
                .orElseThrow();
    }
}
