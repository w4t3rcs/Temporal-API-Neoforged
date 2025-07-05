package com.temporal.api.core.registry.factory.common;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.context.InjectionPool;
import com.temporal.api.core.util.other.ResourceUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityTypeFactory implements ObjectFactory<EntityType<?>> {
    private final DeferredRegister<EntityType<?>> entityTypes;

    public EntityTypeFactory() {
        this(InjectionPool.getFromInstance("$EntityTypes"));
    }

    public EntityTypeFactory(DeferredRegister<EntityType<?>> entityTypes) {
        this.entityTypes = entityTypes;
    }

    public <T extends Entity> Holder<EntityType<?>> create(String name, EntityType.EntityFactory<T> entityFactory, Size size, MobCategory category) {
        return this.create(name, EntityType.Builder.of(entityFactory, category)
                .sized(size.width(), size.height()));
    }

    public Holder<EntityType<?>> create(String name, EntityType.Builder<?> builder) {
        return this.create(name, builder, ResourceUtils.createResourceKey(Registries.ENTITY_TYPE, name));
    }

    public Holder<EntityType<?>> create(String name, EntityType.Builder<?> builder, ResourceKey<EntityType<?>> entityType) {
        return this.create(name, () -> builder.build(entityType.registry().toString()));
    }

    @Override
    public Holder<EntityType<?>> create(String name, Supplier<EntityType<?>> entitySupplier) {
        return entityTypes.register(name, entitySupplier);
    }

    @Override
    public DeferredRegister<EntityType<?>> getRegistry() {
        return entityTypes;
    }
}
