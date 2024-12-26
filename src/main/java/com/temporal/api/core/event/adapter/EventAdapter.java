package com.temporal.api.core.event.adapter;

import net.minecraftforge.eventbus.api.Event;

import java.util.Map;

public interface EventAdapter<T extends Event> {
    Event.Result adapt(T t);

    Event.Result adapt(T t, boolean condition);

    EventAdapter<T> adapt(T t, AdapterInitializer... initializers);

    EventAdapter<T> adapt(T t, boolean condition, AdapterInitializer... initializers);

    EventAdapter<T> adapt(T t, Map<AdapterInitializer, Boolean> initializers);

    EventAdapter<T> adapt(T t, boolean condition, Map<AdapterInitializer, Boolean> initializers);
}
