package com.temporal.api.core.registry.factory.extension.entity;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.EntityTypeFactory;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;

public interface BoatExtension {
    default <T extends Boat> Holder<EntityType<?>> createBoat(String name, EntityType.EntityFactory<T> entityFactory) {
        EntityTypeFactory factory = InjectionContext.getFromInstance(EntityTypeFactory.class);
        return factory.create(name, entityFactory, new Size(1.375f, 0.5625f), MobCategory.MISC);
    }
}
