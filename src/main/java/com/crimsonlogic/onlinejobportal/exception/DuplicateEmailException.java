package com.crimsonlogic.onlinejobportal.exception;

public class DuplicateEmailException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEmailException(String message) {
        super(message);
    }
}
