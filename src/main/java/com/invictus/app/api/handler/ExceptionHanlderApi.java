package com.invictus.app.api.handler;

import com.invictus.app.api.handler.models.ErrorResponseModel;
import com.invictus.app.api.handler.models.NotFoundExceptionCustom;
import com.invictus.app.api.handler.models.RuleExceptionCustom;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHanlderApi {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseModel notFounHandler(NotFoundExceptionCustom exception, HttpServletRequest request) {
        return ErrorResponseModel.builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseModel internalServerErrorHandler(Exception exception, HttpServletRequest request) {
        return ErrorResponseModel.builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseModel internalServerErrorHandler(RuleExceptionCustom exception, HttpServletRequest request) {
        return ErrorResponseModel.builder()
                .timestamp(Instant.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(request.getServletPath())
                .build();
    }
}
