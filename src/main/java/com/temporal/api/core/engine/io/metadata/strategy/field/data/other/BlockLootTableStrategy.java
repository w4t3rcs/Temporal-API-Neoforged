package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.engine.io.metadata.annotation.data.other.BlockLootTable;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.loot.BlockLootTableProvider;
import com.temporal.api.core.exception.NotFoundException;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;

public class BlockLootTableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockLootTable.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            BlockLootTable blockLootTable = field.getDeclaredAnnotation(BlockLootTable.class);
            switch (blockLootTable.value()) {
                case SELF -> BlockLootTableProvider.SELF.add(registryObject);
                case SILK_TOUCH -> BlockLootTableProvider.SILK_TOUCH.add(registryObject);
                case POTTED_CONTENT -> BlockLootTableProvider.POTTED_CONTENT.add(registryObject);
                case OTHER -> {
                    String otherId = blockLootTable.itemId();
                    Holder<Item> itemRegistry = ItemFactory.ITEMS.getEntries()
                            .stream()
                            .filter(item -> item.getId().getPath().equals(otherId))
                            .findAny()
                            .orElseThrow(NotFoundException::new);
                    BlockLootTableProvider.OTHER.put(registryObject, itemRegistry);
                }
            }
        }
    }
}
