package com.temporal.api.core.engine.io;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.registries.DeferredRegister;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

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
        return ResourceLocation.fromNamespaceAndPath(IOLayer.FORGE_MOD.getModId(), name);
    }

    public static <T> DeferredRegister<T> createRegistry(ResourceKey<Registry<T>> registry) {
        return DeferredRegister.create(registry, IOLayer.FORGE_MOD.getModId());
    }
}
