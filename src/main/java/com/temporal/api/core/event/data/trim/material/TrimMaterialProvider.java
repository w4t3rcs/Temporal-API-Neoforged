package com.temporal.api.core.event.data.trim.material;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public interface TrimMaterialProvider {
    void registerTrimMaterials(BootstrapContext<TrimMaterial> context);
}
