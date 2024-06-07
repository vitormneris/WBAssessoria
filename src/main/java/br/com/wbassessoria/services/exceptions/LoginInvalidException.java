package br.com.wbassessoria.services.exceptions;

public class LoginInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public LoginInvalidException() {
		super("E-mail or password not found.");
	}
}
