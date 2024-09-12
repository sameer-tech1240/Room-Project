package com.room.api.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDetailsVO {
    private String errorCode;
    private String errorMessage;
    private boolean result;
    private String requestURI;
    private HttpStatus httpStatus;
}
