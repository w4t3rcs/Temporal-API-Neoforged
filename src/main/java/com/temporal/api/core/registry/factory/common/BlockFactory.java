package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockFactory implements ObjectFactory<Block> {
    private final DeferredRegister.Blocks blocks;
    private final ItemFactory itemFactory;

    public BlockFactory() {
        this(InjectionContext.getFromInstance("blocks"));
    }

    public BlockFactory(DeferredRegister.Blocks blocks) {
        this(blocks, InjectionContext.getFromInstance(ItemFactory.class));
    }

    public BlockFactory(DeferredRegister.Blocks blocks, ItemFactory itemFactory) {
        this.blocks = blocks;
        this.itemFactory = itemFactory;
    }

    public DeferredBlock<Block> createWithoutItem(String name, BlockBehaviour.Properties properties) {
        return blocks.registerBlock(name, Block::new, properties);
    }

    public DeferredBlock<Block> createWithoutItem(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, ? extends Block> function) {
        return blocks.registerBlock(name, function, properties);
    }

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties) {
        return create(name, properties, new Item.Properties());
    }

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        return create(name, properties, Block::new, itemProperties);
    }

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, ? extends Block> function) {
        return create(name, properties, function, new Item.Properties());
    }

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, ? extends Block> function, Item.Properties itemProperties) {
        DeferredBlock<Block> block = blocks.registerBlock(name, function, properties);
        this.itemFactory.create(name, itemProperties, props -> new BlockItem(block.value(), props));
        return block;
    }

    @Override
    public DeferredBlock<Block> create(String name, Supplier<Block> blockSupplier) {
        return create(name, blockSupplier, new Item.Properties());
    }

    public DeferredBlock<Block> create(String name, Supplier<Block> blockSupplier, Item.Properties itemProperties) {
        DeferredBlock<Block> block = blocks.register(name, blockSupplier);
        this.itemFactory.create(name, itemProperties, props -> new BlockItem(block.value(), props));
        return block;
    }

    @Override
    public void register() {
        blocks.register(InjectionContext.getFromInstance(IEventBus.class));
    }

    @Override
    public DeferredRegister<Block> getRegistry() {
        return blocks;
    }
}
