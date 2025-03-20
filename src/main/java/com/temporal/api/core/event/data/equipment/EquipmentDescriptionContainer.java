package com.temporal.api.core.event.data.equipment;

import com.temporal.api.core.collection.TemporalHashMap;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public final class EquipmentDescriptionContainer {
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> HUMANOID_EQUIPMENT = new TemporalHashMap<>();
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> HUMANOID_WINGS_EQUIPMENT = new TemporalHashMap<>();
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> HORSE_EQUIPMENT = new TemporalHashMap<>();
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> HUMANOID_AND_HORSE_EQUIPMENT = new TemporalHashMap<>();
    public static final Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> WOLF_EQUIPMENT = new TemporalHashMap<>();

    private EquipmentDescriptionContainer() {
    }
}
