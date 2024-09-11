package com.room.api.exception;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.room.api.common.RoomApiCommonDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> exceptionHandller(ResourceNotFound found){
    	     String message = found.getMessage();
    	 return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
    	 
    	 
		  
		
	}
	
}
