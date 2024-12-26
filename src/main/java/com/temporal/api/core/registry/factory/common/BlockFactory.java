package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
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
    public static final DeferredRegister.Blocks BLOCKS = IOHelper.createBlockRegistry();
    private final ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties) {
        return create(name, properties, Block::new);
    }

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, ? extends Block> function) {
        DeferredBlock<Block> block = BLOCKS.registerBlock(name, function, properties);
        this.itemFactory.create(name, new Item.Properties(), props -> new BlockItem(block.value(), props));
        return block;
    }

    @Override
    public DeferredBlock<Block> create(String name, Supplier<Block> blockSupplier) {
        DeferredBlock<Block> block = BLOCKS.register(name, blockSupplier);
        this.itemFactory.create(name, new Item.Properties(), props -> new BlockItem(block.value(), props));
        return block;
    }

    @Override
    public void register() {
        BLOCKS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
