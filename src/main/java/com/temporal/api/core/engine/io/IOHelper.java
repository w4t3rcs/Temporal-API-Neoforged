package com.temporal.api.core.engine.io;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOHelper {
    public static <T> T createNullObject(Class<? extends T> clazz) {
        try {
            Constructor<?>[] constructors = clazz.getConstructors();
            Arrays.sort(constructors, Comparator.comparingInt(Constructor::getParameterCount));
            Constructor<?> constructor = constructors[0];
            int parameterCount = constructor.getParameterCount();
            if (parameterCount == 0) {
                return (T) constructor.newInstance();
            } else {
                Object[] nulls = new Object[parameterCount];
                Arrays.fill(nulls, null);
                return (T) constructor.newInstance(nulls);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getInstanceFromClassName(String name, Class<?> dependencyClass, Class<?>[] argumentTypes, Object... arguments) {
        try {
            Class<?> clazz = forName(name, dependencyClass);
            Constructor<?> constructor = clazz.getDeclaredConstructor(argumentTypes);
            return constructor.newInstance(arguments);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static <T> @NotNull Stream<TagKey<T>> getTagKeyStream(String key, Class<?> tagClassHolder) {
        return IOHelper.<T>getTagKeyStream(tagClassHolder).filter(resourceKey -> key.equals(resourceKey.location().getPath()));
    }

    public static <T> @NotNull Stream<TagKey<T>> getTagKeyStream(Class<?> tagClassHolder) {
        return Arrays.stream(tagClassHolder.getDeclaredFields())
                .map(field -> {
                    try {
                        return field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(obj -> obj instanceof TagKey)
                .map(object -> (TagKey<T>) object);
    }
}
