package com.temporal.api.core.collection;

import java.util.ArrayDeque;
import java.util.function.Consumer;

public class TemporalArrayDeque<T> extends ArrayDeque<T> {
    @Override
    public void forEach(Consumer<? super T> action) {
        super.forEach(action);
        this.clear();
    }
}
