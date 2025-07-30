package com.xproj.simpleMessagingApi.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApplicationRuntimeException.class})
    public ResponseEntity<?> handleCustomRuntimeException(ApplicationRuntimeException exp){
        Map<String, String> errors = exp.getErrors();
        var message = exp.getMessage();
        ApplicationException applicationException = new ApplicationException(false,message,errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(applicationException);
    }
}
