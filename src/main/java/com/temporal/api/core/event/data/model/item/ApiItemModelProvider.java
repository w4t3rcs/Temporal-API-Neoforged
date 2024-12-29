package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.engine.IOLayer;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public abstract class ApiItemModelProvider extends ItemModelProvider {
    public ApiItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IOLayer.NEO_MOD.getModId(), existingFileHelper);
    }

    protected ItemModelBuilder registerParentItem(DeferredItem<Item> itemRegistryObject, String parent) {
        return this.registerParentItem(itemRegistryObject, parent, "layer0");
    }

    protected ItemModelBuilder registerParentItem(DeferredItem<Item> itemRegistryObject, String parent, String layer) {
        ResourceLocation location = itemRegistryObject.getId();
        return this.withExistingParent(location.toString(), mcLoc(parent))
                .texture(layer, "item/" + location);
    }
}
