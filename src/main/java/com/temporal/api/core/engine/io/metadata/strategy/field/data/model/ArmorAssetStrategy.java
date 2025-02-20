package com.temporal.api.core.engine.io.metadata.strategy.field.data.model;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.data.model.ArmorAsset;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.equipment.EquipmentDescriptionContainer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.lang.reflect.Field;
import java.util.Optional;

public class ArmorAssetStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ArmorAsset.class)) {
            field.setAccessible(true);
            ArmorMaterial armorMaterial = (ArmorMaterial) field.get(object);
            ResourceKey<EquipmentAsset> equipmentAsset = armorMaterial.assetId();
            ArmorAsset annotation = field.getDeclaredAnnotation(ArmorAsset.class);
            switch (annotation.type()) {
                case HUMANOID -> EquipmentDescriptionContainer.HUMANOID_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addHumanoidLayers(IOHelper.createResourceLocation(equipmentAsset))
                        .build());
                case HUMANOID_WINGS -> EquipmentDescriptionContainer.HUMANOID_WINGS_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addLayers(EquipmentClientInfo.LayerType.WINGS, new EquipmentClientInfo.Layer(IOHelper.createResourceLocation(equipmentAsset), Optional.empty(), true))
                        .build());
                case HORSE -> EquipmentDescriptionContainer.HORSE_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(IOHelper.createResourceLocation(equipmentAsset), false))
                        .build());
                case HUMANOID_AND_HORSE -> EquipmentDescriptionContainer.HUMANOID_AND_HORSE_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addHumanoidLayers(IOHelper.createResourceLocation(equipmentAsset))
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(IOHelper.createResourceLocation(equipmentAsset), false))
                        .build());
                case WOLF -> EquipmentDescriptionContainer.WOLF_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addLayers(EquipmentClientInfo.LayerType.WOLF_BODY, EquipmentClientInfo.Layer.onlyIfDyed(IOHelper.createResourceLocation(equipmentAsset), false))
                        .build());
            }
        }
    }
}
