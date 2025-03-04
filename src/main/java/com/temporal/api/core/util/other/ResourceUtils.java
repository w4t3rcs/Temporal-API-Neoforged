package com.temporal.api.core.util.other;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public final class ResourceUtils {
    private ResourceUtils() {
    }

    public static <T> @NotNull Stream<ResourceKey<T>> getResourceKeyStream(Class<?> resourceClassHolder) {
        return IOUtils.getFieldStream(resourceClassHolder, o -> o instanceof ResourceKey, o -> (ResourceKey<T>) o);
    }

    public static <T> @NotNull Stream<TagKey<T>> getTagKeyStream(Class<?> tagClassHolder) {
        return IOUtils.getFieldStream(tagClassHolder, o -> o instanceof TagKey, o -> (TagKey<T>) o);
    }

    public static ResourceLocation createNamespacedResourceLocation(String name) {
        String[] split = name.split(":");
        return ResourceLocation.fromNamespaceAndPath(split[0], split[1]);
    }

    public static ResourceLocation createResourceLocation(ResourceKey<?> resourceKey) {
        String namespace = resourceKey.location().getNamespace();
        return ResourceLocation.fromNamespaceAndPath(namespace, getResourceId(resourceKey));
    }

    public static ResourceLocation createResourceLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(IOLayer.NEO_MOD.getModId(), name);
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
