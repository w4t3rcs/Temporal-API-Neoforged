package com.temporal.api.core.event.data.model.item;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ItemModelProvider extends ApiItemModelProvider {
    public static final List<RegistryObject<Item>> ITEMS = new ArrayList<>();
    public static final List<RegistryObject<Item>> HANDHELD_ITEMS = new ArrayList<>();

    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ITEMS.forEach(item -> this.registerItem(item, "item/generated"));
        HANDHELD_ITEMS.forEach(item -> this.registerItem(item, "item/handheld"));
    }
}
