package com.washingmachine.exception;

public class WasinghMachineAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public WasinghMachineAlreadyExistException() {}
	
    public WasinghMachineAlreadyExistException(String message){
        super(message);
    }
}
