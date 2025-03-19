package com.temporal.api.core.collection;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class TemporalHashMap<K, V> extends HashMap<K, V> {
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        super.forEach(action);
        this.clear();
    }
}
