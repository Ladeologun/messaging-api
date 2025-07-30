package com.xproj.simpleMessagingApi.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationResponseWrapper<T> {
    private String message;
    private T data;

    public ApplicationResponseWrapper(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
