package com.temporal.api.core.engine.io.metadata.strategy.field.event.fml;

import com.temporal.api.core.engine.event.handler.FMLClientSetupEventHandler;
import com.temporal.api.core.engine.io.metadata.annotation.event.fml.SetupWoodTypeClient;
import com.temporal.api.core.engine.io.metadata.strategy.field.FieldAnnotationStrategy;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.lang.reflect.Field;

public class SetupWoodTypeClientStrategy implements FieldAnnotationStrategy {
    @Override
    public void execute(Field field, Object object) throws Exception {
        if (field.isAnnotationPresent(SetupWoodTypeClient.class)) {
            field.setAccessible(true);
            WoodType woodType = (WoodType) field.get(object);
            FMLClientSetupEventHandler.WOOD_TYPES.add(woodType);
        }
    }
}
