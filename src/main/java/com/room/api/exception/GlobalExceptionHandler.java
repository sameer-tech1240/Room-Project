package com.room.api.exception;

import com.room.api.vo.ErrorDetailsVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RMException.class)
    public ResponseEntity<?> multiExceptionHandler(RMException rmException, HttpServletRequest httpServletRequest) {
        ErrorDetailsVO errorDetailsVO = new ErrorDetailsVO();
        errorDetailsVO.setErrorCode(rmException.getCode());
        errorDetailsVO.setErrorMessage(rmException.getMessage());
        errorDetailsVO.setRequestURI(httpServletRequest.getRequestURI());
        errorDetailsVO.setResult(false);
        errorDetailsVO.setHttpStatus(rmException.getHttpStatus());
        return ResponseEntity.status(rmException.getHttpStatus()).body(errorDetailsVO);
    }

}
