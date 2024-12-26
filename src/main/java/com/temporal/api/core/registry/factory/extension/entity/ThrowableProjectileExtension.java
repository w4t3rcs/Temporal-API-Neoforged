package com.temporal.api.core.registry.factory.extension.entity;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.registry.factory.common.EntityTypeFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraftforge.registries.RegistryObject;

public interface ThrowableProjectileExtension {
    default <T extends ThrowableProjectile> RegistryObject<EntityType<?>> createProjectile(String name, Size size, EntityType.EntityFactory<T> entityFactory) {
        EntityTypeFactory factory = InjectionContext.getInstance().getObject(EntityTypeFactory.class);
        return factory.create(name, entityFactory, size, MobCategory.MISC);
    }
}
