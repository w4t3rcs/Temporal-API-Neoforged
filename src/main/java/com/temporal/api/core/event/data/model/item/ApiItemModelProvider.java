package com.temporal.api.core.event.data.model.item;

import com.temporal.api.core.engine.IOLayer;
import com.temporal.api.core.engine.io.IOHelper;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

@SuppressWarnings({"deprecated", "removal"})
public abstract class ApiItemModelProvider extends ItemModelProvider {
    public ApiItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IOLayer.NEO_MOD.getModId(), existingFileHelper);
    }

    protected ItemModelBuilder registerItem(DeferredItem<Item> itemRegistryObject, String parent) {
        String path = "item/" + itemRegistryObject.getId().getPath();
        return this.withExistingParent(itemRegistryObject.getId().getPath(), ResourceLocation.withDefaultNamespace(parent))
                .texture("layer0", IOHelper.createResourceLocation(path));
    }
}
