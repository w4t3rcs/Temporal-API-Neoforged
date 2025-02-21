package com.temporal.api.core.event.data.trim.material;

import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.HashMap;
import java.util.Map;

public class ApiTrimMaterialProvider implements TrimMaterialProvider {
    public static final Map<ResourceKey<TrimMaterial>, TrimMaterialDescriptionHolder> TRIM_MATERIALS = new HashMap<>();

    @Override
    public void registerTrimMaterials(BootstrapContext<TrimMaterial> context) {
        TRIM_MATERIALS.forEach((trimMaterial, description) -> {
            String assetName = IOHelper.getResourceId(trimMaterial);
            Item ingredient = IOHelper.getItemById(description.itemId());
            String descriptionId = Util.makeDescriptionId("trim_material", trimMaterial.location());
            TextColor textColor = TextColor.parseColor(description.color()).getOrThrow();
            Style style = Style.EMPTY.withColor(textColor);
            MutableComponent component = Component.translatable(descriptionId).withStyle(style);
            context.register(trimMaterial, TrimMaterial.create(assetName, ingredient, component, Map.of()));
        });
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        TrimMaterialProvider provider = new ApiTrimMaterialProvider();
        provider.registerTrimMaterials(context);
    }
}
