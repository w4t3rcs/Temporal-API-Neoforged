package com.temporal.api.core.registry.factory.common;

import com.temporal.api.core.engine.io.context.InjectionContext;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Holder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TriggerFactory implements ObjectFactory<CriterionTrigger<?>> {
    private final DeferredRegister<CriterionTrigger<?>> triggerTypes;

    public TriggerFactory() {
        this(InjectionContext.getFromInstance("trigger_types"));
    }

    public TriggerFactory(DeferredRegister<CriterionTrigger<?>> triggerTypes) {
        this.triggerTypes = triggerTypes;
    }

    @Override
    public Holder<CriterionTrigger<?>> create(String name, Supplier<CriterionTrigger<?>> criterionTriggerSupplier) {
        return triggerTypes.register(name, criterionTriggerSupplier);
    }

    @Override
    public void register() {
        triggerTypes.register(InjectionContext.getFromInstance(IEventBus.class));
    }

    @Override
    public DeferredRegister<CriterionTrigger<?>> getRegistry() {
        return triggerTypes;
    }
}
