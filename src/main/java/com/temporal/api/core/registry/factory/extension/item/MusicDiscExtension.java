package com.temporal.api.core.registry.factory.extension.item;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

public interface MusicDiscExtension {
    default DeferredItem<Item> createMusicDisc(String name, String soundId, Item.Properties properties) {
        ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);
        ResourceKey<JukeboxSong> songResourceKey = ResourceKey.create(Registries.JUKEBOX_SONG, IOHelper.createResourceLocation(soundId));
        return itemFactory.create(name, properties.stacksTo(1)
                .jukeboxPlayable(songResourceKey)
                .rarity(Rarity.RARE), Item::new);
    }
}
