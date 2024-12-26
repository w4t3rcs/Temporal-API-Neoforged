package com.temporal.api.core.registry.factory.extension.entity;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.EntityTypeFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.registries.RegistryObject;

public interface BoatExtension {
    default <T extends Boat> RegistryObject<EntityType<?>> createBoat(String name, EntityType.EntityFactory<T> entityFactory) {
        EntityTypeFactory factory = InjectionContext.getInstance().getObject(EntityTypeFactory.class);
        return factory.create(name, entityFactory, new Size(1.375f, 0.5625f), MobCategory.MISC);
    }
}
