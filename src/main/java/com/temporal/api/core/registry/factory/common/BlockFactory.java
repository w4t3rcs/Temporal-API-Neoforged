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

public class BlockFactory implements TypedFactory<Block> {
    public static final DeferredRegister.Blocks BLOCKS = IOHelper.createBlockRegistry();
    private final ItemFactory itemFactory = InjectionContext.getFromInstance(ItemFactory.class);

    public DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties) {
        return create(name, Block::new, properties);
    }

    public DeferredBlock<Block> create(String name, Function<BlockBehaviour.Properties, ? extends Block> function, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> block = BLOCKS.registerBlock(name, function, properties);
        this.itemFactory.createTyped(name, () -> new BlockItem(block.value(), new Item.Properties()));
        return block;
    }

    @Override
    public DeferredBlock<Block> create(String name, Supplier<Block> blockSupplier) {
        DeferredBlock<Block> block = BLOCKS.register(name, blockSupplier);
        this.itemFactory.createTyped(name, () -> new BlockItem(block.value(), new Item.Properties()));
        return block;
    }

    @Override
    public DeferredBlock<? extends Block> createTyped(String name, Supplier<? extends Block> typedBlockSupplier) {
        DeferredBlock<? extends Block> typedBlock = BLOCKS.register(name, typedBlockSupplier);
        this.itemFactory.createTyped(name, () -> new BlockItem(typedBlock.value(), new Item.Properties()));
        return typedBlock;
    }

    @Override
    public void register() {
        BLOCKS.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
