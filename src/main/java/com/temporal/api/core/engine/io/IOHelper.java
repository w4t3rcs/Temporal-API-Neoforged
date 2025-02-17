package com.temporal.api.core.engine.io;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOHelper {
    public static Set<Class<?>> getAllClasses(Class<?> dependencyClass, Class<? extends Annotation> annotationClass) {
        return ModList.get()
                .getAllScanData()
                .stream()
                .flatMap(modFileScanData -> modFileScanData.getAnnotations()
                        .stream()
                        .filter(annotation -> annotation.annotationType().equals(Type.getType(annotationClass)))
                        .map(ModFileScanData.AnnotationData::clazz)
                        .map(clazz -> IOHelper.forType(clazz, dependencyClass)))
                .collect(Collectors.toSet());
    }

    public static Class<?> forType(Type type, Class<?> dependencyClass) {
        return forName(type.getClassName(), dependencyClass);
    }

    public static Class<?> forName(String name, Class<?> dependencyClass) {
        try {
            return Class.forName(name, false, dependencyClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ResourceKey<T> createResourceKey(ResourceKey<Registry<T>> registry, String name) {
        return ResourceKey.create(registry, createResourceLocation(name));
    }

    public static ResourceLocation createNamespacedResourceLocation(String name) {
        String[] split = name.split(":");
        return ResourceLocation.fromNamespaceAndPath(split[0], split[1]);
    }

    public static ResourceLocation createResourceLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(IOLayer.NEO_MOD.getModId(), name);
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

    public static <T> HolderSet<T> createHolderSetFromIds(List<String> ids, Function<String, T> mapper) {
        if (ids == null || ids.isEmpty()) return HolderSet.empty();
        return HolderSet.direct(ids.stream()
                .map(mapper)
                .map(Holder::direct)
                .toList());
    }

    public static <T> @NotNull Stream<ResourceKey<T>> getResourceKeyStream(Class<?> resourceClassHolder) {
        return getFieldStream(resourceClassHolder, o -> o instanceof ResourceKey, o -> (ResourceKey<T>) o);
    }

    public static <T> @NotNull Stream<TagKey<T>> getTagKeyStream(Class<?> tagClassHolder) {
        return getFieldStream(tagClassHolder, o -> o instanceof TagKey, o -> (TagKey<T>) o);
    }

    public static <T> @NotNull Stream<T> getFieldStream(Class<?> container, Predicate<Object> filteringPredicate, Function<Object, T> mapper) {
        return Arrays.stream(container.getDeclaredFields())
                .map(field -> {
                    try {
                        return field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(filteringPredicate)
                .map(mapper);
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
        return getResourceId(Objects.requireNonNull(item.getDefaultInstance()
                .getItemHolder()
                .getKey()));
    }

    public static Block getBlockById(String id) {
        return getRegistryByCondition(BuiltInRegistries.BLOCK, block -> getIdFromBlock(block).equals(id));
    }

    public static String getIdFromBlock(Block block) {
        return getResourceId(Objects.requireNonNull(block.defaultBlockState()
                .getBlockHolder()
                .getKey()));
    }

    public static <T> T getRegistryByCondition(Registry<T> registry, Predicate<T> condition) {
        return registry.stream()
                .filter(condition)
                .findFirst()
                .orElseThrow();
    }

    public static String getResourceId(ResourceKey<?> resourceKey) {
        if (resourceKey == null) throw new RuntimeException("ResourceKey is null");
        return resourceKey.location().getPath();
    }

    public static String getResourceId(TagKey<?> tagKey) {
        if (tagKey == null) throw new RuntimeException("ResourceKey is null");
        return tagKey.location().getPath();
    }
}
