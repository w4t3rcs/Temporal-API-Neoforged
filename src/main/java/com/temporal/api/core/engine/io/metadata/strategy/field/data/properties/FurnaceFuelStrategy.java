package com.temporal.api.core.engine.io.metadata.strategy.field.data.properties;

import com.temporal.api.core.engine.io.metadata.annotation.data.properties.FurnaceFuel;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import com.temporal.api.core.event.data.map.ApiDataMapProvider;
import com.temporal.api.core.event.data.map.FurnaceFuelDto;
import net.minecraft.core.Holder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;

public class FurnaceFuelStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(FurnaceFuel.class)) {
            field.setAccessible(true);
            FurnaceFuel fuel = field.getAnnotation(FurnaceFuel.class);
            FurnaceFuelDto fuelDto;
            Object o = field.get(object);
            if (o instanceof DeferredItem<?> item) {
                fuelDto = new FurnaceFuelDto(item, fuel.burnTime(), fuel.replace());
            } else if (o instanceof DeferredBlock<?> block) {
                fuelDto = new FurnaceFuelDto(Holder.direct(block.asItem()), fuel.burnTime(), fuel.replace());
            } else {
                throw new RuntimeException();
            }

            ApiDataMapProvider.FURNACE_FUELS.add(fuelDto);
        }
    }
}
