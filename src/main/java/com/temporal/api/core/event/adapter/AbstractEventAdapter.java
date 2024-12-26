package com.temporal.api.core.event.adapter;

import net.minecraftforge.eventbus.api.Event;

import java.util.Map;

public abstract class AbstractEventAdapter<T extends Event> implements EventAdapter<T> {
    @Override
    public Event.Result adapt(T t) {
        return this.adapt(t, true);
    }

    public abstract Event.Result adapt(T t, boolean condition);

    @Override
    public EventAdapter<T> adapt(T t, AdapterInitializer... initializers) {
        return this.adapt(t, true, initializers);
    }

    public abstract EventAdapter<T> adapt(T t, boolean condition, AdapterInitializer... initializers);

    @Override
    public EventAdapter<T> adapt(T t, Map<AdapterInitializer, Boolean> initializers) {
        return this.adapt(t, true, initializers);
    }

    public abstract EventAdapter<T> adapt(T t, boolean condition, Map<AdapterInitializer, Boolean> initializers);
}
