package com.temporal.api.core.engine.io.metadata.strategy;

public interface ObjectStrategy<T> {
    void execute(T t, Object object) throws Exception;
}
