package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.metadata.annotation.ArmorAsset;
import com.temporal.api.core.event.data.equipment.EquipmentDescriptionContainer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.lang.reflect.Field;

public class ArmorAssetStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(ArmorAsset.class)) {
            field.setAccessible(true);
            ArmorMaterial armorMaterial = (ArmorMaterial) field.get(object);
            ResourceKey<EquipmentAsset> equipmentAsset = armorMaterial.assetId();
            ArmorAsset annotation = field.getAnnotation(ArmorAsset.class);
            switch (annotation.type()) {
                case HUMANOID -> EquipmentDescriptionContainer.HUMANOID_EQUIPMENT.put(equipmentAsset, EquipmentClientInfo.builder()
                        .addHumanoidLayers(IOHelper.createResourceLocation(equipmentAsset
                                .location()
                                .getPath()))
                        .build());
            }
        }
    }
}
