package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {RuntimeExceptionCustom.class})
    protected ResponseEntity<Object> handleRuntimeExceptionCustom(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String[] message = ex.getMessage().split("::", 0);
        switch (message[0]){
            case "400": httpStatus = HttpStatus.BAD_REQUEST;
            case "404": httpStatus = HttpStatus.NOT_FOUND;
        }
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus,
                message[1],
                null
        );

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorResponse errorDTO = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, errorDTO, headers, HttpStatus.BAD_REQUEST, request
        );
    }
}
