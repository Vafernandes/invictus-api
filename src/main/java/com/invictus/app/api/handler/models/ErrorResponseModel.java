package com.invictus.app.api.handler.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseModel {
    private Instant timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}
