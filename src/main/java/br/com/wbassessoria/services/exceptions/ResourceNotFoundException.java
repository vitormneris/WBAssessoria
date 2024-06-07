package br.com.wbassessoria.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String data, Object id) {
		super("Resource not found. " + data + " " + id);
	}
}
