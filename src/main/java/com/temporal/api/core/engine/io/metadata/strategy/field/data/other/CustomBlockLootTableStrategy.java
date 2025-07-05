package com.temporal.api.core.engine.io.metadata.strategy.field.data.other;

import com.temporal.api.core.collection.SimplePair;
import com.temporal.api.core.engine.io.metadata.annotation.data.other.CustomBlockLootTable;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.loot.BlockLootTableProvider;
import com.temporal.api.core.event.data.loot.LootProviderStrategy;
import com.temporal.api.core.util.other.CollectionUtils;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomBlockLootTableStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(CustomBlockLootTable.class)) {
            field.setAccessible(true);
            DeferredBlock<?> registryObject = (DeferredBlock<?>) field.get(object);
            CustomBlockLootTable blockLootTable = field.getDeclaredAnnotation(CustomBlockLootTable.class);
            String[] additionalStrings = blockLootTable.additionalStrings();
            Integer[] additionalInts = Arrays.stream(blockLootTable.additionalInts()).boxed().toArray(Integer[]::new);
            Object[] additionalData = CollectionUtils.mergeArrays(additionalStrings, additionalInts);
            LootProviderStrategy providerStrategy = blockLootTable.value()
                    .getDeclaredConstructor()
                    .newInstance();
            BlockLootTableProvider.CUSTOM_LOOT.put(new SimplePair<>(registryObject, additionalData), providerStrategy);
        }
    }
}
