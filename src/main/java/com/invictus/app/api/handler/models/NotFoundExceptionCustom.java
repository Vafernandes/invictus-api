package com.invictus.app.api.handler.models;

public class NotFoundExceptionCustom extends RuntimeException {
    public NotFoundExceptionCustom(String message) {
        super(message);
    }
}
