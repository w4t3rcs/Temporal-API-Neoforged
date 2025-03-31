package com.temporal.api.core.collection;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.function.Consumer;

public class TemporalArrayDeque<T> extends ArrayDeque<T> {
    private byte count;
    private final byte limit;

    public TemporalArrayDeque() {
        this((byte) 1);
    }

    public TemporalArrayDeque(byte limit) {
        this((byte) 0, limit);
    }

    public TemporalArrayDeque(byte count, byte limit) {
        super();
        this.count = count;
        this.limit = limit;
    }

    public TemporalArrayDeque(Collection<? extends T> data) {
        this(data, (byte) 1);
    }

    public TemporalArrayDeque(Collection<? extends T> data, byte limit) {
        this(data, (byte) 0, limit);
    }

    public TemporalArrayDeque(Collection<? extends T> data, byte count, byte limit) {
        super(data);
        this.count = count;
        this.limit = limit;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        super.forEach(action);
        count++;
        if (count == limit) {
            this.clear();
            count = 0;
        }
    }
}
