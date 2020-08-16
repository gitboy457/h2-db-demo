package com.ace.h2dbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(RecordPresentException.class)
	public ResponseEntity<Object> handleRecordPresentException(RecordPresentException recordPresentException){
		Error error= new Error("2002",recordPresentException.getMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException recordNotFoundException){	
		Error error= new Error("2002",recordNotFoundException.getMessage());
		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
		
	}
	
}
