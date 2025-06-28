package com.temporal.api.core.engine.io.context;

public interface ContextContainer<K, C extends Context> {
    C getContext(K key);

    C createContext(K key);

    void removeContext(K key);
}
