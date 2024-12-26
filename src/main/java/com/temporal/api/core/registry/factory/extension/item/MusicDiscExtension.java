package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unchecked")
public interface MusicDiscExtension {
    default RegistryObject<Item> createMusicDisc(String name, String soundId, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getInstance().getObject(ItemFactory.class);
        ResourceKey<JukeboxSong> songResourceKey = ResourceKey.create(Registries.JUKEBOX_SONG, IOHelper.createResourceLocation(soundId));
        return (RegistryObject<Item>) itemFactory.createTyped(name, () -> new Item(properties.stacksTo(1)
                .jukeboxPlayable(songResourceKey)
                .rarity(Rarity.RARE)));
    }
}