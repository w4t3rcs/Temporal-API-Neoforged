package com.temporal.api.core.util.exception;

public class AddingToTabException extends RuntimeException {
    public AddingToTabException() {
    }

    public AddingToTabException(String message) {
        super(message);
    }

    public AddingToTabException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddingToTabException(Throwable cause) {
        super(cause);
    }
}
