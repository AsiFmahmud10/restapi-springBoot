package com.example.demo.exception;


public class ResourceNotFoundException extends RuntimeException{
	
	private String fieldName;
	private long value;
	private String resourceName;
	
	public ResourceNotFoundException(String fieldName, long value, String resourceName) {
		super();
		this.fieldName = fieldName;
		this.value = value;
		this.resourceName = resourceName;
	}
	
	@Override
	public String getMessage() {
		
		return String.format("%s value %s of %s is not found",fieldName,value,resourceName )
;
	}
	
	
	

	
}
