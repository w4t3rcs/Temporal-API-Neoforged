package com.temporal.api.core.event.data.equipment;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;

public final class EquipmentDescriptionContainer {
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> HUMANOID_EQUIPMENT = new HashMap<>();

    private EquipmentDescriptionContainer() {
    }
}
