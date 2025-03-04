package com.temporal.api.core.event.data.preparer.resource.equipment;

import com.temporal.api.core.event.data.preparer.DynamicPreparer;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.*;

public final class EquipmentResourceDynamicPreparer implements DynamicPreparer {
    public static final Set<Class<?>> RESOURCE_CONTAINERS = new HashSet<>(List.of(EquipmentAssets.class));
    public static final Map<String, ResourceKey<EquipmentAsset>> EQUIPMENT_ASSET_RESOURCES = new HashMap<>();

    @Override
    public void prepare() {
        RESOURCE_CONTAINERS.stream()
                .flatMap(ResourceUtils::<EquipmentAsset>getResourceKeyStream)
                .forEach(resource -> {
                    String path = resource.location().getPath();
                    EQUIPMENT_ASSET_RESOURCES.putIfAbsent(path, resource);
                });
    }
}
