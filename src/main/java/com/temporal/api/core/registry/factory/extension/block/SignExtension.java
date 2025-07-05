package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface SignExtension {
    default DeferredBlock<Block> createSign(String name, float strength, WoodType woodType) {
        return createSign(name, BlockPropertiesFactory.wood(), new Item.Properties(), strength, woodType);
    }

    default DeferredBlock<Block> createSign(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, float strength, WoodType woodType) {
        BlockFactory factory = InjectionPool.getFromInstance(BlockFactory.class);
        DeferredBlock<Block> standingSignBlock = factory.createWithoutItem(name, properties.mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new StandingSignBlock(woodType, props));
        DeferredBlock<Block> wallSignBlock = factory.createWithoutItem(name.replace("sign", "wall_sign"), properties.mapColor(MapColor.WOOD)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new WallSignBlock(woodType, props));
        ItemFactory itemFactory = InjectionPool.getFromInstance(ItemFactory.class);
        itemFactory.create(name, itemProperties, props -> new SignItem(props.stacksTo(16), standingSignBlock.get(), wallSignBlock.get()));
        return standingSignBlock;
    }
}
