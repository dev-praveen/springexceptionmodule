package com.spring.api;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer errorCode;
    private String errorType;
    private String errorDescription;
}
