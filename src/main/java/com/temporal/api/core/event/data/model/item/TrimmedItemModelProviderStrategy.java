package com.temporal.api.core.event.data.model.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.*;

public class TrimmedItemModelProviderStrategy implements ItemModelProviderStrategy {
    public static final Map<String, ResourceKey<EquipmentAsset>> ASSETS = new HashMap<>();
    public static final Queue<ItemModelGenerators.TrimMaterialData> TRIM_MATERIAL_MODELS = new ArrayDeque<>();

    @Override
    public void registerItemModel(DeferredItem<?> itemRegistry, ItemModelGenerators itemModels, Object... additionalData) {
        ResourceLocation id = itemRegistry.getId();
        String path = id.getPath();
        String armorType = "";
        if (path.contains("helmet")) armorType = "helmet";
        else if (path.contains("chestplate")) armorType = "chestplate";
        else if (path.contains("leggings")) armorType = "leggings";
        else if (path.contains("boots")) armorType = "boots";
        ResourceKey<EquipmentAsset> asset = ASSETS.entrySet()
                .stream()
                .filter(e -> path.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow();
        this.generateTrimmableItem(itemRegistry.get(), asset, armorType, itemModels);
    }

    private void generateTrimmableItem(Item item, ResourceKey<EquipmentAsset> key, String name, ItemModelGenerators itemModels) {
        ResourceLocation modelLocation = ModelLocationUtils.getModelLocation(item);
        ResourceLocation itemTexture = TextureMapping.getItemTexture(item);
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases = new ArrayList<>(TRIM_MATERIAL_MODELS.size());
        this.fillMinecraftCases(key, name, itemModels, modelLocation, itemTexture, cases);
//        this.fillModCases(key, name, itemModels, modelLocation, itemTexture, cases);
        ModelTemplates.FLAT_ITEM.create(modelLocation, TextureMapping.layer0(itemTexture), itemModels.modelOutput);
        ItemModel.Unbaked plainModel = ItemModelUtils.plainModel(modelLocation);
        itemModels.itemModelOutput.accept(item, ItemModelUtils.select(new TrimMaterialProperty(), plainModel, cases));
    }

    private void fillMinecraftCases(ResourceKey<EquipmentAsset> key, String name, ItemModelGenerators itemModels, ResourceLocation modelLocation, ResourceLocation itemTexture, List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases) {
        ItemModelGenerators.TRIM_MATERIAL_MODELS.forEach(trimMaterialData -> {
            ResourceLocation layer1Location = ResourceLocation.withDefaultNamespace("trims/items/" + name + "_trim_" + trimMaterialData.textureName(key));
            addCase(itemModels, modelLocation, itemTexture, layer1Location, cases, trimMaterialData);
        });
    }

    //todo
    private void fillModCases(ResourceKey<EquipmentAsset> key, String name, ItemModelGenerators itemModels, ResourceLocation modelLocation, ResourceLocation itemTexture, List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases) {
        TRIM_MATERIAL_MODELS.forEach(trimMaterialData -> {
            ResourceLocation layer1Location = ResourceLocation.withDefaultNamespace("trims/items/" + name + "_trim_" + trimMaterialData.textureName(key));
            addCase(itemModels, modelLocation, itemTexture, layer1Location, cases, trimMaterialData);
        });
    }

    private void addCase(ItemModelGenerators itemModels, ResourceLocation modelLocation, ResourceLocation itemTexture, ResourceLocation layer1Location, List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases, ItemModelGenerators.TrimMaterialData trimMaterialData) {
        ResourceLocation trimModelLocation = modelLocation.withSuffix("_" + trimMaterialData.name() + "_trim");
        itemModels.generateLayeredItem(trimModelLocation, itemTexture, layer1Location);
        ItemModel.Unbaked plainTrimModel = ItemModelUtils.plainModel(trimModelLocation);
        cases.add(ItemModelUtils.when(trimMaterialData.materialKey(), plainTrimModel));
    }
}
