package com.xproj.simpleMessagingApi.exceptionHandlers;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ApplicationRuntimeException extends RuntimeException {
    private final Map<String, String> errors;
    public ApplicationRuntimeException(HashMap<String, String> error, String message) {
        super(message);
        this.errors = error;
    }
}
