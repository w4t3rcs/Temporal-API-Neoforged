package com.temporal.api.core.event.data.equipment;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.temporal.api.core.event.data.equipment.EquipmentDescriptionContainer.*;

public class EquipmentAssetProviderImpl extends EquipmentAssetProvider {
    protected final PackOutput.PathProvider pathProvider;

    public EquipmentAssetProviderImpl(PackOutput packOutput) {
        super(packOutput);
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipments = Stream.of(HUMANOID_EQUIPMENT.entrySet(), HUMANOID_WINGS_EQUIPMENT.entrySet(), HORSE_EQUIPMENT.entrySet(), HUMANOID_AND_HORSE_EQUIPMENT.entrySet(), WOLF_EQUIPMENT.entrySet())
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return DataProvider.saveAll(output, EquipmentClientInfo.CODEC, this.pathProvider::json, equipments);
    }
}
