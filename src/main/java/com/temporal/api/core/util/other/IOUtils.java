package com.temporal.api.core.util.other;

import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IOUtils {
    private IOUtils() {
    }

    public static Set<Class<?>> getAllClasses(Class<?> dependencyClass, Class<? extends Annotation> annotationClass) {
        return ModList.get()
                .getAllScanData()
                .stream()
                .flatMap(modFileScanData -> modFileScanData.getAnnotations()
                        .stream()
                        .filter(annotation -> annotation.annotationType().equals(Type.getType(annotationClass)))
                        .map(ModFileScanData.AnnotationData::clazz)
                        .map(clazz -> IOUtils.forType(clazz, dependencyClass)))
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
}
