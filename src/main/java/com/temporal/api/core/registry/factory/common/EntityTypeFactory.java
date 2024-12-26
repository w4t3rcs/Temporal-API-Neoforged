package com.temporal.api.core.registry.factory.common;

import com.temporal.api.client.dto.Size;
import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EntityTypeFactory implements TypedFactory<EntityType<?>> {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = IOHelper.createRegistry(Registries.ENTITY_TYPE);

    public <T extends Entity> RegistryObject<EntityType<?>> create(String name, EntityType.EntityFactory<T> entityFactory, Size size, MobCategory category) {
        return this.create(name, EntityType.Builder.of(entityFactory, category)
                .sized(size.width(), size.height()));
    }

    public RegistryObject<EntityType<?>> create(String name, EntityType.Builder<?> builder) {
        return this.create(name, builder, ResourceKey.create(Registries.ENTITY_TYPE, IOHelper.createResourceLocation(name)));
    }

    public RegistryObject<EntityType<?>> create(String name, EntityType.Builder<?> builder, ResourceKey<EntityType<?>> entityType) {
        return this.create(name, () -> builder.build(entityType));
    }

    @Override
    public RegistryObject<EntityType<?>> create(String name, Supplier<EntityType<?>> entitySupplier) {
        return ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public RegistryObject<? extends EntityType<?>> createTyped(String name, Supplier<? extends EntityType<?>> entitySupplier) {
        return ENTITY_TYPES.register(name, entitySupplier);
    }

    @Override
    public void register() {
        ENTITY_TYPES.register(InjectionContext.getInstance().getObject(IEventBus.class));
    }
}
