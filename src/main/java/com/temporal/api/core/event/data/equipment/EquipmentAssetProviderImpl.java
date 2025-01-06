package com.temporal.api.core.event.data.equipment;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class EquipmentAssetProviderImpl extends EquipmentAssetProvider {
    protected final PackOutput.PathProvider pathProvider;

    public EquipmentAssetProviderImpl(PackOutput packOutput)
    {
        super(packOutput);
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output)
    {
        return DataProvider.saveAll(output, EquipmentClientInfo.CODEC, this.pathProvider::json, EquipmentDescriptionContainer.HUMANOID_EQUIPMENT);
    }
}
