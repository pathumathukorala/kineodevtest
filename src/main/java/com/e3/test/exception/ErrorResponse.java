package com.e3.test.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private String errorCode;

    private String errorDescription;

    private String httpStatusCode;
}
