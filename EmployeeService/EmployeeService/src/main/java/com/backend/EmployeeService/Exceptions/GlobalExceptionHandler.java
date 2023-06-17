package com.backend.EmployeeService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException excp){
		return new ResponseEntity<String>(excp.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= InputErrorException.class)
	public ResponseEntity<?> handleInputErrorException(InputErrorException excp){
		return new ResponseEntity<String>(excp.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	

}
