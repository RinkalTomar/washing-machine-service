package com.washingmachine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.washingmachine.exception.WasinghMachineAlreadyExistException;
import com.washingmachine.exception.WasinghMachineNotFoundException;
import com.washingmachine.model.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(WasinghMachineAlreadyExistException.class)
    public ResponseEntity<?> WashingMachineAlreadyExistException(WasinghMachineAlreadyExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(WasinghMachineNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(WasinghMachineNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
       ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
