package com.temporal.api.core.engine.io.metadata.strategy.field;

import com.temporal.api.core.engine.io.metadata.annotation.BlockLootTable;
import com.temporal.api.core.event.data.loot.BlockLootTableProvider;
import com.temporal.api.core.registry.factory.common.ItemFactory;
import com.temporal.api.core.util.exception.NotFoundException;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class BlockLootTableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(BlockLootTable.class)) {
            field.setAccessible(true);
            RegistryObject<?> registryObject = (RegistryObject<?>) field.get(object);
            BlockLootTable blockLootTable = field.getDeclaredAnnotation(BlockLootTable.class);
            switch (blockLootTable.type()) {
                case SELF -> BlockLootTableProvider.SELF.add((RegistryObject<Block>) registryObject);
                case SILK_TOUCH -> BlockLootTableProvider.SILK_TOUCH.add((RegistryObject<Block>) registryObject);
                case POTTED_CONTENT -> BlockLootTableProvider.POTTED_CONTENT.add((RegistryObject<Block>) registryObject);
                case OTHER -> {
                    String otherId = blockLootTable.itemId();
                    RegistryObject<Item> itemRegistry = ItemFactory.ITEMS.getEntries()
                            .stream()
                            .filter(item -> item.getId().getPath().equals(otherId))
                            .findAny()
                            .orElseThrow(NotFoundException::new);
                    BlockLootTableProvider.OTHER.put((RegistryObject<Block>) registryObject, itemRegistry);
                }
            }
        }
    }
}
