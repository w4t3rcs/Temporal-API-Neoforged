package com.temporal.api.core.event.adapter;

public interface AdapterInitializer {
    void initialize();

    void initialize(boolean condition);
}
