package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.IOHelper;
import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TriggerFactory implements ObjectFactory<CriterionTrigger<?>> {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES = IOHelper.createRegistry(Registries.TRIGGER_TYPE);

    @Override
    public Holder<CriterionTrigger<?>> create(String name, Supplier<CriterionTrigger<?>> criterionTriggerSupplier) {
        return TRIGGER_TYPES.register(name, criterionTriggerSupplier);
    }

    @Override
    public void register() {
        TRIGGER_TYPES.register(InjectionContext.getFromInstance(IEventBus.class));
    }
}
