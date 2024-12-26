package com.temporal.api.core.engine.io.context;

import java.util.List;

public interface Context {
    <T> T getObject(Class<? extends T> key);

    <T> List<? extends T> getObjects(Class<T> commonInterface);

    List<?> getAllObjects();

    <T> void putObject(Class<? extends T> key);

    <T> void putObject(T value);

    <T> void putObject(Class<? extends T> key, T value);
}
