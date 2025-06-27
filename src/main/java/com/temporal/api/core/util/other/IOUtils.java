package com.temporal.api.core.util.other;

import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IOUtils {
    private IOUtils() {
    }

    public static Set<Class<?>> getAllClasses(String modId, Class<?> dependencyClass, Class<? extends Annotation> annotationClass) {
        AtomicReference<Set<Class<?>>> classes = new AtomicReference<>(new HashSet<>());
        ModList.get()
                .getMods()
                .stream()
                .filter(modInfo -> modInfo.getModId().equals(modId))
                .findFirst()
                .ifPresent(mod -> classes.set(mod.getOwningFile()
                        .getFile()
                        .getScanResult()
                        .getAnnotations()
                        .stream()
                        .filter(annotation -> annotation.annotationType().equals(Type.getType(annotationClass)))
                        .map(ModFileScanData.AnnotationData::clazz)
                        .map(clazz -> IOUtils.forType(clazz, dependencyClass))
                        .collect(Collectors.toSet())));
        return classes.get();
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

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> tryCast(Object o) {
        try {
            T casted = (T) o;
            return Optional.of(casted);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
