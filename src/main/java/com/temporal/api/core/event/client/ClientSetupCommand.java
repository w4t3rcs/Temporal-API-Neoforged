package com.temporal.api.core.event.client;

import java.util.List;

public interface ClientSetupCommand<T> {
    void execute(List<T> source);
}
