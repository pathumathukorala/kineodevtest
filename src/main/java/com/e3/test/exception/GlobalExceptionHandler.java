package com.e3.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleValidationException(ValidationException ex) {
        ErrorResponse response = new ErrorResponse();

        ErrorCode errorCode = ErrorCode.findByCode(ex.getMessage());
        response.setErrorCode(errorCode.code);
        response.setErrorDescription(errorCode.errorMessage);
        response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return response;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse();

        ErrorCode errorCode = ErrorCode.findByCode(ex.getMessage());
        response.setErrorCode(errorCode.code);
        response.setErrorDescription(errorCode.errorMessage);
        response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        ErrorResponse response = new ErrorResponse();

        response.setErrorCode(ErrorCode.ERR_005.code);
        response.setErrorDescription(errorMap.toString());
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.toString());

        return response;
    }
}
