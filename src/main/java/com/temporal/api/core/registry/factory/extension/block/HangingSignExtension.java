package com.temporal.api.core.registry.factory.extension.block;

import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.registry.factory.common.BlockFactory;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import com.temporal.api.core.registry.factory.other.BlockPropertiesFactory;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface HangingSignExtension {
    default DeferredBlock<Block> createHangingSign(String name, MapColor color, float strength, WoodType woodType) {
        return createHangingSign(name, BlockPropertiesFactory.wood(), new Item.Properties(), color, strength, woodType);
    }

    default DeferredBlock<Block> createHangingSign(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties, MapColor color, float strength, WoodType woodType) {
        BlockFactory factory = InjectionPool.getFromInstance(BlockFactory.class);
        DeferredBlock<Block> ceilingHangingSignBlock = factory.createWithoutItem(name, properties.mapColor(color)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new CeilingHangingSignBlock(woodType, props));
        DeferredBlock<Block> wallHangingSignBlock = factory.createWithoutItem(name.replace("hanging", "wall_hanging"), properties.mapColor(color)
                .forceSolidOn()
                .instrument(NoteBlockInstrument.BASS)
                .noCollission()
                .strength(strength)
                .ignitedByLava(), props -> new WallHangingSignBlock(woodType, props));
        ItemFactory itemFactory = InjectionPool.getFromInstance(ItemFactory.class);
        itemFactory.create(name, itemProperties, props -> new HangingSignItem(ceilingHangingSignBlock.get(), wallHangingSignBlock.get(), props.stacksTo(16)));
        return ceilingHangingSignBlock;
    }
}
