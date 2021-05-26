package com.washingmachine.exception;

public class WasinghMachineNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public WasinghMachineNotFoundException() {}
	
    public WasinghMachineNotFoundException(String message){
        super(message);
    }
    
}
