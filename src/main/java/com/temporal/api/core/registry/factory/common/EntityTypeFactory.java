package com.temporal.api.core.registry.factory.common;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.context.InjectionContext;
import com.temporal.api.core.util.other.RegistryUtils;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityTypeFactory implements ObjectFactory<EntityType<?>> {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = RegistryUtils.createRegistry(Registries.ENTITY_TYPE);

    public <T extends Entity> Holder<EntityType<?>> create(String name, EntityType.EntityFactory<T> entityFactory, Size size, MobCategory category) {
        return this.create(name, EntityType.Builder.of(entityFactory, category)
                .sized(size.width(), size.height()));
    }

    public Holder<EntityType<?>> create(String name, EntityType.Builder<?> builder) {
        return this.create(name, builder, ResourceUtils.createResourceKey(Registries.ENTITY_TYPE, name));
    }

    public Holder<EntityType<?>> create(String name, EntityType.Builder<?> builder, ResourceKey<EntityType<?>> entityType) {
        return this.create(name, () -> builder.build(entityType));
    }

    @Override
    public Holder<EntityType<?>> create(String name, Supplier<EntityType<?>> entitySupplier) {
        return ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public void register() {
        ENTITY_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
